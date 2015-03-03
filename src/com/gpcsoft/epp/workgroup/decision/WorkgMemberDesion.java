package com.gpcsoft.epp.workgroup.decision;

import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
 *  Comments: <strong>WorkgMemberDesion</strong>            		
 *	 <br/>
 *  业务决策类
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by zhouzhanghe    					                            
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class WorkgMemberDesion implements ProjectPlanDesion{

	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		return true;
	}

}
