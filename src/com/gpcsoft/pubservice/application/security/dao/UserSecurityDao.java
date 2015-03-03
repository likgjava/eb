package com.gpcsoft.pubservice.application.security.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;
import com.gpcsoft.srplatform.auth.domain.User;

public interface UserSecurityDao extends BaseGenericDao<UserSecurity> {

	public List<UserSecurity> getSecurityByUserId(String userId);

	public User getUserByUsName(String usName);

}
