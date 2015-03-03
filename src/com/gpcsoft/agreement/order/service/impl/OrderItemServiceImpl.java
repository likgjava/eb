package com.gpcsoft.agreement.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.dao.OrderItemDao;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.manager.OrderItemManager;
import com.gpcsoft.agreement.order.service.OrderItemService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class OrderItemServiceImpl extends BaseGenericServiceImpl<OrderItem> implements OrderItemService {
	
	@Autowired(required=true)  @Qualifier("orderItemManagerImpl")
	private OrderItemManager orderItemManager;
	
	@Autowired(required=true)  @Qualifier("orderItemDaoHibernate")
	private OrderItemDao orderItemDaoHibernate;

	public void updateGoodsChangeQtyAndMoney(OrderItem orderItem) {
		orderItemManager.updateGoodsChangeQtyAndMoney(orderItem);
	}

	/**
	 * 获得订单商品配件信息
	 */
	public List<OrderItem> getOrderItemGoodsOption(Map<String, Object> paramsMap) {
		return orderItemManager.getOrderItemGoodsOption(paramsMap);
	}
	
	
	/** 
	 * Description :  根据合同id获得订单的明细，并获得商品的选配
	 * Create Date: 2010-12-14下午01:39:56 by liangxj  Modified Date: 2010-12-14下午01:39:56 by liangxj
	 * @param   contractId 合同Id
	 * @return  
	 * @Exception   
	 */
	public List<OrderItem> getOrderItemByContract(String contractId) {
		return orderItemDaoHibernate.getOrderItemByContract(contractId);
	}
	
	/** 
	 * Description :  重写方法 删除时同步订单中的品目名称
	 * Create Date: 2011-3-1下午09:32:12 by yucy  Modified Date: 2011-3-1下午09:32:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	protected void onAfterRemove(String objId) {
		try {
			orderItemDaoHibernate.updateCategoryNames(objId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
