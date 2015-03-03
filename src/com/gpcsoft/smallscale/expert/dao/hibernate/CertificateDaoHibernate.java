package com.gpcsoft.smallscale.expert.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.expert.dao.CertificateDao;
import com.gpcsoft.smallscale.expert.domain.Certificate;

@Repository
public class CertificateDaoHibernate extends BaseGenericDaoHibernate<Certificate> implements CertificateDao {

	/** 
	 * Description : 审核职称信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateCertificateAuditStatus(final String objIds,final String auditStatus){
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				if(StringUtils.hasLength(objIds)){
					hql.append("update Certificate t set t.auditStatus =:auditStatus where t.objId in (:objId)");
					query = session.createQuery(hql.toString());
					query.setParameter("auditStatus", auditStatus);
					query.setParameterList("objId", objIds.split(","));
				}
				return query.executeUpdate();
			}
		});
	}
	
}
