package com.gpcsoft.agreement.cart.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartGoodsOptionDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.manager.ShoppingCartGoodsOptionManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class ShoppingCartGoodsOptionManagerImpl extends BaseManagerImpl<ShoppingCartGoodsOption> implements ShoppingCartGoodsOptionManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("shoppingCartGoodsOptionDaoHibernate")
	private ShoppingCartGoodsOptionDao shoppingCartGoodsOptionDaoHibernate;

}
