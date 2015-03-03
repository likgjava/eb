package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractPaymentInfo;
import com.gpcsoft.epp.contract.manager.ContractPaymentInfoManager;
import com.gpcsoft.epp.contract.service.ContractPaymentInfoService;

@Service 
public class ContractPaymentInfoServiceImpl extends BaseGenericServiceImpl<ContractPaymentInfo> implements ContractPaymentInfoService {

	@Autowired(required=true) @Qualifier("contractPaymentInfoManagerImpl")
	private ContractPaymentInfoManager contractPaymentInfoManager;

}
