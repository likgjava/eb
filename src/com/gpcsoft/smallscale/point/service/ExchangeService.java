package com.gpcsoft.smallscale.point.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ExchangeService extends BaseGenericService<Exchange> {
	/**
	 * 查询用户的当前有效总积分
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
