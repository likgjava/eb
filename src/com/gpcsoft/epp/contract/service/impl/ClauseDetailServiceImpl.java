package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ClauseDetail;
import com.gpcsoft.epp.contract.manager.ClauseDetailManager;
import com.gpcsoft.epp.contract.service.ClauseDetailService;

@Service 
public class ClauseDetailServiceImpl extends BaseGenericServiceImpl<ClauseDetail> implements ClauseDetailService {

	@Autowired(required=true) @Qualifier("clauseDetailManagerImpl")
	private ClauseDetailManager clauseDetailManager;

}
