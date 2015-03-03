package com.gpcsoft.agreement.cart.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface ShoppingCartManager extends BaseManager<ShoppingCart> {
	
	/** 
	 * Description :  获取分类下的所有品牌
	 * Create Date: 2010-5-20上午10:38:47 by wangcl  Modified Date: 2010-5-20上午10:38:47 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap);
	/** 
	 * Description : 我的购物车 
	 * Create Date: 2010-4-9下午04:53:30 by wangsw  Modified Date: 2010-4-9下午04:53:30 by yucy
	 * @param paramsMap 
	 * @return  
	 * @Exception   
	 */
	List<ShoppingCartItem> findMyShoppingCar(Map<String, Object> paramsMap);

	/** 
	 * Description :  删除购物车里的一个商品, 删除商品时级联删除配件，并且重新算购物车的数量和金额
	 * Create Date: 2010-4-14下午05:48:34 by wangsw  Modified Date: 2010-4-14下午05:48:34 by wangsw
	 * @param   购物车Item 有ID
	 * @Exception   
	 */
	void removeShoppingItem(ShoppingCartItem shoppingCartItem);

	/** 
	 * Description : 清空购物车 
	 * Create Date: 2010-5-5上午09:54:57 by wangsw  Modified Date: 2010-5-5上午09:54:57 by yucy
	 * @param  机构 
	 * @Exception   
	 */
	void removeAllByOrgInfo(Map<String, Object> paramsMap)throws Exception;
	
	public List<GoodsClass> findGoodsClassForShowGoods(Map<String, Object> paramsMap) ;
	
}
