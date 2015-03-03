package com.gpcsoft.agreement.order.dao;

import javax.servlet.http.HttpServletRequest;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;

public interface ProcurementtaskDao extends BaseGenericDao<Procurementtask> {

	Page<Order> listOrderBySql(Page<Order> page, HttpServletRequest request);
}
