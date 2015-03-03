package com.gpcsoft.epp.project.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectDepartment;
import com.gpcsoft.epp.project.domain.ProjectLinkGovMan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.srplatform.auth.domain.User;


@SuppressWarnings(value={"unchecked"})
public interface ProjectDao extends BaseGenericDao<Project> {
	
	/**
	 * FuncName:saveProjectDepart
	 * Description:分配执行部门 
	 * @param projectDepartment:执行部门
	 * @return  ProjectDepartment
	 */
	public ProjectDepartment saveProjectDepart(ProjectDepartment projectDepartment);

	/**
	 * FuncName:saveProjectLinkMan
	 * Description: 指定经办人
	 * @param projectLinkGovMan:项目经办人
	 * @return ProjectLinkGovMan
	 */
	public ProjectLinkGovMan saveProjectLinkMan(ProjectLinkGovMan projectLinkGovMan);
	
	/**
	 * FuncName:addConsign
	 * Description:提交委托
	 * @param  consign:委托协议
	 * @return Consign
	 */
	public Consign addConsign(Consign consign);

	/** 
	 * FuncName:searchProject
	 * Description:根据查询条件获得相关的项目信息
	 * @param queryObject:组装的查询条件对象,page:分页对象
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:11:42   
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19上午10:11:42 
	 */
	public Page<Project> searchProject(QueryObject queryObject,
			Page<Project> page);

	/**
	 * FuncName:queryProject
	 * Description:根据ID查询项目信息
	 * @param objId:项目主键,
	 * @return Page<Project>
	 */
	public Page<Project> queryProject(String objId,int start, int pageSize);
	
	/**
	 * FuncName:searchAllProject
	 * Description:获取全部项目列表(不通过控件显示，所以不分页用LIST返回)
	 * @param  queryObject:组装的查询对象
	 * @return  List<Project> 
	 * @author guom
	 * @Create Date: 2010-6-7下午4:23:57 
	 * @Modifier guom
	 * @Modified Date: 2010-6-7下午4:23:57
	 */
	public List<Project> searchAllProject(QueryObject queryObject);
	
	/** 
	 * FuncName:updateProjectAgency
	 * Description :  更新项目的代理机构
	 * @param   projectId:项目主键,agencyId:代理机构主键
	 * @return  void
	 * @author sunl
	 * @Create Date: 2010-6-18下午05:31:15 
	 * @Modifier    sunl
	 * @Modified Date: 2010-6-18下午05:31:15    
	 */
	public void updateProjectAgency(String projectId,String agencyId);
	
	/** 
	 * FuncName:removeProjectMTaskPlan
	 * Description :  删除包组条目中间表
	 * @param   projectId:项目主键
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-6-25上午10:25:43 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-25上午10:25:43    
	 */
	public void removeProjectMTaskPlan(String projectId);
	
	/** 
	 * FuncName:getTotalProjectMTaskPlanByProjectId
	 * Description :  根据项目ID获取统计分包数量
	 * @param   objId:项目主键
	 * @return  SubProjectMTaskPlanSub
	 * @author yangx
	 * @Create Date: 2010-6-30下午03:48:07 
	 * @Modifier yangx   
	 * @Modified Date: 2010-6-30下午03:48:07     
	 */
	public SubProjectMTaskPlanSub getTotalProjectMTaskPlanByProjectId(String objId);
	
	/** 
	 * FuncName:getTotalTaskPlanSubByProjectId
	 * Description:根据项目ID获取统计条目数量
	 * @param   objId:项目主键
	 * @return  TaskPlanSub
	 * @author yangx
	 * @Create Date: 2010-6-30下午03:48:07 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-30下午03:48:07    
	 */
	public TaskPlanSub getTotalTaskPlanSubByProjectId(String objId);
	
	/** 
	 * FuncName:getSumTaskPlanDetailProjectId
	 * Description :  根据项目ID采购预算总金额
	 * @param   projectId:项目主键
	 * @return  String
	 * @author yangx
	 * @Create Date: 2010-7-7下午04:14:47 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-7下午04:14:47     
	 */
	public String getSumTaskPlanDetailProjectId(String projectId);
	
	/** 
	 * FuncName:getProjectBySubProjectId
	 * Description : 根据包组Id查询项目 
	 * @param   subProjectId:包组主键
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-8-3下午01:54:00 
	 * @Modifier   yangx
	 * @Modified Date: 2010-8-3下午01:54:00  
	 */
	public Project getProjectBySubProjectId(String subProjectId);
	
