package com.gpcsoft.epp.project.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
public interface ProjectMTaskPlanService extends BaseGenericService<ProjectMTaskPlan> {

	/**
	 * FuncName:getProjectMTaskPlanByProjectId
	 * Description : 通过项目获得申报书项目中间表对象
	 * @param   projectId:项目主键
	 * @return  List<ProjectMTaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-28上午09:55:38 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-28上午09:55:38  
	 */
	public List<ProjectMTaskPlan> getProjectMTaskPlanByProjectId(String projectId);
	
	/** 
	 * FuncName:updateProjectByTaskPlanSubs
	 * Description :根据选中的申报书条目修改项目
	 * @param   project:项目,taskPlanSubIds:申报书条目主键(多个)
	 * @return void
	 * @author yangx 
	 * @Create Date: 2010-9-2下午04:45:05 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-2下午04:45:05    
	 */
	public void updateProjectByTaskPlanSubs(Project project, String taskPlanSubIds);
	
	/**
	 * FuncName:getAllTaskPlanSubByProject
	 * Description : 通过项目查找申报书条目
	 * @param   projectId:项目主键
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-7-10下午01:39:10 
	 * @Modifier liuke
	 * @Modified Date: 2010-7-10下午01:39:10 
	 */
	public List getAllTaskPlanSubByProject(String projectId);

	/**
	 * FuncName: getProjectMTaskPlan
	 * Description :  根据项目逐渐获得招标项目关联申报书条目表
	 * @param 
	 * @param projectId
	 * @return ProjectMTaskPlan
	 * @author: shenjz
	 * @Create Date:2011-3-29  下午05:03:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-29  下午05:03:17
	 */
	public ProjectMTaskPlan getProjectMTaskPlan(String projectId);
}
