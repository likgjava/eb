package com.gpcsoft.goods.goodsprice.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;

public interface GoodsOptFitPriceService extends BaseGenericService<GoodsOptFitPrice> {
	
	/** 
	 * Description :  根据行情id取得选配行情
	 * Create Date: 2011-12-8上午12:22:50 by yucy  Modified Date: 2011-12-8上午12:22:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsOptFitPrice> getGoodsOptFitPriceListByGoodsPriceId( Map<String, Object> param ) throws Exception;
}
