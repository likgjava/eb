package com.gpcsoft.smallscale.business.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.business.dao.ForumTopicDao;
import com.gpcsoft.smallscale.business.manager.ForumTopicManager;
import com.gpcsoft.smallscale.business.domain.ForumTopic;

@Repository
public class ForumTopicManagerImpl extends BaseManagerImpl<ForumTopic> implements ForumTopicManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("forumTopicDaoHibernate")
	private ForumTopicDao forumTopicDaoHibernate;

}
