/**
 * 
 */
package com.gpcsoft.epp.inviterollrequ.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.manager.InviterollrequManager;
import com.gpcsoft.epp.project.domain.ProjectPlan;


public class InviterollrequDesion implements ProjectPlanDesion{

	private InviterollrequManager inviterollrequManager;
	private InviterollrequManager getInviterollrequManager(){
		if(this.inviterollrequManager == null){
				this.inviterollrequManager = (InviterollrequManager)FrameBeanFactory.getBean("inviterollrequManagerImpl");
		}
		return this.inviterollrequManager;
	}
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		Inviterollrequ inviterollrequ = (Inviterollrequ)bizObject;
		inviterollrequ.setAuditStatus(InrqAuditStatusEnum.AUDIT_PASS);//设置审核状态为审核通过
		inviterollrequ.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//使用状态为正式
		this.getInviterollrequManager().save(inviterollrequ);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
		return true;
	}

}
