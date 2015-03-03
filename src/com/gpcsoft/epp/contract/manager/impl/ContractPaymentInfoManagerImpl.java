package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractPaymentInfoDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentInfo;
import com.gpcsoft.epp.contract.manager.ContractPaymentInfoManager;

@Repository
public class ContractPaymentInfoManagerImpl extends BaseManagerImpl<ContractPaymentInfo> implements ContractPaymentInfoManager {

	@Autowired(required=true)  @Qualifier("contractPaymentInfoDaoHibernate")
	private ContractPaymentInfoDao contractPaymentInfoDaoHibernate;

}
