package com.gpcsoft.bizplatform.base.purcatalog.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDetailDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class PurCatalogDetailDaoHibernate extends BaseGenericDaoHibernate<PurCatalogDetail> implements PurCatalogDetailDao {

	/** 
	 * Description :  获得catalogdetail明细
	 * Create Date: 2010-8-11上午10:50:38 by yucy  Modified Date: 2010-8-11上午10:50:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCatalogDetail> getDetailInfo(final Map<String, Object> param) throws Exception {
		return (List<PurCatalogDetail>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String catalogId = (String)param.get("catalogId");
				String categoryId = (String)param.get("categoryId");
				String hql = "select pd from PurCatalogDetail pd where pd.purCatalog.objId = :catalogId and pd.purCategory.objId = :categoryId ";
				Query query = session.createQuery(hql);
				query.setString("catalogId",catalogId );
				query.setString("categoryId",categoryId );
				return query.list();
		}});
	}

	/** 
	 * Description :  根据目录Id取得PurCatalogDetailList
	 * Create Date: 2010-8-11下午11:51:21 by yucy  Modified Date: 2010-8-11下午11:51:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<PurCatalogDetail> getDetailInfoByCatalogId(final String catelogId)throws Exception {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select pd from PurCatalogDetail pd left join fetch pd.purCategory where pd.purCatalog.objId = :catelogId ";
				Query query = session.createQuery(hql);
				query.setString("catelogId",catelogId );
				return query.list();
		}});
	}
	
}
