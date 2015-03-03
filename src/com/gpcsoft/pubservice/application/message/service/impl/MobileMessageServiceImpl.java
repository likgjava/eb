package com.gpcsoft.pubservice.application.message.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.pubservice.application.message.manager.MobileMessageManager;
import com.gpcsoft.pubservice.application.message.service.MobileMessageService;
import com.gpcsoft.pubservice.application.message.domain.MobileMessage;

@Service 
public class MobileMessageServiceImpl extends BaseGenericServiceImpl<MobileMessage> implements MobileMessageService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("mobileMessageManagerImpl")
	private MobileMessageManager mobileMessageManager;

}
