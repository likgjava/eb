package com.gpcsoft.agreement.bargin.project.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.bargin.project.dao.ProjectPayInfoDao;
import com.gpcsoft.agreement.bargin.project.manager.ProjectPayInfoManager;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;

@Repository
public class ProjectPayInfoManagerImpl extends BaseManagerImpl<ProjectPayInfo> implements ProjectPayInfoManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("projectPayInfoDaoHibernate")
	private ProjectPayInfoDao projectPayInfoDaoHibernate;

}
