package com.gpcsoft.smallscale.vote.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VotePointerDao;
import com.gpcsoft.smallscale.vote.manager.VotePointerManager;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

@Repository
public class VotePointerManagerImpl extends BaseManagerImpl<VotePointer> implements VotePointerManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("votePointerDaoHibernate")
	private VotePointerDao votePointerDaoHibernate;

}
