package com.gpcsoft.pubservice.application.security.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.security.dao.UserSecurityDao;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;
import com.gpcsoft.srplatform.auth.domain.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserSecurityDaoHibernate extends BaseGenericDaoHibernate<UserSecurity> implements UserSecurityDao {

	@SuppressWarnings("unchecked")
	public List<UserSecurity> getSecurityByUserId(final String userId){ 
		return (List<UserSecurity>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("from UserSecurity us left join fetch us.user usu");
				hql.append("  where usu.objId='"+userId +"' ");
				Query query = session.createQuery(hql.toString());
				return (List<UserSecurity>)query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public User getUserByUsName(final String usName) {
		return (User)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql="from User u where u.usName='"+usName+"' ";
				Query query = session.createQuery(hql);
				if(query.list().size()>0){
					return (User)query.list().get(0);
				}else{
					return null;
				}
					
				
			}
		});
		
	}

}
