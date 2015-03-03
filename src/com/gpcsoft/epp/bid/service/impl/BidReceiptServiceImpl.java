package com.gpcsoft.epp.bid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.dao.BidReceiptDao;
import com.gpcsoft.epp.bid.domain.BidReceipt;
import com.gpcsoft.epp.bid.service.BidReceiptService;

@Service 
public class BidReceiptServiceImpl extends BaseGenericServiceImpl<BidReceipt> implements BidReceiptService {

	@Autowired(required=true)  @Qualifier("bidReceiptDaoHibernate")
	private BidReceiptDao bidReceiptDaoHibernate;
	/**
	 * FuncName: getBidReceiptByPackageId
	 * Description :  根据投标包件获得回执对象
	 * @param type   回执类型
	 * @param packageId  包件ID
	 * @return BidReceipt
	 * @author: liuke
	 * @Create Date:2011-6-29  上午11:21:03
	 * @Modifier: liuke
	 * @Modified Date:2011-6-29  上午11:21:03
	 */
	public BidReceipt getBidReceiptByBidPackageId(String bidpackageId,
			String type) {
		return bidReceiptDaoHibernate.getBidReceiptByBidPackageId(bidpackageId, type);
	}

}
