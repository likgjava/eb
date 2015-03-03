package com.gpcsoft.bizplatform.base.industry.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.base.industry.dao.IndustryDao;
import com.gpcsoft.bizplatform.base.industry.manager.IndustryManager;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;

@Repository
public class IndustryManagerImpl extends BaseManagerImpl<Industry> implements IndustryManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("industryDaoHibernate")
	private IndustryDao industryDaoHibernate;

}
