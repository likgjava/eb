package com.gpcsoft.agreement.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.manager.ShoppingCartGoodsOptionManager;
import com.gpcsoft.agreement.cart.service.ShoppingCartGoodsOptionService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ShoppingCartGoodsOptionServiceImpl extends BaseGenericServiceImpl<ShoppingCartGoodsOption> implements ShoppingCartGoodsOptionService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("shoppingCartGoodsOptionManagerImpl")
	private ShoppingCartGoodsOptionManager shoppingCartGoodsOptionManager;

}
