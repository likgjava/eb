package com.gpcsoft.smallscale.expert.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.expert.domain.Training;
import com.gpcsoft.smallscale.expert.manager.TrainingManager;

@Repository
public class TrainingManagerImpl extends BaseManagerImpl<Training> implements TrainingManager {

//	@Autowired(required=true)  @Qualifier("trainingDaoHibernate")
//	private TrainingDao trainingDaoHibernate;

}
