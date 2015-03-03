package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.math.BigDecimal;
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
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;


@Repository
public class TaskPlanDaoHibernate extends BaseGenericDaoHibernate<TaskPlan> implements TaskPlanDao {

	/**
	 * FuncName:submitTaskPlan
	 * Description :  提交审核采购计划
	 * @param   objIds:以逗号分割的id,useStatus:使用状态,confirmStatus:任务书审核状态,auditDetail:资金审核状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43    
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43  
	 */
	@SuppressWarnings("unchecked")
	public void submitTaskPlan(final String objIds,final String useStatus,final String confirmStatus,final String auditDetail) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update TaskPlan set";
				if(useStatus != null && useStatus.length() != 0){//使用状态
					hql +=	" useStatus =:useStatus ,";
				}
				if(confirmStatus != null && confirmStatus.length() != 0){  //任务书审核状态
					hql += " confirmStatus =:confirmStatus ,";
				}
				if(auditDetail != null && auditDetail.length() != 0){ //资金审核状态
					hql += " auditDetail =:auditDetail";
				}
				if(hql.endsWith(",")){
					hql = hql.substring(0,hql.length()-1);
				}
				hql += " where objId in (:objIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("objIds", objIds.split(","));
				if(useStatus != null && useStatus.length() != 0){
					query.setString("useStatus", useStatus);
				}
				if(confirmStatus != null && confirmStatus.length() != 0){
					query.setString("confirmStatus", confirmStatus);
				}
				if(auditDetail != null && auditDetail.length() != 0){
					query.setString("auditDetail", auditDetail);
				}
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构id,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getSubNotSumSubsByOrg(final Page<TaskPlan> page,final String orgId,final String status,final String taskCode,final String applyDate)  throws Exception{
		return (Page<TaskPlan>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//查询分页记录
				String preHql=" from TaskPlan t where t.objId in( ";
				StringBuffer hql = new StringBuffer();
				hql.append("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.taskPlan.objId")+" from TaskPlanMSub ts where ts.taskPlanSub.objId not in ( " +
						"select tms.taskPlanSub.objId from TaskPlanMSub tms where tms.status = '"+ status +"'" +
						" or tms.taskPlanSub.createUser.objId = '"+AuthenticationHelper.getCurrentUser().getObjId()+"'" +
						" or tms.taskPlan.budget.objId = '"+orgId+"') " +
						") and t.department.objId = '"+ orgId +"' and " +
						"t.useStatus = '"+ CommonEnum.USER_STATUS_FORMAL +"' and " +
						"t.auditDetail = '"+ CommonEnum.CONFIRM_STATUS_WAIT +"' and " +
						"t.confirmStatus = '" + CommonEnum.CONFIRM_STATUS_WAIT +"' " );
				if(taskCode!=null&&!"".equals(taskCode)){
					hql.append(" and t.taskCode like '%" + taskCode +"%' ");
				}
				if(applyDate!=null&&!"".equals(applyDate)){
					hql.append(" and to_char(t.applyDate,'yyyy-mm-dd') = '"+applyDate+"'");
				}
				hql.append(" order by t.taskCode desc, t.createTime asc ");
				
				Query query = session.createQuery(preHql + hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlan> taskList=query.list();
				page.setData(taskList);
				//查询总数
				preHql = "select count(t.objId) from TaskPlan t where t.objId in( ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构id,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getSubAllNotSumSubsByOrg(final Page<TaskPlan> page,final String orgId,final String status,final String taskCode,final String applyDate)  throws Exception{
		return (Page<TaskPlan>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//查询分页记录
				String preHql=" from TaskPlan t where t.objId in( ";
				StringBuffer hql = new StringBuffer();
				hql.append("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.taskPlan.objId")+" from TaskPlanMSub ts where ts.taskPlanSub.objId not in ( " +
						"select tms.taskPlanSub.objId from TaskPlanMSub tms where tms.status = '"+ status +"'" +
						" or tms.taskPlanSub.createUser.objId = '"+AuthenticationHelper.getCurrentUser().getObjId()+"'" +
						" or tms.taskPlan.budget.objId = '"+orgId+"') " +
						") and t.department.objId = '"+ orgId +"' " );
				if(taskCode!=null&&!"".equals(taskCode)){
					hql.append(" and t.taskCode like '%" + taskCode +"%' ");
				}
				if(applyDate!=null&&!"".equals(applyDate)){
					hql.append(" and to_char(t.applyDate,'yyyy-mm-dd') = '"+applyDate+"'");
				}
				hql.append(" order by t.taskCode desc, t.createTime asc ");
				
				Query query = session.createQuery(preHql + hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List<TaskPlan> taskList=query.list();
				page.setData(taskList);
				//查询总数
				preHql = "select count(t.objId) from TaskPlan t where t.objId in( ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrgNum
	 * Description : 根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:机构ID,status:汇总状态
	 * @return BigDecimal
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getSubNotSumSubsByOrgNum(final String orgId,final String status)  throws Exception{
		return (BigDecimal) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//查询分页记录
				String preHql = "select count(t.objId) from TaskPlan t where t.objId in( "; //查询总数
				StringBuffer hql = new StringBuffer();
				hql.append("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("ts.taskPlan.objId")+" from TaskPlanMSub ts where ts.taskPlanSub.objId not in ( " +
						"select tms.taskPlanSub.objId from TaskPlanMSub tms where tms.status = '"+ status +"'" +
						" or tms.taskPlanSub.createUser.objId = '"+AuthenticationHelper.getCurrentUser().getObjId()+"'" +
						" or tms.taskPlan.budget.objId = '"+orgId+"') " +
						") and t.department.objId = '"+ orgId +"' and " +
						"t.useStatus = '"+ CommonEnum.USER_STATUS_FORMAL +"' and " +
						"t.auditDetail = '"+ CommonEnum.CONFIRM_STATUS_WAIT +"' and " +
						"t.confirmStatus = '" + CommonEnum.CONFIRM_STATUS_WAIT +"' " );
				hql.append(" order by t.taskCode desc, t.createTime asc ");
				Query	query = session.createQuery(preHql + hql.toString());
				return  new BigDecimal(query.list().get(0).toString());
		}});
	}

	/** 
	 * Funcname:getTaskPlanTotalByQueryObject
	 * Description :  根据查询条件统计所有的申报书数据
	 * @param queryObject[taskType:类型，useStatus:使用状态,createUser:采购人帐号ID,confirmStatus:确认状态,auditDetail:审核状态,governmentId:部门ID,leader:负责人ID]
	 * @return BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午04:02:54 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-7下午04:02:54  
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getTaskPlanTotalByQueryObject(QueryObject queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(t) from TaskPlan t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("leader".equals(queryParam.getName())){
					hql.append(" and t.leader.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("governmentId".equals(queryParam.getName())){
					hql.append(" and t.government.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("useStatus".equals(queryParam.getName())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"' ");
				};
				if("auditDetail".equals(queryParam.getName())){
					hql.append(" and t.auditDetail='"+(String)queryParam.getValue()+"' ");
				}
				if("confirmStatus".equals(queryParam.getName())){
					hql.append(" and t.confirmStatus='"+(String)queryParam.getValue()+"' ");
				}
				if("taskAgentId".equals(queryParam.getName())){
					hql.append("and t.taskAgent.objId = '"+(String)queryParam.getValue()+"'");
				}
				if("taskType".equals(queryParam.getName())){
					hql.append(" and t.taskType in ('"+(String)queryParam.getValue()+"') ");
				}
				if("isSubmit".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					if(!"yes".equals(queryParam.getValue())){
					hql.append(" and t.objId in ");
					hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("t1.taskPlan.objId")+" from TaskPlanMSub t1,TaskPlanSub t2 where t1.taskPlanSub.objId=t2.objId and t1.taskPlan.objId=t.objId and t2.createUser.objId='"+(String)queryParam.getValue()+"' ) ");
					}
				}
				if("createUser".equals(queryParam.getName())){
				hql.append(" and createUser.objId = '"+(String)queryParam.getValue()+"'");
			}
			}
		}
		List<QueryParam> queryOrList = queryObject.getQueryParam().getOrParams();
		if(queryOrList != null && !queryOrList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryOrList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryOrList.get(i);
				hql.append(" and "+queryParam.toString()+" ");
			}
		}
		List list=this.findbyHql(hql.toString());
		Object object = list.get(0);
		return new BigDecimal(object.toString());
	}

	/**
	 * FuncName: getTaskPlanPageByQueryObject
	 * Description:通过查询对象获得对应的申报书列表
	 * @param queryObject:拼装的对象,page:分页对象
	 * @return Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-7-13下午01:09:32 
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQueryObject(QueryObject queryObject, Page<TaskPlan> page) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from TaskPlan t where 1=1  ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		//modify by yangx
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("taskCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.taskCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("taskName".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.taskName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("leader".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.leader.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("governmentId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.government.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.useStatus='"+(String)queryParam.getValue()+"' ");
				};
				if("auditDetail".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.auditDetail='"+(String)queryParam.getValue()+"' ");
				}
				if("confirmStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.confirmStatus='"+(String)queryParam.getValue()+"' ");
				}
				if("taskType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.taskType = '"+(String)queryParam.getValue()+"'");
				}
				if("isSubmit".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					if(!"yes".equals(queryParam.getValue())){
					hql.append(" and t.objId in ");
					hql.append(" (select distinct "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("t1.taskPlan.objId")+" from TaskPlanMSub t1,TaskPlanSub t2 where t1.taskPlanSub.objId=t2.objId and t1.taskPlan.objId=t.objId and t2.createUser.objId='"+(String)queryParam.getValue()+"' ) ");
					}
				}
				if("createUser".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and createUser.objId = '"+(String)queryParam.getValue()+"'");
				}
				if("taskAgentId".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and taskAgent.objId = '"+(String)queryParam.getValue()+"'");
				}
				
		}
		List<QueryParam> queryOrList = queryObject.getQueryParam().getOrParams();
		if(queryOrList != null && !queryOrList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryOrList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryOrList.get(i);
				hql.append(" and "+queryParam.toString()+" ");
			}
		}
		}
		hql.append(" order by createTime desc,taskCode desc ");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), TaskPlan.class);
	}

	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表
	 * @param   page:分页对象,queryObject:查询的条件对象
	 * @return  Page
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-8上午09:39:48 
	 */
	public Page getTaskPlanSubListByNotblockAndPass(Page page,
			QueryObject<TaskPlanSub> queryObject) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from TaskPlanSub t where 1=1 ");
		StringBuffer subHql = new StringBuffer(" select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("t1.taskPlanSub.objId")+" from TaskPlan t0, TaskPlanMSub t1 where t0.objId=t1.taskPlan.objId  ");
				//     subHql.append(" and t0.confirmStatus='"+TaskPlanConfirmEnum.SELECT_AGENT_PASS+"' ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("taskCode".equals(queryParam.getName())){
					subHql.append(" and t0.taskCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("taskName".equals(queryParam.getName())){
					subHql.append(" and t0.taskName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("taskAgentId".equals(queryParam.getName())){
					subHql.append(" and t0.taskAgent.objId='"+(String)queryParam.getValue()+"' ");
				}
				if("budgetName".equals(queryParam.getName())){
					hql.append(" and t.budgetName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("purchaseName".equals(queryParam.getName())){
					hql.append(" and t.purchaseName like '%"+(String)queryParam.getValue()+"%' ");
				}
				if("taskPlanSubIds".equals(queryParam.getName()) && queryParam.getValue() != null && !"".equals(queryParam.getValue())){
					String ids = ((String)queryParam.getValue()).replace(",", "','");
					hql.append(" and t.objId in ( '"+ids+"') ");
				}
				if("taskPlanSubIds_not".equals(queryParam.getName()) && queryParam.getValue() != null && !"".equals(queryParam.getValue())){
					String ids = ((String)queryParam.getValue()).replace(",", "','");
					hql.append(" and t.objId not in ( '"+ids+"') ");
				}
			}
		}
		hql.append("and t.objId in ("+subHql.toString()+")");
		hql.append(" order by purchaseName desc,budgetName desc ");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), TaskPlanSub.class);
	}

	/** 
	 * FuncName:getTaskPlanListByTaskPlanIds
	 * Description:通过申报书主键得到申报书列表
	 * @param TaskPlanIds:以逗号分隔的多个申报书主键
	 * @return List<TaskPlan>
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * @Modifier   liuy
	 * @Modified Date: 2010-7-15下午02:25:38
	 */
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds) {
		TaskPlanIds = TaskPlanIds.replaceAll(",", "','");
		TaskPlanIds ="'" +TaskPlanIds+"'";
		List<TaskPlan> list = this.findbyHql("from TaskPlan tp where tp.objId in ("+TaskPlanIds+")");
		return list;
	}

	/** 
	 * FuncName:getTaskPlanSubListByConfirmConsign
	 * Description :  得到所有已确认委托协议后的申报书明细列表
	 * @param page:分页对象,queryObject:查询的条件对象
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	@SuppressWarnings("unchecked")
	public Page getTaskPlanSubListByConfirmConsign(final Page page,final String budgetName,final String purchaseName,final String ebuyMethod,final String taskType,final String taskPlanSubIds_not,final String agentId){
		return (Page) getHibernateTemplate().execute(new HibernateCallback(){
			public Page doInHibernate(Session session) throws HibernateException, SQLException {
				String preHql = "select s.* from ";
				StringBuffer hql = new StringBuffer();
				hql.append(" ECP_TASK_SUB m, ECP_TASK_PLAN p,ecp_task_plan_sub s");
				hql.append(" where m.TASK_PLAN_ID = p.TASK_PLAN_ID AND M.TASK_PLAN_SUB_ID=s.task_plan_sub_id ");
				hql.append(" AND NOT EXISTS");
				hql.append(" (SELECT 1  FROM ECP_TEND_M_TASK T WHERE T.TASK_PLAN_SUB_ID = S.TASK_PLAN_SUB_ID)");
				if (taskType!=null&&!"".equals(taskType)) {//申报书类型
					hql.append(" AND EXISTS ");
					hql.append(" (SELECT 1 FROM ECP_TASK_PLAN TP WHERE TP.TASK_PLAN_ID=P.TASK_PLAN_ID AND TP.TASK_PLAN_TYPE='"+taskType+"')");
				}
				if (agentId!=null&&!"".equals(agentId)) {//代理机构id
					hql.append(" and p.AGENTY_ID = '"+agentId+"'");
				}
//				if (ebuyMethod!=null&&!"".equals(ebuyMethod)) {//采购方式
//					hql.append(" and p.EBUY_METHOD = '"+ebuyMethod+"'");
//				}
				if (taskPlanSubIds_not!=null&&!"".equals(taskPlanSubIds_not)) {//已选择的申报书条目IDS
					hql.append(" and s.task_plan_sub_id not in('"+taskPlanSubIds_not+"')");
				}
				if (budgetName!=null&&!"".equals(budgetName)) {//预算单位名称
					hql.append(" and p.BUDGET_NAME = '"+budgetName+"'");
				}
				if (purchaseName!=null&&!"".equals(purchaseName)) {//采购品目
					hql.append(" AND S.PURCHASE_NAME like '%"+purchaseName+"%'");
				}
				hql.append(" AND p.CONFIRM_STATUS = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND p.AUDIT_DETAIL = '"+TaskPlanConfirmEnum.WAIT_AUDIT+"'");
				hql.append(" AND P.USE_STATUS = '"+CommonEnum.USER_STATUS_FORMAL+"'");
				
				Query query = session.createSQLQuery(preHql+hql.toString()).addEntity(TaskPlanSub.class);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<TaskPlanSub> taskPlanSubList=query.list();
				page.setData(taskPlanSubList);//数据
				
				preHql = "select count(s.task_plan_sub_id) from ";
				query = session.createSQLQuery(preHql + hql.toString());
				page.setTotalRowNum(((BigDecimal)query.list().get(0)).intValue());
				return  page;
		}});
	}
	
	/** 
	 * FuncName:getTaskPlanPageByQO
	 * Description :  获取待处理采购申报书
	 * @param   QueryObject[taskType:申报书类型,useStatus:使用状态,confirmStatus:确认状态,block_check_status:大宗审核状态,taskCode：申报书编号,applyDate:日期],page:分页对象
	 * @return  Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-15下午07:04:45 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-15下午07:04:45  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQO(QueryObject queryObject,Page page){
		StringBuffer hql = new StringBuffer();
		hql.append("from TaskPlan t where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				/** 申报书编号 */
				if("taskCode".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.taskCode like '%"+(String)queryParam.getValue()+"%' ");
				}
				/** 日期 */
				if("applyDate".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().date2String("t.applyDate")+" ='"+(String)queryParam.getValue()+"' ");
				}
				/** 申报书类型 */
				if("taskType".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.taskType = '"+(String)queryParam.getValue()+"' ");
				}
				/** 申报书使用状态 */
				if("useStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.useStatus = '"+(String)queryParam.getValue()+"' ");
				}
				/** 申报书确认状态 */
				if("confirmStatus".equals(queryParam.getName())&&queryParam.getValue()!=null&&!"".equals(queryParam.getValue())){
					hql.append(" and t.confirmStatus = '"+(String)queryParam.getValue()+"' ");
				}
				/** 申报书确认状态 */
				if("block_check_status".equals(queryParam.getName())){
					hql.append(" and t.block_check_status is null ");
				}
			}
		}
		hql.append(" order by t.applyDate desc ");
		return this.findbyHql(hql.toString(), page.getStart(), page.getPageSize(), TaskPlan.class);
	}
	
	/**
	 * FuncName:getTaskPlanSubInProject
	 * Description : 得到被列入项目中的申报书条目列表
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-7-16上午10:57:17 
	 * @Modifier liuke
	 * @Modified Date: 2010-7-16上午10:57:17 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanSubInProject() {
		/*
		 * @Modified Date: 2011-6-23 14:10 by zhouzhanghe
		 * old:List list= this.findbyHql("select distinct pmtp.taskPlanSub from ProjectMTaskPlan pmtp");
		 */
		List list= this.findbyHql("select tps from TaskPlanSub tps where tps.objId in(select pmtp.taskPlanSub from ProjectMTaskPlan pmtp)");
		
		return list;
	}
	
	/** 
	 * FuncName:getTaskPlanSubInProject
	 * Description :  得到被列入项目中的申报书条目列表
	 * @param   projectId:项目主键
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-9-2下午05:31:40 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-2下午05:31:40 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanSubInProject(String projectId){
		
		/*
		 * @Modified Date: 2011-6-23 14:10 by zhouzhanghe
		 * old:"select distinct pmtp.taskPlanSub from ProjectMTaskPlan pmtp where pmtp.tproject != '"+projectId+"'"
		 */
		return this.findbyHql("select tps from TaskPlanSub tps where tps.objId in(select pmtp.taskPlanSub from ProjectMTaskPlan pmtp where pmtp.tproject.objId = ?)", new Object[]{projectId});
	}
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return List
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38  
	 */
	@SuppressWarnings("unchecked")
	public List getNoSetupProjectLeader(final String type)throws EsException{
		return (List)getHibernateTemplate().execute(new HibernateCallback(){
			public List doInHibernate(Session session)throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				if ("count".equals(type)) {
					hql.append(" select count(task.objId) ");
				}
				hql.append(" from TaskPlan as task ");
				hql.append(" where task.auditDetail=:auditDetail ");
				hql.append(" and task.confirmStatus=:confirmStatus ");
				hql.append(" and task.leader is null ");
				hql.append(" order by task.taskCode desc,task.createTime desc ");
				if ("data".equals(type)) {
					return  session.createQuery(hql.toString())
					.setString("auditDetail", CommonEnum.CONFIRM_STATUS_SURE)
					.setString("confirmStatus", CommonEnum.CONFIRM_STATUS_NEGOTIATE)
					.setFirstResult(0)
					.setMaxResults(5)
					.list();
				}
				return session.createQuery(hql.toString())
				.setString("auditDetail", CommonEnum.CONFIRM_STATUS_SURE)
				.setString("confirmStatus", CommonEnum.CONFIRM_STATUS_NEGOTIATE)
				.list();
			}});
	}

	/**
	 * 
	 * FuncName: getTaskPlanListByObjId
	 * Description :  创建或修改对象
	 * @param 
	 * @param objId
	 * @return List<TaskPlan>
	 * @author: liuke
	 * @Create Date:2010-12-23  上午10:54:38
	 * @Modifier: liuke
	 * @Modified Date:2010-12-23  上午10:54:38
	 */
	public List<TaskPlan> getTaskPlanListByObjId(String objId) {
		return this.findbyHql("from TaskPlan tp where tp.objId = ?", objId);
	}

	/**
	 * 
	 * FuncName: getTaskPlanBySubsAndStatus
	 * Description : 根据申报书明细和审核状态查询申报书列表 
	 * @param subIds
	 * @param status
	 * @return List<TaskPlan>
	 * @author: liuke
	 * @Create Date:2010-12-27  下午07:03:30
	 * @Modifier: liuke
	 * @Modified Date:2010-12-27  下午07:03:30
	 */
	public List<TaskPlan> getTaskPlanBySubsAndStatus(String subIds,
			String status) {
		return this.findbyHql("select t.taskPlan from TaskPlanMSub t where t.taskPlanSub.objId in ('"+subIds+"') and t.status != ?",status);
	}

	
	/**
	 * FuncName: saveTaskPlanBySQL
	 * Description :  保存申报书对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午03:13:55
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午03:13:55
	 */
	@SuppressWarnings("unchecked")
	public void saveTaskPlanBySQL(final TaskPlan taskPlan) throws EsException{
	  getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("insert into ECP_TASK_PLAN (REMARK,TASK_PLAN_CODE,TASK_PLAN_ID,CREATE_TIME,BUDGET_NAME,TASK_PLAN_AMT,TASK_PLAN_NAME,MODIFY_TIME,BUDGET_ID,DEPART_NAME,DEPART_ID,BUDGET_LINKER,BUDGET_LINETEL,EBUY_METHOD,TASK_PLAN_ORGTYPE,TASK_PLAN_APPLYDATE,TASK_PLAN_FINISHDATE,CREATE_USER,MODIFY_USER,USE_STATUS) ");
				sql.append(" values('"+taskPlan.getRemark()+"','"+taskPlan.getTaskCode()+"','"+taskPlan.getObjId()+"',"+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(taskPlan.getCreateTime(),"yyyy-MM-dd HH:mm:ss"))+",'"+taskPlan.getBudgetName()+"','"+taskPlan.getAmt().toString()+"',");
				sql.append(" '"+taskPlan.getTaskName()+"',"+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(taskPlan.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"))+",'"+taskPlan.getBudget().getObjId()+"','"+taskPlan.getDepartmentName()+"','"+taskPlan.getDepartment().getObjId()+"','"+taskPlan.getBudgetLinker()+"',");
				sql.append(" '"+taskPlan.getBudgetLinkerTel()+"','"+taskPlan.getEbuyMethod()+"','"+taskPlan.getOrgType()+"',"+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(taskPlan.getApplyDate(),"yyyy-MM-dd HH:mm:ss"))+","+UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.format(taskPlan.getFinishDate(), "yyyy-MM-dd HH:mm:ss"))+",'"+taskPlan.getCreateUser().getObjId()+"','"+taskPlan.getUpdateUser().getObjId()+"','"+taskPlan.getUseStatus()+"')");
				Query query = session.createSQLQuery(sql.toString());
				return query.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	  });
		
	}

	/**
	 * FuncName: getTaskPlanIsExsit
	 * Description :  判断申报书是否已存在
	 * @param 
	 * @param taskPlan
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-4-13  下午03:48:57
	 * @Modifier: liuke
	 * @Modified Date:2011-4-13  下午03:48:57
	 */
	@SuppressWarnings("unchecked")
	public boolean getTaskPlanIsExsit(final TaskPlan taskPlan) {
	List list =  (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("select t.TASK_PLAN_ID from ECP_TASK_PLAN t where t.TASK_PLAN_ID=:objId");
				Query query = session.createSQLQuery(sql.toString()).setString("objId", taskPlan.getObjId());
				return query.list();
			}
			
		});
	   return (list.size()>0)?true:false;
	}
	
}

