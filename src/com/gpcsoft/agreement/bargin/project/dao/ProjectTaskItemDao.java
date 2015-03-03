package com.gpcsoft.agreement.bargin.project.dao;

import com.gpcsoft.agreement.bargin.project.domain.ProjectTaskItem;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface ProjectTaskItemDao extends BaseGenericDao<ProjectTaskItem> {

	/** 
	 * Description :  根据项目Id删除项目与任务书的关联数据
	 * Create Date: 2011-12-7上午10:20:16 by likg  Modified Date: 2011-12-7上午10:20:16 by likg
	 * @param   projectId：项目Id
	 * @return  
	 * @Exception   
	 */
	void deleteByProjectId(String projectId) throws Exception;
	
}
