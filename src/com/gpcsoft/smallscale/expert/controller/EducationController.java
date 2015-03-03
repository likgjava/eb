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
import com.gpcsoft.smallscale.expert.domain.Education;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.EducationService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="educationListView"
 *  url="view/smallscale/expert/education_list.jsp"
 * @gpcsoft.view value="educationFormView"
 *  url="view/smallscale/expert/education_form.jsp"
 * 
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Education.class})
@RequestMapping("/EducationController.do")//页面请求路径,可修改
public class EducationController extends AnnotationMultiController<Education> {

	@Autowired(required=true) @Qualifier("educationServiceImpl")
	private EducationService educationService;

	/** 
	 * Description : 跳转到教育经历列表页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toEducationList")
	public ModelAndView toEducationList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		// 用户的专家信息
		if(null != user.getExtInfo()){
			model.put("expertInfoId", user.getExtInfo().getObjId());
		}
		return new ModelAndView("educationListView", model);	
    }
	
	/** 
	 * Description : 保存教育经历信息
	 * Create Date: 2010-11-26下午03:28:52 by guoyr  Modified Date: 2010-11-26下午03:28:52 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveEducation")
	public ModelAndView saveEducation(HttpServletRequest request, Education education) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 如果是提交，设为待审核状态
		if(request.getParameter("saveType").equals("submit")){
			education.setAuditStatus(ExpertEnum.AWAIT_EXAM);
		}else{
			// 如果是保存，设为草稿状态
			education.setAuditStatus(ExpertEnum.DRAFT_EXAM);
		}
		educationService.saveEducation(education);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
	
	/** 
	 * Description : 跳转到修改或新增教育经历表单页面 
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
		Education education  = null;
		if(StringUtils.hasLength(objId)) {
			education  = educationService.get(objId);
		}else {
			education = new Education();
			User user = AuthenticationHelper.getCurrentUser(true);
			
			// 专家信息
			education.setExpertInfo((ExpertInfo) user.getExtInfo());
		}
		model.put("education", education);
		
		return new ModelAndView("educationFormView", model);
	}
	
	/** 
	 * Description : 审核专家教育经历 
	 * Create Date: 2010-11-29上午10:22:40 by guoyr  Modified Date: 2010-11-29上午10:22:40 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditEducation")
	public ModelAndView auditEducation(HttpServletRequest request,String objIds,String auditStatus) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		educationService.updateEducationAuditStatus(objIds, auditStatus);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
}
