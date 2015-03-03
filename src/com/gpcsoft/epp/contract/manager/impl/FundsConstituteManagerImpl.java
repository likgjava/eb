package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.FundsConstituteDao;
import com.gpcsoft.epp.contract.domain.FundsConstitute;
import com.gpcsoft.epp.contract.manager.FundsConstituteManager;

@Repository
public class FundsConstituteManagerImpl extends BaseManagerImpl<FundsConstitute> implements FundsConstituteManager {

	@Autowired(required=true)  @Qualifier("fundsConstituteDaoHibernate")
	private FundsConstituteDao fundsConstituteDaoHibernate;

}
