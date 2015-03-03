package com.gpcsoft.bizplatform.base.evaluate.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.evaluate.dao.QuotaDao;
import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.bizplatform.base.evaluate.manager.QuotaManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class QuotaManagerImpl extends BaseManagerImpl<Quota> implements QuotaManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("quotaDaoHibernate")
	private QuotaDao quotaDaoHibernate;

}
