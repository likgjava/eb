package com.gpcsoft.epp.bulletin.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.impl.BulletinManagerImpl;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的审核招标公告操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class AuditPurBulletinDesion implements ProjectPlanDesion {
	private BulletinManagerImpl bulletinManagerImpl;
	private BulletinManagerImpl getBulletinManagerImpl(){
		if(this.bulletinManagerImpl == null){
				this.bulletinManagerImpl = (BulletinManagerImpl)FrameBeanFactory.getBean("bulletinManagerImpl");
		}
		return this.bulletinManagerImpl;
	}
	
	private FrameMessageResource messageSource;
	private FrameMessageResource getMessageSource(){
		if(this.messageSource == null){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Bulletin bulletin = (Bulletin)bizObject;
		//修改公告状态
		if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
			if("01".equals(projectPlan.getTaskType())){//若当前工作计划是审批节点
				if(!"end".equals(projectPlan.getTaskTypeValue())){//不是最后一个审批节点，则需要将业务对象仍置为待审状态
					bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
				}else{
					bulletin.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
				}
			}else {//如果不是审批节点，则将业务数据审批通过
				bulletin.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
			}
		}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
			bulletin.setAuditStatus(AuditStatusEnum.AUDIT_NO_PASS);
		}else
			throw new EsException(getMessageSource().getMessage(EsExceptionEnum.ES_BULLETIN_AUDITSTATUS_IS_INVALID));
		getBulletinManagerImpl().save(bulletin, Bulletin.class);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
