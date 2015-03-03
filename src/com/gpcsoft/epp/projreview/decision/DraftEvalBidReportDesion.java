package com.gpcsoft.epp.projreview.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.manager.ProjectManager;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的生成评标报告操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class DraftEvalBidReportDesion implements ProjectPlanDesion {
	private ProjectManager projectManager;
	private ProjectManager getProjectManager(){
		if(this.projectManager == null){
				this.projectManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManager;
	}
	
	private BulletinManager bulletinManager;
	private BulletinManager getBulletinManager(){
		if(this.bulletinManager == null){
			this.bulletinManager = (BulletinManager)FrameBeanFactory.getBean("bulletinManagerImpl");
	}
	return this.bulletinManager;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Bulletin bulletin = (Bulletin)bizObject;

		//保存评审报告状态
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);  //设置使用状态为正式
		getBulletinManager().save(bulletin, Bulletin.class);
		
		//保存项目状态
		getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(), ProjProcessStatusEnum.GENERATE_EVALUATION_REPORT);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
