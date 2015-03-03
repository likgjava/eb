package com.gpcsoft.agreement.bargin.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.ProjectContactInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;
import com.gpcsoft.agreement.bargin.project.service.ProjectContactInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ProjectContactInfoServiceImpl extends BaseGenericServiceImpl<ProjectContactInfo> implements ProjectContactInfoService {

	@Autowired(required=true) @Qualifier("projectContactInfoDaoHibernate")
	private ProjectContactInfoDao projectContactInfoDao;

	/** 
	 * Description :  取得信息(by projectId)
	 * Create Date: 2010-12-2上午11:28:40 by yucy  Modified Date: 2010-12-2上午11:28:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProjectContactInfo getContactInfoByProjectId(String projectId) throws Exception {
		return projectContactInfoDao.getContactInfoByProjectId(projectId);
	}

}
