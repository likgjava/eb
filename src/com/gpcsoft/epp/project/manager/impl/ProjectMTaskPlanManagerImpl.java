package com.gpcsoft.epp.project.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.dao.ProjectMTaskPlanDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;

@Repository
public class ProjectMTaskPlanManagerImpl extends BaseManagerImpl<ProjectMTaskPlan> implements ProjectMTaskPlanManager {

	@Autowired(required=true)  @Qualifier("projectMTaskPlanDaoHibernate")
	private ProjectMTaskPlanDao projectMTaskPlanDaoHibernate;
    
	/**
	 * FuncName:getProjectListByTaskPlan
	 * Description :  得到大宗未审核项目列表
	 * @param page:分页对象,auditStatus:审核条件,useStatus:临时状态 
	 * @return  Page
	 * @author liuke
	 * @Create Date:  
	 */
	public Page getProjectListByTaskPlan(Page page,String auditStatus,String useStatus) {
		Page pages = projectMTaskPlanDaoHibernate.getProjectListByTaskPlan(page, auditStatus, useStatus);
		return pages;
	}
	
	/**
	 * FuncName:getTaskPlanByProjectId
	 * Description:通过项目主键得到申报书列表
	 * @param   projectId:项目主键
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-7-5上午11:40:55 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-5上午11:40:55 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List getTaskPlanByProjectId(String projectId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct tpms.taskPlan from TaskPlanMSub tpms where tpms.taskPlanSub.objId in (" );
		hql.append("select pmtp.taskPlanSub from ProjectMTaskPlan pmtp where pmtp.tproject.objId = ?)");//pmtp.taskPlanSub.objId改为pmtp.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe	
		List list = projectMTaskPlanDaoHibernate.findbyHql(hql.toString(), projectId);
		return list;
	}

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
	public void removeProjectMTaskPlanByProjectId(String projectId) {
		projectMTaskPlanDaoHibernate.removeProjectMTaskPlanByProjectId(projectId);
	}
  
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
	public void reductionTaskPlanSubByProjectId(String projectId) {
		projectMTaskPlanDaoHibernate.reductionTaskPlanSubByProjectId(projectId);
	}

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
	public void removeProjectMTaskPlanByBytaskPlanIdAndProjectId(String taskPlanId, String projectId) {
		projectMTaskPlanDaoHibernate.removeProjectMTaskPlanByBytaskPlanIdAndProjectId(taskPlanId, projectId);
	}

	/**
	 * FuncName:getProjectByProjectMTaskPlanAndTaskPlanId
	 * Description:根据申报书主键与中间表得到项目对象  
	 * @param   taskPlanId:申报书主键
	 * @return Project
	 * @author liuke  
	 * @Create Date: 2010-7-8下午05:47:33 
	 * @Modifier   liuke 
	 * @Modified Date: 2010-7-8下午05:47:33 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public Project getProjectByProjectMTaskPlanAndTaskPlanId(String taskPlanId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct pmtp.tproject from ProjectMTaskPlan pmtp where pmtp.taskPlanSub in  ");//pmtp.taskPlanSub.objId改为pmtp.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		hql.append("(select tpms.taskPlanSub.objId from TaskPlanMSub tpms where tpms.taskPlan.objId = '"+taskPlanId+"' )");
		List list = projectMTaskPlanDaoHibernate.findbyHql(hql.toString());
		if(list.size()>0){
			return (Project) list.get(0);
		}
		else{
			return null;
		}
	}

	/**
	 * FuncName:getAllTaskPlanSubByProject
	 * Description:通过项目查找申报书条目
	 * @param   projectId:项目主键
	 * @return List
	 * @author liuke  
	 * @Create Date: 2010-7-10下午01:39:10 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-10下午01:39:10 
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List getAllTaskPlanSubByProject(String projectId) {
		/*
		 * Modified Date: 2011-6-23 14:10 by zhouzhanghe
		 * old: List list = 	projectMTaskPlanDaoHibernate.findbyHql("select distinct pmtp.taskPlanSub from ProjectMTaskPlan pmtp where pmtp.tproject.objId = ?", projectId);
		 */
	  List list = 	projectMTaskPlanDaoHibernate.findbyHql("select tps from TaskPlanSub tps where tps.objId in(select pmtp.taskPlanSub from ProjectMTaskPlan pmtp where pmtp.tproject.objId = ?)", projectId);
	  return list;
	}

	/**
	 * FuncName:getProjectMTaskPlanByQueryObject
	 * Description :通过QueryObject对象得到中间表对象
	 * @param   queryObject:查询条件组装的对象
	 * @return   List<ProjectMTaskPlan>
	 * @author  liuke
	 * @Create Date: 2010-7-28上午10:04:38
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-28上午10:04:38 
	 */
	public List<ProjectMTaskPlan> getProjectMTaskPlanByQueryObject(QueryObject<ProjectMTaskPlan> queryObject) {
		return projectMTaskPlanDaoHibernate.getProjectMTaskPlanByQueryObject(queryObject);
	}
 
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
	public List<ProjectMTaskPlan> getProjectMTaskPlanByProjectId(String projectId) {
		List<ProjectMTaskPlan> list = projectMTaskPlanDaoHibernate.findbyHql("from ProjectMTaskPlan t where t.tproject.objId = ?", projectId);
		return list;
	}
}
