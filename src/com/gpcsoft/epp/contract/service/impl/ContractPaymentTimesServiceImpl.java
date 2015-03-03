package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractPaymentTimes;
import com.gpcsoft.epp.contract.manager.ContractPaymentTimesManager;
import com.gpcsoft.epp.contract.service.ContractPaymentTimesService;

@Service 
public class ContractPaymentTimesServiceImpl extends BaseGenericServiceImpl<ContractPaymentTimes> implements ContractPaymentTimesService {

	@Autowired(required=true) @Qualifier("contractPaymentTimesManagerImpl")
	private ContractPaymentTimesManager contractPaymentTimesManager;

}
