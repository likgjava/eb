package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractPaymentTimesDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentTimes;

import org.springframework.stereotype.Repository;

@Repository
public class ContractPaymentTimesDaoHibernate extends BaseGenericDaoHibernate<ContractPaymentTimes> implements ContractPaymentTimesDao {

}
