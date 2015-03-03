package com.gpcsoft.smallscale.point.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.point.domain.Deal;
import com.gpcsoft.srplatform.auth.domain.User;

public interface DealDao extends BaseGenericDao<Deal> {
	/**
	 * 查询用户交易的积分__谁赠送的 减去
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealFormUserIntegral(User user)throws Exception;
	
	/**
	 * 查询用户交易的积分__赠送给谁的  加上
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealToUserIntegral(User user)throws Exception;
}
