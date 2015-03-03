package com.gpcsoft.agreement.order.manager;

import javax.servlet.http.HttpServletRequest;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;

public interface ProcurementtaskManager extends BaseManager<Procurementtask> {
	@SuppressWarnings("unchecked")
	Page<Order> listOrderBySql(Page page, HttpServletRequest request);
}
