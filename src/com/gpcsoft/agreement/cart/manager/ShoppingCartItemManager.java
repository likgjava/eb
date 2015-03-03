package com.gpcsoft.agreement.cart.manager;

import java.util.List;

import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface ShoppingCartItemManager extends BaseManager<ShoppingCartItem> {

	/** 
	 * Description :  首页需要的供应商销售排行 
	 * Create Date: 2010-4-16下午11:59:38 by wangsw  Modified Date: 2010-4-16下午11:59:38 by wangsw
	 * @return  前10家供应商的销售排行
	 * @Exception   
	 */
	List<OrgInfo> findSupplierByOrderMoney();

	/** 
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell);

	/** 
	 * Description :  首页需要的热门商品
	 * Create Date: 2010-4-19上午11:30:59 by wangsw  Modified Date: 2010-4-19上午11:30:59 by wangsw
	 * @return  热门商品
	 * @Exception   
	 */
	List<Goods> findHotGoodsForIndex();

	/** 
	 * Description :  添加商品到购物车 ShopingCartItem
	 * Create Date: 2010-4-22下午07:00:12 by wangsw  Modified Date: 2010-4-22下午07:00:12 by yucy
	 * @param   shoppingCartItem
	 * @Exception   
	 */
	ShoppingCartItem saveAppendGoodsToShoppingCart(ShoppingCartItem shoppingCartItem) throws Exception;

	/** 
	 * Description : 改变购物车和购物车item的数量、金额 
	 * Create Date: 2010-4-12下午02:02:33 by wangsw  Modified Date: 2010-4-12下午02:02:33 by wangsw
	 * @param   购物车ITEM
	 * @throws Exception 
	 * @Exception   
	 */
	void updateGoodsChangeQtyAndMoney(ShoppingCartItem cartItem) throws Exception;

}
