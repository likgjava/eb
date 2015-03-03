package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.OrderGoodsOptionDao;
import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrderGoodsOptionDaoHibernate extends BaseGenericDaoHibernate<OrderGoodsOption> implements OrderGoodsOptionDao {
	
	@SuppressWarnings("unchecked")
	public void updateChangeQtyAndMoney(final OrderGoodsOption option) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql=new StringBuffer("update OrderGoodsOption option set option.optPrice=:optPrice , option.optQty=:optQty where option.objId=:objId");
				
				Query query = session.createQuery(hql.toString());
				query.setString("objId", option.getObjId());
				query.setBigDecimal("optPrice", option.getOptPrice());
				query.setBigDecimal("optQty", option.getOptQty());
				
				return query.executeUpdate();
			}});
	}

}
