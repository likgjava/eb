package com.gpcsoft.agreement.bargin.project.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;

public interface ProjectPayInfoService extends BaseGenericService<ProjectPayInfo> {

	/** 
	 * Description :  根据项目id取得扩展信息
	 * Create Date: 2010-11-26下午05:38:21 by yucy  Modified Date: 2010-11-26下午05:38:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	ProjectPayInfo getPayInfoByProjectId(String projectId) throws Exception;
}
