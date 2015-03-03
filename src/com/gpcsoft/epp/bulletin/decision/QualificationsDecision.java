package com.gpcsoft.epp.bulletin.decision;

import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
 *  Comments: <strong>QualificationsDecision</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2011-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   zh-epp                    					          
 *  <br/>Module ID:     		
 *  <br/>Create Date：2011-11-17 下午02:26:25 by chenhongjun    					                            
 *  <br/>Modified Date:  2011-11-17 下午02:26:25 by chenhongjun                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
public class QualificationsDecision   implements
		ProjectPlanDesion {

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-11-17下午02:26:25 by chenhongjun  
	 *  Modified Date: 2011-11-17下午02:26:25 by chenhongjun 
	 * @see com.gpcsoft.epp.common.decision.ProjectPlanDesion#endDecide(com.gpcsoft.epp.project.domain.ProjectPlan, java.lang.Object)
	 *
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-11-17下午02:26:25 by chenhongjun  
	 *  Modified Date: 2011-11-17下午02:26:25 by chenhongjun 
	 * @see com.gpcsoft.epp.common.decision.ProjectPlanDesion#launchDecide(com.gpcsoft.epp.project.domain.ProjectPlan, java.lang.Object)
	 *
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-11-17下午02:26:25 by chenhongjun  
	 *  Modified Date: 2011-11-17下午02:26:25 by chenhongjun 
	 * @see com.gpcsoft.epp.common.decision.ProjectPlanDesion#startDecide(com.gpcsoft.epp.project.domain.ProjectPlan, java.lang.Object)
	 *
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
