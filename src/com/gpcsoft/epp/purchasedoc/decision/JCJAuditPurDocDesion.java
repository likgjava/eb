package com.gpcsoft.epp.purchasedoc.decision;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
  *  Comments: <strong>PurchaseDocDesion</strong>            		
  *	 <br/>
  *  根据工作计划和业务实体，判断是否是当前的监察局审核操作
  *  如：传进来的是监察局审核的工作计划，那么根据业务大于50万才需要审核，
  *  所以，当业务对象拿到后，查询出对应的预算金额，若大于50万，返回true,否则，返回false
  *  		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-9-3 上午11:24:44 by liuy    					                            
  *  <br/>Modified Date:  2010-9-3 上午11:24:44 by liuy  
  */ 
public class JCJAuditPurDocDesion implements ProjectPlanDesion {
	
	private ProjectMTaskPlanManager projectMTaskPlanManager;
	private ProjectMTaskPlanManager getProjectMTaskPlanManager(){
		if(this.projectMTaskPlanManager == null){
				this.projectMTaskPlanManager = (ProjectMTaskPlanManager)FrameBeanFactory.getBean("projectMTaskPlanManagerImpl");
		}
		return this.projectMTaskPlanManager;
	}
	
	private PurchaseDocManager purchaseDocManager;
	private PurchaseDocManager getPurchaseDocManager(){
		if(this.purchaseDocManager == null){
				this.purchaseDocManager = (PurchaseDocManager)FrameBeanFactory.getBean("purchaseDocManagerImpl");
		}
		return this.purchaseDocManager;
	}
	/** 
	 * Description :  决策启动某个业务是否合适
	 * Create Date: 2010-11-1上午11:08:00 by liuy  Modified Date: 2010-11-1上午11:08:00 by liuy
	 * @param projectPlan	对应的工作计划
	 * @param bizObject		对应的业务对象
	 * @return				可操作："true";不可操作："false"
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		Double sumPrice = 0.0;
		List taskPalnSubList = this.getProjectMTaskPlanManager().getAllTaskPlanSubByProject(workFlowModel.getParentBizId());
		for (int i=0;i<taskPalnSubList.size();i++) {
			TaskPlanSub taskPlanSub = (TaskPlanSub) taskPalnSubList.get(i);
			sumPrice += taskPlanSub.getTotalPrice().doubleValue();
		}
		if ("saveForJcjAudit".equals(projectPlan.getServiceMethodName()) && sumPrice>=(PurchaseDocEnum.SUMPRICE)) {//若大于50万且工作计划是"监察局审核"
			checkValue = true;
		}
		return checkValue;
	}
	
	/** 
	 * Description :   判断决策是否可以结束监察局审核招标文件业务
	 * Create Date: 2011-1-19下午03:45:45 by yangx  Modified Date: 2011-1-19下午03:45:45 by yangx
	 * @param   projectPlan	对应的工作计划,bizObject		对应的业务对象
	 * @return  可操作：true;不可操作：false;默认返回true
	 * @Exception   
	 */
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		PurchaseDoc purchaseDoc = this.getPurchaseDocManager().getPurchaseDocByProjectId(projectPlan.getProject().getObjId());
		if (WorkFlowModel.AUDIT_STATUS_PASS.equals(workFlowModel.getWorkflowAuditStatus())) {
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//通过
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
		}
		this.getPurchaseDocManager().save(purchaseDoc);
		return true;
	}
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject) throws Exception {
		return true;
	}

}
