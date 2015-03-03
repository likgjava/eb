package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class TaskPlanMSubDaoHibernate extends BaseGenericDaoHibernate<TaskPlanMSub> implements TaskPlanMSubDao {
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param orgId:机构ID,status:汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39   
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String orgId,String status, String taskType,String ebuyMethod){
		StringBuffer hql = new StringBuffer();
		hql.append("from TaskPlanMSub t ");
		hql.append("left join fetch t.taskPlan as p left join fetch t.taskPlanSub ");
		hql.append("where t.taskPlan.department.objId =? and t.taskPlan.budget.objId !=? ");
		hql.append("and p.useStatus =? and p.auditDetail=? and p.confirmStatus=?");
		hql.append("and t.taskPlanSub.objId not in ");
		hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("s.taskPlanSub.objId")+" from TaskPlanMSub s where t.taskPlan.department.objId =? and s.status =?) ");
		hql.append("and t.taskPlan.taskType =? and t.taskPlan.ebuyMethod =?");
		hql.append("order by p.createTime,p.objId");
		List<TaskPlanMSub> list = this.getHibernateTemplate().find(hql.toString(), new Object[]{orgId,orgId,CommonEnum.USER_STATUS_FORMAL,CommonEnum.CONFIRM_STATUS_WAIT,CommonEnum.CONFIRM_STATUS_WAIT,orgId,status,taskType,ebuyMethod});
		return list;
	}

	/** 
	 * FuncName:removeSumMSubs
	 * Description:根据taskPlan id,删除所属的申报书所有汇总的条目,不级联删除taskPlanSub
	 * @param taskPlanIds:申报书主键,status:汇总状态  
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	@SuppressWarnings("unchecked")
	public void removeSumMSubs(final String taskPlanIds, final String status) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append("delete from TaskPlanMSub t where taskPlanSub.objId in ");
				hql.append("(select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("p.taskPlanSub.objId")+" from TaskPlanMSub p where p.taskPlan.objId in (:planIds)) ");
				hql.append("and t.status =:status");
				Query query = session.createQuery(hql.toString());
				query.setParameterList("planIds", taskPlanIds.split(","));
				query.setString("status", status);
				return query.executeUpdate();
			}
		});
	}

	/** 
	 * FuncName:
	 * Description :  根据申报书条目ID删除对应的中间表数据
	 * @param taskPlanSubId:申报书条目主键
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午04:55:41 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-10下午04:55:41 
	 */
	@SuppressWarnings("unchecked")
	public void removeByTaskPlanSubId(final String taskPlanSubId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete from TaskPlanMSub c where c.taskPlanSub.objId =:taskPlanSubId";
				Query query = session.createQuery(hql);
				query.setString("taskPlanSubId", taskPlanSubId);
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * FuncName:getTaskPlanMSubByProjectId
	 * Description :  根据项目id获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键s
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-24下午01:10:46
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10   
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getTaskPlanMSubByProjectId(String projectId){
		if(projectId==null||"".equals(projectId)) return null;
		List<TaskPlanMSub> list = new ArrayList<TaskPlanMSub>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql1 = new StringBuffer();
		hql.append("from TaskPlanMSub t where t.taskPlanSub.objId in ");
		hql.append("(select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.taskPlanSub")+" from ProjectMTaskPlan tm where tm.tproject.objId = '"+projectId+"') and t.status='"+TaskPlanSumEnum.SUMMARY+"'");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql1.append("from TaskPlanMSub t where t.taskPlanSub.objId in");
		hql1.append("(select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm.taskPlanSub")+" from ProjectMTaskPlan tm where tm.tproject.objId = '"+projectId+"')");//tm.taskPlanSub.objId改为tm.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql1.append("  and t.status='"+TaskPlanSumEnum.NORMAL+"' and t.taskPlanSub.objId not in ");
		hql1.append(" (select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.taskPlanSub.objId")+" from TaskPlanMSub ts where ts.taskPlanSub.objId in ");
		hql1.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("tm1.taskPlanSub")+" from ProjectMTaskPlan tm1 where tm1.tproject.objId = '"+projectId+"')");//tm1.taskPlanSub.objId改为tm1.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql1.append(" and ts.status='"+TaskPlanSumEnum.SUMMARY+"')");
		hql.append("order by t.taskPlan.budgetName desc");
		hql1.append("order by t.taskPlan.budgetName desc");
		List<TaskPlanMSub> list1 = this.findbyHql(hql.toString());
		List<TaskPlanMSub> list2 = this.findbyHql(hql1.toString());
		/** 判断汇总的结果集是否存在 */
		if(list1!=null&&list1.size()>0){
			for(TaskPlanMSub taskPlanMSub1:list1){
				list.add(taskPlanMSub1);
			}
		}
		/** 判断没汇总的结果集是否存在 */
		if(list2!=null&&list2.size()>0){
			for(TaskPlanMSub taskPlanMSub2:list2){
				list.add(taskPlanMSub2);
			}
		}
		return list;
	}

	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description : 根据申报书获得申报书明细列表
	 * @param   taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57 
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-15上午09:34:57 
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String taskPlanId) {
		List list = this.findbyHql("select tms.taskPlanSub from TaskPlanMSub tms where tms.taskPlan.objId=?", taskPlanId);
		return list;
	}
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description:获取本级的申报书明细
	 * @param	taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21 
	 */  
	public List<TaskPlanMSub> getTaskPlanMSubByStatus(String taskPlanId) {
		String hql = "from TaskPlanMSub t where t.taskPlan.objId = ? and t.status = ?";
		return this.findbyHql(hql,taskPlanId,TaskPlanSumEnum.NORMAL);
	}
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreateProj
	 * Description :  得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param page:分页对象,taskAgentId:代理机构ID,ids:申报书条目主键,purCategoryId:品目主键,ebuyMethod:采购方式,taskType:申报书类型
	 * @return  Page<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-9-17上午10:57:56 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-17上午10:57:56  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlanMSub> getAllTaskPlanMSubForCreateProj(final Page page,final String taskAgentId,final String ids,final String purCategoryId,final String ebuyMethod,final String taskType){
		return (Page<TaskPlanMSub>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("select taskplanms0_.*,taskplan3_.CREATE_TIME,taskplan3_.TASK_PLAN_NAME from ECP_TASK_SUB taskplanms0_, ECP_TASK_PLAN taskplan3_");
				hql.append(" where taskplanms0_.TASK_PLAN_ID = taskplan3_.TASK_PLAN_ID");
				if (ids!=null&&!"".equals(ids)) {
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID not in ('"+ids+"')");
				}
				hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID in");
				hql.append(" (select consigntas1_.TASK_PLAN_SUB_ID from ECP_CONS_TASK_PLAN consigntas1_ where consigntas1_.CONS_ID in");
				hql.append(" (select consign2_.CONS_ID from ECP_CONSIGN consign2_ where consign2_.CONS_AGENT_ID = '"+taskAgentId+"' and consign2_.CONFIRM_STATUS = '"+CommonEnum.CONFIRM_STATUS_SURE+"'))");
				hql.append(" and taskplanms0_.TASK_M_SUB_ID not in ( ");
				hql.append(" select taskplanms1_.TASK_M_SUB_ID  from ECP_TASK_SUB taskplanms1_ where  taskplanms1_.TASK_PLAN_SUB_ID in (  ");
				hql.append(" select taskplanms2_.TASK_PLAN_SUB_ID  from  ECP_TASK_SUB taskplanms2_  where taskplanms2_.STATUS = '"+TaskPlanSumEnum.SUMMARY+"') and taskplanms1_.STATUS = '"+TaskPlanSumEnum.NORMAL+"' )");

				if (purCategoryId!=null&&!"".equals(purCategoryId)) {//判断是否有品目
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID in(");
					hql.append(" select tb.TASK_PLAN_SUB_ID from ECP_TASK_PLAN_SUB tb where tb.PURCHASE_ID = '"+purCategoryId+"')");
				}
				if (ebuyMethod!=null&&!"".equals(ebuyMethod)) {//判断是否有采购方式
					hql.append(" and taskplanms0_.TASK_PLAN_ID in(");
					hql.append(" select t.TASK_PLAN_ID from ECP_TASK_PLAN t where t.EBUY_METHOD = '"+ebuyMethod+"')");
				}
				if (taskType!=null&&!"".equals(taskType)) {//判断是否有申报书类型
					hql.append(" and taskplanms0_.TASK_PLAN_ID in(");
					hql.append(" select t.TASK_PLAN_ID from ECP_TASK_PLAN t where t.TASK_PLAN_TYPE = '"+taskType+"')");
				}
				hql.append("order by CREATE_TIME desc");
				Query query = session.createSQLQuery(hql.toString()).addEntity(TaskPlanMSub.class);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				page.setData(taskPlanMSubList);//数据
				//查询总数
				String preHql = "select count(*) from (";
				String endHql = ")";
				query = session.createSQLQuery(preHql + hql.toString()+endHql);
				page.setTotalRowNum(((BigDecimal)query.list().get(0)).intValue());
				return page;
		}});
	}
	
	/**
	 * FuncName:getTaskPlanMSubsByPackId
	 * Description: 根据包件ID获取所有申报书明细中间表
	 * @param packId
	 * @return List<TaskPlanSub>
	 * @author wanghz
	 * @Create Date 2010-10-8 上午10:51:10 
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getTaskPlanMSubsByPackId(final String packId){
		return (List<TaskPlanMSub>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				StringBuffer hql = new StringBuffer();//modify by yangx
				hql.append("select t.* from ECP_TASK_SUB t");
				hql.append(" where (t.TASK_PLAN_SUB_ID in");
				hql.append(" (select m.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK_P m where m.SUB_TENDERID = '"+packId+"')");
				hql.append(" or t.TASK_PLAN_SUB_ID in");
				hql.append(" (select em.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK em where em.TENDERID = '"+packId+"'))");
				hql.append(" and  t.status='"+TaskPlanSumEnum.SUMMARY+"'");
				hql.append(" union");
				hql.append(" select t.* from ECP_TASK_SUB t");
				hql.append(" where (t.TASK_PLAN_SUB_ID in");
				hql.append(" (select m.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK_P m where m.SUB_TENDERID = '"+packId+"')");
				hql.append(" or t.TASK_PLAN_SUB_ID in");
				hql.append(" (select em.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK em where em.TENDERID = '"+packId+"'))");
				hql.append(" and  t.status='"+TaskPlanSumEnum.NORMAL+"'");
				hql.append(" and t.TASK_PLAN_SUB_ID not in(");
				hql.append("select t.TASK_PLAN_SUB_ID from ECP_TASK_SUB t");
				hql.append(" where (t.TASK_PLAN_SUB_ID in");
				hql.append(" (select m.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK_P m where m.SUB_TENDERID = '"+packId+"')");
				hql.append(" or t.TASK_PLAN_SUB_ID in");
				hql.append(" (select em.TASK_PLAN_SUB_ID from ECP_TEND_M_TASK em where em.TENDERID = '"+packId+"'))");
				hql.append(" and  t.status='"+TaskPlanSumEnum.SUMMARY+"')");
				Query query = session.createSQLQuery(hql.toString()).addEntity(TaskPlanMSub.class);
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				return taskPlanMSubList;
			}
		});
	}
	
	/** 
	 * FuncName:getTaskPlanMSubByTaskPlanIdAndStatus
	 * Description :  根据申报书和汇总状态查询申报书、条目中间表
	 * @param   taskPlanId：申报书Id,status：状态
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-11-18下午04:01:52 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-18下午04:01:52 by 
	 */
	public List<TaskPlanMSub>  getTaskPlanMSubByTaskPlanIdAndStatus(String taskPlanId,String status){
		String hql = "From TaskPlanMSub t where t.taskPlan.objId='"+taskPlanId+"' and t.status='"+status+"'";
		return this.findbyHql(hql);
	}
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreatedProj
	 * Description :  查询已完成采购申报书
	 * @param  page:分页对象 createUser:创建人 purchaseName:品目名称 ebuyMethod:采购方式
	 * @return  Page<ProjectMTaskPlan> 
	 * @author shenjz
	 * @Create Date: 2010-11-22上午10:57:56 
	 * @Modifier shenjz
	 * @Modified Date: 2010-11-22上午10:57:56 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectMTaskPlan> getAllTaskPlanMSubForCreatedProj(final Page page,final String createUser,final String purchaseName,final String ebuyMethod){
		return (Page<ProjectMTaskPlan>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				StringBuffer hql2 = new StringBuffer();
				hql.append("select p.tproject.objId,p.tproject.projName,p.taskPlanSub,p.purchaseName,p.budgetMoney,t.taskPlan.objId,t.taskPlan.taskCode,t.taskPlan.applyDate,t.taskPlan.taskName,p.objId from ProjectMTaskPlan p,TaskPlanMSub t where p.buyMainBody.objId = '"+createUser+"'" );//p.taskPlanSub.objId改为p.taskPlanSub，p.taskPlanSub.purchaseName改为p.purchaseName，p.taskPlanSub.totalPrice改为p.budgetMoney  at 2011-6-23 15:08 by zhouzhanghe
				hql.append("and p.taskPlanSub=t.taskPlanSub.objId");//p.taskPlanSub.objId改为p.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
				hql.append(" and t.objId not in (select t3.objId from TaskPlanMSub t3 where t3.taskPlanSub.objId in (select t2.taskPlanSub.objId from TaskPlanMSub t2 where t2.status='"+TaskPlanSumEnum.SUMMARY+"') and t3.status ='"+TaskPlanSumEnum.NORMAL+"')");
				if(!purchaseName.trim().equals("")){
					hql.append("and p.purchaseName='"+purchaseName+"'");
				}
				if(!ebuyMethod.trim().equals("")){
					hql.append(" and p.tproject.ebuyMethod='"+ebuyMethod+"'");
				}
				hql.append(" order by t.taskPlan.applyDate desc");
				hql.append(" order by p.purchaseName asc");//p.taskPlanSub.purchaseName改为p.purchaseName at 2011-6-23 15:08 by zhouzhanghe
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List ProjectMTaskPlanList = query.list();
				List<ProjectMTaskPlan> ProjectMTaskPlanList2 = new ArrayList<ProjectMTaskPlan>();
				for (Iterator iterator = ProjectMTaskPlanList.iterator(); iterator
				.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					ProjectMTaskPlan pmt = new ProjectMTaskPlan();
					Project proj = new Project();
					proj.setObjId(object[0].toString());
					proj.setProjName(object[1].toString());
					TaskPlanSub tps = new TaskPlanSub();
					tps.setObjId(object[2].toString());
					tps.setPurchaseName(object[3].toString());
					tps.setTotalPrice(new BigDecimal((object[4] == null ? "0":object[4]).toString()));
					pmt.setPurchaseName(object[3].toString());
					pmt.setBudgetMoney(new BigDecimal((object[4] == null ? "0":object[4]).toString()));
					pmt.setTaskplanId(object[5].toString());
					pmt.setTaskcode(object[6].toString());
					pmt.setTaskName(object[8].toString());
					pmt.setProjectId(object[0].toString());
					pmt.setTaskPlanSubId(object[2].toString());
					pmt.setTproject(proj);
					pmt.setTaskPlanSub(tps.getObjId());
					if(object[7]!=null){
						pmt.setApplyDate(object[7].toString());
					}
					
					pmt.setObjId(object[9].toString());
					ProjectMTaskPlanList2.add(pmt);
				}
				page.setData(ProjectMTaskPlanList2);//数据
				hql2.append("select count(*) from ProjectMTaskPlan p,TaskPlanMSub t where p.buyMainBody.objId = '"+createUser+"'" );
				hql2.append("and p.taskPlanSub=t.taskPlanSub.objId");//p.taskPlanSub.objId改为p.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
				hql2.append(" and t.objId not in (select t3.objId from TaskPlanMSub t3 where t3.taskPlanSub.objId in (select t2.taskPlanSub.objId from TaskPlanMSub t2 where t2.status='"+TaskPlanSumEnum.SUMMARY+"') and t3.status ='"+TaskPlanSumEnum.NORMAL+"')");
				if(!purchaseName.trim().equals("")){
					hql2.append("and p.purchaseName='"+purchaseName+"'");
				}
				if(!ebuyMethod.trim().equals("")){
					hql2.append(" and p.tproject.ebuyMethod='"+ebuyMethod+"'");
				}
				query = session.createQuery(hql2.toString());
				page.setTotalRowNum(Integer.parseInt(query.list().get(0).toString()));
				return page;
		}});
	}
	
	/**
	 * FuncName:getTaskPlanMSubListByQueryObject
	 * Description :根据项目查询申报书与申报书条目中间表数据  
	 * @param   queryObject:组装的查询条件对象
	 * @return  List<TaskPlanMSub>
	 * @author liuke
	 * @Create Date: 2010-11-25上午10:38:53 
	 * @Modifier liuke
	 * @Modified Date: 2010-11-25上午10:38:53 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List<TaskPlanMSub> getTaskPlanMSubListByQueryObject(QueryObject queryObject){
		StringBuffer hql = new StringBuffer();
		String projectId= "";
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("projectId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					projectId = (String)queryParam.getValue();
				}
			}
		}
		hql.append("from TaskPlanMSub t1 where t1.taskPlanSub.objId in ( ");
		hql.append("select tt.taskPlanSub from ProjectMTaskPlan tt where tt.tproject.objId ='"+projectId+"')");//tt.taskPlanSub.objId改为tt.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql.append("and (( t1.status ='00'");
		hql.append("and t1.taskPlanSub.objId not in (select t.taskPlanSub.objId from TaskPlanMSub t where t.taskPlanSub.objId in (");
		hql.append("select tt.taskPlanSub from ProjectMTaskPlan tt where tt.tproject.objId ='"+projectId+"') and t.status = '01')) or t1.status = '01')");//tt.taskPlanSub.objId改为tt.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		List<TaskPlanMSub> TaskPlanMSubList = this.findbyHql(hql.toString());
		return TaskPlanMSubList;
	};
	
	/** 
	 * Description :  查询待立项的申报书条目数
	 * Create Date: 2011-1-22上午11:34:14 by yangx  Modified Date: 2011-1-22上午11:34:14 by yangx
	 * @param   taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @return  List<TaskPlanMSub>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getAllTaskPlanMSubForWaitCreateProj(final String taskAgentId,final String ids){
		return (List<TaskPlanMSub>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("select taskplanms0_.*,taskplan3_.CREATE_TIME,taskplan3_.TASK_PLAN_NAME from ECP_TASK_SUB taskplanms0_, ECP_TASK_PLAN taskplan3_");
				hql.append(" where taskplanms0_.TASK_PLAN_ID = taskplan3_.TASK_PLAN_ID");
				if (ids!=null&&!"".equals(ids)) {
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID not in ('"+ids+"')");
				}
				hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID in");
				hql.append(" (select consigntas1_.TASK_PLAN_SUB_ID from ECP_CONS_TASK_PLAN consigntas1_ where consigntas1_.CONS_ID in");
				hql.append(" (select consign2_.CONS_ID from ECP_CONSIGN consign2_ where consign2_.CONS_AGENT_ID = '"+taskAgentId+"' and consign2_.CONFIRM_STATUS = '"+CommonEnum.CONFIRM_STATUS_SURE+"'))");
				hql.append(" and taskplanms0_.TASK_M_SUB_ID not in ( ");
				hql.append(" select taskplanms1_.TASK_M_SUB_ID  from ECP_TASK_SUB taskplanms1_ where  taskplanms1_.TASK_PLAN_SUB_ID in (  ");
				hql.append(" select taskplanms2_.TASK_PLAN_SUB_ID  from  ECP_TASK_SUB taskplanms2_  where taskplanms2_.STATUS = '"+TaskPlanSumEnum.SUMMARY+"') and taskplanms1_.STATUS = '"+TaskPlanSumEnum.NORMAL+"' )");

				hql.append("order by CREATE_TIME desc");
				Query query = session.createSQLQuery(hql.toString()).addEntity(TaskPlanMSub.class);
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				//查询总数
				String preHql = "select count(*) from (";
				String endHql = ")";
				query = session.createSQLQuery(preHql + hql.toString()+endHql);
				return taskPlanMSubList;
		}});
	}

	/**
	 * FuncName: getTaskPlanMSubForECPCreateProj
	 * Description :得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param page:分页对象,taskAgentId:代理机构id,purCategoryId:品目Id,ebuyMethod:采购方式,taskType:申报书类型
	 * @param page
	 * @param taskAgentId
	 * @param ids
	 * @param purCategoryId
	 * @param ebuyMethod
	 * @param taskType
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-3-1  上午11:57:18
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  上午11:57:18
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlanMSub> getTaskPlanMSubForECPCreateProj(final Page page,final String taskAgentId,final String purCategoryId,final String ebuyMethod,final String taskType) {
		return (Page<TaskPlanMSub>) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Page<TaskPlanMSub> doInHibernate(Session session) throws HibernateException,SQLException {
				String preHql = "select m.* from ";
				StringBuffer hql = new StringBuffer("  ECP_TASK_SUB m, ECP_TASK_PLAN p where m.TASK_PLAN_ID = p.TASK_PLAN_ID");
				hql.append(" AND NOT EXISTS (");
				hql.append(" SELECT 1 FROM ECP_TASK_PLAN_SUB S WHERE s.TASK_PLAN_SUB_ID =M.TASK_PLAN_SUB_ID ");
				hql.append(" AND EXISTS (SELECT 1 FROM ECP_TEND_M_TASK T WHERE T.TASK_PLAN_SUB_ID=S.TASK_PLAN_SUB_ID)");
				hql.append(" )");
				hql.append(" AND p.AGENTY_ID = '"+taskAgentId+"'");//代理机构ID
				hql.append(" AND p.CONFIRM_STATUS = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND p.AUDIT_DETAIL = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND P.USE_STATUS = '"+CommonEnum.USER_STATUS_FORMAL+"'");
				if (purCategoryId!=null&&!"".equals(purCategoryId)) {//判断是否有品目
					hql.append(" AND EXISTS (");
					hql.append(" SELECT 1 FROM ECP_TASK_PLAN_SUB S WHERE S.task_plan_sub_id = M.task_plan_sub_id AND S.PURCHASE_ID='"+purCategoryId+"'");
					hql.append(" )");
				}
				if (ebuyMethod!=null&&!"".equals(ebuyMethod)) {//判断是否有采购方式
					hql.append(" AND EXISTS (");
					hql.append(" SELECT 1 FROM ECP_TASK_PLAN TP WHERE TP.TASK_PLAN_ID=P.TASK_PLAN_ID AND TP.EBUY_METHOD='"+ebuyMethod+"'");
					hql.append(" )");
				}
				if (taskType!=null&&!"".equals(taskType)) {//判断是否有申报书类型
					hql.append(" AND EXISTS (");
					hql.append(" SELECT 1 FROM ECP_TASK_PLAN TP WHERE TP.TASK_PLAN_ID=P.TASK_PLAN_ID AND TP.TASK_PLAN_TYPE='"+taskType+"'");
					hql.append(" )");
				}
				hql.append("order by CREATE_TIME desc");
				Query query = session.createSQLQuery(preHql+hql.toString()).addEntity(TaskPlanMSub.class);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				page.setData(taskPlanMSubList);//数据
				//查询总数
				preHql = "select count(m.task_m_sub_id) from ";
				query = session.createSQLQuery(preHql + hql.toString());
				page.setTotalRowNum(((BigDecimal)query.list().get(0)).intValue());
				return page;
			}});
		
	}

	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param 
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午02:30:34
	 * @Modified Date: 2011-10-19下午01:28:09 by yangx
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getTaskPlanMSubForECPWaitCreateProj(final	String taskAgentId) {
		return (BigDecimal) getHibernateTemplate().execute(new HibernateCallback(){
			public BigDecimal doInHibernate(Session session) throws HibernateException, SQLException {
				/** 修改查询条件与关键字 modify by yangx */
				StringBuffer hql = new StringBuffer("SELECT COUNT(M.TASK_PLAN_SUB_ID) FROM ECP_TASK_SUB M, ECP_TASK_PLAN P ");
				hql.append(" WHERE M.TASK_PLAN_ID = P.TASK_PLAN_ID");
				hql.append(" AND  NOT EXISTS (");
				hql.append(" SELECT 1 FROM ECP_TASK_PLAN_SUB S WHERE s.TASK_PLAN_SUB_ID =M.TASK_PLAN_SUB_ID AND EXISTS (");
				hql.append(" SELECT 1 FROM ECP_TEND_M_TASK T WHERE T.TASK_PLAN_SUB_ID=S.TASK_PLAN_SUB_ID)) ");
				hql.append(" AND P.CONFIRM_STATUS = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND P.AUDIT_DETAIL = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND P.USE_STATUS = '"+CommonEnum.USER_STATUS_FORMAL+"'");
				hql.append(" AND P.AGENTY_ID = '"+taskAgentId+"'");
				Query query = session.createSQLQuery(hql.toString());
				List taskPlanIds = query.list();
				return new BigDecimal(taskPlanIds.get(0).toString());
		}});
	}

	
	/**
	 * FuncName: getTaskPlanMSubByTaskPlanSubId
	 * Description : 根据申报书条目主键得到申报书条目与申报书中间表对象
	 * @param 
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午06:29:50
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午06:29:50
	 */
	public List<TaskPlanMSub> getTaskPlanMSubByTaskPlanSubId(String taskPlanSubId) {
		return this.findbyHql("from TaskPlanMSub t where t.taskPlanSub.objId in ('"+taskPlanSubId+"')");
	}

	/**
	 * FuncName: saveTaskPlanMSubBySQL
	 * Description :  保存申报书明细对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午05:28:44
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午05:28:44
	 */
	@SuppressWarnings("unchecked")
	public void saveTaskPlanMSubBySQL(final TaskPlanMSub taskPlanMSub)
			throws EsException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ECP_TASK_SUB (TASK_M_SUB_ID,TASK_PLAN_ID,TASK_PLAN_SUB_ID,STATUS)");	
			sql.append(" values('"+taskPlanMSub.getObjId()+"','"+taskPlanMSub.getTaskPlan().getObjId()+"','"+taskPlanMSub.getTaskPlanSub().getObjId()+"','"+taskPlanMSub.getStatus()+"')");
			Query query = session.createSQLQuery(sql.toString());	
			return query.executeUpdate();
			}
		});
	}

	/**
	 * FuncName: getTaskPlanMSubForECPCreateProj
	 * Description :得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param page:分页对象,taskAgentId:代理机构id,purCategoryId:品目Id,ebuyMethod:采购方式,taskType:申报书类型
	 * @param page
	 * @param taskAgentId
	 * @param ids
	 * @param purCategoryId
	 * @param ebuyMethod
	 * @param taskType
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-3-1  上午11:57:18
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  上午11:57:18
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlanMSub> getTaskPlanMSubForECPCreateProj(final Page page,final String taskAgentId,
		final String ids,final String purCategoryId,final String ebuyMethod,final String taskType) {
	return (Page<TaskPlanMSub>) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Page<TaskPlanMSub> doInHibernate(Session session) throws HibernateException,SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("select taskplanms0_.*,taskplan3_.CREATE_TIME,taskplan3_.TASK_PLAN_NAME from ECP_TASK_SUB taskplanms0_, ECP_TASK_PLAN taskplan3_");
				hql.append(" where taskplanms0_.TASK_PLAN_ID = taskplan3_.TASK_PLAN_ID");
				if (ids!=null&&!"".equals(ids)) {
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID not in ('"+ids+"')");
				}
				hql.append(" and taskplan3_.AGENTY_ID = '"+taskAgentId+"'");    //判断该申报书分给哪个代理机构
				hql.append(" and taskplan3_.CONFIRM_STATUS = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");   //待审核资金的申报书
				hql.append(" and taskplan3_.AUDIT_DETAIL = '"+TaskPlanConfirmEnum.AUDIT_PASS+"'");       //待审核资金的申报书
				
				hql.append(" and taskplanms0_.TASK_M_SUB_ID not in ( ");
				hql.append(" select taskplanms1_.TASK_M_SUB_ID  from ECP_TASK_SUB taskplanms1_ where  taskplanms1_.TASK_PLAN_SUB_ID in (  ");
				hql.append(" select taskplanms2_.TASK_PLAN_SUB_ID  from  ECP_TASK_SUB taskplanms2_  where taskplanms2_.STATUS = '"+TaskPlanSumEnum.SUMMARY+"') and taskplanms1_.STATUS = '"+TaskPlanSumEnum.NORMAL+"' )");
				if (purCategoryId!=null&&!"".equals(purCategoryId)) {//判断是否有品目
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID in(");
					hql.append(" select tb.TASK_PLAN_SUB_ID from ECP_TASK_PLAN_SUB tb where tb.PURCHASE_ID = '"+purCategoryId+"')");
				}
				if (ebuyMethod!=null&&!"".equals(ebuyMethod)) {//判断是否有采购方式
					hql.append(" and taskplanms0_.TASK_PLAN_ID in(");
					hql.append(" select t.TASK_PLAN_ID from ECP_TASK_PLAN t where t.EBUY_METHOD = '"+ebuyMethod+"')");
				}
				if (taskType!=null&&!"".equals(taskType)) {//判断是否有申报书类型
					hql.append(" and taskplanms0_.TASK_PLAN_ID in(");
					hql.append(" select t.TASK_PLAN_ID from ECP_TASK_PLAN t where t.TASK_PLAN_TYPE = '"+taskType+"')");
				}
				hql.append("order by CREATE_TIME desc");
				Query query = session.createSQLQuery(hql.toString()).addEntity(TaskPlanMSub.class);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				page.setData(taskPlanMSubList);//数据
				//查询总数
				String preHql = "select count(*) from (";
				String endHql = ")";
				query = session.createSQLQuery(preHql + hql.toString()+endHql);
				page.setTotalRowNum(((BigDecimal)query.list().get(0)).intValue());
				return page;
			}});
		
	}

	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param 
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午02:30:34
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午02:30:34
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanMSub> getTaskPlanMSubForECPWaitCreateProj(
		final	String taskAgentId, final String ids) {
		return (List<TaskPlanMSub>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("select taskplanms0_.*,taskplan3_.CREATE_TIME,taskplan3_.TASK_PLAN_NAME from ECP_TASK_SUB taskplanms0_, ECP_TASK_PLAN taskplan3_");
				hql.append(" where taskplanms0_.TASK_PLAN_ID = taskplan3_.TASK_PLAN_ID");
				if (ids!=null&&!"".equals(ids)) {
					hql.append(" and taskplanms0_.TASK_PLAN_SUB_ID not in ('"+ids+"')");
				}
				hql.append(" and taskplan3_.AGENTY_ID = '"+taskAgentId+"'");    //判断该申报书分给哪个代理机构
				hql.append(" and taskplan3_.CONFIRM_STATUS = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");   //待审核资金的申报书
				hql.append(" and taskplan3_.AUDIT_DETAIL = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");       //待审核资金的申报书
	
				hql.append(" and taskplanms0_.TASK_M_SUB_ID not in ( ");
				hql.append(" select taskplanms1_.TASK_M_SUB_ID  from ECP_TASK_SUB taskplanms1_ where  taskplanms1_.TASK_PLAN_SUB_ID in (  ");
				hql.append(" select taskplanms2_.TASK_PLAN_SUB_ID  from  ECP_TASK_SUB taskplanms2_  where taskplanms2_.STATUS = '"+TaskPlanSumEnum.SUMMARY+"') and taskplanms1_.STATUS = '"+TaskPlanSumEnum.NORMAL+"' )");
	
				hql.append("order by CREATE_TIME desc");
				Query query = session.createSQLQuery(hql.toString()).addEntity(TaskPlanMSub.class);
				List<TaskPlanMSub> taskPlanMSubList=query.list();
				//查询总数
				String preHql = "select count(*) from (";
				String endHql = ")";
				query = session.createSQLQuery(preHql + hql.toString()+endHql);
				return taskPlanMSubList;
		}});
	}
}
