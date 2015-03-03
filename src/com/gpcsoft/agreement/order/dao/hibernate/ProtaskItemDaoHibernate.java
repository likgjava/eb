package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.ProtaskItemDao;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class ProtaskItemDaoHibernate extends BaseGenericDaoHibernate<ProtaskItem> implements ProtaskItemDao {
	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProtaskItem> getProtaskItemList(final String objIds) throws Exception {
		return (List<ProtaskItem>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from ProtaskItem as pi left join fetch pi.purCategory where pi.objId in (:objId)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				return query.list();
			}
		});
	}
}
