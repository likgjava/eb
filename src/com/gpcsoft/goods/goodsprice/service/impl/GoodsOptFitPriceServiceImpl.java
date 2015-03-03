package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;
import com.gpcsoft.goods.goodsprice.manager.GoodsOptFitPriceManager;
import com.gpcsoft.goods.goodsprice.service.GoodsOptFitPriceService;

@Service 
public class GoodsOptFitPriceServiceImpl extends BaseGenericServiceImpl<GoodsOptFitPrice> implements GoodsOptFitPriceService {

	@Autowired(required=true) @Qualifier("goodsOptFitPriceManagerImpl")
	private GoodsOptFitPriceManager goodsOptFitPriceManager;
	
	/** 
	 * Description :  根据行情id取得选配行情
	 * Create Date: 2011-12-8上午12:22:50 by yucy  Modified Date: 2011-12-8上午12:22:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsOptFitPrice> getGoodsOptFitPriceListByGoodsPriceId( Map<String, Object> param ) throws Exception{
		return goodsOptFitPriceManager.getGoodsOptFitPriceListByGoodsPriceId(param);
	}
}
