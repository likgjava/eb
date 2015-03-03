package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.GiftSupplierDao;
import com.gpcsoft.smallscale.pointmall.manager.GiftSupplierManager;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;

@Repository
public class GiftSupplierManagerImpl extends BaseManagerImpl<GiftSupplier> implements GiftSupplierManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("giftSupplierDaoHibernate")
	private GiftSupplierDao giftSupplierDaoHibernate;
}
