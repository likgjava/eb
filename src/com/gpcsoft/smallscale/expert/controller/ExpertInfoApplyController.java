package com.gpcsoft.smallscale.expert.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.domain.ExpertInfoApply;
import com.gpcsoft.smallscale.expert.enumeration.ExpertEnum;
import com.gpcsoft.smallscale.expert.service.ExpertInfoApplyService;
import com.gpcsoft.smallscale.expert.service.ExpertInfoService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="applyConsultantView"
  *  url="view/smallscale/expert/consultant_apply.jsp"
  *  
  * @gpcsoft.view value="applyReviewersView"
  *  url="view/smallscale/expert/reviewers_apply.jsp"
  *  
  * @gpcsoft.view value="auditApplyExpertView"
  *  url="view/smallscale/expert/expert_apply_audit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ExpertInfoApply.class})
@RequestMapping("/ExpertInfoApplyController.do")//页面请求路径,可修改
public class ExpertInfoApplyController extends AnnotationMultiController<ExpertInfoApply> {

	@Autowired(required=true) @Qualifier("expertInfoApplyServiceImpl")
	private ExpertInfoApplyService expertInfoApplyService;
	
	@Autowired(required=true) @Qualifier("expertInfoServiceImpl")
	private ExpertInfoService expertInfoService;
	
	/** 
	 * Description :  跳转到专家角色申请页面
	 * Create Date: 2011-1-6上午11:34:52 by likg  Modified Date: 2011-1-6上午11:34:52 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toApplyExpertInfoView")
    public ModelAndView toApplyExpertInfoView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		User user=AuthenticationHelper.getCurrentUser(true);
		
		String applyType = request.getParameter("applyType");
		String viewName = request.getParameter("viewName");
		
		if(ExpertEnum.CONSULTANT.startsWith(applyType)){ //咨询员
			applyType = ExpertEnum.CONSULTANT;
		}else if(ExpertEnum.REVIEWERS.startsWith(applyType)){ //评审员
			applyType = ExpertEnum.REVIEWERS;
		}
		
		//获取申请的专家信息
		ExpertInfo expertInfo = expertInfoService.getUserExpertInfo(user.getObjId());
		List<ExpertInfoApply> applyExpertList = expertInfoApplyService.getApplyExpertList(expertInfo.getObjId(), applyType);
		ExpertInfoApply applyExpert = (applyExpertList.size()>0 ? applyExpertList.get(0) : null);
		
		if(ExpertEnum.CONSULTANT.equals(applyType)) {//咨询员申请页面
			if(applyExpert != null) {
				model.put("consultantAuditStatus", applyExpert.getAuditStatus());
				model.put("consultantObjId", applyExpert.getObjId());
			}
		}else if(ExpertEnum.REVIEWERS.equals(applyType)) {//评审员申请页面
			if(applyExpert != null) {
				model.put("reviewersAuditStatus", applyExpert.getAuditStatus());
				model.put("reviewersObjId", applyExpert.getObjId());
			}
		}
		model.put("currentExpertId", expertInfo.getObjId());
		
		if(!StringUtils.hasLength(viewName)) {
			viewName = Constants.JSON_VIEW;
		}
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  申请为咨询员，保存信息
	 * Create Date: 2011-1-6下午01:19:17 by likg  Modified Date: 2011-1-6下午01:19:17 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyConsultant")
	public ModelAndView saveApplyConsultant(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyExpertStr = request.getParameter("applyExpertStr");
		 
		 ExpertInfoApply expertInfoApply = JsonUtils.json2Bean(applyExpertStr, ExpertInfoApply.class);
		 if(!StringUtils.hasLength(expertInfoApply.getObjId())) {
			 expertInfoApply.setObjId(null);
		 }
		 expertInfoApply.setApplyType(ExpertEnum.CONSULTANT);//咨询员角色类型
		 expertInfoApply.setApplyTime(new Date());
		 expertInfoApply.setCreateTime(new Date());
		 expertInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 expertInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 expertInfoApplyService.save(expertInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  申请为评审员，保存信息
	 * Create Date: 2011-1-6下午01:19:17 by likg  Modified Date: 2011-1-6下午01:19:17 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyReviewers")
	public ModelAndView saveApplyReviewers(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String applyExpertStr = request.getParameter("applyExpertStr");
		
		ExpertInfoApply expertInfoApply = JsonUtils.json2Bean(applyExpertStr, ExpertInfoApply.class);
		if(!StringUtils.hasLength(expertInfoApply.getObjId())) {
			expertInfoApply.setObjId(null);
		}
		expertInfoApply.setApplyType(ExpertEnum.REVIEWERS);//评审员角色类型
		expertInfoApply.setApplyTime(new Date());
		expertInfoApply.setCreateTime(new Date());
		expertInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		expertInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		
		expertInfoApplyService.save(expertInfoApply);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到专家申请的审核页面
	 * Create Date: 2011-1-6下午02:07:26 by likg  Modified Date: 2011-1-6下午02:07:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditApplyExpertView")
    public ModelAndView toAuditApplyExpertView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String expertId = request.getParameter("expertId");
		String applyType = request.getParameter("applyType");
		
		List<ExpertInfoApply> applyExpertList = expertInfoApplyService.getApplyExpertList(expertId, applyType);
		ExpertInfoApply applyExpert = (applyExpertList.size()>0 ? applyExpertList.get(0) : new ExpertInfoApply());
		model.put("applyExpert", applyExpert);
		
		return new ModelAndView("auditApplyExpertView", model);
	}
	
	/** 
	 * Description :  审核专家的申请
	 * Create Date: 2011-1-6下午02:26:03 by likg  Modified Date: 2011-1-6下午02:26:03 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditApplyExpert")
    public ModelAndView auditApplyExpert(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("objId", request.getParameter("objId"));
		params.put("expertId", request.getParameter("expertId"));
		params.put("auditStatus", request.getParameter("auditStatus"));
		params.put("applyType", request.getParameter("applyType"));
		params.put("opinion", request.getParameter("opinion"));
		
		expertInfoApplyService.auditApplyExpert(params);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

}
