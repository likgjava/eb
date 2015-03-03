package com.gpcsoft.epp.bulletin.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.impl.BulletinManagerImpl;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.manager.impl.ProjectManagerImpl;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的起草中标公告操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class DraftResultBulletinDesion implements ProjectPlanDesion {
	private BulletinManagerImpl bulletinManagerImpl;
	private BulletinManagerImpl getBulletinManagerImpl(){
		if(this.bulletinManagerImpl == null){
				this.bulletinManagerImpl = (BulletinManagerImpl)FrameBeanFactory.getBean("bulletinManagerImpl");
		}
		return this.bulletinManagerImpl;
	}
	private ProjectManagerImpl projectManagerImpl;
	private ProjectManagerImpl getProjectManagerImpl(){
		if(this.projectManagerImpl == null){
			this.projectManagerImpl = (ProjectManagerImpl)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManagerImpl;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Bulletin bulletin = (Bulletin)bizObject;
		
		//保存公告状态
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		getBulletinManagerImpl().save(bulletin, Bulletin.class);
		
		//保存项目状态
		getProjectManagerImpl().saveProjProcessStatus(bulletin.getProject().getObjId(),ProjProcessStatusEnum.SUBMIT_RESULT_BULLETIN);//保存项目实施状态
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
