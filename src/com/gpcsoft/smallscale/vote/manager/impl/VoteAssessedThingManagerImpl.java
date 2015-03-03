package com.gpcsoft.smallscale.vote.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedThingDao;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedThingManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;

@Repository
public class VoteAssessedThingManagerImpl extends BaseManagerImpl<VoteAssessedThing> implements VoteAssessedThingManager {

	@Autowired(required=true)  @Qualifier("voteAssessedThingDaoHibernate")
	private VoteAssessedThingDao voteAssessedThingDaoHibernate;

	/**
	 * 获取主题下的评选单位
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String templateId) {
		return voteAssessedThingDaoHibernate.findAssessedThingOfTemplate(templateId);
	}

}
