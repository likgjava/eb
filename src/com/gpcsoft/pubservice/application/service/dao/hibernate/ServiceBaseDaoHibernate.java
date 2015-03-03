package com.gpcsoft.pubservice.application.service.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.service.dao.ServiceBaseDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;

@Repository
public class ServiceBaseDaoHibernate extends BaseGenericDaoHibernate<ServiceBase> implements ServiceBaseDao {

	/** 
     * Description : 查询要订阅的服务列表
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	public List<ServiceBase> getServiceSubscribeList() throws Exception {
		return (List<ServiceBase>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("select g from ServiceBase g where 1=1 ");
				hql.append("and g.useStatus=:useStatus ");//过滤有效的服务
				hql.append("order by g.isRecommendation ");
				Query query = session.createQuery(hql.toString());
				query.setString("useStatus", ServiceEnum.USE_VALID);
				return query.list();
			}
		});
	}

	/** 
     * Description :  修改服务的状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	public void updateStatus(final Map<String, Object> param) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String objId = (String) param.get("objId");
				String statusName = (String) param.get("statusName"); //属性名
				String statusValue = (String) param.get("statusValue"); //属性值
				
				StringBuilder hql = new StringBuilder();
				
				if(StringUtils.hasLength(statusName)) {
					hql.append("update ServiceBase s set s.").append(statusName).append(" = '").append(statusValue).append("' where s.objId in (");
					for(String id : objId.split(",")) {
						hql.append(" '").append(id).append("',");
					}
					if(hql.toString().endsWith(",")) {
						hql.deleteCharAt(hql.length()-1);
					}
					hql.append(")");
				}
				
				Query query = session.createQuery(hql.toString());
				query.executeUpdate();
				
				return null;
			}
		});
	}
}
