package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractAcquirerDao;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;

import org.springframework.stereotype.Repository;

@Repository
public class ContractAcquirerDaoHibernate extends BaseGenericDaoHibernate<ContractAcquirer> implements ContractAcquirerDao {

}
