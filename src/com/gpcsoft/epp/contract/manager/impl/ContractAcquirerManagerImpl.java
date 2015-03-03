package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractAcquirerDao;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.manager.ContractAcquirerManager;

@Repository
public class ContractAcquirerManagerImpl extends BaseManagerImpl<ContractAcquirer> implements ContractAcquirerManager {

	@Autowired(required=true)  @Qualifier("contractAcquirerDaoHibernate")
	private ContractAcquirerDao contractAcquirerDaoHibernate;

}
