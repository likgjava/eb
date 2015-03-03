package com.gpcsoft.pubservice.application.advertisement.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

public interface AdvertisingSubscribeService extends BaseGenericService<AdvertisingSubscribe> {
	
	/**
	 * Description :  删除(可批量删除)
	 * Create Date: 2011-3-23下午04:30:13 by zhaojf  Modified Date: 2011-3-23下午04:30:13 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> removeAdverSubscribe(String adverSubscribeIds) throws Exception;
	
	/**
	 * Description :  更改使用状态或审核状态
	 * Create Date: 2011-3-24下午05:12:15 by zhaojf  Modified Date: 2011-3-24下午05:12:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void  modifyUseORAuditStatus(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * Description : 更改广告跑马灯排序  
	 * Create Date: 2011-9-23上午09:25:26 by zhaojf  Modified Date: 2011-9-23上午09:25:26 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateSort(String objId, boolean isToUp) throws Exception;
	
	/**
	 * Description :返回广告最大排序号  
	 * Create Date: 2011-9-23上午11:31:10 by zhaojf  Modified Date: 2011-9-23上午11:31:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Long adverSubscribeMaxSort() throws Exception;

}
