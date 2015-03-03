package com.gpcsoft.agreement.bargin.requirement.controller;

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

import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementRegService;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="toRequirementRegFormView"
  *  url="view/agreement/purchaserequire/showrequirement/requirement_reg_form.jsp"
  *  
  * @gpcsoft.view value="toRequirementRegListMangerView"
  *  url="view/agreement/purchaserequire/requirement_reg_list_manager.jsp"
  *  
  * @gpcsoft.view value="toRequirementRegListView"
  *  url="view/agreement/purchaserequire/requirement_reg_list.jsp"
  *  
  * @gpcsoft.view value="toRequirementRegDetailView"
  *  url="view/agreement/purchaserequire/requirement_reg_detail.jsp"
  *  
  * @gpcsoft.view value="toRequirementRegAuditView"
  *  url="view/agreement/purchaserequire/requirement_reg_audit.jsp"
  *  
  * @gpcsoft.view value="toMyRequirementRegView"
  *  url="view/agreement/purchaserequire/requirement_list_mine.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RequirementReg.class})
@RequestMapping("/RequirementRegController.do")//页面请求路径,可修改
public class RequirementRegController extends AnnotationMultiController<RequirementReg> {

	@Autowired(required=true) @Qualifier("requirementRegServiceImpl")
	private RequirementRegService requirementRegService;
	
	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;

	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
	 * Description :  跳转到我报名的需求(供应商)
	 * Create Date: 2011-5-3上午09:48:05 by yucy  Modified Date: 2011-5-3上午09:48:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toMyRequirementReg")
	public ModelAndView toMyRequirementReg(HttpServletRequest request,String objId) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		return new ModelAndView("toMyRequirementRegView",model);
	}
	
	/** 
	 * Description :  跳转到需求报名页面
	 * Create Date: 2011-4-1下午12:06:00 by yucy  Modified Date: 2011-4-1下午12:06:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toRequirementRegForm")
	public ModelAndView toRequirementRegForm(HttpServletRequest request,String objId) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		User currentUser = AuthenticationHelper.getCurrentUser(true);

		if(StringUtils.hasLength(objId)){
			model.put("requirementReg", requirementRegService.get(objId));
			model.put("currentEmpId", currentUser.getEmp().getObjId());

		}else{
			RequirementReg requirementReg = new RequirementReg();
			OrgInfo regOrg = orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true);
			requirementReg.setRegOrg(regOrg);
			requirementReg.setLinkMen(currentUser.getEmp().getName());
			requirementReg.setLinkTel(currentUser.getEmp().getMobile());
			requirementReg.setAddress(currentUser.getEmp().getAddress());
			requirementReg.setIdCard(currentUser.getEmp().getIdCard());
			requirementReg.setZipCode(currentUser.getEmp().getZipCode());
			
			model.put("requirementReg", requirementReg);
			
			model.put("currentEmpId", currentUser.getEmp().getObjId());
		}
		return new ModelAndView("toRequirementRegFormView",model);
	}
	
	/** 
	 * Description :  校验报名身份证号码的唯一性
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkedIdCard")   
	public ModelAndView checkedIdCard(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("idCard", request.getParameter("idCard"));
		param.put("requirementId", request.getParameter("requirementId"));

		Boolean result =  requirementRegService.checkedIdCard(param);
		Map<String, Object> model  = new HashMap<String, Object>();
		if(result){
			model.put(Constants.SUCCESS, true);//校验成功  
		}else{
			model.put(Constants.FAILURE, true);//校验失败
		}
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * Description :  检查是否报名过
	 * Create Date: 2011-4-1下午02:45:38 by yucy  Modified Date: 2011-4-1下午02:45:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=checkRequirementReged")
	public ModelAndView checkRequirementReged(HttpServletRequest request ,String objId) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		Map<String, Object>  param = new HashMap<String, Object>();
		
		OrgInfo regOrg = orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true);
		param.put("requirementId", objId);
		param.put("orgId", regOrg.getObjId());
		
		Boolean isReged = requirementRegService.checkRequirementReged(param);
		model.put("isReged", isReged);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  保存需求报名信息
	 * Create Date: 2011-4-1下午01:52:33 by yucy  Modified Date: 2011-4-1下午01:52:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=saveRequirementReg")
	public ModelAndView saveRequirementReg(HttpServletRequest request, RequirementReg requirementReg ,SessionStatus status) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		Map<String, Object>  param = new HashMap<String, Object>();

		String type = request.getParameter("type");
		String empId = request.getParameter("currentEmpId");
		param.put("type", type);
		param.put("empId", empId);
		requirementRegService.saveRequirementReg(requirementReg,param);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转到需求报名管理列表页面(管理员)
	 * Create Date: 2011-4-1下午04:13:15 by yucy  Modified Date: 2011-4-1下午04:13:15 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toRequirementRegListManger")
	public ModelAndView toRequirementRegListManger(HttpServletRequest request) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		model.put("requirement", requirementService.get(request.getParameter("objId")));
		return new ModelAndView("toRequirementRegListMangerView",model);
	}
	
	/** 
	 * Description :  跳转到需求报名管理列表页面(非管理员)
	 * Create Date: 2011-4-1下午04:13:49 by yucy  Modified Date: 2011-4-1下午04:13:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toRequirementRegList")
	public ModelAndView toRequirementRegList(HttpServletRequest request) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		model.put("requirement", requirementService.get(request.getParameter("objId")));
		return new ModelAndView("toRequirementRegListView",model);
	}
	
	/** 
	 * Description :  跳转到需求报名详情
	 * Create Date: 2011-4-1下午05:11:01 by yucy  Modified Date: 2011-4-1下午05:11:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toRequirementRegDetail")
	public ModelAndView toRequirementRegDetail(HttpServletRequest request ,String objId) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		
		model.put("requirementReg", requirementRegService.get(objId));

		return new ModelAndView("toRequirementRegDetailView",model);
	}
	
	/** 
	 * Description :  跳转到需求报名审核页面
	 * Create Date: 2011-4-1下午05:14:58 by yucy  Modified Date: 2011-4-1下午05:14:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping( params="method=toRequirementRegAudit")
	public ModelAndView toRequirementRegAudit(HttpServletRequest request ,String objId) throws Exception{
		Map<String, Object>  model = new HashMap<String, Object>();
		
		model.put("requirementReg",  requirementRegService.get(objId));

		return new ModelAndView("toRequirementRegAuditView",model);
	}
	
	/** 
	 * Description :  更新需求报名的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStatus")
	public ModelAndView updateStatus(HttpServletRequest request ,String objIds) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();
		if(StringUtils.hasLength(objIds)){
			param.put("auditStatus", request.getParameter("auditStatus"));
			requirementRegService.updateStatus(objIds, param);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得报名集合
	 * Create Date: 2011-4-7上午11:30:44 by yucy  Modified Date: 2011-4-7上午11:30:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getRequirementRegList")
	public ModelAndView getRequirementRegList(HttpServletRequest request) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("requirement.objId", request.getParameter("requirementIds"));
		model.put("requirementRegList", requirementRegService.getRequirementRegList(param));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		//供应商列表
		if("supplier".equals(request.getParameter("orgType"))){
			query.getQueryParam().and(new QueryParam("regOrg.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
		}
		return query;
	}
}
