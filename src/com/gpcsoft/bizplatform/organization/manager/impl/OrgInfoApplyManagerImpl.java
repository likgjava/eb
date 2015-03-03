package com.gpcsoft.bizplatform.organization.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoApplyDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoApplyManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrgInfoApplyManagerImpl extends BaseManagerImpl<OrgInfoApply> implements OrgInfoApplyManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("orgInfoApplyDaoHibernate")
	private OrgInfoApplyDao orgInfoApplyDaoHibernate;

}
