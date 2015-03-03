package com.gpcsoft.goods.goods.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goods.domain.Goods;

public interface GoodsManager extends BaseManager<Goods>{

	/**
	 * Description :  拷贝商品参数的选配信息到新商品中
	 * Create Date: 2010-8-11下午02:41:57 by sunl  Modified Date: 2010-8-11下午02:41:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOptionFittingOfModify(Goods newGoods) throws Exception;
}
