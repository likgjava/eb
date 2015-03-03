package com.gpcsoft.agreement.order.dao.hibernate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.ProcurementtaskDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;

@Repository
public class ProcurementtaskDaoHibernate extends BaseGenericDaoHibernate<Procurementtask> implements ProcurementtaskDao {

	@SuppressWarnings("unchecked")
	public Page<Order> listOrderBySql(Page page, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
