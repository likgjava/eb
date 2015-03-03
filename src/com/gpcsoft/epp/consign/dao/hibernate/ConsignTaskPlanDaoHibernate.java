package com.gpcsoft.epp.consign.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.epp.consign.dao.ConsignTaskPlanDao;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

@Repository
public class ConsignTaskPlanDaoHibernate extends BaseGenericDaoHibernate<ConsignTaskPlan> implements ConsignTaskPlanDao {
	
	/** 
	 * FuncName:getConsignByTaskPlan
	 * Description:根据采购计划获得委托协议
	 * @param   taskPlanIds:以逗号分隔的申报书主键
	 * @return  List<Consign>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	@SuppressWarnings("unchecked")
	public List<Consign> getConsignByTaskPlan(String taskPlanIds){
		//将id拼装成 'id1','id2'...的格式
		//modify by xiaojf
		String[] ids = taskPlanIds.split(",");
		String tIds = "";
		for (String id : ids) {
			tIds +=  id + "," ;
		}
		tIds = tIds.substring(0,tIds.length()-1);
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct consign from ConsignTaskPlan  ct left join ct.consign as consign ");
		hql.append("where ct.taskPlan.objId in (:tIds)");
		return this.getHibernateTemplate().findByNamedParam(hql.toString(), "tIds", tIds);
	}
	
	/** 
	 * FuncName:getTaskPlanByConsign
	 * Description :  根据委托协议获得采购计划
	 * @param   consignIds:以逗号分隔的委托协议主键
	 * @return  List<TaskPlan>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlan> getTaskPlanByConsign(String consignIds){
		//将id拼装成 'id1','id2'...的格式
		//modify by xiaojf
		String[] ids = consignIds.split(",");
		String tIds = "";
		for (String id : ids) {
			tIds +=  id + "," ;
		}
		tIds = tIds.substring(0,tIds.length()-1);
		String hql="select distinct taskPlan from ConsignTaskPlan ct left join ct.taskPlan as taskPlan where ct.consign.objId in (:tIds)";
		return this.getHibernateTemplate().findByNamedParam(hql, "tIds", tIds);
	}
	
	/** 
	 * FuncName:removeByConsign
	 * Description :  根据委托协议删除中间表
	 * @param   consignIds:以逗号分隔的委托协议主键
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	@SuppressWarnings("unchecked")
	public void removeByConsign(final String consignIds){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete from ConsignTaskPlan c where c.consign.objId in (:consignIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("consignIds", consignIds.split(","));
				
				return query.executeUpdate();
			}
		});
	}

	/**
	 * FuncName:removeConsignTaskPlanBytaskPlanIdAndConsignId
	 * Description :根据项目主键和委托书主键删除中间表对象
	 * @param   taskPlanId:申报书主键,consignId:委托协议主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier   liuke 
	 * @Modified Date: 2010-7-7下午01:26:14 
	 */
	@SuppressWarnings("unchecked")
	public void removeConsignTaskPlanBytaskPlanIdAndConsignId(final	String taskPlanId,final String consignId) {
	          getHibernateTemplate().execute(new HibernateCallback(){
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {	
						StringBuffer hql = new StringBuffer();
					    hql.append("delete from ConsignTaskPlan ctp where ctp.consign.objId ='"+consignId+"' and ctp.taskPlan.objId ='"+taskPlanId+"'");
						Query query = session.createQuery(hql.toString());
						return query.executeUpdate();
					}
				});
	}

	/**
	 * FuncName:removeConsignTaskPlanByConsignId
	 * Description :根据委托书删除中间表对象  
	 * @param   consignId:委托协议主键
	 * @return  void
	 * @author lic
	 * @Create Date: 2010-7-14下午07:08:38 
	 * @Modifier   lic 
	 * @Modified Date: 2010-7-14下午07:08:38 
	 */
	@SuppressWarnings("unchecked")
	public void removeConsignTaskPlanByConsignId(final	String consignId) {
		  getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {	
					StringBuffer hql = new StringBuffer();
				    hql.append("delete from ConsignTaskPlan ctp where ctp.consign.objId ='"+consignId+"'");
					Query query = session.createQuery(hql.toString());
					return query.executeUpdate();
				}
			});
		
	}

	/** 
	 * FuncName:searchByQueryObject
	 * Description:根据查询对象获取对应的数据
	 * @param queryObject
	 * @return List<ConsignTaskPlan>
	 * @author liuy
	 * @Create Date: 2010-7-15下午06:50:42 by   
	 * @Modifier liuy
	 * @Modified Date: 2010-7-15下午06:50:42 
	 */
	public List<ConsignTaskPlan> searchByQueryObject(QueryObject<ConsignTaskPlan> queryObject) {
		StringBuffer hql = new StringBuffer("select t from ConsignTaskPlan t where 1=1  ");
		StringBuffer whereHql = new StringBuffer();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("taskPlanSubs".equals(queryParam.getName())){
					whereHql.append(" and t.taskPlanSub.objId in( '"+((String)queryParam.getValue()).replace(",", "','")+"') ");
				}
			}
		}
		return this.findbyHql(hql.toString()+whereHql.toString(), new Object[]{});
	}

}
