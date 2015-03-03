package com.gpcsoft.bizplatform.base.qualitymanagement.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationClassDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationClassManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationClass;

@Repository
public class QualificationClassManagerImpl extends BaseManagerImpl<QualificationClass> implements QualificationClassManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("qualificationClassDaoHibernate")
	private QualificationClassDao qualificationClassDaoHibernate;

}
