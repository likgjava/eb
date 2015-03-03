package com.gpcsoft.agreement.cart.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;

public interface ShoppingCartGoodsAccessoriesDao extends BaseGenericDao<ShoppingCartGoodsAccessories> {

	/** 
	 * Description :  获取商品零配件列表
	 * Create Date: 2011-1-18上午10:13:34 by likg  Modified Date: 2011-1-18上午10:13:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsAccessories> getGoodsAccessList(Map<String, Object> param) throws Exception;
}
