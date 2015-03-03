package com.gpcsoft.agreement.management.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementSecondDao;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.manager.AgreementSecondManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class AgreementSecondManagerImpl extends BaseManagerImpl<AgreementSecond> implements AgreementSecondManager {

	@Autowired(required=true)  @Qualifier("agreementSecondDaoHibernate")
	private AgreementSecondDao agreementSecondDaoHibernate;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AgreementSecondManager#saveAgreementSecond(java.util.Map)
	 */
	public Integer saveAgreementSecond(Map<String, Object> params) {
		return agreementSecondDaoHibernate.saveAgreementSecond(params);
	}
	
}
