package com.gpcsoft.agreement.bargin.project.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.project.domain.AssessmentRule;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface AssessmentRuleDao extends BaseGenericDao<AssessmentRule> {

	/** 
	 * Description : 获取规则集合根据品目
	 * Create Date: 2011-8-25下午03:31:33 by yucy  Modified Date: 2011-8-25下午03:31:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<AssessmentRule> getAssessmentRuleByCategoryIds( Map<String, Object> param) throws Exception;

}
