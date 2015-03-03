package com.gpcsoft.agreement.bargin.project.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.bargin.project.dao.ProjectContactInfoDao;
import com.gpcsoft.agreement.bargin.project.manager.ProjectContactInfoManager;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;

@Repository
public class ProjectContactInfoManagerImpl extends BaseManagerImpl<ProjectContactInfo> implements ProjectContactInfoManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("projectContactInfoDaoHibernate")
	private ProjectContactInfoDao projectContactInfoDaoHibernate;

}
