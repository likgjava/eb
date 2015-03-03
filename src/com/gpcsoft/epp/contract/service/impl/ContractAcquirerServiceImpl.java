package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.manager.ContractAcquirerManager;
import com.gpcsoft.epp.contract.service.ContractAcquirerService;

@Service 
public class ContractAcquirerServiceImpl extends BaseGenericServiceImpl<ContractAcquirer> implements ContractAcquirerService {

	@Autowired(required=true) @Qualifier("contractAcquirerManagerImpl")
	private ContractAcquirerManager contractAcquirerManager;

}
