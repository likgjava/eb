package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.QualificationDetailDao;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class QualificationDetailDaoHibernate extends BaseGenericDaoHibernate<QualificationDetail> implements QualificationDetailDao {

	/** 
	 * Description : 修改企业的资质信息 
	 * Create Date: 2010-11-15下午03:09:51 by guoyr  Modified Date: 2010-11-15下午03:09:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateQualificationDetail(final String objId, final String paramValue) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="update QualificationDetail t set t.paramValue =:paramValue where t.objId = :objId";
				Query query = session.createQuery(hql);
				query.setParameter("objId", objId);
				query.setParameter("paramValue", paramValue);
				return query.executeUpdate();
		}});
	}

	/** 
	 * Description :  根据资质分类code和机构id获取信息(财务信息和法务信息用的)
	 * Create Date: 2011-8-19下午02:18:57 by yucy  Modified Date: 2011-8-19下午02:18:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<QualificationDetail> getQualificationDetailListByCodeAndOrgId(final String code ,final String orgId) throws Exception {
		return (List<QualificationDetail>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql="select oqd.* from ORG_QUALIFICATION_DETAIL  oqd  ,BASE_QUALIFICATION bq ,ORG_QUALIFICATION oq"+
							" where oqd.qualification_id = oq.qualification_id and oq.quality_class_id = bq.quality_id"+
							" and bq.quality_code = :code"+
							" and oq.belong_object_id = :orgId";
				
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter("code", code);
				sqlQuery.setParameter("orgId", orgId);
				sqlQuery.addEntity(QualificationDetail.class);
				return sqlQuery.list();
		}});
	}
}
