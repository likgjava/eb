package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderProtaskDao;
import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrderProtaskDaoHibernate extends BaseGenericDaoHibernate<OrderProtask> implements OrderProtaskDao {

	
	/**
	 * 签订合同时，将同步修改任务书条目的已完成数量和金额
	 */
	@SuppressWarnings("unchecked")
	public void updateProtaskByContractForSign(final String contractObjId,final String operator) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				StringBuffer hql = new StringBuffer("from OrderProtask op where op.orderItem.order.contract.objId=:contractObjId");
				
				Query query = session.createQuery(hql.toString());
				query.setString("contractObjId", contractObjId);

				List<OrderProtask> orderProtasks = query.list();
				for (OrderProtask orderProtask : orderProtasks) {
					StringBuffer hql2 = new StringBuffer("update ProtaskItem pi set pi.finGoodSqty = pi.finGoodSqty"+operator+":qty , pi.finGoodTotal =pi.finGoodTotal"+operator+":total " +
							"where pi.objId=:objId");
					query = session.createQuery(hql2.toString());
					query.setBigDecimal("qty", orderProtask.getOrderTaskQty());
					query.setBigDecimal("total", orderProtask.getOrderTaskTotal());
					query.setString("objId", orderProtask.getProtaskItem().getObjId());
					
					query.executeUpdate();
				}
				return true;
		}});
	}

	/**
	 * 作废合同时，将同步删除订单与任务书的关系
	 */
	@SuppressWarnings("unchecked")
	public void deleteProtaskByContractForInvalid(final String contractObjId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				StringBuffer hql = new StringBuffer("delete from OrderProtask op where op.orderItem.objId in (select i.objId from OrderItem i where i.order.contract.objId=:contractObjId )");
				
				Query query = session.createQuery(hql.toString());
				query.setString("contractObjId", contractObjId);

				return query.executeUpdate();
		}});
	}
	
	
}
