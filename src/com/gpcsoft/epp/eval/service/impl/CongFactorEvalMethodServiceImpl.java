package com.gpcsoft.epp.eval.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.eval.domain.CongFactorEvalMethod;
import com.gpcsoft.epp.eval.manager.CongFactorEvalMethodManager;
import com.gpcsoft.epp.eval.service.CongFactorEvalMethodService;

@Service 
public class CongFactorEvalMethodServiceImpl extends BaseGenericServiceImpl<CongFactorEvalMethod> implements CongFactorEvalMethodService {

	@Autowired(required=true) @Qualifier("congFactorEvalMethodManagerImpl")
	private CongFactorEvalMethodManager congFactorEvalMethodManager;

}
