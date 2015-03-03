package com.gpcsoft.smallscale.point.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.point.domain.Rule;

public interface RuleDao extends BaseGenericDao<Rule> {
	/** 
	 * Description :  根据积分来源和积分计算参数取得规则
	 * Create Date: 2010-10-19下午05:36:03 by dongcl  Modified Date: 2010-10-19下午05:36:03 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Rule getUniqueRule(String sourceCode,Double amount);
}
