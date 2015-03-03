package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractAcceptanceDao;
import com.gpcsoft.epp.contract.domain.ContractAcceptance;
import com.gpcsoft.epp.contract.manager.ContractAcceptanceManager;

@Repository
public class ContractAcceptanceManagerImpl extends BaseManagerImpl<ContractAcceptance> implements ContractAcceptanceManager {

	@Autowired(required=true)  @Qualifier("contractAcceptanceDaoHibernate")
	private ContractAcceptanceDao contractAcceptanceDaoHibernate;

}
