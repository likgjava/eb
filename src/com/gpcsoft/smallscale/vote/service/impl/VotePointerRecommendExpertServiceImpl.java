package com.gpcsoft.smallscale.vote.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VotePointerRecommendExpertManager;
import com.gpcsoft.smallscale.vote.service.VotePointerRecommendExpertService;
import com.gpcsoft.smallscale.vote.domain.VotePointerRecommendExpert;

@Service 
public class VotePointerRecommendExpertServiceImpl extends BaseGenericServiceImpl<VotePointerRecommendExpert> implements VotePointerRecommendExpertService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("votePointerRecommendExpertManagerImpl")
	private VotePointerRecommendExpertManager votePointerRecommendExpertManager;

}
