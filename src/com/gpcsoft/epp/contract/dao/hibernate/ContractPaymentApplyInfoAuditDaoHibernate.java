package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractPaymentApplyInfoAuditDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfoAudit;

import org.springframework.stereotype.Repository;

@Repository
public class ContractPaymentApplyInfoAuditDaoHibernate extends BaseGenericDaoHibernate<ContractPaymentApplyInfoAudit> implements ContractPaymentApplyInfoAuditDao {

}
