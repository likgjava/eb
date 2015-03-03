package com.gpcsoft.epp.consign.decision;

import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
 *  Comments: <strong>TaskPlanDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的审核委托协议操作
 *  如：返回true,否则返回false		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-8 上午11:10:22 by yangx    					                            
 *  <br/>Modified Date:  2010-11-8 上午11:10:22 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class AuditConsign implements ProjectPlanDesion {

	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

}
