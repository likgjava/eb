package com.gpcsoft.epp.signuprecord.decision;

import java.util.Date;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.epp.common.decision.ProjectPlanDesion;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.manager.SignUprecordManager;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

/** 
 *  Comments: <strong>ProjectRuleDesion</strong>            		
 *	 <br/>
 *  根据工作计划和业务实体，判断是否是当前的报名操作
 *  如：返回true,否则返回false		 		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   srplatform                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-11-9 上午09:29:00 by yangx    					                            
 *  <br/>Modified Date:  2010-11-9 上午09:29:00 by yangx                                   
 *	  @since 0.5
 *   @version: 0.5 
 */ 
public class SignUpRecordDesion implements ProjectPlanDesion {

	private ProjectManager projectManager;
	private ProjectManager getProjectManager(){
		if(this.projectManager == null){
				this.projectManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManager;
	}
	
	private SignUprecordManager signUprecordManager;
	private SignUprecordManager getSignUprecordManager(){
		if(this.signUprecordManager == null){
			this.signUprecordManager = (SignUprecordManager)FrameBeanFactory.getBean("signUprecordManagerImpl");
		}
		return this.signUprecordManager;
	}
	
	private FrameMessageResource messageSource;
	private FrameMessageResource getMessageSource(){
		if(this.messageSource == null){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	
	
	public Boolean endDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		//modify by yangx此处只是提交报名信息不需要操作状态
		return true;
	}

	/** 
	 * Description :  决策是否可以启动报名操作
	 * Create Date: 2010-11-22下午02:24:30 by yangx  Modified Date: 2010-11-22下午02:24:30 by yangx
	 * @param   projectPlan:对应的工作计划,bizObject:对应的业务对象
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean launchDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		return true;
	}

	/** 
	 * Description :  决策是否可以开始做报名操作
	 * Create Date: 2010-11-22下午02:24:30 by yangx  Modified Date: 2010-11-22下午02:24:30 by yangx
	 * @param   projectPlan:对应的工作计划,bizObject:对应的业务对象
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean startDecide(ProjectPlan projectPlan, Object bizObject)throws Exception {
		Boolean checkValue = false;
		WorkFlowModel workFlowModel = (WorkFlowModel)bizObject;
		ProjectRule projectRule =this.getProjectManager().getProjectRuleByProjectId(workFlowModel.getParentBizId());//根据项目Id获取项目规则时间
		Date nowDate = new Date();
		if (projectRule.getSignUpSTime()!=null&&projectRule.getSignUpETime()!=null&&nowDate.after(projectRule.getSignUpSTime())&&nowDate.before(projectRule.getSignUpETime())){//判断能否报名
			checkValue = true;
		}
		
		return checkValue;
	}
}
