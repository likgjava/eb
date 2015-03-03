package com.gpcsoft.agreement.bargin.project.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.bargin.project.dao.AssessmentRuleDao;
import com.gpcsoft.agreement.bargin.project.manager.AssessmentRuleManager;
import com.gpcsoft.agreement.bargin.project.domain.AssessmentRule;

@Repository
public class AssessmentRuleManagerImpl extends BaseManagerImpl<AssessmentRule> implements AssessmentRuleManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("assessmentRuleDaoHibernate")
	private AssessmentRuleDao assessmentRuleDaoHibernate;

}
