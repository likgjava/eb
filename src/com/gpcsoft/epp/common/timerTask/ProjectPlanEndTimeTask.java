package com.gpcsoft.epp.common.timerTask;

import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.service.ProjectPlanService;

/**
 * 
  *  Comments: <strong>同步项目计划结束时间定时任务</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台     		
  *  <br/>Create Date：2010-3-10 上午10:55:47 by lic    					                            
  *  <br/>Modified Date:  2010-3-10 上午10:55:47 by lic                                 
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class ProjectPlanEndTimeTask extends TimerTask {
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	public void run() {
		try{
			List<ProjectPlan> list = projectPlanService.getProjectPlanByEndDate();//查询出 1.已启动，2.“系统自动完成”设置为“是” 3.启动时间到当前时间超过工期的工作计划
			projectPlanService.saveProjectPlanByAuto(list);//
		}catch(Exception ce){
			ce.printStackTrace();
		}
  }

} 
