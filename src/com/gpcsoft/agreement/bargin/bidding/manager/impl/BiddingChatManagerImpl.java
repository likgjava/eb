package com.gpcsoft.agreement.bargin.bidding.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingChatDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingChatManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class BiddingChatManagerImpl extends BaseManagerImpl<BiddingChat> implements BiddingChatManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("biddingChatDaoHibernate")
	private BiddingChatDao biddingChatDaoHibernate;

}
