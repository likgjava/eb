package com.gpcsoft.agreement.bargin.project.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;

public interface ProjectSignInfoService extends BaseGenericService<ProjectSignInfo> {

	/** 
	 * Description :  取得信息（by projectID）
	 * Create Date: 2010-12-2上午11:34:00 by yucy  Modified Date: 2010-12-2上午11:34:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectSignInfo getSignInfoByProjectId(String projectId) throws Exception;

}
