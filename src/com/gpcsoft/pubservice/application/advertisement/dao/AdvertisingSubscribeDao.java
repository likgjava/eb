package com.gpcsoft.pubservice.application.advertisement.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

public interface AdvertisingSubscribeDao extends BaseGenericDao<AdvertisingSubscribe> {
	
	/**
	 * Description :  更改使用状态或审核状态
	 * Create Date: 2011-3-24下午05:12:15 by zhaojf  Modified Date: 2011-3-24下午05:12:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void  modifyUseORAuditStatus(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * Description :  根据广告位Id获取广告订阅对象列表
	 * Create Date: 2011-3-25下午03:07:03 by zhaojf  Modified Date: 2011-3-25下午03:07:03 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(Map<String, Object> paramsMap) throws Exception;
	
	/**
	 * Description : 更改广告跑马灯排序  
	 * Create Date: 2011-9-23上午09:25:26 by zhaojf  Modified Date: 2011-9-23上午09:25:26 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateSort(Long sort, String positionId, boolean isToUp) throws Exception;
	
	/**
	 * Description :返回广告最大排序号  
	 * Create Date: 2011-9-23上午11:31:10 by zhaojf  Modified Date: 2011-9-23上午11:31:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Long adverSubscribeMaxSort() throws Exception;
}
