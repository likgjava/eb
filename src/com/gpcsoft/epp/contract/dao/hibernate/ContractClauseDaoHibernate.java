package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractClauseDao;
import com.gpcsoft.epp.contract.domain.ContractClause;

import org.springframework.stereotype.Repository;

@Repository
public class ContractClauseDaoHibernate extends BaseGenericDaoHibernate<ContractClause> implements ContractClauseDao {

}
