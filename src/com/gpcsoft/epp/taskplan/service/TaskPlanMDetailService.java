package com.gpcsoft.epp.taskplan.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;

public interface TaskPlanMDetailService extends BaseGenericService<TaskPlanMDetail> {
	
	/** 
	 * FuncName:getSubNotSumDetailsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param objId:部门ID,status:为汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMDetail>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39  
	 * @Modifier liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMDetail> getSubNotSumDetailsByOrg(String objId,String status, String taskType,String ebuyMethod);
	
	/** 
	 * FuncName:getDetailByTaskPlan
	 * Description : 根据申报书的主键，获得资金明细
	 * @param taskPlanIds:以逗号分割的申报书主键
	 * @return List<TaskPlanDetail>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39    
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanDetail> getDetailByTaskPlan(String taskPlanIds);
	
	/**
	 * FuncName:getTaskPlanMDetailByStatus
	 * Description : 获取本级的资金明细
	 * @param taskPlanId:申报书主键
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:25:15  
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:25:15 
	 */	
	public float getTaskPlanMDetailByStatus(String taskPlanId);
}
