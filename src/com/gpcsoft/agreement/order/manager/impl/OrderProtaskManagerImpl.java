package com.gpcsoft.agreement.order.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderProtaskDao;
import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.agreement.order.manager.OrderProtaskManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrderProtaskManagerImpl extends BaseManagerImpl<OrderProtask> implements OrderProtaskManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("orderProtaskDaoHibernate")
	private OrderProtaskDao orderProtaskDaoHibernate;

}
