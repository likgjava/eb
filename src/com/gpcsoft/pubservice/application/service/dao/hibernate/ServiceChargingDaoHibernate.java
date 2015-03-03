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
import com.gpcsoft.pubservice.application.service.dao.ServiceChargingDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;

@Repository
public class ServiceChargingDaoHibernate extends BaseGenericDaoHibernate<ServiceCharging> implements ServiceChargingDao {
	/** 
	 * Description :  获取服务计费信息(根据服务ID和会员级别ID),会员级别为空的也要被查询出来
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceCharging> getServiceChargingList(final Map<String,Object> params) throws Exception {
		return (List<ServiceCharging>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("select g from ServiceCharging g where g.serviceBase.objId=:serviceId ");
				hql.append("and (g.memberClass.objId=:memberClassId or g.memberClass is null)");
				hql.append("and g.useStatus=:useStatus order by g.duration ");
				Query query = session.createQuery(hql.toString());
				query.setString("serviceId", params.get("serviceId").toString());
				query.setString("memberClassId", params.get("memberClassId").toString());
				query.setString("useStatus", params.get("useStatus").toString());
				return query.list();
			}
		});
	}
	
	/** 
	 * Description : 根据服务ID获取各个级别最小时长的服务计费
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ServiceCharging> getMinAgeAndFeeByOrgInfoId(Map<String,Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select g from ServiceCharging g left join fetch g.memberClass where 1=1 ");
		hql.append("and g.memberClass.objId || g.duration in ( ");
		hql.append("select t.memberClass.objId || min(t.duration) from ServiceCharging t ");
		hql.append("where t.serviceBase.objId=? group by t.memberClass.objId) ");
		hql.append("and g.serviceBase.objId=?");
		
		return this.findbyHql(hql.toString(), params.get("serviceId").toString(),params.get("serviceId").toString());
	}
}
