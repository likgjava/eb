package com.gpcsoft.smallscale.business.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.business.dao.CommunityDao;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.manager.CommunityManager;

@Repository
public class CommunityManagerImpl extends BaseManagerImpl<Community> implements CommunityManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("communityDaoHibernate")
	private CommunityDao communityDaoHibernate;

}
