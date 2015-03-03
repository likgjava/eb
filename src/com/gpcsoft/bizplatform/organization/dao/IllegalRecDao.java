package com.gpcsoft.bizplatform.organization.dao;


import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface IllegalRecDao extends BaseGenericDao<IllegalRec> {

	/** 
	 * Description :  获取违法信息
	 * Create Date: 2011-7-13上午12:35:44 by yucy  Modified Date: 2011-7-13上午12:35:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<IllegalRec> getIllegalRec(Map<String, Object> param) throws Exception;

}
