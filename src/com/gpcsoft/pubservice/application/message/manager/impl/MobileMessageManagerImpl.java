package com.gpcsoft.pubservice.application.message.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.message.dao.MobileMessageDao;
import com.gpcsoft.pubservice.application.message.manager.MobileMessageManager;
import com.gpcsoft.pubservice.application.message.domain.MobileMessage;

@Repository
public class MobileMessageManagerImpl extends BaseManagerImpl<MobileMessage> implements MobileMessageManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("mobileMessageDaoHibernate")
	private MobileMessageDao mobileMessageDaoHibernate;

}
