package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.GiftDao;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.manager.GiftManager;

@Repository
public class GiftManagerImpl extends BaseManagerImpl<Gift> implements GiftManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("giftDaoHibernate")
	private GiftDao giftDaoHibernate;

}
