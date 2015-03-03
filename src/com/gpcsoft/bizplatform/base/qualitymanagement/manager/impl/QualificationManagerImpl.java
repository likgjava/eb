package com.gpcsoft.bizplatform.base.qualitymanagement.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.Qualification;

@Repository
public class QualificationManagerImpl extends BaseManagerImpl<Qualification> implements QualificationManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("qualificationDaoHibernate")
	private QualificationDao qualificationDaoHibernate;

}
