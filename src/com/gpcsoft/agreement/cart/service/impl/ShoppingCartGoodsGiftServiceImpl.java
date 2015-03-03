package com.gpcsoft.agreement.cart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.cart.dao.ShoppingCartGoodsGiftDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.agreement.cart.service.ShoppingCartGoodsGiftService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ShoppingCartGoodsGiftServiceImpl extends BaseGenericServiceImpl<ShoppingCartGoodsGift> implements ShoppingCartGoodsGiftService {

	@Autowired(required=true)  @Qualifier("shoppingCartGoodsGiftDaoHibernate")
	private ShoppingCartGoodsGiftDao shoppingCartGoodsGiftDaoHibernate;
	
	/** 
	 * Description :  获取商品礼包-购物车条目列表集合
	 * Create Date: 2011-1-18下午04:45:26 by likg  Modified Date: 2011-1-18下午04:45:26 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ShoppingCartGoodsGift> getShoppingCartGoodsGiftList(Map<String, Object> param) throws Exception {
		return shoppingCartGoodsGiftDaoHibernate.getShoppingCartGoodsGiftList(param);
	}

}
