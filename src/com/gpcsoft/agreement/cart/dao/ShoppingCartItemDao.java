package com.gpcsoft.agreement.cart.dao;

import java.util.List;

import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface ShoppingCartItemDao extends BaseGenericDao<ShoppingCartItem> {

	/** 
	 * Description :  改变购物车的数量、金额
	 * Create Date: 2010-4-12下午03:17:17 by wangsw  Modified Date: 2010-4-12下午03:17:17 by wangsw
	 * @Exception   
	 */
	void updateChangeQtyAndMoney(ShoppingCartItem cartItem);

	/** 
	 * Description :  首页需要的供应商销售排行 
	 * Create Date: 2010-4-17上午12:00:44 by wangsw  Modified Date: 2010-4-17上午12:00:44 by wangsw
	 * @return  首页前10名销售额的供应商排名
	 * @Exception   
	 */
	List<OrgInfo> findSupplierByOrderMoney();

	/** 
	 * Description :  首页需要的商品分类
	 * Create Date: 2010-4-17下午07:00:31 by wangsw  Modified Date: 2010-4-17下午07:00:31 by wangsw
	 * @param  分类名称首字母
	 * @return  3级商品分类
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell);

	/** 
	 * Description : 首页需要的热门商品 
	 * Create Date: 2010-4-19上午11:33:13 by wangsw  Modified Date: 2010-4-19上午11:33:13 by wangsw
	 * @return  热门商品
	 * @Exception   
	 */
	List<Goods> findHotGoodsForIndex();

	/** 
	 * Description :  取得ShoppingCartItemBy Ids（结果可能多条）
	 * Create Date: 2010-10-28上午11:33:39 by yucy  Modified Date: 2010-10-28上午11:33:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ShoppingCartItem> getShoppingCartItemByIds(Object[] ids) throws Exception;

}
