package com.gpcsoft.epp.purchasedoc.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的采购办审核招标文件操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class SuperviseAuditPurDocDesion implements ProjectPlanDesion {
	
	private PurchaseDocManager purchaseDocManager;
	private PurchaseDocManager getPurchaseDocManager(){
		if(this.purchaseDocManager == null){
				this.purchaseDocManager = (PurchaseDocManager)FrameBeanFactory.getBean("purchaseDocManagerImpl");
		}
		return this.purchaseDocManager;
	}
	
	private FrameMessageResource messageSource;
	private FrameMessageResource getMessageSource(){
		if(this.messageSource == null){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	
	/**
	 * created by zhouzhanghe at 2011.3.14 9:22
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		PurchaseDoc purchaseDoc =(PurchaseDoc)bizObject;
		if(null!=purchaseDoc.getAuditStatus() &&"Y".equals(purchaseDoc.getAuditStatus())) {  //采购人确认通过
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);	
			if("01".equals(projectPlan.getTaskType())){//若当前工作计划是审批节点
				if(!"end".equals(projectPlan.getTaskTypeValue())){//不是最后一个审批节点，则需要将业务对象仍置为待审状态
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
				}else{
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);
				}
			}else {//如果不是审批节点，则将业务数据审批通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);
			}
		} else if(null!=purchaseDoc.getAuditStatus() &&"N".equals(purchaseDoc.getAuditStatus())) { //采购人确认不通过
			purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
		}else{
			throw new EsException(getMessageSource().getMessage(EsExceptionEnum.ES_BULLETIN_AUDITSTATUS_IS_INVALID));
		}
		this.getPurchaseDocManager().save(purchaseDoc);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
	
	
}
