package com.gpcsoft.agreement.cart.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.GoodsGift;

public interface ShoppingCartGoodsGiftDao extends BaseGenericDao<ShoppingCartGoodsGift> {

	/** 
	 * Description :  获取商品礼包列表
	 * Create Date: 2011-1-18上午10:13:34 by likg  Modified Date: 2011-1-18上午10:13:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsGift> getGoodsGiftList(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取商品礼包-购物车条目列表集合
	 * Create Date: 2011-1-18下午04:45:26 by likg  Modified Date: 2011-1-18下午04:45:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ShoppingCartGoodsGift> getShoppingCartGoodsGiftList(Map<String, Object> param) throws Exception;

}
