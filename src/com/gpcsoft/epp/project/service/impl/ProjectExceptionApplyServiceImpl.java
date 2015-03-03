package com.gpcsoft.epp.project.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.project.dao.ProjectExceptionApplyDao;
import com.gpcsoft.epp.project.domain.ProjExceptionApplyEnum;
import com.gpcsoft.epp.project.domain.ProjImplStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectExceptionApplyManager;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.service.ProjectExceptionApplyService;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;

@Service 
public class ProjectExceptionApplyServiceImpl extends BaseGenericServiceImpl<ProjectExceptionApply> implements ProjectExceptionApplyService {

	@Autowired(required=true) @Qualifier("projectExceptionApplyManagerImpl")
	private ProjectExceptionApplyManager projectExceptionApplyManager;
	
	@Autowired(required=true)  @Qualifier("projectExceptionApplyDaoHibernate")
	private ProjectExceptionApplyDao projectExceptionApplyDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanManagerImpl")
	private ProjectMTaskPlanManager projectMTaskPlanManager;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	/** 
	 * Description :  保存异常申请
	 * Create Date: 2010-10-11下午03:57:54 by yangx  
	 * Modified Date: 2010-10-11下午03:57:54 by yangx
	 * @param   projectExceptionApply:异常对象
	 * @return  ProjectExceptionApply:异常对象
	 * @Exception   
	 */
	public ProjectExceptionApply saveExceptionApply(ProjectExceptionApply projectExceptionApply){
		return projectExceptionApplyManager.save(projectExceptionApply);
	}
	
	/** 
	 * Description :  保存修改异常申请
	 * Create Date: 2010-10-11下午03:57:54 by yangx  Modified Date: 2010-10-11下午03:57:54 by yangx
	 * @param   projectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveUpdateExceptionApply(ProjectExceptionApply projectExceptionApply){
		return projectExceptionApplyManager.save(projectExceptionApply);
	}
	
	/** 
	 * Description :  提交异常申请
	 * Create Date: 2010-10-12下午01:09:47 by yangx  Modified Date: 2010-10-12下午01:09:47 by yangx
	 * @param   projectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveSubmitExceptionApply(ProjectExceptionApply projectExceptionApply){
		projectExceptionApply = projectExceptionApplyManager.save(projectExceptionApply);
		completedWorkTaskManager.createCompTaskByPassivity("提交异常申请","08",null,projectExceptionApply.getObjId(),projectExceptionApply.getProject().getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return projectExceptionApply;
	}
	
	/** 
	 * Description :  提交修改异常申请
	 * Create Date: 2010-10-12下午01:09:47 by yangx  Modified Date: 2010-10-12下午01:09:47 by yangx
	 * @param   PrprojectExceptionApply：异常对象
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply saveSubmitUpdateExceptionApply(ProjectExceptionApply projectExceptionApply){
		projectExceptionApply = projectExceptionApplyManager.save(projectExceptionApply);
		completedWorkTaskManager.createCompTaskByPassivity("提交异常申请","08",null,projectExceptionApply.getObjId(),projectExceptionApply.getProject().getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return projectExceptionApply;
	}
	
	/** 
	 * Description : 根据项目Id获取待审核的暂停项目 
	 * Create Date: 2010-10-11下午07:15:27 by yangx  
	 * Modified Date: 2010-10-11下午07:15:27 by yangx
	 * @param   projectId：项目Id
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply getProjectExceptionApplyByProjectId(String projectId){
		QueryObject<ProjectExceptionApply> queryObject = new QueryObjectBase<ProjectExceptionApply>();
		queryObject.setEntityClass(ProjectExceptionApply.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		List<ProjectExceptionApply> list = projectExceptionApplyManager.findByQuery(queryObject);
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
	/** 
	 * Description :  审核异常申请
	 * Create Date: 2010-10-11下午08:19:35 by yangx  Modified Date: 2010-10-11下午08:19:35 by yangx
	 * @param   projectExceptionApplyId：项目异常Id,auditStatus:审核状态
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public Project saveAuditStatusExceptionApply(String projectExceptionApplyId,String auditStatus,String opinion) throws Exception{
		ProjectExceptionApply projectExceptionApply= projectExceptionApplyManager.get(projectExceptionApplyId);
		projectExceptionApply.setAuditStatus(auditStatus);
		projectExceptionApply = projectExceptionApplyManager.save(projectExceptionApply);
		Project newProject = new Project();
		Project project = projectExceptionApply.getProject();
		if (projectExceptionApply!=null&&AuditStatusEnum.AUDIT_PASS.equals(projectExceptionApply.getAuditStatus())) {//判断是否为审核通过
			if (ProjExceptionApplyEnum.REPURVHASE.equals(projectExceptionApply.getAdviceProcway())) {//重新招标
				
			}else if (ProjExceptionApplyEnum.CHANGEEBUYMETHOD.equals(projectExceptionApply.getAdviceProcway())) {//变更采购方式
				newProject = this.saveNewProjectByIsCreateProject(project,projectExceptionApply.getNewEbuyMethod(), projectExceptionApply.getReCreateProj());
			}else{//终止项目
				newProject = this.saveNewProjectByIsCreateProject(project,project.getEbuyMethod(), projectExceptionApply.getReCreateProj());
				project.setProjImplStatus("02");
				projectManager.save(project);
			}
			completedWorkTaskManager.createCompTaskByPassivity("审核异常申请","08",opinion,projectExceptionApply.getObjId(),project.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
		}else{//审核不通过
			completedWorkTaskManager.createCompTaskByPassivity("审核异常申请","08",opinion,projectExceptionApply.getObjId(),project.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		}
		
		return newProject;
	}
	
	/** 
	 * Description :  获取异常申请列表
	 * Create Date: 2010-10-12上午10:55:07 by yangx  Modified Date: 2010-10-12上午10:55:07 by yangx
	 * @param   queryObject：封装条件,page：分页对象
	 * @return  Page<ProjectExceptionApply>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectExceptionApply> getProjectExceptionList(Page page,QueryObject queryObject){
		logger.debug("\nes=ProjectExceptionApplyServiceImpl||getProjectExceptionList\n");
		return projectExceptionApplyDaoHibernate.getProjectExceptionList(page,queryObject);
	}
	
	/** 
	 * Description :  根据主键获取异常申请信息
	 * Create Date: 2010-10-12下午02:57:19 by yangx  Modified Date: 2010-10-12下午02:57:19 by yangx
	 * @param   projectExceptionApplyId：项目异常Id
	 * @return  ProjectExceptionApply
	 * @Exception   
	 */
	public ProjectExceptionApply getProjectExceptionApplyByObjId(String projectExceptionApplyId){
		return projectExceptionApplyManager.get(projectExceptionApplyId);
	}
	
