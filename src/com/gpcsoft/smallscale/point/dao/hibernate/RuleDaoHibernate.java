package com.gpcsoft.smallscale.point.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.RuleDao;
import com.gpcsoft.smallscale.point.domain.Rule;
import org.springframework.stereotype.Repository;

@Repository
public class RuleDaoHibernate extends BaseGenericDaoHibernate<Rule> implements RuleDao {
	public Rule getUniqueRule(String sourceCode,Double amount){
		
		StringBuffer hql = new StringBuffer("from Rule where sourceCode='");
		hql.append(sourceCode).append("' and currentStatus=1 ");
		if(amount!=null && amount>0){
			hql.append(" and amountMin<=").append(amount);
			hql.append(" and amountMax>").append(amount);
		}
		
		Object[] param = null;
		List<Rule> r = super.findbyHql(hql.toString(), param);
		if(r!=null && r.size()>0){
			return r.get(0);
		}
		return null;
		
	}
}
