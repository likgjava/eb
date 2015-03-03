package com.gpcsoft.epp.workgroup.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.manager.WorkGroupManager;
import com.gpcsoft.epp.workgroup.manager.WorkgMemberManager;
import com.gpcsoft.epp.workgroup.service.WorkGroupService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class WorkGroupServiceImpl extends BaseGenericServiceImpl<WorkGroup> implements WorkGroupService {

	@Autowired(required=true) @Qualifier("workgMemberManagerImpl")
	private WorkgMemberManager workgMemberManager;
	
	@Autowired(required=true) @Qualifier("workGroupManagerImpl")
	private WorkGroupManager workGroupManager;

	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	/** 
	 * Description :  通过项目主键和小组类型得到工作小组对象
	 * Create Date: 2010-11-2下午02:20:38 by yangx  Modified Date: 2010-11-2下午02:20:38 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String workType) {
		logger.debug("\nWorkGroupServiceImpl||getWorkGroupByProjectIdAndType\n");
		return workGroupManager.getWorkGroupByProjectIdAndType(projectId, workType);
	}


	/** 
	 * Description :  根据项目Id、小组类型保存工作小组对象
	 * Create Date: 2010-11-2下午02:07:09 by yangx  Modified Date: 2010-11-2下午02:07:09 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup saveWorkGroupByProjectIdAndType(String projectId,String workType) {
		logger.debug("\nWorkGroupServiceImpl||saveWorkGroupByProjectIdAndType\n");
		return workGroupManager.saveWorkGroupByProjectIdAndType(projectId, workType);
	}

	/** 
	 * Description :  按指定组别获取项目下所有标段的某组成员
	 * Create Date: 2010-6-25下午01:39:48 by wangcl  Modified Date: 2010-6-25下午01:39:48 by wangcl
	 * @param   projectId：项目Id,groupType：工作组类型
	 * @return  List<WorkGroup>
	 * @Exception   
	 */
	public List<WorkGroup> saveOrgetAllGroupMenber(String projectId, String groupType) {
		logger.debug("\n WorkGroupServiceImpl||saveOrgetAllGroupMenber\n");
		List<WorkGroup> list = new ArrayList<WorkGroup>();
		Project project = (Project)this.get(projectId, Project.class);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
		   if(projectRule.getIsDividePack()==true){
				for (Iterator iterator = project.getSubProject().iterator(); iterator.hasNext();) {
					Project child = (Project) iterator.next();
					WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(child.getObjId(), groupType);
					if (g==null) {
						g = workGroupManager.saveWorkGroupByProjectIdAndType(child.getObjId(), groupType);
					}
					list.add(g);
				} 
		   } else {
			   WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(project.getObjId(), groupType);
			   if (g==null) {
					g = workGroupManager.saveWorkGroupByProjectIdAndType(projectId, groupType);
				}
				list.add(g);
		   }
		return list;
	}

	/** 
	 * Description :  获取项目下所有标段的论证组成员
	 * Create Date: 2010-6-25下午01:39:48 by wangcl  Modified Date: 2010-6-25下午01:39:48 by wangcl
	 * @param   projectId：项目Id
	 * @return  List<WorkGroup>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<WorkGroup> saveOrGetProofGroupMenber(String projectId) {
		logger.debug("\n WorkGroupServiceImpl||saveOrGetProofGroupMenber\n");
		List<WorkGroup> list = new ArrayList<WorkGroup>();
		Project project = (Project) this.get(projectId, Project.class);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
		if (projectRule.getIsDividePack()) {
			for (Iterator iterator = project.getSubProject().iterator(); iterator.hasNext();) {
				Project child = (Project) iterator.next();
				WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(child.getObjId(),WorkGroupTypeEnum.PROOF);
				if (g==null) {
					g = workGroupManager.saveWorkGroupByProjectIdAndType(child.getObjId(), WorkGroupTypeEnum.PROOF);
					g.setProject(child);
				}
				list.add(g);
			}
		} else {
			WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(project.getObjId(), WorkGroupTypeEnum.PROOF);
			if (g==null) {
				g = workGroupManager.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.PROOF);
				g.setProject(project);
			}
			list.add(g);
		}
		return list;
	}
	
	/**
	 * 
	 * Description :保存或更新工作小组对象  
	 * Create Date: 2010-5-23下午04:42:30 by liuke  Modified Date: 2010-5-23下午04:42:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOrUpdateWorkGroup(WorkGroup workGroup) {
		workGroupManager.saveOrUpdateWorkGroup(workGroup);
	}


	
	/**
	 * FuncName: saveOrGetProofGroupMenber
	 * Description : 获取项目下所有标段的评审小组成员
	 * @param 
	 * @param projectId
	 * @return List<WorkGroup>
	 * @author: liuke
	 * @Create Date:2011-3-3  下午06:57:02
	 * @Modifier: liuke
	 * @Modified Date:2011-3-3  下午06:57:02
	 */
	public List<WorkGroup> saveOrGetJudgeGroupMenber(String projectId) {
		logger.debug("\n WorkGroupServiceImpl||saveOrGetJudgeGroupMenber\n");
		List<WorkGroup> list = new ArrayList<WorkGroup>();
		Project project = (Project) this.get(projectId, Project.class);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
		if (projectRule.getIsDividePack()) {
			for (Iterator iterator = project.getSubProject().iterator(); iterator.hasNext();) {
				Project child = (Project) iterator.next();
				WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(child.getObjId(),WorkGroupTypeEnum.APPRAISAL);
				if (g==null) {
					g = workGroupManager.saveWorkGroupByProjectIdAndType(child.getObjId(), WorkGroupTypeEnum.APPRAISAL);
					g.setProject(child);
				}
				list.add(g);
			}
		} else {
			WorkGroup g = workGroupManager.getWorkGroupByProjectIdAndType(project.getObjId(), WorkGroupTypeEnum.APPRAISAL);
			if (g==null) {
				g = workGroupManager.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.APPRAISAL);
				g.setProject(project);
			}
			list.add(g);
		}
		return list;
	}
}
