package com.gpcsoft.pubservice.application.service.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ServiceSubscribeDao extends BaseGenericDao<ServiceSubscribe> {

	/** 
	 * Description :  更新服务订阅支付状态和支付金额
	 * Create Date: 2011-7-21下午04:30:56 by sunl  Modified Date: 2011-7-21下午04:30:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean updateServicePayStatus(String serviceOrderId, BigDecimal payAmount, String payStatus) throws Exception;
	
	/** 
	 * Description :  我订阅的服务
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceSubscribe> getMySubscribeList(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  判断服务是否已订阅
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer isServcieSubscribed(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  判断机构是否拥有某个服务
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isOrgInfoHasService(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description : 根据机构ID获取订阅的总时长和总缴费金额（有效订阅）
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> getTotalAgeAndFeeByOrgInfoId(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  获取已经订阅的服务ID（以逗号分割）
	 * Create Date: 2011-4-22下午02:49:13 by likg  Modified Date: 2011-4-22下午02:49:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getSubscribedServiceIds(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description : 根据机构ID获取总缴费金额
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Double getTotalFeeByOrgInfoId(Map<String,Object> params) throws Exception;

	/** 
	 * Description :  取得有某个服务的所有机构用户（机构管理员）
	 * Create Date: 2011-7-25上午10:25:53 by yucy  Modified Date: 2011-7-25上午10:25:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<User> getAllUserHasService(Map<String, Object> params) throws Exception;
}
