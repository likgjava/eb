package com.gpcsoft.agreement.bargin.requirement.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementDao;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.manager.RequirementManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class RequirementManagerImpl extends BaseManagerImpl<Requirement> implements RequirementManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("requirementDaoHibernate")
	private RequirementDao requirementDaoHibernate;

}
