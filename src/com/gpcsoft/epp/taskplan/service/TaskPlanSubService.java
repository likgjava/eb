package com.gpcsoft.epp.taskplan.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanSubService extends BaseGenericService<TaskPlanSub> {

	/**
	 * FuncName: saveTaskPlanSubAndPreqEntry
	 * Description :  保存申报书条目和需求条目
	 * @param taskPlanSub:申报书条目对象,preqEntry:需求对象,taskPlanId:申报书Id
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午01:54:32 
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-10下午01:54:32  
	 */
	public void saveTaskPlanSubAndPreqEntry(TaskPlanSub taskPlanSub,PreqEntry preqEntry, String taskPlanId);

	/** 
	 * FuncName:removeTaskPlanSubAndPreqBySubId
	 * Description :  根据申报书条目ID删除申报书条目和对应的需求条目信息
	 * @param taskPlanSubId:申报书条目主键
	 * @return void 
	 * @author Administrator
	 * @Create Date: 2010-7-10下午03:53:26
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-10下午03:53:26 by Administrator
	 */
	public void removeTaskPlanSubAndPreqBySubId(String taskPlanSubId);
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 删除申报书明细,同时删除关联申报书中间表
	 * @param objId 申报书明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:43:10  
	 */
	public void removeTaskPlanSub(String objId)throws Exception;
	
	/**
	 * FuncName: getTaskPlanSubByTaskPlanSubIds
	 * Description :  根据申报书明细Ids获取申报书明细
	 * @param   page:分页对象,taskPlanSubIds:申报书条目主键
	 * @return  Page<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:32:46 
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午02:32:46 
	 */
	public Page<TaskPlanSub> getTaskPlanSubByTaskPlanSubIds(Page<TaskPlanSub> page,String taskPlanSubIds);
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description :  根据项目Id获取申报书明细
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午03:50:23 
	 * @Modifier yangx    
	 * @Modified Date: 2010-9-2下午03:50:23  
	 */
	public List<TaskPlanSub> getTaskPlanSubByProjectId(String projectId);
	

	/**
	 * FuncName:getLowerLeverTaskPlanSubByTaskPlan
	 * Description: 获取下级申报书汇总明细
	 * @param:taskPlanId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-15 下午06:14:48  
	 */
	public List<TaskPlanSub> getLowerLeverTaskPlanSubByTaskPlan(String taskPlanId)throws EsException;
}
