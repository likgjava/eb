package com.gpcsoft.bizplatform.base.purcatalog.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogProcTypeDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class PurCatalogProcTypeDaoHibernate extends BaseGenericDaoHibernate<PurCatalogProcType> implements PurCatalogProcTypeDao {

	/** 
	 * Description :  获得catalogType 明细
	 * Create Date: 2010-8-11上午10:51:13 by yucy  Modified Date: 2010-8-11上午10:51:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCatalogProcType> purCatalogProcTypeHibernate(final Map<String, Object> param) {
		return (List<PurCatalogProcType>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String catalogId = (String)param.get("catalogId");
				String categoryId = (String)param.get("categoryId");
				String hql = "from PurCatalogProcType ppt where ppt.purCatalog.objId = :catalogId and ppt.purCategory.objId = :categoryId ";
				Query query = session.createQuery(hql);
				query.setString("catalogId",catalogId );
				query.setString("categoryId",categoryId );
				return query.list();
		}});
	}

	/** 
	 * Description :  获得PurCatalogProcTypeList  by catalogId
	 * Create Date: 2010-8-12上午12:00:03 by yucy  Modified Date: 2010-8-12上午12:00:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCatalogProcType> getProcTypeInfoByCatalogId(final String catalogId) {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select ppt from PurCatalogProcType ppt left join fetch ppt.purCategory where ppt.purCatalog.objId = :catalogId ";
				Query query = session.createQuery(hql);
				query.setString("catalogId",catalogId );
				return query.list();
		}});
	}
}
