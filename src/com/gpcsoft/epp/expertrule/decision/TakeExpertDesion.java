package com.gpcsoft.epp.expertrule.decision;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.manager.impl.ExpertRuleManagerImpl;
import com.gpcsoft.epp.project.domain.ProjectPlan;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的申请专家操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class TakeExpertDesion implements ProjectPlanDesion {
	private ExpertRuleManagerImpl expertRuleManagerImpl;
	private ExpertRuleManagerImpl getExpertRuleManagerImpl(){
		if(this.expertRuleManagerImpl == null){
			this.expertRuleManagerImpl = (ExpertRuleManagerImpl) FrameBeanFactory.getBean("expertRuleManagerImpl");
		}
		return this.expertRuleManagerImpl;
	}

	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		TakeExpertRule takeExpertRule = (TakeExpertRule)bizObject;
		takeExpertRule.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//将规则对象保存成正式
		getExpertRuleManagerImpl().save(takeExpertRule, TakeExpertRule.class);
		return true;
	}

	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}
}
