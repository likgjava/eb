package com.gpcsoft.epp.taskplan.manager;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanManager extends BaseManager<TaskPlan> {
	
	/**
	 * FuncName:submitTaskPlan
	 * Description:提交采购计划
	 * @param   objIds:以逗号分割的采购计划主键,useStatus:使用状态,confirmStatus:确认状态,auditDetail:审核状态
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43  
	 */
	public void submitTaskPlan(String objIds,String useStatus,String confirmStatus,String auditDetail);
	
	/**
	 * FuncName:getTaskPlanBySubs
	 * Description:根据条目和状态获得申报书
	 * @param subIds:条目主键,status:
	 * @return   List<TaskPlan>
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43    
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43  
	 */
	public List<TaskPlan> getTaskPlanBySubs(String subIds,String status);
	
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
	public Page<TaskPlan> getSubNotSumSubsByOrg(Page<TaskPlan> page,String orgId,String status, String taskCode, String applyDate)throws Exception;
	
	/**
	 * FuncName:getTaskPlanListByObjId
	 * Description : 通过申报书主键得到申报书列表 
	 * @param   objId:申报书主键
	 * @return  List<TaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-5下午03:22:01    
	 * @Modifier liuke
	 * @Modified Date: 2010-7-5下午03:22:01  
	 */
	public List<TaskPlan>  getTaskPlanListByObjId(String objId);

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
	 * FuncName:getTaskPlanPageByQueryObject
	 * Description:通过查询对象获得对应的申报书列表
	 * @param queryObject:拼装的对象,page:分页对象
	 * @return Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13下午01:09:32  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQueryObject(QueryObject queryObject, Page<TaskPlan> page);

	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表
	 * @param   page:分页对象,queryObject:拼装的对象
	 * @return  Page
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-8上午09:39:48  
	 */
	public Page getTaskPlanSubListByNotblockAndPass(Page page,
			QueryObject<TaskPlanSub> queryObject);
	
	/** 
	 * FuncName:getTaskPlanListByTaskPlanIds
	 * Description:通过申报书主键得到申报书列表
	 * @param TaskPlanIds:申报书主键
	 * @return List<TaskPlan> 
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-8上午09:39:48  
	 */
	public List<TaskPlan> getTaskPlanListByTaskPlanIds(String TaskPlanIds); 
	
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 仅为获得采购申报书编号+1在页面展示
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String generatorTaskPlanCodeByQO(QueryObject queryObject)throws EsException;
	
	/**
	 * FuncName: generatorUpdateTaskPlanCodeByQO
	 * Description:生成采购申报书编号,Service层必须开启DB事物
	 * @param 
	 * @param queryObject
	 * @return
	 * @throws EsException String
	 * @author: shenjz
	 * @Create Date:2011-11-9  上午10:00:25
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-9  上午10:00:25
	 */
	public String generatorUpdateTaskPlanCodeByQO(QueryObject queryObject)throws EsException;

	/**
	 * FuncName: getTaskPlanSubListByConfirmConsign
	 * Description :  得到所有已确认委托协议后的申报书明细列表
	 * @param page:分页对象,queryObject:组装的查询条件对象
	 * @return Page
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * Modified Date: 2011-10-20上午11:49:28 by yangx
	 */
	public Page getTaskPlanSubListByConfirmConsign(Page page,String budgetName,String purchaseName,String ebuyMethod,String taskType,String taskPlanSubIds_not,String agentId);

	
	/** 
	 * FuncName:getTaskPlanPageByQO
	 * Description :  获取待处理采购申报书
	 * @param   QueryObject[taskType:申报书类型,useStatus:使用状态,confirmStatus:确认状态,block_check_status:大宗审核状态,taskCode：申报书编号,applyDate:日期]
	 * @return  Page<TaskPlan>
	 * @author yangx
	 * @Create Date: 2010-7-15下午07:04:45 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-15下午07:04:45 
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPageByQO(QueryObject queryObject,Page page);
	
	/**
	 * FuncName:getTaskPlanSubInProject
	 * Description : 得到被列入项目中的申报书条目列表
	 * @param   
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-16上午10:57:17 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-16上午10:57:17  
	 */
	public List<TaskPlanSub> getTaskPlanSubInProject();
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return List
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38  
	 */
	public List getNoSetupProjectLeader(String type)throws EsException;
	
	/**
	 * FuncName:getMoneySubByTaskPlan
	 * Description:根据申报书获取到申报书所有条目"预算总金额"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:32 
	 * @Modifier   shenjianzhuang
	 * @Modified Date: 2010-12-13下午04:27:32  
	 */	
	public float getMoneySubByTaskPlan(TaskPlan taskPlan);
	
	/**
	 * FuncName:getMoneyDetailByTaskPlan
	 * Description :  根据申报书获取到申报书所有资金明细"总资金"的和
	 * @param taskPlan:申报书对象
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-12-13下午04:27:43 
	 * @Modifier  shenjianzhuang  
	 * @Modified Date: 2010-12-13下午04:27:43  
	 */	
	public float getMoneyDetailByTaskPlan(TaskPlan taskPlan);
}
