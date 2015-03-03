package com.gpcsoft.goods.goodsprice.manager;

import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;

public interface GoodsPriceSupplierManager extends BaseManager<GoodsPriceSupplier> {

	/** 
	 * Description : 保存供应商行情 
	 * Create Date: 2010-4-15下午02:18:09 by yucy  Modified Date: 2010-4-15下午02:18:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	GoodsPriceSupplier saveSupplierPrice(Map<String, Object> param) throws Exception;

}
