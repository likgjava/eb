package com.gpcsoft.pubservice.application.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.security.dao.UserSecurityDao;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;
import com.gpcsoft.pubservice.application.security.manager.UserSecurityManager;
import com.gpcsoft.pubservice.application.security.service.UserSecurityService;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class UserSecurityServiceImpl extends BaseGenericServiceImpl<UserSecurity> implements UserSecurityService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("userSecurityManagerImpl")
	private UserSecurityManager userSecurityManager;
	
	@Autowired(required=true)  @Qualifier("userSecurityDaoHibernate")
	private UserSecurityDao userSecurityDaoHibernate;
	
	public List<UserSecurity> getSecurityByUserId(String userId){
		return userSecurityDaoHibernate.getSecurityByUserId(userId);
	}

	public User getUserByUsName(String usName) {
		return userSecurityDaoHibernate.getUserByUsName(usName);
	}

}
