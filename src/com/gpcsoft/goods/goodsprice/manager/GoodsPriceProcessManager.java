package com.gpcsoft.goods.goodsprice.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;

public interface GoodsPriceProcessManager extends BaseManager<GoodsPriceProcess> {

	/** 
	 * Description :  根据行情生成行情统计单元
	 * Create Date: 2010-10-12上午09:43:49 by yucy  Modified Date: 2010-10-12上午09:43:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveProcessByPrice(GoodsPrice goodsPrice) throws Exception;

}
