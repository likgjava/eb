package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.GiftSeriesDao;
import com.gpcsoft.smallscale.pointmall.manager.GiftSeriesManager;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;

@Repository
public class GiftSeriesManagerImpl extends BaseManagerImpl<GiftSeries> implements GiftSeriesManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("giftSeriesDaoHibernate")
	private GiftSeriesDao giftSeriesDaoHibernate;
}
