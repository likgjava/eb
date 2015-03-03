package com.gpcsoft.epp.common.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.domain.ApiLog;

public interface ApiLogDao extends BaseGenericDao<ApiLog>{

	/** 
	 * Description :  根据业务Id、交换类型查询日志记录
	 * Create Date: 2010-9-13下午02:37:26 by yangx  Modified Date: 2010-9-13下午02:37:26 by yangx
	 * @param   bizId：业务Id,apiType：交换类型
	 * @return  
	 * @Exception   
	 */
	public ApiLog getApiLogByBizIdAndApiType(String bizId,String apiType);
}
