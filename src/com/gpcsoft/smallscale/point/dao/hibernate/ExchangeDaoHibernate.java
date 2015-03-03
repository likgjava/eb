package com.gpcsoft.smallscale.point.dao.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.ExchangeDao;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ExchangeDaoHibernate extends BaseGenericDaoHibernate<Exchange> implements ExchangeDao {
	/**
	 * 查询用户积分表的当前有效积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryLeaveIntegral(User user)throws Exception{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		//用户的积分--积分状态：有效， 且在有效日期内
	    String hql="select SUM(exchangeNumber) FROM Exchange WHERE userId=:userId " +
	    		" AND currentStatus=:currentStatus AND valDate>=:nowDate";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		query.setString("currentStatus", SmallscaleEnum.CURRENT_STATUS_YES);
		query.setDate("nowDate", new Date());
		long integral=0;
		Object o = query.list().get(0);
		if(o != null){
			integral	= Long.parseLong(o.toString());
		}
		
		return integral;
	}
	
	/**
	 * 查询用户的历史总积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryHistoryTotalLeave(User user)throws Exception{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		//用户的历史总积分
	    String hql="select SUM(exchangeNumber) FROM Exchange WHERE userId=:userId ";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		long history = 0;
		Object o = query.list().get(0);
		if(o != null){
			history	= Long.parseLong(o.toString());
		}
		return history;
	}
}
