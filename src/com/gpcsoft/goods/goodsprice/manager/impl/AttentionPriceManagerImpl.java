package com.gpcsoft.goods.goodsprice.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goodsprice.dao.AttentionPriceDao;
import com.gpcsoft.goods.goodsprice.manager.AttentionPriceManager;
import com.gpcsoft.goods.goodsprice.domain.AttentionPrice;

@Repository
public class AttentionPriceManagerImpl extends BaseManagerImpl<AttentionPrice> implements AttentionPriceManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("attentionPriceDaoHibernate")
	private AttentionPriceDao attentionPriceDaoHibernate;

}
