package com.gpcsoft.agreement.bargin.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.project.service.ProjectSignInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ProjectSignInfoServiceImpl extends BaseGenericServiceImpl<ProjectSignInfo> implements ProjectSignInfoService {

	
	@Autowired(required=true) @Qualifier("projectSignInfoDaoHibernate")
	private ProjectSignInfoDao projectSignInfoDao;

	/** 
	 * Description :  取得信息（by projectID）
	 * Create Date: 2010-12-2上午11:34:00 by yucy  Modified Date: 2010-12-2上午11:34:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProjectSignInfo getSignInfoByProjectId(String projectId) throws Exception {
		return projectSignInfoDao.getSignInfoByProjectId(projectId);
	}

}
