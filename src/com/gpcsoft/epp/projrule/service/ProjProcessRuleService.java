package com.gpcsoft.epp.projrule.service;
import java.util.List;
import java.util.Set;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;

public interface ProjProcessRuleService extends BaseGenericService<ProjProcessRule> {


	
	/**
	 * 
	 * Description :  根据对象主键得到项目规则对象
	 * Create Date: 2010-5-20上午11:36:02 by liuke  Modified Date: 2010-5-20上午11:36:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProjProcessRule> getProjProcessRuleByProjectId(String projectId) ;
	
	/** 
	 * Description :  保存项目规则
	 * Create Date: 2010-11-4下午03:52:51 by yangx  Modified Date: 2010-11-4下午03:52:51 by yangx
	 * @param   projectId：项目Id,projProcessRules：规则集合
	 * @return  ProjProcessRule：保存项目规则
	 * @Exception   
	 */
	public ProjProcessRule saveProjProcessRule(String projectId,Set<ProjProcessRule> projProcessRules)throws Exception;

	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code);

}
