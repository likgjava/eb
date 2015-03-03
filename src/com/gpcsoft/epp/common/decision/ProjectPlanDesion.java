package com.gpcsoft.epp.common.decision;

import com.gpcsoft.epp.project.domain.ProjectPlan;



/** 
  *  Comments: <strong>工作计划的决策</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-9-3 上午11:22:35 by liuy    					                            
  *  <br/>Modified Date:  2010-9-3 上午11:22:35 by liuy  
  */ 
public interface ProjectPlanDesion {
	
	/** 
	 * Description :  决策开始做某个业务是否合适
	 * Create Date: 2010-11-1上午11:08:00 by liuy  Modified Date: 2010-11-1上午11:08:00 by liuy
	 * @param projectPlan	对应的工作计划
	 * @param bizObject		对应的业务对象
	 * @return				可操作：true;不可操作：false;默认返回true
	 * @throws Exception
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject) throws Exception;
	
	/** 
	 * Description :  决策启动某个业务是否合适
	 * Create Date: 2010-11-1上午11:08:00 by liuy  Modified Date: 2010-11-1上午11:08:00 by liuy
	 * @param projectPlan	对应的工作计划
	 * @param bizObject		对应的业务对象
	 * @return				可操作：true;不可操作：false;默认返回true
	 * @throws Exception
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject) throws Exception;
	
	/** 
	 * Description :  决策结束某个业务
	 * Create Date: 2010-11-1上午11:08:00 by liuy  Modified Date: 2010-11-1上午11:08:00 by liuy
	 * @param projectPlan	对应的工作计划
	 * @param bizObject		对应的业务对象
	 * @return				可操作：true;不可操作：false;默认返回true
	 * @throws Exception
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject) throws Exception;
}
