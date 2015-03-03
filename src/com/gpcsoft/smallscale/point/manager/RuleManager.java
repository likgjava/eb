package com.gpcsoft.smallscale.point.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Rule;

public interface RuleManager extends BaseManager<Rule> {
	
	/** 
	 * Description : 根据积分来源和积分计算参数取得规则
	 * Create Date: 2010-10-19下午03:16:07 by dongcl  Modified Date: 2010-10-19下午03:16:07 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Rule getUniqueRule(String sourceCode,Double amount);
	
	/** 
	 * Description :  根据积分来源和积分计算参数取得规则对应的积分数
	 * Create Date: 2010-10-19下午03:15:55 by dongcl  Modified Date: 2010-10-19下午03:15:55 by dongcl
	 * @param   
	 * @return 返回积分数，积分累加方式为减分时，值为负数
	 * @Exception   
	 */
	Double getPointNumber(String sourceCode,Double amount);

}
