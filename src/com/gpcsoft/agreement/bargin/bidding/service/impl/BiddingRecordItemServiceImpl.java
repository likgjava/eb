package com.gpcsoft.agreement.bargin.bidding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordItemManager;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordItemService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class BiddingRecordItemServiceImpl extends BaseGenericServiceImpl<BiddingRecordItem> implements BiddingRecordItemService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("biddingRecordItemManagerImpl")
	private BiddingRecordItemManager biddingRecordItemManager;

}
