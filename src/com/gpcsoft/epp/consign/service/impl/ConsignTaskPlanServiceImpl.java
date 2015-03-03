package com.gpcsoft.epp.consign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.consign.manager.ConsignTaskPlanManager;
import com.gpcsoft.epp.consign.service.ConsignTaskPlanService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

@Service 
public class ConsignTaskPlanServiceImpl extends BaseGenericServiceImpl<ConsignTaskPlan> implements ConsignTaskPlanService {

	@Autowired(required=true) @Qualifier("consignTaskPlanManagerImpl")
	private ConsignTaskPlanManager consignTaskPlanManager;
	
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
	public List<Consign> getConsignByTaskPlan(String taskPlanIds){
		logger.debug("\nes ConsignTaskPlanServiceImpl||getConsignByTaskPlan\n");
	  	return consignTaskPlanManager.getConsignByTaskPlan(taskPlanIds);
	}
	
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
	public List<TaskPlan> getTaskPlanByConsign(String consignIds){
		logger.debug("\nes ConsignTaskPlanServiceImpl||getTaskPlanByConsign\n");
		return consignTaskPlanManager.getTaskPlanByConsign(consignIds);
	}

}
