package com.gpcsoft.epp.taskplan.dao;


import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanDao extends BaseGenericDao<TaskPlan> {
	
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
	public void submitTaskPlan(String objIds,String useStatus,String confirmStatus,String auditDetail);	
	
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
	public Page<TaskPlan> getSubNotSumSubsByOrg(final Page<TaskPlan> page,final String orgId,final String status,final String taskCode,final String applyDate) throws Exception;
	
	/**
	 * FuncName:getSubAllNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构id,status:汇总状态,taskCode:申报书编号,applyDate:申请日期
	 * @return Page<TaskPlan>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getSubAllNotSumSubsByOrg(final Page<TaskPlan> page,final String orgId,final String status,final String taskCode,final String applyDate)  throws Exception;
	
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
	 * FuncName: getTaskPlanPageByQueryObject
	 * Description:通过查询对象获得对应的申报书列表
	 * @param queryObject:拼装的对象,page:分页对象
	 * @return Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-7-13下午01:09:32 
	 */
	public Page<TaskPlan> getTaskPlanPageByQueryObject(QueryObject queryObject,Page<TaskPlan> page);

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
			QueryObject<TaskPlanSub> queryObject);
	
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
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds);

	/** 
	 * FuncName:getTaskPlanSubListByConfirmConsign
	 * Description :  得到所有已确认委托协议后的申报书明细列表
	 * @param page:分页对象,queryObject:查询的条件对象
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	public Page getTaskPlanSubListByConfirmConsign(Page page,String budgetName,String purchaseName,String ebuyMethod,String taskType,String taskPlanSubIds_not,String agentId);
	
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
	public Page<TaskPlan> getTaskPlanPageByQO(QueryObject queryObject,Page page);
	
	/**
	 * @Description : 得到被列入项目中的申报书条目列表
	 * @return  List
	 * @Create Date: 2010-7-16上午10:57:17 by liuke  Modified Date: 2010-7-16上午10:57:17 by liuke
	 */
	public List<TaskPlanSub> getTaskPlanSubInProject();
	
	/** 
	 * @Description :  得到被列入项目中的申报书条目列表
	 * @param   projectId:项目主键
	 * @return  List
	 * @Create Date: 2010-9-2下午05:31:40 by yangx  Modified Date: 2010-9-2下午05:31:40 by yangx
	 */
	public List<TaskPlanSub> getTaskPlanSubInProject(String projectId);
	
	/**
	 * @Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return List
	 * @throws Exception
	 * @Create Date 2010-9-2 下午01:30:38 By wanghz
	 */
	public List getNoSetupProjectLeader(String type)throws EsException;
	
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
	public BigDecimal getSubNotSumSubsByOrgNum(final String orgId,final String status)throws Exception;
	
	
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
	public List<TaskPlan> getTaskPlanListByObjId(String objId);
	
	
	
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
	public List<TaskPlan> getTaskPlanBySubsAndStatus(String subIds,String status);
	
	
	/**
	 * FuncName: saveTaskPlanBySQL
	 * Description :  保存申报书对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午03:13:55
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午03:13:55
	 */
	public void saveTaskPlanBySQL(TaskPlan taskPlan)throws EsException;
	
	
	
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

