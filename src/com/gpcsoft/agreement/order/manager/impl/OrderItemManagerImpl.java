package com.gpcsoft.agreement.order.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderItemDao;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.manager.OrderItemManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrderItemManagerImpl extends BaseManagerImpl<OrderItem> implements OrderItemManager {

	@Autowired(required=true)  @Qualifier("orderItemDaoHibernate")
	private OrderItemDao orderItemDaoHibernate;
	
	public void updateGoodsChangeQtyAndMoney(OrderItem orderItem) {
		orderItemDaoHibernate.updateChangeQtyAndMoney(orderItem);
	}

	/**
	 * 获得订单商品配件信息
	 */
	public List<OrderItem> getOrderItemGoodsOption(Map<String, Object> paramsMap) {
		return orderItemDaoHibernate.getOrderItemGoodsOption(paramsMap);
	}
}
