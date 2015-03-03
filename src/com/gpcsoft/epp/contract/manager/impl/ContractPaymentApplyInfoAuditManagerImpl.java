package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractPaymentApplyInfoAuditDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfoAudit;
import com.gpcsoft.epp.contract.manager.ContractPaymentApplyInfoAuditManager;

@Repository
public class ContractPaymentApplyInfoAuditManagerImpl extends BaseManagerImpl<ContractPaymentApplyInfoAudit> implements ContractPaymentApplyInfoAuditManager {

	@Autowired(required=true)  @Qualifier("contractPaymentApplyInfoAuditDaoHibernate")
	private ContractPaymentApplyInfoAuditDao contractPaymentApplyInfoAuditDaoHibernate;

}
