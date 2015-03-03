package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.CongruousFactorDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.manager.CongruousFactorManager;

@Repository
public class CongruousFactorManagerImpl extends BaseManagerImpl<CongruousFactor> implements CongruousFactorManager {

	@Autowired(required=true)  @Qualifier("congruousFactorDaoHibernate")
	private CongruousFactorDao congruousFactorDaoHibernate;

}
