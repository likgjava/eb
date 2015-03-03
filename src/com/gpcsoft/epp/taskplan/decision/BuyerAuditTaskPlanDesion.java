package com.gpcsoft.epp.taskplan.decision;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
 *  Comments: <strong>TaskPlanDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的采购人审核申报书操作
 *  如：返回true,否则返回false		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-8 上午11:10:22 by yangx    					                            
 *  <br/>Modified Date:  2010-11-8 上午11:10:22 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class BuyerAuditTaskPlanDesion implements ProjectPlanDesion {

	private UserApiManager userApiManager;
	private UserApiManager getUserApiManager(){
		if (this.userApiManager==null) {
			this.userApiManager = (UserApiManager)FrameBeanFactory.getBean("userApiManagerImpl");
		}
		return this.userApiManager;
	}

	private TaskPlanMSubManager taskPlanMSubManager;
	private TaskPlanMSubManager getTaskPlanMSubManager(){
		if (this.taskPlanMSubManager==null) {
			this.taskPlanMSubManager = (TaskPlanMSubManager)FrameBeanFactory.getBean("taskPlanMSubManagerImpl");
		}
		return this.taskPlanMSubManager;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策启动采购人审核申报书业务是否合适
	 * Create Date: 2010-11-18上午09:49:57 by yangx  Modified Date: 2010-11-18上午09:49:57 by yangx
	 * @param   projectPlan	对应的工作计划
	 * @param 	bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		/**
		 * 1.根据当前对象判断是几级采购人 
		 * 2.如果是二级采购人,启动采购人审核申报书业务,返回true
		 * 3.如果是一级采购人,判断申报书是否汇总过
		 * 3.1汇总过申报书,查询出汇总过的申报书,做相应的业务流程处理,不启动采购人审核申报书业务,返回false
		 * 3.2没有汇总过申报书,不启动采购人审核申报书业务,返回false
		 */
		Boolean checkValue = false;
		TaskPlan taskPlan = (TaskPlan)bizObject;
		User user = taskPlan.getUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		//根据当前人查询部门信息
		Company company = this.getUserApiManager().getOrganizationByOrgInfoId(orgInfo.getObjId());
		if (company.getParent()!=null&&company.getParent().getObjId()!=null) {//二级采购单位
			checkValue = true;
		}else{//判断是否为一级采购单位
			//查询汇总的申报书
			List<TaskPlanMSub> TaskPlanMSubList = this.getTaskPlanMSubManager().getTaskPlanMSubByTaskPlanIdAndStatus(taskPlan.getObjId(),TaskPlanSumEnum.SUMMARY);
			if (TaskPlanMSubList!=null&&TaskPlanMSubList.size()>0) {//查询是否有汇总的申报书
				//处理汇总申报书相关流程	
			}
		}
		return checkValue;
	}

	/** 
	 * Description :  决策开始做采购人审核申报书业务是否合适
	 * Create Date: 2010-11-18上午09:49:57 by yangx  Modified Date: 2010-11-18上午09:49:57 by yangx
	 * @param   projectPlan	对应的工作计划
	 * @param 	bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

}
