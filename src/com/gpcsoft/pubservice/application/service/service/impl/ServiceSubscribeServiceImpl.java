package com.gpcsoft.pubservice.application.service.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.member.manager.MemberClassManager;
import com.gpcsoft.pubservice.application.member.manager.MemberManager;
import com.gpcsoft.pubservice.application.service.dao.ServiceSubscribeDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.manager.ServiceSubscribeManager;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

@Service 
public class ServiceSubscribeServiceImpl extends BaseGenericServiceImpl<ServiceSubscribe> implements ServiceSubscribeService {

	@Autowired(required=true) @Qualifier("serviceSubscribeDaoHibernate")
	private ServiceSubscribeDao serviceSubscribeDao;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeManagerImpl")
	private ServiceSubscribeManager serviceSubscribeManager;
	
	@Autowired(required=true) @Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Autowired(required=true) @Qualifier("memberClassManagerImpl")
	private MemberClassManager memberClassManager;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  更新服务订阅支付状态和支付金额
	 * Create Date: 2011-7-21下午04:30:56 by sunl  Modified Date: 2011-7-21下午04:30:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean updateServicePayStatus(String serviceOrderId, BigDecimal payAmount, String payStatus) throws Exception {
		ServiceSubscribe serviceSubscribe = serviceSubscribeDao.get(serviceOrderId);
		Role role = roleManagerImpl.getRoleByType(serviceSubscribe.getServiceBase().getObjId());
		User user  =  serviceSubscribe.getCreateUser();
		//更新角色
		user.getRoles().add(role);
		
		return serviceSubscribeDao.updateServicePayStatus(serviceOrderId, payAmount, payStatus);
	}
	
	/** 
	 * Description :  我订阅的服务
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceSubscribe> getMySubscribeList(Map<String,Object> params) throws Exception {
		return serviceSubscribeDao.getMySubscribeList(params);
	}
	
	/** 
	 * Description :  审核订阅的服务
	 * 				  审核通过后同步会员信息
	 * Create Date: 2011-3-30上午11:48:13 by sunl  Modified Date: 2011-3-30上午11:48:13 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditSubscribe(Map<String,Object> params) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		
		String subscribeId = (String)params.get("subscribeId");//订阅ID
		String auditStatus = (String)params.get("auditStatus");//审核状态
    	
    	ServiceSubscribe servcieSubscribe = serviceSubscribeDao.get(subscribeId);
    	servcieSubscribe.setAuditor(user);
    	servcieSubscribe.setAuditTime(new Date());
    	servcieSubscribe.setAuditStatus(auditStatus);
    	
    	//如果审核通过,同步会员信息,顺延开始时间和结束时间
    	if(OrganizationEnum.PASS_EXAM.equals(auditStatus)) {
    		servcieSubscribe.setEndTime(getDelayDate(servcieSubscribe.getStartTime(),servcieSubscribe.getEndTime()));//结束日期向后延时
    		servcieSubscribe.setStartTime(new Date());//开始时间从审核通过时间开始算起
    		
    		//获取机构有效的缴费总金额
    		params.put("orgInfoId", servcieSubscribe.getOrgInfo().getObjId());//订阅机构ID
    		Double totalFee = serviceSubscribeDao.getTotalFeeByOrgInfoId(params);
    		
    		/**
    		 * 根据有效缴费总金额获取会员级别
    		 * 如果是第一次缴费，则根据当前缴费金额获取一个会员级别
    		 */
    		if(totalFee == 0) {
    			params.put("totalFee", servcieSubscribe.getPayAmount().doubleValue());
    		} else {
    			params.put("totalFee", totalFee);
    		}
    		MemberClass newMemberClass = memberClassManager.getMemberClassByDurationAndFee(params);
    		
    		//如果没有这个缴费区间的会员级别,则获取初始会员级别
    		if(null == newMemberClass) {
    			newMemberClass = memberClassManager.getInitMemberClass();
    		}
    		
    		/**
    		 * 同步机构的会员信息，包括会员级别，累加会员缴费金额
    		 * orgInfoId-订阅服务的机构ID
    		 * payAmount-订阅服务缴纳费用
    		 * memberClassId-新会员级别
    		 */
    		params.put("payAmount", servcieSubscribe.getPayAmount());
    		params.put("memberClassId", newMemberClass.getObjId());
    		memberManager.syncMemberInfo(params);
    	}
    	
    	serviceSubscribeDao.save(servcieSubscribe);
	}
	
	/** 
	 * Description :  判断服务是否已订阅
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer isServcieSubscribed(Map<String,Object> params) throws Exception {
		return serviceSubscribeDao.isServcieSubscribed(params);
	}
	
	/** 
	 * Description :  获取已经订阅的服务ID（以逗号分割）
	 * Create Date: 2011-4-22下午02:49:13 by likg  Modified Date: 2011-4-22下午02:49:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getSubscribedServiceIds(Map<String,Object> params) throws Exception {
		return serviceSubscribeDao.getSubscribedServiceIds(params);
	}
	
	//获取顺延后的日期
	private Date getDelayDate(Date oldStartTime,Date oldEndDate) {
		//开始日期和当前日期相差的天数
		float d = new Date().getTime() - oldStartTime.getTime();
		float dd = d / 86400000f;
		int delayDay = (int)dd;
		
		//结束日期向后延后
		Calendar cal = Calendar.getInstance();
		cal.setTime(oldEndDate);
		cal.add(Calendar.DATE, delayDay);
		return cal.getTime();
	}
	
	/** 
	 * Description :  机构是否商圈会员（是否具有商圈会员服务）
	 * 				  是，返回true；否，返回false
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isShangQuanHuiYuan(String orgInfoId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgInfoId", orgInfoId);
		params.put("serviceId", ServiceEnum.SERVICE_A);//商圈会员服务
		
		return serviceSubscribeManager.isOrgInfoHasService(params);
	}
	
	/** 
	 * Description :  是否具有公告订阅服务
	 * Create Date: 2011-7-25上午09:34:03 by yucy  Modified Date: 2011-7-25上午09:34:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean hasBulletinOrder(String orgInfoId) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgInfoId", orgInfoId);
		params.put("serviceId", ServiceEnum.SERVICE_B);//公告订阅服务
		return serviceSubscribeManager.isOrgInfoHasService(params);
	}

	/** 
	 * Description :  机构拥有某些服务
	 * Create Date: 2011-8-8上午10:42:48 by yucy  Modified Date: 2011-8-8上午10:42:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isOrgInfoHasService(Map<String, Object> params) throws Exception {
		return serviceSubscribeManager.isOrgInfoHasService(params);
	}
}
