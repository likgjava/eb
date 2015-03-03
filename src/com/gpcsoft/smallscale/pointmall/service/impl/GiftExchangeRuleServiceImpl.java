package com.gpcsoft.smallscale.pointmall.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.pointmall.manager.GiftExchangeRuleManager;
import com.gpcsoft.smallscale.pointmall.service.GiftExchangeRuleService;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;

@Service 
public class GiftExchangeRuleServiceImpl extends BaseGenericServiceImpl<GiftExchangeRule> implements GiftExchangeRuleService {

	@Autowired(required=true) @Qualifier("giftExchangeRuleManagerImpl")
	private GiftExchangeRuleManager giftExchangeRuleManager;

	public void setGiftExchangeRuleManager(GiftExchangeRuleManager giftExchangeRuleManager) {
		this.giftExchangeRuleManager = giftExchangeRuleManager;
	}

	public GiftExchangeRuleManager getGiftExchangeRuleManager() {
		return giftExchangeRuleManager;
	}

}
