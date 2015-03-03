package com.gpcsoft.bizplatform.organization.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.QualificationDetailDao;
import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.manager.QualificationDetailManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class QualificationDetailManagerImpl extends BaseManagerImpl<QualificationDetail> implements QualificationDetailManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("qualificationDetailDaoHibernate")
	private QualificationDetailDao qualificationDetailDaoHibernate;

}
