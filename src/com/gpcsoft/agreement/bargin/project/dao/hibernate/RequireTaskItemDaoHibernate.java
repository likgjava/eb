package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.agreement.bargin.project.dao.RequireTaskItemDao;
import com.gpcsoft.agreement.bargin.project.domain.RequireTaskItem;
import com.gpcsoft.agreement.order.domain.ProtaskItem;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class RequireTaskItemDaoHibernate extends BaseGenericDaoHibernate<RequireTaskItem> implements RequireTaskItemDao {

	/** 
	 * Description :  根据需求Id获取任务书条目(返回值也能为空)
	 * Create Date: 2011-1-21上午11:14:06 by yucy  Modified Date: 2011-1-21上午11:14:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public ProtaskItem getTaskItemByRequireItem(final String requireItemId) throws Exception {
		return (ProtaskItem) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = "select rt.protaskItem from RequireTaskItem rt where rt.requireItem.objId = :requireItemId ";
					Query query = session.createQuery(Hql);
					query.setParameter("requireItemId",requireItemId );
					List list = query.list();
					return list.size()>0?list.get(0):null;
				}
		});
	}

}
