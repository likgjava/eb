package com.gpcsoft.agreement.bargin.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.ProjectPayInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.service.ProjectPayInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ProjectPayInfoServiceImpl extends BaseGenericServiceImpl<ProjectPayInfo> implements ProjectPayInfoService {
	@Autowired(required=true) @Qualifier("projectPayInfoDaoHibernate")
	private ProjectPayInfoDao projectPayInfoDao;

	/** 
	 * Description :  根据项目id取得扩展信息
	 * Create Date: 2010-11-26下午05:38:21 by yucy  Modified Date: 2010-11-26下午05:38:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ProjectPayInfo getPayInfoByProjectId(String projectId) throws Exception {
		return projectPayInfoDao.getPayInfoByProjectId(projectId);
	}
}
