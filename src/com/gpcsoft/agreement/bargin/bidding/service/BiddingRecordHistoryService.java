package com.gpcsoft.agreement.bargin.bidding.service;
import java.util.List;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.core.service.BaseGenericService;

public interface BiddingRecordHistoryService extends BaseGenericService<BiddingRecordHistory> {

	/** 
	 * Description : 根据detail获得历史
	 * Create Date: 2011-5-30上午09:29:31 by yucy  Modified Date: 2011-5-30上午09:29:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getHistoryInfoByDetail(String detailId) throws Exception;
	/** 
	 * Description :  根据DetialId获取历史报价  
	 * Create Date: 2011-5-27下午04:51:21 by sunl  Modified Date: 2011-5-27下午04:51:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getHistoryByDetail(String bargainDetailId) throws Exception;
}
