package com.gpcsoft.bizplatform.organization.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.UserInfoDao;
import com.gpcsoft.bizplatform.organization.domain.UserInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class UserInfoDaoHibernate extends BaseGenericDaoHibernate<UserInfo> implements UserInfoDao {

}
