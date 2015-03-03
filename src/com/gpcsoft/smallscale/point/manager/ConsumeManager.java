package com.gpcsoft.smallscale.point.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ConsumeManager extends BaseManager<Consume> {
	/**
	 * 查询用户消费的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryConsumeIntegral(User user)throws Exception;
}
