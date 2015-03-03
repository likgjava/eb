package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;

@Repository
public class TaskPlanSubDaoHibernate extends BaseGenericDaoHibernate<TaskPlanSub> implements TaskPlanSubDao {
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 删除申报书明细,同时删除关联申报书中间表
	 * @param objId 申报书明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:43:10 
	 */
	@SuppressWarnings("unchecked")
	public void removeTaskPlanSub(final String objId) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete TaskPlanMSub where taskPlanSub.objId =:objId";
				session.createQuery(hql).setString("objId", objId).executeUpdate();
				return null;
			}});
	}
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanSubIds
	 * Description :  根据申报书明细Ids获取申报书明细
	 * @param page:分页对象,taskPlanSubIds:申报书条目主键
	 * @return  Page<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:32:46 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-2下午02:32:46 by 
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlanSub> getTaskPlanSubByTaskPlanSubIds(Page<TaskPlanSub> page,String taskPlanSubIds){
		String hql = "From TaskPlanSub t where t.objId in ("+taskPlanSubIds+")";
		Page<TaskPlanSub> pages = this.findbyHql(hql,page.getStart(),page.getPageSize(),TaskPlanSub.class);
		return pages;
	}
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description :  根据项目Id获取申报书明细
	 * @param  projectId:项目ID 
	 * @return  List<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午03:50:23 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-2下午03:50:23 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List<TaskPlanSub> getTaskPlanSubByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append("From TaskPlanSub t where t.objId in");
		hql.append("(select tm.taskPlanSub from ProjectMTaskPlan tm where tm.tproject.objId='"+projectId+"')");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		return this.findbyHql(hql.toString());
	}
	
	/**
	 * FuncName:getLowerLeverTaskPlanSubByTaskPlan
	 * Description: 获取下级申报书汇总明细
	 * @param taskPlanId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-15 下午06:14:48  
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanSub> getLowerLeverTaskPlanSubByTaskPlan(final String taskPlanId)throws EsException{
		return (List<TaskPlanSub>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<TaskPlanSub> doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append(" select t.taskPlanSub from TaskPlanMSub t where  t.taskPlan=:taskPlanId and t.status=:status ");
				List<TaskPlanSub> taskPlanSubList = session.createQuery(hql.toString()).setString("taskPlanId", taskPlanId).setString("status", TaskPlanSumEnum.SUMMARY).list();
				if (null == taskPlanSubList || taskPlanSubList.isEmpty()) {
					taskPlanSubList = new ArrayList<TaskPlanSub>();
				}
				return taskPlanSubList;
			}});
	}

	
	/**
	 * FuncName: saveTaskPlanSubBySQL
	 * Description :  保存申报书明细对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午05:28:44
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午05:28:44
	 */
	@SuppressWarnings("unchecked")
	public void saveTaskPlanSubBySQL(final TaskPlanSub taskPlanSub) throws EsException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ECP_TASK_PLAN_SUB (REMARK,TASK_PLAN_SUB_ID,QUANTITY,UNIT,TOTALPRICE,PURCHASE_ID,BUDGET_NAME,PURCHASE_NAME,CREATE_USER)");	
			sql.append(" values('"+taskPlanSub.getRemark()+"','"+taskPlanSub.getObjId()+"','"+taskPlanSub.getQuantity().toString()+"','"+taskPlanSub.getUnit()+"',");
			sql.append(" '"+taskPlanSub.getTotalPrice().toString()+"','"+taskPlanSub.getPurchase().getObjId()+"','"+taskPlanSub.getBudgetName()+"','"+taskPlanSub.getPurchaseName()+"','"+taskPlanSub.getCreateUser().getObjId()+"')");
			Query query = session.createSQLQuery(sql.toString());	
			return query.executeUpdate();
			}
		});
	}

	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanSub> getTaskPlanSubList(final String objIds) throws Exception {
		return (List<TaskPlanSub>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from TaskPlanSub tps left join fetch tps.purchase where tps.objId in (:objId)");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("objId", objIds.split(","));
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Page<TaskPlanSub> getTaskPlanSub(final Page<TaskPlanSub> page,
			final Map<String, Object> paramsMap) throws Exception {
		return (Page<TaskPlanSub>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//获取查询条件
				String budgetId = (String) paramsMap.get("budgetId");
				String ebuyMethod = (String) paramsMap.get("ebuyMethod");
				String auditDetail = (String) paramsMap.get("auditDetail");
				String purCategoryObjId = (String) paramsMap.get("purCategoryObjId");
				String finQty = (String) paramsMap.get("finQty");
				
				StringBuilder hql = new StringBuilder();
				hql.append("from TaskPlanSub tps ");
				hql.append("     left join tps.taskPlanMSubs mSubs ");
				hql.append("     left join mSubs.taskPlan tskp ");
				hql.append("     where tskp.budget.objId = '" + budgetId + "' ");
				hql.append("           and tskp.ebuyMethod = '" + ebuyMethod + "'");
				hql.append("           and tskp.auditDetail = '" + auditDetail + "'");
				hql.append("           and tps.purchase.objId = '" + purCategoryObjId + "'");
				hql.append("           and tps.finQty != " + finQty + "");
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlanSub> taskPlanSubList=query.list();
				page.setData(taskPlanSubList);
				
				//查询总数
				String preHql = "select count(*) " + hql;
				query = session.createQuery(preHql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
			}
		});
	}
}
