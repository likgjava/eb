package com.gpcsoft.pubservice.common.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.model.BaseObject;

public interface SecDomainDao extends BaseGenericDao<BaseObject> {

	/** 
	 * Description :  
	 * Create Date: 2011-10-11上午11:34:13 by yucy  Modified Date: 2011-10-11上午11:34:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getPassSecDomainInfo() throws Exception;

	
}
