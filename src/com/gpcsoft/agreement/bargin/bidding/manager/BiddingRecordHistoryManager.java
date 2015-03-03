package com.gpcsoft.agreement.bargin.bidding.manager;

import java.util.List;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.core.manager.BaseManager;

public interface BiddingRecordHistoryManager extends BaseManager<BiddingRecordHistory> {

	/** 
	 * Description : 根据detail获得历史
	 * Create Date: 2011-5-30上午09:29:31 by yucy  Modified Date: 2011-5-30上午09:29:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getHistoryInfoByDetail(String detailId) throws Exception;

}
