package com.gpcsoft.smallscale.expert.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.domain.Training;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.TrainingService;
import com.gpcsoft.srplatform.auth.domain.User;


/**
 * @gpcsoft.view value="trainingListView"
 *  url="view/smallscale/expert/training_list.jsp"
 * @gpcsoft.view value="trainingFormView"
 *  url="view/smallscale/expert/training_form.jsp"
 * 
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Training.class})
@RequestMapping("/TrainingController.do")//页面请求路径,可修改
public class TrainingController extends AnnotationMultiController<Training> {

	@Autowired(required=true) @Qualifier("trainingServiceImpl")
	private TrainingService trainingService;

	/** 
	 * Description : 跳转到培训信息列表页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toTrainingList")
	public ModelAndView toTrainingList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		// 用户的专家信息
		if(null != user.getExtInfo()){
			model.put("expertInfoId", user.getExtInfo().getObjId());
		}
		return new ModelAndView("trainingListView", model);	
    }
	
	/** 
	 * Description : 保存培训信息
	 * Create Date: 2010-11-26下午03:28:52 by guoyr  Modified Date: 2010-11-26下午03:28:52 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveTraining")
	public ModelAndView saveTraining(HttpServletRequest request, Training training) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 如果是提交，设为待审核状态
		if(request.getParameter("saveType").equals("submit")){
			training.setAuditStatus(ExpertEnum.AWAIT_EXAM);
		}else {
			// 如果是保存，设为草稿状态
			training.setAuditStatus(ExpertEnum.DRAFT_EXAM);
		}
		trainingService.saveTraining(training);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
	
	/** 
	 * Description : 跳转到修改或新增培训信息表单页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		String objId = request.getParameter("objId");
		Training training  = null;
		if(StringUtils.hasLength(objId)) {
			training  = trainingService.get(objId);
		}else {
			training = new Training();
			User user = AuthenticationHelper.getCurrentUser(true);
			
			// 专家信息
			training.setExpertInfo((ExpertInfo) user.getExtInfo());
		}
		model.put("training", training);
		
		return new ModelAndView("trainingFormView", model);
	}
	
	/** 
	 * Description : 审核专家培训信息
	 * Create Date: 2010-11-29上午10:22:40 by guoyr  Modified Date: 2010-11-29上午10:22:40 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditTraining")
	public ModelAndView auditTraining(HttpServletRequest request,String objIds,String auditStatus) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		trainingService.updateTrainingAuditStatus(objIds, auditStatus);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
}
