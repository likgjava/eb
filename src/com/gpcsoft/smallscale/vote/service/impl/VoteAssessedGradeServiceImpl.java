package com.gpcsoft.smallscale.vote.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedGradeManager;
import com.gpcsoft.smallscale.vote.service.VoteAssessedGradeService;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;

@Service 
public class VoteAssessedGradeServiceImpl extends BaseGenericServiceImpl<VoteAssessedGrade> implements VoteAssessedGradeService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("voteAssessedGradeManagerImpl")
	private VoteAssessedGradeManager voteAssessedGradeManager;

}
