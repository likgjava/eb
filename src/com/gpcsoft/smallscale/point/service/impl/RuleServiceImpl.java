package com.gpcsoft.smallscale.point.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.point.manager.RuleManager;
import com.gpcsoft.smallscale.point.service.RuleService;
import com.gpcsoft.smallscale.point.domain.Rule;

@Service 
public class RuleServiceImpl extends BaseGenericServiceImpl<Rule> implements RuleService {

	@Autowired(required=true) @Qualifier("ruleManagerImpl")
	private RuleManager ruleManager;
	
	public Rule getUniqueRule(String sourceCode,Double amount){
		
		
		return ruleManager.getUniqueRule(sourceCode, amount);
	}
	
	public Double getPointNumber(String sourceCode,Double amount){
		
		return ruleManager.getPointNumber(sourceCode, amount);
	}

}
