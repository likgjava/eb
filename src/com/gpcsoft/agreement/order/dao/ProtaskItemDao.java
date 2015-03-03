package com.gpcsoft.agreement.order.dao;

import java.util.List;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface ProtaskItemDao extends BaseGenericDao<ProtaskItem> {

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
