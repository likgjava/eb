package com.gpcsoft.epp.taskplan.decision;

import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
  *  Comments: <strong>TaskPlanDesion</strong>            		
  *	 <br/>
  *  根据工作计划和业务实体，判断是否是当前的采购人提交申报书操作
  *  如：返回true,否则返回false		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-11-8 上午11:10:22 by yangx    					                            
  *  <br/>Modified Date:  2010-11-8 上午11:10:22 by yangx                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
public class BuyerDraftTaskPlanDesion implements ProjectPlanDesion{

	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策启动提交申报书业务是否合适
	 * Create Date: 2010-11-18上午09:49:57 by yangx  Modified Date: 2010-11-18上午09:49:57 by yangx
	 * @param   projectPlan	对应的工作计划
	 * @param 	bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策开始做提交申报书业务是否合适
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
