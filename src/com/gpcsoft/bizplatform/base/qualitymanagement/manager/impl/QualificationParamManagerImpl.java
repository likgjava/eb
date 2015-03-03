package com.gpcsoft.bizplatform.base.qualitymanagement.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationParamDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationParamManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationParam;

@Repository
public class QualificationParamManagerImpl extends BaseManagerImpl<QualificationParam> implements QualificationParamManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("qualificationParamDaoHibernate")
	private QualificationParamDao qualificationParamDaoHibernate;

}
