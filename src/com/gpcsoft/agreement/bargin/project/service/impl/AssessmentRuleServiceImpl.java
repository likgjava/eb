package com.gpcsoft.agreement.bargin.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.project.dao.AssessmentRuleDao;
import com.gpcsoft.agreement.bargin.project.domain.AssessmentRule;
import com.gpcsoft.agreement.bargin.project.service.AssessmentRuleService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class AssessmentRuleServiceImpl extends BaseGenericServiceImpl<AssessmentRule> implements AssessmentRuleService {

	@Autowired(required=true) @Qualifier("assessmentRuleDaoHibernate")
	private AssessmentRuleDao assessmentRuleDao;

	/** 
	 * Description : 获取规则集合根据品目
	 * Create Date: 2011-8-25下午03:31:33 by yucy  Modified Date: 2011-8-25下午03:31:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<AssessmentRule> getAssessmentRuleByCategoryIds(Map<String, Object> param) throws Exception{
		return assessmentRuleDao.getAssessmentRuleByCategoryIds( param );
	}
}
