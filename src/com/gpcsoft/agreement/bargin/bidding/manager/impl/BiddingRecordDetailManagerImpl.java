package com.gpcsoft.agreement.bargin.bidding.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDetailDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordDetailManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class BiddingRecordDetailManagerImpl extends BaseManagerImpl<BiddingRecordDetail> implements BiddingRecordDetailManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("biddingRecordDetailDaoHibernate")
	private BiddingRecordDetailDao biddingRecordDetailDaoHibernate;

}
