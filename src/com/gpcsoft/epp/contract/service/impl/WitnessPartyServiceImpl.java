package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.WitnessParty;
import com.gpcsoft.epp.contract.manager.WitnessPartyManager;
import com.gpcsoft.epp.contract.service.WitnessPartyService;

@Service 
public class WitnessPartyServiceImpl extends BaseGenericServiceImpl<WitnessParty> implements WitnessPartyService {

	@Autowired(required=true) @Qualifier("witnessPartyManagerImpl")
	private WitnessPartyManager witnessPartyManager;

}
