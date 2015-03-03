package com.gpcsoft.pubservice.application.service.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;

public interface ServiceGroupService extends BaseGenericService<ServiceGroup> {

	/** 
	 * Description :  获取搭配服务
	 * 				  根据会员级别进行过滤（会员级别为空的也应该被查询出来）
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	public List<ServiceGroup> getServiceGroupList(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :   新增/修改服务组合
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveServiceGroup(ServiceGroup serviceGroup) throws Exception;

	/** 
     * Description :  修改服务组合的使用状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	void updateUseStatus(String objId, String useStatus) throws Exception;
}
