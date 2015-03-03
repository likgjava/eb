package com.gpcsoft.epp.purchasedoc.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.purchasedoc.dao.ProcFileDownRecDao;
import com.gpcsoft.epp.purchasedoc.manager.ProcFileDownRecManager;
import com.gpcsoft.epp.purchasedoc.domain.ProcFileDownRec;

@Repository
public class ProcFileDownRecManagerImpl extends BaseManagerImpl<ProcFileDownRec> implements ProcFileDownRecManager {

	@Autowired(required=true)  @Qualifier("procFileDownRecDaoHibernate")
	private ProcFileDownRecDao procFileDownRecDaoHibernate;

}
