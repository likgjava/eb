package com.gpcsoft.agreement.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.agreement.management.manager.PeriodManager;
import com.gpcsoft.agreement.management.service.PeriodService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class PeriodServiceImpl extends BaseGenericServiceImpl<Period> implements PeriodService {

	@Autowired(required=true) @Qualifier("periodManagerImpl")
	private PeriodManager periodManager;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.PeriodService#delPeriod(java.lang.String)
	 */
	public Integer delPeriod(String periods) {
		return periodManager.delPeriod(periods);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.PeriodService#getAnnualSearchContent()
	 */
	@SuppressWarnings("unchecked")
	public List getAnnualSearchContent() {
		return periodManager.getAnnualSearchContent();
	}

}
