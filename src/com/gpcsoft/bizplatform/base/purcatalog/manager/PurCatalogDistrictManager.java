package com.gpcsoft.bizplatform.base.purcatalog.manager;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.core.manager.BaseManager;

public interface PurCatalogDistrictManager extends BaseManager<PurCatalogDistrict> {

	/** 
	 * Description :  删除中间对象bycatalogId
	 * Create Date: 2010-8-25上午10:03:47 by yucy  Modified Date: 2010-8-25上午10:03:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deletePurCatalogDistrictByCataId(String purCatalogId);

}
