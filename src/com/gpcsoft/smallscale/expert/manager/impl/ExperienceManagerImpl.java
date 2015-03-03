package com.gpcsoft.smallscale.expert.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.expert.domain.Experience;
import com.gpcsoft.smallscale.expert.manager.ExperienceManager;

@Repository
public class ExperienceManagerImpl extends BaseManagerImpl<Experience> implements ExperienceManager {

//	@Autowired(required=true)  @Qualifier("experienceDaoHibernate")
//	private ExperienceDao experienceDaoHibernate;

}
