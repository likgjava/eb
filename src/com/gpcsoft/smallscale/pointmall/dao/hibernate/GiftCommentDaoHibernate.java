package com.gpcsoft.smallscale.pointmall.dao.hibernate;

import java.sql.SQLException;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.pointmall.dao.GiftCommentDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class GiftCommentDaoHibernate extends BaseGenericDaoHibernate<GiftComment> implements GiftCommentDao {
	
	/**
	 * Description :  是否已经评价
	 * Create Date: 2011-1-14下午04:50:24 by zhaojf  Modified Date: 2011-1-14下午04:50:24 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Long hasGiftComment(final Map<String, Object> param) throws Exception {
		return  (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String giftId = (String)param.get("giftId");
				String userId = (String)param.get("userId");
				String hql= "select count(gc.objId) from GiftComment gc where gc.createUser.objId = :userId and gc.gift.objId = :giftId";
				Query query = session.createQuery(hql);
				query.setParameter("giftId",giftId);
				query.setParameter("userId",userId);
				return (Long) query.list().get(0);
			}}
		);
	}

}
