package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractFundDetail;
import com.gpcsoft.epp.contract.manager.ContractFundDetailManager;
import com.gpcsoft.epp.contract.service.ContractFundDetailService;

@Service 
public class ContractFundDetailServiceImpl extends BaseGenericServiceImpl<ContractFundDetail> implements ContractFundDetailService {

	@Autowired(required=true) @Qualifier("contractFundDetailManagerImpl")
	private ContractFundDetailManager contractFundDetailManager;

}
