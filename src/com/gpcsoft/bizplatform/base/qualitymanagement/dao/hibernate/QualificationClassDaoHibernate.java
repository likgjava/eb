package com.gpcsoft.bizplatform.base.qualitymanagement.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationClassDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class QualificationClassDaoHibernate extends BaseGenericDaoHibernate<QualificationClass> implements QualificationClassDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationClassDao#getQualificationClassMaxSort()
	 */
	@SuppressWarnings("unchecked")
	public int getQualificationClassMaxSort() {
		
		String hql = "select " + UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("max(sort)","0") + " from QualificationClass where parent.objId is null ";
		List<Object> list = getHibernateTemplate().find(hql);
		
		return (Integer) list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationClassDao#removeQualificationClass(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Long getQualificationClassCountFromOrgQuality(String objId) {
		String hql = "select count(objId) from OrgQuality where qualificationClass.objId = ?";
		List<Object> list = getHibernateTemplate().find(hql, objId);
		
		return (Long) list.get(0);
	}

	/** 
	 * Description : 根据资质分类的编号获得该分类  
	 * Create Date: 2010-11-18上午10:28:16 by guoyr  Modified Date: 2010-11-18上午10:28:16 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public QualificationClass getQualificationClassByCode(final String qualityCode){
		return (QualificationClass)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="from QualificationClass  where qualityCode =:qualityCode";
				Query query = session.createQuery(hql);
				query.setString("qualityCode", qualityCode);
				List<QualificationClass>  list =  query.list();
				QualificationClass qualificationClass = null;
				if(null != list && list.size() > 0){
					qualificationClass = list.get(0);
				}
				return qualificationClass;
			}
		});
	}
}
