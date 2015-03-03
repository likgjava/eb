package com.gpcsoft.smallscale.groupbuying.dao.hibernate;

import java.sql.SQLException;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyerDao;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class GroupBuyerDaoHibernate extends BaseGenericDaoHibernate<GroupBuyer> implements GroupBuyerDao {

	/** 
	 * Description :  修改支付状态
	 * Create Date: 2011-6-24上午11:27:13 by likg  Modified Date: 2011-6-24上午11:27:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean updatePayStatus(final String objId, final String payStatus) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql="update GroupBuyer gb set gb.payStatus = :payStatus where gb.objId = :objId ";
				Query query = session.createQuery(hql);
				query.setString("payStatus", payStatus);
				query.setString("objId", objId);
				return query.executeUpdate()>0;
			}
		});
	}
}
