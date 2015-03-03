package com.gpcsoft.agreement.management.service;
import java.util.List;

import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.core.service.BaseGenericService;

public interface PeriodService extends BaseGenericService<Period> {

	/** 
	 * Description :  删除期间
	 * Create Date: 2010-4-17下午05:01:14 by yucy  Modified Date: 2010-4-17下午05:01:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer delPeriod(String periods);

	/** 
	 * Description :  获取按年度搜索框内容
	 * Create Date: 2010-4-19上午12:11:52 by yucy  Modified Date: 2010-4-19上午12:11:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	List getAnnualSearchContent();

}
