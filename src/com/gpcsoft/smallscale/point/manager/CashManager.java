package com.gpcsoft.smallscale.point.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Cash;
import com.gpcsoft.srplatform.auth.domain.User;

public interface CashManager extends BaseManager<Cash> {
	/**
	 * 查询用户兑现的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryCashIntegral(User user)throws Exception;
}
