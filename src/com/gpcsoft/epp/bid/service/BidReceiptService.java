package com.gpcsoft.epp.bid.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.bid.domain.BidReceipt;

public interface BidReceiptService extends BaseGenericService<BidReceipt> {

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
	public BidReceipt getBidReceiptByBidPackageId(String bidpackageId,String type);
}
