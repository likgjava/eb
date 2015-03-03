package com.gpcsoft.smallscale.business.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.business.dao.ForumReplyDao;
import com.gpcsoft.smallscale.business.manager.ForumReplyManager;
import com.gpcsoft.smallscale.business.domain.ForumReply;

@Repository
public class ForumReplyManagerImpl extends BaseManagerImpl<ForumReply> implements ForumReplyManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("forumReplyDaoHibernate")
	private ForumReplyDao forumReplyDaoHibernate;

}
