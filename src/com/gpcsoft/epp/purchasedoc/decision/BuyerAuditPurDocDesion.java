package com.gpcsoft.epp.purchasedoc.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的采购人确认招标文件操作
 *  可以操作返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class BuyerAuditPurDocDesion implements ProjectPlanDesion {

	private ProjProcessRuleManager projProcessRuleManager;
	private ProjProcessRuleManager getProjProcessRuleManager(){
		if(this.projProcessRuleManager == null){
				this.projProcessRuleManager = (ProjProcessRuleManager)FrameBeanFactory.getBean("projProcessRuleManagerImpl");
		}
		return this.projProcessRuleManager;
	}
	
	private PurchaseDocManager purchaseDocManager;
	private PurchaseDocManager getPurchaseDocManager(){
		if(this.purchaseDocManager == null){
				this.purchaseDocManager = (PurchaseDocManager)FrameBeanFactory.getBean("purchaseDocManagerImpl");
		}
		return this.purchaseDocManager;
	}
	/** 
	 * Description :  决策是否可以结束采购人确认招标文件操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		PurchaseDoc purchaseDoc =(PurchaseDoc)bizObject;
		if(null!=purchaseDoc.getAuditStatus() &&"Y".equals(purchaseDoc.getWorkflowAuditStatus())) {  //采购人确认通过
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
		}else if(null!=purchaseDoc.getAuditStatus() &&"N".equals(purchaseDoc.getWorkflowAuditStatus())) { //采购人确认不通过
			purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
		}
		this.getPurchaseDocManager().save(purchaseDoc,PurchaseDoc.class);
		return true;
	}

	/** 
	 * Description :  决策是否可以启动采购人确认招标文件操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象[项目对象]
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		//根据项目Id、规则编号获取项目规则[是否采购人确认招标文件]
		ProjProcessRule projectRule = this.getProjProcessRuleManager().getProjProcessRuleByProjectIdAndCode(workFlowModel.getParentBizId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projectRule!=null&&projectRule.getResValue()!=null&&"true".equals(projectRule.getResValue())) {//判断规则是否采购人确认
			checkValue = true;
		}
		return checkValue;
	}

	/** 
	 * Description :  决策是否可以开始做采购人确认招标文件操作
	 * Create Date: 2010-11-19上午10:10:09 by yangx  Modified Date: 2010-11-19上午10:10:09 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject	对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		//根据项目Id、规则编号获取项目规则[是否采购人确认招标文件]
		ProjProcessRule projectRule = this.getProjProcessRuleManager().getProjProcessRuleByProjectIdAndCode(workFlowModel.getParentBizId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projectRule!=null&&projectRule.getResValue()!=null&&"true".equals(projectRule.getResValue())) {//判断规则是否采购人确认
			checkValue = true;
		}
		return checkValue;
	}
}
