package com.gpcsoft.epp.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.consign.manager.ConsignTaskPlanManager;
import com.gpcsoft.epp.project.dao.ProjectMTaskPlanDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;

@Service 
public class ProjectMTaskPlanServiceImpl extends BaseGenericServiceImpl<ProjectMTaskPlan> implements ProjectMTaskPlanService {

	@Autowired(required=true) @Qualifier("projectMTaskPlanManagerImpl")
	private ProjectMTaskPlanManager projectMTaskPlanManager;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanManagerImpl")
	private ConsignTaskPlanManager consignTaskPlanManager;
	
	@Autowired(required=true)  @Qualifier("projectMTaskPlanDaoHibernate")
	private ProjectMTaskPlanDao projectMTaskPlanDao;
    
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
	public List<ProjectMTaskPlan> getProjectMTaskPlanByProjectId(String projectId) {
		logger.debug("\nes ProjectMTaskPlanServiceImpl||getProjectMTaskPlanByProjectId\n");
	    QueryObject<ProjectMTaskPlan> queryObject = new QueryObjectBase<ProjectMTaskPlan>();
	    queryObject.setEntityClass(ProjectMTaskPlan.class);
	    queryObject.getQueryParam().and(new QueryParam("tproject.objId",QueryParam.OPERATOR_EQ,projectId));
	    List<ProjectMTaskPlan> list = projectMTaskPlanManager.getProjectMTaskPlanByQueryObject(queryObject);
		return list;
	}
	
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
	public void updateProjectByTaskPlanSubs(Project project, String taskPlanSubIds){
		logger.debug("\nes ProjectMTaskPlanServiceImpl||updateProjectByTaskPlanSubs\n");
		String purCategoryIds = "";//品目Ids
		String purCategoryName = "";//品目名称
		this.save(project,Project.class);
		projectMTaskPlanManager.removeProjectMTaskPlanByProjectId(project.getObjId());//删除以前的项目与条目信息
		//保存招标项目与申报书明细的中间表
		QueryObject<ConsignTaskPlan> queryObject = new QueryObjectBase<ConsignTaskPlan>();
		queryObject.setEntityClass(ConsignTaskPlan.class);
		queryObject.getQueryParam().and(new QueryParam("taskPlanSubs",QueryParam.OPERATOR_EQ,taskPlanSubIds));
		List<ConsignTaskPlan> list = consignTaskPlanManager.searchByQueryObject(queryObject);
		for (int i = 0; i < list.size(); i++) {
			ConsignTaskPlan consignTaskPlan = (ConsignTaskPlan)list.get(i);
			ProjectMTaskPlan projectMTaskPlan = new ProjectMTaskPlan();
			projectMTaskPlan.setTproject(project);
			projectMTaskPlan.setTaskPlanSub(consignTaskPlan.getTaskPlanSub().getObjId());
			projectMTaskPlan.setBuyMainBody(consignTaskPlan.getConsign().getConsBuyer());
			projectMTaskPlanManager.save(projectMTaskPlan);
			purCategoryIds += consignTaskPlan.getTaskPlanSub().getPurchase().getObjId()+SeparationEnum.COMMA;
			purCategoryName += consignTaskPlan.getTaskPlanSub().getPurchase().getCategoryName()+SeparationEnum.COMMA;
		}
		if(!StringUtils.empty(purCategoryIds))
			project.setPurCategoryIds(purCategoryIds.substring(0,purCategoryIds.lastIndexOf(SeparationEnum.COMMA)));
		if(!StringUtils.empty(purCategoryName))
			project.setPurCategoryNames(purCategoryName.substring(0,purCategoryName.lastIndexOf(SeparationEnum.COMMA)));
		this.save(project,Project.class);
	}
	
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
	public List getAllTaskPlanSubByProject(String projectId) {
		logger.debug("\n ProjectMTaskPlanServiceImpl||getAllTaskPlanSubByProject\n");
		return projectMTaskPlanManager.getAllTaskPlanSubByProject(projectId);
	}
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
	public ProjectMTaskPlan getProjectMTaskPlan(String projectId){
		return projectMTaskPlanDao.getProjectMTaskPlan(projectId);
	}
}
