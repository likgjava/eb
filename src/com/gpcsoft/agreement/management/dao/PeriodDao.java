package com.gpcsoft.agreement.management.dao;

import java.util.List;

import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PeriodDao extends BaseGenericDao<Period> {

	/** 
	 * Description :  删除period
	 * Create Date: 2010-4-17下午05:03:13 by yucy  Modified Date: 2010-4-17下午05:03:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer delPeriod(String periods);

	/** 
	 * Description :  获取按年度搜索框内容
	 * Create Date: 2010-4-19上午12:14:12 by yucy  Modified Date: 2010-4-19上午12:14:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	List getAnnualSearchContent();

}
