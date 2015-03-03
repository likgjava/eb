package com.gpcsoft.serve.hotel.dao.hibernate;

import java.sql.SQLException;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.serve.hotel.dao.HotelEvaluateDao;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class HotelEvaluateDaoHibernate extends BaseGenericDaoHibernate<HotelEvaluate> implements HotelEvaluateDao {
	/** 
	 * Description :  是否已评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long hasHotelEvaluate(final Map<String, Object> param)throws Exception {
		return  (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hotelId = (String)param.get("hotelId");
				String userId = (String)param.get("userId");
				String hql= "select count(he.objId) from HotelEvaluate he where he.createUser.objId = :userId and he.hotel.objId = :hotelId";
				Query query = session.createQuery(hql);
				query.setParameter("hotelId",hotelId);
				query.setParameter("userId",userId);
				return (Long) query.list().get(0);
			}}
		);
	}
	
}
