package com.gpcsoft.pubservice.application.message.service;


import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;

import com.gpcsoft.pubservice.application.message.domain.Complain;

public interface ComplainService extends BaseGenericService<Complain> {
	
	/**
	 * 投诉举报
	 * @param complain
	 */
	void saveTell(Complain complain );
	
    /**
     * 处理投诉
     * @param complain
     */
    void saveDeal(Complain complain ) throws Exception;

	/** 
	 * Description :  查询投诉列表
	 * Create Date: 2011-7-11下午04:40:14 by yucy  Modified Date: 2011-7-11下午04:40:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Complain> getComplainList(Page<Complain> page3, Map<String, Object> paramsMap) throws Exception;  
}
