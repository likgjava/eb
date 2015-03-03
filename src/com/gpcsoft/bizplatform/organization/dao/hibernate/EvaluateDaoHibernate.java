package com.gpcsoft.bizplatform.organization.dao.hibernate;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.dao.EvaluateDao;
import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class EvaluateDaoHibernate extends BaseGenericDaoHibernate<Evaluate> implements EvaluateDao {

	/** 
	 * Description : 取得机构评价统计表 
	 * Create Date: 2010-8-12下午05:26:37 by yucy  Modified Date: 2010-8-12下午05:26:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getTotalTableInfo(final OrgInfo currentOrg,final Character Level) throws Exception {
		return (Map<String, Object>)getHibernateTemplate().execute(new HibernateCallback(){
			@SuppressWarnings("static-access")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> result = new HashMap<String,Object>();
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, +1);
				
				String today = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));
				
				calendar.setTime(new Date());
				calendar.add(calendar.DATE, -7);
				String oneWeek = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));

				calendar.setTime(new Date());
				calendar.add(calendar.MONTH, -1);
				String oneMonth = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));
				
				calendar.setTime(new Date());
				calendar.add(calendar.MONTH, -6);
				String sixMonth = UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date(DateUtil.format(calendar.getTime()));
				
				String sql1 = "select temp1.a,temp2.b,temp3.c,temp4.d,(temp3.c+temp4.d) from (select count(e.evaluate_id) as a from ECP_PUB_EVALUATE e where e.eval_leval = :leval and e.orginfo_id = :orgId  and e.eval_time  between "+oneWeek+" and "+today+")temp1 "+
							"left join (select count(e.evaluate_id) as b from ECP_PUB_EVALUATE e where e.eval_leval = :leval and e.orginfo_id = :orgId and e.eval_time between "+oneMonth+" and "+today + ") temp2 on 1=1 "+
							"left join (select count(e.evaluate_id) as c from ECP_PUB_EVALUATE e where e.eval_leval = :leval and e.orginfo_id = :orgId and e.eval_time between "+sixMonth+" and "+today +") temp3 on 1=1 "+
							"left join (select count(e.evaluate_id) as d from ECP_PUB_EVALUATE e where e.eval_leval = :leval and e.orginfo_id = :orgId and e.eval_time < "+sixMonth+" ) temp4 on 1=1 ";
				
				Query row1 = session.createSQLQuery(sql1);
				row1.setParameter("leval",Level);
				row1.setParameter("orgId",currentOrg.getObjId());
				
				Object[] objectStrings = (Object[])row1.list().get(0);
				result.put("lastWeek", objectStrings[0]);
				result.put("lastMonth", objectStrings[1]);
				result.put("lastSixMonth",objectStrings[2]);
				result.put("beforSixMonth",objectStrings[3]);
				result.put("total",objectStrings[4]);
				return result;
			}}
		);
	}

	
	/** 
	 * Description :  取得机构指标评价详情信息
	 * Create Date: 2010-8-13下午03:30:41 by yucy  Modified Date: 2010-8-13下午03:30:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getQuataScoreDetail(final OrgInfo currentOrg, final String quotaType)throws Exception {
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String sql = "select q.quota_name,q.quota_id ,(sum(temp1.score)/(count(q.quota_id)*10))*10 from ecp_pub_evaluate_quota q "+
							"left join  (select s.quota_id,s.score_id,s.score from ecp_pub_evaluate_score s ,ecp_pub_evaluate e where s.evaluate_id = e.evaluate_id and e.orginfo_id = :orgId ) temp1 "+
							"on q.quota_id = temp1.quota_id "+
							"where q.quota_type = :quotaType group by q.quota_id,q.quota_name";
				Query query = session.createSQLQuery(sql);
				query.setParameter("orgId", currentOrg.getObjId());
				query.setParameter("quotaType",quotaType);
				return query.list();
			}}
		);
	}

	/** 
	 * Description :  获取评价列表
	 * Create Date: 2011-2-23下午06:05:22 by likg  Modified Date: 2011-2-23下午06:05:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Evaluate> getEvaluateList(final Page<Evaluate> page, final Map<String, Object> param) throws Exception {
		return (Page<Evaluate>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Object orgInfoId = param.get("orgInfoId"); //被评价机构的id
				
				String preHql = "from Evaluate e where 1=1 ";
				StringBuilder hql = new StringBuilder();
				
				if(orgInfoId!=null && StringUtils.hasLength(orgInfoId.toString())){
					hql.append(" and e.org.objId = '").append(orgInfoId.toString()).append("'");
				}
				
				Query query = session.createQuery(preHql + hql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				page.setData(query.list());
				
				//查询总数
				preHql = "select count(e.objId) from Evaluate e where 1=1 ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

	/** 
	 * Description : 根据机构id取得评价信息
	 * Create Date: 2011-12-7上午11:56:19 by yucy  Modified Date: 2011-12-7上午11:56:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getEvaluateByOrgId(final String orgIds) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "select o.org_info_id ,nvl ( sum(e.summary_score)/count(e.evaluate_id),0) from org_info o left join ECP_PUB_EVALUATE e on o.org_info_id = e.orginfo_id where o.org_info_id  in (:orgIds) group by o.org_info_id";
				Query query = session.createSQLQuery(sql);
				query.setParameterList("orgIds", orgIds.split(","));
				return query.list();
			}
		});
	}
}
