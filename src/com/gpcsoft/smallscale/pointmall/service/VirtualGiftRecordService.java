package com.gpcsoft.smallscale.pointmall.service;


import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;

public interface VirtualGiftRecordService extends BaseGenericService<VirtualGiftRecord> {
	
	/**
	 * 根据礼品规则保存虚拟礼品
	 * @param eruleId
	 * @param giftCount
	 */
	public void save(String giftId,String eruleId,Integer giftCount,String email);

	
	/**
	 * 礼品处理
	 * @param virtualGiftRecord
	 */
	public void saveDeal(VirtualGiftRecord virtualGiftRecord);


	/** 
	 * Description :   获得记录列表
	 * Create Date: 2011-1-16下午11:43:43 by yucy  Modified Date: 2011-1-16下午11:43:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<VirtualGiftRecord> getVirtualListByGiftId(String objId) throws Exception;
}
