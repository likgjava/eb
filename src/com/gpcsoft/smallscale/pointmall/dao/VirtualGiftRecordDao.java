package com.gpcsoft.smallscale.pointmall.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;

public interface VirtualGiftRecordDao extends BaseGenericDao<VirtualGiftRecord> {

	/** 
	 * Description :   获得记录列表
	 * Create Date: 2011-1-16下午11:43:43 by yucy  Modified Date: 2011-1-16下午11:43:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<VirtualGiftRecord> getVirtualListByGiftId(String objId) throws Exception;

}
