package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfoAudit;
import com.gpcsoft.epp.contract.manager.ContractPaymentApplyInfoAuditManager;
import com.gpcsoft.epp.contract.service.ContractPaymentApplyInfoAuditService;

@Service 
public class ContractPaymentApplyInfoAuditServiceImpl extends BaseGenericServiceImpl<ContractPaymentApplyInfoAudit> implements ContractPaymentApplyInfoAuditService {

	@Autowired(required=true) @Qualifier("contractPaymentApplyInfoAuditManagerImpl")
	private ContractPaymentApplyInfoAuditManager contractPaymentApplyInfoAuditManager;

}
