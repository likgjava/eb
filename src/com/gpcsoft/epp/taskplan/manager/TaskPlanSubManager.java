package com.gpcsoft.epp.taskplan.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanSubManager extends BaseManager<TaskPlanSub> {
	
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
	 * FuncName:saveTaskPlanSubAndPreqEntry
	 * Description :  保存申报书条目和需求条目
	 * @param taskPlanSub:申报书条目对象,preqEntry:需求对象,taskPlanId:申报书Id
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午01:54:32 
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-10下午01:54:32 
	 */
	public void saveTaskPlanSubAndPreqEntry(TaskPlanSub taskPlanSub,PreqEntry preqEntry, String taskPlanId);
}
