package com.gpcsoft.smallscale.vote.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VotePointerRecommendExpertDao;
import com.gpcsoft.smallscale.vote.manager.VotePointerRecommendExpertManager;
import com.gpcsoft.smallscale.vote.domain.VotePointerRecommendExpert;

@Repository
public class VotePointerRecommendExpertManagerImpl extends BaseManagerImpl<VotePointerRecommendExpert> implements VotePointerRecommendExpertManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("votePointerRecommendExpertDaoHibernate")
	private VotePointerRecommendExpertDao votePointerRecommendExpertDaoHibernate;

}
