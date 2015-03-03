package com.gpcsoft.smallscale.point.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.point.manager.PromoterInfoManager;
import com.gpcsoft.smallscale.point.service.PromoterInfoService;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;

@Service 
public class PromoterInfoServiceImpl extends BaseGenericServiceImpl<PromoterInfo> implements PromoterInfoService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("promoterInfoManagerImpl")
	private PromoterInfoManager promoterInfoManager;

}
