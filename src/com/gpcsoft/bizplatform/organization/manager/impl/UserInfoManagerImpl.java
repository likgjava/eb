package com.gpcsoft.bizplatform.organization.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.UserInfoDao;
import com.gpcsoft.bizplatform.organization.domain.UserInfo;
import com.gpcsoft.bizplatform.organization.manager.UserInfoManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class UserInfoManagerImpl extends BaseManagerImpl<UserInfo> implements UserInfoManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("userInfoDaoHibernate")
	private UserInfoDao userInfoDaoHibernate;

}
