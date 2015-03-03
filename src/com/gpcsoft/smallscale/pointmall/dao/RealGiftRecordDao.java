package com.gpcsoft.smallscale.pointmall.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.pointmall.domain.RealGiftRecord;

public interface RealGiftRecordDao extends BaseGenericDao<RealGiftRecord> {

	/** 
	 * Description :  获得记录列表
	 * Create Date: 2011-1-16下午11:42:53 by yucy  Modified Date: 2011-1-16下午11:42:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<RealGiftRecord> getRecordListByGiftId(String objId) throws Exception;

}
