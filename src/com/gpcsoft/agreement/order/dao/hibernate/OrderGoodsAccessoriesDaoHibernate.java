package com.gpcsoft.agreement.order.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.agreement.order.dao.OrderGoodsAccessoriesDao;
import com.gpcsoft.agreement.order.domain.OrderGoodsAccessories;
import org.springframework.stereotype.Repository;

@Repository
public class OrderGoodsAccessoriesDaoHibernate extends BaseGenericDaoHibernate<OrderGoodsAccessories> implements OrderGoodsAccessoriesDao {

}
