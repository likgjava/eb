package com.gpcsoft.bizplatform.base.purcatalog.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDistrictDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class PurCatalogDistrictDaoHibernate extends BaseGenericDaoHibernate<PurCatalogDistrict> implements PurCatalogDistrictDao {

	/** 
	 * Description :  删除中间对象
	 * Create Date: 2010-8-25上午10:05:46 by yucy  Modified Date: 2010-8-25上午10:05:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean deletePurCatalogDistrictByCataId(final String purCatalogId) {
		Boolean result = false;
		Integer flag =  (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException,SQLException {
				String hql = "delete from PurCatalogDistrict pcd where pcd.purCatalog.objId = :purCatalogId";
				Query query = session.createQuery(hql);
				if(null!=purCatalogId&&!"".equals(purCatalogId)){
					query.setParameter("purCatalogId", purCatalogId);
				}
				return query.executeUpdate();
			}
		});
		if(flag>0){
			result =true;
		}
		return result;
	}
}
