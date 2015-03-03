package com.gpcsoft.goods.goodsprice.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsGiftPrice;

public interface GoodsGiftPriceDao extends BaseGenericDao<GoodsGiftPrice> {

	/** 
	 * Description :  获取礼包价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsGiftPrice> getGoodsGiftPriceList(Map<String, Object> param) throws Exception;

}
