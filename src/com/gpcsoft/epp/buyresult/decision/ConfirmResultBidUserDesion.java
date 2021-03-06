package com.gpcsoft.epp.buyresult.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.manager.ProjectManager;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的确认中标人操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class ConfirmResultBidUserDesion implements ProjectPlanDesion {
	private ProjectManager projectManager;
	private ProjectManager getProjectManager(){
		if(this.projectManager == null){
				this.projectManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManager;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		BuyResult buyResult = (BuyResult)bizObject;
		
		/** 保存项目实施状态 */
		getProjectManager().saveProjProcessStatus(buyResult.getProject().getObjId(), ProjProcessStatusEnum.CALIBRATION_BID);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
