package com.gpcsoft.agreement.order.service;
import java.util.List;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.service.BaseGenericService;

public interface ProtaskItemService extends BaseGenericService<ProtaskItem> {

	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProtaskItem> getProtaskItemList(String objIds) throws Exception;
}
