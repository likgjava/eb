package com.gpcsoft.epp.project.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;

public interface ProjectMTaskPlanManager extends BaseManager<ProjectMTaskPlan> {

	/**
	 * FuncName:getTaskPlanByProjectId
	 * Description:通过项目主键得到申报书列表
	 * @param   projectId:项目主键
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-7-5上午11:40:55 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-5上午11:40:55 
	 */
	public List getTaskPlanByProjectId(String projectId);
	
	/**
	 * FuncName:getProjectListByTaskPlan
	 * Description :  得到大宗未审核项目列表
	 * @param page:分页对象,auditStatus:审核条件,useStatus:临时状态 
	 * @return  Page
	 * @author liuke
	 * @Create Date:  
	 */
	public Page getProjectListByTaskPlan(Page page, String auditStatus,String useStatus);
			
	/**
	 * FuncName:removeProjectMTaskPlanByProjectId
	 * Description:根据项目主键删除中间表数据  
	 * @param  projectId:项目主键 
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-7-7下午01:08:31 
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-7下午01:08:31 
	 */
	public void removeProjectMTaskPlanByProjectId(String projectId);
	
	/**
	 * FuncName:reductionTaskPlanSubByProjectId
	 * Description:根据主键设置申报书大宗状态为null  
	 * @param   projectId:项目主键
	 * @return void
	 * @author 	liuke  
	 * @Create Date: 2010-7-7下午01:26:14
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-7下午01:26:14  
	 */
	public void reductionTaskPlanSubByProjectId(String projectId);
	
	/**
	 * FuncName:removeProjectMTaskPlanByBytaskPlanIdAndProjectId
	 * Description :根据项目主键和申报书主键删除中间表对象
	 * @param   taskPlanId:申报书主键,projectId:项目主键
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier liuke  
	 * @Modified Date: 2010-7-7下午01:26:14 
	 */
	public void removeProjectMTaskPlanByBytaskPlanIdAndProjectId(String taskPlanId,String projectId);
	
	/**
	 * FuncName:getProjectByProjectMTaskPlanAndTaskPlanId
	 * Description:根据申报书主键与中间表得到项目对象  
	 * @param   taskPlanId:申报书主键
	 * @return Project
	 * @author liuke  
	 * @Create Date: 2010-7-8下午05:47:33 
	 * @Modifier   liuke 
	 * @Modified Date: 2010-7-8下午05:47:33 
	 */
	public Project getProjectByProjectMTaskPlanAndTaskPlanId(String taskPlanId);
	
	/**
	 * FuncName:getAllTaskPlanSubByProject
	 * Description:通过项目查找申报书条目
	 * @param   projectId:项目主键
	 * @return List
	 * @author liuke  
	 * @Create Date: 2010-7-10下午01:39:10 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-10下午01:39:10 
	 */
	public List getAllTaskPlanSubByProject(String projectId);
	
	/**
	 * FuncName:getProjectMTaskPlanByQueryObject
	 * Description :通过QueryObject对象得到中间表对象
	 * @param   queryObject:查询条件组装的对象
	 * @return   List<ProjectMTaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-28上午10:04:38
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-28上午10:04:38 
	 */
	public List<ProjectMTaskPlan> getProjectMTaskPlanByQueryObject(QueryObject<ProjectMTaskPlan> queryObject);
	
	/**
	 * FuncName:getProjectMTaskPlanByProjectId
	 * Description:通过项目得到中间表对象
	 * @param   projectId:项目主键
	 * @return List<ProjectMTaskPlan>
	 * @author liuke  
	 * @Create Date: 2010-7-28上午10:04:38 
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-28上午10:04:38 
	 */
	public List<ProjectMTaskPlan> getProjectMTaskPlanByProjectId(String projectId);
}
