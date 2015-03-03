package com.gpcsoft.pubservice.common.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.model.BaseObject;
import com.gpcsoft.pubservice.common.dao.SecDomainDao;

@Repository
public class SecDomainDaoHibernate extends BaseGenericDaoHibernate<BaseObject> implements SecDomainDao {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPassSecDomainInfo() {
		return (Map<String, Object>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" select org.orgSite, o from OrgInfo o , Organization org where org.objId = o.company.objId  and o.auditStatus = :auditStatus and org.orgSite is not null ");
				Query query = session.createQuery(hql.toString());
				query.setString("auditStatus", OrganizationEnum.PASS_EXAM);
				//定义一个返回变量
				Map<String, Object> cach = null;
				for (Object o: query.list()){
					if( cach == null) cach = new HashMap<String, Object>();
					Object[] objects = (Object[])o;
					cach.put((String)objects[0], objects[1]);
				}
				return cach;
			}
		});
	}
	
}
