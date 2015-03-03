package com.gpcsoft.agreement.cart.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.cart.dao.ShoppingCartGoodsOptionDao;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class ShoppingCartGoodsOptionDaoHibernate extends BaseGenericDaoHibernate<ShoppingCartGoodsOption> implements ShoppingCartGoodsOptionDao {

}
