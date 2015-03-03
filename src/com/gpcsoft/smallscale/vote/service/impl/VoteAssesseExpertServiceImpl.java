package com.gpcsoft.smallscale.vote.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VoteAssesseExpertManager;
import com.gpcsoft.smallscale.vote.service.VoteAssesseExpertService;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;

@Service 
public class VoteAssesseExpertServiceImpl extends BaseGenericServiceImpl<VoteAssesseExpert> implements VoteAssesseExpertService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("voteAssesseExpertManagerImpl")
	private VoteAssesseExpertManager voteAssesseExpertManager;

}
