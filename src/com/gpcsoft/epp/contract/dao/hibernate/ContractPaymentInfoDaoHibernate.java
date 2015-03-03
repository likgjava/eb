package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractPaymentInfoDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentInfo;

import org.springframework.stereotype.Repository;

@Repository
public class ContractPaymentInfoDaoHibernate extends BaseGenericDaoHibernate<ContractPaymentInfo> implements ContractPaymentInfoDao {

}
