package com.gpcsoft.epp.bid.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.bid.dao.BidReceiptDao;
import com.gpcsoft.epp.bid.domain.BidReceipt;
import org.springframework.stereotype.Repository;

@Repository
public class BidReceiptDaoHibernate extends BaseGenericDaoHibernate<BidReceipt> implements BidReceiptDao {

	/**
	 * FuncName: getBidReceiptByPackageId
	 * Description :  根据投标包件获得回执对象
	 * @param type   回执类型
	 * @param packageId  包件ID
	 * @return BidReceipt:回执对象
	 * @author: liuke
	 * @Create Date:2011-6-29  上午11:21:03
	 * @Modifier: liuke
	 * @Modified Date:2011-6-29  上午11:21:03
	 */
	public BidReceipt getBidReceiptByBidPackageId(String bidpackageId,
			String type) {
		List <BidReceipt> bidReceiptList = this.findbyHql("from BidReceipt br where br.bidPackage.objId = ? and br.bidReType = ? ",bidpackageId ,type);
		return (bidReceiptList!=null&&!bidReceiptList.isEmpty())?bidReceiptList.get(0):null;
	}

}
