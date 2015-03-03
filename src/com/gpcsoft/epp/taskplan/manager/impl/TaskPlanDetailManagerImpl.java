package com.gpcsoft.epp.taskplan.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.taskplan.dao.TaskPlanDetailDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanDetailManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class TaskPlanDetailManagerImpl extends BaseManagerImpl<TaskPlanDetail> implements TaskPlanDetailManager {

	@Autowired(required=true)  @Qualifier("taskPlanDetailDaoHibernate")
	private TaskPlanDetailDao taskPlanDetailDaoHibernate;
	
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
	public void saveTaskPlanSet(TaskPlanDetail taskPlanDetail, String taskPlanId) {
		taskPlanDetail.setBudgetName(((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getOrgName());//预算单位
		taskPlanDetail = taskPlanDetailDaoHibernate.save(taskPlanDetail);//保存申报书条目表
		TaskPlanMDetail taskPlanMDetail = new TaskPlanMDetail();//构建申报书与申报书条目中间表
		TaskPlan taskPlan = new TaskPlan();//构建申报书对象
		taskPlan.setObjId(taskPlanId);//设置申报书主键
		taskPlanMDetail.setTaskPlan(taskPlan);//申报书
		taskPlanMDetail.setTaskPlanDetail(taskPlanDetail);//申报书条目
		taskPlanMDetail.setStatus(TaskPlanSumEnum.NORMAL);  //非汇总状态
		taskPlanDetailDaoHibernate.save(taskPlanMDetail, TaskPlanMDetail.class);//保存申报书与申报书条目中间表
	}
}
