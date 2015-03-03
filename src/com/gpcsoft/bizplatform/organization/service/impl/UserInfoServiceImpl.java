package com.gpcsoft.bizplatform.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.UserInfo;
import com.gpcsoft.bizplatform.organization.manager.UserInfoManager;
import com.gpcsoft.bizplatform.organization.service.UserInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class UserInfoServiceImpl extends BaseGenericServiceImpl<UserInfo> implements UserInfoService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("userInfoManagerImpl")
	private UserInfoManager userInfoManager;

}
