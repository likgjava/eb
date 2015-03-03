package com.gpcsoft.smallscale.vote.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteAssesseExpertDao;
import com.gpcsoft.smallscale.vote.manager.VoteAssesseExpertManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;

@Repository
public class VoteAssesseExpertManagerImpl extends BaseManagerImpl<VoteAssesseExpert> implements VoteAssesseExpertManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("voteAssesseExpertDaoHibernate")
	private VoteAssesseExpertDao voteAssesseExpertDaoHibernate;

}
