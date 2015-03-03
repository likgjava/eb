package com.gpcsoft.epp.taskplan.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;

public interface TaskPlanDetailManager extends BaseManager<TaskPlanDetail> {

	/** 
	 * FuncName:saveTaskPlanSet
	 * Description :  保存采购申报书明细（进行中间表关联操作）
	 * @param taskPlanDetail:资金明细实体类,taskPlanId:采购申报书主键
	 * @return void
	 * @author shenjz
	 * @Create Date: Create Date：2010-6-3 下午04:12:39 
	 * @Modifier: shenjz
	 * @Modified Date: 2010-6-3 下午04:12:39 by 
	 */
	public void saveTaskPlanSet(TaskPlanDetail taskPlanDetail, String taskPlanId);
}
