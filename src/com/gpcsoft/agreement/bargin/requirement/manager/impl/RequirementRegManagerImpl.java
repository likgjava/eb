package com.gpcsoft.agreement.bargin.requirement.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.requirement.dao.RequirementRegDao;
import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.agreement.bargin.requirement.manager.RequirementRegManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class RequirementRegManagerImpl extends BaseManagerImpl<RequirementReg> implements RequirementRegManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("requirementRegDaoHibernate")
	private RequirementRegDao requirementRegDaoHibernate;

}
