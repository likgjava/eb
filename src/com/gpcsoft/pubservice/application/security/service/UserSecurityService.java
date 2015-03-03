package com.gpcsoft.pubservice.application.security.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;
import com.gpcsoft.srplatform.auth.domain.User;

public interface UserSecurityService extends BaseGenericService<UserSecurity> {

	public List<UserSecurity> getSecurityByUserId(String userId);

	public User getUserByUsName(String usName);

}
