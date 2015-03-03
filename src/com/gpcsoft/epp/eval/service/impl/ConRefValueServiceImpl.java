package com.gpcsoft.epp.eval.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.eval.domain.ConRefValue;
import com.gpcsoft.epp.eval.manager.ConRefValueManager;
import com.gpcsoft.epp.eval.service.ConRefValueService;

@Service 
public class ConRefValueServiceImpl extends BaseGenericServiceImpl<ConRefValue> implements ConRefValueService {

	@Autowired(required=true) @Qualifier("conRefValueManagerImpl")
	private ConRefValueManager conRefValueManager;

}
