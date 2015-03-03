package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goods.dao.GoodsOptionalFittingDao;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;

@Repository
public class GoodsOptionalFittingDaoHibernate extends BaseGenericDaoHibernate<GoodsOptionalFitting> implements GoodsOptionalFittingDao {

	/** 
	 * Description :  禁用选配
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer disableFitting(final String objId) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				//禁用02
				hql.append("update GoodsOptionalFitting t set t.isUse = '").append(GoodsEnum.SELL_STOP).append("' where t.objId =:objId)");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}
}
