package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractAcceptance;
import com.gpcsoft.epp.contract.manager.ContractAcceptanceManager;
import com.gpcsoft.epp.contract.service.ContractAcceptanceService;

@Service 
public class ContractAcceptanceServiceImpl extends BaseGenericServiceImpl<ContractAcceptance> implements ContractAcceptanceService {

	@Autowired(required=true) @Qualifier("contractAcceptanceManagerImpl")
	private ContractAcceptanceManager contractAcceptanceManager;

}
