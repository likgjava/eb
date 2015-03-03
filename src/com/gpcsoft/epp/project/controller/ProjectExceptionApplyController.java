package com.gpcsoft.epp.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.service.ProjectExceptionApplyService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="projectExceptionApplyDetailView"
  *  url="view/es/planform/project/projectExceptionApplyDetail.jsp"
  * @gpcsoft.view value="toViewProjectExceptionApply"
  *  url="view/es/planform/project/projectExceptionApplyView.jsp"
  * @gpcsoft.view value="toUpdateProjectExceptionApply"
  *  url="view/es/planform/project/projectExceptionUpdate.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectExceptionApply.class})
@RequestMapping("/ProjectExceptionApplyController.do")//页面请求路径,可修改
public class ProjectExceptionApplyController extends AnnotationMultiController<ProjectExceptionApply> {

	@Autowired(required=true) @Qualifier("projectExceptionApplyServiceImpl")
	private ProjectExceptionApplyService projectExceptionApplyService;

	/** 
	 * FuncName:saveExceptionApply
	 * Description : 代理机构：保存异常申请
	 * @param   request,projectExceptionApply:异常申请,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:00:09 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午04:00:09   
	 */
	@RequestMapping(params="method=saveExceptionApply")
	public ModelAndView saveExceptionApply(HttpServletRequest request,ProjectExceptionApply projectExceptionApply,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||saveExceptionApply\n");
		projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		projectExceptionApplyService.saveExceptionApply(projectExceptionApply);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:saveUpdateExceptionApply
	 * Description:代理机构：保存修改异常申请
	 * @param   request,projectExceptionApply:异常申请,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:00:09 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午04:00:09    
	 */
	@RequestMapping(params="method=saveUpdateExceptionApply")
	public ModelAndView saveUpdateExceptionApply(HttpServletRequest request,ProjectExceptionApply projectExceptionApply,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||saveUpdateExceptionApply\n");
		projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		projectExceptionApplyService.saveUpdateExceptionApply(projectExceptionApply);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/** 
	 * FuncName:saveSubmitExceptionApply
	 * Description :代理机构：提交异常申请
	 * @param   request,projectExceptionApply:异常申请对象,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:00:09 
 	 * @Modifier yangx
	 * @Modified Date: 2010-10-11下午04:00:09     
	 */
	@RequestMapping(params="method=saveSubmitExceptionApply")
	public ModelAndView saveSubmitExceptionApply(HttpServletRequest request,ProjectExceptionApply projectExceptionApply,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||saveSubmitExceptionApply\n");
		projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		projectExceptionApplyService.saveSubmitExceptionApply(projectExceptionApply);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:saveSubmitUpdateExceptionApply
	 * Description:代理机构：提交修改异常申请
	 * @param   request,projectExceptionApply:异常申请对象,status
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-10-11下午04:00:09
	 * @Modifier yangx
	 * @Modified Date: 2010-10-11下午04:00:09    
	 */
	@RequestMapping(params="method=saveSubmitUpdateExceptionApply")
	public ModelAndView saveSubmitUpdateExceptionApply(HttpServletRequest request,ProjectExceptionApply projectExceptionApply,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||saveSubmitUpdateExceptionApply\n");
		projectExceptionApply.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		projectExceptionApplyService.saveSubmitUpdateExceptionApply(projectExceptionApply);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:toAuditProjectException
	 * Description :采购办：跳转到审核暂停项目页面
	 * @param   request
	 * @return  ModelAndView
	 * @author  yangx
	 * @Create Date: 2010-10-11下午07:13:15 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午07:13:15   
	 */
	@RequestMapping(params="method=toAuditProjectException")
	public ModelAndView toAuditProjectException(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||toAuditProjectException\n");
		String projectId = request.getParameter("projectId");
		ProjectExceptionApply projectExceptionApply = projectExceptionApplyService.getProjectExceptionApplyByProjectId(projectId);
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("projectExceptionApply",projectExceptionApply);
		return new ModelAndView("projectExceptionApplyDetailView", model);
	}
	
	/** 
	 * FuncName:auditProjectException
	 * Description:采购办:审核异常申请
	 * @param   request,status
	 * @return  ModelAndView
	 * @author  yangx
	 * @Create Date: 2010-10-12上午10:45:02 
	 * @Modifier yangx
	 * @Modified Date: 2010-10-12上午10:45:02    
	 */
	@RequestMapping(params="method=auditProjectException")
	public ModelAndView auditProjectException(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||auditProjectException\n");
		String auditStatus = request.getParameter("auditStatus");
		String projectExceptionApplyId = request.getParameter("projectExceptionApplyId");
		projectExceptionApplyService.saveAuditStatusExceptionApply(projectExceptionApplyId,auditStatus,"");
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getProjectExceptionList
	 * Description :代理机构：获取异常申请列表
	 * @param   request
	 * @return  ModelAndView
	 * @author   yangx
	 * @Create Date: 2010-10-12上午10:50:13 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-10-12上午10:50:13    
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getProjectExceptionList")
	public ModelAndView getProjectExceptionList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||getProjectExceptionList\n");
		String adviceProcway = request.getParameter("adviceProcway");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		queryObject.getQueryParam().and(new QueryParam("adviceProcway",QueryParam.OPERATOR_EQ,adviceProcway));
		queryObject.getQueryParam().and(new QueryParam("agencies",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		Page page = prePage(request);
		page = projectExceptionApplyService.getProjectExceptionList(page,queryObject);
		Map model = new HashMap();
		model.put(FrameJsonView.INCLUDED_PROPERTIES,new String[]{"project","project.projCode"});
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:toViewProjectExceptionApply
	 * Description : 代理机构：查看异常申请
	 * @param   request
	 * @return  ModelAndView
	 * @author    yangx
	 * @Create Date: 2010-10-12下午02:55:29 
	 * @Modifier   yangx
	 * @Modified Date: 2010-10-12下午02:55:29   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toViewProjectExceptionApply")
	public ModelAndView toViewProjectExceptionApply(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||toViewProjectExceptionApply\n");
		String projectExceptionApplyId = request.getParameter("projectExceptionApplyId");
		ProjectExceptionApply projectExceptionApply = projectExceptionApplyService.getProjectExceptionApplyByObjId(projectExceptionApplyId);
		Map model = new HashMap();
		model.put("projectExceptionApply",projectExceptionApply);
		return new ModelAndView("toViewProjectExceptionApply", model);
	}
	
	/** 
	 * FuncName:toUpdateProjectExceptionApply
	 * Description :代理机构：跳转到修改异常申请页面
	 * @param   request
	 * @return ModelAndView
	 * @author    yangx  
	 * @Create Date: 2010-10-12下午03:19:33 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-12下午03:19:33     
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toUpdateProjectExceptionApply")
	public ModelAndView toUpdateProjectExceptionApply(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectExceptionApplyController||toUpdateProjectExceptionApply\n");
		String projectExceptionApplyId = request.getParameter("projectExceptionApplyId");
		ProjectExceptionApply projectExceptionApply = projectExceptionApplyService.getProjectExceptionApplyByObjId(projectExceptionApplyId);
		Map model = new HashMap();
		model.put("projectExceptionApply",projectExceptionApply);
		return new ModelAndView("toUpdateProjectExceptionApply", model);
	}
}
