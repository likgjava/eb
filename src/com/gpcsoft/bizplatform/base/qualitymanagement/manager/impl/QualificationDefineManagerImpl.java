package com.gpcsoft.bizplatform.base.qualitymanagement.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.base.qualitymanagement.dao.QualificationDefineDao;
import com.gpcsoft.bizplatform.base.qualitymanagement.manager.QualificationDefineManager;
import com.gpcsoft.bizplatform.base.qualitymanagement.domain.QualificationDefine;

@Repository
public class QualificationDefineManagerImpl extends BaseManagerImpl<QualificationDefine> implements QualificationDefineManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("qualificationDefineDaoHibernate")
	private QualificationDefineDao qualificationDefineDaoHibernate;

}
