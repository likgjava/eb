package com.gpcsoft.agreement.bargin.bidding.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordHistoryDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.agreement.bargin.bidding.manager.BiddingRecordHistoryManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class BiddingRecordHistoryManagerImpl extends BaseManagerImpl<BiddingRecordHistory> implements BiddingRecordHistoryManager {

	@Autowired(required=true)  @Qualifier("biddingRecordHistoryDaoHibernate")
	private BiddingRecordHistoryDao biddingRecordHistoryDaoHibernate;

	/** 
	 * Description : 根据detail获得历史
	 * Create Date: 2011-5-30上午09:29:31 by yucy  Modified Date: 2011-5-30上午09:29:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getHistoryInfoByDetail(String detailId) throws Exception {
		return biddingRecordHistoryDaoHibernate.getHistoryInfoByDetail(detailId);
	}

	
}
