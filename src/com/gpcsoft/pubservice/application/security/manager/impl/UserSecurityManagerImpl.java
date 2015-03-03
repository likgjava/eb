package com.gpcsoft.pubservice.application.security.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.security.dao.UserSecurityDao;
import com.gpcsoft.pubservice.application.security.manager.UserSecurityManager;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;

@Repository
public class UserSecurityManagerImpl extends BaseManagerImpl<UserSecurity> implements UserSecurityManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("userSecurityDaoHibernate")
	private UserSecurityDao userSecurityDaoHibernate;

}
