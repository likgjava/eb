package com.gpcsoft.pubservice.application.message.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.message.dao.AdviseDao;
import com.gpcsoft.pubservice.application.message.manager.AdviseManager;
import com.gpcsoft.pubservice.application.message.domain.Advise;

@Repository
public class AdviseManagerImpl extends BaseManagerImpl<Advise> implements AdviseManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("adviseDaoHibernate")
	private AdviseDao adviseDaoHibernate;

}
