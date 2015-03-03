package com.gpcsoft.smallscale.expert.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.expert.domain.Certificate;
import com.gpcsoft.smallscale.expert.manager.CertificateManager;

@Repository
public class CertificateManagerImpl extends BaseManagerImpl<Certificate> implements CertificateManager {

//	@Autowired(required=true)  @Qualifier("certificateDaoHibernate")
//	private CertificateDao certificateDaoHibernate;

}
