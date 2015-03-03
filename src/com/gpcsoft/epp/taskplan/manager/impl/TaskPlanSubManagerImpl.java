package com.gpcsoft.epp.taskplan.manager.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.requirement.dao.PreqEntryDao;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.taskplan.dao.TaskPlanSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanSubManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class TaskPlanSubManagerImpl extends BaseManagerImpl<TaskPlanSub> implements TaskPlanSubManager {
	
	@Autowired(required=true)  @Qualifier("taskPlanSubDaoHibernate")
	private TaskPlanSubDao taskPlanSubDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("preqEntryDaoHibernate")
	private PreqEntryDao preqEntryDaoHibernate;
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 删除申报书明细,同时删除关联申报书中间表
	 * @param objId 申报书明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:43:10  
	 */
	public void removeTaskPlanSub(String objId) throws Exception {
		this.remove(objId, TaskPlanSub.class);
		taskPlanSubDaoHibernate.removeTaskPlanSub(objId);
	}
	
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
	public void saveTaskPlanSubAndPreqEntry(TaskPlanSub taskPlanSub,PreqEntry preqEntry, String taskPlanId) {
		logger.debug("\nTaskPlanSubServiceImpl||saveTaskPlanSubAndPreqEntry\n");
		if (taskPlanSub.getQuantity().compareTo(new BigDecimal("0"))<= 0) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_NO_QUANTITY));
		}else{//计算单价
			taskPlanSub.setUnitPrice(taskPlanSub.getTotalPrice().divide(taskPlanSub.getQuantity(),2));
		}
		if(taskPlanSub.getObjId() == null){//新增，同时保存中间表数据
			taskPlanSub.setBudgetName(((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getOrgName());//预算单位
			taskPlanSub = taskPlanSubDaoHibernate.save(taskPlanSub);
			TaskPlanMSub taskPlanMSub = new TaskPlanMSub();
			TaskPlan taskPlan = new TaskPlan();//申报书
			taskPlan.setObjId(taskPlanId);
			taskPlanMSub.setTaskPlan(taskPlan);
			taskPlanMSub.setTaskPlanSub(taskPlanSub);//申报书条目
			taskPlanMSub.setStatus(TaskPlanSumEnum.NORMAL);  //非汇总状态
			taskPlanSubDaoHibernate.save(taskPlanMSub, TaskPlanMSub.class);
		}else{//修改
			taskPlanSubDaoHibernate.save(taskPlanSub);
		}
		TaskPlan taskPlan = new TaskPlan();
		taskPlan.setObjId(taskPlanId);
		preqEntry.setTaskPlan(taskPlan);
		preqEntry.setQuantity(taskPlanSub.getQuantity());
		preqEntry.setTotalPrice(taskPlanSub.getTotalPrice());
		preqEntry.setPurchase(taskPlanSub.getPurchase());
		preqEntry.setPurchaseCode(taskPlanSub.getPurchase().getObjId());
		preqEntry.setPurchaseName(taskPlanSub.getPurchaseName());
		preqEntry.setTaskPlanSub(taskPlanSub);
		preqEntry.setUnit(taskPlanSub.getUnit());
		preqEntry.setBuyer((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo());
		preqEntryDaoHibernate.save(preqEntry);
	}
}
