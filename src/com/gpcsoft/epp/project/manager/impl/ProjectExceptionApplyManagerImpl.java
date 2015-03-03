package com.gpcsoft.epp.project.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.project.dao.ProjectExceptionApplyDao;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.manager.ProjectExceptionApplyManager;

@Repository
public class ProjectExceptionApplyManagerImpl extends BaseManagerImpl<ProjectExceptionApply> implements ProjectExceptionApplyManager {

	@Autowired(required=true)  @Qualifier("projectExceptionApplyDaoHibernate")
	private ProjectExceptionApplyDao projectExceptionApplyDaoHibernate;

}
