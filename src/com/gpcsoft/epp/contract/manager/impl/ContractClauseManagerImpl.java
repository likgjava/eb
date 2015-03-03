package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractClauseDao;
import com.gpcsoft.epp.contract.domain.ContractClause;
import com.gpcsoft.epp.contract.manager.ContractClauseManager;

@Repository
public class ContractClauseManagerImpl extends BaseManagerImpl<ContractClause> implements ContractClauseManager {

	@Autowired(required=true)  @Qualifier("contractClauseDaoHibernate")
	private ContractClauseDao contractClauseDaoHibernate;

}
