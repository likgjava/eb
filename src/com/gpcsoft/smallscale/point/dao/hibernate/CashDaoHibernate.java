package com.gpcsoft.smallscale.point.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.CashDao;
import com.gpcsoft.smallscale.point.domain.Cash;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class CashDaoHibernate extends BaseGenericDaoHibernate<Cash> implements CashDao {
	/**
	 * 查询用户兑现的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryCashIntegral(User user)throws Exception{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	    String hql="select SUM(cashNumber) from Cash where userId=:userId";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		long cash=0;
		Object o = query.list().get(0);
		if(o != null){
			cash	= Long.parseLong(o.toString());
		}		
		return cash;
	}
}
