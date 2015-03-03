package com.gpcsoft.bizplatform.organization.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoModifyService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.OrganizationManager;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.auth.service.EmployeeService;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.service.DistrictService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
*  @gpcsoft.view value="orgHistoryListView"
*  url="view/bizplatform/organization/manager/orginfo_history_list.jsp"
*  
*  @gpcsoft.view value="orgHistoryDetailView"
*  url="view/bizplatform/organization/manager/orginfo_history_detail.jsp"
*  
*  @gpcsoft.view value="qualityManageView"
*  url="view/bizplatform/organization/manager/org_quality_manage.jsp"
*  
*  @gpcsoft.view value="toModifyOrgInfoView"
*  url="view/bizplatform/organization/manager/organization_modify.jsp"
*  
*  @gpcsoft.view value="toApplyOrgInfoView"
*  url="view/bizplatform/organization/manager/organization_apply.jsp"
*  
*  @gpcsoft.view value="toApplyRole"
*  url="view/bizplatform/organization/manager/applyRole.jsp"
*  
*  @gpcsoft.view value="toAuditOrgInfoView"
*  url="view/bizplatform/organization/manager/orginfo_audit.jsp"
*  
*  @gpcsoft.view value="toAuditOrgInfo_XYGH_View"
*  url="view/bizplatform/organization/manager/orginfo_audit_xygh.jsp"
*  
*  @gpcsoft.view value="toContractorFormView"
*  url="view/bizplatform/organization/manager/contact_form.jsp"
*  
*  @gpcsoft.view value="toContractorDetailView"
*  url="view/bizplatform/organization/manager/contact_detail.jsp"
*  
*  @gpcsoft.view value="toContactListView"
*  url="view/bizplatform/organization/manager/contact_list.jsp"
*  
*  @gpcsoft.view value="toContactListSelfView"
*  url="view/bizplatform/organization/manager/contact_list_self.jsp"
*  
*  @gpcsoft.view value="orgInfoListView"
*  url="view/bizplatform/organization/manager/organization_manage_list.jsp"
*  
*  @gpcsoft.view value="toOrgInfoDetailView"
*  url="view/bizplatform/organization/manager/organization_detail.jsp"
*  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgInfo.class})
@RequestMapping("/OrgInfoController.do")//页面请求路径,可修改
public class OrgInfoController extends AnnotationMultiController<OrgInfo> {
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	@Autowired(required=true) @Qualifier("districtServiceImpl")
	private DistrictService districtService;
	@Autowired(required=true) @Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;
	@Autowired(required = true) @Qualifier("userServiceImpl")
	private UserService userService;
	@Autowired(required = true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierServiceImpl;
	@Autowired(required=true) @Qualifier("orgInfoModifyServiceImpl")
	private OrgInfoModifyService orgInfoModifyService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required=true) @Qualifier("organizationManagerImpl")
	private OrganizationManager organizationManager;
	
	
	/** 
	 * Description :  查询待审核的机构信息列表
	 * 				  查询条件为：auditStatus=01(待审核)
	 * 				  或者 supplierStatus=01 or buyersTatus=01 or agencyTatus=01)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listOrgInfoForAudit")
    public ModelAndView listOrgInfoForAudit(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<OrgInfo> page = prePage(request);//预分页,算出当前页和大小等		
		
		//参数封装
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgName", StringUtils.spaceToPercent(request.getParameter("orgName")));
		param.put("auditType", request.getParameter("auditType"));
		param.put("order", request.getParameter("order"));//排序列
		param.put("order_flag", request.getParameter("order_flag"));//排序方向
		param.put("type", request.getParameter("type"));
		
		Page pageData = (Page) orgInfoService.listOrgInfoForAudit(page,param);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
    
	/** 
	 * Description : 联系人信息列表
	 * 				 取出当前用户的employeeId共页面调用  
	 * Create Date: 2010-7-30上午10:45:50 by sunl  Modified Date: 2010-7-30上午10:45:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=listContact")
    public ModelAndView listContact(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentEmployeeId", AuthenticationHelper.getCurrentUser(true).getEmp().getObjId());
		model.put("currentCompanyId", AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		ModelAndView mv =null;
		//进入带机构树的联系人管理页面
		if(organizationManager.getSubOrgByParentId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId())>0){
			mv = new ModelAndView("toContactListView",model);
		}
		//进入只有本机构的联系人管理页面
		else{
			mv = new ModelAndView("toContactListSelfView",model);
		}
		return mv ;
	}
	
	/** 
	 * Description :  机构维护页面，获取机构信息
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifyOrgInfo")
    public ModelAndView toModifyOrgInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String orgId = request.getParameter("orgId");
		OrgInfo orgInfo = null;
		
		if(StringUtils.hasLength(orgId)) {
			orgInfo = orgInfoService.get(orgId);
		} else {
			orgInfo = orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(),true);
		}
		
		//取所有province数据,根据provinceid取city数据,根据cityid取town数据
		List<District> province = districtService.findTopDistricts();
		List<District> city = new ArrayList<District>();
		List<District> town = new ArrayList<District>();
		
		if(orgInfo.getCompany().getTown() != null) {
			city = districtService.getSubDistricts(orgInfo.getCompany().getTown().getParent().getParent().getObjId());
			town = districtService.getSubDistricts(orgInfo.getCompany().getTown().getParent().getObjId());
		}
		
		model.put("orgInfo", orgInfo);
		model.put("province", province);
		model.put("city", city);
		model.put("town", town);
		
		//判断当前用户角色
		User user = AuthenticationHelper.getCurrentUser(true);
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER)){
			model.put("role_type", "3");
		}else{
			model.put("role_type", "4");
		}
		
		return new ModelAndView("toModifyOrgInfoView", model);
    }
	
	/** 
	 * Description :  机构审核页面，获取机构信息
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditOrgInfo")
	public ModelAndView toAuditOrgInfo(String orgInfoId,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		OrgInfo orgInfo = orgInfoService.get(orgInfoId);
		model.put("orgInfo", orgInfo);
		
		if(StringUtils.hasLength(orgInfo.getCurrentId())) {
			model.put("orgInfoWithEx", orgInfoService.get(orgInfo.getCurrentId()));
		}else {
			model.put("orgInfoWithEx", orgInfo);
		}
		
		//获得用户角色
		List<Role> roles = userService.getSelfRoleByUserId(orgInfo.getUser().getObjId());
		model.put("roles",roles);
		
		//所审核的角色(供应商审核采购人审核代理机构审核)
		model.put("auditRole", request.getParameter("auditRole"));
		
		return new ModelAndView("toAuditOrgInfoView", model);
	}
	
	/** 
	 * Description :  协议供货采购人机构审核页面
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditOrgInfo_XYGH")
	public ModelAndView toAuditOrgInfo_XYGH(String orgInfoId,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		OrgInfo orgInfo = orgInfoService.get(orgInfoId);
		model.put("orgInfo", orgInfo);
		
		if(StringUtils.hasLength(orgInfo.getCurrentId())) {
			model.put("orgInfoWithEx", orgInfoService.get(orgInfo.getCurrentId()));
		}else {
			model.put("orgInfoWithEx", orgInfo);
		}
		
		//获得用户角色
		List<Role> roles = userService.getSelfRoleByUserId(orgInfo.getUser().getObjId());
		model.put("roles",roles);
		
		//所审核的角色(供应商审核采购人审核代理机构审核)
		model.put("auditRole", request.getParameter("auditRole"));
		
		return new ModelAndView("toAuditOrgInfo_XYGH_View", model);
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
		
		//根据当前用户的companyId，获得最新的orgInfo
		OrgInfo orgInfo = orgInfoService.getLastedOrgInfo(user.getEmp().getCompany().getObjId(),false);
		model.put("orgInfo", orgInfo);
		
		//获得用户角色
		List<Role> roles = userService.getSelfRoleByUserId(user.getObjId());
		model.put("roles",roles);
		
		model.put("currentOrgId", user.getOrgInfo().getObjId());
		
		String viewName = request.getParameter("viewName");
		if(StringUtils.hasLength(viewName)) {
			modelView = viewName;
		}
		return new ModelAndView(modelView, model);
    }
	
	/** 
	 * Description :  联系人管理页面，获取employee信息
	 * Create Date: 2010-7-29下午05:25:01 by sunl  Modified Date: 2010-7-29下午05:25:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toContractorForm")
    public ModelAndView toContractorForm(HttpServletRequest request,String employeeId) throws Exception {
		String viewName = "toContractorFormView";
		Map<String, Object> model = new HashMap<String, Object>();
		Employee employee = null;
		if(StringUtils.hasLength(employeeId)){
			employee = employeeService.get(employeeId);
		}
		if(StringUtils.hasLength(request.getParameter("type")) && "view".equals(request.getParameter("type"))){
			viewName = "toContractorDetailView";
		}
		model.put("employee", employee);
		return new ModelAndView(viewName, model);
    }
	
	/** 
	 * Description :  保存或更新联系人信息
	 * Create Date: 2010-7-29下午05:25:01 by sunl  Modified Date: 2010-7-29下午05:25:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveContact")
    public ModelAndView saveContact(Employee employee,HttpServletRequest request,SessionStatus status) throws Exception {
		if(StringUtils.hasLength(employee.getObjId())){
			Employee oldEmployee = employeeService.get(employee.getObjId());
			
			//BeanUtils.copyPropertiesFilterEmpty(oldEmployee, employee);
			//上面的方法在删除以前存在的信息时存在问题（不能删除，只能增加和修改），暂时使用下面的方法。
			oldEmployee.setName(employee.getName());
			oldEmployee.setSex(employee.getSex());
			oldEmployee.setIdCard(employee.getIdCard());
			oldEmployee.setMobile(employee.getMobile());
			oldEmployee.setTelOffice(employee.getTelOffice());
			oldEmployee.setTelHome(employee.getTelHome());
			oldEmployee.setFax(employee.getFax());
			oldEmployee.setAddress(employee.getAddress());
			oldEmployee.setZipCode(employee.getZipCode());
			oldEmployee.setMsn(employee.getMsn());
			oldEmployee.setQq(employee.getQq());
			oldEmployee.setEmail(employee.getEmail());
			
			employeeService.save(oldEmployee);
		}else{
			//employee.setCompany(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany());
			employeeService.save(employee);
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
    }

	/** 
	 * Description :   保存机构的修改信息
	 * Create Date: 2010-7-20下午02:23:02 by sunl  Modified Date: 2010-7-20下午02:23:02 by sunl
	 * @param   supplierStr 供应商
	 * @param   buyerStr 采购人
	 * @param   agencyStr 代理机构
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveModifyOrgInfo")
	public ModelAndView saveModifyOrgInfo(OrgInfo orgInfo,String supplierStr,String buyerStr,String agencyStr,SessionStatus status,HttpServletRequest request) throws Exception {
		 Map<String, Object> model = new HashMap<String, Object>(); 
		 String returnMessage = "success";
		 Supplier supplier = null;
		 Buyer buyer = null;
		 Agency agency = null;
		 
		 List<GpcBaseObject> list = new ArrayList<GpcBaseObject>();
		 
		 //根据前台绑定的结果，获取扩展对象
		 if(supplierStr != null){
			 supplier=JsonUtils.json2Bean(supplierStr ,Supplier.class);
			 if(supplier.getRegCapital() == null) {
				 supplier.setRegCapital(0.0);
			 }if(supplier.getPaidUpCapital() == null) {
				 supplier.setPaidUpCapital(0.0);
			 }
			 list.add(supplier);
		 }
		 if(buyerStr != null){
			 buyer=JsonUtils.json2Bean(buyerStr ,Buyer.class);
			 list.add(buyer);
		 }
		 if(agencyStr != null){
			 agency=JsonUtils.json2Bean(agencyStr ,Agency.class);
			 list.add(agency);
		 }
		 
		 //处理机构图片
		 CommonsMultipartFile file = null;
		 
		 try{
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		     file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			 if(file != null && file.getSize() != 0){
				 Object o=AttachmentUtil.uploadFile(request,"pictureFile");
				 if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentServiceImpl.saveAttachment((Attachment)o);
						orgInfo.setLogo(attachment.getObjId());
				 }
			 }
		 }catch(Exception de) {
			returnMessage = de.getMessage();
		 }
		 String saveStatus = request.getParameter("saveStatus");
		 //新objId
		 String newOrgInfoId = "";
		 
		 if(returnMessage.equals("success")) {
			 //根据参数个数传递可变参数值
			 int paramSize = list.size();
			 if(paramSize ==  1){
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus,list.get(0));
			 }else if(paramSize == 2){
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus,list.get(0),list.get(1));
			 }else if(paramSize ==3){
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus,list.get(0),list.get(1),list.get(2));
			 }else if(paramSize ==4){
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus,list.get(0),list.get(1),list.get(2),list.get(3));
			 }else if(paramSize ==5){
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4));
			 }else{
				 newOrgInfoId = orgInfoService.saveModifyOrgInfo(orgInfo,saveStatus);
			 }
			 status.setComplete();
			 model.put("newOrgInfoId", newOrgInfoId);
			 model.put(Constants.JSON_RESULT,returnMessage);
		 }else {
			 model.put(Constants.JSON_RESULT,StringUtils.string2ASCII(returnMessage));
		 }
		
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
		 String orgInfoId=request.getParameter("orgInfoId");
		 
		 OrgInfo orgInfo = orgInfoService.get(orgInfoId);
		 orgInfo.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		 orgInfo.setAuditType(OrganizationEnum.APPLY_AUDIT);//申请审核
		 orgInfo.setIsManufacturer("1");//是厂家
		 
		 Supplier supplier = supplierServiceImpl.get(orgInfo.getSupplierId());
		 supplier.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		 
		 supplierServiceImpl.save(supplier);
		 orgInfoService.save(orgInfo);
		 
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
		 String orgInfoId=request.getParameter("orgInfoId");
		 
		 OrgInfo orgInfo = orgInfoService.get(orgInfoId);
		 orgInfo.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		 orgInfo.setAuditType(OrganizationEnum.APPLY_AUDIT);//申请审核
		 orgInfo.setIsManufacturer("2");//用2记录申请的是服务商
		 
		 orgInfoService.save(orgInfo);
		 
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  开启或禁用机构账号
	 * 				  同步机构下用户状态
	 * Create Date: 2010-8-2上午11:41:27 by sunl  Modified Date: 2010-8-2上午11:41:27 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateDisableOrEnable")  
	public ModelAndView updateDisableOrEnable(String orgInfoId,String isOff,HttpServletRequest request,SessionStatus status) throws Exception {
	    orgInfoService.updateDisableOrEnable(orgInfoId,isOff);
	    status.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	/** 
	 * Description :  审核机构信息
	 * 				  根据不同的审核类型，调用不同的审核方法
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditOrgInfo")
	public ModelAndView auditOrgInfo(OrgInfo orgInfo, HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		//该参数区分将角色授予机构管理员还是授予给机构
		String msg = "";
		//得到旧的orgInfo信息
		OrgInfo oldOrgInfo = orgInfoService.get(orgInfo.getObjId());
		//注册审核
		if(StringUtils.hasLength(orgInfo.getAuditType()) && OrganizationEnum.REG_AUDIT.equals(orgInfo.getAuditType())){
			msg = CustomerMessage.ORGINFO_AUDIT_COMPLETE;
			orgInfoService.auditOrgBaseInfoForReg(orgInfo,params);
		}
		//变更审核
		else if(StringUtils.hasLength(orgInfo.getAuditType()) && OrganizationEnum.MODIFY_AUDIT.equals(orgInfo.getAuditType())){
			orgInfoService.auditOrgBaseInfoForMod(orgInfo);
			msg = CustomerMessage.ORGINFO_MODIFY_AUDIT_COMPLETE;
		}
		//申请审核(如果是注册完就申请或审核未通过的,调用注册审核方法,如果是审核通过再申请的,调用申请审核方法)
		else if(StringUtils.hasLength(orgInfo.getAuditType()) && OrganizationEnum.APPLY_AUDIT.equals(orgInfo.getAuditType())){
			if(StringUtils.hasLength(orgInfo.getAuditStatus())){
				if(oldOrgInfo.getAuditStatus().equals(OrganizationEnum.PASS_EXAM)
						|| (StringUtils.hasLength(oldOrgInfo.getIsManufacturer()) && !"0".equals(oldOrgInfo.getIsManufacturer()))){
					orgInfoService.auditOrgBaseInfoForApply(orgInfo);
					msg = CustomerMessage.ORGINFO_APPLY_AUDIT_COMPLETE;
				}else{
					orgInfoService.auditOrgBaseInfoForReg(orgInfo,params);
					msg = CustomerMessage.ORGINFO_AUDIT_COMPLETE;
				}
			}
		}else {//只审核
			orgInfoService.auditOrgInfo(orgInfo);
		}
		status.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, messageSource.getMessage(msg));
        return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * 重写onfind方法，在机构管理查询时，默认查询有效的记录
	 * param：type,区分所查询的列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		String orgType = request.getParameter("orgType");//机构类别
		if(null != type && type.equals("manage") && orgType != null){//机构管理查询列表
			query.getQueryParam().removeAndParams(new QueryParam("orgType",QueryParam.OPERATOR_EQ ,request.getParameter("orgType")));
			if(orgType.equals("SUPPLIER")){
				query.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_NIS ,null));
			}
			if(orgType.equals("BUYER")){
				query.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_NIS ,null));
			}
			if(orgType.equals("AGENT")){
				query.getQueryParam().and(new QueryParam("agencyId",QueryParam.OPERATOR_NIS ,null));
			}
			query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_NE ,OrganizationEnum.USE_INVALID));
			
			if(StringUtils.hasLength(request.getParameter("orgName"))){
				query.getQueryParam().removeAndParams(new QueryParam("orgName",QueryParam.OPERATOR_EQ ,request.getParameter("orgName")));
				if(request.getParameter("orgName").replaceAll(" ", "").equals("_")) {
					query.getQueryParam().and(new QueryParam("orgName",QueryParam.OPERATOR_LIKE,"\\_"));
				} else {
					query.getQueryParam().and(new QueryParam("orgName",QueryParam.OPERATOR_LIKE,StringUtils.spaceToPercent(request.getParameter("orgName"))));
				}
			}
			if(StringUtils.hasLength(request.getParameter("createUser.usName"))){
				query.getQueryParam().removeAndParams(new QueryParam("createUser.usName",QueryParam.OPERATOR_EQ ,request.getParameter("createUser.usName")));
				if(request.getParameter("createUser.usName").replaceAll(" ", "").equals("_")) {
					query.getQueryParam().and(new QueryParam("createUser.usName",QueryParam.OPERATOR_LIKE,"\\_"));
				} else {
					query.getQueryParam().and(new QueryParam("createUser.usName",QueryParam.OPERATOR_LIKE,StringUtils.spaceToPercent(request.getParameter("createUser.usName"))));
				}
			}
		}
		return query;
	}
	
	/** 
	 * Description :  获取机构历史信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return	
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrgHistory")
	public ModelAndView getOrgHistory(HttpServletRequest request) throws Exception {
		String viewName = "orgHistoryListView";
		Map<String, Object> model = new HashMap<String, Object>();
		//如果是查询列表
		if(null == request.getParameter("type")){
			//参数封装
			Map<String, Object> param = new HashMap<String, Object>();  
			param.put("id", request.getParameter("id"));
			List<OrgInfoModify> orgModifyList = orgInfoModifyService.getOrgInfoModifyList(request.getParameter("id"));
			model.put("orgHistoryList", orgModifyList);
		}else if(null != request.getParameter("type") && "view".equals(request.getParameter("type"))){
			OrgInfo orgHistory = orgInfoService.get(request.getParameter("id"));
			model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"company"});
			model.put("orgHistory", orgHistory);
			viewName = "orgHistoryDetailView";
		}
        return new ModelAndView(viewName,model);
	}
	
	/**
	 * 
	 * Description :  验证机构代码和机构名称的唯一
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=checkOrgUnique")
	public ModelAndView checkOrgUnique(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String property = request.getParameter("property");
		String type = request.getParameter("type");
		String propertyValue = StringUtils.ascii2Native(request.getParameter(property));
		param.put(property, propertyValue);
		param.put("id", request.getParameter("id"));
		if(type == null) {
			OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
			if(StringUtils.hasLength(orgInfo.getSupplierId())) {
				param.put("type", "supplier");//基本信息维护时
			}else if(StringUtils.hasLength(orgInfo.getBuyerId())) {
				param.put("type", "buyer");//基本信息维护时
			}
		}else{
			param.put("type", type);//注册时
		}
		Integer result = orgInfoService.checkOrgUnique(param);
		
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put(Constants.STRING_RESULT,String.valueOf(result>0?false:true));
    	return new ModelAndView(Constants.STRING_VIEW, model);
	}
	
	/**
	 * 
	 * Description :  模糊检验机构名称
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=checkOrgName")
	public ModelAndView checkOrgName(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String orgName = request.getParameter("orgName");
		param.put("orgName", orgName);
		Map<String,Object> result = orgInfoService.checkOrgName(param);
		return new ModelAndView(Constants.JSON_VIEW, result);	
	}
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，包括供应商信息，采购人信息，代理机构信息
	 * 				  机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:50:25 by sunl  Modified Date: 2010-8-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrgAllInfo")
	public ModelAndView getOrgAllInfo(HttpServletRequest request,String orgInfoId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		OrgInfo orgInfo = orgInfoService.getOrgAllInfo(orgInfoId);
		model.put("orgInfo", orgInfo);
		return new ModelAndView("toOrgInfoDetailView", model);
	}
		
	/** 
	 * Description :  获得代理机构下拉列表
	 * Create Date: 2010-9-26下午06:50:25 by sunl  Modified Date: 2010-9-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAgencies")
	public ModelAndView getAgencies() throws Exception {
		List<OrgInfo> agencyList = orgInfoService.getAgencies();
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("result", agencyList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description : 取得当前org 或者传入org是否审核
	 * Create Date: 2010-11-10下午06:34:57 by yucy  Modified Date: 2010-11-10下午06:34:57 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getIsAuditPass")
	public ModelAndView getIsAuditPass(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> param = new HashMap<String, Object>();
    	//设置参数
    	if(StringUtils.hasLength(request.getParameter("orgId"))){
    		param.put("orgId", request.getParameter("orgId"));
    	}else{
    		param.put("orgId",orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
    	}
    	Boolean isAudit = orgInfoService.getIsAuditPass(param);
    	model.put("isAudit", isAudit);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据供应商名称查询供应商列表
	 * Create Date: 2010-11-22下午03:40:51 by guoyr  Modified Date: 2010-11-22下午03:40:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAllSupplierByOrgName")
	public ModelAndView  getAllSupplierByOrgName(HttpServletRequest request,String orgName){
		List<OrgInfo> orgInfoList=new ArrayList<OrgInfo>();
		orgName = StringUtils.ascii2Native(orgName);
		orgInfoList = orgInfoService.getAllOrgInfoByOrgName(orgName);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", orgInfoList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  定位到指定id的数据的那一页的分页方式
	 * Create Date: 2011-2-15下午06:57:41 by yucy  Modified Date: 2011-2-15下午06:57:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getSpecifiesIdPage")
	public ModelAndView getSpecifiesIdPage(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
		String specifiesId = request.getParameter("specifiesId");
		String queryColumns = request.getParameter("queryColumns");
		int pageNum = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("rp"));
        Object specifiesObj  = null;
        QueryObject query = QueryWebUtils.getQuery(request, getPersistClass());
        initQueryColums(request, query);
        QueryParam specifiesObjParam =  new QueryParam("objId",specifiesId);
		query.getQueryParam().and(specifiesObjParam);//加上特定id的参数
        List<OrgInfo> specifiesObjList = orgInfoService.findByQuery(query);
        query.getQueryParam().removeAndParams(specifiesObjParam);//去除特定id的参数
        if(specifiesObjList!=null&&specifiesObjList.size()>0){
        	specifiesObj = specifiesObjList.get(0);
        }
        
        //供应商ID非空,机构审核通过
        query.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_NIS ,null));
        query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ ,OrganizationEnum.USE_VALID));
        query.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ ,OrganizationEnum.PASS_EXAM));
		List<OrgInfo> specifiesIdOrgInfo = orgInfoService.findByQuery(query);//查询整个集合
		int index =  -1;//返回这条数据在这个集合里的位置
		for(int i = 0;i<specifiesIdOrgInfo.size()&&specifiesObj!=null;i++){
			Object obj = specifiesIdOrgInfo.get(i);
			if(((Object[])specifiesObj)[0].equals(((Object[])obj)[0]) ){
				index = i;break;
			}
		}
		int curpage = index/limit+1;//当前页
		
		if(pageNum!=curpage&&"notFirst".equals(request.getParameter("loadTime"))){//加这个参数表示非首次加载
			curpage = pageNum;
		}
		Page page = prePage(request);
        initQueryColums(request, query);//前面查过一次query已被毁掉所以再初始化一次
        int start = curpage != 1 ? Page.getStartOfPage(curpage, limit) : 0;
        page.setStart(start);
        Page pageData = orgInfoService.findByQuery(query, true, page.getStart(), page.getPageSize());
        pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), null/*不用alias*/, getPersistClass(), new Map[] {
            getEnumColumns()
        }));
        endPage(model, pageData, request);
        return  new ModelAndView("jsonView", model);
	}
}
