package com.gpcsoft.smallscale.register.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.WordToSpell;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.register.manager.PromoterRegisterManager;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.dao.UserDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;


@Repository
public class PromoterRegisterManagerImpl extends BaseManagerImpl<Promoter> implements PromoterRegisterManager {


	
	@Autowired(required=true)  @Qualifier("userDaoHibernate")
	private UserDao userDao;
	
	@Autowired(required=true)  @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
	
//	@Autowired(required=true)  @Qualifier("organizationDaoHibernate")
//	private OrganizationDao organizationDaoHibernate;
	

	
	public void saveOfRegisterPromoter(Company company, Employee employee,User user){
		/** 保存员工信息 和 账号信息 */ 
//		List<Organization> orgList = organizationDaoHibernate.getOrganizationBySpeciaFlag("MN");
//		if(orgList !=null  && orgList.size()>0){
//			company = (Company)orgList.get(0);			
//			employee.setCompany(company);
//		}
    	
    	Set<User> users = new HashSet<User>();
    	String password=MathUtil.encryptPassWordSHA(user.getPassword());   //密码加密
    	user.setPassword(password);
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置当前日期
        c.add(Calendar.YEAR, 1); //日期加1 
        Date date = c.getTime(); //结果

    	user.setUsrPeriodOfValidity(date);
    	user.setUsrPwdPeriodValidity(date);
    	Role roleOrg = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_PROMOTER);//获得推广人角色
    	Set<Role> roles = new HashSet<Role>();
    	roles.add(roleOrg);
    	user.setRoles(roles);
    	
		user.setUsrIsAdmin(User.USER_TYPE_MANAGER); //默认为机构管理员用户
		user.setCreateTime(new Date());
		users.add(user);
	    
		WordToSpell spell = new WordToSpell();
		
    	employee.setUsers(users);
    	employee.setShortSpellName(spell.String2Alpha(employee.getName()));  //姓名简写
    	employeeDaoHibernate.save(employee);/*保存个人信息**/
    	user.setEmp(employee);
    	
    	user = userDao.save(user);

	}
	
}
