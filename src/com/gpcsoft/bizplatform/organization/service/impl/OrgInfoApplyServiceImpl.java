package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.dao.AgencyDao;
import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.buyer.dao.BuyerDao;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoApplyDao;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoApplyService;
import com.gpcsoft.bizplatform.suppliers.dao.SupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.OrganizationManager;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.manager.UserManager;

@Service 
public class OrgInfoApplyServiceImpl extends BaseGenericServiceImpl<OrgInfoApply> implements OrgInfoApplyService {

	@Autowired(required=true) @Qualifier("orgInfoApplyDaoHibernate")
	private OrgInfoApplyDao orgInfoApplyDaoHibernate;
	@Autowired(required=true) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required=true) @Qualifier("organizationManagerImpl")
	private OrganizationManager organizationManagerImpl;
	@Autowired(required=true) @Qualifier("supplierDaoHibernate")
	private SupplierDao supplierDaoHibernate;
	@Autowired(required=true) @Qualifier("buyerDaoHibernate")
	private BuyerDao buyerDaoHibernate;
	@Autowired(required=true) @Qualifier("agencyDaoHibernate")
	private AgencyDao agencyDaoHibernate;
	
	/** 
	 * Description :  获取申请的机构角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoApply> getApplyOrgList(String orgId,String applyType) throws Exception {
		return orgInfoApplyDaoHibernate.getApplyOrgList(orgId,applyType);
	}
	
	/** 
	 * Description :  申请审核
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditApplyOrg(Map<String,Object> params) throws Exception {
		//获取参数
		String orgId = (String)params.get("orgId");
		String status = (String)params.get("status");
		String applyType = (String)params.get("applyType");
		
		//同步orgInfo中isManufacturer值
		OrgInfo org = orgInfoDaoHibernate.get(orgId);
		String oldR = org.getIsManufacturer();
		
		//审核通过后授权
		String userId = org.getUser().getObjId();
		User user = userManagerImpl.get(userId);
		Set<Role> roles = user.getRoles();
		StringBuffer orgRoles = new StringBuffer();//记录角色ID
		
		if("m".equals(applyType) && OrganizationEnum.PASS_EXAM.equals(status)) {//厂商审核通过
			if(!StringUtils.hasLength(oldR) || "0".equals(oldR)) {
				org.setIsManufacturer("1");
			} else {
				if(oldR.indexOf('2')==-1) {
					org.setIsManufacturer("1");
				}else {//如果已经是服务商
					org.setIsManufacturer("1,2");
				}
			}
			
			//授权厂商角色
			Role manufacturerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_MANUFACTURER);
			if(!roles.contains(manufacturerRole)) {
				roles.add(manufacturerRole);
			}
			orgRoles.append(manufacturerRole.getObjId()+",");
			
		} else if("r".equals(applyType) && OrganizationEnum.PASS_EXAM.equals(status)) {//服务商审核通过
			if(!StringUtils.hasLength(oldR) || "0".equals(oldR)) {
				org.setIsManufacturer("2");
			} else {
				if(oldR.indexOf('1')==-1) {
					org.setIsManufacturer("2");
				}else {//如果已经是厂商
					org.setIsManufacturer("1,2");
				}
			}
			
			//授权服务商角色
			Role serverRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SERVER);
			if(!roles.contains(serverRole)) {
				roles.add(serverRole);
			}
			orgRoles.append(serverRole.getObjId()+",");
		} else if("s".equals(applyType) && OrganizationEnum.PASS_EXAM.equals(status)) {//供应商审核通过
			//授权供应商角色
			Role supplierRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_SUPPLIER);
			if(!roles.contains(supplierRole)) {
				roles.add(supplierRole);
			}
			orgRoles.append(supplierRole.getObjId()+",");
			
			//创建supplier并同步orgInfo的supplierId
			Supplier supplier = new Supplier();
			supplier.setOrgInfo(org);
			supplier.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
			supplier = supplierDaoHibernate.save(supplier);
			org.setSupplierId(supplier.getObjId());
		} else if("b".equals(applyType) && OrganizationEnum.PASS_EXAM.equals(status)) {//采购人审核通过
			//授权采购人角色
			Role buyerRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUYER);
			if(!roles.contains(buyerRole)) {
				roles.add(buyerRole);
			}
			orgRoles.append(buyerRole.getObjId()+",");
			
			//创建buyer并同步orgInfo的buyerId
			Buyer buyer = new Buyer();
			buyer.setOrgInfo(org);
			buyer.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
			buyer = buyerDaoHibernate.save(buyer);
			org.setBuyerId(buyer.getObjId());
		} else if("a".equals(applyType) && OrganizationEnum.PASS_EXAM.equals(status)) {//代理机构审核通过
			//授权代理机构角色
			Role agencyRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_AGENCY);
			if(!roles.contains(agencyRole)) {
				roles.add(agencyRole);
			}
			orgRoles.append(agencyRole.getObjId()+",");
			
			//创建agency并同步orgInfo的buyerId
			Agency agency = new Agency();
			agency.setOrgInfo(org);
			agency.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
			agency = agencyDaoHibernate.save(agency);
			org.setAgencyId(agency.getObjId());
		}
		
		//将角色授予所注册的用户即机构管理员
		user.setRoles(roles);
		userManagerImpl.save(user);
		
		//将当前角色权限授予机构
		if(StringUtils.hasLength(orgRoles)) {
			organizationManagerImpl.saveOrgRole(org.getCompany().getObjId(), orgRoles.toString().substring(0,orgRoles.toString().length()-1).split(","));
		}
	}
}
