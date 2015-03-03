package com.gpcsoft.agreement.order.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderGoodsOptionDao;
import com.gpcsoft.agreement.order.dao.OrderItemDao;
import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.agreement.order.manager.OrderGoodsOptionManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrderGoodsOptionManagerImpl extends BaseManagerImpl<OrderGoodsOption> implements OrderGoodsOptionManager {

	@Autowired(required=true)  @Qualifier("orderItemDaoHibernate")
	private OrderItemDao orderItemDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("orderGoodsOptionDaoHibernate")
	private OrderGoodsOptionDao orderGoodsOptionDaoHibernate;

	public void updateGoodsChangeQtyAndMoney(OrderGoodsOption option) {
		orderItemDaoHibernate.updateChangeQtyAndMoney(option.getOrderItem());
		orderGoodsOptionDaoHibernate.updateChangeQtyAndMoney(option);
	}
}
