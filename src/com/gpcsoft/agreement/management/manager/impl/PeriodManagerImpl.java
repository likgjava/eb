package com.gpcsoft.agreement.management.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.PeriodDao;
import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.agreement.management.manager.PeriodManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class PeriodManagerImpl extends BaseManagerImpl<Period> implements PeriodManager {

	@Autowired(required=true)  @Qualifier("periodDaoHibernate")
	private PeriodDao periodDaoHibernate;
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.PeriodService#delPeriod(java.lang.String)
	 */
	public Integer delPeriod(String periods) {
		return periodDaoHibernate.delPeriod(periods);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.PeriodService#getAnnualSearchContent()
	 */
	@SuppressWarnings("unchecked")
	public List getAnnualSearchContent() {
		return periodDaoHibernate.getAnnualSearchContent();
	}

}
