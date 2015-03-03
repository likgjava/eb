package com.gpcsoft.agreement.cart.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
  *  Comments: <strong>ShoppingCartDao</strong>            		
  *	 <br/>	购物车DAO	        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-12 下午03:09:21 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-12 下午03:09:21 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public interface ShoppingCartDao extends BaseGenericDao<ShoppingCart> {
	
	/** 
	 * Description :  获取分类下的所有品牌
	 * Create Date: 2010-5-20上午10:29:29 by wangcl  Modified Date: 2010-5-20上午10:29:29 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap);

	/** 
	 * Description : 我的购物车  
	 * Create Date: 2010-4-9下午06:35:13 by wangsw  Modified Date: 2010-4-9下午06:35:13 by wangsw
	 * @param   
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
	 * Description :  改变购物车的一个商品item的数量、金额
	 * Create Date: 2010-4-12下午03:08:44 by wangsw  Modified Date: 2010-4-12下午03:08:44 by wangsw
	 * @param   
	 * @Exception   
	 */
	void updateChangeQtyAndMoney(ShoppingCartItem cartItem);

	/** 
	 * Description :  根据购物车ID调整购物车、购物车Item的数量和金额
	 * Create Date: 2010-4-14下午05:58:31 by wangsw  Modified Date: 2010-4-14下午05:58:31 by yucy
	 * @param  购物车ID 
	 * @return  
	 * @Exception   
	 */
	void updateSortChatAndItemQytAndMoney(String objId);

	/** 
	 * Description : 根据user查找购物车 
	 * Create Date: 2010-4-26下午12:14:42 by wangsw  Modified Date: 2010-4-26下午12:14:42 by yucy
	 * @param   orgInfo
	 * @return  购物车
	 * @Exception   
	 */
	ShoppingCart findShoppingCartByOrgInfoId(Map<String, Object> paramsMap)throws Exception;

	/** 
	 * Description : 清空购物车
	 * Create Date: 2010-5-5上午09:56:06 by wangsw  Modified Date: 2010-5-5上午09:56:06 by wangsw
	 * @param   机构
	 * @Exception   
	 */
	void removeAllByOrgInfo(Map<String, Object> paramsMap)throws Exception;

	/** 
	 * Description : 查找指定分类Id及其下面的商品分类
	 * Create Date: 2010-5-17上午09:22:29 by wangsw  Modified Date: 2010-5-17上午09:22:29 by wangsw
	 * @param   id
	 * @return  List<GoodsClass>
	 * @Exception   
	 */
	List<GoodsClass> getGoodsClassAndChild(final String id);
	
	/** 
	 * Description :  service里有了
	 * Create Date: 2010-5-17下午01:40:21 by wangsw  Modified Date: 2010-5-17下午01:40:21 by wangsw
	 * @param	paramsMap   
	 * @return	List<GoodsClass>  
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForShowGoods(Map<String, Object> paramsMap);

}