	/**
	 * FuncName:getPackNamesByPackIds
	 * Description: 根据包件ID获取包件名称(多个名称逗号分隔)
	 * @param packIds:包件主键
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-8-11 上午11:23:11 
	 */
	public String getPackNamesByPackIds(String[] packIds);
	
	/**
	 * FuncName:getPackNumsByProjectId
	 * Description: 项目主键获得包件数量
	 * @param projectId:项目主键
	 * @return String
	 * @author liuke
	 * @Create Date 2010-8-11 上午11:23:11  
	 */
	public BigDecimal getPackNumsByProjectId(String projectId);
	
	/** 
	 * FuncName:removeProject
	 * Description:删除项目
	 * @param   projectId:项目主键
	 * @return void
	 * @author yangx 
	 * @Create Date: 2010-9-19下午02:03:04 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-19下午02:03:04    
	 */
	public void removeProject(String projectId);

	/** 
	 * FuncName:removeProjectRule
	 * Description:删除项目规则
	 * @param   projectId：项目Id
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-11-11上午11:32:23 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-11上午11:32:23   
	 */
	public void removeProjectRule(String projectId);
	
	/** 
	 * FuncName:searchProjectByCurUser
	 * Description :根据供应商当前用户和查询对象获得对应的参加过的招标项目
	 * @param queryObject:查询条件组装的对象,page:分页对象,user:当前登录用户
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-6-26上午11:41:11 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-6-26上午11:41:11  
	 */
	public Page<Project> searchProjectByCurUser(QueryObject queryObject,Page<Project> page, User user);
	
	/** 
	 * FuncName:getPuaseProjectList
	 * Description :  获取暂停项目列表
	 * @param   page:分页对象,queryObject:查询条件组装的对象
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:09:45 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-10-11下午04:09:45    
	 */
	public Page<Project> getPuaseProjectList(Page page,QueryObject<Project> queryObject);
	
	/** 
	 * FuncName:getPuaseProjectListForAudit
	 * Description :  获取待审核暂停项目列表
	 * @param   page:分页对象,queryObject:查询条件组装的对象
	 * @return  Page<Project>
	 * @author 	yangx
	 * @Create Date: 2010-10-11下午07:07:03 
	 * @Modifier   yangx
	 * @Modified Date: 2010-10-11下午07:07:03    
	 */
	public Page<Project> getPuaseProjectListForAudit(Page page,QueryObject<Project> queryObject);

	/** 
	 * FuncName:searchProjectList
	 * Description :  获取项目列表
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午04:54:23  
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午04:54:23   
	 */
	public Page<Project> searchProjectList(QueryObject<Project> queryObject,Page page);
	
	/** 
	 * FuncName:statisticsEbuyMethod
	 * Description :  取得采购方式的统计数据（5种采购方式的项目数）
	 * @param   queryObject:组装的查询条件对象
	 * @return List
	 * @author yangx  
	 * @Create Date: 2010-10-15下午04:32:56 
	 * @Modifier   yangx 
	 * @Modified Date: 2010-10-15下午04:32:56    
	 */
	public List statisticsEbuyMethod(QueryObject queryObject);
	
	/** 
	 * FuncName:getProjectRuleByProjectId
	 * Description :  根据项目Id获取项目规则
	 * @param   projectId：项目Id
	 * @return  ProjectRule
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:04:41 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-10下午05:04:41     
	 */
	public ProjectRule getProjectRuleByProjectId(String projectId);

	/** 
	 * FuncName: getProjectByNameOrCode  
	 * Description : 根据包组名称或编号查询项目下的包组
	 * Create Date: 2011-1-19下午08:43:54 by yangx  Modified Date: 2011-1-19下午08:43:54 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Project> getProjectByNameOrCode(QueryObject<Project> queryObject);
	
	
	
	/** 
	 * Description :  根据项目Id获取分包总金额
	 * Create Date: 2011-3-9下午03:17:58 by yangx  Modified Date: 2011-3-9下午03:17:58 by yangx
	 * @param   projectId：项目ID
	 * @return  String
	 * @Exception   
	 */
	public String getSumSProjectMTSByProjectId(String projectId);
	
	
	/**
	 * 
	 * FuncName: getSubProjectMTaskPlanSubByPackId
	 * Description :  创建或修改对象
	 * @param 
	 * @param packId
	 * @return SubProjectMTaskPlanSub
	 * @author: liuke
	 * @Create Date:2011-2-23  下午05:26:27
	 * @Modifier: liuke
	 * @Modified Date:2011-2-23  下午05:26:27
	 */
	public SubProjectMTaskPlanSub  getSubProjectMTaskPlanSubByPackId(String packId);
	
