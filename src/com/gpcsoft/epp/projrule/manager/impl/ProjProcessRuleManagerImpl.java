package com.gpcsoft.epp.projrule.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.projrule.dao.ProjProcessRuleDao;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;

@Repository
public class ProjProcessRuleManagerImpl extends BaseManagerImpl<ProjProcessRule> implements ProjProcessRuleManager {

	@Autowired(required=true)  @Qualifier("projProcessRuleDaoHibernate")
	private ProjProcessRuleDao projProcessRuleDaoHibernate;
	
	/** 
	 * Description :  根据对象主键得到项目规则对象
	 * Create Date: 2010-10-28下午05:01:34 by yangx  Modified Date: 2010-10-28下午05:01:34 by yangx
	 * @param   projectId：项目Id
	 * @return  List<ProjProcessRule>
	 * @Exception   
	 */
	public List<ProjProcessRule> getProjProcessRuleByProjectId(String projectId){
		QueryObject<ProjProcessRule> queryObject = new QueryObjectBase<ProjProcessRule>();
		queryObject.setEntityClass(ProjProcessRule.class);
		queryObject.setQueryParam(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		List<ProjProcessRule> projProcessRule = projProcessRuleDaoHibernate.findByQuery(queryObject);
		return projProcessRule;
	}
	
	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code){
		return projProcessRuleDaoHibernate.getProjProcessRuleByProjectIdAndCode(projectId, code);
	}
}
