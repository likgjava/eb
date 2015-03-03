package com.gpcsoft.epp.taskplan.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMDetailManager;
import com.gpcsoft.epp.taskplan.service.TaskPlanMDetailService;

@Service 
public class TaskPlanMDetailServiceImpl extends BaseGenericServiceImpl<TaskPlanMDetail> implements TaskPlanMDetailService {

	@Autowired(required=true) @Qualifier("taskPlanMDetailManagerImpl")
	private TaskPlanMDetailManager taskPlanMDetailManager;
	
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
	public List<TaskPlanMDetail> getSubNotSumDetailsByOrg(String objId,String status, String taskType,String ebuyMethod){
		logger.debug("\nes=TaskPlanMDetailServiceImpl||getSubNotSumDetailsByOrg\n");
		return taskPlanMDetailManager.getSubNotSumDetailsByOrg(objId,status, taskType,ebuyMethod);
	}

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
	public List<TaskPlanDetail> getDetailByTaskPlan(String taskPlanIds) {
		logger.debug("\nes=TaskPlanMDetailServiceImpl||getDetailByTaskPlan\n");
		return taskPlanMDetailManager.getDetailByTaskPlan(taskPlanIds);
	}
	
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
	public float getTaskPlanMDetailByStatus(String taskPlanId ) {
		logger.debug("\nes=TaskPlanMDetailServiceImpl||getTaskPlanMDetailByStatus\n");
		float moneyDetail = 0;
		List<TaskPlanMDetail> taskPlanMDetails = taskPlanMDetailManager.getTaskPlanMDetailByStatus(taskPlanId);
		if(taskPlanMDetails.size()!=0){
			for (Iterator<TaskPlanMDetail> iterator = taskPlanMDetails.iterator(); iterator
					.hasNext();) {
				TaskPlanMDetail taskPlanMDetail = (TaskPlanMDetail) iterator
						.next();
				TaskPlanDetail taskPlanDetail = taskPlanMDetail.getTaskPlanDetail();
				moneyDetail+=Double.parseDouble(taskPlanDetail.getQuantity());
			}
		}
		return moneyDetail;
	}
}
