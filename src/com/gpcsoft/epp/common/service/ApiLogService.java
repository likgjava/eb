package com.gpcsoft.epp.common.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.domain.ApiLog;

public interface ApiLogService extends BaseGenericService<ApiLog> {

	/** 
	 * Description :  根据业务Id发送公告
	 * Create Date: 2010-9-15上午10:50:09 by yangx  Modified Date: 2010-9-15上午10:50:09 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveSendBulletin(String bizId);
}
