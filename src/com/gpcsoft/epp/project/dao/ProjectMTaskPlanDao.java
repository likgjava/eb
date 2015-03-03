package com.gpcsoft.epp.project.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;

public interface ProjectMTaskPlanDao extends BaseGenericDao<ProjectMTaskPlan> {

	/**
	 * FuncName:removeProjectMTaskPlanByProjectId
	 * Description :根据项目主键删除中间表数据  
s	 * @param   projectId:项目主键
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-7-7下午01:08:31 
	 * @Modifier  liukes
	 * @Modified Date: 2010-7-7下午01:08:31 
	 */
	public void removeProjectMTaskPlanByProjectId(String projectId);
	
	/**
	 * FuncName:reductionTaskPlanSubByProjectId
	 * Description :根据项目主键设置申报书大宗状态为null  
	 * @param   projectId:项目主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-7下午01:26:14 
	 */
	public void reductionTaskPlanSubByProjectId(String projectId);
	
	/**
	 * FuncName:removeProjectMTaskPlanByBytaskPlanIdAndProjectId
	 * Description :根据项目主键和申报书主键删除中间表对象
	 * @param   taskPlanId:申报书主键,projectId:项目主键
	 * @return  void
	 * @author 		liuke
	 * @Create Date: 2010-7-7下午01:26:14 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-7下午01:26:14
	 */
	public void removeProjectMTaskPlanByBytaskPlanIdAndProjectId(String taskPlanId,String projectId);
	
	/**
	 * FuncName:getProjectMTaskPlanByQueryObject
	 * Description :通过QueryObject对象得到中间表对象
	 * @param   queryObject:查询条件组装的对象
	 * @return  List<ProjectMTaskPlan>
	 * @author 	liuke
	 * @Create Date: 2010-7-28上午10:04:38 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-28上午10:04:38 by 
	 */
	public List<ProjectMTaskPlan> getProjectMTaskPlanByQueryObject(QueryObject<ProjectMTaskPlan> queryObject);
	
	/**
	 * FuncName: getProjectListByTaskPlan
	 * Description :  创建或修改对象
	 * @param page:分页对象,auditStatus:审核状态,useStatus:临时状态
	 * @return Page
	 * @author: shenjz
	 * @Create Date:2011-1-5  下午03:45:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-1-5  下午03:45:17
	 */
	public Page getProjectListByTaskPlan(Page page,String auditStatus,String useStatus);
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
	
	/** 
	 * FuncName:searchProjectList
	 * Description:所有项目的总金额
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author shenjz
	 * @Create Date: 2011-4-8上午9:54:23  
	 * @Modifier shenjz
	 * @Modified Date: 2011-4-8上午9:54:23   
	 */
	public Double searchProjectMoney(QueryObject<Project> queryObject);
	
	/**
	 * FuncName: searchProjectMoney2
	 * Description : 根据项目的创建日期获取某月的项目资金总金额
	 * @param 
	 * @param date1
	 * @param date2
	 * @return Double
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午11:33:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午11:33:33
	 */
	public List<ProjectMTaskPlan> searchProjectMoney2(String date1,String date2);
	
}
