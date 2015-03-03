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
import com.gpcsoft.smallscale.expert.domain.Experience;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.ExperienceService;
import com.gpcsoft.srplatform.auth.domain.User;


/**
 * @gpcsoft.view value="experienceListView"
 *  url="view/smallscale/expert/experience_list.jsp"
 * @gpcsoft.view value="experienceFormView"
 *  url="view/smallscale/expert/experience_form.jsp"
 * 
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Experience.class})
@RequestMapping("/ExperienceController.do")//页面请求路径,可修改
public class ExperienceController extends AnnotationMultiController<Experience> {

	@Autowired(required=true) @Qualifier("experienceServiceImpl")
	private ExperienceService experienceService;

	/** 
	 * Description : 跳转到任职经历列表页面 
	 * Create Date: 2010-11-26上午10:15:24 by guoyr  Modified Date: 2010-11-26上午10:15:24 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toExperienceList")
	public ModelAndView toExperienceList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		// 用户的专家信息
		if(null != user.getExtInfo()){
			model.put("expertInfoId", user.getExtInfo().getObjId());
		}
		return new ModelAndView("experienceListView", model);	
    }
	
	/** 
	 * Description : 保存任职经历信息
	 * Create Date: 2010-11-26下午03:28:52 by guoyr  Modified Date: 2010-11-26下午03:28:52 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveExperience")
	public ModelAndView saveExperience(HttpServletRequest request, Experience experience) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 如果是提交，设为待审核状态
		if(request.getParameter("saveType").equals("submit")){
			experience.setAuditStatus(ExpertEnum.AWAIT_EXAM);
		}else {
			// 如果是保存，设为草稿状态
			experience.setAuditStatus(ExpertEnum.DRAFT_EXAM);
		}
		experienceService.saveExperience(experience);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
	
	/** 
	 * Description : 跳转到修改或新增任职经历表单页面 
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
		Experience experience = null;
		if(StringUtils.hasLength(objId)) {
			experience  = experienceService.get(objId);
		}else {
			experience = new Experience();
			User user = AuthenticationHelper.getCurrentUser(true);
			
			// 专家信息
			experience.setExpertInfo((ExpertInfo) user.getExtInfo());
		}
		model.put("experience", experience);
		
		return new ModelAndView("experienceFormView", model);
	}
	
	/** 
	 * Description : 审核专家任职经历
	 * Create Date: 2010-11-29上午10:22:40 by guoyr  Modified Date: 2010-11-29上午10:22:40 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditExperience")
	public ModelAndView auditExperience(HttpServletRequest request,String objIds,String auditStatus) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		experienceService.updateExperienceAuditStatus(objIds, auditStatus);
		return new ModelAndView(Constants.JSON_VIEW, model);	
    }
}
