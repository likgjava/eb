package com.gpcsoft.epp.projreview.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projreview.dao.OpenBidSignDao;
import com.gpcsoft.epp.projreview.manager.OpenBidSignManager;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;

@Repository
public class OpenBidSignManagerImpl extends BaseManagerImpl<OpenBidSign> implements OpenBidSignManager {

	@Autowired(required=true)  @Qualifier("openBidSignDaoHibernate")
	private OpenBidSignDao openBidSignDaoHibernate;

}
