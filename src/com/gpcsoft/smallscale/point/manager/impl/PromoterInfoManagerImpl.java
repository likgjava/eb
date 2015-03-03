package com.gpcsoft.smallscale.point.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.PromoterInfoDao;
import com.gpcsoft.smallscale.point.manager.PromoterInfoManager;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;

@Repository
public class PromoterInfoManagerImpl extends BaseManagerImpl<PromoterInfo> implements PromoterInfoManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("promoterInfoDaoHibernate")
	private PromoterInfoDao promoterInfoDaoHibernate;

}
