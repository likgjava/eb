package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractPaymentApplyInfoDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;

import org.springframework.stereotype.Repository;

@Repository
public class ContractPaymentApplyInfoDaoHibernate extends BaseGenericDaoHibernate<ContractPaymentApplyInfo> implements ContractPaymentApplyInfoDao {

}
