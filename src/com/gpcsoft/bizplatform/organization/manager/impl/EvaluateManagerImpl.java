package com.gpcsoft.bizplatform.organization.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.EvaluateDao;
import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.manager.EvaluateManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class EvaluateManagerImpl extends BaseManagerImpl<Evaluate> implements EvaluateManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("evaluateDaoHibernate")
	private EvaluateDao evaluateDaoHibernate;

}
