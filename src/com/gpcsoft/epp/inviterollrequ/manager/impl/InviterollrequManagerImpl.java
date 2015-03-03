package com.gpcsoft.epp.inviterollrequ.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.inviterollrequ.dao.InviterollrequDao;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.manager.InviterollrequManager;

@Repository
public class InviterollrequManagerImpl extends BaseManagerImpl<Inviterollrequ> implements InviterollrequManager {

	@Autowired(required=true)  @Qualifier("inviterollrequDaoHibernate")
	private InviterollrequDao inviterollrequDaoHibernate;

}
