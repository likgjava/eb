package com.gpcsoft.pubservice.application.service.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;

public interface ServiceChargingService extends BaseGenericService<ServiceCharging> {

	/** 
	 * Description :   新增/修改服务计费
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveServiceCharging(ServiceCharging serviceCharging) throws Exception;

	/** 
     * Description :  修改服务计费的使用状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	void updateUseStatus(String objId, String useStatus) throws Exception;

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
