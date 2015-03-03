package com.gpcsoft.agreement.bargin.project.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;

public interface ProjectContactInfoService extends BaseGenericService<ProjectContactInfo> {

	/** 
	 * Description :  取得信息(by projectId)
	 * Create Date: 2010-12-2上午11:28:40 by yucy  Modified Date: 2010-12-2上午11:28:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectContactInfo getContactInfoByProjectId(String projectId) throws Exception;

}
