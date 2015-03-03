package com.gpcsoft.smallscale.point.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ExchangeManager extends BaseManager<Exchange> {
	/**
	 * 查询用户积分表的当前有效积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryLeaveIntegral(User user)throws Exception;
	
	/**
	 * 查询用户的历史总积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryHistoryTotalLeave(User user)throws Exception;
}
