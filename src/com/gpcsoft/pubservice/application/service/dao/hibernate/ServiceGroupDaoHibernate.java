package com.gpcsoft.pubservice.application.service.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.service.dao.ServiceGroupDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;

@Repository
public class ServiceGroupDaoHibernate extends BaseGenericDaoHibernate<ServiceGroup> implements ServiceGroupDao {

	/** 
	 * Description :  获取搭配服务
	 * 根据会员级别进行过滤（会员级别为空的也应该被查询出来）
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceGroup> getServiceGroupList(final Map<String,Object> params) throws Exception {
		return (List<ServiceGroup>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("select g from ServiceGroup g left join fetch g.appendService where 1=1 ");
				hql.append("and g.mainService.objId=:serviceId ");
				hql.append("and (g.memberClass.objId=:memberClassId or g.memberClass is null)");
				
				Query query = session.createQuery(hql.toString());
				query.setString("serviceId", params.get("serviceId").toString());
				query.setString("memberClassId", params.get("memberClassId").toString());
				return query.list();
			}
		});
	}
}
