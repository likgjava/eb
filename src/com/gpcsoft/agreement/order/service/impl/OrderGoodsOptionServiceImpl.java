package com.gpcsoft.agreement.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.agreement.order.manager.OrderGoodsOptionManager;
import com.gpcsoft.agreement.order.service.OrderGoodsOptionService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class OrderGoodsOptionServiceImpl extends BaseGenericServiceImpl<OrderGoodsOption> implements OrderGoodsOptionService {

	@Autowired(required=true)  @Qualifier("orderGoodsOptionManagerImpl")
	private OrderGoodsOptionManager orderGoodsOptionManager;

	public void updateGoodsChangeQtyAndMoney(OrderGoodsOption option) {
		orderGoodsOptionManager.updateGoodsChangeQtyAndMoney(option);
	}

	
}
