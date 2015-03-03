package com.gpcsoft.pubservice.application.advertisement.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.advertisement.dao.AdvertisingPositionDao;
import com.gpcsoft.pubservice.application.advertisement.manager.AdvertisingPositionManager;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;

@Repository
public class AdvertisingPositionManagerImpl extends BaseManagerImpl<AdvertisingPosition> implements AdvertisingPositionManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("advertisingPositionDaoHibernate")
	private AdvertisingPositionDao advertisingPositionDaoHibernate;
}
