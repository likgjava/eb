package com.gpcsoft.bizplatform.organization.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.ScoreDao;
import com.gpcsoft.bizplatform.organization.domain.Score;
import com.gpcsoft.bizplatform.organization.manager.ScoreManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class ScoreManagerImpl extends BaseManagerImpl<Score> implements ScoreManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("scoreDaoHibernate")
	private ScoreDao scoreDaoHibernate;

}
