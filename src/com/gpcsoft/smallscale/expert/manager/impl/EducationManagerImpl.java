package com.gpcsoft.smallscale.expert.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.expert.domain.Education;
import com.gpcsoft.smallscale.expert.manager.EducationManager;

@Repository
public class EducationManagerImpl extends BaseManagerImpl<Education> implements EducationManager {

//	@Autowired(required=true)  @Qualifier("educationDaoHibernate")
//	private EducationDao educationDaoHibernate;

}
