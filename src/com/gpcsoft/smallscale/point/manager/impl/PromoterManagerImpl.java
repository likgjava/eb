package com.gpcsoft.smallscale.point.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

import com.gpcsoft.smallscale.point.dao.PromoterDao;
import com.gpcsoft.smallscale.point.manager.PromoterManager;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;

import com.gpcsoft.srplatform.auth.dao.UserDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;



@Repository
public class PromoterManagerImpl extends BaseManagerImpl<Promoter> implements PromoterManager {

	@Autowired(required=true)  @Qualifier("promoterDaoHibernate")
	private PromoterDao promoterDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("userDaoHibernate")
	private UserDao userDao;
	

	
	public void createByUserId(String promoterid,User buyer,Company company,String demo){		
		Promoter p = new Promoter();
		User u = userDao.get(promoterid);
		p.setPromoter(u);
		p.setPromoterBuyer(buyer);
		p.setOrg(company);			
		p.setPromoterMemo(demo);
		
		p.setCreator(buyer);
		p.setDealStatus(SmallscaleEnum.PROMOTER_STATUS_NEW);
		p.setRecordType(SmallscaleEnum.RECORD_TYPE_BUYER);
		p.setPromotedLinkName(buyer.getEmp().getName());
		p.setPromotedLinkTel(buyer.getEmp().getMobile());
		p.setPromoterCID(u.getEmp().getIdCard());
		p.setPromoterDate(new Date());
		p.setPromoterName(u.getEmp().getName());
		p.setPromoterUnitCode(company.getCode());
		p.setPromoterUnitName(company.getName());
		p.setCreateTime(new Date());
		
		p.setValidationCode(StringUtils.getRandomString(SmallscaleEnum.PROMOTER_CODE_LENGTH));
		
		promoterDaoHibernate.save(p);
	}
	
	public Promoter getByOrgName(String orgName){
		String hql = "from Promoter where promoterUnitName='"+ orgName + "'";
		Object[] o = null;
		List<Promoter>  l = promoterDaoHibernate.findbyHql(hql,o);
		
		if(l!=null && l.size()>0){
			return l.get(0);
		}
		
		return null;
		
	}
	
//	public void saveOfRegisterPromoter(Company company, Employee employee,User user){
//		/** 保存员工信息 和 账号信息 */ 
//    	//employee.setCompany(orgInfo.getCompany());
//		//Company company = companyDaoHibernate.get(companyId);
//		
//    	employee.setCompany(company);
//    	Set<User> users = new HashSet<User>();
//    	String password=MathUtil.encryptPassWordSHA(user.getPassword());   //密码加密
//    	user.setPassword(password);
//    	Calendar c = Calendar.getInstance();
//        c.setTime(new Date());   //设置当前日期
//        c.add(Calendar.YEAR, 1); //日期加1 
//        Date date = c.getTime(); //结果
//
//    	user.setUsrPeriodOfValidity(date);
//    	user.setUsrPwdPeriodValidity(date);
//    	//Role roleOrg = roleDaoHibernate.getRoleByType(OrganizationEnum.ROLE_ORG);//获得机构管理员默认角色
//    	Role roleOrg = roleDaoHibernate.getRoleByType("p");//获得推广人角色
//    	Set<Role> roles = new HashSet<Role>();
//    	roles.add(roleOrg);
//    	user.setRoles(roles);
//    	
//		user.setUsrIsAdmin(OrganizationEnum.ROLE_ORG.charAt(0)); //默认为机构管理员用户
//		user.setCreateTime(new Date());
//		users.add(user);
//	    
//		WordToSpell spell = new WordToSpell();
//		
//    	employee.setUsers(users);
//    	employee.setShortSpellName(spell.String2Alpha(employee.getName()));  //姓名简写
//    	employeeDaoHibernate.save(employee);/*保存个人信息**/
//    	user.setEmp(employee);
//    	
//    	user = userDao.save(user);
//
//	}
//	
}
