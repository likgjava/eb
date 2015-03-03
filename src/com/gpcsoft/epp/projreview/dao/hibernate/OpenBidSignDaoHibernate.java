package com.gpcsoft.epp.projreview.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projreview.dao.OpenBidSignDao;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;
import org.springframework.stereotype.Repository;

@Repository
public class OpenBidSignDaoHibernate extends BaseGenericDaoHibernate<OpenBidSign> implements OpenBidSignDao {

	/**
	 * 根据TenderId获取OpenBidSign
	 * @param tenderId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-05 13:16
	 */
	public List<OpenBidSign> getOpenBidSignByTenderId(String tenderId) {
		return findbyHql("from OpenBidSign ops where ops.tenderId = ?", tenderId);
	}

	/**
	 * 根据supplierCode获取OpenBidSign
	 * @param supplierCode
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-05 13:16
	 */
	public List<OpenBidSign> getOpenBidSignByTenderIdAndOrgCode(
			String tenderId, String supplierCode) {
		return findbyHql("from OpenBidSign ops where ops.tenderId = ? and ops.supplierCode = ?", tenderId, supplierCode);
	}
}
