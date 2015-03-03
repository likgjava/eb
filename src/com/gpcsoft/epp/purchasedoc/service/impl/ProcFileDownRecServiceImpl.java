package com.gpcsoft.epp.purchasedoc.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.epp.purchasedoc.manager.ProcFileDownRecManager;
import com.gpcsoft.epp.purchasedoc.service.ProcFileDownRecService;
import com.gpcsoft.epp.purchasedoc.domain.ProcFileDownRec;

@Service 
public class ProcFileDownRecServiceImpl extends BaseGenericServiceImpl<ProcFileDownRec> implements ProcFileDownRecService {

	@Autowired(required=true) @Qualifier("procFileDownRecManagerImpl")
	private ProcFileDownRecManager procFileDownRecManager;

}
