package com.gpcsoft.smallscale.vote.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteUnitGroupDao;
import com.gpcsoft.smallscale.vote.manager.VoteUnitGroupManager;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

@Repository
public class VoteUnitGroupManagerImpl extends BaseManagerImpl<VoteUnitGroup> implements VoteUnitGroupManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("voteUnitGroupDaoHibernate")
	private VoteUnitGroupDao voteUnitGroupDaoHibernate;

}
