package com.gpcsoft.epp.bulletin.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的发布中标公示操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */
public class DraftResultPublicityDesion  implements ProjectPlanDesion {

	private ProjProcessRuleManager projProcessRuleManager;
	private ProjProcessRuleManager getProjProcessRuleManager(){
		if(this.projProcessRuleManager == null){
				this.projProcessRuleManager = (ProjProcessRuleManager)FrameBeanFactory.getBean("projProcessRuleManagerImpl");
		}
		return this.projProcessRuleManager;
	}
	
	/** 
	 * Description :  决策是否可以结束发布中标公示操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策是否可以启动起草发布中标公示操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		//根据项目Id、规则编号获取项目规则[是否是发布中标公示]
		ProjProcessRule projectRule = this.getProjProcessRuleManager().getProjProcessRuleByProjectIdAndCode(workFlowModel.getParentBizId(),SysConfigEnum.PUBLISHRESULTREVIEW);
		if (projectRule!=null&&projectRule.getResValue()!=null&&"true".equals(projectRule.getResValue())) {//判断规则是否发布中标公示
			checkValue = true;
		}
		return checkValue;
	}

	/** 
	 * Description :  决策是否可以开始操作起草发布中标公示操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		//根据项目Id、规则编号获取项目规则[是否是发布中标公示]
		ProjProcessRule projectRule = this.getProjProcessRuleManager().getProjProcessRuleByProjectIdAndCode(workFlowModel.getParentBizId(),SysConfigEnum.PUBLISHRESULTREVIEW);
		if (projectRule!=null&&projectRule.getResValue()!=null&&"true".equals(projectRule.getResValue())) {//判断规则是否发布中标公示
			checkValue = true;
		}
		return checkValue;
	}
}
