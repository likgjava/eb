package com.gpcsoft.smallscale.pointmall.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.pointmall.domain.RealGiftRecord;

public interface RealGiftRecordService extends BaseGenericService<RealGiftRecord> {
	
	/**
	 * 保存实物兑换记录
	 * @param realGiftRecord
	 * @param giftId
	 * @param eruleId
	 */
	public void saveRecord(RealGiftRecord realGiftRecord,String giftId,String eruleId);
	
	/**
	 * 处理兑换
	 * @param realGiftRecord
	 */
	public void saveDealRecord(RealGiftRecord realGiftRecord);

	/** 
	 * Description :  获得记录列表
	 * Create Date: 2011-1-16下午11:42:53 by yucy  Modified Date: 2011-1-16下午11:42:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<RealGiftRecord> getRecordListByGiftId(String objId) throws Exception;

}
