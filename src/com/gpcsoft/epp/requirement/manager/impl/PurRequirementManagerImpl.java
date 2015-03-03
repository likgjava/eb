package com.gpcsoft.epp.requirement.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.requirement.dao.PurRequirementDao;
import com.gpcsoft.epp.requirement.domain.PurRequirement;
import com.gpcsoft.epp.requirement.manager.PurRequirementManager;

@Repository
public class PurRequirementManagerImpl extends BaseManagerImpl<PurRequirement> implements PurRequirementManager {

	@Autowired(required=true)  @Qualifier("purRequirementDaoHibernate")
	private PurRequirementDao purRequirementDaoHibernate;
  
}
