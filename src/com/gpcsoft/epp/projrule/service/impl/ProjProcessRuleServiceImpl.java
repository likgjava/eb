package com.gpcsoft.epp.projrule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjPlanStatusEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemValue;

@Service 
public class ProjProcessRuleServiceImpl extends BaseGenericServiceImpl<ProjProcessRule> implements ProjProcessRuleService {

	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
    
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;

	@Autowired(required=true) @Qualifier("projectPlanManagerImpl")
	private ProjectPlanManager projectPlanManager;
	
	/**
	 * 
	 * Description :  根据对象主键得到项目规则对象
	 * Create Date: 2010-5-20上午11:36:02 by liuke  Modified Date: 2010-5-20上午11:36:02 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProjProcessRule> getProjProcessRuleByProjectId(String projectId) {
		logger.debug("\nProjProcessRuleServiceImpl||getProjProcessRuleByProjectId\n");
		return projProcessRuleManager.getProjProcessRuleByProjectId(projectId);
	}
	
	/**
	 * 保存项目规则
	 */
	@Override
	protected void onBeforeSave(ProjProcessRule baseobject) {
		super.onBeforeSave(baseobject);
		/** 保存项目实施状态 */
		projectManager.saveProjProcessStatus(baseobject.getProject().getObjId(), ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE);
	}
	
	/** 
	 * Description :  保存项目规则
	 * Create Date: 2010-11-4下午03:52:51 by yangx  Modified Date: 2010-11-4下午03:52:51 by yangx
	 * @param   projectId：项目Id,projProcessRules：规则集合
	 * @return  ProjProcessRule：保存项目规则
	 * @Exception   
	 */
	public ProjProcessRule saveProjProcessRule(String projectId,Set<ProjProcessRule> projProcessRules)throws Exception{
		logger.debug("\nProjProcessRuleServiceImpl||saveProjProcessRule\n");
		List<SysConfigItemValue> sysConfigItemValueList = new ArrayList<SysConfigItemValue>();// 重置项目计划
		Project project = projectManager.get(projectId);//关联的项目
		ProjProcessRule projProcessRule = new ProjProcessRule();//保存的项目规则
		for (ProjProcessRule projProcessRuleq:projProcessRules) {
			projProcessRuleq.setProject(project);
			projProcessRuleManager.save(projProcessRuleq);
			projProcessRule = projProcessRuleq;
			sysConfigItemValueList.add(this.createSysConfigItemValue(projProcessRuleq.getSysItemId(),projProcessRuleq.getResValue()));//创建SysConfigItemValue 对象
		}
		projectPlanManager.resetProjectPlan(projectId, sysConfigItemValueList);// 重置项目计划
		projectManager.save(project);
		projProcessRule.setParentBizId(projectId);
		projProcessRule.setUser(AuthenticationHelper.getCurrentUser());
		return projProcessRule;
	}
	
	/** 
	 * Description :  创建SysConfigItemValue 对象
	 * Create Date: 2010-8-13下午02:13:00 by yangx  Modified Date: 2010-8-13下午02:13:00 by yangx
	 * @param   objId：主键值,value：响应值
	 * @return  SysConfigItemValue
	 * @Exception   
	 */
	private SysConfigItemValue createSysConfigItemValue(String objId,String value)throws Exception{
		logger.debug("\nProjProcessRuleServiceImpl||createSysConfigItemValue\n");
		SysConfigItemValue sysConfigItemValue = new SysConfigItemValue();//系统配置值
		sysConfigItemValue.setObjId(objId);
		if(null != value && ProjPlanStatusEnum.IN_TRUE.equals(value)){
			sysConfigItemValue.setValue(ProjPlanStatusEnum.IN_EFFECT);//响应值
		}else{
			sysConfigItemValue.setValue(ProjPlanStatusEnum.INEFFICACY);//响应值
		}
		return sysConfigItemValue;
	}

	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code){
		logger.debug("\nProjProcessRuleServiceImpl||getProjProcessRuleByProjectIdAndCode\n");
		return projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(projectId,code);
	}
}
