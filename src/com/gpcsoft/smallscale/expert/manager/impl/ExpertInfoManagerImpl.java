package com.gpcsoft.smallscale.expert.manager.impl;

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
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.manager.ExpertInfoManager;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.dao.UserDao;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ExpertInfoManagerImpl extends BaseManagerImpl<ExpertInfo> implements ExpertInfoManager {
	
//	@Autowired(required=true)  @Qualifier("expertInfoDaoHibernate")
//	private ExpertInfoDao expertInfoDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("userDaoHibernate")
	private UserDao userDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
	
//	@Autowired(required=true)  @Qualifier("organizationDaoHibernate")
//	private OrganizationDao organizationDaoHibernate;

	/** 
	 * Description :  保存专家注册信息【用户信息，员工信息】
	 * Create Date: 2010-11-29下午05:19:38 by likg  Modified Date: 2010-11-29下午05:19:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveExpertInfoOfRegister(User user, Employee employee) throws Exception {
		//为employee设置一个默认的机构【特殊处理】
//		List<Organization> orgList = organizationDaoHibernate.getOrganizationBySpeciaFlag("MN");
//		if(orgList !=null  && orgList.size()>0){
//			Company company = (Company)orgList.get(0);
//			employee.setCompany(company);
//		}
		
    	//密码加密
    	String password = MathUtil.encryptPassWordSHA(user.getPassword());
    	user.setPassword(password);
    	
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置当前日期
        c.add(Calendar.YEAR, 1); //日期加1
        Date date = c.getTime(); //结果
    	user.setUsrPeriodOfValidity(date);
    	user.setUsrPwdPeriodValidity(date);
		user.setCreateTime(new Date());
		user.setUsrIsAdmin(User.USER_TYPE_MANAGER); //默认为机构管理员用户
		
		//分配角色
		Role role = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_EXPERT);//获得咨询专家角色
    	Set<Role> roles = new HashSet<Role>();
    	roles.add(role);
    	user.setRoles(roles);
	    
		//姓名简写
		WordToSpell spell = new WordToSpell();
    	employee.setShortSpellName(spell.String2Alpha(employee.getName()));
    	
    	//保存个人信息
    	employeeDaoHibernate.save(employee);
    	user.setEmp(employee);
    	
    	//保存用户信息
    	user = userDaoHibernate.save(user);
	}

}
