package com.gpcsoft.agreement.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.manager.ShoppingCartItemManager;
import com.gpcsoft.agreement.cart.service.ShoppingCartItemService;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.manager.ProtaskItemManager;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.manager.GoodsManager;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

/** 
  *  Comments: <strong>ShoppingCartItemServiceImpl</strong>            		
  *	 <br/>	购物车挑选商品业务组件															
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-16 下午06:28:52 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-16 下午06:28:52 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Service 
public class ShoppingCartItemServiceImpl extends BaseGenericServiceImpl<ShoppingCartItem> implements ShoppingCartItemService {
 
	@Autowired(required=true) @Qualifier("shoppingCartItemManagerImpl")
	private ShoppingCartItemManager shoppingCartItemManager;
	
	@Autowired(required=true) @Qualifier("protaskItemManagerImpl")
	private ProtaskItemManager protaskItemManager;
	
	@Autowired(required=true) @Qualifier("goodsManagerImpl")
	private GoodsManager goodsManager;
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findSupplierByOrderMoney()
	 */
	public List<OrgInfo> findSupplierByOrderMoney() {
		return shoppingCartItemManager.findSupplierByOrderMoney();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findGoodsClassForIndex()
	 */
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {
		return shoppingCartItemManager.findGoodsClassForIndex(nameFirstSpell);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findHotGoodsForIndex()
	 */
	public List<Goods> findHotGoodsForIndex() {
		return shoppingCartItemManager.findHotGoodsForIndex();
	}

	/** 
	 * Description :  添加商品到购物车 ShopingCartItem
	 * Create Date: 2010-4-22下午07:00:12 by wangsw  Modified Date: 2010-4-22下午07:00:12 by yucy
	 * @param   shoppingCartItem
	 * @Exception   
	 */
	public void saveAppendGoodsToShoppingCart(ShoppingCartItem shopingCartItem,String protaskItemObjId) throws Exception {
		//相关任务书不为空的时候，判断任务书的采购品目，剩余数量和剩余金额是否匹配，如果匹配，将任务书的id存在购物车中
		if(protaskItemObjId != null && !protaskItemObjId.equals("")){
			ProtaskItem protaskItem = protaskItemManager.get(protaskItemObjId);
			Goods goods = goodsManager.get(shopingCartItem.getGoods().getObjId());
			//判断品目是否匹配
			if(protaskItem.getPurCategory().getObjId().equals(goods.getPurCategory().getObjId()))
				//判断剩余数量是否满足
				if(protaskItem.getFinQty().compareTo(shopingCartItem.getGoodsQty()) >= 0)
					//判断剩余金额是否满足
					if(protaskItem.getFinTotal().compareTo(shopingCartItem.getGoodsTotal()) >= 0){
						shopingCartItem.setProtaskItem(protaskItem);
					}
		}
		shoppingCartItemManager.saveAppendGoodsToShoppingCart(shopingCartItem);
	}

	/** 
	 * Description : 改变购物车和购物车item的数量、金额 
	 * Create Date: 2010-4-12下午02:02:33 by wangsw  Modified Date: 2010-4-12下午02:02:33 by wangsw
	 * @param   购物车ITEM
	 * @throws Exception 
	 * @Exception   
	 */
	public void updateGoodsChangeQtyAndMoney(ShoppingCartItem cartItem) throws Exception {
		shoppingCartItemManager.updateGoodsChangeQtyAndMoney(cartItem);
	}
	
}
