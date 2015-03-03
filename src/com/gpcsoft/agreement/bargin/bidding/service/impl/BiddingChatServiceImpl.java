package com.gpcsoft.agreement.bargin.bidding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingChatDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingChatManager;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingChatService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class BiddingChatServiceImpl extends BaseGenericServiceImpl<BiddingChat> implements BiddingChatService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("biddingChatManagerImpl")
	private BiddingChatManager biddingChatManager;
	
	@Autowired(required=true) @Qualifier("biddingChatDaoHibernate")
	private BiddingChatDao biddingChatDaoHibernate;

	/** 
	 * Description :  定时查询新聊天内容
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> findNewChatContent(Map<String, Object> params) {
		return biddingChatDaoHibernate.findNewChatContent(params);
	}
	
	/** 
	 * Description :  查找聊天内容 
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingChat> findChatContent(Map<String, Object> params) {
		return biddingChatDaoHibernate.findChatContent(params);
	}

	/** 
	 * Description :  修改阅读状态
	 * Create Date: 2010-10-29下午04:14:53 by yucy  Modified Date: 2010-10-29下午04:14:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateChatRecord(Map<String, Object> params) throws Exception {
		return biddingChatDaoHibernate.updateChatRecord(params);
	}

}
