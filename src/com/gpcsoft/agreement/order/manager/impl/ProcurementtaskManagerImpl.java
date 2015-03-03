package com.gpcsoft.agreement.order.manager.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.ProcurementtaskDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.agreement.order.manager.ProcurementtaskManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;

@Repository
public class ProcurementtaskManagerImpl extends BaseManagerImpl<Procurementtask> implements ProcurementtaskManager {

	@Autowired(required=true)  @Qualifier("procurementtaskDaoJDBC")
	private ProcurementtaskDao procurementtaskDaoJDBC;

	@SuppressWarnings("unchecked")
	public Page<Order> listOrderBySql(Page page ,HttpServletRequest request) {
		return procurementtaskDaoJDBC.listOrderBySql(page, request);
	}
}
