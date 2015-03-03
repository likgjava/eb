package com.gpcsoft.agreement.cart.manager.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartDao;
import com.gpcsoft.agreement.cart.dao.ShoppingCartItemDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.manager.ShoppingCartItemManager;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;

@Repository
public class ShoppingCartItemManagerImpl extends BaseManagerImpl<ShoppingCartItem> implements ShoppingCartItemManager {

	@Autowired(required=true) @Qualifier("shoppingCartItemDaoHibernate")
	private ShoppingCartItemDao shoppingCartItemDao;
	
	@Autowired(required=true) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDaoHibernate;
	
	@Autowired(required=true) @Qualifier("shoppingCartDaoHibernate")
	private ShoppingCartDao shoppingCartDaoHibernate;
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findSupplierByOrderMoney()
	 */
	public List<OrgInfo> findSupplierByOrderMoney() {
		return shoppingCartItemDao.findSupplierByOrderMoney();
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findGoodsClassForIndex()
	 */
	public List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell) {
		return shoppingCartItemDao.findGoodsClassForIndex(nameFirstSpell);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.cart.service.ShoppingCartItemService#findHotGoodsForIndex()
	 */
	public List<Goods> findHotGoodsForIndex() {
		return shoppingCartItemDao.findHotGoodsForIndex();
	}

	/** 
	 * Description :  添加商品到购物车 ShopingCartItem
	 * Create Date: 2010-4-22下午07:00:12 by wangsw  Modified Date: 2010-4-22下午07:00:12 by yucy
	 * @param   shoppingCartItem
	 * @throws Exception 
	 * @Exception   
	 */
	public ShoppingCartItem saveAppendGoodsToShoppingCart(ShoppingCartItem shopingCartItem) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgId", orgInfoDaoHibernate.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
		paramMap.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		 ShoppingCart shoppingCartTempTemp=shoppingCartDaoHibernate.findShoppingCartByOrgInfoId(paramMap);

		 if(shoppingCartTempTemp==null){//购物车不存在
			 shoppingCartTempTemp=new ShoppingCart();
			 //shoppingCartTempTemp.setBuyer(shopingCartItem.getShoppingCart().getBuyer()); modify by yucy
			 shoppingCartTempTemp.setBuyer(orgInfoDaoHibernate.getLastedOrgInfo(AuthenticationHelper.getCurrentUser().getEmp().getCompany().getObjId(), true));

			 shoppingCartTempTemp.setGoodsQty(shopingCartItem.getGoodsQty());
			 shoppingCartTempTemp.setGoodsTotal(shopingCartItem.getGoodsTotal());
			 shoppingCartTempTemp.setCartNo(SequenceNumberUtil.getShoppingCartSN());//购物车编号
			 shopingCartItem.setShoppingCart(shoppingCartTempTemp);
			 shoppingCartDaoHibernate.save(shoppingCartTempTemp);
			 shopingCartItem.setShoppingCart(shoppingCartDaoHibernate.save(shopingCartItem.getShoppingCart()));
		 }else{//购物车已存在
			 //this.save(shopingCartItem);
			 shoppingCartTempTemp.setGoodsQty(shoppingCartTempTemp.getGoodsQty().add(shopingCartItem.getGoodsQty()));
			 shoppingCartTempTemp.setGoodsTotal(shoppingCartTempTemp.getGoodsTotal().add(shopingCartItem.getGoodsTotal()));
			 shopingCartItem.setShoppingCart(shoppingCartTempTemp);
		 }
		 
		 //比较购物车中的购物车条目
		 shopingCartItem = comparedShoppingCartItem(shopingCartItem);
		 
		 //有商品礼包，则更改购物车条目中商品价格（加上商品礼包的价格）
		 if(shopingCartItem.getCartGoodsGifts()!=null && shopingCartItem.getCartGoodsGifts().size()>0){
			 for(ShoppingCartGoodsGift shoppingCartGoodsGift : shopingCartItem.getCartGoodsGifts()) {
				 if(!StringUtils.hasLength(shopingCartItem.getObjId())){
					 shopingCartItem.setAgreePrice(shopingCartItem.getAgreePrice().add(shoppingCartGoodsGift.getGiftPrice()));
				 }
				 shopingCartItem.setGoodsTotal(shopingCartItem.getGoodsTotal().add(shoppingCartGoodsGift.getGiftPrice()));
			 }
		 }
		 //有商品零配件，则更改购物车条目中商品价格（加上商品零配件的价格）
		 if(!StringUtils.hasLength(shopingCartItem.getObjId()) && shopingCartItem.getCartGoodsAccessories()!=null && shopingCartItem.getCartGoodsAccessories().size()>0){
			 for(ShoppingCartGoodsAccessories shoppingCartGoodsAccess : shopingCartItem.getCartGoodsAccessories()) {
				 if(!StringUtils.hasLength(shopingCartItem.getObjId())){
					 shopingCartItem.setAgreePrice(shopingCartItem.getAgreePrice().add(shoppingCartGoodsAccess.getAccessPrice()));
				 }
				 shopingCartItem.setGoodsTotal(shopingCartItem.getGoodsTotal().add(shoppingCartGoodsAccess.getAccessPrice()));
			 }
		 }
		 this.save(shopingCartItem);
		 
		 return shopingCartItem;
	}
	
