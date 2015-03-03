package com.gpcsoft.pubservice.application.advertisement.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.pubservice.application.advertisement.manager.AdvertisingSubscribeManager;
import com.gpcsoft.pubservice.application.advertisement.service.AdvertisingSubscribeService;
import com.gpcsoft.pubservice.application.advertisement.dao.AdvertisingSubscribeDao;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

@Service 
public class AdvertisingSubscribeServiceImpl extends BaseGenericServiceImpl<AdvertisingSubscribe> implements AdvertisingSubscribeService {

	@Autowired(required=true) @Qualifier("advertisingSubscribeManagerImpl")
	private AdvertisingSubscribeManager advertisingSubscribeManager;
	
	@Autowired(required=true)  @Qualifier("advertisingSubscribeDaoHibernate")
	private AdvertisingSubscribeDao advertisingSubscribeDaoHibernate;

	/**
	 * 删除(可批量删除)
	 */
	public Map<String, Object> removeAdverSubscribe(String adverSubscribeIds) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] adverSubscribeArray = adverSubscribeIds.split(",");
		advertisingSubscribeManager.remove(adverSubscribeArray, AdvertisingSubscribe.class);
		resultMap.put("result", "true");
		return resultMap;
	}

	/**
	 * 更改使用状态或审核状态
	 */
	public void modifyUseORAuditStatus(Map<String, Object> paramMap) throws Exception {
		String adverSubscribeIds = "";
		String[] adverIdsArray = (String[]) paramMap.get("adverIdsArray");
		if(adverIdsArray != null && adverIdsArray.length >0){
			for(int i=0;i<adverIdsArray.length;i++){
				adverSubscribeIds = adverSubscribeIds+"'"+adverIdsArray[i]+"',";
			}
		}
		if(adverSubscribeIds != ""){
			paramMap.put("adverSubscribeIds", adverSubscribeIds.substring(0, adverSubscribeIds.length()-1));
		}
		advertisingSubscribeDaoHibernate.modifyUseORAuditStatus(paramMap);
	}

	/**
	 * 更改广告跑马灯排序
	 */
	public void updateSort(String objId, boolean isToUp) throws Exception {
		AdvertisingSubscribe advertisingSubscribe = advertisingSubscribeManager.get(objId);
		
		advertisingSubscribeDaoHibernate.updateSort(advertisingSubscribe.getSort(),advertisingSubscribe.getAdvertisingPosition().getObjId(),isToUp);
	}

	/**
	 * 返回广告最大排序号  
	 */
	public Long adverSubscribeMaxSort() throws Exception {
		return advertisingSubscribeDaoHibernate.adverSubscribeMaxSort();
	}
}
