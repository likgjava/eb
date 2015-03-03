package com.gpcsoft.bizplatform.base.purcatalog.service;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.core.service.BaseGenericService;

public interface PurCatalogProcTypeService extends BaseGenericService<PurCatalogProcType> {

	/**
	 * Description : 删除采购方式明细对象 Create Date: 2010-8-10下午04:09:34 by yucy Modified
	 * Date: 2010-8-10下午04:09:34 by yucy
	 * @param
	 * @return
	 * @Exception
	 */
	Boolean deletePurCatalogProcType(String objId) throws Exception;

}
