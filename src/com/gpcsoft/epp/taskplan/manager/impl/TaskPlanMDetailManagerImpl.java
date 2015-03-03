package com.gpcsoft.epp.taskplan.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDetailDao;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMDetailDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMDetailManager;

@Repository
public class TaskPlanMDetailManagerImpl extends BaseManagerImpl<TaskPlanMDetail> implements TaskPlanMDetailManager {

	@Autowired(required=true)  @Qualifier("taskPlanMDetailDaoHibernate")
	private TaskPlanMDetailDao taskPlanMDetailDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("taskPlanDetailDaoHibernate")
	private TaskPlanDetailDao taskPlanDetailDaoHibernate;

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
		return taskPlanMDetailDaoHibernate.getSubNotSumDetailsByOrg(objId,status, taskType,ebuyMethod);
	}
	
	/** 
	 * FuncName:saveMDetailsByTaskPlan
	 * Description : 根据TaskPlan的id，将其下的条目和明细汇总
	 * @param taskPlanIds:以逗号分割的申报书主键,sumTaskPlanId:汇总的申报书id,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void saveMDetailsByTaskPlan(String taskPlanIds,String sumTaskPlanId,String status){
		List<TaskPlanDetail> taskPlanList = this.getDetailByTaskPlan(taskPlanIds);
		for (TaskPlanDetail taskPlanDetail : taskPlanList) {
			TaskPlanMDetail taskPlanMDetail = new TaskPlanMDetail();
			TaskPlan taskPlan = new TaskPlan();
			taskPlanMDetail.setStatus(TaskPlanSumEnum.SUMMARY);
			taskPlanMDetail.setTaskPlanDetail(taskPlanDetail);
			taskPlan.setObjId(sumTaskPlanId);
			taskPlanMDetail.setTaskPlan(taskPlan);
			taskPlanMDetailDaoHibernate.save(taskPlanMDetail);
		}
	}
	
	/** 
	 * FuncName:getDetailByTaskPlan
	 * Description : 根据TaskPlan的id，获得资金明细
	 * @param taskPlanIds:以逗号分割申报书主键
	 * @return List<TaskPlanDetail>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj   
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanDetail> getDetailByTaskPlan(String taskPlanIds){
		String[] ids = taskPlanIds.split(",");
		String tIds = "";
		for (String id : ids) {
			tIds += "'" + id + "'," ;
		}
		tIds = tIds.substring(0,tIds.length()-1);
		List<TaskPlanDetail> taskPlanList = taskPlanDetailDaoHibernate.findbyHql("select t.taskPlanDetail from TaskPlanMDetail t where t.taskPlan.objId in ( "+tIds+" )");
		return taskPlanList;
	}
	
	/** 
	 * FuncName:removeSumMDetails
	 * Description : 根据申报书主键，删除所属的申报书所有汇总的资金明细，不级联删除taskPlanDetail
	 * @param taskPlanIds:申报书主键,status:汇总状态  
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void removeSumMDetails(final String taskPlands, final String status){
		taskPlanMDetailDaoHibernate.removeSumMDetails(taskPlands, status);
	}
	
	/**
	 * FuncName:getTaskPlanMDetailByStatus
	 * Description : 获取本级的资金明细
	 * @param taskPlanId:申报书主键
	 * @return  List<TaskPlanDetail>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:25:15 
	 * @Modifier    shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:25:15  
	 */	
	public List<TaskPlanMDetail> getTaskPlanMDetailByStatus(String taskPlanId ) {
		logger.debug("\nes=TaskPlanMDetailServiceImpl||getTaskPlanMDetailByStatus\n");
		return taskPlanMDetailDaoHibernate.getTaskPlanMDetailByStatus(taskPlanId);
	}
}
