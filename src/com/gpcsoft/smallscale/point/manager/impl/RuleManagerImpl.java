package com.gpcsoft.smallscale.point.manager.impl;

import java.math.BigDecimal;


import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.RuleDao;
import com.gpcsoft.smallscale.point.manager.RuleManager;
import com.gpcsoft.smallscale.point.domain.Rule;

@Repository
public class RuleManagerImpl extends BaseManagerImpl<Rule> implements RuleManager {

	@Autowired(required=true)  @Qualifier("ruleDaoHibernate")
	private RuleDao ruleDaoHibernate;
	
	public Rule getUniqueRule(String sourceCode,Double amount){		
		
		return ruleDaoHibernate.getUniqueRule(sourceCode, amount);
	}
	
	public Double getPointNumber(String sourceCode,Double amount){
		Rule r = this.getUniqueRule(sourceCode, amount);
		if(r != null){
			BigDecimal pnumber = new BigDecimal(r.getPointNumber());
			pnumber = pnumber.multiply(new BigDecimal(r.getPointSign()));			
			if(r.getPointWay().equals(Rule.POINT_WAY_PERCENT)){
				pnumber = pnumber.multiply(new BigDecimal(r.getPointPercent()));
				pnumber = pnumber.divide(new BigDecimal(100));					
			}
			
			return pnumber.doubleValue();
			
		}
		
		return new Double(0.0);
	}

}
