package com.gpcsoft.smallscale.business.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.domain.ForumTopic;

public interface ForumTopicDao extends BaseGenericDao<ForumTopic> {

	/** 
	 * Description :  是否显示主题
	 * Create Date: 2011-2-25上午10:31:27 by zhongq  Modified Date: 2011-2-25上午10:31:27 by zhongq
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateShowStatus(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  是否置顶(isTop),是否显示(isShow),是否精华(isElite)
	 * Create Date: 2011-2-25下午14:00:27 by zhongq  Modified Date: 2011-2-25下午14:00:27 by zhongq
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateTopOrShowOrElite(Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  删除帖子主题,并删除此主题下的所有的回复帖子
	 * Create Date: 2011-3-8下午04:44:38 by zhaojf  Modified Date: 2011-3-8下午04:44:38 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeTopicAndReply(String objId);
	
	/**
	 * Description :  主题的回复数、发布机构的发帖数、发布机构发布的精英主题
	 * Create Date: 2011-3-11下午04:43:28 by zhaojf  Modified Date: 2011-3-11下午04:43:28 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> getReplyAndOrgReplyAndOrgTop(ForumTopic forumTopic);
	
	/** 
	 * Description :  获取社区主题列表
	 * Create Date: 2011-3-21上午11:52:31 by likg  Modified Date: 2011-3-21上午11:52:31 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<ForumTopic> getForumTopicList(Page<ForumTopic> page, Map<String, Object> paramsMap) throws Exception;
}
