package com.gpcsoft.pubservice.application.service.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.pubservice.application.service.dao.ServiceSubscribeDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class ServiceSubscribeDaoHibernate extends BaseGenericDaoHibernate<ServiceSubscribe> implements ServiceSubscribeDao {

	/** 
	 * Description :  更新服务订阅支付状态和支付金额
	 * Create Date: 2011-7-21下午04:30:56 by sunl  Modified Date: 2011-7-21下午04:30:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean updateServicePayStatus(final String serviceOrderId, final BigDecimal payAmount, final String payStatus) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql="update ServiceSubscribe o set o.payStatus = :payStatus,o.payAmount = :payAmount where o.objId = :objId ";
				Query query = session.createQuery(hql);
				query.setString("payStatus", payStatus);
				query.setBigDecimal("payAmount",payAmount);
				query.setString("objId", serviceOrderId);
				return query.executeUpdate()>0;
			}
		});
	}
	
	/** 
	 * Description :  我订阅的服务
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceSubscribe> getMySubscribeList(final Map<String,Object> params) throws Exception {
		List<ServiceSubscribe> list = (List<ServiceSubscribe>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				hql.append("from ServiceSubscribe t left join fetch t.serviceBase where 1=1 ");
				if(StringUtils.hasLength((String)params.get("orgInfoId"))) {
					hql.append(" and t.orgInfo.objId =:orgInfoId ");
				}
				hql.append(" order by t.createTime desc ");
				query = session.createQuery(hql.toString());
				if(StringUtils.hasLength((String)params.get("orgInfoId"))) {
					query.setParameter("orgInfoId", params.get("orgInfoId"));
				}
				return query.list();
		}});
		return list;
	}
	
	/** 
	 * Description :  判断服务是否已订阅
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer isServcieSubscribed(final Map<String,Object> params) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				Query query = null;
				sql.append("select t.SERVICE_SUBSCRIBE_ID,t.audit_status from SERVICE_SUBSCRIBE t where 1=1 ");
				sql.append(" and t.end_time > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				if(StringUtils.hasLength((String)params.get("orgInfoId"))) {
					sql.append(" and t.ORG_INFO_ID =:orgInfoId ");
				}
				if(StringUtils.hasLength((String)params.get("serviceId"))) {
					sql.append(" and t.SERVICE_ID =:serviceId ");
				}
				sql.append(" order by t.createTime desc ");
				query = session.createSQLQuery(sql.toString());
				if(StringUtils.hasLength((String)params.get("orgInfoId"))) {
					query.setParameter("orgInfoId", params.get("orgInfoId"));
				}
				if(StringUtils.hasLength((String)params.get("serviceId"))) {
					query.setParameter("serviceId", params.get("serviceId"));
				}
				return query.list().size();
		}});
	}
	
	/** 
	 * Description :  判断机构是否拥有某个(些)服务
	 * 				  如果是，返回true，否则返回false
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isOrgInfoHasService(Map<String,Object> params) throws Exception {
		boolean res = false;
		StringBuilder hql = new StringBuilder();
		hql.append("from ServiceSubscribe t where t.orgInfo.objId=? and t.serviceBase.objId=? ");
		hql.append(" and t.auditStatus=? and t.startTime <= ");
		hql.append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
		hql.append(" and t.endTime >= ");
		hql.append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
		List<ServiceSubscribe> resList = this.findbyHql(hql.toString(),
				(String)params.get("orgInfoId"),(String)params.get("serviceId"),ServiceEnum.PASS_EXAM);
		
		if(null != resList && resList.size() > 0) {
			res = true;
		}
		return res;
	}
	
	/** 
	 * Description : 根据机构ID获取订阅的总时长和总缴费金额（有效订阅）
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> getTotalAgeAndFeeByOrgInfoId(Map<String,Object> params) throws Exception {
		return null;
	}
	
	/** 
	 * Description :  获取已经订阅的服务ID（以逗号分割）
	 * Create Date: 2011-4-22下午02:49:13 by likg  Modified Date: 2011-4-22下午02:49:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String getSubscribedServiceIds(Map<String,Object> params) throws Exception {
		String auditStatus = (String) params.get("auditStatus"); //审核状态
		String orgInfoId = (String) params.get("orgInfoId"); //机构id
		String resStr = ""; //服务id（以逗号分割）
		
		StringBuilder hql = new StringBuilder();
		hql.append("select t.serviceBase.objId from ServiceSubscribe t where 1=1 ");
		hql.append(" and t.startTime <= " ).append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
		hql.append(" and t.endTime >= " ).append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
		
		//过滤审核状态
		if(StringUtils.hasLength(auditStatus)) {
			hql.append(" and t.auditStatus = '").append(auditStatus).append("' ");
		}
		
		//过滤机构
		if(StringUtils.hasLength(orgInfoId)) {
			hql.append(" and t.orgInfo.objId = '").append(orgInfoId).append("' ");
		}
		
		List res = this.findbyHql(hql.toString());
		for(Object obj : res) {
			resStr += obj.toString()+",";
		}
		return resStr;
	}
	
	/** 
	 * Description : 根据机构ID获取总缴费金额
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Double getTotalFeeByOrgInfoId(Map<String,Object> params) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(t.pay_amount) from service_subscribe t where t.org_info_id=:orgInfoId and t.audit_status=:auditStatus");
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setString("orgInfoId", (String)params.get("orgInfoId"));
		query.setString("auditStatus", (String)params.get("auditStatus"));
		
		List res = query.list();
		
		Double sumFee = 0.0;
		if(null != res && res.size() > 0 && res.get(0) != null) {
			for (Object obj : res) {
				sumFee += ((BigDecimal)obj).doubleValue();
			}
		}
		return sumFee;
	}

	/** 
	 * Description :  取得有某个服务的所有机构用户（机构管理员）
	 * Create Date: 2011-7-25上午10:25:53 by yucy  Modified Date: 2011-7-25上午10:25:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUserHasService(final Map<String, Object> params) throws Exception {
		return (List<User>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categorySqlString = "";
				String [] categoryIds = params.get("purCategoryIds").toString().split(",");
				for (int i=0 ;i< categoryIds.length ;i++){
					categorySqlString += StringUtils.hasLength(categorySqlString)?" or o.bidForRange like ?":"o.bidForRange like ?";
				}
				String  Hql = "select u from User u ,OrgInfo o ,ServiceSubscribe s  " +
				" where u.usrIsAdmin='" + User.USER_TYPE_MANAGER + "' and u.emp.company.objId = o.company.objId and o.objId = s.orgInfo.objId " +
				" and s.serviceBase.objId='"+(String)params.get("serviceId")+"'"+
				" and s.auditStatus='"+ServiceEnum.PASS_EXAM+"' " +
				" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime())+" between s.startTime and s.endTime "+
				" and (" + categorySqlString + ")";

				Query query = session.createQuery(Hql);
				for (int i=0 ;i< categoryIds.length ;i++){
					query.setParameter(i, "%"+categoryIds[i]+"%");
				}
				return query.list();
		}});
	}
}
