package com.gpcsoft.bizplatform.suppliers.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.bizplatform.suppliers.dao.SupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.srplatform.auth.dao.OrganizationDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class SupplierServiceImpl extends BaseGenericServiceImpl<Supplier> implements SupplierService {

	@Autowired(required=true) @Qualifier("supplierManagerImpl")
	private SupplierManager supplierManager;
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	@Autowired(required=true) @Qualifier("supplierDaoHibernate")
	private SupplierDao supplierDaoHibernate;
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
	@Autowired(required=true)  @Qualifier("organizationDaoHibernate")
    private OrganizationDao organizationDaoHibernate;
	
	/** 
	 * Description :  保存供应商角色申请
	 * 				  保存供应商,机构里的supplierId更新为新保存的supplierId
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Supplier saveApplySupplier(Supplier supplier,String orgInfoId) throws Exception {
		OrgInfo orgInfo = orgInfoManager.get(orgInfoId);
		supplier.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		supplier.setOrgInfo(orgInfo);
		supplier = supplierManager.save(supplier);
		
		orgInfo.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		orgInfo.setAuditType(OrganizationEnum.APPLY_AUDIT);//申请审核
		orgInfo.setSupplierId(supplier.getObjId());//更新orgInfo里的supplierId
		return supplier;
	}

	/**
	 * Description : 供应商注册，同步保存公司信息、机构信息、供应商信息、员工信息、账号信息以及用户的默认角色
     * Create Date: 2010-7-9下午13:33:22 by sunl  Modified Date: 2010-7-9下午13:33:23 by sunl
	 * @return
	 * @throws Exception
	 */
	public Supplier saveSupplierOfRegister(Supplier supplier,Company company,Employee employee,OrgInfo orgInfo,User user) throws Exception{
		
		//加密之前的密码要在邮件里告知用户
		final String password = user.getPassword();
		
		//将默认供应商角色添加到角色里
		Set<Role> roles = new HashSet<Role>();
		Role defaultSupplier = roleDaoHibernate.getRoleByType(OrganizationEnum.DEFALT_SUPPLIER);//获取默认供应商角色
		roles.add(defaultSupplier);
		user.setRoles(roles);
		
		//调用orgInfo的机构保存方法
		OrgInfo org = orgInfoManager.saveSimilar(company, employee, orgInfo, user);  
		
		/** 以下是保存供应商详细信息*/
		if(!StringUtils.hasLength(orgInfo.getAuditStatus())) {
			supplier.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
		} else {
			supplier.setAuditStatus(orgInfo.getAuditStatus());
		}
		supplier.setOrgInfo(org);
		supplier.setCreateUser(user);
		supplier.setCreateTime(new Date());
		supplier = this.save(supplier);
		
		org.setSupplierId(supplier.getObjId());  //更新机构中的供应商id
		
		//发送通知邮件(注册完毕模板邮件)
		sendEmail(org,password,CustomerMessage.ORGINFO_REGISTER_COMPLETE,CustomerMessage.ORGINFO_REGISTER_TEMPLATE);
		
		return supplier;
	}
	
	/** 
	 * Description :  根据参数获得供应商的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含品目信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Supplier> getSupplierListForShow(Page<Supplier> page,Map<String, Object> paramsMap) throws Exception {
		return supplierDaoHibernate.getSupplierListForShow(page, paramsMap);
	}
	
	/** 
	 * Description :  根据品目获得包含供应商数量的区域信息
	 * Create Date: 2010-8-9下午03:25:57 by liangxj  Modified Date: 2010-8-9下午03:25:57 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String categoryCode,Short districtLevel,String keyWord) throws Exception {
		return supplierDaoHibernate.getDistrictListForShow(districtId, categoryCode,districtLevel,keyWord);
	}
	
	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf,String keyWord) throws Exception {
		return supplierDaoHibernate.getCategoryListShowByCategory(categoryId, categoryCode, isLeaf,keyWord);
	}
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Supplier> getSupplierAllInfoList(String objIds) throws Exception {
		return supplierDaoHibernate.getSupplierAllInfoList(objIds);
	}
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Supplier getSupplierAllInfo(String objId) throws Exception {
		return supplierDaoHibernate.getSupplierAllInfo(objId);
	}
	
	/** 
	 * Description :  发送通知邮件
	 * Create Date: 2010-7-29下午02:52:21 by sunl  Modified Date: 2010-7-29下午02:52:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private void sendEmail(OrgInfo orgInfo,String password,String subjectMsg, String templateMsg) throws Exception {
		//此处不能从内存中取得用户邮箱地址,因为可能用户已经修改了邮箱地址
		User user = (User)get(orgInfo.getUser().getObjId(), User.class);
        
        Map model = new HashMap();
        
        //加密之前的密码要在邮件里告知用户
        user.setUsrPasswordInit(password);//用户密码
        model.put("user", user);
        model.put("orgName", orgInfo.getOrgName());//机构名称
        model.put("appName",SysInfo.getInstance().getProjectname());//系统名称
        model.put("appUrl", SysInfo.getInstance().getSitename());//首页
        model.put("loginUrl", SysInfo.getInstance().getServername() + "/view/srplatform/login/login.jsp");//登录页面
        
        //发送邮件
        MailUtil.sendEmailAlways(new String[]{user.getEmail()}, messageSource.getMessage(subjectMsg),null, messageSource.getMessage(templateMsg), model);
	}
	
	/** 
	 * Description :  取消机构角色申请
	 * 				  删除supplier，并将orginfo的supplier信息置空
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String supplierId) throws Exception {
		 Supplier supplier = supplierManager.get(supplierId);
		 OrgInfo orgInfo = orgInfoManager.get(supplier.getOrgInfo().getObjId());
		 orgInfo.setSupplierId(null);
		 supplierManager.remove(supplierId, Supplier.class);
	}

	/** 
	 * Description :  保存供应商信息（只包括机构信息）
	 * Create Date: 2010-11-12下午08:29:16 by likg  Modified Date: 2010-11-12下午08:29:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveSimpleSupplier(Supplier supplier, OrgInfo orgInfo) throws Exception 
	{
		//设置公司信息
		Company company = orgInfo.getCompany();
		company.setName(orgInfo.getOrgName());
		company.setCode(orgInfo.getOrgCode());
    	company.setIsLeaf(true);
    	company.setLevel(new Integer(Organization.ORG_TYPE_COM).shortValue());
    	WordToSpell spell = new WordToSpell();
    	company.setShortSpellName(spell.String2Alpha(orgInfo.getOrgName())); //拼音简写
    	company.setCreateTime(new Date());
    	
		//保存机构信息，同步级联保存公司信息 
    	orgInfo.setIsOff(OrganizationEnum.ENABLE);//启用禁用(默认启用1)
    	orgInfo.setCreateUser(AuthenticationHelper.getCurrentUser(true));
    	orgInfo.setCreateTime(new Date());
    	
    	if(orgInfo.getUseStatus()!=null && orgInfo.getUseStatus().equals(OrganizationEnum.USE_VALID))
    	{
    		orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
    		company.setAuditStatus(Organization.ORG_AUDIT_STATUS_AGREE);  //审核状态为通过
    		supplier.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
    	}
    	else
    	{
    		orgInfo.setUseStatus(OrganizationEnum.USE_TEMP);//临时状态
    		orgInfo.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    		company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DRAFT);  //审核状态为临时
    		supplier.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    	}
    	
		OrgInfo org = orgInfoManager.save(orgInfo);
		
    	//设置上级单位
    	String parentId = "";
    	if(company.getParent() != null) {
    		parentId = company.getParent().getObjId();
    	}
    	if(StringUtils.hasLength(parentId)) {
	        Organization orgtion = (Organization)this.organizationDaoHibernate.get(parentId);
	        orgInfo.getCompany().setPath(orgtion.getPath() + "#" + orgInfo.getCompany().getObjId());
	        if (orgtion.getIsLeaf().booleanValue()) {
	            orgtion.setIsLeaf(Boolean.valueOf(false));
	        }
    	}
		
		//以下是保存供应商详细信息
		supplier.setOrgInfo(org);
		supplier.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		supplier.setCreateTime(new Date());
		supplier = this.save(supplier);
		
		org.setSupplierId(supplier.getObjId());  //更新机构中的供应商id
	}
}
