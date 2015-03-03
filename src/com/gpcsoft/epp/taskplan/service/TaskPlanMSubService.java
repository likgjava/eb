package com.gpcsoft.epp.taskplan.service;
import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanMSubService extends BaseGenericService<TaskPlanMSub> {

	/** 
	 * Description :根据任务书返回任务书条目ids，逗号分隔  
	 * Create Date: 2011-9-20下午04:09:01 by sunl  Modified Date: 2011-9-20下午04:09:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getTaskPlanSubIdsByTaskPlanId(String taskPlanId) throws Exception;
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description:根据机构,获得其下级机构的未汇总的采购书条目
	 * @param objId:部门ID,status:汇总状态,taskType:申报书类型
	 * @return  List<TaskPlanMSub> 
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39      
	 * @Modified liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String objId,String status, String taskType,String ebuyMethod);
	
	/** 
	 * FuncName:saveMSubsByTaskPlan
	 * Description:根据申报书的主键,将其下的条目和明细汇总
	 * @param taskPlanIds  申报书id，以逗号分割  sumTaskPlanId 汇总的申报书id
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void saveMSubsByTaskPlan(String taskPlanIds,String sumTaskPlanId,String status);
	
	/** 
	 * FuncName:removeSumMSubs
	 * Description : 根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除taskPlanSub
	 * @param taskPlanIds:申报书主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39   
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void removeSumMSubs(String taskPlanSubIds,String status);
	
	/** 
	 * FuncName:backSumMSubs
	 * Description : 退回申报书条目，根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目,
	 * 并同步将资金明细删除,将对应申报书的状态改为临时
	 * @param taskPlanSubIds:申报书条目主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void backSumMSubs(String taskPlanSubIds,String status);
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanId
	 * Description:根据申报书id获取采购申报书明细
	 * @param   taskPlanId:申报书主键
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-30上午10:32:43 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-30上午10:32:43  
	 */
	public List<TaskPlanMSub> getTaskPlanSubByTaskPlanId(String taskPlanId);
	
	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description:根据申报书获得申报书明细列表
	 * @param   TaskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57    
	 * @Modifier liuke
	 * @Modified Date: 2010-7-15上午09:34:57 
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId);
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description:获取本级的申报书明细预算总金额的和
	 * @param taskPlanId:申报书主键
	 * @return  int
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21    
	 * @Modifier:shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21  
	 */  
	public float getTaskPlanMSubByStatus(String taskPlanId);
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreateProj
	 * Description:得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param   page:分页对象,taskAgentId:代理机构id,purCategoryId:品目Id,ebuyMethod:采购方式,taskType:申报书类型
	 * @return  Page
	 * @author yangx
	 * @Create Date: 2010-9-17上午10:57:56   
	 * @Modifier : yangx
	 * @Modified Date: 2010-9-17上午10:57:56  
	 */
	public Page getAllTaskPlanMSubForCreateProj(Page page,String taskAgentId,String ids,String purCategoryId,String ebuyMethod,String taskType);
	
	/** 
	 * Description :  查询待立项的申报书条目数
	 * Create Date: 2011-1-22上午11:34:14 by yangx  Modified Date: 2011-1-22上午11:34:14 by yangx
	 * @param   taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @return  List<TaskPlanMSub>
	 * @Exception   
	 */
	public List<TaskPlanMSub> getAllTaskPlanMSubForWaitCreateProj(final String taskAgentId,final String ids);
	
	/** 
	 * FuncName:getTaskPlanMSubByObjId
	 * Description:根据主键获取申报书、申报书条目中间对象
	 * @param   objId:申报书与申报书条目中间表对象主键
	 * @return  TaskPlanMSub
	 * @author yangx
	 * @Create Date: 2010-9-19上午11:02:31 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-19上午11:02:31  
	 */
	public TaskPlanMSub getTaskPlanMSubByObjId(String objId);
	
	/** 
	 * FucnName getTaskPlanMSubByProjectId
	 * Description:根据项目主键获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier   	yangx
	 * @Modified Date: 2010-6-24下午01:10:46   
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanMSubByProjectId(String projectId);
	
	/** 
	 * FuncName getAllTaskPlanMSubForCreatedProj
	 * Description:查询已完成采购申报书
	 * @param   page:分页对象,createUser:创建人,purchaseName:申报书名称,ebuyMethod:采购方式
	 * @return  Page<ProjectMTaskPlan>
	 * @author shenjz
	 * @Create Date: 2010-11-22上午10:57:56 
	 * @Modifier :  shenjz
	 * @Modified Date: 2010-11-22上午10:57:56 
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectMTaskPlan> getAllTaskPlanMSubForCreatedProj( Page page,String createUser,String purchaseName,String ebuyMethod);
	
	/**
	 * @Description:根据项目主键对象查询申报书条目与申报书中间表 
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanMSub>
	 * @Exception
	 * @Create Date: 2010-11-25上午10:54:31 by liuke  Modified Date: 2010-11-25上午10:54:31 by liuke
	 */
	public List<TaskPlanMSub> getTaskPlanMSubListByProjectId(String projectId);
	
	
	
	
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
	public Page getTaskPlanMSubForECPCreateProj(Page page,String taskAgentId,String purCategoryId,String ebuyMethod,String taskType);
	
	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param  taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Modified Date: 2011-10-19下午01:28:09 by yangx
	 */
	public BigDecimal getTaskPlanMSubForECPWaitCreateProj(final String taskAgentId);
	
	
	/**
	 * FuncName: getTaskPlanMSubByTaskPlanSubId
	 * Description :  根据申报书条目主键得到申报书条目与申报书中间表对象
	 * @param 
	 * @param TaskPlanSubId
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午06:27:31
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午06:27:31
	 */
	public List<TaskPlanMSub> getTaskPlanMSubByTaskPlanSubId(String TaskPlanSubId);
	
	
}
