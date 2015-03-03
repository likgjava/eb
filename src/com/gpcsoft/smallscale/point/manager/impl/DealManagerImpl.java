package com.gpcsoft.smallscale.point.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.DealDao;
import com.gpcsoft.smallscale.point.domain.Deal;
import com.gpcsoft.smallscale.point.manager.DealManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class DealManagerImpl extends BaseManagerImpl<Deal> implements DealManager {

	@Autowired(required=true)  @Qualifier("dealDaoHibernate")
	private DealDao dealDaoHibernate;
	
	/**
	 * 查询用户交易的积分__谁赠送的 减去
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealFormUserIntegral(User user)throws Exception{
		return dealDaoHibernate.queryDealFormUserIntegral(user);
	}
	
	/**
	 * 查询用户交易的积分__赠送给谁的  加上
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryDealToUserIntegral(User user)throws Exception{
		return dealDaoHibernate.queryDealToUserIntegral(user);
	}
}
