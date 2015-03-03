package com.gpcsoft.agreement.order.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.service.BaseGenericService;

public interface OrderItemService extends BaseGenericService<OrderItem> {
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
	
	
	/** 
	 * Description :  根据合同id获得订单的明细，并获得商品的选配
	 * Create Date: 2010-12-14下午01:39:56 by liangxj  Modified Date: 2010-12-14下午01:39:56 by liangxj
	 * @param   contractId 合同Id
	 * @return  
	 * @Exception   
	 */
	public List<OrderItem> getOrderItemByContract(String contractId);
}
