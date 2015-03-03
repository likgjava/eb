package com.gpcsoft.bizplatform.organization.controller;

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
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.bizplatform.organization.service.OrgInfoApplyService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
*  @gpcsoft.view value="toApplyManufactorView"
*  url="view/bizplatform/organization/manager/manufactor_apply.jsp"
*  
*  @gpcsoft.view value="toApplyServerView"
*  url="view/bizplatform/organization/manager/server_apply.jsp"
*  
*  @gpcsoft.view value="toAuditApplyOrgView"
*  url="view/bizplatform/organization/manager/organization_apply_audit.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgInfoApply.class})
@RequestMapping("/OrgInfoApplyController.do")//页面请求路径,可修改
public class OrgInfoApplyController extends AnnotationMultiController<OrgInfoApply> {

	@Autowired(required=true) @Qualifier("orgInfoApplyServiceImpl")
	private OrgInfoApplyService orgInfoApplyService;
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoServiceImpl;
	
	/** 
	 * Description :  获得申请状态
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getApplyAuditStatus")
    public ModelAndView getApplyAuditStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String applyType = request.getParameter("applyType");
		User user=AuthenticationHelper.getCurrentUser(true);
		
		List<OrgInfoApply> applyOrgList = orgInfoApplyService.getApplyOrgList(user.getOrgInfo().getObjId(),applyType);
		OrgInfoApply applyOrg = applyOrgList.size()>0?applyOrgList.get(0):null;
		if("m".equals(applyType)) {
			if(applyOrg != null) {
				model.put("manufatorAuditStatus", applyOrg.getAuditStatus());
				model.put("manufatorObjId", applyOrg.getObjId());
			}
		}
		if("b".equals(applyType)) {
			if(applyOrg != null) {
				model.put("buyerAuditStatus", applyOrg.getAuditStatus());
				model.put("buyerObjId", applyOrg.getObjId());
			}
		}
		if("s".equals(applyType)) {
			if(applyOrg != null) {
				model.put("supplierAuditStatus", applyOrg.getAuditStatus());
				model.put("supplierObjId", applyOrg.getObjId());
			}
		}
		if("a".equals(applyType)) {
			if(applyOrg != null) {
				model.put("agencyAuditStatus", applyOrg.getAuditStatus());
				model.put("agencyObjId", applyOrg.getObjId());
			}
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  机构申请页面，获取机构信息
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toApplyOrgInfo")
    public ModelAndView toApplyOrgInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String modelView = "toApplyOrgInfoView";
		User user=AuthenticationHelper.getCurrentUser(true);
		
		String applyType = request.getParameter("applyType");
		
		//获取申请的机构角色信息 m 代表厂商类型
		List<OrgInfoApply> applyOrgList = orgInfoApplyService.getApplyOrgList(user.getOrgInfo().getObjId(),applyType);
		OrgInfoApply applyOrg = applyOrgList.size()>0?applyOrgList.get(0):null;
		if("r".equals(applyType)) {//服务商申请页面
			if(applyOrg != null) {
				model.put("serverAuditStatus", applyOrg.getAuditStatus());
				model.put("serverObjId", applyOrg.getObjId());
			}
			
		}else if("m".equals(applyType)) {//厂商申请页面
			if(applyOrg != null) {
				model.put("manufatorAuditStatus", applyOrg.getAuditStatus());
				model.put("manufatorObjId", applyOrg.getObjId());
			}
		}
		model.put("currentOrgId", user.getOrgInfo().getObjId());
		
		String viewName = request.getParameter("viewName");
		if(StringUtils.hasLength(viewName)) {
			modelView = viewName;
		}
		return new ModelAndView(modelView, model);
	}
	
	/** 
	 * Description :  跳转到审核申请的机构
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditApplyOrg")
    public ModelAndView toAuditApplyOrg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String orgId = request.getParameter("orgId");
		String applyType = request.getParameter("applyType");
		
		List<OrgInfoApply> applyOrgList = orgInfoApplyService.getApplyOrgList(orgId,applyType);
		OrgInfoApply applyOrg = applyOrgList.size()>0?applyOrgList.get(0):new OrgInfoApply();
		model.put("applyOrg", applyOrg);
		
		OrgInfo org = orgInfoServiceImpl.get(orgId);
		model.put("org", org);
		
		return new ModelAndView("toAuditApplyOrgView", model);
	}
	
	/** 
	 * Description :  审核申请的机构
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditApplyOrg")
    public ModelAndView auditApplyOrg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("objId", request.getParameter("objId"));
		
		params.put("orgId", request.getParameter("orgId"));
		params.put("status", request.getParameter("status"));
		params.put("applyType", request.getParameter("applyType"));
		
		//申请审核(赋予角色以及更新orgInfo的isManufaturer字段值)
		orgInfoApplyService.auditApplyOrg(params);
		
		OrgInfoApply applyOrg = orgInfoApplyService.get(request.getParameter("objId"));
		applyOrg.setAuditStatus(request.getParameter("status"));
		applyOrg.setVerifyTime(new Date());
		applyOrg.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
		applyOrg.setOpinion(request.getParameter("opinion"));
		orgInfoApplyService.save(applyOrg);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  申请成为厂商
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyManufactor")
	public ModelAndView saveApplyManufactor(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyOrgStr=request.getParameter("applyOrgStr");
		 
		 OrgInfoApply orgInfoApply = JsonUtils.json2Bean(applyOrgStr, OrgInfoApply.class);
		 if(null!=orgInfoApply.getObjId()&&"".equals(orgInfoApply.getObjId())) {
			 orgInfoApply.setObjId(null);
		 }
		 orgInfoApply.setApplyType("m");//厂商角色类型
		 orgInfoApply.setApplyTime(new Date());
		 orgInfoApply.setCreateTime(new Date());
		 orgInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 orgInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 orgInfoApplyService.save(orgInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  申请成为服务商
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyServer")
	public ModelAndView saveApplyServer(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyOrgStr=request.getParameter("applyOrgStr");
		 
		 OrgInfoApply orgInfoApply = JsonUtils.json2Bean(applyOrgStr, OrgInfoApply.class);
		 orgInfoApply.setApplyType("r");//服务商角色类型
		 orgInfoApply.setApplyTime(new Date());
		 orgInfoApply.setCreateTime(new Date());
		 orgInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 orgInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 orgInfoApplyService.save(orgInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  申请成为供应商
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplySupplier")
	public ModelAndView saveApplySupplier(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyOrgStr=request.getParameter("applyOrgStr");
		 
		 OrgInfoApply orgInfoApply = JsonUtils.json2Bean(applyOrgStr, OrgInfoApply.class);
		 if(null!=orgInfoApply.getObjId()&&"".equals(orgInfoApply.getObjId())) {
			 orgInfoApply.setObjId(null);
		 }
		 orgInfoApply.setApplyType("s");//供应商角色类型
		 orgInfoApply.setApplyTime(new Date());
		 orgInfoApply.setCreateTime(new Date());
		 orgInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 orgInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 orgInfoApplyService.save(orgInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  申请成为采购人
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyBuyer")
	public ModelAndView saveApplyBuyer(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyOrgStr=request.getParameter("applyOrgStr");
		 
		 OrgInfoApply orgInfoApply = JsonUtils.json2Bean(applyOrgStr, OrgInfoApply.class);
		 if(null!=orgInfoApply.getObjId()&&"".equals(orgInfoApply.getObjId())) {
			 orgInfoApply.setObjId(null);
		 }
		 orgInfoApply.setApplyType("b");//采购人角色类型
		 orgInfoApply.setApplyTime(new Date());
		 orgInfoApply.setCreateTime(new Date());
		 orgInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 orgInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 orgInfoApplyService.save(orgInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  申请成为代理机构
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyAgency")
	public ModelAndView saveApplyAgency(HttpServletRequest request) throws Exception{
		 Map<String, Object> model = new HashMap<String, Object>();
		 String applyOrgStr=request.getParameter("applyOrgStr");
		 
		 OrgInfoApply orgInfoApply = JsonUtils.json2Bean(applyOrgStr, OrgInfoApply.class);
		 if(null!=orgInfoApply.getObjId()&&"".equals(orgInfoApply.getObjId())) {
			 orgInfoApply.setObjId(null);
		 }
		 orgInfoApply.setApplyType("a");//代理机构角色类型
		 orgInfoApply.setApplyTime(new Date());
		 orgInfoApply.setCreateTime(new Date());
		 orgInfoApply.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		 orgInfoApply.setApplyUser(AuthenticationHelper.getCurrentUser(true));
		 
		 orgInfoApplyService.save(orgInfoApply);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
