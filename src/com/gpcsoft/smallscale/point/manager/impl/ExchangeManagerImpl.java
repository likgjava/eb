package com.gpcsoft.smallscale.point.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.ExchangeDao;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.smallscale.point.manager.ExchangeManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ExchangeManagerImpl extends BaseManagerImpl<Exchange> implements ExchangeManager {

	@Autowired(required=true)  @Qualifier("exchangeDaoHibernate")
	private ExchangeDao exchangeDaoHibernate;
	
	/**
	 * 查询用户积分表的当前有效积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryLeaveIntegral(User user)throws Exception{
		return exchangeDaoHibernate.queryLeaveIntegral(user);
	}
	
	/**
	 * 查询用户的历史总积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryHistoryTotalLeave(User user)throws Exception{
		return exchangeDaoHibernate.queryHistoryTotalLeave(user);
	}

}
