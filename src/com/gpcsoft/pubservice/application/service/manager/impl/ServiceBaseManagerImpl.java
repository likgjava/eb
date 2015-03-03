package com.gpcsoft.pubservice.application.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.service.dao.ServiceBaseDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.manager.ServiceBaseManager;

@Repository
public class ServiceBaseManagerImpl extends BaseManagerImpl<ServiceBase> implements ServiceBaseManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("serviceBaseDaoHibernate")
	private ServiceBaseDao serviceBaseDaoHibernate;

}
