package com.gpcsoft.bizplatform.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.SuccessCase;
import com.gpcsoft.bizplatform.organization.manager.SuccessCaseManager;
import com.gpcsoft.bizplatform.organization.service.SuccessCaseService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class SuccessCaseServiceImpl extends BaseGenericServiceImpl<SuccessCase> implements SuccessCaseService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("successCaseManagerImpl")
	private SuccessCaseManager successCaseManager;

}
