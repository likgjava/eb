package com.gpcsoft.agreement.bargin.bidding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordHistoryDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordHistoryManager;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordHistoryService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class BiddingRecordHistoryServiceImpl extends BaseGenericServiceImpl<BiddingRecordHistory> implements BiddingRecordHistoryService {

	@Autowired(required=true) @Qualifier("biddingRecordHistoryManagerImpl")
	private BiddingRecordHistoryManager biddingRecordHistoryManager;
	@Autowired(required=true) @Qualifier("biddingRecordHistoryDaoHibernate")
	private BiddingRecordHistoryDao biddingRecordHistoryDao;

	/** 
	 * Description : 根据detail获得历史
	 * Create Date: 2011-5-30上午09:29:31 by yucy  Modified Date: 2011-5-30上午09:29:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getHistoryInfoByDetail(String detailId) throws Exception {
		return biddingRecordHistoryManager.getHistoryInfoByDetail(detailId);
	}

	/** 
	 * Description :  根据DetialId获取历史报价  
	 * Create Date: 2011-5-27下午04:51:21 by sunl  Modified Date: 2011-5-27下午04:51:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getHistoryByDetail(String bargainDetailId) throws Exception {
		return biddingRecordHistoryDao.getHistoryByDetail(bargainDetailId);
	}
}
