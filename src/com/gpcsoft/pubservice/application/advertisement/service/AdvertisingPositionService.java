package com.gpcsoft.pubservice.application.advertisement.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

public interface AdvertisingPositionService extends BaseGenericService<AdvertisingPosition> {
	
	/**
	 * Description :   删除(可批量删除)
	 * Create Date: 2011-3-23上午11:04:11 by zhaojf  Modified Date: 2011-3-23上午11:04:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> removeAdverPositon(String adverPositionIds) throws Exception;
	
	/**
	 * Description :  获取广告位下的有效广告订阅对象
	 * Create Date: 2011-3-25下午03:25:23 by zhaojf  Modified Date: 2011-3-25下午03:25:23 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(Map<String, Object> paramsMap) throws Exception;

	/**
	 * Description :  判断广告位下是否还有广告订阅记录 
	 * Create Date: 2011-3-31上午11:20:21 by zhaojf  Modified Date: 2011-3-31上午11:20:21 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Boolean judgeCondition(String[] adverPositonArray) throws Exception;
	
	/**
	 * Description :  根据广告位名称获取广告位信息
	 * Create Date: 2011-9-30上午10:21:53 by zhaojf  Modified Date: 2011-9-30上午10:21:53 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public AdvertisingPosition getPositionByName(String positionName) throws Exception;
}
