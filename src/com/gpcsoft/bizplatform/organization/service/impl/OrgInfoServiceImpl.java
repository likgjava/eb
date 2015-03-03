package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.manager.AgencyManager;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.manager.BuyerManager;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.manager.MemberManager;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.pubservice.utils.MobileMessageUtil;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.OrganizationManager;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.auth.service.OrganizationService;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class OrgInfoServiceImpl extends BaseGenericServiceImpl<OrgInfo> implements OrgInfoService {
	
	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	@Autowired(required=true) @Qualifier("buyerManagerImpl")
	private BuyerManager buyerManager;
	@Autowired(required=true) @Qualifier("agencyManagerImpl")
	private AgencyManager agencyManager;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	@Autowired(required=true) @Qualifier("organizationServiceImpl")
	private OrganizationService organizationServiceImpl;
	@Autowired(required=true) @Qualifier("organizationManagerImpl")
	private OrganizationManager organizationManagerImpl;
	@Autowired(required=true)  @Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	/** 
	 * Description :  获得代理机构下拉列表
	 * Create Date: 2010-9-26下午10:11:49 by sunl  Modified Date: 2010-9-26下午10:11:49 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfo> getAgencies() throws Exception {
		List<OrgInfo> agenciesList = new ArrayList<OrgInfo>();
		List<String[]> list = new ArrayList<String[]>();
		list = orgInfoDaoHibernate.getAgencies();
		for(Object[] obj : list){
			OrgInfo orgInfo = new OrgInfo();
			orgInfo.setObjId((String)obj[0]);
			orgInfo.setOrgName((String)obj[1]);
			agenciesList.add(orgInfo);
		}
		
		return agenciesList;
	}
	
	/** 
	 * Description :  查询待审核的机构信息列表
	 * 				  查询条件为：auditStatus=01(待审核)
	 * 				  或者 supplierStatus=01 or buyersTatus=01 or agencyTatus=01)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<OrgInfo> listOrgInfoForAudit(Page<OrgInfo> page,Map<String,Object> param) throws Exception {
		return orgInfoDaoHibernate.listOrgInfoForAudit(page,param);
	}
	
	/** 
     * Description : 修改机构信息，如果机构已经通过审核，
     * 				 则创建新的数据，并创建扩展信息，否则更新原有信息
     * 				 如果是创建新数据，则currentId的值需同步为原有数据的id
     * Create Date: 2010-7-22下午05:09:57 by sunl  Modified Date: 2010-7-22下午05:09:57 by sunl
     * @param   baseObject 可以传入多个扩展信息，包括Supplier、agency、buyer
     * @return  
     * @Exception   
     */
	public String saveModifyOrgInfo(OrgInfo orgInfo,String saveStatus,GpcBaseObject... baseObject) throws Exception {
		String orgInfoId = "";
		GpcBaseObject obj = null;
		Supplier supplier = null;
		Buyer buyer = null;
		Agency agency = null;
		for(int i=0; i<baseObject.length; i++){
			obj = baseObject[i];
			if(obj instanceof Supplier){
				supplier = (Supplier)obj;
			}else if(obj instanceof Buyer){
				buyer = (Buyer)obj;
			}else if(obj instanceof Agency){
				agency = (Agency)obj;
			}
		}
		//同步company信息
		Company oldCompany = (Company)AuthenticationHelper.getCurrentUser(true).getEmp().getCompany();
		Company company = orgInfo.getCompany();
		oldCompany.setName(orgInfo.getOrgName());
		oldCompany.setCode(orgInfo.getOrgCode());
		oldCompany.setMobilePhone(company.getMobilePhone());
 		oldCompany.setTel(company.getTel());
 		if(oldCompany.getTown() == null){
 			District town = new District();
 			town.setObjId(orgInfo.getDistrictId());
 			oldCompany.setTown(town);
 		}
		oldCompany.getTown().setObjId(orgInfo.getDistrictId());
		oldCompany.setAddress(company.getAddress());
		oldCompany.setPostCode(company.getPostCode());
		
		orgInfo.setCompany(oldCompany);
		
		if(orgInfo.getAuditStatus().equals(OrganizationEnum.PASS_EXAM)){//审核通过
			orgInfo = orgInfoManager.save(orgInfo);
		} 
		else{//待审核或审核不通过,将就对象取出，设置几个页面提交的新值，再级联保存，避免更新为空
			if(saveStatus.equals("01")) {//提交
				orgInfo.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
			}else if(saveStatus.equals("02")) {//保存为正式 
				orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);
				orgInfo.setUseStatus(OrganizationEnum.USE_VALID);
				orgInfo.setValidTime(new Date());
				
				//获取机构管理员用户赋予权限
				User user = userManagerImpl.getManagerUser(company.getObjId(), User.USER_TYPE_MANAGER.toString());
				if(user != null && user.getObjId() != null) {
					Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
					if(!StringUtils.isEmpty(orgInfo.getSupplierId())) {
						Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
						roles.add(supplierRole);
					}else if(!StringUtils.isEmpty(orgInfo.getBuyerId())) {
						Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
						roles.add(buyerRole);
					}
					User userRole = userManagerImpl.get(user.getObjId());
					userRole.setRoles(roles);
					userManagerImpl.save(userRole);
				}
			}
			orgInfoManager.save(orgInfo);
			orgInfoId = orgInfo.getObjId();
		}
		
		//更新扩展信息，更新orgInfo里的扩展信息Id（扩展信息不保存历史2010-9-10）
		if(!StringUtils.isEmpty(orgInfo.getSupplierId())){
			if(saveStatus.equals("02")) {//保存为正式 
				supplier.setAuditStatus(OrganizationEnum.PASS_EXAM);
			}else if(saveStatus.equals("01")) {//提交
				supplier.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
			}
			orgInfo.setBidForRange(supplier.getBidForRange());
			supplierManager.saveSupplier(false, supplier);
		}
		if(!StringUtils.isEmpty(orgInfo.getBuyerId())){
			if(saveStatus.equals("02")) {//保存为正式 
				buyer.setAuditStatus(OrganizationEnum.PASS_EXAM);
			}else if(saveStatus.equals("01")) {//提交
				buyer.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
			}
			buyerManager.saveBuyer(false,buyer);
		}
		if(!StringUtils.isEmpty(orgInfo.getAgencyId())){
			if(saveStatus.equals("02")) {//保存为正式 
				agency.setAuditStatus(OrganizationEnum.PASS_EXAM);
			}else if(saveStatus.equals("01")) {//提交
				agency.setAuditStatus(OrganizationEnum.AWAIT_EXAM);
			}
			agencyManager.saveAgency(false,agency);
		}
		
		return orgInfoId;
	}
	
	/** 
	 * Description :  发送通知邮件
	 * Create Date: 2010-8-29下午02:52:21 by sunl  Modified Date: 2010-8-29下午02:52:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private void sendEmail(String userId,String orgName,String applyName,String subjectMsg, String templateMsg) throws Exception {
		//此处不能从内存中取得用户邮箱地址,因为可能用户已经修改了邮箱地址
		User user = (User)get(userId, User.class);
		
		//机构审核通过后,邮件发送到企业邮箱里
		String email = user.getEmp().getCompany().getEmail();
        Map model = new HashMap();
        model.put("user", user);
        model.put("orgName", orgName);//机构名称
        model.put("applyName", applyName);//申请的机构名称
        model.put("appName",SysInfo.getInstance().getProjectname());//系统名称
        model.put("appUrl", SysInfo.getInstance().getSitename());//首页
        model.put("loginUrl", SysInfo.getInstance().getServername()+ "/IndexViewController.do?method=toLogin");//登录页面
        
        //发送邮件
        MailUtil.sendEmailAlways(new String[]{email} , messageSource.getMessage(subjectMsg),null, messageSource.getMessage(templateMsg), model);
	}
	
	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * 				  查询条件为根据companyId获取创建时间最新的orginfo信息 
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getLastedOrgInfo(String companyId,boolean isGetValid) throws Exception {
		return orgInfoDaoHibernate.getLastedOrgInfo(companyId,isGetValid);
	}

	/** 
	 * Description :  注册信息的审核（包含管理员做的新增机构）
	 * 				  如果通过,则将useStatus变为01、auditStatus变为03、生效时间为当前时间
	 * 				  不通过，则将auditStatus变为04
	 * 				  需同步扩展信息的审核状态，同步company的审核状态
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForReg(OrgInfo orgInfo,Map<String,Object> params) throws Exception {
		OrgInfo obj = orgInfoManager.get(orgInfo.getObjId());
		
		//审核
		preVerify(obj);
		obj.setOpinion(orgInfo.getOpinion());
		
		//同步company的审核状态
		Company company = obj.getCompany();
		
		//获取机构管理员用户
		User user = userManagerImpl.getManagerUser(company.getObjId(), User.USER_TYPE_MANAGER.toString());
		
		if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
			obj.setUseStatus(OrganizationEnum.USE_VALID);//有效
			obj.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
			obj.setValidTime(new Date());//生效时间
			obj.setAuditType(null);//审核类型变为空
			company.setAuditStatus(Organization.ORG_AUDIT_STATUS_AGREE);
			
			Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
			StringBuffer orgRoles = new StringBuffer();//记录角色ID
			
			if(!StringUtils.isEmpty(obj.getSupplierId())){
				Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
				roles.add(supplierRole);
				orgRoles.append(supplierRole.getObjId()+",");
				
				//是否厂商,是厂商,添加厂商角色
				if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "1".equals(orgInfo.getIsManufacturer())){
					Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
					roles.add(manufacturerRole);
					orgRoles.append(manufacturerRole.getObjId()+",");
				}
				//是否添加服务商角色
				else if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "2".equals(orgInfo.getIsManufacturer())) {
					Role serverRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SERVER);
					roles.add(serverRole);
					orgRoles.append(serverRole.getObjId()+",");
				}
			}
			if(!StringUtils.isEmpty(obj.getBuyerId())){
				Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
				roles.add(buyerRole);
				orgRoles.append(buyerRole.getObjId()+",");
			}
			if(!StringUtils.isEmpty(obj.getAgencyId())){
				Role agencyRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_AGENCY);
				roles.add(agencyRole);
				orgRoles.append(agencyRole.getObjId()+",");
			}
			
			//将角色授予所注册的用户即机构管理员
			User USER = userManagerImpl.get(user.getObjId());
			USER.setRoles(roles);
			userManagerImpl.save(USER);
			
			//将当前角色权限授予机构
			organizationManagerImpl.saveOrgRole(company.getObjId(), orgRoles.toString().substring(0,orgRoles.toString().length()-1).split(","));
			
			//发送邮件给注册的用户(审核通过模板邮件)
			sendEmail(user.getObjId(),obj.getOrgName(),"",CustomerMessage.ORGINFO_AUDIT_COMPLETE, CustomerMessage.ORGINFO_AUDIT_PASS_TEMPLATE);
			
			//获取模板所需数据
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("orgName", orgInfo.getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			String title = "";
			String content = "";
			try {
				title = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.ORG_REG_AUDIT_PASS_REMIND_TITLE), templateMap);
				content = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.ORG_REG_AUDIT_PASS_REMIND_CONTENT), templateMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//发送短信
			MobileMessageUtil.sendMobileMessage(USER.getEmp().getMobile(),AuthenticationHelper.getCurrentUser(true).getObjId(), AuthenticationHelper.getCurrentUser(true).getUsername(), USER.getObjId(), USER.getUsername(), content, null, null);
			
			//发送站内信
			MessageUtil.sendMessageSystem(new String[]{USER.getObjId()}, title, content, null, null, false);
			
		}else{
			obj.setAuditStatus(OrganizationEnum.NO_PASS_EXAM);//不通过
			company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DISAGREE);
			
			//发送邮件给注册的用户(审核不通过模板邮件)
			sendEmail(user.getObjId(),obj.getOrgName(),"",CustomerMessage.ORGINFO_AUDIT_COMPLETE, CustomerMessage.ORGINFO_AUDIT_NOT_PASS_TEMPLATE);
			
			//发送短信
			User USER = userManagerImpl.get(user.getObjId());
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("orgName", orgInfo.getOrgName());
			templateMap.put("siteName", SysInfo.getInstance().getSitename());
			MobileMessageUtil.sendMobileMessage(USER.getEmp().getMobile(),AuthenticationHelper.getCurrentUser(true).getObjId(), AuthenticationHelper.getCurrentUser(true).getUsername(), USER.getObjId(), USER.getUsername(), null, CustomerMessage.ORG_REG_AUDIT_PASS_REMIND_CONTENT, templateMap);
		}
		//同步扩展信息状态
		updateExtendInfoStatus(orgInfo);
		
		//初始会员信息
		params.put("orgInfo", obj);
		memberManager.initMemberInfo(params);
	}
	
	/** 
	 * Description :  变更信息的审核
	 * 				  如果通过,则将当前数据C的useStatus变为01、auditStatus变为03、生效时间为当前时间，currentId置空，
	 * 				  同步将objId为currentId的数据B的useStatus变为02，失效时间为当前时间。
	 * 				  同步将所有currentId为B的数据A的currentId变为当前数据C的objId
	 * 				  不通过，则将auditStatus变为04
	 * 				  需同步扩展信息的审核状态，同步company的审核状态
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForMod(OrgInfo orgInfo) throws Exception {
		OrgInfo obj = orgInfoManager.get(orgInfo.getObjId());
		//获取机构管理员用户
		User user = userManagerImpl.getManagerUser(obj.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
		
		//同步company的审核状态
		Company company = obj.getCompany();
		String currentId = obj.getCurrentId();
		OrgInfo validOrg = orgInfoManager.get(currentId);
		if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
			//获得变更前的orgInfo,将临时的orgInfo名称代码经营范围更新进去
			String oldCode = validOrg.getOrgCode();
			String oldName = validOrg.getOrgName();
			String oldRange = validOrg.getBidForRange();
			validOrg.setOrgCode(obj.getOrgCode());
			validOrg.setOrgName(obj.getOrgName());
			validOrg.setBidForRange(obj.getBidForRange());
			validOrg.setBelongIndustry(obj.getBelongIndustry());
			validOrg.setEntPrpt(obj.getEntPrpt());
			validOrg.setUnitScape(obj.getUnitScape());
			validOrg.setBegainDate(obj.getBegainDate());
			validOrg.setWebUrl(obj.getWebUrl());
			validOrg.setMainProducts(obj.getMainProducts());
			validOrg.setEntCapacity(obj.getEntCapacity());
			validOrg.setDescCn(obj.getDescCn());
			validOrg.setIsManufacturer(orgInfo.getIsManufacturer());
			
			//临时orgInfo更新为无效,code,name,range等更新为变更前数据保留历史
			obj.setUseStatus(OrganizationEnum.USE_INVALID);//失效
			obj.setAuditStatus(OrganizationEnum.PASS_EXAM);//以免再次审核
			obj.setInvalidTime(new Date());//失效时间
			obj.setOrgCode(oldCode);
			obj.setOrgName(oldName);
			obj.setBidForRange(oldRange);
		}else{
			obj.setAuditStatus(OrganizationEnum.NO_PASS_EXAM);//不通过
			company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DISAGREE);
		}
		
		//同步扩展信息状态并授权角色
		if(!StringUtils.isEmpty(orgInfo.getSupplierId())){
			Supplier supplier = supplierManager.get(obj.getSupplierId());
			if(OrganizationEnum.AWAIT_EXAM.equals(supplier.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
				roles.add(supplierRole);
				//是否厂商,是厂商,添加厂商角色
				if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "1".equals(orgInfo.getIsManufacturer())){
					Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
					roles.add(manufacturerRole);
				}
				//是否添加服务商角色
				else if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "2".equals(orgInfo.getIsManufacturer())) {
					Role serverRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SERVER);
					roles.add(serverRole);
				}
			}
			//已通过审核的扩展信息,状态不变,否则更改状态
			if(!OrganizationEnum.PASS_EXAM.equals(supplier.getAuditStatus())) {
				supplier.setAuditStatus(orgInfo.getAuditStatus());
			}
			supplier.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
			supplier.setVerifyTime(new Date());
			supplier.setOpinion(orgInfo.getOpinion());
			supplier.setOrgInfo(obj);
		}
		if(!StringUtils.isEmpty(orgInfo.getBuyerId())){
			Buyer buyer = buyerManager.get(obj.getBuyerId());
			if(OrganizationEnum.AWAIT_EXAM.equals(buyer.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
				roles.add(buyerRole);
			}
			//已通过审核的扩展信息,状态不变,否则更改状态
			if(!OrganizationEnum.PASS_EXAM.equals(buyer.getAuditStatus())) {
				buyer.setAuditStatus(orgInfo.getAuditStatus());
			}
			buyer.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
			buyer.setVerifyTime(new Date());
			buyer.setOpinion(orgInfo.getOpinion());
			buyer.setOrgInfo(obj);
		}
		if(!StringUtils.isEmpty(orgInfo.getAgencyId())){
			Agency agency = agencyManager.get(obj.getAgencyId());
			if(OrganizationEnum.AWAIT_EXAM.equals(agency.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role agencyRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_AGENCY);
				roles.add(agencyRole);
			}
			//已通过审核的扩展信息,状态不变,否则更改状态
			if(!OrganizationEnum.PASS_EXAM.equals(agency.getAuditStatus())) {
				agency.setAuditStatus(orgInfo.getAuditStatus());
			}
			agency.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
			agency.setVerifyTime(new Date());
			agency.setOpinion(orgInfo.getOpinion());
			agency.setOrgInfo(obj);
		}
		//授予注册机构所注册的角色
		User userRole = userManagerImpl.get(user.getObjId());
		userRole.setRoles(roles);
		userManagerImpl.save(userRole);
		
		if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
			//发送邮件给机构变更的用户(审核通过模板邮件)
			sendEmail(user.getObjId(),obj.getOrgName(),"",CustomerMessage.ORGINFO_MODIFY_AUDIT_COMPLETE, CustomerMessage.ORGINFO_MODIFY_PASS_TEMPLATE);
		}else{
			sendEmail(user.getObjId(),obj.getOrgName(),"",CustomerMessage.ORGINFO_MODIFY_AUDIT_COMPLETE, CustomerMessage.ORGINFO_MODIFY_NOT_PASS_TEMPLATE);
		}
	}
	
	/** 
	 * Description :  申请信息的审核
	 * Create Date: 2010-7-28下午04:55:24 by sunl  Modified Date: 2010-7-28下午04:55:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForApply(OrgInfo orgInfo) throws Exception {
		
		OrgInfo obj = orgInfoManager.get(orgInfo.getObjId());
		obj.setAuditStatus(orgInfo.getAuditStatus());
		if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())) {
			obj.setAuditType(null);//审核类型变为空
		}
		//获取机构管理员用户
		User user = userManagerImpl.getManagerUser(obj.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
		String applyName = "";//记录所申请机构的名称
		
		//如果审核通过就将所申请角色授权给用户
		if(!StringUtils.isEmpty(obj.getSupplierId())){
			Supplier supplier = supplierManager.get(obj.getSupplierId());
			if(OrganizationEnum.AWAIT_EXAM.equals(supplier.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
				roles.add(supplierRole);
				//是否厂商,是厂商,添加厂商角色
				if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "1".equals(orgInfo.getIsManufacturer())){
					Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
					roles.add(manufacturerRole);
				}
				//不是厂商就不厂商角色去掉以免第二次提交审核还存在厂商角色
				else{
					Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
					roles.remove(manufacturerRole);
				}
				applyName = "供应商";
			}
			
			//申请服务商的话，审核通过就添加服务商角色
			if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "2".equals(orgInfo.getIsManufacturer())) {
				if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())) {
					Role serverRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SERVER);
					roles.add(serverRole);
				}
			}
			
			//同步所申请的供应商角色
			if(OrganizationEnum.AWAIT_EXAM.equals(supplier.getAuditStatus())) {
				supplierManager.updateSupplierStatus(orgInfo.getSupplierId(), orgInfo);
			}
		}
		if(!StringUtils.isEmpty(obj.getBuyerId())){
			Buyer buyer = buyerManager.get(obj.getBuyerId());
			if(OrganizationEnum.AWAIT_EXAM.equals(buyer.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
				roles.add(buyerRole);
				applyName = "采购人";
			}
			
			//同步所申请的采购人角色
			if(OrganizationEnum.AWAIT_EXAM.equals(buyer.getAuditStatus())) {
				buyerManager.updateBuyerStatus(orgInfo.getBuyerId(), orgInfo);
			}
		}
		if(!StringUtils.isEmpty(obj.getAgencyId())){
			Agency agency = agencyManager.get(obj.getAgencyId());
			if(OrganizationEnum.AWAIT_EXAM.equals(agency.getAuditStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
				Role agencyRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_AGENCY);
				roles.add(agencyRole);
				applyName = "代理机构";
			}
			
			//同步所申请的代理机构角色
			if(OrganizationEnum.AWAIT_EXAM.equals(agency.getAuditStatus())) {
				agencyManager.updateAgencyStatus(orgInfo.getAgencyId(), orgInfo);
			}
		}
		//授予注册机构所注册的角色
		User userRole = userManagerImpl.get(user.getObjId());
		userRole.setRoles(roles);
		userManagerImpl.save(userRole);
		
		if(OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus())){
			//发送邮件给机构申请的用户(审核通过模板邮件)
			sendEmail(user.getObjId(),obj.getOrgName(), applyName,CustomerMessage.ORGINFO_APPLY_AUDIT_COMPLETE, CustomerMessage.ORGINFO_APPLY_PASS_TEMPLATE);
		}else{
			sendEmail(user.getObjId(),obj.getOrgName(), applyName,CustomerMessage.ORGINFO_APPLY_AUDIT_COMPLETE, CustomerMessage.ORGINFO_APPLY_NOT_PASS_TEMPLATE);
		}
	}
	
	/** 
	 * Description :  普通审核（处理用户提交产生的信息进行审核，只进行审核不进行其他操作）
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgInfo(OrgInfo orgInfo) throws Exception {
		OrgInfo obj = orgInfoManager.get(orgInfo.getObjId());
		obj.setOpinion(orgInfo.getOpinion());
		obj.setAuditStatus(orgInfo.getAuditStatus());
		
		//获取机构管理员用户和角色
		User user = userManagerImpl.getManagerUser(obj.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
		Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
		//不是厂商就不厂商角色去掉以免第二次提交审核还存在厂商角色
		if(StringUtils.isEmpty(orgInfo.getIsManufacturer()) || "0".equals(orgInfo.getIsManufacturer())){
			Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
			roles.remove(manufacturerRole);
		}else if(!StringUtils.isEmpty(orgInfo.getIsManufacturer()) && "1".equals(orgInfo.getIsManufacturer())){
			Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
			roles.add(manufacturerRole);
		}

		//同步扩展信息状态
		updateExtendInfoStatus(orgInfo);
	}
	
	/**
	 * 同步扩展信息状态
	 * Description :  
	 * Create Date: 2010-11-10下午01:26:57 by sunl  Modified Date: 2010-11-10下午01:26:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateExtendInfoStatus(OrgInfo orgInfo) throws Exception {
		//同步扩展信息状态
		if(!StringUtils.isEmpty(orgInfo.getSupplierId())){
			supplierManager.updateSupplierStatus(orgInfo.getSupplierId(), orgInfo);
		}
		if(!StringUtils.isEmpty(orgInfo.getBuyerId())){
			buyerManager.updateBuyerStatus(orgInfo.getBuyerId(), orgInfo);
		}
		if(!StringUtils.isEmpty(orgInfo.getAgencyId())){
			agencyManager.updateAgencyStatus(orgInfo.getAgencyId(), orgInfo);
		}
	}
	
	/** 
	 * Description :  开启或禁用机构账号,并更新机构下用户状态
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateDisableOrEnable(String orgInfoId,String isOff) throws Exception {
		//开启或禁用机构
		OrgInfo orgInfo = orgInfoManager.get(orgInfoId);
		orgInfo.setIsOff(isOff);
		
		//开启或禁用机构下用户
		String userIsLocked = "0";
		if(isOff.equals(OrganizationEnum.DISABLE)){
			userIsLocked = "1";//如果禁用，那么锁定用户
		}
		orgInfoManager.disableOrEnableUser(orgInfo.getCompany().getObjId(), userIsLocked);
	}
	
	/** 
	 * Description :  获取机构历史信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return	
	 * @Exception   
	 */
	public List<OrgInfo> getOrgHistory(Map<String,Object> param) throws Exception {
		return orgInfoDaoHibernate.getOrgHistory(param);
	}
	
	/**
	 * 
	 * Description :  验证机构代码和机构名称的唯一
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Integer checkOrgUnique(Map<String,Object> param) throws Exception {
		return orgInfoDaoHibernate.checkOrgUnique(param);
	}
	
	/**
	 * 
	 * Description :  模糊检验机构名称
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String,Object> checkOrgName(Map<String,Object> param) throws Exception {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<String> list = orgInfoDaoHibernate.checkOrgName(param);
		
		if(list!=null && list.size()>0){
			result.put(Constants.SUCCESS,true);
			result.put(Constants.JSON_RESULT, "");
			result.put("sameNameList", list);
		}else {
			result.put(Constants.SUCCESS,true);
			result.put(Constants.JSON_RESULT, "机构名称可以使用!");
		}
		
		return result;
	}
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，包括供应商信息，采购人信息，代理机构信息
	 * 				  机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:50:25 by sunl  Modified Date: 2010-8-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgAllInfo(String objIds) throws Exception {
		return orgInfoDaoHibernate.getOrgAllInfo(objIds);
	}

	/** 
	 * Description :  查询是否通过审核
	 * Create Date: 2010-11-10下午06:41:44 by yucy  Modified Date: 2010-11-10下午06:41:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean getIsAuditPass(Map<String, Object> param) throws Exception {
		return orgInfoDaoHibernate.getIsAuditPass(param);
	}
	
	/** 
     * Description : 维护企业基本信息
     * Create Date: 2010-7-22下午05:09:57 by sunl  Modified Date: 2010-7-22下午05:09:57 by sunl
     * @param 
     * @return  
     * @Exception   
     */
	public String saveEntBaseInfo(OrgInfo orgInfo,String saveType) throws Exception {
		String orgInfoId = "";
		String status = orgInfo.getAuditStatus();
		if(saveType.equals("submit")) {
			status = OrganizationEnum.PASS_EXAM;
		}else if("submitOfOrg".equals(saveType)){
			status = OrganizationEnum.AWAIT_EXAM;
		}
		//同步company信息
		Company oldCompany = (Company)organizationServiceImpl.get(orgInfo.getCompany().getObjId());
		
		District town = new District();
		town.setObjId(orgInfo.getDistrictId());
		oldCompany.setTown(town);//行政区域
		
		Company company = orgInfo.getCompany();
		oldCompany.setName(orgInfo.getOrgName());//机构名称
		oldCompany.setCode(orgInfo.getOrgCode());//机构代码
		oldCompany.setMobilePhone(company.getMobilePhone());//手机
 		oldCompany.setTel(company.getTel());//办公电话
 		oldCompany.setFax(company.getFax());//办公传真
 		oldCompany.setEmail(company.getEmail());//公司email
		oldCompany.setAddress(company.getAddress());//详细地址
		oldCompany.setPostCode(company.getPostCode());//邮编
		oldCompany.setCroporate(company.getCroporate());//法定代表人
		oldCompany.setOrgSite(company.getOrgSite());//机构域名 by yucy
		
		if(orgInfo.getAuditStatus().equals(OrganizationEnum.PASS_EXAM)){//审核通过
			orgInfo.setCompany(oldCompany);
			orgInfo = orgInfoManager.save(orgInfo);
		} 
		else{//待审核或审核不通过,将就对象取出，设置几个页面提交的新值，再级联保存，避免更新为空
			if(saveType.equals("submit")) { //运营管理员保存为正式
				orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);
				orgInfo.setUseStatus(OrganizationEnum.USE_VALID);
				orgInfo.setValidTime(new Date());
				
				//获取机构管理员用户赋予权限
				StringBuffer orgRoles = new StringBuffer();//记录角色ID
				User user = userManagerImpl.getManagerUser(company.getObjId(), User.USER_TYPE_MANAGER.toString());
				if(user != null && user.getObjId() != null) {
					Set<Role> roles = userManagerImpl.get(user.getObjId()).getRoles();
					if(!StringUtils.isEmpty(orgInfo.getSupplierId())) {
						Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
						orgRoles.append(supplierRole.getObjId()+",");
						roles.add(supplierRole);
					}else if(!StringUtils.isEmpty(orgInfo.getBuyerId())) {
						Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
						orgRoles.append(buyerRole.getObjId()+",");
						roles.add(buyerRole);
					}
					User USER = userManagerImpl.get(user.getObjId());
					USER.setRoles(roles);
					userManagerImpl.save(USER);
					
					//将当前角色权限授予机构
					organizationManagerImpl.saveOrgRole(company.getObjId(), orgRoles.toString().substring(0,orgRoles.toString().length()-1).split(","));
				}
			}else{//机构管理员提交
				orgInfo.setAuditStatus(status);
			}
			orgInfo.setCompany(oldCompany);
			orgInfoManager.save(orgInfo);
			orgInfoId = orgInfo.getObjId();
		}
		
		//更新扩展信息，更新orgInfo里的扩展信息Id（扩展信息不保存历史2010-9-10）
		if(!StringUtils.isEmpty(orgInfo.getSupplierId())){
			Supplier supplier = supplierManager.get(orgInfo.getSupplierId());
			supplier.setAuditStatus(status);
			supplierManager.saveSupplier(false, supplier);
			orgInfo.setSupplierId(supplier.getObjId());
		}
		if(!StringUtils.isEmpty(orgInfo.getBuyerId())){
			Buyer buyer = buyerManager.get(orgInfo.getBuyerId());
			buyer.setAuditStatus(status);
			buyerManager.saveBuyer(false,buyer);
			orgInfo.setBuyerId(buyer.getObjId());
		}
		if(!StringUtils.isEmpty(orgInfo.getAgencyId())){
			Agency agency = agencyManager.get(orgInfo.getAgencyId());
			agency.setAuditStatus(status);
			agencyManager.saveAgency(false,agency);
			orgInfo.setAgencyId(agency.getObjId());
		}
		
		return orgInfoId;
	}
	
	/** 
	 * Description :  根据供应商名称查询供应商列表
	 * Create Date: 2010-11-22下午03:40:51 by guoyr  Modified Date: 2010-11-22下午03:40:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfo> getAllOrgInfoByOrgName(String orgName){
		return orgInfoDaoHibernate.getAllOrgInfoByOrgName(orgName);
	}
	
	/** 
	 * Description :  根据当前用户返回机构状态
	 * Create Date: 2011-4-12下午02:07:42 by sunl  Modified Date: 2011-4-12下午02:07:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> getCurrentOrgStatus() throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String currSuppStatus = "";
		String currBuyStatus = "";
		
		User user = AuthenticationHelper.getCurrentUser(true);
		//获取当前机构状态，包括供应商或采购人状态
		OrgInfo currOrg = (OrgInfo)user.getOrgInfo();
		if(currOrg != null && !StringUtils.isEmpty(currOrg.getSupplierId())) {
			currSuppStatus = supplierManager.get(currOrg.getSupplierId()).getAuditStatus();
		}
		if(currOrg != null && !StringUtils.isEmpty(currOrg.getBuyerId())) {
			currBuyStatus = buyerManager.get(currOrg.getBuyerId()).getAuditStatus();
		}
		
		resultMap.put("currSuppStatus", currSuppStatus);
		resultMap.put("currBuyStatus", currBuyStatus);
		
		return resultMap;
	}
	
	/** 
	 * Description :  根据当前组织机构id获得orgInfo
	 * Create Date: 2011-9-22上午11:33:04 by liangxj  Modified Date: 2011-9-22上午11:33:04 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByCompany(String companyId) throws Exception {
		return orgInfoManager.getOrgInfoByCompany(companyId);
	}
	
	/** 
	 * Description :  插入/更新auth_orgnizaiton数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveCompany(Company company,String saveType) throws Exception {
		return orgInfoDaoHibernate.saveCompany(company,saveType);
	}
	
	/** 
	 * Description :  插入/更新org_info数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveOrgInfo(OrgInfo orgInfo,String saveType) throws Exception {
		return orgInfoDaoHibernate.saveOrgInfo(orgInfo,saveType);
	}
}
