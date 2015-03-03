package com.gpcsoft.smallscale.point.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;
import com.gpcsoft.smallscale.point.domain.Rule;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.point.manager.ExchangeManager;
import com.gpcsoft.smallscale.point.manager.PromoterInfoManager;
import com.gpcsoft.smallscale.point.manager.PromoterManager;
import com.gpcsoft.smallscale.point.manager.RuleManager;
import com.gpcsoft.smallscale.point.service.PromoterService;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service
public class PromoterServiceImpl extends BaseGenericServiceImpl<Promoter>
		implements PromoterService {

	@Autowired(required = true)
	@Qualifier("promoterManagerImpl")
	private PromoterManager promoterManager;
	
	@Autowired(required = true)
	@Qualifier("userManagerImpl")
	private UserManager userManager;
	
    @Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDaoHibernate;
    
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("promoterInfoManagerImpl")
	private PromoterInfoManager promoterInfoManager;
	
	@Autowired(required=true) @Qualifier("exchangeManagerImpl")
	private ExchangeManager exchangeManager;   //积分
	
	@Autowired(required=true) @Qualifier("ruleManagerImpl")
	private RuleManager ruleManager;

	public void createByUserId(String promoterid, User buyer,Company company,
			String demo) {
		promoterManager.createByUserId(promoterid, buyer, company, demo);
	}

	/**
	 * Description : 推广人发送邮件 Create Date: 2010-10-27下午03:17:20 by dongcl
	 * Modified Date: 2010-10-27下午03:17:20 by dongcl
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public void sendMail(String mailadds, String objIds, String receiverName,
			String title, String content) throws Exception {

		List<String> emailAddrs = new ArrayList<String>();
		User user = null;

		// 输入的邮箱地址
		if (null != mailadds) {
			String[] addres = mailadds.split(",");
			for (int i = 0; i < addres.length; i++) {
				emailAddrs.add(addres[i]);
			}
		}

		// 取得用户邮箱
		if (null != objIds) {
			String[] userIds = objIds.split(",");

			for (int i = 0; i < userIds.length; i++) {
				user = userManager.get(userIds[i]);
				if (user != null && StringUtils.hasLength(user.getEmail())) {
					emailAddrs.add(user.getEmail());
				}
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		User cuser = AuthenticationHelper.getCurrentUser(true);
		// 发送人信息
		model.put("user", cuser);
		model.put("centent", content);
        String url = SysInfo.getInstance().getSitename() + messageSource.getMessage(CommonEnum.APP_REGURL);
        if(cuser!=null){
    	   url += "&uid="+ cuser.getObjId();
        }
		model.put("regUrl", url);// 注册地址
		
		//发送邮件
		MailUtil.sendEmailAlways(
				(String[]) emailAddrs.toArray(new String[emailAddrs.size()]),
				title,
				null, 
				messageSource.getMessage("smallscale.promoter.invitation.template"), 
				model);

	}
	
	/** 
	 * Description :  推广人推荐保存
	 * Create Date: 2010-11-23下午02:43:02 by dongcl  Modified Date: 2010-11-23下午02:43:02 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean savePromoter(Promoter promoter) throws Exception{
		boolean isNew = true;
		User user = AuthenticationHelper.getCurrentUser(true);		
		if(promoter.getObjId()!=null){//session缓存问题？
			promoter.setObjId(null);
		}
		if(promoter.getPromoterUnitName()!=null){
			Promoter dbPromoter = this.getByOrgName(promoter.getPromoterUnitName());			
			
			if(user != null ){	
				promoter.setPromoterCID(user.getEmp().getIdCard());
				promoter.setPromoterName(user.getEmp().getName());
			}
			
			if((null!=dbPromoter  && null == dbPromoter.getPromoterCID() )|| null ==promoter.getPromoterCID()){
				throw new Exception("推广人身份证为空异常！");				
			}
			
			//当前推广员没有推广过该企业时，才保存推广数据
			if(dbPromoter == null || !dbPromoter.getPromoterCID().equals(promoter.getPromoterCID())){			
				promoter.setRecordType(SmallscaleEnum.RECORD_TYPE_PROMOTER);
				promoter.setDealStatus(SmallscaleEnum.PROMOTER_STATUS_NEW);				
				promoter.setValidationCode(StringUtils.getRandomString(SmallscaleEnum.PROMOTER_CODE_LENGTH));				
				promoter.setCreator(user);
				Date now = new Date();
				promoter.setCreateTime(now);
				promoter.setPromoterDate(now);
				promoterManager.save(promoter);
			}
			else{
				isNew = false;
			}
		}
		
		if(isNew){//已推荐过不再发邮件		
			List<String> emailAddrs = new ArrayList<String>();		
	
			// 输入的邮箱地址
			if (null != promoter.getEmail()) {		
					emailAddrs.add(promoter.getEmail());			
			}		
	
			Map<String, Object> model = new HashMap<String, Object>();
			
			// 发送人信息
			model.put("user", user);
			model.put("promoter", promoter);
			model.put("now", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			String siteUrl = SysInfo.getInstance().getSitename();
			String url = siteUrl + messageSource.getMessage(CommonEnum.APP_REGURL);
			model.put("siteUrl", siteUrl);
			model.put("regUrl", url);// 注册地址
			
			//发送邮件
			MailUtil.sendEmailAlways(
					(String[]) emailAddrs.toArray(new String[emailAddrs.size()]),
					messageSource.getMessage(OrganizationEnum.PROMOTER_TITLE),
					null,
					messageSource.getMessage("smallscale.promoter.invitation.template"), 
					model);
		}
		return isNew;
		
	}
	
	/** 
	 * Description :  推广人录入推广数据
	 * Create Date: 2010-11-16下午01:32:24 by dongcl  Modified Date: 2010-11-16下午01:32:24 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Promoter saveByType(Promoter promoter ){
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		if(promoter.getRecordType()!=null){
			if(promoter.getRecordType().equals(SmallscaleEnum.RECORD_TYPE_PROMOTER)){//推广人录入
				promoter.setPromoterCID(user.getEmp().getIdCard());
				promoter.setPromoterName(user.getEmp().getName());
				promoter.setPromoter(user);
				promoter.setDealStatus(SmallscaleEnum.PROMOTER_STATUS_NEW);
			}
			else if(promoter.getRecordType().equals(SmallscaleEnum.RECORD_TYPE_BUYER)){//采购人录入
				promoter.setPromotedLinkName(user.getEmp().getName());
				promoter.setPromotedLinkTel(user.getEmp().getMobile());
				promoter.setPromoterUnitName(user.getEmp().getCompany().getName());
				promoter.setPromoterUnitCode(user.getEmp().getCompany().getCode());
				promoter.setOrg(user.getEmp().getCompany());
				promoter.setEmail(user.getEmail());
				promoter.setDealStatus(SmallscaleEnum.PROMOTER_STATUS_NEW);
			}
			else if(promoter.getRecordType().equals(SmallscaleEnum.RECORD_TYPE_ADMIN)){//管理员录入				
				promoter.setDealStatus(SmallscaleEnum.PROMOTER_STATUS_DEAL);
				//推荐积分
				User promoterUser = this.getUserByIdCard(promoter.getPromoterCID());
				this.saveExchange(promoterUser);
			}	
			
			promoter.setCreateTime(new Date());
			promoter.setPromoterDate(new Date());
			promoter.setCreator(user);
			
			
			promoter.setValidationCode(StringUtils.getRandomString(SmallscaleEnum.PROMOTER_CODE_LENGTH));		
			
			promoterManager.save(promoter);
		}
		return promoter;
	}
	
	/** 
	 * Description :  确认已推广用户
	 * Create Date: 2010-11-16下午01:32:39 by dongcl  Modified Date: 2010-11-16下午01:32:39 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean saveDeal(Promoter promoter,String dealStatus){
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		Promoter newPromoter = promoterManager.get(promoter.getObjId());
		if(newPromoter.getValidationCode().equals(promoter.getValidationCode())){
			newPromoter.setDealUser(user);
			newPromoter.setDealStatus(dealStatus);
			promoterManager.save(newPromoter);			
			//推荐积分
			User promoterUser = null;
			if(newPromoter.getPromoter() != null){
				promoterUser = newPromoter.getPromoter();
			}
			else{//根据身份证取得用户
				promoterUser = this.getUserByIdCard(newPromoter.getPromoterCID());				
			}
			this.saveExchange(promoterUser);
			
			return true;
		}
		else{
			return false;
		}		
	}
	
	
	/** 
	 * Description :  根据被推广人公司名称取值
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Promoter  getByOrgName(String orgName){
		
		return promoterManager.getByOrgName(orgName);
	}
	


	/** 
	 * Description :  保存用户(给用户增加了新的角色后保存)
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveUser(User userRole) {
		//取得传入的idCard
		String idCard = userRole.getEmp().getIdCard();
		
		userRole = this.getUser(userRole.getObjId());
		userRole.getEmp().setIdCard(idCard);
		Role roleOrg = this.getRoleByType(OrganizationEnum.ROLE_PROMOTER);//获得推广人角色
		Set<Role> roles = this.getUser(userRole.getObjId()).getRoles();
		
	    roles.add(roleOrg);
	    userRole.setRoles(roles);
	    
	    PromoterInfo proInof = new PromoterInfo();
	    proInof.setPromoterType(SmallscaleEnum.PROMOTER_TYPE_B);
		proInof.setUser(userRole);
		
		promoterInfoManager.save(proInof);
		
		userManager.save(userRole);
	}

	
	/** 
	 * Description :  获得推广人角色
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Role getRoleByType(String roleType) {
		return roleDaoHibernate.getRoleByType(roleType);
	}

	
	/** 
	 * Description : 根据用户id获取用户信息
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public User getUser(String userId) {
		return userManager.get(userId);
	}

	
	/** 
	 * Description : 根据用户id,角色类型判断此用户是否拥有此角色
	 * Create Date: 2010-11-16下午03:37:42 by dongcl  Modified Date: 2010-11-16下午03:37:42 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isHasThisRole(String userId, String roleType) {
		return roleManagerImpl.isHasThisRole(userId,roleType);
	}
	
	
	/**
	 * 推广人获得推荐积分
	 */
	private void saveExchange(User user){
		
		Rule rule = ruleManager.getUniqueRule(SmallscaleEnum.EXCHANGE_SOURCE_PRO, null);
		
		if(rule != null && null !=rule.getPointNumber() ){
			Exchange exchange = new Exchange();
			exchange.setExchangeNumber(new Long(rule.getPointNumber()));		
			exchange.setCreateTime(new Date());
			exchange.setCurrentStatus(SmallscaleEnum.CURRENT_STATUS_YES);			
			
			exchange.setExchangeType(SmallscaleEnum.EXCHANGE_TYPE_HORTATION);
			Calendar calendar = Calendar.getInstance();
			
			calendar.add(Calendar.YEAR, 2);//当前时间加2年
			exchange.setValDate(calendar.getTime());
			
			exchange.setObtainDate(new Date());
			
			exchange.setUserId(user);		
			exchangeManager.save(exchange);
		
		}
	}
	
	
	private User getUserByIdCard(String idCard){
		Object[] params = new Object[1];
		params[0] = idCard;
		List<User> users = userManager.findByHql("from User u where u.emp.idCard=?", params);
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
}
