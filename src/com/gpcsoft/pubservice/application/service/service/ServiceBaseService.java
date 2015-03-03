package com.gpcsoft.pubservice.application.service.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;

public interface ServiceBaseService extends BaseGenericService<ServiceBase> {

	/** 
	 * Description :   新增/修改服务
	 * Create Date: 2011-3-22下午01:21:16 by likg  Modified Date: 2011-3-22下午01:21:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveService(ServiceBase service) throws Exception;

	/** 
	 * Description :  查询要订阅的服务列表
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceBase> getServiceSubscribeList() throws Exception;
	
	 /** 
     * Description :  修改服务的状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	void updateStatus(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获得服务的详细信息（包括服务计费信息和服务组合信息）
	 * Create Date: 2011-4-6下午08:06:07 by likg  Modified Date: 2011-4-6下午08:06:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void getServiceAllInfo(Map<String, Object> param, Map<String, Object> model) throws Exception;
}
