package com.gpcsoft.agreement.bargin.project.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;

public interface ProjectContactInfoDao extends BaseGenericDao<ProjectContactInfo> {

	/** 
	 * Description :  取得信息(by projectId)
	 * Create Date: 2010-12-2上午11:28:40 by yucy  Modified Date: 2010-12-2上午11:28:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectContactInfo getContactInfoByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目ID删除扩展信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeContactInfoByProjId(final String projId) throws Exception;
}