	/** 
	 * Description :  根据项目编号查询项目
	 * Create Date: 2011-3-16下午13:35:51 by zhouzhanghe
	 * @param  projCode 项目编号 
	 * @return  项目对象
	 * @Exception   
	 */
	public Project findProjectByProjCode(String projCode);
	
	/** 
	 * FuncName:searchProjectList
	 * Description:获取项目列表数目
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author shenjz
	 * @Create Date: 2011-4-8上午9:54:23  
	 * @Modifier shenjz
	 * @Modified Date: 2011-4-8上午9:54:23   
	 */
	public Long searchProjectCount(QueryObject<Project> queryObject);
	
	/**
	 * FuncName: findProjectByProjCodeBy
	 * Description :  通过包组编号和项目编号获取到项目包组
	 * @param 
	 * @param projCode
	 * @param packCode
	 * @return Project
	 * @author: shenjz
	 * @Create Date:2011-5-17  上午10:25:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-17  上午10:25:40
	 */
	public Project findProjectByProjCodeBy(String projCode,String packCode);
	/**
	 * FuncName: getProject
	 * Description :  获取到一个项目下的包组集合包组
	 * @param 
	 * @param projCode
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-5-18  上午11:53:11
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-18  上午11:53:11
	 */
	public List<Project> getSubProjectList(String projCode);
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取品目分类项目数目 
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectNumber(String itemsId);
	
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取品目分类项目数目 
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectEbuyMethodNumber(String ebuyMethod,String tenderType,String year);
	/**
	 * FuncName: getProjectNumber
	 * Description :  获取采购方式项目数目  
	 * @param 
	 * @param itemsId
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-28  上午09:55:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28  上午09:55:31
	 */
	public Long getProjectEbuyMethodNumber2(String ebuyMethod,String year);
	/**
	 * FuncName: getProjectPurCategoryNumber
	 * Description :  获取到品目采购分类该类别下的数目
	 * @param year
	 * @return Long
	 * @author: shenjz
	 * @Create Date:2011-6-29  上午09:42:03
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-29  上午09:42:03
	 */
	public List getProjectPurCategoryNumber(String year);
	/**
	 * FuncName: searchProjectListForEntryBailRecord
	 * Description :  查询待录入保证金的项目
	 * @param 
	 * @param queryObject
	 * @param page
	 * @return Page<Project>
	 * @author: liuke
	 * @Create Date:2011-8-10  下午01:51:21
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  下午01:51:21
	 */
	public Page<Project> searchProjectListForEntryBailRecord(QueryObject<Project> queryObject, Page<Project> page);
	
	
	/** 
	 * FuncName : getSubProjectByProjectId
	 * Description :  根据项目Id获取包组
	 * Create Date: 2011-10-11下午04:44:00 by yangx  
	 * Modified Date: 2011-10-11下午04:44:00 by yangx
	 * @param   projectId：项目Id
	 * @return  List<Project>
	 * @Exception   
	 */
	public List<Project> getSubProjectByProjectId(String projectId);
	
	/** 
	 * FuncName : getProjectImplStatusByProjectId
	 * Description :  根据项目Id获取项目实施状态[00：正常;01:暂停;02:终止;]
	 * Create Date: 2011-11-09上午10:06:00 by yangyc  
	 * @param   projectId：项目Id
	 * @return  List<Project>
	 * @Exception   
	 */
	public String getProjectImplStatusByProjectId(String projectId);
	
	/**
	 * FuncName : getSubProjectCountByProjectId
	 * Description :  根据项目Id获取包组数量
	 * @param projectId
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-10 14:28
	 */
	public short getSubProjectCountByProjectId(String projectId);

	/**
	 * FuncName:getTaskPlanSubBySubTenderId
	 * Description : 根据包件Id获得中间表数据
	 * @param   subTenderId:包件主键
	 * @return  SubProjectMTaskPlanSub
	 * @author caojz
	 * @Create Date: 2011-8-25上午09:34:57 
	 * @Modifier  caojz
	 * @Modified Date: 2011-8-25上午09:34:57 
	 */
	 public SubProjectMTaskPlanSub getTaskPlanSubBySubTenderId(String subTenderId);

	/** 
	 * FuncName : getProjectIdByPurchaseDocId
	 * Description :  根据招标文件Id获取项目
	 * Create Date: 2011-10-20  11:30  by caojz  
	 * Modified Date: 2011-10-20 11:30 by caojz
	 * @param   purchaseId
	 * @return  Project
	 * @Exception   
	 */
	public  Project  getProjectIdByPurchaseDocId(String purchaseId);
}
