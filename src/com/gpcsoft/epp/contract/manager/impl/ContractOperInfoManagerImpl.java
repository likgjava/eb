package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractOperInfoDao;
import com.gpcsoft.epp.contract.domain.ContractOperInfo;
import com.gpcsoft.epp.contract.manager.ContractOperInfoManager;

@Repository
public class ContractOperInfoManagerImpl extends BaseManagerImpl<ContractOperInfo> implements ContractOperInfoManager {

	@Autowired(required=true)  @Qualifier("contractOperInfoDaoHibernate")
	private ContractOperInfoDao contractOperInfoDaoHibernate;

}
