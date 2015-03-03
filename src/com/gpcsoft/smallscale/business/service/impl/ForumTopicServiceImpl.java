package com.gpcsoft.smallscale.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.ForumTopicDao;
import com.gpcsoft.smallscale.business.domain.ForumTopic;
import com.gpcsoft.smallscale.business.service.ForumTopicService;

@Service 
public class ForumTopicServiceImpl extends BaseGenericServiceImpl<ForumTopic> implements ForumTopicService {

	
	@Autowired(required=true) @Qualifier("forumTopicDaoHibernate")
	private ForumTopicDao forumTopicrDao;
	
	/**
	 * 是否显示主题
	 */
	public Boolean updateShowStatus(Map<String, Object> param) throws Exception {
		return forumTopicrDao.updateShowStatus(param);
	}

	/**
	 *  是否置顶(isTop),是否显示(isShow),是否精华(isElite)
	 */
	public Boolean updateTopOrShowOrElite(Map<String, Object> param) throws Exception {
		return forumTopicrDao.updateTopOrShowOrElite(param);
	}

	/**
	 * 删除帖子主题,并删除此主题下的所有的回复帖子
	 */
	public void removeTopicAndReply(String objId) {
		//删除此主题下的所有的回复帖子
		forumTopicrDao.removeTopicAndReply(objId);
		
		//删除帖子主题
		forumTopicrDao.remove(objId, ForumTopic.class);
	}

	/**
	 *  主题的回复数、发布机构的发帖数、发布机构发布的精英主题
	 */
	public Map<String, Object> getReplyAndOrgReplyAndOrgTop(ForumTopic forumTopic) {
		return forumTopicrDao.getReplyAndOrgReplyAndOrgTop(forumTopic);
	}

	/** 
	 * Description :  获取社区主题列表
	 * Create Date: 2011-3-21上午11:52:31 by likg  Modified Date: 2011-3-21上午11:52:31 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<ForumTopic> getForumTopicList(Page<ForumTopic> page, Map<String, Object> paramsMap) throws Exception {
		return forumTopicrDao.getForumTopicList(page, paramsMap);
	}
}
