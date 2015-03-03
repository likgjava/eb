package com.gpcsoft.smallscale.pointmall.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.pointmall.dao.VirtualGiftRecordDao;
import com.gpcsoft.smallscale.pointmall.manager.VirtualGiftRecordManager;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;

@Repository
public class VirtualGiftRecordManagerImpl extends BaseManagerImpl<VirtualGiftRecord> implements VirtualGiftRecordManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("virtualGiftRecordDaoHibernate")
	private VirtualGiftRecordDao virtualGiftRecordDaoHibernate;

}
