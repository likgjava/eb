package com.gpcsoft.epp.purchasedoc.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.purchasedoc.dao.ProofOpinionDao;
import com.gpcsoft.epp.purchasedoc.domain.ProofOpinion;
import com.gpcsoft.epp.purchasedoc.manager.ProofOpinionManager;

@Repository
public class ProofOpinionManagerImpl extends BaseManagerImpl<ProofOpinion> implements ProofOpinionManager {

	@Autowired(required=true)  @Qualifier("proofOpinionDaoHibernate")
	private ProofOpinionDao proofOpinionDaoHibernate;

}
