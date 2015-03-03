package com.gpcsoft.bizplatform.organization.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationParamService;
import com.gpcsoft.bizplatform.base.qualitymanagement.service.QualificationService;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.organization.service.OrgQualityService;
import com.gpcsoft.bizplatform.organization.service.QualificationDetailService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.service.DistrictService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
/**
*  @gpcsoft.view value="toOrgLegalInfoView"
*  url="view/bizplatform/organization/exinfo/org_legal_info_form.jsp"
*  @gpcsoft.view value="orgFinanceInfoFormView"
*  url="view/bizplatform/organization/exinfo/org_finance_info_form.jsp"
*  @gpcsoft.view value="toEntBaseInfoView"
*  url="view/bizplatform/organization/exinfo/exBaseInfo_modify.jsp"
*  @gpcsoft.view value="toExAllBaseInfoView"
*  url="view/bizplatform/organization/exinfo/exBaseInfo_detail.jsp"
*  @gpcsoft.view value="createOrgInfoBuyerView"
*  url="view/bizplatform/organization/manager/create_orginfo_buyer.jsp"
*  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgInfo.class})
@RequestMapping("/ExOrgInfoController.do")//页面请求路径,可修改
public class ExOrgInfoController extends AnnotationMultiController<OrgInfo> {
	
	@Autowired(required=true) @Qualifier("qualificationServiceImpl")
	private QualificationService qualificationService;
	@Autowired(required=true) @Qualifier("qualificationDetailServiceImpl")
	private QualificationDetailService qualificationDetailService;
	@Autowired(required=true) @Qualifier("districtServiceImpl")
	private DistrictService districtService;
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	@Autowired(required=true) @Qualifier("qualificationParamServiceImpl")
	private QualificationParamService qualificationParamService;
	@Autowired(required=true) @Qualifier("orgQualityServiceImpl")
	private OrgQualityService orgQualityService;
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required = true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description : 跳转到公司法务信息页面  (可传递一个qualityClassId 也可以传递一个 qualityClassCode  也可以都传 )
	 * Create Date: 2010-11-15上午10:27:25 by yucy  Modified Date: 2010-11-15上午10:27:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toOrgLegalInfo")
	public ModelAndView toOrgLegalInfo(HttpServletRequest request ,String qualityClassId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		
		String qualityClassCode = request.getParameter("qualityClassCode");
		param.put("qualityClassCode", qualityClassCode);
		// 如果是通过code获取资质
		if(!StringUtils.hasLength(qualityClassId) && StringUtils.hasLength(qualityClassCode)){
			Qualification qualification = qualificationService.getQualificationByCode(qualityClassCode);
			qualityClassId = qualification.getObjId();
		}

		param.put("qualityClassId", qualityClassId);
		List<QualificationParam> qualificationParamList = qualificationParamService.getParamByClass(param);
		
		List<OrgQuality>  orgQualityList = null;
		if(StringUtils.hasLength(request.getParameter("orgId"))){
			 orgQualityList= orgQualityService.getOrgQuality(request.getParameter("orgId"));
			 model.put("orgId" ,request.getParameter("orgId"));
		}else{
			 orgQualityList= orgQualityService.getOrgQuality(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			 model.put("orgId" ,AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		}
		
		List<QualificationDetail>  qualificationDetailList = new ArrayList<QualificationDetail>();
		for(OrgQuality orgQuality: orgQualityList){
			if(!orgQuality.getQualificationClass().getIsDisplay()){
				qualificationDetailList.addAll(orgQuality.getQualificationDetailSet());
			}
		}
		
		model.put("qualificationParamList",qualificationParamList);
		
		model.put("qualificationDetailList",qualificationDetailList);

		return  new ModelAndView("toOrgLegalInfoView",model);
	}
	
	/** 
	 * Description :  保存机构财务信息(资质)
	 * Create Date: 2010-11-15下午02:52:45 by yucy  Modified Date: 2010-11-15下午02:52:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	
	@RequestMapping(params="method=saveExOrgQuality")
	public ModelAndView saveExOrgQuality(HttpServletRequest request) throws Exception {
		String returnMessage = "success";
		Map<String, Object> model = new HashMap<String, Object>();
		String orgQualitysJson = StringUtils.ascii2Native(request.getParameter("orgQualitysJson"));
		List<OrgQuality> orgQualityList= new ArrayList<OrgQuality>();
		List<Attachment> attachmentList= new ArrayList<Attachment>();
		
		if(StringUtils.hasLength(orgQualitysJson)){
			String[] orgQualityArray = orgQualitysJson.split(",");
			for(String orgQualityStr: orgQualityArray){
				String [] propertiesArray  =  orgQualityStr.split(":");
		        
		        OrgQuality orgQuality = new OrgQuality();
		        
		        Set<QualificationDetail> qualificationDetailSet = null;
		        if(!"-1".equals(propertiesArray[4])){
		        	orgQuality = orgQualityService.get(propertiesArray[4]);
		        	qualificationDetailSet = orgQuality.getQualificationDetailSet();
		        	for(QualificationDetail qualificationDetail :qualificationDetailSet){
		        		qualificationDetail.setParamValue(propertiesArray[0]);
		        	}
		        }else {
		        	//分类
		        	QualificationClass qualificationClass=  new QualificationClass();
		        	qualificationClass.setObjId(propertiesArray[1]);
		        	orgQuality.setQualificationClass(qualificationClass);
		        	
		        	//定义
		        	QualificationDefine qualificationDefine=  new QualificationDefine();
		        	qualificationDefine.setObjId(propertiesArray[2]);
		        	orgQuality.setQualificationDefine(qualificationDefine);
		        	
		        	//值
		        	qualificationDetailSet = new HashSet<QualificationDetail>();
		        	QualificationDetail qualificationDetail = new QualificationDetail();
		        	qualificationDetail.setParamValue(propertiesArray[0]);
		        	qualificationDetail.setOrgQuality(orgQuality);
		        	//参数
		        	QualificationParam qualificationParam=  new QualificationParam();
		        	qualificationParam.setObjId(propertiesArray[5]);
		        	qualificationDetail.setQualityParam(qualificationParam);
		        	qualificationDetailSet.add(qualificationDetail);
		        	orgQuality.setQualificationDetailSet(qualificationDetailSet);
		        	
		        	//临时
		        	orgQuality.setAuditStatus(OrganizationEnum.PASS_EXAM);
		        	orgQuality.setUseStatus(OrganizationEnum.USE_VALID);
		        	
		        	//机构
		        	OrgInfo orgInfo = new OrgInfo();
		        	orgInfo.setObjId(request.getParameter("orgId"));
		        	orgQuality.setOrg(orgInfo);
				}
		        
				//附件处理
		        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		        CommonsMultipartFile file = null;
		        try {
			        file = (CommonsMultipartFile)multipartRequest.getFile(propertiesArray[3]);
			        if(file.getSize()!=0){
			        	Object o = AttachmentUtil.uploadFile(request,propertiesArray[3]);
			        	if(o instanceof GpcBaseObject){
			        		Attachment attachment = (Attachment)o;
			        		//已有附件
			        		if(orgQuality.getQualificationFile()!=null){
			        			attachment.setRelationId(orgQuality.getQualificationFile());
			        		}
			        		attachmentList.add(attachment);
			        		orgQuality.setQualificationFile(attachment.getRelationId());
			        	}
			        }
		        }catch(Exception de) {
					returnMessage = de.getMessage();
				}
		        orgQualityList.add(orgQuality);
			}
		}
		
		//保存
        if(returnMessage.equals("success")) {
        	attachmentService.save(attachmentList);
        	orgQualityService.save(orgQualityList);
        	model.put(Constants.JSON_RESULT,returnMessage);
		}else {
			model.put(Constants.JSON_RESULT,StringUtils.string2ASCII(returnMessage));
		}		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 跳转到企业财务信息页面
	 * Create Date: 2010-11-15上午10:33:51 by guoyr  Modified Date: 2010-11-15上午10:33:51 by guoyr
	 * @param  objId企业的资质分类ID 
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgFinanceInfoView")
    public ModelAndView toOrgFinanceInfoView(HttpServletRequest request,String qualificationClassId, String qualificationCode) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		// 如果是通过code获取资质
		if(!StringUtils.hasLength(qualificationClassId) && StringUtils.hasLength(qualificationCode)){
			Qualification qualification = qualificationService.getQualificationByCode(qualificationCode);
			qualificationClassId = qualification.getObjId();
		}
		
		// 取该分下的参数定义列表
		List<Qualification> qualificationList = qualificationService.getSubQualificationList(qualificationClassId);
		
		// 当前机构ID
		String orgId = request.getParameter("orgId");
		if(null == orgId || "".equals(orgId)){
			User curUser = AuthenticationHelper.getCurrentUser(true);
			orgId = curUser.getOrgInfo().getObjId();
		}
		model.put("orgId", orgId);
		model.put("qualificationClassId", qualificationClassId);
		model.put("qualificationCode", qualificationCode);
		model.put("qualificationList", qualificationList);
		return new ModelAndView("orgFinanceInfoFormView",model);
	}
	
	/** 
	 * Description : 保存企业财务信息
	 * Create Date: 2010-11-15上午10:33:51 by guoyr  Modified Date: 2010-11-15上午10:33:51 by guoyr
	 * @param  objId企业的资质分类ID 
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveOrgFinanceInfo")
	public ModelAndView saveOrgFinanceInfo(HttpServletRequest request,String financeInfoJSONString,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			qualificationDetailService.saveOrUpdateQualificationDetail(financeInfoJSONString);
			model.put("result", "保存成功");
		} catch (Exception e) {
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转到企业基本信息页面
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toEntBaseInfo")
    public ModelAndView toEntBaseInfo(HttpServletRequest request, String orgId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = "toEntBaseInfoView";
		//系统类型参数,用于判断系统来源转向不同的页面
		String systemType = request.getParameter("systemType");
		OrgInfo orgInfo = null;
		
		if(StringUtils.hasLength(orgId)){
			orgInfo = orgInfoService.get(orgId);
		}else{
			orgInfo = orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(),true);
		}
		
		if(StringUtils.hasLength(orgInfo.getSupplierId())) {
			Supplier supply = supplierService.get(orgInfo.getSupplierId());
			model.put("openAccount", supply.getOpenAccount());
			model.put("openBank", supply.getOpenBank());
			model.put("openAccountNmbr", supply.getOpenAccountNmbr());
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
		
		//获得用户角色
		List<Role> roles = userService.getSelfRoleByUserId(user.getObjId());
		model.put("roles",roles);
		
		//协议供货-采购人的基本信息跳转
		if(StringUtils.hasLength(systemType) && "a".equals(systemType) && StringUtils.hasLength(orgInfo.getBuyerId())) {
			viewName = "toModifyOrgInfoView";
		}
		
		//是否有机构域名服务
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgInfoId", orgInfo.getObjId());
		params.put("serviceId", ServiceEnum.SERVICE_C);
		model.put("showSiteName", serviceSubscribeService.isOrgInfoHasService(params));
		
		return new ModelAndView(viewName, model);
    }
	
	/** 
	 * Description :  跳转到企业基本信息页面
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateBuerOrgInfo")
	public ModelAndView toCreateBuerOrgInfo(HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = "createOrgInfoBuyerView";
		
		//系统类型
		model.put("appType", request.getParameter("appType"));
		
		status.setComplete();
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :   保存企业基本信息
	 * Create Date: 2010-7-20下午02:23:02 by sunl  Modified Date: 2010-7-20下午02:23:02 by sunl
	 * @param   supplierStr 供应商
	 * @param   buyerStr 采购人
	 * @param   agencyStr 代理机构
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveEntBaseInfo")
	public ModelAndView saveEntBaseInfo(OrgInfo orgInfo,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		orgInfo.getCompany().setOrgSite(request.getParameter("orgSite"));//机构子域名处理 by yucy
		String saveType = request.getParameter("saveType");//保存或提交
		
		String newOrgInfoId = orgInfoService.saveEntBaseInfo(orgInfo, saveType); //新objId
		model.put("newOrgInfoId", newOrgInfoId);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  保存机构信息（采购人/供应商）
	 * Create Date: 2010-11-12下午07:13:08 by likg  Modified Date: 2010-11-12下午07:13:08 by likg
	 * @param createType 机构类型(采购人/供应商)
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveOrgInfo")
	public ModelAndView saveOrgInfo(String createType,String buyerStr,OrgInfo orgInfoG,SessionStatus status,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//设置机构的区域信息
		String townObjId = request.getParameter("town.objId");
		District town = new District();
		town.setObjId(townObjId);
		orgInfoG.getCompany().setTown(town);
		orgInfoG.getCompany().setOrgSite(request.getParameter("orgSite"));
		
		if(StringUtils.hasLength( request.getParameter("company.parent.objId") )){
			Company parent = new Company();
			parent.setObjId(  request.getParameter("company.parent.objId") );
			orgInfoG.getCompany().setParent(parent);
		}
		
		if(createType!=null && createType.indexOf("buyer")!=-1) {
			Buyer buyer = new Buyer();
			if(buyerStr != null){
				 buyer=JsonUtils.json2Bean(buyerStr ,Buyer.class);
				 if(null!=buyer.getObjId()&&"".equals(buyer.getObjId())) {
					buyer.setObjId(null); 
				 }
			}
			buyerService.saveSimpleBuyer(buyer, orgInfoG);
		}
		if(createType!=null && createType.indexOf("supplier")!=-1) {
			Supplier supplier = new Supplier();
			supplierService.saveSimpleSupplier(supplier, orgInfoG);
		}
		
		status.setComplete();
	    
        return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，如机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:50:25 by sunl  Modified Date: 2010-8-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExAllBaseInfo")
	public ModelAndView getExAllBaseInfo(HttpServletRequest request,String orgId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		OrgInfo orgInfo = orgInfoService.getOrgAllInfo(orgId);
		model.put("orgInfo", orgInfo);
		
		if(StringUtils.hasLength(orgInfo.getCurrentId())) {
			model.put("orgInfoWithEx", orgInfoService.get(orgInfo.getCurrentId()));
		}else {
			model.put("orgInfoWithEx", orgInfo);
		}
		
		//获得用户角色
		if(orgInfo.getUser() != null) {
			List<Role> roles = userService.getSelfRoleByUserId(orgInfo.getUser().getObjId());
			model.put("roles",roles);
		}
		
		return new ModelAndView("toExAllBaseInfoView", model);
	}
}
