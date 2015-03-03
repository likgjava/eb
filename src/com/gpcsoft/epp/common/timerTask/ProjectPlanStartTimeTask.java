package com.gpcsoft.epp.common.timerTask;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.util.Log4jConfigListener;

import sun.util.logging.resources.logging;

import com.gpcsoft.epp.project.service.ProjectPlanService;

/** 
  *  Comments: <strong>定时启动工作计划</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-9-10 上午10:01:31 by liuy    					                            
  *  <br/>Modified Date:  2010-9-10 上午10:01:31 by liuy  
  */ 
public class ProjectPlanStartTimeTask extends TimerTask {
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	public synchronized void run() {
		try{
			/**
			 * 1.获得所有在开标阶段的
			 */
			projectPlanService.updateEndTime();
		}catch(Exception ce){
			ce.printStackTrace();
		}
	}

}
