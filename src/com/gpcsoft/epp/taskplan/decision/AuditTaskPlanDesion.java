package com.gpcsoft.epp.taskplan.decision;

import java.util.List;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 *  Comments: <strong>TaskPlanDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的审核采购申报书操作
 *  如：返回true,否则返回false		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-8 上午11:10:22 by yangx    					                            
 *  <br/>Modified Date:  2010-11-8 上午11:10:22 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class AuditTaskPlanDesion implements ProjectPlanDesion{

	private TaskPlanManager taskPlanManager;
	private TaskPlanManager getTaskPlanManager(){
		if (this.taskPlanManager==null) {
			this.taskPlanManager = (TaskPlanManager)FrameBeanFactory.getBean("taskPlanManagerImpl");
		}
		return this.taskPlanManager;
	}
	
	/** 
	 * Description :  决策结束审核申报书业务
	 * Create Date: 2010-12-29上午11:26:40 by yangx  Modified Date: 2010-12-29上午11:26:40 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		List<TaskPlan> listTaskPlan = this.getTaskPlanManager().getTaskPlanListByTaskPlanIds(workFlowModel.getParentBizId());//根据Ids查询申报书
		for (int i=0;i<listTaskPlan.size();i++) {//循环申报书
			TaskPlan taskPlan = listTaskPlan.get(i);
			if (WorkFlowModel.AUDIT_STATUS_PASS.equals(workFlowModel.getWorkflowAuditStatus())) {//判断是否为通过
				if(taskPlan.getTaskType().equals(TaskPlanTypeEnum.BLOCK_TRADE)){//如果申报书是大宗交易
					taskPlan.setConfirmStatus(TaskPlanConfirmEnum.SELECT_AGENT_PASS);	
				}else{
					taskPlan.setConfirmStatus(TaskPlanConfirmEnum.AUDIT_PASS);				// 02
				}
				taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);					// 01
				this.getTaskPlanManager().save(taskPlan);
			} else {
				taskPlan.setLeader(null);
				taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);					// 01
				taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_BACK);				// 04
				this.getTaskPlanManager().save(taskPlan);
			}
		}
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
