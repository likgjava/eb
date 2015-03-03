package com.gpcsoft.pubservice.application.message.manager;

import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.message.domain.Complain;

public interface ComplainManager extends BaseManager<Complain> {

	/** 
	 * Description :  查询投诉列表
	 * Create Date: 2011-7-11下午04:40:14 by yucy  Modified Date: 2011-7-11下午04:40:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Complain> getComplainList(Page<Complain> page,Map<String, Object> paramsMap) throws Exception;

}
