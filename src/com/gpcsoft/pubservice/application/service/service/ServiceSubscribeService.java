package com.gpcsoft.pubservice.application.service.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;

public interface ServiceSubscribeService extends BaseGenericService<ServiceSubscribe> {

	/** 
	 * Description :  更新服务订阅支付状态和支付金额
	 * Create Date: 2011-7-21下午04:30:56 by sunl  Modified Date: 2011-7-21下午04:30:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean updateServicePayStatus(String serviceOrderId, BigDecimal payAmount , String payStatus) throws Exception;
	
	/** 
	 * Description :  我订阅的服务
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceSubscribe> getMySubscribeList(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  审核订阅的服务
	 * Create Date: 2011-3-30上午11:48:13 by sunl  Modified Date: 2011-3-30上午11:48:13 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditSubscribe(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  判断服务是否已订阅
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer isServcieSubscribed(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  获取已经订阅的服务ID（以逗号分割）
	 * Create Date: 2011-4-22下午02:49:13 by likg  Modified Date: 2011-4-22下午02:49:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getSubscribedServiceIds(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  机构是否商圈会员（是否具有商圈会员服务）
	 * 				  是，返回true；否，返回false
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isShangQuanHuiYuan(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  是否具有公告订阅服务
	 * Create Date: 2011-7-25上午09:34:03 by yucy  Modified Date: 2011-7-25上午09:34:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean hasBulletinOrder(String orgInfoId) throws Exception;

	/** 
	 * Description :  机构拥有某些服务
	 * Create Date: 2011-8-8上午10:42:48 by yucy  Modified Date: 2011-8-8上午10:42:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isOrgInfoHasService(Map<String, Object> params) throws Exception;
}
