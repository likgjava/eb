package com.gpcsoft.smallscale.business.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.business.dao.OrgCommunityDao;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;
import com.gpcsoft.smallscale.business.manager.OrgCommunityManager;

@Repository
public class OrgCommunityManagerImpl extends BaseManagerImpl<OrgCommunity> implements OrgCommunityManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("orgCommunityDaoHibernate")
	private OrgCommunityDao orgCommunityDaoHibernate;

}
