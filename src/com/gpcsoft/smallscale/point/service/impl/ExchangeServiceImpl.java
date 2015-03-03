package com.gpcsoft.smallscale.point.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.smallscale.point.manager.CashManager;
import com.gpcsoft.smallscale.point.manager.ConsumeManager;
import com.gpcsoft.smallscale.point.manager.DealManager;
import com.gpcsoft.smallscale.point.manager.ExchangeManager;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class ExchangeServiceImpl extends BaseGenericServiceImpl<Exchange> implements ExchangeService {

	@Autowired(required=true) @Qualifier("exchangeManagerImpl")
	private ExchangeManager exchangeManager;   //积分
	
	@Autowired(required=true) @Qualifier("cashManagerImpl")
	private CashManager cashManager;   //兑现
	
	@Autowired(required=true) @Qualifier("dealManagerImpl")
	private DealManager dealManager;   //交易
	
	@Autowired(required=true) @Qualifier("consumeManagerImpl")
	private ConsumeManager consumeManager;  //消费

	/**
	 * 查询用户的当前有效总积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryLeaveIntegral(User user)throws Exception{
		long integral=exchangeManager.queryLeaveIntegral(user);//查询用户积分表的当前有效积分
		long cash=cashManager.queryCashIntegral(user);  //查询用户兑现的积分
		long formUserDeal=dealManager.queryDealFormUserIntegral(user);  //查询用户交易的积分__谁赠送的 减去
		long toUserDeal=dealManager.queryDealToUserIntegral(user);  //查询用户交易的积分__赠送给谁的 加上
		long consume=consumeManager.queryConsumeIntegral(user);  //查询用户消费的积分
		long leave=integral-cash-formUserDeal+toUserDeal-consume;
		return leave;
	}
	
	/**
	 * 查询用户的历史总积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryHistoryTotalLeave(User user)throws Exception{
		long history=exchangeManager.queryHistoryTotalLeave(user);//查询用户积分表的历史总积分
		long toUserDeal=dealManager.queryDealToUserIntegral(user);  //查询用户交易的积分__赠送给谁的 加上
		return history+toUserDeal;
	}
}
