package com.gpcsoft.smallscale.point.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.ConsumeDao;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.srplatform.auth.domain.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumeDaoHibernate extends BaseGenericDaoHibernate<Consume> implements ConsumeDao {
	/**
	 * 查询用户消费的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryConsumeIntegral(User user)throws Exception{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	    String hql="select SUM(consumeNumber) from Consume where userId=:userId";
		Query query=session.createQuery(hql);
		query.setEntity("userId", user);
		long consume=0;
		Object o = query.list().get(0);
		if(o != null){
			consume	= Long.parseLong(o.toString());
		}
		
		return consume;
	}
	
	/**
	 * 根据项目ID取的消费记录
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Consume getByProjectId(String projectId){
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	    String hql="from Consume where consumeProjectId=:consumeProjectId";
		Query query=session.createQuery(hql);
		query.setString("consumeProjectId", projectId);
		List<Consume> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		else {
			return null;
		}
		
	}
	
}
