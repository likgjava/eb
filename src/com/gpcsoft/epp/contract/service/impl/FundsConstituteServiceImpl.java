package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.FundsConstitute;
import com.gpcsoft.epp.contract.manager.FundsConstituteManager;
import com.gpcsoft.epp.contract.service.FundsConstituteService;

@Service 
public class FundsConstituteServiceImpl extends BaseGenericServiceImpl<FundsConstitute> implements FundsConstituteService {

	@Autowired(required=true) @Qualifier("fundsConstituteManagerImpl")
	private FundsConstituteManager fundsConstituteManager;

}
