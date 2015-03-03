package com.gpcsoft.bizplatform.base.qualitymanagement.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDefineDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class QualificationDefineDaoHibernate extends BaseGenericDaoHibernate<QualificationDefine> implements QualificationDefineDao {

	/** 
	 * Description :  取得资质定义 根据资质分类
	 * Create Date: 2010-7-30下午02:47:57 by yucy  Modified Date: 2010-7-30下午02:47:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<QualificationDefine> getDefineByClass(final Map<String, Object> param) {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String qualificationClassId = (String)param.get("qualificationClassId");
				String orgId = (String)param.get("orgId");
				Assert.notNull(qualificationClassId);
				Assert.notNull(orgId);
				String hql="from QualificationDefine q where q.parent.objId = :qualificationClassId  and  q.objId not in (select o.qualificationDefine.objId from OrgQuality o where o.org.objId = :orgId)";
				Query query = session.createQuery(hql);
				query.setParameter("qualificationClassId", qualificationClassId);
				query.setParameter("orgId", orgId);
				return query.list();
		}});
		return list;
	}

	/** 
	 * Description : 查找机构资质信息中引秀用质定义的个数 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Long getQualificationDefineCountFromOrgQuality(String objId) {
		String hql = "select count(objId) from OrgQuality where qualificationDefine.objId = '" + objId + "'";
		List<Object> list = getHibernateTemplate().find(hql);
		
		return (Long) list.get(0);
	}

}
