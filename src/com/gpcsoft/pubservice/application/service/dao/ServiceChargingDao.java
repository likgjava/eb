package com.gpcsoft.pubservice.application.service.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;

public interface ServiceChargingDao extends BaseGenericDao<ServiceCharging> {

	/** 
	 * Description :  获取服务计费信息
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	public List<ServiceCharging> getServiceChargingList(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description : 根据服务ID获取各个级别最小时长的服务计费
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceCharging> getMinAgeAndFeeByOrgInfoId(Map<String,Object> params) throws Exception;
}
