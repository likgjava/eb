package com.gpcsoft.epp.taskplan.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;

public interface TaskPlanDetailService extends BaseGenericService<TaskPlanDetail> {
	
	/** 
	 * FuncName:saveTaskPlanSet
	 * Description:保存采购申报书明细（进行中间表关联操作）
	 * @param taskPlanDetail:资金明细实体类,taskPlanId:采购申报书ID
	 * @return void
	 * @author guom
	 * @Create Date: Create Date：2010-6-3 下午04:12:39 
	 * @Modifier: guom 
	 * @Modified Date: 2010-6-3 下午04:12:39 by guom
	 */
	public void saveTaskPlanSet(TaskPlanDetail taskPlanDetail,String objId);
	
	/**
	 * FuncName:updateTaskPlanSet
	 * Description: 修改采购资金明细
	 * @param taskPlanDetail:资金明细实体类
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-6 下午06:22:06   
	 * @Modifier wanghz
	 * @Modified Date:2010-9-6 下午14:12:39 
	 */
	public void updateTaskPlanSet(TaskPlanDetail taskPlanDetail);

	/**
	 * FuncName: getTaskPlanDetailList
	 * Description :  查询采购资金明细批准文号
	 * @return List<TaskPlanDetail>
	 * @author: shenjz
	 * @Create Date:2011-10-8  下午04:47:49
	 * @Modifier: shenjz
	 * @Modified Date:2011-10-8  下午04:47:49
	 */
	public List getTaskPlanDetailList(String taskPlanId,String approvalNumber);
}
