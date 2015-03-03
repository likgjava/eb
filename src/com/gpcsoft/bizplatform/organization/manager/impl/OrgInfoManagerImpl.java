package com.gpcsoft.bizplatform.organization.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.dao.OrganizationDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class OrgInfoManagerImpl extends BaseManagerImpl<OrgInfo> implements OrgInfoManager {

	@Autowired(required=true)  @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	@Autowired(required=true)  @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDaoHibernate;
    @Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
    @Autowired(required=true)  @Qualifier("organizationDaoHibernate")
    private OrganizationDao organizationDaoHibernate;
	
	/**
	 * Description : 将代理机构、供应商、采购人、主管单位、监管机构相同的机构信息、用户信息、账号信息等保存
     * Create Date: 2010-7-9下午13:35:55 by sunl  Modified Date: 2010-7-9下午13:35:55 by sunl
	 * @return
	 * @throws Exception
	 */
	public OrgInfo saveSimilar(Company company,Employee employee,OrgInfo orgInfo,User user) throws Exception{
		/** 设置公司信息 */ 
		company.setName(orgInfo.getOrgName());
		company.setCode(orgInfo.getOrgCode());
    	company.setAddress(orgInfo.getOrgAddress());
    	company.setIsLeaf(true);
    	company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DRAFT);  //审核状态为临时
    	company.setContact(employee.getName());  //联系人
    	company.setMobilePhone(employee.getMobile());//移动电话
    	company.setLevel(new Integer(Organization.ORG_TYPE_COM).shortValue());
    	WordToSpell spell = new WordToSpell();
    	company.setShortSpellName(spell.String2Alpha(orgInfo.getOrgName())); //拼音简写
    	company.setCreateTime(new Date());
    	
    	/** 保存机构信息，同步级联保存公司信息 */ 
    	orgInfo.setCompany(company);
    	orgInfo.setUseStatus(OrganizationEnum.USE_TEMP);//临时状态
    	if(!StringUtils.hasLength(orgInfo.getAuditStatus())) {
    		orgInfo.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    	}
    	orgInfo.setAuditType(OrganizationEnum.REG_AUDIT);//注册审核
    	orgInfo.setIsOff(OrganizationEnum.ENABLE);//启用禁用(默认启用1)
    	orgInfo.setCreateTime(new Date());
    	
    	this.save(orgInfo);/*保存机构*/
    	orgInfo.getCompany().setPath(orgInfo.getCompany().getObjId());
    	
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
        
    	/** 保存员工信息 和 账号信息 */ 
    	employee.setCompany(orgInfo.getCompany());
    	Set<User> users = new HashSet<User>();
    	String password=MathUtil.encryptPassWordSHA(user.getPassword());   //密码加密
    	user.setPassword(password);
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置当前日期
        c.add(Calendar.YEAR, 1); //日期加1 
        Date date = c.getTime(); //结果

    	user.setUsrPeriodOfValidity(date);
    	user.setUsrPwdPeriodValidity(date);
    	Role roleOrg = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_ORG);//获得机构管理员默认角色
    	
    	Set<Role> roles = new HashSet<Role>();
    	if(null != user.getRoles() && user.getRoles().size() > 0) {
    		roles = user.getRoles();
    	}
    	roles.add(roleOrg);
    	user.setRoles(roles);
    	
		user.setUsrIsAdmin(User.USER_TYPE_MANAGER); //默认为机构管理员用户
		user.setCreateTime(new Date());
		users.add(user);
	
    	employee.setUsers(users);
    	employee.setShortSpellName(spell.String2Alpha(employee.getName()));  //姓名简写
    	employeeDaoHibernate.save(employee);/*保存个人信息**/
    	user.setEmp(employee);
    	 
    	orgInfo.setUser(user);/*增加的机构管理员*/
    	orgInfo.setCreateUser(user);
         
		return orgInfo;
	}

	public OrgInfo getQualityOrgInfo(String objId) throws Exception {
		return orgInfoDaoHibernate.getOrgInfoByCompanyId(objId);
	}
	
	/** 
	 * Description :  开启或禁用机构下用户
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void disableOrEnableUser(String companyId,String userIsLocked) throws Exception {
		orgInfoDaoHibernate.disableOrEnableUser(companyId, userIsLocked);
	}
	
	/** 
	 * Description :  将代理机构、主管单位、监管机构相同的机构信息等保存
	 * Create Date: 2010-11-15上午08:31:50 by likg  Modified Date: 2010-11-15上午08:31:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo saveSimilar(Company company, Employee employee, OrgInfo orgInfo) throws Exception{
		//设置公司信息
		company.setName(orgInfo.getOrgName());
		company.setCode(orgInfo.getOrgCode());
    	company.setAddress(orgInfo.getOrgAddress());
    	company.setIsLeaf(true);
    	company.setAuditStatus(Organization.ORG_AUDIT_STATUS_DRAFT);  //审核状态为临时
    	company.setContact(employee.getName());  //联系人
    	company.setLevel(new Integer(Organization.ORG_TYPE_COM).shortValue());
    	WordToSpell spell = new WordToSpell();
    	company.setShortSpellName(spell.String2Alpha(orgInfo.getOrgName())); //拼音简写
    	company.setCreateTime(new Date());
    	
    	//保存机构信息，同步级联保存公司信息 
    	orgInfo.setCompany(company);
    	orgInfo.setUseStatus(OrganizationEnum.USE_TEMP);//临时状态
    	orgInfo.setAuditStatus(OrganizationEnum.DRAFT_EXAM);//临时
    	orgInfo.setAuditType(OrganizationEnum.REG_AUDIT);//注册审核
    	orgInfo.setIsOff(OrganizationEnum.ENABLE);//启用禁用(默认启用1)
    	orgInfo.setCreateTime(new Date());
    	
    	this.save(orgInfo);//保存机构
    	orgInfo.getCompany().setPath(orgInfo.getCompany().getObjId());
    	 
    	//保存员工信息
    	employee.setCompany(orgInfo.getCompany());
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置当前日期
        c.add(Calendar.YEAR, 1); //日期加1 
    	
    	//employee.setShortSpellName(spell.String2Alpha(employee.getName()));  //姓名简写
    	employeeDaoHibernate.save(employee);/*保存个人信息**/
         
		return orgInfo;
	}

	/**
	 * Description :  根据机构名称获取此机构信息
	 * Create Date: 2011-1-13上午11:14:53 by zhaojf  Modified Date: 2011-1-13上午11:14:53 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public OrgInfo getOrgInfoByOrgName(String orgName) throws Exception {
		String hql = "from orgInfo where orgName = " + orgName;
		return orgInfoDaoHibernate.findbyHql(hql).get(0);
	}
	
	/** 
	 * Description :  根据当前组织机构code获得orgInfo
	 * Create Date: 2011-12-9下午02:30:10 by yucy  Modified Date: 2011-12-9下午02:30:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByOrgCode(String orgCode) throws Exception {
		return orgInfoDaoHibernate.getOrgInfoByOrgCode(orgCode);
	}
	
	
	/** 
	 * Description :  根据当前组织机构id获得orgInfo
	 * Create Date: 2011-9-22上午11:33:04 by liangxj  Modified Date: 2011-9-22上午11:33:04 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByCompany(String companyId) throws Exception {
		if ("".equals(companyId))
			return null;
		
		QueryObject<OrgInfo> query=new QueryObjectBase<OrgInfo>();
		query.setEntityClass(OrgInfo.class);
		query.getQueryParam().and(new QueryParam("company.objId",QueryParam.OPERATOR_EQ,companyId));
		List<OrgInfo> orgList = this.findByQuery(query);
		
		if(orgList != null && orgList.size() > 0)
			return orgList.get(0);
		else 
			return null;
	}
}
