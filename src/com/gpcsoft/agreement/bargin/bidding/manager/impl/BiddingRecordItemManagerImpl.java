package com.gpcsoft.agreement.bargin.bidding.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordItemDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordItemManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class BiddingRecordItemManagerImpl extends BaseManagerImpl<BiddingRecordItem> implements BiddingRecordItemManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("biddingRecordItemDaoHibernate")
	private BiddingRecordItemDao biddingRecordItemDaoHibernate;

}
