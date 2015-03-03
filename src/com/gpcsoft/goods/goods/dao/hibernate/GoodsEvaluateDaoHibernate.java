package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsEvaluateDao;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;

@Repository
public class GoodsEvaluateDaoHibernate extends BaseGenericDaoHibernate<GoodsEvaluate> implements GoodsEvaluateDao {

	/** 
	 * Description :  是否已评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long hasGoodsEvaluate(final Map<String, Object> param)throws Exception {
		return  (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String goodsId = (String)param.get("goodsId");
				String userId = (String)param.get("userId");
				String hql= "select count(ge.objId) from GoodsEvaluate ge where ge.createUser.objId = :userId and ge.goods.objId = :goodsId";
				Query query = session.createQuery(hql);
				query.setParameter("goodsId",goodsId);
				query.setParameter("userId",userId);
				return (Long) query.list().get(0);
			}}
		);
	}
}
