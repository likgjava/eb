package com.gpcsoft.epp.consign.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

public interface ConsignTaskPlanService extends BaseGenericService<ConsignTaskPlan> {
	
	
	/** 
	 * FuncName:getConsignByTaskPlan
	 * Description :  根据采购计划获得委托协议
	 * @param   taskPlanIds:以逗号分隔的申报书主键
	 * @return  List<Consign>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-6-10下午02:00:34 
	 */
	public List<Consign> getConsignByTaskPlan(String taskPlanIds);
	
	/** 
	 * FuncName:getTaskPlanByConsign
	 * Description :  根据委托协议获得采购计划
	 * @param  consignIds:以逗号分隔的委托协议主键
	 * @return  List<TaskPlan>
	 * @author liangxj
	 * @Create Date: 2010-6-10下午02:00:34 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-10下午02:00:34  
	 */
	public List<TaskPlan> getTaskPlanByConsign(String consignIds);
	
}
