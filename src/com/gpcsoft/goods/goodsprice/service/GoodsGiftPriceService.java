package com.gpcsoft.goods.goodsprice.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsprice.domain.GoodsGiftPrice;

public interface GoodsGiftPriceService extends BaseGenericService<GoodsGiftPrice> {

	/** 
	 * Description :  获取礼包价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsGiftPrice> getGoodsGiftPriceList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  保存和修改礼包价格信息
	 * Create Date: 2011-1-10下午03:17:25 by likg  Modified Date: 2011-1-10下午03:17:25 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveGoodsGiftPrice(Map<String, Object> param) throws Exception;

}
