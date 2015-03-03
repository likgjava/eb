package com.gpcsoft.epp.taskplan.service;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.srplatform.auth.domain.User;

public interface TaskPlanService extends BaseGenericService<TaskPlan> {

	
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
	public Page<TaskPlan> getSubNotSumSubsByOrg(Page<TaskPlan> page,String orgId,String status,String taskCode,String applyDate) throws Exception;
	
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
	public Page<TaskPlan> getSubAllNotSumSubsByOrg(Page<TaskPlan> page,String orgId,String status,String taskCode,String applyDate) throws Exception;
	
	/** 
	 * FuncName:getTaskPlanListByTaskPlanIds
	 * Description : 通过申报书主键得到申报书列表
	 * @param TaskPlanIds:申报书主键
	 * @return List<TaskPlan>
	 */
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds); 
	
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
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId);

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
	public BigDecimal getTaskPlanTotalByQueryObject(QueryObject queryObject);
	
	/**
	 * FuncName:getSubNotSumSubsByOrgNum
	 * Description :根据机构，获得其下级机构的未汇总的采购书  
	 * @param   orgId:机构ID,status:汇总状态
	 * @return BigDecimal
	 * @author liuke
	 * @Create Date: 2010-10-18下午01:14:33 by   
	 * @Modified Date: 2010-10-18下午01:14:33 by liuke
	 */
	public BigDecimal getSubNotSumSubsByOrgNum(String orgId,String status)throws Exception;
	
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
			, String leader,String governmentId ,String isSubmit,String taskAgentId,String taskType,User user,Page<TaskPlan> page);
	
	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表
	 * @param   page:分页对象,queryObject:查询条件对象
	 * @return  
	 * @author Administrator
	 * @Create Date: 2010-7-8上午09:39:48 by liuke  
	 * @Modified Date: 2010-7-8上午09:39:48 by liuke
	 */
	public Page getTaskPlanSubListByNotblockAndPass(Page page,
			QueryObject<TaskPlanSub> query);

	/** 
	 * FuncName:getTaskPlanSubListByConfirmConsign
	 * Description:得到所有已确认委托协议后的申报书明细列表
	 * @param request
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	public Page getTaskPlanSubListByConfirmConsign(Page page,String budgetName,String purchaseName,String ebuyMethod,String taskType,String taskPlanSubIds_not,String agentId);
	
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
	public String getTaskPlanSubInProject();

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
	public String getTaskPlanSubInProject(String projectId);
	
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
	public List<TaskPlanDetail> getTaskPlanDetailByTaskPlan(String TaskPlanId);
	
	/**
	 * FuncName:saveAuditAllTaskPlan
	 * Description: 批量审核采购申报书,通过
	 * @param taskId
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-8-9 上午10:59:01 
	 */
	public void saveAuditAllTaskPlan(String taskId)throws Exception;
	
	/**
	 * FuncName:saveAuditAllStapleTaskPlan
	 * Description: 批量审核大宗申报书,通过
	 * @param taskId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-8-9 下午01:30:45  
	 */
	public void saveAuditAllStapleTaskPlan(String taskId)throws Exception;

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
	public TaskPlan saveLeader(TaskPlan taskPlan);
	
	/**
	 * FuncName:createTaskPlanCodeByQO
	 * Description: 仅为获得采购申报书编号+1在页面展示
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	public String createTaskPlanCodeByQO(QueryObject queryObject)throws EsException;
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return Long
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38 
	 */
	public List getNoSetupProjectLeader(String type)throws EsException;

	/** 
	 * Description :  保存申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午04:02:24 by sunl  Modified Date: 2011-9-20下午04:02:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan saveTaskPlan(TaskPlan taskPlan)throws EsException;
	
	/** 
	 * Description :  提交申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午04:02:24 by sunl  Modified Date: 2011-9-20下午04:02:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan submitTaskPlan(TaskPlan taskPlan)throws EsException;
	
	/**
	 * FuncName:saveCreateTaskPlanTwain
	 * Description: 创建申报书(二级采购人)
	 * @param taskPlan:申报书实体类
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan saveCreateTaskPlanTwain(TaskPlan taskPlan)throws EsException;
	
	/**
	 * FuncName:updateTaskPlanTwain
	 * Description: 更新申报书(二级采购人)
	 * @param taskPlan:申报书实体类
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan updateTaskPlanTwain(TaskPlan taskPlan)throws EsException;
	
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
	public TaskPlan saveSubmitTaskPlanByBuyerLevel(TaskPlan taskPlan,String buyerLevel)throws EsException;
	
	/**
	 * FuncName:removeTaskPlan
	 * Description: 删除申报书
	 * @param objId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-6 上午11:35:29 By wanghz
	 */
	public void removeTaskPlan(String objId)throws EsException;
	
	/**
	 * FuncName:saveCreateTaskPlanStair
	 * Description: 创建申报书(一级采购人)
	 * @param taskPlan:申报书对象
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:34:39  
	 */
	public TaskPlan saveCreateTaskPlanStair(TaskPlan taskPlan)throws EsException;
	
	/**
	 * FuncName: updateTaskPlanStair
	 * Description: 更新采购申报书(一级采购人)
	 * @param taskPlan:申报书对象
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-6 下午01:20:35  
	 */
	public TaskPlan updateTaskPlanStair(TaskPlan taskPlan)throws EsException;
	
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
	public TaskPlan saveAuditStockBankroll(String  ids, String opinion,boolean flag)throws EsException;
	
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
	public TaskPlan saveAuditStockTaskPlan(String ids, String opinion,boolean flag,OrgInfo taskAgent)throws Exception;
	
	/**
	 * FuncName:saveSubmitTwainTaskPlan
	 * Description: 一级采购人提交二级申报书
	 * @param taskPlan
	 * @return TaskPlan
	 * @param opinion 审核意见
	 * @author wanghz
	 * @Create Date 2010-9-7 上午09:44:03  
	 */
	public TaskPlan saveSubmitTwainTaskPlan(TaskPlan taskPlan,String opinion)throws EsException;
	
	/**
	 * FuncName:saveRejectTaskPlan
	 * @Description: 一级采购人退回二级申报书
	 * @param taskPlan:申报书对象
	 * @param opinion 审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午09:44:03  
	 */
	public TaskPlan saveRejectTaskPlan(TaskPlan taskPlan, String opinion)throws EsException;
	
	/**
	 * FuncName:saveAuditColligateTaskPlanReject
	 * Description: 审核大宗申报书[退回]
	 * @param taskPlan:申报书对象,opinion:审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:09:40  
	 */
	public TaskPlan saveAuditColligateTaskPlanReject(TaskPlan taskPlan,String opinion)throws EsException;
	
	/**
	 * FuncName:saveAuditColligateTaskPlanPass\
	 * Description: 审核大宗申报书[通过]
	 * @param taskPlan:申报书对象,opinion:审核意见
	 * @return TaskPlan
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:09:40  
	 */
	public TaskPlan saveAuditColligateTaskPlanPass(TaskPlan taskPlan,String opinion)throws EsException;
	
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
	public TaskPlan getTaskPlanByObjId(String objId);
	
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
	public TaskPlan auditTaskPlanForSelectAgent(String ids,String opinion,String confirmStatus);
	
	/** 
	 * Description :  一级采购人审核（批量）下级采购申报书
	 * 				本方法可作为工作计划节点服务方法，支持批量
	 * Create Date: 2010-10-20下午03:28:17 by wangcl  Modified Date: 2010-10-20下午03:28:17 by wangcl
	 * @param   ids 审核对象的id串，多个以逗号隔开
	 * @param    opinion  审核意见
	 * @param	flag ：true通过；false不通过。
	 * @return  
	 * @Exception   
	 */
	public TaskPlan saveAuditBySuperior(String ids,String opinion,boolean flag);
	
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
	public String getRandomAgent(String selectAgents);
	
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
	public TaskPlan saveTaskPlanForSelectAgent(String taskplanId,String drawType,String agentId);
	
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
	public  TaskPlan  saveReturnTaskplanByAgent(String opinion,String taskplanId);
	
	
	/**
	 * FuncName:getMoneySubByTaskPlan
	 * Description :  根据申报书获取到申报书所有条目"预算总金额"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:32 
	 * @Modifier  shenjianzhuang   
	 * @Modified Date: 2010-12-13下午04:27:32  
	 */	
	public float getMoneySubByTaskPlan(TaskPlan taskPlan);
	
	/**
	 * FuncName:getMoneyDetailByTaskPlan
	 * Description:根据申报书获取到申报书所有资金明细"总资金"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:43    
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-12-13下午04:27:43  
	 */	
	public float getMoneyDetailByTaskPlan(TaskPlan taskPlan);
	
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
	public void saveTaskPlan(List<TaskPlan> taskPlanList)throws Exception;
	
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
	public boolean getTaskPlanIsExsit(TaskPlan taskPlan);
}
