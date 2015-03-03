package com.gpcsoft.agreement.cart.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.dao.ShoppingCartItemDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.manager.ShoppingCartManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

@Repository
public class ShoppingCartManagerImpl extends BaseManagerImpl<ShoppingCart> implements ShoppingCartManager {

	@Autowired(required=true)  @Qualifier("shoppingCartDaoHibernate")
	private ShoppingCartDao shoppingCartDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("shoppingCartItemDaoHibernate")
	private ShoppingCartItemDao shoppingCartItemDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("shoppingCartDaoJdbc")
	private ShoppingCartDao shoppingCartDaoJdbc;

	/** 
	 * Description : 我的购物车 
	 * Create Date: 2010-4-9下午04:53:30 by wangsw  Modified Date: 2010-4-9下午04:53:30 by yucy
	 * @param paramsMap 
	 * @return  
	 * @Exception   
	 */
	public List<ShoppingCartItem> findMyShoppingCar(Map<String, Object> paramsMap) {
		return shoppingCartDaoHibernate.findMyShoppingCar(paramsMap);
	}

	/** 
	 * Description :  删除购物车里的一个商品
	 * Create Date: 2010-4-14下午05:47:02 by wangsw  Modified Date: 2010-4-14下午05:47:02 by yucy
	 * @Exception   
	 */
	public void removeShoppingItem(ShoppingCartItem shoppingCartItem) {
		this.shoppingCartItemDaoHibernate.remove(shoppingCartItem.getObjId(), ShoppingCartItem.class);
		this.shoppingCartDaoHibernate.updateSortChatAndItemQytAndMoney(shoppingCartItem.getShoppingCart().getObjId());
	}

	/** 
	 * Description : 清空购物车
	 * Create Date: 2010-5-5上午09:54:34 by wangsw  Modified Date: 2010-5-5上午09:54:34 by yucy
	 * @Exception   
	 */
	public void removeAllByOrgInfo(Map<String, Object> paramsMap)throws Exception {
		this.shoppingCartDaoHibernate.removeAllByOrgInfo(paramsMap);
	}

	public List<GoodsClass> findGoodsClassForShowGoods(
			Map<String, Object> paramsMap) {
		return shoppingCartDaoJdbc.findGoodsClassForShowGoods(paramsMap);
	}

	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap) {
		return shoppingCartDaoJdbc.findBrandForShowGoods(paramsMap);
	}

}