	/** 
	 * Description :  比较是否已经存在相同的购物车条目
	 * Create Date: 2010-9-27上午12:48:04 by yucy  Modified Date: 2010-9-27上午12:48:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ShoppingCartItem comparedShoppingCartItem(ShoppingCartItem shopingCartItem)throws Exception{
		Map<String, Object> param =new  HashMap<String, Object>();
		param.put("orgInfoId", orgInfoDaoHibernate.getLastedOrgInfo(AuthenticationHelper.getCurrentUser().getEmp().getCompany().getObjId(), true).getObjId());
		param.put("userId", AuthenticationHelper.getCurrentUser().getObjId());
		List<ShoppingCartItem> shoppingCartItemList = shoppingCartDaoHibernate.findMyShoppingCar(param);
		for (ShoppingCartItem sc : shoppingCartItemList) {
			if(!shopingCartItem.getGoods().getObjId().equals(sc.getGoods().getObjId())){
				continue;
			}
			if((shopingCartItem.getSupplier()==null||sc.getSupplier()==null)&&(!(shopingCartItem.getSupplier()==null&&sc.getSupplier()==null))){
				continue;
			}
			if((shopingCartItem.getSupplier()!=null&&sc.getSupplier()!=null)&&(!shopingCartItem.getSupplier().getObjId().equals(sc.getSupplier().getObjId()))){
				continue;
			}
			//有商品选配
			if(shopingCartItem.getCartGoodsOptions()!=null && shopingCartItem.getCartGoodsOptions().size()>0){
				if(!comparedTwoList(sc.getCartGoodsOptions(),shopingCartItem.getCartGoodsOptions())){
					continue;
				}else {
					sc.setGoodsQty(sc.getGoodsQty().add(shopingCartItem.getGoodsQty()));
					sc.setGoodsTotal(sc.getGoodsTotal().add(shopingCartItem.getGoodsTotal()));
					for (ShoppingCartGoodsOption shoppingCartGoodsOption : sc.getCartGoodsOptions()) {
						shoppingCartGoodsOption.setOptQty(sc.getGoodsQty());
					}
					return sc;
				}
			}
			//有商品礼包
			else if(shopingCartItem.getCartGoodsGifts()!=null && shopingCartItem.getCartGoodsGifts().size()>0){
				if(!comparedGoodsGiftList(sc.getCartGoodsGifts(), shopingCartItem.getCartGoodsGifts())){
					continue;
				}else{
					sc.setGoodsQty(sc.getGoodsQty().add(shopingCartItem.getGoodsQty()));
					sc.setGoodsTotal(sc.getGoodsTotal().add(shopingCartItem.getGoodsTotal()));
					return sc;
				}
			}
			//有商品零配件
			else if(shopingCartItem.getCartGoodsAccessories()!=null && shopingCartItem.getCartGoodsAccessories().size()>0){
				if(!comparedGoodsAccessList(sc.getCartGoodsAccessories(), shopingCartItem.getCartGoodsAccessories())){
					continue;
				}else{
					sc.setGoodsQty(sc.getGoodsQty().add(shopingCartItem.getGoodsQty()));
					sc.setGoodsTotal(sc.getGoodsTotal().add(shopingCartItem.getGoodsTotal()));
					return sc;
				}
			}
			//无商品选配，无商品礼包,无零配件
			else{
				if(sc.getCartGoodsOptions().size()>0 || sc.getCartGoodsGifts().size()>0 || sc.getCartGoodsAccessories().size()>0){
					return shopingCartItem;
				}
				sc.setGoodsQty(sc.getGoodsQty().add(shopingCartItem.getGoodsQty()));
				sc.setGoodsTotal(sc.getGoodsTotal().add(shopingCartItem.getGoodsTotal()));
				return sc;
			}
			
		}
		return shopingCartItem;
	}
	
	/** 
	 * Description :  比较两个字符串集合包含的字符串是否相同
	 * Create Date: 2010-9-27上午09:32:14 by yucy  Modified Date: 2010-9-27上午09:32:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean comparedTwoList(Set<ShoppingCartGoodsOption> set1,Set<ShoppingCartGoodsOption> set2){
		Set<ShoppingCartGoodsOption> list1= new HashSet<ShoppingCartGoodsOption>(set1); 
		Set<ShoppingCartGoodsOption> list2= new HashSet<ShoppingCartGoodsOption>(set2); 
		Iterator<ShoppingCartGoodsOption> i1 =  list1.iterator();
		Iterator<ShoppingCartGoodsOption> i2 =  list2.iterator();
		while (i1.hasNext()){
			ShoppingCartGoodsOption o1= i1.next();
			while (i2.hasNext()){
				ShoppingCartGoodsOption o2= i2.next();
				if (o1.getOption().getObjId().equals(o2.getOption().getObjId())) {
					i1.remove();
					i2.remove();
				}
		    }
			i2 =  list2.iterator();
	    }
		return list1.size()+list2.size()==0?true:false;
	}
	
	/** 
	 * Description :  比较ShoppingCartGoodsGift集合中商品礼包GoodsGift是否相同
	 * Create Date: 2011-1-20上午10:06:47 by likg  Modified Date: 2011-1-20上午10:06:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private Boolean comparedGoodsGiftList(Set<ShoppingCartGoodsGift> set1,Set<ShoppingCartGoodsGift> set2){
		Set<ShoppingCartGoodsGift> list1= new HashSet<ShoppingCartGoodsGift>(set1); 
		Set<ShoppingCartGoodsGift> list2= new HashSet<ShoppingCartGoodsGift>(set2); 
		
		if(list1.size() != list2.size()){
			return false;
		}
		
		Iterator<ShoppingCartGoodsGift> i1 =  list1.iterator();
		Iterator<ShoppingCartGoodsGift> i2 =  list2.iterator();
		while (i1.hasNext()){
			ShoppingCartGoodsGift o1= i1.next();
			while (i2.hasNext()){
				ShoppingCartGoodsGift o2= i2.next();
				if (o1.getGoodsGift().getObjId().equals(o2.getGoodsGift().getObjId())) {
					i1.remove();
					i2.remove();
				}
			}
			i2 =  list2.iterator();
		}
		return list1.size()+list2.size()==0?true:false;
	}
	
	/** 
	 * Description :  比较ShoppingCartGoodsGift集合中商品礼包GoodsGift是否相同
	 * Create Date: 2011-1-20上午10:06:47 by likg  Modified Date: 2011-1-20上午10:06:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private Boolean comparedGoodsAccessList(Set<ShoppingCartGoodsAccessories> set1,Set<ShoppingCartGoodsAccessories> set2){
		Set<ShoppingCartGoodsAccessories> list1= new HashSet<ShoppingCartGoodsAccessories>(set1); 
		Set<ShoppingCartGoodsAccessories> list2= new HashSet<ShoppingCartGoodsAccessories>(set2); 
		
		if(list1.size() != list2.size()){
			return false;
		}
		
		Iterator<ShoppingCartGoodsAccessories> i1 =  list1.iterator();
		Iterator<ShoppingCartGoodsAccessories> i2 =  list2.iterator();
		while (i1.hasNext()){
			ShoppingCartGoodsAccessories o1= i1.next();
			while (i2.hasNext()){
				ShoppingCartGoodsAccessories o2= i2.next();
				if (o1.getGoodsAccess().getObjId().equals(o2.getGoodsAccess().getObjId())) {
					i1.remove();
					i2.remove();
				}
			}
			i2 =  list2.iterator();
		}
		return list1.size()+list2.size()==0?true:false;
	}

	/** 
	 * Description : 改变购物车和购物车item的数量、金额 
	 * Create Date: 2010-4-12下午02:02:33 by wangsw  Modified Date: 2010-4-12下午02:02:33 by wangsw
	 * @param   购物车ITEM
	 * @throws Exception 
	 * @Exception   
	 */
	public void updateGoodsChangeQtyAndMoney(ShoppingCartItem cartItem) throws Exception {
		ShoppingCartItem shoppingCartItem=this.get(cartItem.getObjId());
		BeanUtils.copyPropertiesFilterEmptyNew(shoppingCartItem, cartItem);
		for (ShoppingCartGoodsOption  shoppingCartGoodsOption  : shoppingCartItem.getCartGoodsOptions()) {
			shoppingCartGoodsOption.setOptQty(cartItem.getGoodsQty());
		}
		this.save(shoppingCartItem);
		ShoppingCart shoppingCart=(ShoppingCart) this.get(cartItem.getShoppingCart().getObjId(), ShoppingCart.class);
		BeanUtils.copyPropertiesFilterEmptyNew(shoppingCart, cartItem.getShoppingCart());
		this.save(shoppingCart, ShoppingCart.class);
	}

}
