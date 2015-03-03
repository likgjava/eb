package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractPaymentApplyInfoDao;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.manager.ContractPaymentApplyInfoManager;

@Repository
public class ContractPaymentApplyInfoManagerImpl extends BaseManagerImpl<ContractPaymentApplyInfo> implements ContractPaymentApplyInfoManager {

	@Autowired(required=true)  @Qualifier("contractPaymentApplyInfoDaoHibernate")
	private ContractPaymentApplyInfoDao contractPaymentApplyInfoDaoHibernate;

}
