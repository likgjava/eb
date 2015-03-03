package com.gpcsoft.smallscale.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.ForumReplyDao;
import com.gpcsoft.smallscale.business.domain.ForumReply;
import com.gpcsoft.smallscale.business.manager.ForumReplyManager;
import com.gpcsoft.smallscale.business.service.ForumReplyService;

@Service 
public class ForumReplyServiceImpl extends BaseGenericServiceImpl<ForumReply> implements ForumReplyService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("forumReplyManagerImpl")
	private ForumReplyManager forumReplyManager;
	
	@Autowired(required=true) @Qualifier("forumReplyDaoHibernate")
	private ForumReplyDao forumReplyrDao;
	
	/** 
	 * Description :  更改帖子的显示状态(true：显示,false：不显示)
	 * Create Date: 2011-2-25下午15:30:27 by zhongq  Modified Date: 2011-2-25下午15:30:27 by zhongq
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean isUnfurlTopicReply(Map<String, Object> param){
		return forumReplyrDao.isUnfurlTopicReply(param);
	}

	/**
	 * 获取回帖列表，及回帖机构信息、回帖机构发帖状况
	 */
	public Page<Object> getTopicReplyListForShow(Page<Object> page,
			Map<String, Object> paramsMap) {
		return forumReplyrDao.getTopicReplyListForShow(page, paramsMap);
	}
}
