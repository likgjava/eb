package com.gpcsoft.pubservice.application.advertisement.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

public interface AdvertisingSubscribeManager extends BaseManager<AdvertisingSubscribe> {
	
	/**
	 * Description :  根据广告位Id获取广告订阅对象列表
	 * Create Date: 2011-3-25下午02:24:32 by zhaojf  Modified Date: 2011-3-25下午02:24:32 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(Map<String, Object> paramsMap) throws Exception;

}
