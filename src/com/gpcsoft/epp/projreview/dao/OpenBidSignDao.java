package com.gpcsoft.epp.projreview.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;

public interface OpenBidSignDao extends BaseGenericDao<OpenBidSign> {

	/**
	 * 根据TenderId获取OpenBidSign
	 * @param tenderId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-05 13:16
	 */
	public List<OpenBidSign> getOpenBidSignByTenderId(String tenderId);
	
	/**
	 * 根据supplierCode获取OpenBidSign
	 * @param supplierCode
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-05 13:16
	 */
	public List<OpenBidSign> getOpenBidSignByTenderIdAndOrgCode(String tenderId, String supplierCode);
}
