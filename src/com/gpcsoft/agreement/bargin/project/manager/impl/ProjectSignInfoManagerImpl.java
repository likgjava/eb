package com.gpcsoft.agreement.bargin.project.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.manager.ProjectSignInfoManager;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;

@Repository
public class ProjectSignInfoManagerImpl extends BaseManagerImpl<ProjectSignInfo> implements ProjectSignInfoManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("projectSignInfoDaoHibernate")
	private ProjectSignInfoDao projectSignInfoDaoHibernate;

}
