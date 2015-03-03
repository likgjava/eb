package com.gpcsoft.agreement.cart.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface ShoppingCartService extends BaseGenericService<ShoppingCart> {

	/** 
	 * Description : 我的购物车 
	 * Create Date: 2010-4-9下午04:53:30 by wangsw  Modified Date: 2010-4-9下午04:53:30 by wangsw
	 * @param paramsMap 
	 * @return  
	 * @Exception   
	 */
	List<ShoppingCartItem> findMyShoppingCar(Map<String, Object> paramsMap);
	
	
	/** 
	 * Description :  根据购物车ITEM的id和orginfoid获得购物车条目
	 * Create Date: 2010-10-9上午09:50:30 by sunl  Modified Date: 2010-10-9上午09:50:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ShoppingCartItem> getShoppingCarItem(Map<String, Object> paramsMap);

	/** 
	 * Description :  删除购物车里的一个商品, 删除商品时级联删除配件，并且重新算购物车的数量和金额
	 * Create Date: 2010-4-14下午05:48:34 by wangsw  Modified Date: 2010-4-14下午05:48:34 by wangsw
	 * @param   购物车Item 有ID
	 * @Exception   
	 */
	void removeShoppingItem(ShoppingCartItem shoppingCartItem) throws Exception;

	/** 
	 * Description : 清空购物车 
	 * Create Date: 2010-5-5上午09:54:57 by wangsw  Modified Date: 2010-5-5上午09:54:57 by wangsw
	 * @param  机构 
	 * @Exception   
	 */
	void removeAllByOrgInfo(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  查找品牌
	 * Create Date: 2010-5-13下午04:52:56 by wangsw  Modified Date: 2010-5-13下午04:52:56 by wangsw
	 * @param   paramsMap
	 * @return  品牌
	 * @Exception   
	 */
	List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap);

	/** 
	 * Description :  根据品目ID过滤商品分类
	 * Create Date: 2010-5-13下午05:51:28 by wangsw  Modified Date: 2010-5-13下午05:51:28 by wangsw
	 * @param   品目ID
	 * @return  商品分类
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForShowGoods(Map<String, Object> paramsMap);

}
