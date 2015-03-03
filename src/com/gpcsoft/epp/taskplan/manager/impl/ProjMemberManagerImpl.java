package com.gpcsoft.epp.taskplan.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.taskplan.dao.ProjMemberDao;
import com.gpcsoft.epp.taskplan.manager.ProjMemberManager;
import com.gpcsoft.epp.taskplan.domain.ProjMember;

@Repository
public class ProjMemberManagerImpl extends BaseManagerImpl<ProjMember> implements ProjMemberManager {

	@Autowired(required=true)  @Qualifier("projMemberDaoHibernate")
	private ProjMemberDao projMemberDaoHibernate;

}
