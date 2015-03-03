package com.gpcsoft.agreement.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.agreement.order.manager.OrderProtaskManager;
import com.gpcsoft.agreement.order.service.OrderProtaskService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class OrderProtaskServiceImpl extends BaseGenericServiceImpl<OrderProtask> implements OrderProtaskService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("orderProtaskManagerImpl")
	private OrderProtaskManager orderProtaskManager;

}
