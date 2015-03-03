package com.gpcsoft.agreement.bargin.project.dao;

import com.gpcsoft.agreement.bargin.project.domain.RequireTaskItem;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface RequireTaskItemDao extends BaseGenericDao<RequireTaskItem> {
	
	/** 
	 * Description :  根据需求Id获取任务书条目(返回值也能为空)
	 * Create Date: 2011-1-21上午11:14:06 by yucy  Modified Date: 2011-1-21上午11:14:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProtaskItem getTaskItemByRequireItem (String requireItemId)throws Exception;

}
