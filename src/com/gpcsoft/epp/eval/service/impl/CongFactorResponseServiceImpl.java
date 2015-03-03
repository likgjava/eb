package com.gpcsoft.epp.eval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.eval.dao.CongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.manager.CongFactorResponseManager;
import com.gpcsoft.epp.eval.service.CongFactorResponseService;

@Service 
public class CongFactorResponseServiceImpl extends BaseGenericServiceImpl<CongFactorResponse> implements CongFactorResponseService {

	@Autowired(required=true) @Qualifier("congFactorResponseManagerImpl")
	private CongFactorResponseManager congFactorResponseManager;

	@Autowired(required=true)  @Qualifier("congFactorResponseDaoHibernate")
	private CongFactorResponseDao congFactorResponseDaoHibernate;

}
