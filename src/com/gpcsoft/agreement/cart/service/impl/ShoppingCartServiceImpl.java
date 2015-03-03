package com.gpcsoft.agreement.cart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.manager.ShoppingCartManager;
import com.gpcsoft.agreement.cart.service.ShoppingCartService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
  *  Comments: <strong>ShoppingCartServJiceImpl</strong>            		
  *	 <br/>	购物车业务组件														
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-12 下午03:07:48 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-12 下午03:07:48 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Service 
public class ShoppingCartServiceImpl extends BaseGenericServiceImpl<ShoppingCart> implements ShoppingCartService {
	
	@Autowired(required=true)  @Qualifier("shoppingCartManagerImpl")
	private ShoppingCartManager shoppingCartManager;
	
	@Autowired(required=true)  @Qualifier("shoppingCartDaoHibernate")
	private ShoppingCartDao shoppingCartDaoHibernate;
	
	/** 
	 * Description : 我的购物车 
	 * Create Date: 2010-4-9下午04:53:30 by wangsw  Modified Date: 2010-4-9下午04:53:30 by yucy
	 * @param paramsMap 
	 * @return  
	 * @Exception   
	 */
	public List<ShoppingCartItem> findMyShoppingCar(Map<String, Object> paramsMap) {
		return shoppingCartManager.findMyShoppingCar(paramsMap);
	}
	
	/** 
	 * Description :  根据购物车ITEM的id和orginfoid获得购物车条目
	 * Create Date: 2010-10-9上午09:50:30 by sunl  Modified Date: 2010-10-9上午09:50:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ShoppingCartItem> getShoppingCarItem(Map<String, Object> paramsMap) {
		return shoppingCartDaoHibernate.getShoppingCarItem(paramsMap);
	}

	/** 
	 * Description :  删除购物车里的一个商品
	 * Create Date: 2010-4-14下午05:47:02 by wangsw  Modified Date: 2010-4-14下午05:47:02 by yucy
	 * @Exception   
	 */
	public void removeShoppingItem(ShoppingCartItem shoppingCartItem) throws Exception{
		this.shoppingCartManager.removeShoppingItem(shoppingCartItem);
	}

	/** 
	 * Description : 清空购物车
	 * Create Date: 2010-5-5上午09:54:34 by wangsw  Modified Date: 2010-5-5上午09:54:34 by yucy
	 * @Exception   
	 */
	public void removeAllByOrgInfo(Map<String, Object> paramsMap) throws Exception{
		this.shoppingCartManager.removeAllByOrgInfo(paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartService#findBrandForShowGoods(java.util.Map)
	 */
	public List<GoodsBrand> findBrandForShowGoods(Map<String, Object> paramsMap) {
//		String categoryId=(String) paramsMap.get("categoryId");
//		String goodsClassId=(String) paramsMap.get("goodsClassId");
//		List<GoodsBrand> brandList=new ArrayList<GoodsBrand>();
//		List<String> paramsList=new ArrayList<String>();
//		StringBuilder hql=new StringBuilder();
//		if(categoryId!=null){
//			hql.append(" select b from GoodsBrand b inner join b.goodsClassSet as gc inner join gc.purCategorySet as cat where 1=1 ");
//			hql.append(" and cat.objId=? ");
//			paramsList.add(categoryId);
//		}
//		if(goodsClassId!=null){
//			hql.append(" select b from GoodsBrand b inner join b.goodsClassSet as gc where 1=1 ");
//			hql.append(" and gc.objId=? ");
//			paramsList.add(goodsClassId);
//		}
//		brandList=goodsBrandService.findByHql(hql.toString(), paramsList.toArray());
		
		return shoppingCartManager.findBrandForShowGoods(paramsMap);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartService#findGoodsClassForShowGoods(java.util.Map)
	 */
	public List<GoodsClass> findGoodsClassForShowGoods(Map<String, Object> paramsMap) {
		List<GoodsClass> goodsClassList=new ArrayList<GoodsClass>();
		goodsClassList=shoppingCartManager.findGoodsClassForShowGoods(paramsMap);
		return goodsClassList;
	}
 
}
