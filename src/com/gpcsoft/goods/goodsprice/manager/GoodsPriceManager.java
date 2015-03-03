package com.gpcsoft.goods.goodsprice.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;

public interface GoodsPriceManager extends BaseManager<GoodsPrice> {

	/** 
	 * Description :  
	 * Create Date: 2010-4-15下午01:39:05 by yucy  Modified Date: 2010-4-15下午01:39:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	GoodsPrice savePrice(Map<String, Object> param);

    List<OrgInfo> getProvideSupplierByGoods(String s) throws Exception;

}
