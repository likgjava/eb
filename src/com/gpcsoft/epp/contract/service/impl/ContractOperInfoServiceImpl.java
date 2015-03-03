package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractOperInfo;
import com.gpcsoft.epp.contract.manager.ContractOperInfoManager;
import com.gpcsoft.epp.contract.service.ContractOperInfoService;

@Service 
public class ContractOperInfoServiceImpl extends BaseGenericServiceImpl<ContractOperInfo> implements ContractOperInfoService {

	@Autowired(required=true) @Qualifier("contractOperInfoManagerImpl")
	private ContractOperInfoManager contractOperInfoManager;

}
