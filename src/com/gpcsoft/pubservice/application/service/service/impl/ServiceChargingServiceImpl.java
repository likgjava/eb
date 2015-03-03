package com.gpcsoft.pubservice.application.service.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.dao.ServiceChargingDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.manager.ServiceChargingManager;
import com.gpcsoft.pubservice.application.service.service.ServiceChargingService;

@Service 
public class ServiceChargingServiceImpl extends BaseGenericServiceImpl<ServiceCharging> implements ServiceChargingService {

	@Autowired(required=true) @Qualifier("serviceChargingManagerImpl")
	private ServiceChargingManager serviceChargingManager;

	@Autowired(required=true) @Qualifier("serviceChargingDaoHibernate")
	private ServiceChargingDao serviceChargingDao;

	/** 
	 * Description :  获取服务计费信息
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	public List<ServiceCharging> getServiceChargingList(Map<String,Object> params) throws Exception {
		return serviceChargingDao.getServiceChargingList(params);
	}
	
	/** 
	 * Description :   新增/修改服务计费
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveServiceCharging(ServiceCharging serviceCharging) throws Exception {
		//新增
		if(!StringUtils.hasLength(serviceCharging.getObjId())) {
			serviceCharging.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			serviceCharging.setCreateTime(new Date());
			serviceCharging.setUseStatus(ServiceEnum.USE_VALID);
		}
		
		serviceChargingManager.save(serviceCharging);
	}

	/** 
     * Description :  修改服务计费的使用状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	public void updateUseStatus(String objId, String useStatus) throws Exception {
		ServiceCharging serviceCharging = serviceChargingManager.get(objId);
		
		if(ServiceEnum.USE_TEMP.equals(useStatus)) { //设置为临时状态，可以再启用
			serviceCharging.setUseStatus(ServiceEnum.USE_TEMP);
		} else if(ServiceEnum.USE_VALID.equals(useStatus)) { //设置为有效状态（启用状态）
			serviceCharging.setUseStatus(ServiceEnum.USE_VALID);
		} else if(ServiceEnum.USE_INVALID.equals(useStatus)) { //设置为无效状态（不可再启用，相当于删除）
			serviceCharging.setUseStatus(ServiceEnum.USE_INVALID);
		}
	}

	/** 
	 * Description : 根据服务ID获取各个级别最小时长的服务计费
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceCharging> getMinAgeAndFeeByOrgInfoId(Map<String,Object> params) throws Exception {
		return serviceChargingDao.getMinAgeAndFeeByOrgInfoId(params);
	}
}
