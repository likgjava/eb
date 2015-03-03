package com.gpcsoft.epp.taskplan.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDetailDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMDetailDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMSubDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDrawTypeEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMDetailManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskURLEnum;
import com.gpcsoft.epp.worktask.domain.WorkTaskReceiverTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.epp.worktask.manager.WaitprocWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.sysconfig.manager.SysConfigManager;



@Service 
public class TaskPlanServiceImpl extends BaseGenericServiceImpl<TaskPlan> implements TaskPlanService {

	@Autowired(required=true) @Qualifier("taskPlanManagerImpl")
	private TaskPlanManager taskPlanManager;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubManagerImpl")
	private TaskPlanMSubManager taskPlanMSubManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;

	@Autowired(required=true) @Qualifier("taskPlanMDetailManagerImpl")
	private TaskPlanMDetailManager taskPlanMDetailManager;
	
	@Autowired(required=true) @Qualifier("waitprocWorkTaskManagerImpl")
	private WaitprocWorkTaskManager waitprocWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("sysConfigManagerImpl")
	private SysConfigManager sysConfigManager ;
	
	@Autowired(required=true)  @Qualifier("taskPlanDaoHibernate")
	private TaskPlanDao taskPlanDaoHibernate;

	@Autowired(required=true)  @Qualifier("taskPlanDetailDaoHibernate")
	private TaskPlanDetailDao taskPlanDetailDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("taskPlanMDetailDaoHibernate")
	private TaskPlanMDetailDao taskPlanMDetailDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("taskPlanMSubDaoHibernate")
	private TaskPlanMSubDao taskPlanMSubDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("taskPlanSubDaoHibernate")
	private TaskPlanSubDao taskPlanSubDaoHibernate;
	/**
	 * FunName:getSubNotSumSubsByOrg
	 * Description:根据机构,获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构ID,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>:申报书分页对象集合
	 * @Author:liangxj
	 * @Create   Date:  2010-6-3 下午04:12:39    
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public Page<TaskPlan> getSubNotSumSubsByOrg(Page<TaskPlan> page,String orgId,String status,String taskCode,String applyDate) throws Exception{
		logger.debug("\n=TaskPlanServiceImpl||getSubNotSumSubsByOrg\n");
		return taskPlanManager.getSubNotSumSubsByOrg(page, orgId, status,taskCode,applyDate);
	}
	
	/**
	 * FunName:getSubAllNotSumSubsByOrg
	 * Description:根据机构,获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构ID,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>:申报书分页对象集合
	 * @Author:liangxj
	 * @Create   Date:  2010-6-3 下午04:12:39    
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public Page<TaskPlan> getSubAllNotSumSubsByOrg(Page<TaskPlan> page,String orgId,String status,String taskCode,String applyDate) throws Exception{
		logger.debug("\n=TaskPlanServiceImpl||getSubNotSumSubsByOrg\n");
		return taskPlanDaoHibernate.getSubAllNotSumSubsByOrg(page, orgId, status,taskCode,applyDate);
	}
	
	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description :通过申报书得到申报书明细列表  
	 * @param   TaskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-6上午10:42:27 
	 * @Modifier liuke   
	 * @Modified Date: 2010-7-6上午10:42:27  
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId) {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanSubByTaskPlan\n");
		String taskPlanIds = TaskPlanId+",";
		List<TaskPlanSub> list = taskPlanMSubManager.getSubByTaskPlan(taskPlanIds);
		return list;
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
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanTotalByQueryObject\n");
		return taskPlanManager.getTaskPlanTotalByQueryObject(queryObject);
	}
    
	/**
	 * FuncName: getTaskPlanPageByQueryObject
	 * Description:通过查询对象获得对应的申报书列表
	 * @param taskCode:申报书编号;taskName:申报书名称;useStatus:使用状态,confirmStatus:确认状态,auditDetail:审核状态,leader:负责人ID
	 * @param governmentId:部门ID,isSubmit:是否为待提交申报书,taskAgentId:代理机构主键,taskType:申报书类型,user:用户,page:分页对象
	 * @return Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-7-13下午01:09:32  
	 */
	public Page<TaskPlan> getTaskPlanPageByQueryObject(String taskCode,String taskName,String useStatus,String confirmStatus,String auditDetail
			, String leader,String governmentId ,String isSubmit,String taskAgentId,String taskType,User user,Page<TaskPlan> page) {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanTotalByQueryObject\n");
		QueryObject<TaskPlan> queryObject = new QueryObjectBase<TaskPlan>();
		queryObject.getQueryParam().and(new QueryParam("taskCode",QueryParam.OPERATOR_EQ,taskCode));
		queryObject.getQueryParam().and(new QueryParam("taskName",QueryParam.OPERATOR_EQ,taskName));
		queryObject.getQueryParam().and(new QueryParam("isSubmit",QueryParam.OPERATOR_EQ,isSubmit));
		if(leader!=null&&!"".equals(leader)){//监管单位：待审核的申报书 
			queryObject.getQueryParam().and(new QueryParam("leader",QueryParam.OPERATOR_EQ,leader));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
		} else if(governmentId!=null&&!"".equals(governmentId)) {//监管单位：待审核资金的申报书 
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
			queryObject.getQueryParam().and(new QueryParam("governmentId",QueryParam.OPERATOR_EQ,governmentId));
		} else if(confirmStatus.equals(TaskPlanConfirmEnum.AUDIT_PASS)){//采购人：待抽取代理机构
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
			queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,taskType));
		} else if(confirmStatus.equals(TaskPlanConfirmEnum.SELECT_AGENT_NOPASS)){//采购人：重新抽取代理机构
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
		}else if(confirmStatus.equals(TaskPlanConfirmEnum.SELECT_AGENT)){// 采购办：审核抽取代理机构
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
			queryObject.getQueryParam().and(new QueryParam("leader",QueryParam.OPERATOR_EQ,leader));
		}else if(confirmStatus.equals(TaskPlanConfirmEnum.SELECT_AGENT_PASS)){ //代理机构：待委托的申报书
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("taskAgentId",QueryParam.OPERATOR_EQ,taskAgentId));
			queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,taskType));
		}else if(confirmStatus.equals(TaskPlanConfirmEnum.AUDIT_FOR_TPC)){ //代理机构：待委托的申报书
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
		}else {//采购人：待修改 
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));
			QueryParam queryParam = new QueryParam();
			queryParam.or(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryParam.or(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,auditDetail));
			queryObject.getQueryParam().or(queryParam);
			QueryParam queryParam1 = new QueryParam();
			queryParam1.or(new QueryParam("createUser",QueryParam.OPERATOR_EQ,"'"+user.getObjId()+"'"));
			queryObject.getQueryParam().or(queryParam1);
		}
		return taskPlanManager.getTaskPlanPageByQueryObject(queryObject,page);
	}

	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表
	 * @param   page:分页对象,queryObject:查询条件对象
	 * @return  
	 * @author Administrator
	 * @Create Date: 2010-7-8上午09:39:48 by liuke  
	 * @Modified Date: 2010-7-8上午09:39:48 by liuke
	 */
	@SuppressWarnings("unchecked")
	public Page getTaskPlanSubListByNotblockAndPass(Page page,
			QueryObject<TaskPlanSub> queryObject) {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanSubListByNotblockAndPass\n");
		return taskPlanManager.getTaskPlanSubListByNotblockAndPass(page, queryObject);
	}
    
	/** 
	 * FuncName:getTaskPlanListByTaskPlanIds
	 * Description : 通过申报书主键得到申报书列表
	 * @param TaskPlanIds:申报书主键
	 * @return List<TaskPlan>
	 */
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds) {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanListByTaskPlanIds\n");
		return taskPlanManager.getTaskPlanListByTaskPlanIds(TaskPlanIds);
	}

	/** 
	 * FuncName:getTaskPlanSubListByConfirmConsign
	 * Description:得到所有已确认委托协议后的申报书明细列表
	 * @param request
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	public Page getTaskPlanSubListByConfirmConsign(Page page,String budgetName,String purchaseName,String ebuyMethod,String taskType,String taskPlanSubIds_not,String agentId){
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanSubListByConfirmConsign\n");
		return taskPlanManager.getTaskPlanSubListByConfirmConsign(page,budgetName,purchaseName,ebuyMethod,taskType,taskPlanSubIds_not,agentId);
	}

	/**
	 * FuncName:getTaskPlanSubInProject
	 * Description : 得到被列入项目中的申报书条目列表
	 * @param   
	 * @return String
	 * @author liuke
	 * @Create Date: 2010-7-16上午10:57:17   
	 * @Modifier liuke
	 * @Modified Date: 2010-7-16上午10:57:17    
	 */
	@SuppressWarnings("unchecked")
	public String getTaskPlanSubInProject() {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanSubInProject\n");
		String usedTaskPlanSub = "";
		List<TaskPlanSub> list = taskPlanManager.getTaskPlanSubInProject();
		if(list != null && !list.isEmpty()){//若能获取申报书明细，则拼装ID
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				TaskPlanSub taskPlanSub = (TaskPlanSub) iterator.next();
				usedTaskPlanSub += taskPlanSub.getObjId() +",";
			}
			usedTaskPlanSub = usedTaskPlanSub.substring(0, usedTaskPlanSub.length()-1);
		}
		return usedTaskPlanSub;
	}
	
	/** 
	 * FuncName:getTaskPlanSubInProject
	 * Description:修改项目：到被列入项目中的申报书条目列表
	 * @param   projectId:项目主键
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-9-2下午05:29:28    
	 * @Modifier yangx
	 * @Modified Date: 2010-9-2下午05:29:28  
	 */
	public String getTaskPlanSubInProject(String projectId){
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanSubInProject\n");
		String usedTaskPlanSub = "";
		List<TaskPlanSub> list = taskPlanDaoHibernate.getTaskPlanSubInProject(projectId);
		if(list != null && !list.isEmpty()){//若能获取申报书明细，则拼装ID
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				TaskPlanSub taskPlanSub = (TaskPlanSub) iterator.next();
				usedTaskPlanSub += taskPlanSub.getObjId() +",";
			}
			usedTaskPlanSub = usedTaskPlanSub.substring(0, usedTaskPlanSub.length()-1);
		}
		return usedTaskPlanSub;
	}
	
	/**
	 * FuncName:getTaskPlanDetailByTaskPlan
	 * Description:根据申报书得到申报书资金明细 
	 * @param   
	 * @return  
	 * @author liuke
	 * @Create Date: 2010-7-21下午01:54:38 
	 * @Modifier liuke 
	 * @Modified Date: 2010-7-21下午01:54:38 
	 */
	public List<TaskPlanDetail> getTaskPlanDetailByTaskPlan(String TaskPlanId) {
		logger.debug("\n=TaskPlanServiceImpl||getTaskPlanDetailByTaskPlan\n");
		List<TaskPlanDetail> list = taskPlanMDetailManager.getDetailByTaskPlan(TaskPlanId);
		return list;
	}

	/**
	 * FuncName:saveAuditAllTaskPlan
	 * Description: 批量审核采购申报书,通过
	 * @param taskId
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-8-9 上午10:59:01 
	 */
	public void saveAuditAllTaskPlan(String taskId) throws Exception {
		logger.debug("\n=TaskPlanServiceImpl||saveAuditAllTaskPlan\n");
		for(String taskObjId:taskId.split(",")){
			TaskPlan task = taskPlanManager.get(taskObjId);
			if(TaskPlanTypeEnum.BLOCK_TRADE.equals(task.getTaskType())){//若是大宗申报，则需要把代理机构清空
				task.setTaskAgent(null);
				task.setTaskAgentName("");
				task.setConfirmStatus(CommonEnum.CONFIRM_STATUS_EXERCISE);
			}else{
				task.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);
			}
			task.setAuditDetail(CommonEnum.CONFIRM_STATUS_SURE);
			taskPlanManager.save(task);
		}
	}

	/**
	 * FuncName:saveAuditAllStapleTaskPlan
	 * Description: 批量审核大宗申报书,通过
	 * @param taskId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-8-9 下午01:30:45  
	 */
	public void saveAuditAllStapleTaskPlan(String taskId) throws Exception {
		logger.debug("\n=TaskPlanServiceImpl||saveAuditAllTaskPlan\n");
		for(String taskObjId:taskId.split(",")){
			TaskPlan task = taskPlanManager.get(taskObjId);
			task.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);
			task.setAuditDetail(CommonEnum.CONFIRM_STATUS_SURE);
			taskPlanManager.save(task);
		}
	}

	/** 
	 * FuncName:saveLeader
	 * Description :  保存项目经办人
	 * @param taskPlan:申报书对象
	 * @author liuy
	 * @Create Date: 2010-8-25下午03:31:31   
	 * @Modifier  liuy
	 * @Modified Date: 2010-8-25下午03:31:31  
	 * 
	 */
	@SuppressWarnings("unchecked")
	public TaskPlan saveLeader(TaskPlan taskPlan) {
		logger.debug("\n=TaskPlanServiceImpl||saveLeader\n");
		this.save(taskPlan);
		User user = AuthenticationHelper.getCurrentUser();
		//产生审核申报书待办
		//设置任务显示列
		String[] titleArray = new String[3];
		titleArray[0]="TaskPlanController.do?method=toAuditTaskPlanPage&objId="+taskPlan.getObjId();
		//权限平台			
		Map receiverMap = new HashMap();
		receiverMap.put(taskPlan.getLeader().getObjId(), WorkTaskReceiverTypeEnum.USER);
		waitprocWorkTaskManager.insertTaskByHand((String)sysConfigManager.getSysConfigItemValueByItemCode("EcpWaitprocWorkTask__desktop__taskplan__auditTaskPlan_taskTaskCode"), taskPlan.getObjId(), taskPlan.getObjId(), 
				"["+taskPlan.getTaskCode()+"]"+taskPlan.getTaskName()+"-"+(String)sysConfigManager.getSysConfigItemValueByItemCode("EcpWaitprocWorkTask__desktop__taskplan__auditTaskPlan_waitTaskName"), receiverMap, titleArray, user.getObjId());
		taskPlan.setUser(AuthenticationHelper.getCurrentUser(true));
		taskPlan.setParentBizId(taskPlan.getObjId());
		completedWorkTaskManager.createCompTaskByPassivity("指定项目负责人",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,taskPlan.getOpinion(),taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return taskPlan;
		
	}

	/**
	 * FuncName:createTaskPlanCodeByQO
	 * Description: 仅为获得采购申报书编号+1在页面展示
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	@SuppressWarnings("unchecked")
	public String createTaskPlanCodeByQO(QueryObject queryObject) throws EsException {
		logger.debug("\n=TaskPlanServiceImpl||createTaskPlanCodeByQO\n");
		return taskPlanManager.generatorTaskPlanCodeByQO(queryObject);
	}
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return Long
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38 
	 */
	public List getNoSetupProjectLeader(String type)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||getNoSetupProjectLeader\n");
		return taskPlanManager.getNoSetupProjectLeader(type);
	}
	
	/**
	 * 需要先清理下级汇总的明细关系后，才可删除
	 */
	@Override
	protected void onBeforeRemove(String id) {
		logger.debug("\n=TaskPlanServiceImpl||onBeforeRemove\n");
		//删除任务条目
		taskPlanMSubManager.removeSumMSubs(id, TaskPlanSumEnum.SUMMARY);
		//删除资金明细
		taskPlanMDetailManager.removeSumMDetails(id, TaskPlanSumEnum.SUMMARY);
	}
	
	/** 
	 * Description :  保存申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午04:02:24 by sunl  Modified Date: 2011-9-20下午04:02:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan saveTaskPlan(TaskPlan taskPlan) throws EsException {
		logger.debug("\n=TaskPlanServiceImpl||saveTaskPlan\n");
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);			// 临时
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);	// 待确认
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);	// 待确认
		taskPlan.setParentBizId(taskPlan.getObjId());
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("新增申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return taskPlan;
	}
	
	/** 
	 * Description :  提交申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午04:02:24 by sunl  Modified Date: 2011-9-20下午04:02:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan submitTaskPlan(TaskPlan taskPlan)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||submitTaskPlan\n");
		
		TaskPlan old = taskPlanManager.get(taskPlan.getObjId());
		old.setTaskName(taskPlan.getTaskName());//项目名称
		old.setTotalScale(taskPlan.getTotalScale());//投资规模
		old.setConstructScale(taskPlan.getConstructScale());//建设规模
		old.setMoneySrc(taskPlan.getMoneySrc());//资金来源
		old.setPrjAddress(taskPlan.getPrjAddress());//工程地点
		old.setEbuyMethod(taskPlan.getEbuyMethod());//招标方式
		old.setDepartmentLinker(taskPlan.getDepartmentLinker());//招标中心项目负责人
		old.setDepartmentLinkerTel(taskPlan.getDepartmentLinkerTel());//招标中心项目负责人联系方式
		old.setDepartment(taskPlan.getDepartment());//管理公司
		old.setDepartmentName(taskPlan.getDepartmentName());//管理公司名称
		old.setEbuyMethod(taskPlan.getEbuyMethod());//招标方式
		old.setLeaderName(taskPlan.getLeaderName());//业主单位项目负责人
		old.setLeader(taskPlan.getLeader());//业主单位项目负责人
		old.setBudget(taskPlan.getBudget());//业主单位
		old.setProcessStatus(taskPlan.getProcessStatus());//进度状态
		
		old.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
		old.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
		old.setUseStatus(CommonEnum.USER_STATUS_FORMAL);		// 01
		
		//因空对象保证为报错，所以这里需要判断代理机构是否为空对象
		if(old.getTaskAgent()!= null && old.getTaskAgent().getObjId() == null ){
			old.setTaskAgent(null);
		}
		User user = AuthenticationHelper.getCurrentUser();
		old.setUser(user);
		old.setParentBizId(taskPlan.getObjId());
		completedWorkTaskManager.createCompTaskByPassivity("提交审核申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,old.getObjId(),old.getObjId(),CompletedWorkTaskURLEnum.WORKTASK_URL_TASKPLANUPDATE,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return old;
	}
	
	/**
	 * FuncName:saveCreateTaskPlanTwain
	 * Description: 创建申报书(二级采购人)
	 * @param taskPlan:申报书实体类
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan saveCreateTaskPlanTwain(TaskPlan taskPlan) throws EsException {
		logger.debug("\n=TaskPlanServiceImpl||saveCreateTaskPlanTwain\n");
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);			// 临时
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);	// 待确认
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);	// 待确认
		taskPlan.setParentBizId(taskPlan.getObjId());
		taskPlan.setTaskCode(taskPlanManager.generatorUpdateTaskPlanCodeByQO(null));
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("新增申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return taskPlan;
	}
	
	/**
	 * FuncName:updateTaskPlanTwain
	 * Description: 更新申报书(二级采购人)
	 * @param taskPlan:申报书实体类
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan updateTaskPlanTwain(TaskPlan taskPlan) throws EsException {
		logger.debug("\n=TaskPlanServiceImpl||saveCreateTaskPlanTwain\n");
		taskPlanManager.save(taskPlan);
		return taskPlan;
	}
	
	/**
	 * FuncName:removeTaskPlan
	 * Description: 删除申报书
	 * @param objId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-6 上午11:35:29 By wanghz
	 */
	public void removeTaskPlan(String objId)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||removeTaskPlan\n");
		taskPlanMSubManager.removeSumMSubs(objId, TaskPlanSumEnum.SUMMARY);//删除任务条目
		taskPlanMDetailManager.removeSumMDetails(objId, TaskPlanSumEnum.SUMMARY);//删除资金明细
		taskPlanManager.remove(objId.split(","), TaskPlan.class);
	}
	
	/**
	 * FuncName:saveCreateTaskPlanStair
	 * Description: 创建申报书(一级采购人)
	 * @param taskPlan:申报书对象
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan saveCreateTaskPlanStair(TaskPlan taskPlan)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||saveCreateTaskPlanStair\n");
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
		taskPlan.setTaskCode(taskPlanManager.generatorUpdateTaskPlanCodeByQO(null));
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("一级单位新增申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return taskPlan;
	}
	
	/**
	 * FuncName: updateTaskPlanStair
	 * Description: 更新采购申报书(一级采购人)
	 * @param taskPlan:申报书对象
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 下午01:20:35  
	 */
	public TaskPlan updateTaskPlanStair(TaskPlan taskPlan)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||updateTaskPlanStair\n");
		//因空对象保证为报错，所以这里需要判断代理机构是否为空对象
		if(taskPlan.getTaskAgent()!= null && taskPlan.getTaskAgent().getObjId() == null ){
			taskPlan.setTaskAgent(null);
		}
		taskPlanManager.save(taskPlan);
		return taskPlan;
	}
	
	/** 
	 * FuncName: saveSubmitTaskPlanByBuyerLevel
	 * Description :  根据采购人级别提交申报书
	 * @param   taskPlan：申报书;BuyerLevel：采购人级别[一级提交审核、二级提交审核]
	 * @return  TaskPlan
	 * @author yangx
	 * @Create Date: 2010-11-10上午09:55:49 
	 * @Modifier yangx
	 * @Modified Date: 2010-11-10上午09:55:49  
	 */
	public TaskPlan saveSubmitTaskPlanByBuyerLevel(TaskPlan taskPlan,String buyerLevel)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||saveSubmitTaskPlanByBuyerLevel\n");
		if (buyerLevel!=null&&TaskPlanTypeEnum.BUYER_ONE.equals(buyerLevel)) {//一级采购人提交审核
			taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_NEGOTIATE);	// 01
			taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);		// 00
			//因空对象保证为报错，所以这里需要判断代理机构是否为空对象
			if(taskPlan.getTaskAgent()!= null && taskPlan.getTaskAgent().getObjId() == null ){
				taskPlan.setTaskAgent(null);
			}
			taskPlanManager.save(taskPlan);
			User user = AuthenticationHelper.getCurrentUser();
			taskPlan.setUser(user);
			completedWorkTaskManager.createCompTaskByPassivity("一级单位提交审核申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,taskPlan.getObjId(),taskPlan.getObjId(),CompletedWorkTaskURLEnum.WORKTASK_URL_TASKPLANAUDIT_S,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
			taskPlan.setParentBizId(taskPlan.getObjId());
			taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		} else if (buyerLevel!=null&&TaskPlanTypeEnum.BUYER_TWO.equals(buyerLevel)) {//二级采购人提交审核
			taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
			taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);	// 00
			taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);		// 01
			//因空对象保证为报错，所以这里需要判断代理机构是否为空对象
			if(taskPlan.getTaskAgent()!= null && taskPlan.getTaskAgent().getObjId() == null ){
				taskPlan.setTaskAgent(null);
			}
			taskPlanManager.save(taskPlan);
			User user = AuthenticationHelper.getCurrentUser();
			taskPlan.setUser(user);
			taskPlan.setParentBizId(taskPlan.getObjId());
			completedWorkTaskManager.createCompTaskByPassivity("提交审核申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,null,taskPlan.getObjId(),taskPlan.getObjId(),CompletedWorkTaskURLEnum.WORKTASK_URL_TASKPLANUPDATE,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		}
		return taskPlan;
	}
	
	/** 
	 * FuncName:saveAuditStockBankroll
	 * Description :  审核采购资金,支持批量审核,是计划任务节点唯一指定服务供应商
	 * @param   ids:申报书主键,opinion:审核意见,flag ：true 通过 false 不通过
	 * @return TaskPlan 
	 * @author wangcl
	 * @Create Date: 2010-10-12下午03:04:38 
	 * @Modifier   wangcl
	 * @Modified Date: 2010-10-12下午03:04:38  
	 */
	public TaskPlan saveAuditStockBankroll(String ids,String opinion,boolean flag){
		logger.debug("\n=TaskPlanServiceImpl||saveAuditStockBankroll\n");
		TaskPlan taskPlan = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			taskPlan = this.get(objId);
			if(flag){//通过
				taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);		// 审核中
				taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);					// 正式
				taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_SURE);				// 确认
				taskPlan.setLeader(null);
				taskPlanManager.save(taskPlan);
				completedWorkTaskManager.createCompTaskByPassivity("审核采购资金",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
				taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}else{//不通过
				taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);					// 正式
				taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_BACK);				// 退回
				taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_BACK);				// 退回
				//若是大宗申报，则需要把代理机构清空
				if(TaskPlanTypeEnum.BLOCK_TRADE.equals(taskPlan.getTaskType())){
					taskPlan.setTaskAgent(null);
					taskPlan.setTaskAgentName("");
				}
				taskPlanManager.save(taskPlan);
				completedWorkTaskManager.createCompTaskByPassivity("审核采购资金",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
				taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
		}
		taskPlan.setUser(AuthenticationHelper.getCurrentUser());
		taskPlan.setParentBizId(ids);
		return taskPlan;
	}
	

	/**
	 * FuncName:saveAuditStockTaskPlan
	 * Description: 审核采购申报书 支持批量
	 * @param taskPlan:申报书对象
	 * @param flag: true pass;false no pass;
	 * @param opinion 审核意见
	 * @return TaskPlan
	 * @author wangcl
	 * @Create Date 2010-9-6 下午02:49:36 By wanghz  
	 */
	public TaskPlan saveAuditStockTaskPlan(String ids, String opinion ,boolean flag ,OrgInfo taskAgent)throws Exception{
		logger.debug("\n=TaskPlanServiceImpl||saveAuditStockTaskPlan\n");
		TaskPlan taskPlan = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			taskPlan = this.get(objId);
			if(taskAgent!=null){
				taskPlan.setTaskAgent(taskAgent);
				taskPlan.setTaskAgentName(taskAgent.getOrgName());
			}
			if (flag) {
				taskPlanManager.save(taskPlan);
				completedWorkTaskManager.createCompTaskByPassivity("审核采购申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
				taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else {
				taskPlanManager.save(taskPlan);
				completedWorkTaskManager.createCompTaskByPassivity("审核采购申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
				taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			
		}
		taskPlan.setUser(AuthenticationHelper.getCurrentUser(true));
		taskPlan.setParentBizId(ids);
		return taskPlan;
	}
	
	/**
	 * FuncName:saveSubmitTwainTaskPlan
	 * Description: 一级采购人提交二级申报书
	 * @param taskPlan
	 * @return TaskPlan
	 * @param opinion 审核意见
	 * @author wanghz
	 * @Create Date 2010-9-7 上午09:44:03  
	 */
	public TaskPlan saveSubmitTwainTaskPlan(TaskPlan taskPlan,String opinion)throws EsException{
		logger.debug("\n=TaskPlanServiceImpl||saveSubmitTwainTaskPlan\n");
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_NEGOTIATE);			// 01
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);					// 01
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("一级单位提交审核申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
		return taskPlan;
	}
	
	/**
	 * FuncName:saveRejectTaskPlan
	 * @Description: 一级采购人退回二级申报书
	 * @param taskPlan:申报书对象
	 * @param opinion 审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午09:44:03  
	 */
	public TaskPlan saveRejectTaskPlan(TaskPlan taskPlan, String opinion)throws EsException{
		logger.debug("\nTaskPlanServiceImpl||saveRejectTaskPlan\n");
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_BACK);					// 04
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);						// 01
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("一级单位退回申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		return taskPlan;
	}
	
	public TaskPlan saveAuditBySuperior(String ids,String opinion,boolean flag){
		logger.debug("\nTaskPlanServiceImpl||saveAuditBySuperior\n");
		TaskPlan taskplan = null;
		String[] objIds = ids.split(",");
		if(flag){
			for (int i = 0; i < objIds.length; i++) {
				TaskPlan plan = this.get(objIds[i]);
				taskplan = this.saveSubmitTwainTaskPlan(plan, opinion);
			}
			taskplan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		} else{
			for (int i = 0; i < objIds.length; i++) {
				TaskPlan plan = this.get(objIds[i]);
				taskplan = this.saveRejectTaskPlan(plan, opinion);
			}
			taskplan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}
		taskplan.setUser(AuthenticationHelper.getCurrentUser(true));
		taskplan.setParentBizId(ids);
		return taskplan;
	}
	
	/**
	 * FuncName:saveAuditColligateTaskPlanReject
	 * Description: 审核大宗申报书[退回]
	 * @param taskPlan:申报书对象,opinion:审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:09:40  
	 */
	public TaskPlan saveAuditColligateTaskPlanReject(TaskPlan taskPlan,String opinion)throws EsException{
		logger.debug("\nTaskPlanServiceImpl||saveAuditBySuperior\n");
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_BACK);					// 04
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("审核大宗申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		return taskPlan;
	}
	
	/**
	 * FuncName:saveAuditColligateTaskPlanPass\
	 * Description: 审核大宗申报书[通过]
	 * @param taskPlan:申报书对象,opinion:审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:09:40  
	 */
	public TaskPlan saveAuditColligateTaskPlanPass(TaskPlan taskPlan,String opinion)throws EsException{
		logger.debug("\nTaskPlanServiceImpl||saveAuditColligateTaskPlanPass\n");
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_SURE);					// 02
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);				// 01
		taskPlan.setLeader(null);
		taskPlanManager.save(taskPlan);
		completedWorkTaskManager.createCompTaskByPassivity("审核大宗申报书",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_TASKPLAN,opinion,taskPlan.getObjId(),taskPlan.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
		return taskPlan;
	}
	
	/**
	 * FuncName:getTaskPlanByObjId
	 * Description :根据主键获得申报书  
	 * @param   objId:申报书主键
	 * @return TaskPlan
	 * @author liuke
	 * @Create Date: 2010-10-12上午10:35:47 
	 * @Modifier  liuke
	 * @Modified Date: 2010-10-12上午10:35:47  
	 */
	public TaskPlan getTaskPlanByObjId(String objId) {
		logger.debug("\nTaskPlanServiceImpl||getTaskPlanByObjId\n");
		return taskPlanManager.get(objId);
	}
	
	/**
	 * FuncName:auditTaskPlanForSelectAgent
	 * Description : 审核申报书选择代理机构 
	 * @param ids:申报书主键,opinion:审核意见,confirmStatus:确认状态
	 * @return TaskPlan
	 * @author liuke
	 * @Create Date: 2010-10-13上午11:07:39   
	 * @Modifier liuke
	 * @Modified Date: 2010-10-13上午11:07:39  
	 */
	public TaskPlan auditTaskPlanForSelectAgent(String ids, String opinion,String confirmStatus) {
		logger.debug("\nTaskPlanServiceImpl||auditTaskPlanForSelectAgent\n");
		String objIds [] = ids.split(",");
		TaskPlan taskPlan = null;
		for (String objId : objIds) {
			taskPlan = taskPlanManager.get(objId);
			taskPlan.setConfirmStatus(confirmStatus);
			taskPlanManager.save(taskPlan);
		}
		if(confirmStatus.equals(TaskPlan.CONFIRM_STATUS_NO_PASS)){
			taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}else{
			taskPlan.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}
		taskPlan.setUser(AuthenticationHelper.getCurrentUser(true));
		taskPlan.setParentBizId(ids);
		return taskPlan;
	}

	/**
	 * FuncName:getSubNotSumSubsByOrgNum
	 * Description :根据机构，获得其下级机构的未汇总的采购书  
	 * @param   orgId:机构ID,status:汇总状态
	 * @return BigDecimal
	 * @author liuke
	 * @Create Date: 2010-10-18下午01:14:33 by   
	 * @Modified Date: 2010-10-18下午01:14:33 by liuke
	 */
	public BigDecimal getSubNotSumSubsByOrgNum(String orgId,String status)throws Exception {
		logger.debug("\nTaskPlanServiceImpl||getSubNotSumSubsByOrgNum\n");
		return taskPlanDaoHibernate.getSubNotSumSubsByOrgNum(orgId, status);
	}
	
	/**
	 * FuncName:getRandomAgent
	 * Description :随机抽取代理机构  
	 * @param   selectAgents:代理机构主键
	 * @return  String
	 * @author liuke
	 * @Create Date: 2010-10-12下午03:50:26 
	 * @Modifier    liuke
	 * @Modified Date: 2010-10-12下午03:50:26  
	 */
	public String getRandomAgent(String selectAgents) {
		logger.debug("\nTaskPlanServiceImpl||getRandomAgent\n");
		String[] selectAgentArray = selectAgents.split(",");
		Random rd = new Random();
		String selectId = selectAgentArray[rd.nextInt(selectAgentArray.length)];
		return selectId;
	}
	
	/**
	 * FuncName:saveTaskPlanForSelectAgent
	 * Description :保存选择代理机构的申报书
	 * @param  taskplanId:申报书主键,drawType:抽取方式,agentId:代理机构ID
	 * @return  TaskPlan
	 * @author liuke
	 * @Create Date: 2010-10-12下午05:24:56 by liuke  
	 * @Modifier liuke
	 * @Modified Date: 2010-10-12下午05:24:56 by liuke
	 */
	public TaskPlan saveTaskPlanForSelectAgent(String taskplanId,String drawType, String agentId) {
		logger.debug("\nTaskPlanServiceImpl||saveTaskPlanForSelectAgent\n");
		TaskPlan taskPlan = taskPlanManager.get(taskplanId);
		OrgInfo agent = (OrgInfo) taskPlanManager.get(agentId, OrgInfo.class);
		taskPlan.setDrawType(drawType);
		taskPlan.setTaskAgent(agent);
		taskPlan.setTaskAgentName(agent.getOrgName());
		if(TaskPlanDrawTypeEnum.RANDOM_SELECT.equals(drawType)){//随机抽取方式
			taskPlan.setConfirmStatus(TaskPlanConfirmEnum.SELECT_AGENT_PASS);
		}else{//定向选择方式
			taskPlan.setConfirmStatus(TaskPlanConfirmEnum.SELECT_AGENT);
		}
		taskPlanManager.save(taskPlan);
		return taskPlan;
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :保存代理机构退回的申报书  
	 * @param   String opinion 退回意见 ,String taskplanId 申报书主键
	 * @return  TaskPlan 申报书对象
	 * @author lic
	 * @Create Date: 2010-10-25下午03:46:31    
	 * @Modifier lic
	 * @Modified Date: 2010-10-25下午03:46:31  
	 */
	public  TaskPlan  saveReturnTaskplanByAgent(String opinion,String taskplanId){
		logger.debug("\nTaskPlanServiceImpl||saveReturnTaskplanByAgent\n");
		TaskPlan taskPlan = taskPlanManager.get(taskplanId);
		taskPlan.setConfirmStatus(TaskPlanConfirmEnum.AUDIT_PASS);
		taskPlan.setUser(AuthenticationHelper.getCurrentUser(true));
		taskPlan.setParentBizId(taskplanId);
		return taskPlan;
	}
	
	/**
	 * FuncName:getMoneySubByTaskPlan
	 * Description :  根据申报书获取到申报书所有条目"预算总金额"的和
	 * @param taskPlan:申报书对象
	 * @return  int
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:32 
	 * @Modifier  shenjianzhuang   
	 * @Modified Date: 2010-12-13下午04:27:32  
	 */	
	public float getMoneySubByTaskPlan(TaskPlan taskPlan){
		return taskPlanManager.getMoneySubByTaskPlan(taskPlan);
	}
	
	/**
	 * FuncName:getMoneyDetailByTaskPlan
	 * Description:根据申报书获取到申报书所有资金明细"总资金"的和
	 * @param taskPlan:申报书对象
	 * @return  int
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:43    
	 * @Modifier:shenjianzhuang
	 * @Modified Date: 2010-12-13下午04:27:43  
	 */	
	public float getMoneyDetailByTaskPlan(TaskPlan taskPlan){
		return taskPlanManager.getMoneyDetailByTaskPlan(taskPlan);
	}
	
	/**
	 * FuncName: saveTaskPlan
	 * Description :  保存申报书对象
	 * @param 
	 * @param taskPlan void
	 * @author: liuke
	 * @Create Date:2011-4-11  下午08:23:26
	 * @Modifier: liuke
	 * @Modified Date:2011-4-11  下午08:23:26
	 */
	public void saveTaskPlan(List<TaskPlan> taskPlanList)throws Exception{
			Map<String,Object> objects = new HashMap<String, Object>();
			for(TaskPlan  taskPlan:taskPlanList){
			taskPlanDaoHibernate.saveTaskPlanBySQL(taskPlan);
			for (TaskPlanMSub taskPlanMSub:taskPlan.getTaskPlanMSubs()) {
				if (null == objects.get(taskPlanMSub.getTaskPlanSub().getObjId())) {
					taskPlanSubDaoHibernate.saveTaskPlanSubBySQL(taskPlanMSub.getTaskPlanSub());
					objects.put(taskPlanMSub.getTaskPlanSub().getObjId(), taskPlanMSub.getTaskPlanSub());
				}
				taskPlanMSubDaoHibernate.saveTaskPlanMSubBySQL(taskPlanMSub);
			}
			for (TaskPlanMDetail taskPlanMDetail:taskPlan.getTaskPlanMDetails()) {
				if (null == objects.get(taskPlanMDetail.getTaskPlanDetail().getObjId())) {
					taskPlanDetailDaoHibernate.saveTaskPlanDetailBySQL(taskPlanMDetail.getTaskPlanDetail());
					objects.put(taskPlanMDetail.getTaskPlanDetail().getObjId(), taskPlanMDetail.getTaskPlanDetail());
				}
				taskPlanMDetailDaoHibernate.saveTaskPlanMDetailBySQL(taskPlanMDetail);
			}
			}
	
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
	public boolean getTaskPlanIsExsit(TaskPlan taskPlan) {
		return taskPlanDaoHibernate.getTaskPlanIsExsit(taskPlan);
	}
	
}
