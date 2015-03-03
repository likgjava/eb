package com.gpcsoft.pubservice.application.advertisement.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.advertisement.dao.AdvertisingSubscribeDao;
import com.gpcsoft.pubservice.application.advertisement.manager.AdvertisingSubscribeManager;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

@Repository
public class AdvertisingSubscribeManagerImpl extends BaseManagerImpl<AdvertisingSubscribe> implements AdvertisingSubscribeManager {

	@Autowired(required=true)  @Qualifier("advertisingSubscribeDaoHibernate")
	private AdvertisingSubscribeDao advertisingSubscribeDaoHibernate;

	/**
	 * 根据广告位Id获取广告订阅对象列表
	 */
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(Map<String, Object> paramsMap) throws Exception {
		return advertisingSubscribeDaoHibernate.getAdverSubscribeByPositionId(paramsMap);
	}

}
