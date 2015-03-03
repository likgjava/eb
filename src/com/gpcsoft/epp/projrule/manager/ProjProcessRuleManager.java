package com.gpcsoft.epp.projrule.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;

public interface ProjProcessRuleManager extends BaseManager<ProjProcessRule> {

	/** 
	 * Description :  根据对象主键得到项目规则对象
	 * Create Date: 2010-10-28下午05:01:34 by yangx  Modified Date: 2010-10-28下午05:01:34 by yangx
	 * @param   projectId：项目Id
	 * @return  List<ProjProcessRule>
	 * @Exception   
	 */
	public List<ProjProcessRule> getProjProcessRuleByProjectId(String projectId);
	
	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code);
}
