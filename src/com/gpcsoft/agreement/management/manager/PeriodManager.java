package com.gpcsoft.agreement.management.manager;

import java.util.List;

import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.core.manager.BaseManager;

public interface PeriodManager extends BaseManager<Period> {

	/** 
	 * Description :  删除期间
	 * Create Date: 2010-5-7上午10:24:26 by yucy  Modified Date: 2010-5-7上午10:24:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer delPeriod(String periods);

	/** 
	 * Description :  获取按年度搜索框内容
	 * Create Date: 2010-5-7上午10:24:39 by yucy  Modified Date: 2010-5-7上午10:24:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	List getAnnualSearchContent();

}
