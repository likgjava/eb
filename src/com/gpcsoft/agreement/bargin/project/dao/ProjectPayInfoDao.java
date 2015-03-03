package com.gpcsoft.agreement.bargin.project.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;

public interface ProjectPayInfoDao extends BaseGenericDao<ProjectPayInfo> {

	/** 
	 * Description :  根据项目id获取扩展信息
	 * Create Date: 2010-11-26下午05:00:21 by yucy  Modified Date: 2010-11-26下午05:00:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectPayInfo getPayInfoByProjectId(String projectId) throws Exception;

	/** 
	 * Description :  根据项目ID删除扩展信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removePayInfoByProjId(final String projId) throws Exception;
}
