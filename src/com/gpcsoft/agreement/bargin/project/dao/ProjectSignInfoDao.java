package com.gpcsoft.agreement.bargin.project.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;

public interface ProjectSignInfoDao extends BaseGenericDao<ProjectSignInfo> {

	/** 
	 * Description :  取得信息（by projectID）
	 * Create Date: 2010-12-2上午11:34:00 by yucy  Modified Date: 2010-12-2上午11:34:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectSignInfo getSignInfoByProjectId(String projectId) throws Exception;

	/** 
	 * Description :  根据项目ID删除扩展信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeSignInfoByProjId(final String projId) throws Exception;
}
