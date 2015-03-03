package com.gpcsoft.bizplatform.organization.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.Score;
import com.gpcsoft.bizplatform.organization.manager.ScoreManager;
import com.gpcsoft.bizplatform.organization.service.ScoreService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ScoreServiceImpl extends BaseGenericServiceImpl<Score> implements ScoreService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("scoreManagerImpl")
	private ScoreManager scoreManager;

}
