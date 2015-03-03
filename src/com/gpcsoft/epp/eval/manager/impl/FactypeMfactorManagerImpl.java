package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.FactypeMfactorDao;
import com.gpcsoft.epp.eval.domain.FactypeMfactor;
import com.gpcsoft.epp.eval.manager.FactypeMfactorManager;

@Repository
public class FactypeMfactorManagerImpl extends BaseManagerImpl<FactypeMfactor> implements FactypeMfactorManager {

	@Autowired(required=true)  @Qualifier("factypeMfactorDaoHibernate")
	private FactypeMfactorDao factypeMfactorDaoHibernate;

}
