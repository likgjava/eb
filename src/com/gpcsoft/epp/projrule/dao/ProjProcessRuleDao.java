package com.gpcsoft.epp.projrule.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;

public interface ProjProcessRuleDao extends BaseGenericDao<ProjProcessRule> {

	/** 
	 * Description :  根据项目Id删除规则
	 * Create Date: 2010-10-18下午04:56:32 by yangx  Modified Date: 2010-10-18下午04:56:32 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeProjProcessRuleByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code);
}
