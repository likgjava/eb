package com.gpcsoft.goods.goodsprice.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;

public interface GoodsOptFitPriceManager extends BaseManager<GoodsOptFitPrice> {

	/** 
	 * Description :  保存选配行情
	 * Create Date: 2010-4-15下午01:43:24 by yucy  Modified Date: 2010-4-15下午01:43:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveOptFitPrice(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  根据行情id取得选配行情
	 * Create Date: 2011-12-8上午12:22:50 by yucy  Modified Date: 2011-12-8上午12:22:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	 List<GoodsOptFitPrice> getGoodsOptFitPriceListByGoodsPriceId(Map<String, Object> param) throws Exception;

}
