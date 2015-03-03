package com.gpcsoft.epp.common.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.dao.FactorDeDao;
import com.gpcsoft.epp.common.domain.FactorDe;
import com.gpcsoft.epp.common.manager.FactorDeManager;

@Repository
public class FactorDeManagerImpl extends BaseManagerImpl<FactorDe> implements FactorDeManager {

	@Autowired(required=true)  @Qualifier("factorDeDaoHibernate")
	private FactorDeDao factorDeDaoHibernate;

}
