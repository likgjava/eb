package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractAcceptanceDao;
import com.gpcsoft.epp.contract.domain.ContractAcceptance;

import org.springframework.stereotype.Repository;

@Repository
public class ContractAcceptanceDaoHibernate extends BaseGenericDaoHibernate<ContractAcceptance> implements ContractAcceptanceDao {

}
