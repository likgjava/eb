package com.gpcsoft.smallscale.business.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.domain.ForumReply;

public interface ForumReplyDao extends BaseGenericDao<ForumReply> {

	/** 
	 * Description :  更改帖子的显示状态(true：显示,false：不显示)
	 * Create Date: 2011-2-25下午15:30:27 by zhongq  Modified Date: 2011-2-25下午15:30:27 by zhongq
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean isUnfurlTopicReply(Map<String, Object> param);
	
	/**
	 * Description :  获取回帖列表，及回帖机构信息、回帖机构发帖状况
	 * Create Date: 2011-3-17上午09:43:44 by zhaojf  Modified Date: 2011-3-17上午09:43:44 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<Object> getTopicReplyListForShow(Page<Object> page,Map<String, Object> paramsMap);
}
