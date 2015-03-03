package com.gpcsoft.epp.bid.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.bid.dao.BidReceiptDao;
import com.gpcsoft.epp.bid.manager.BidReceiptManager;
import com.gpcsoft.epp.bid.domain.BidReceipt;

@Repository
public class BidReceiptManagerImpl extends BaseManagerImpl<BidReceipt> implements BidReceiptManager {

	@Autowired(required=true)  @Qualifier("bidReceiptDaoHibernate")
	private BidReceiptDao bidReceiptDaoHibernate;

}
