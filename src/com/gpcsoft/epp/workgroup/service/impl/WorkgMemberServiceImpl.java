package com.gpcsoft.epp.workgroup.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.workgroup.dao.WorkgMemberDao;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.manager.WorkGroupManager;
import com.gpcsoft.epp.workgroup.manager.WorkgMemberManager;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

@Service 
public class WorkgMemberServiceImpl extends BaseGenericServiceImpl<WorkgMember> implements WorkgMemberService {

	@Autowired(required=true) @Qualifier("workgMemberManagerImpl")
	protected WorkgMemberManager workgMemberManager;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	protected RoleManager RoleManager;

	@Autowired(required=true) @Qualifier("projectManagerImpl")
	protected ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("workGroupManagerImpl")
	protected WorkGroupManager workGroupManager;
	
	@Autowired(required=true)  @Qualifier("workgMemberDaoHibernate")
	protected WorkgMemberDao workgMemberDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	protected ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanManagerImpl")
	protected ProjectMTaskPlanManager projectMTaskPlanManager;
	
	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	protected UserApiManager userApiManager;
	
	public void saveMemberByEmp(String[] empIds, String workGroupId,String projectId ) {
		Project project = projectService.get(projectId);
		ProjectMTaskPlan projectMTaskPlan =  projectMTaskPlanManager.getProjectMTaskPlanByProjectId(projectId).get(0);
		WorkGroup g = new WorkGroup();
		g.setObjId(workGroupId);
		for (int i = 0; i < empIds.length; i++) {
			Employee emp = (Employee)this.get(empIds[i], Employee.class);
			WorkgMember member = new WorkgMember();
			member.setCreateTime(new Date());
			member.setCreateUser(AuthenticationHelper.getCurrentUser());
			member.setWorkgmName(emp.getName());
			if(emp.getCompany().getObjId().equals(project.getAgencies().getCompany().getObjId()))
			{
				member.setWorkgmType("01");
			}
			if(project.getMonitor()==null){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.NO_SETUP_PROJECT_MONITOR_PERSON));
			}else if(emp.getCompany().getObjId().equals(project.getMonitor().getCompany().getObjId()))
			{
				member.setWorkgmType("02");
			}
			if(emp.getCompany().getObjId().equals(projectMTaskPlan.getBuyMainBody().getCompany().getObjId()))
			{
				member.setWorkgmType("03");
			}
			User user = emp.getUsers().iterator().next();
			if(workgMemberManager.memberIsExisted(user.getObjId(), workGroupId)){
				throw new EsException(messageSource.getMessage(EsExceptionEnum.WORKGROUP_MEMBER_ISEXISTED));
			}
			member.setUser(user);
			member.setWorkGroup(g);
			this.save(member);
		}
	}


	/* 
	 * 解除eager引起的关联关系 否则抛出ObjectDeletedException
	 */
	@Override
	public void remove(String objId, Class clazz) {
		workgMemberManager.remove(objId);
	}

	/**
	 * 
	 * Description : 根据当前用户ID获得工作小组列表
	 * Create Date: 2010-7-29上午10:56:21 by liuke  Modified Date: 2010-7-29上午10:56:21 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Project> getProjectListByUserId(String userId) {
		logger.debug("\n=WorkgMemberServiceImpl||getProjectListByUserId\n");
		List<Project> list = new ArrayList<Project>();
		List<String> ProjectIdList = workGroupManager.getProjectListByUserId(userId);//根据用户得到用户小组列表
		for(int i=0;i<ProjectIdList.size();i++){
			Project project = projectManager.get(ProjectIdList.get(i));
			list.add(project);
		}
		return list;
	}
	
	/** 
	 * Description :  删除开标小组成员
	 * Create Date: 2010-9-7下午04:15:56 by wangcl  Modified Date: 2010-9-7下午04:15:56 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember removeBidMember(String objId) {
		logger.debug("\n=WorkgMemberServiceImpl||removeBidMember\n");
		return workgMemberManager.remove(objId);
	}
	
	/** 
	 * Description :  删除论证小组成员
	 * Create Date: 2010-9-7下午04:15:59 by wangcl  Modified Date: 2010-9-7下午04:15:59 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember removeProofMember(String objId) {
		logger.debug("\n=WorkgMemberServiceImpl||removeProofMember\n");
		return workgMemberManager.remove(objId);
	}
	
	/**
	 * 
	 * Description : 保存开标小组成员
	 * Create Date: 2010-9-14上午10:45:45 by shenjz  Modified Date: 2010-9-14上午10:45:45 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Project saveBidMemberByEmp(String[] empIds, String workGroupId,String projectId) {
		logger.debug("\n=WorkgMemberServiceImpl||saveBidMemberByEmp\n");
		this.saveMemberByEmp(empIds, workGroupId, projectId);
		Project p = (Project)this.get(projectId, Project.class);
		p.setUser(AuthenticationHelper.getCurrentUser());
		p.setParentBizId(projectId);
		return p;
		
	}
	
	/**
	 * 
	 * Description : 保存论证小组成员
	 * Create Date: 2010-9-14上午10:45:45 by shenjz  Modified Date: 2010-9-14上午10:45:45 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Project saveProofMemberByEmp(String[] empIds, String workGroupId,
			String projectId) {
		logger.debug("\n=WorkgMemberServiceImpl||saveProofMemberByEmp\n");
		this.saveMemberByEmp(empIds, workGroupId, projectId);
		Project p = (Project)this.get(projectId, Project.class);
		p.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(projectId);
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		p.setParentBizId(parentBizId);
		return p;
	}
	/** 
	 * Description :  根据项目Id、工作组类型、成员类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectIdAndGroupType(String projectId,String groupType,String workgmType){
		logger.debug("\n=WorkgMemberServiceImpl||getWorkgMemberByProjectIdAndGroupType\n");
		return workgMemberDaoHibernate.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,workgmType);
	}
	
	/** 
	 * Description :  根据项目Id、工作组类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectId(String projectId,String groupType){
		return workgMemberDaoHibernate.getWorkgMemberByProjectId(projectId, groupType);
	}
	/** 
	 * Description :  保存开标人
	 * Create Date: 2010-10-22下午01:43:29 by yangx  Modified Date: 2010-10-22下午01:43:29 by yangx
	 * @param   workgMember:成员,projectId：项目Id,groupType：工作组类型,workGroupId：工作组Id,empId：员工Id
	 * @return  WorkgMember：工作组成员
	 * @Exception   
	 */
	public WorkgMember saveWorkgMember(WorkgMember workgMember,String projectId,String groupType,String workGroupId,String empId){
		logger.debug("\n WorkgMemberServiceImpl||saveWorkgMember\n");
		User user = userApiManager.getUserByEmpId(empId);
		Project subProject = new Project();
		subProject.setObjId(projectId);
		WorkGroup workGroup = new WorkGroup();
		workGroup.setObjId(workGroupId);
		workgMember.setWorkGroup(workGroup);
		workgMember.setSubProject(subProject);
		workgMember.setUser(user);
		workgMemberManager.save(workgMember);
		Project project = (Project)workgMemberManager.get(projectId,Project.class);
		if (project!=null&&project.getParentId()!=null&&!"".equals(project.getParentId())) {
			workgMember.setParentBizId(project.getParentId());
		} else {
			workgMember.setParentBizId(project.getObjId());
		}
		workgMember.setUser(AuthenticationHelper.getCurrentUser());
		return workgMember;
	}
	
	/** 
	 * Description :  根据主键获取开标人
	 * Create Date: 2010-10-22下午07:15:16 by yangx  Modified Date: 2010-10-22下午07:15:16 by yangx
	 * @param   workgMemberId：开标人Id
	 * @return   WorkgMember：工作组成员
	 * @Exception   
	 */
	public WorkgMember getWorkgMemberByObjId(String workgMemberId){
		logger.debug("\n WorkgMemberServiceImpl||getWorkgMemberByObjId\n");
		return workgMemberManager.get(workgMemberId);
	}
	
	/** 
	 * Description :  修改开标人
	 * Create Date: 2010-10-22下午07:29:44 by yangx  Modified Date: 2010-10-22下午07:29:44 by yangx
	 * @param   workgMember:开标人对象;empId：员工Id
	 * @return  WorkgMember:开标人对象
	 * @Exception   
	 */
	public WorkgMember updateWorkgMember(WorkgMember workgMember,String empId){
		logger.debug("\n WorkgMemberServiceImpl||updateWorkgMember\n");
		User user = userApiManager.getUserByEmpId(empId);
		workgMember.setUser(user);
		return workgMemberManager.save(workgMember);
	}


	
	/**
	 * FuncName: saveJudgeMember
	 * Description :  保存评标人
	 * @param 
	 * @param workgMember
	 * @param projectId
	 * @param groupType
	 * @param workGroupId
	 * @return WorkgMember
	 * @author: liuke
	 * @Create Date:2011-3-4  上午10:19:51
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午10:19:51
	 */
	public WorkgMember saveJudgeMember(WorkgMember workgMember,
			String projectId, String groupType, String workGroupId) {
		logger.debug("\n WorkgMemberServiceImpl||saveJudgeMember\n");
		Project subProject = new Project();
		subProject.setObjId(projectId);
		WorkGroup workGroup = new WorkGroup();
		workGroup.setObjId(workGroupId);
		workgMember.setWorkGroup(workGroup);
		workgMember.setSubProject(subProject);
		workgMemberManager.save(workgMember);
		Project project = (Project)workgMemberManager.get(projectId,Project.class);
		if (project!=null&&project.getParentId()!=null&&!"".equals(project.getParentId())) {
			workgMember.setParentBizId(project.getParentId());
		} else {
			workgMember.setParentBizId(project.getObjId());
		}
		workgMember.setUser(AuthenticationHelper.getCurrentUser());
		return workgMember;
	}
	
    /**
     * FuncName: saveWorkgMemberDesion
     * Description:完成组建开标小组节点的方法
     * @param 
     * @param request
     * @return
     * @throws Exception ModelAndView
     * @author: shenjz
     * @Create Date:2011-4-1  上午10:42:39
     * @Modifier: shenjz
     * @Modified Date:2011-4-1  上午10:42:39
     */
	public WorkgMember saveWorkgMemberDesion(String projectId){
		WorkgMember workgMember  = new WorkgMember();
    	workgMember.setParentBizId(projectId);
    	workgMember.setUser(AuthenticationHelper.getCurrentUser());
    	return workgMember;
	}


	/** 
	 * Description :  根据工作小组Id查询小组成员
	 * @param   workGroupId：工作小组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-26 15:04
	 */
	public List<WorkgMember> getWorkgMemberByWorkGroupId(String workGroupId){
		return workgMemberDaoHibernate.getWorkgMemberByWorkGroupId(workGroupId);
	}
	
	/** 
	 * FuncName : saveWorkgMember
	 * Description :  保存开标小组
	 * Create Date: 2011-11-10下午05:56:40 by yangx  
	 * Modified Date: 2011-11-10下午05:56:40 by yangx
	 * @param   workgMember
	 * @return  WorkgMember
	 */
	public WorkgMember saveLeaderWorkgMember(WorkgMember workgMember,String projectId){
		workgMember = workgMemberDaoHibernate.save(workgMember);
		workgMember.setParentBizId(projectId);
    	workgMember.setUser(AuthenticationHelper.getCurrentUser());
    	return workgMember;
	}
	
	/**
	 * FuncName: finishLeanderWorkgMember
	 * Description :  完成领导小组创建
	 * @param 
	 * @param projectId
	 * @return WorkgMember
	 * @author: zhangsh
	 * @Create Date:2011-11-16  上午10:01:00
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-16  上午10:01:00
	*/	
	public WorkgMember finishLeanderWorkgMember(String projectId){
		WorkgMember workgMember = new WorkgMember();
		workgMember.setParentBizId(projectId);
    	workgMember.setUser(AuthenticationHelper.getCurrentUser());
    	return workgMember;
	}


	/**
	 * FuncName: saveWorkgMemberDesion
	 * Description:完成组建评审小组节点的方法
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @Created at 2011-5-17 17:02 by zhouzhanghe
	 */
	public WorkgMember saveScoreWorkgMember(String projectId) {
		WorkgMember workgMember  = new WorkgMember();
		workgMember.setParentBizId(projectId);
		workgMember.setUser(AuthenticationHelper.getCurrentUser());
		return workgMember;
	}


	/**
	 * 
	 * Description :根据包组和成员类型获得专家列表  
	 * Create Date: 2010-9-14下午02:26:35 by caojz  Modified Date: 2010-9-14下午02:26:35 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberByProjectAndMemberType(String subProjectId,String isLeader){
		return workgMemberDaoHibernate.getWorkgMemberByProjectAndMemberType(subProjectId, isLeader);
	}


	/**
	 * 
	 * Description :根据包组获得专家列表  
	 * Create Date: 2010-8-11下午02:26:35 by liuke  Modified Date: 2010-8-11下午02:26:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListBysubProjectId(
			String subProjectId) {
		return workgMemberManager.getWorkgMemberListBysubProjectId(subProjectId);
	}


	/**
	 * 
	 * Description :根据项目/包组Id和工作小组类型获得成员列表 
	 * Create Date: 2011-9-2上午 11:40:30 by caojz  Modified Date: 2011-9-2上午 11:40:30 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListByWorkgroupId(String projectId,String groupType){
		return this.workgMemberDaoHibernate.getWorkgMemberListByWorkgroupId(projectId, groupType);
	}


	/**
	 * FuncName: saveLeaderGroupMember
	 * Description: 保存领导小组成员
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @Created at 2011-10-19 18:04 by zhouzhanghe
	 */
	public WorkgMember saveLeaderGroupMember(WorkgMember workgMember) {
		save(workgMember);
	
		workgMember.setParentBizId(workgMember.getSubProject().getObjId());
		workgMember.setUser(AuthenticationHelper.getCurrentUser());
		return workgMember;
	}
}
