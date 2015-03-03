package com.gpcsoft.agreement.management.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.manager.AgreementSecondManager;
import com.gpcsoft.agreement.management.service.AgreementSecondService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class AgreementSecondServiceImpl extends BaseGenericServiceImpl<AgreementSecond> implements AgreementSecondService {

	@Autowired(required=true) @Qualifier("agreementSecondManagerImpl")
	private AgreementSecondManager agreementSecondManager;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementSecondService#saveAgreementSecond(java.util.Map)
	 */
	public Integer saveAgreementSecond(Map<String, Object> params) {
		return agreementSecondManager.saveAgreementSecond(params);
	}

}
