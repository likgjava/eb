package com.gpcsoft.smallscale.point.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.DealDao;
import com.gpcsoft.smallscale.point.domain.Deal;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class DealDaoHibernate extends BaseGenericDaoHibernate<Deal> implements DealDao {
	/**
	 * 查询用户交易的积分__谁赠送的 减去
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealFormUserIntegral(User user)throws Exception{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	    String hql="select SUM(dealNumber) from Deal where formUser=:userId";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		long formUserDeal=0;
		
		Object o = query.list().get(0);
		if(o != null){
			formUserDeal	= Long.parseLong(o.toString());
		}
		

		return formUserDeal;
	}
	
	/**
	 * 查询用户交易的积分__赠送给谁的  加上
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealToUserIntegral(User user)throws Exception{
        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	    String hql="select SUM(dealNumber) from Deal where toUser=:userId";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		long toUserDeal=0;
		Object o = query.list().get(0);
		if(o != null){
			toUserDeal	= Long.parseLong(o.toString());
		}
		//toUserDeal= Long.parseLong(query.list().get(0).toString());
		return toUserDeal;
	}
}
