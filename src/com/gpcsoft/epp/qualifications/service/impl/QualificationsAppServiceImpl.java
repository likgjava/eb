package com.gpcsoft.epp.qualifications.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.qualifications.domain.QualificationsApp;
import com.gpcsoft.epp.qualifications.manager.QualificationsAppManager;
import com.gpcsoft.epp.qualifications.service.QualificationsAppService;

@Service 
public class QualificationsAppServiceImpl extends BaseGenericServiceImpl<QualificationsApp> implements QualificationsAppService {

	@Autowired(required=true) @Qualifier("qualificationsAppManagerImpl")
	private QualificationsAppManager qualificationsAppManager;

}
