package com.gpcsoft.agreement.order.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.manager.BaseManager;

public interface OrderItemManager extends BaseManager<OrderItem> {
	/** 
	 * Description : 改变订单item的数量、金额 
	 * Create Date: 2010-4-12下午02:02:33 by wangsw  Modified Date: 2010-4-12下午02:02:33 by wangsw
	 * @param   购物车ITEM
	 * @Exception   
	 */
	void updateGoodsChangeQtyAndMoney(OrderItem orderItem);
	
	/**
	 * 获得订单商品配件信息
	 */
	public List<OrderItem> getOrderItemGoodsOption(Map<String, Object> paramsMap);
}
