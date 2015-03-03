package com.gpcsoft.agreement.bargin.project.manager.impl;

import org.springframework.stereotype.Repository;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.bargin.project.manager.RecommendProjectManager;
import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;

@Repository
public class RecommendProjectManagerImpl extends BaseManagerImpl<RecommendProject> implements RecommendProjectManager {

	//@Autowired(required=true)  @Qualifier("recommendProjectDaoHibernate")
	//private RecommendProjectDao recommendProjectDaoHibernate;

}
