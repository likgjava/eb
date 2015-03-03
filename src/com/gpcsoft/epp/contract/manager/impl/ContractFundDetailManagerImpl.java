package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractFundDetailDao;
import com.gpcsoft.epp.contract.domain.ContractFundDetail;
import com.gpcsoft.epp.contract.manager.ContractFundDetailManager;

@Repository
public class ContractFundDetailManagerImpl extends BaseManagerImpl<ContractFundDetail> implements ContractFundDetailManager {

	@Autowired(required=true)  @Qualifier("contractFundDetailDaoHibernate")
	private ContractFundDetailDao contractFundDetailDaoHibernate;

}
