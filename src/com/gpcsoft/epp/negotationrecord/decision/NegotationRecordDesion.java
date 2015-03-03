package com.gpcsoft.epp.negotationrecord.decision;

import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.project.domain.ProjectPlan;

public class NegotationRecordDesion implements ProjectPlanDesion{

	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)
			throws Exception {
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
