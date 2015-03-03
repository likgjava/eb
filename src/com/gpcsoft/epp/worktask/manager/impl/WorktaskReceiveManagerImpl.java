package com.gpcsoft.epp.worktask.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.worktask.dao.WorktaskReceiveDao;
import com.gpcsoft.epp.worktask.domain.WorktaskReceive;
import com.gpcsoft.epp.worktask.manager.WorktaskReceiveManager;

@Repository
public class WorktaskReceiveManagerImpl extends BaseManagerImpl<WorktaskReceive> implements WorktaskReceiveManager {

	@Autowired(required=true)  @Qualifier("worktaskReceiveDaoHibernate")
	private WorktaskReceiveDao worktaskReceiveDaoHibernate;

}
