package com.gpcsoft.smallscale.vote.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateMediumDao;
import com.gpcsoft.smallscale.vote.manager.VoteTemplateMediumManager;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;

@Repository
public class VoteTemplateMediumManagerImpl extends BaseManagerImpl<VoteTemplateMedium> implements VoteTemplateMediumManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("voteTemplateMediumDaoHibernate")
	private VoteTemplateMediumDao voteTemplateMediumDaoHibernate;

}
