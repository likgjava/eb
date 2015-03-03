package com.gpcsoft.pubservice.application.service.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;

public interface ServiceBaseDao extends BaseGenericDao<ServiceBase> {

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
	public void updateStatus(Map<String, Object> param) throws Exception;
}
