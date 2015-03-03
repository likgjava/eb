package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractPaymentTimesDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentTimes;
import com.gpcsoft.epp.contract.manager.ContractPaymentTimesManager;

@Repository
public class ContractPaymentTimesManagerImpl extends BaseManagerImpl<ContractPaymentTimes> implements ContractPaymentTimesManager {

	@Autowired(required=true)  @Qualifier("contractPaymentTimesDaoHibernate")
	private ContractPaymentTimesDao contractPaymentTimesDaoHibernate;

}
