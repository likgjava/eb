package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.GiftExchangeRuleDao;
import com.gpcsoft.smallscale.pointmall.manager.GiftExchangeRuleManager;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;

@Repository
public class GiftExchangeRuleManagerImpl extends BaseManagerImpl<GiftExchangeRule> implements GiftExchangeRuleManager {

	@Autowired(required=true)  @Qualifier("giftExchangeRuleDaoHibernate")
	private GiftExchangeRuleDao giftExchangeRuleDaoHibernate;

	public void setGiftExchangeRuleDaoHibernate(
			GiftExchangeRuleDao giftExchangeRuleDaoHibernate) {
		this.giftExchangeRuleDaoHibernate = giftExchangeRuleDaoHibernate;
	}

	public GiftExchangeRuleDao getGiftExchangeRuleDaoHibernate() {
		return giftExchangeRuleDaoHibernate;
	}

}
