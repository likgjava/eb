package com.gpcsoft.bizplatform.base.industry.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.industry.dao.IndustryDao;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.HqlResultConvertUtils;

@Repository
public class IndustryDaoHibernate extends BaseGenericDaoHibernate<Industry> implements IndustryDao {

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.dao.IndustryDao#updateIndustryIsLeaf(java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public void updateIndustryIsLeaf(final String objId, final boolean isleaf) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update Industry set isLeaf =:isLeaf where objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				query.setBoolean("isLeaf", isleaf);
				return query.executeUpdate();
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.purcategory.dao.IndustryDao#getSubIndustryCount(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Long getSubIndustryCount(final String objId) {
		return (Long)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select count(objId) from Industry  where parent.objId = ( " +
				"select parent.objId from Industry  where objId =:objId)";
				Query query = session.createQuery(hql);
				query.setString("objId", objId);
				List<Object> list = query.list();
				if(list != null && list.size() > 0) {
					return (Long)list.get(0);
				} else {
					return 0;
				}
		
			}
		});
	}
	
	/** 
	 * Description :  获得X级行业
	 * Create Date: 2011-11-8上午11:04:45 by yucy  Modified Date: 2011-11-8上午11:04:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Industry> getIndustryByLevel(final Short level) throws Exception {
		return (List<Industry>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql = " select ind.industry_id, ind.industry_code, ind.industry_name ,ind.short_spell_name from base_industry ind where ind.ind_level = '"+level+"' ";
				Query query = session.createSQLQuery(sql);
				String[] queryColum = new String[]{"objId","code","name" ,"shortSpellName"};
				return HqlResultConvertUtils.hqlResultConvert(query.list(),queryColum,Industry.class);
//				if(list != null && list.size() > 0) {
//					return (Long)list.get(0);
//				} else {
//					return 0;
//				}
			}
		});
	}
}
