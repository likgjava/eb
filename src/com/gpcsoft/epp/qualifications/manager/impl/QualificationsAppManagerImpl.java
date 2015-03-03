package com.gpcsoft.epp.qualifications.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.qualifications.dao.QualificationsAppDao;
import com.gpcsoft.epp.qualifications.domain.QualificationsApp;
import com.gpcsoft.epp.qualifications.manager.QualificationsAppManager;

@Repository
public class QualificationsAppManagerImpl extends BaseManagerImpl<QualificationsApp> implements QualificationsAppManager {

	@Autowired(required=true)  @Qualifier("qualificationsAppDaoHibernate")
	private QualificationsAppDao qualificationsAppDaoHibernate;

}
