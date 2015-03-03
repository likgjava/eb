package com.gpcsoft.epp.bid.decision;

import java.util.Date;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的投标操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-22 下午02:55:21 by yangx   					                            
 *  <br/>Modified Date: 2010-11-22 下午02:55:21 by yangx                                    
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class BidDesion implements ProjectPlanDesion {

	private ProjectManager projectManager;
	private ProjectManager getProjectManager(){
		if(this.projectManager == null){
				this.projectManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManager;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		
		/** 保存项目实施状态 */
		getProjectManager().saveProjProcessStatus(workFlowModel.getParentBizId(),ProjProcessStatusEnum.SUPPLIERS_BID);
		return true;
	}

	/** 
	 * Description :  决策是否可以启动投标操作
	 * Create Date: 2010-11-22下午02:24:30 by yangx  Modified Date: 2010-11-22下午02:24:30 by yangx
	 * @param   projectPlan:对应的工作计划,bizObject:对应的业务对象
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策是否可以开始做投标操作
	 * Create Date: 2010-11-22下午02:24:30 by yangx  Modified Date: 2010-11-22下午02:24:30 by yangx
	 * @param   projectPlan:对应的工作计划,bizObject:对应的业务对象
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		ProjectRule projectRule =this.getProjectManager().getProjectRuleByProjectId(workFlowModel.getParentBizId());//根据项目Id获取项目规则时间
		Date nowDate = new Date();
		if (projectRule.getOpenBidSDate()!=null&&nowDate.after(projectRule.getOpenBidSDate())){//判断是否在开标时间之后
			checkValue = true;
		}
		return checkValue;
	}
}
