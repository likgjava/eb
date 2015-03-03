package com.gpcsoft.bizplatform.base.purcatalog.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PurCatalogProcTypeDao extends BaseGenericDao<PurCatalogProcType> {

	/** 
	 * Description :  获得catalogType 明细
	 * Create Date: 2010-8-11上午10:51:13 by yucy  Modified Date: 2010-8-11上午10:51:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogProcType> purCatalogProcTypeHibernate(Map<String, Object> param);

	/** 
	 * Description :  获得PurCatalogProcTypeList  by catalogId
	 * Create Date: 2010-8-12上午12:00:03 by yucy  Modified Date: 2010-8-12上午12:00:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogProcType> getProcTypeInfoByCatalogId(String catalogId);

}
