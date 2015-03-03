package com.gpcsoft.goods.goods.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsAccessoriesPrice;

public interface GoodsAccessoriesPriceService extends BaseGenericService<GoodsAccessoriesPrice> {

	/** 
	 * Description :  获取零配件价格列表
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsAccessoriesPrice> listAccPrice(Page<GoodsAccessoriesPrice> page,Map<String,Object> param) throws Exception;
	
	/** 
	 * Description :  获取零配件价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsAccessoriesPrice> getGoodsAccessPriceList(Map<String, Object> param) throws Exception;
}
