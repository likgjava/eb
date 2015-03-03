package com.gpcsoft.bizplatform.base.purcatalog.dao;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PurCatalogDistrictDao extends BaseGenericDao<PurCatalogDistrict> {

	/** 
	 * Description :  删除中间对象
	 * Create Date: 2010-8-25上午10:05:46 by yucy  Modified Date: 2010-8-25上午10:05:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deletePurCatalogDistrictByCataId(String purCatalogId);

}
