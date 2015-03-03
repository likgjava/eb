package com.gpcsoft.smallscale.expert.dao.hibernate;

import java.sql.SQLException;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.expert.dao.EducationDao;
import com.gpcsoft.smallscale.expert.domain.Education;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class EducationDaoHibernate extends BaseGenericDaoHibernate<Education> implements EducationDao {

	/** 
	 * Description : 审核教育经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateEducationAuditStatus(final String objIds, final String auditStatus){
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				if(StringUtils.hasLength(objIds)){
					hql.append("update Education t set t.auditStatus =:auditStatus where t.objId in (:objId)");
					query = session.createQuery(hql.toString());
					query.setParameter("auditStatus", auditStatus);
					query.setParameterList("objId", objIds.split(","));
				}
				return query.executeUpdate();
			}
		});
	}
}
