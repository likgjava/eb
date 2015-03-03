package com.gpcsoft.bizplatform.organization.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.core.service.BaseGenericService;

public interface IllegalRecService extends BaseGenericService<IllegalRec> {
	
	/** 
	 * Description :  获取违法信息
	 * Create Date: 2011-7-13上午12:35:44 by yucy  Modified Date: 2011-7-13上午12:35:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<IllegalRec> getIllegalRec(Map<String, Object> param) throws Exception;
	

}
