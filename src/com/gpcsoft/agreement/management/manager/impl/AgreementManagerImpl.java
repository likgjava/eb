package com.gpcsoft.agreement.management.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementDao;
import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.agreement.management.manager.AgreementManager;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;
import com.gpcsoft.srplatform.baseData.dao.hibernate.SequenceNumberDaoHibernate;

@Repository
public class AgreementManagerImpl extends BaseManagerImpl<Agreement> implements AgreementManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("agreementDaoHibernate")
	private AgreementDao agreementDaoHibernate;

	public String createAgreementNo() {
		SequenceNumberDao sequenceNumberDaoHibernate =  (SequenceNumberDaoHibernate) FrameBeanFactory.getBean("sequenceNumberDaoHibernate");
		String No = "A-"+DateUtil.format(new Date(), "yyyyMMdd");
		return No + "-" + sequenceNumberDaoHibernate.updateAndGetCurSn(No, 3);
	}

}
