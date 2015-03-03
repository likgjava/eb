package com.gpcsoft.epp.requirement.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.requirement.dao.PurRequirementDao;
import com.gpcsoft.epp.requirement.domain.PurRequirement;

@Repository
public class PurRequirementDaoHibernate extends BaseGenericDaoHibernate<PurRequirement> implements PurRequirementDao {
	
}