	/** 
	 * Description :  根据是否重新立项来重新创建项目或是改变项目采购方式
	 * Create Date: 2011-5-23下午08:34:37 by yangx  
	 * Modified Date: 2011-5-23下午08:34:37 by yangx
	 * @param   project：原有项目,newEbuyMethod：新的采购方式,reCreateProj：是否重新立项
	 * @return  
	 * @Exception   
	 * @Modifier zhouzhanghe 
	 * @Modified Date at 2011-10-08 16:25(增加生成工作计划)
	 */
	private Project saveNewProjectByIsCreateProject(Project project,String newEbuyMethod,String reCreateProj)throws Exception{
		Project newProject_ = null;
		if (ProjExceptionApplyEnum.ISRECREATEPROJECT.equals(reCreateProj)) {//重新立项
			Project newProject = new Project();
			org.springframework.beans.BeanUtils.copyProperties(project, newProject);
			newProject.setEbuyMethod(newEbuyMethod);
			newProject.setProjCode("ex"+project.getProjCode());
			newProject.setObjId(null);
			newProject.setSubProject(null);
			newProject.setProjImplStatus(ProjImplStatusEnum.NORMAL);//正常
			project.setProjImplStatus(ProjImplStatusEnum.SUSPEND);//旧项目暂停
			newProject_ = projectManager.save(newProject);//创建新项目
			this.saveProjectMTaskPlanByOldProject(project,newProject_);//保存项目-条目中间表
			this.saveProjectRuleByNewProject(project, newProject_);//保存项目规则表
			projectPlanService.createProjectPlan(newProject.getObjId());//创建工作计划
		}else{
			project.setEbuyMethod(newEbuyMethod);
			project.setProjImplStatus(ProjImplStatusEnum.NORMAL);//正常
			projectManager.save(project);
		}
		return newProject_;
	}
	
	/** 
	 * Description :  根据原有项目保存新项目-条目中间对象
	 * Create Date: 2011-5-24下午06:12:45 by yangx  
	 * Modified Date: 2011-5-24下午06:12:45 by yangx
	 * @param   project:被变更的项目,newProject:变更后的项目
	 * @return  ProjectMTaskPlan
	 * @Exception   
	 */
	private ProjectMTaskPlan saveProjectMTaskPlanByOldProject(Project project,Project newProject){
		ProjectMTaskPlan newProjectMTaskPlan = null;
		QueryObject<ProjectMTaskPlan> queryObject = new QueryObjectBase<ProjectMTaskPlan>();
		queryObject.setEntityClass(ProjectMTaskPlan.class);
		queryObject.getQueryParam().and(new QueryParam("tproject.objId",QueryParam.OPERATOR_EQ,project.getObjId()));
		List<ProjectMTaskPlan> list = projectMTaskPlanManager.findByQuery(queryObject);
		if (list!=null&&list.size()>0) {
			for (ProjectMTaskPlan oldM:list) {
				newProjectMTaskPlan = new ProjectMTaskPlan();
				org.springframework.beans.BeanUtils.copyProperties(oldM,newProjectMTaskPlan);//将原项目的工作计划复制到新的工作计划表
				newProjectMTaskPlan.setObjId(null);
				newProjectMTaskPlan.setTproject(newProject);
				projectMTaskPlanManager.save(newProjectMTaskPlan);
			}
		}
		return newProjectMTaskPlan;
	}
	
	/** 
	 * Description :  根据项目保存项目规则
	 * Create Date: 2011-5-25下午03:20:50 by yangx  
	 * Modified Date: 2011-5-25下午03:20:50 by yangx
	 * @param   oldProject:被变更的项目,newProject:变更后的项目
	 * @return  ProjectRule
	 * @Exception   
	 */
	private ProjectRule saveProjectRuleByNewProject(Project oldProject,Project newProject){
		ProjectRule rule = projectManager.getProjectRuleByProjectId(oldProject.getObjId());
		ProjectRule newRule = new ProjectRule();
		BeanUtils.copyProperties(rule,newRule);
		newRule.setProject(newProject);
		newRule.setObjId(null);
		return (ProjectRule)projectManager.save(newRule,ProjectRule.class);
	}
}
