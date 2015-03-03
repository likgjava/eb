package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.manager.ContractPaymentApplyInfoManager;
import com.gpcsoft.epp.contract.service.ContractPaymentApplyInfoService;

@Service 
public class ContractPaymentApplyInfoServiceImpl extends BaseGenericServiceImpl<ContractPaymentApplyInfo> implements ContractPaymentApplyInfoService {

	@Autowired(required=true) @Qualifier("contractPaymentApplyInfoManagerImpl")
	private ContractPaymentApplyInfoManager contractPaymentApplyInfoManager;

}
