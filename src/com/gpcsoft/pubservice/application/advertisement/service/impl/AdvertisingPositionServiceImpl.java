package com.gpcsoft.pubservice.application.advertisement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.pubservice.application.advertisement.manager.AdvertisingPositionManager;
import com.gpcsoft.pubservice.application.advertisement.manager.AdvertisingSubscribeManager;
import com.gpcsoft.pubservice.application.advertisement.service.AdvertisingPositionService;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

@Service 
public class AdvertisingPositionServiceImpl extends BaseGenericServiceImpl<AdvertisingPosition> implements AdvertisingPositionService {

	@Autowired(required=true) @Qualifier("advertisingPositionManagerImpl")
	private AdvertisingPositionManager advertisingPositionManager;
	
	@Autowired(required=true) @Qualifier("advertisingSubscribeManagerImpl")
	private AdvertisingSubscribeManager advertisingSubscribeManager;

	/**
	 * 删除(可批量删除)
	 */
	public Map<String, Object> removeAdverPositon(String adverPositionIds) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] adverPositonArray = adverPositionIds.split(",");
		if(this.judgeCondition(adverPositonArray)){
			resultMap.put("result", "false");
		}else{
			advertisingPositionManager.remove(adverPositonArray, AdvertisingPosition.class);
			resultMap.put("result", "true");
		}
		return resultMap;
	}
	
	/**
	 * 判断广告位下是否还有广告订阅记录 
	 */
	public Boolean judgeCondition(String[] adverPositonArray) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		boolean flag = false;
		int i = 1;
		do{
			paramsMap.put("adverPositionId", adverPositonArray[i-1]);
			List<AdvertisingSubscribe> list = advertisingSubscribeManager.getAdverSubscribeByPositionId(paramsMap);
			if(list != null && list.size()>0){
				flag = true;
			}
			i++;
		}while(flag==false && i<= adverPositonArray.length);
		return flag;
	}

	/**
	 * 获取广告位下的有效广告订阅对象
	 */
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(Map<String, Object> paramsMap) throws Exception {
		if("selectLoad".equals(paramsMap.get("operType")) || "preview".equals(paramsMap.get("operType"))){
			paramsMap.put("useStatus", "02");
			paramsMap.put("useStatusOperType", "!=");
		}else{
			paramsMap.put("useStatus", "01");
			paramsMap.put("auditStatus", "02");
		}
		
		List<AdvertisingSubscribe> list = advertisingSubscribeManager.getAdverSubscribeByPositionId(paramsMap);
		return list;
	}

	/**
	 * 根据广告位名称获取广告位信息
	 */
	public AdvertisingPosition getPositionByName(String positionName) throws Exception {
		String array [] = {positionName};
		List<AdvertisingPosition> adverPositionList = advertisingPositionManager.findByHql("from AdvertisingPosition p where p.positionName = ?", array);
		return adverPositionList.size()>0?adverPositionList.get(0):null;
	}
}
