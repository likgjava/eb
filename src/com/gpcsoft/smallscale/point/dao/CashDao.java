package com.gpcsoft.smallscale.point.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.point.domain.Cash;
import com.gpcsoft.srplatform.auth.domain.User;

public interface CashDao extends BaseGenericDao<Cash> {
	/**
	 * 查询用户兑现的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryCashIntegral(User user)throws Exception;
}
