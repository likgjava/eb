package com.gpcsoft.epp.common.utils;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;

public class WorkFlowAdvice implements AfterReturningAdvice {
	
	/** 
	 * 注入工作流业务逻辑构件
	*/
	private com.gpcsoft.plugin.wflow.manager.WorkFlowManager workFlowManager;
	
	private ProjectPlanManager projectPlanManager;
	
	//拿到JBPM的所有服务的入口
	private com.gpcsoft.plugin.wflow.manager.WorkFlowManager getWorkFlowManager(){
		if(null == this.workFlowManager){
			this.workFlowManager = (com.gpcsoft.plugin.wflow.manager.WorkFlowManager)FrameBeanFactory.getBean("workFlowWaitTaskImpl");
		}
		return this.workFlowManager;
	}
	private ProjectPlanManager getProjectPlanManager(){
		if (null == this.projectPlanManager) {
			this.projectPlanManager = (ProjectPlanManager)FrameBeanFactory.getBean("projectPlanManagerImpl");
		}
		return this.projectPlanManager;
	}


	public void afterReturning(Object returnPara, Method methodName, Object[] paras,
			Object className) throws Throwable {
		
		this.getProjectPlanManager().finishPlan(returnPara, methodName, paras, className);//完成工作计划，并相关工作计划的状态
	}

}
