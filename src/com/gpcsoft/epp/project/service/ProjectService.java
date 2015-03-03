package com.gpcsoft.epp.project.service;
import java.util.List;

import net.sf.json.JSONArray;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectItemTend;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ProjectService extends BaseGenericService<Project> {

	/** 
	 * FuncName:searchProjectForBuyer
	 * Description:根据查询条件，获得采购人相关的项目
	 * @param queryObject:查询条件,page:分页对象,user:用户对象
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:10:20 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-5-19上午10:10:20 
	 */
	public Page<Project> searchProjectForBuyer(QueryObject queryObject, Page<Project> page,User user);

	/** 
	 * FuncName:searchProjectForAgent
	 * Description:根据查询条件，获得代理机构相关的项目
	 * @param queryObject:查询条件,page:分页对象,user:当前用户
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:10:20 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-5-19上午10:10:20 
	 */
	public Page<Project> searchProjectForAgent(QueryObject queryObject, Page<Project> page,User user);	
	
	/** 
	 * FuncName:searchProjectForSupplier
	 * Description :根据查询条件，获得供应商相关的项目
	 * @param queryObject,page,user
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:10:20 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-5-19上午10:10:20  
	 */
	public Page<Project> searchProjectForSupplier(QueryObject queryObject, Page<Project> page,User user);
	
	/** 
	 * FuncName:getProjectByTotal
	 * Description :根据项目主键获取项目信息,并将项目的统计信息封装
	 * @param objId:项目主键 
	 * @return Project
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:54:50 
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19上午10:54:50  
	 */
	public Project getProjectByTotal(String objId);

	/** 
	 * FuncName:getSubProjectByProjectId
	 * Description :根据项目Id获得项目对应的包组信息
	 * @param   projectId:项目主键
	 * @return List<Project>
	 * @author yangx  
	 * @Create Date: 2010-11-9下午05:13:01 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-9下午05:13:01     
	 */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception;
	
	/** 
	 * FuncName:saveProjectAndReq
	 * Description:保存包组和需求条目中间表
	 * @param   project:包组,recordCount:包组和条目中间表数据
	 * @return  Project
	 * @author 	  yangx
	 * @Create Date: 2010-6-24下午03:21:43 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-24下午03:21:43     
	 */
	public Project saveProjectAndReq(Project project,String recordCount);
	
	/** 
	 * FuncName:removeProjectAndReq
	 * Description:删除包组以及中间表
	 * @param   projectId:项目主键
	 * @return void
	 * @author yangx  
	 * @Create Date: 2010-6-25上午10:25:43 
	 * @Modifier yangx
	 * @Modified Date: 2010-6-25上午10:25:43     
	 */
	public void removeProjectAndReq(String projectId);
	
	/** 
	 * FuncName:getSubProjectMTaskPlanSubByProjectId
	 * Description:根据包组ID获取中间表(包组与申报书条目中间表)值
	 * @param   projectId:项目主键
	 * @return List
	 * @author yangx  
	 * @Create Date: 2010-6-25下午01:11:34 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-6-25下午01:11:34    
	 */
	@SuppressWarnings("unchecked")
	public List getSubProjectMTaskPlanSubByProjectId(String projectId);
	
	/** 
	 * FuncName:updateProjectAndReq
	 * Description :修改包组和申报书条目中间数据
	 * @param   project:包组,recordCount:申报书条目
	 * @return void
	 * @author yangx  
	 * @Create Date: 2010-6-25下午02:27:13 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-6-25下午02:27:13    
	 */
	public void updateProjectAndReq(Project project,String recordCount);

	/** 
	 * FuncName:searchProjectForSupervise
	 * Description:根据查询条件，获得监管机构相关的项目
	 * @param queryObject:查询条件,page:分页对象,user:当前用户
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:10:20 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-5-19上午10:10:20 
	 */
	public Page<Project> searchProjectForSupervise(QueryObject queryObject, Page<Project> page,User user);
	
	/** 
	 * FuncName:getSumTaskPlanDetailProjectId
	 * Description :  根据项目ID获取采购预算总金额
	 * @param   projectId:项目主键
	 * @return String
	 * @author yangx  
	 * @Create Date: 2010-7-7下午04:14:47 
	 * @Modifier    yangx
	 * @Modified Date: 2010-7-7下午04:14:47     
	 */
	public String getSumTaskPlanDetailProjectId(String projectId);
	
	/** 
	 * FuncName:saveProjectByTaskPlanSubs
	 * Description :根据选中的申报书条目创建项目
	 * @param project
	 * @param taskPlanSubIds
	 * @author liuy
	 * @Create Date: 2010-7-15下午03:27:19
	 * @Modifier   liuy 
	 * @Modified Date: 2010-7-15下午03:27:19
	 */
	public void saveProjectByTaskPlanSubs(Project project, String taskPlanSubIds);
	
	/** 
	 * FuncName:getProjectBySubProjectId
	 * Description : 根据包组Id查询项目 
	 * @param   subProjectId:包组主键
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-8-3下午01:54:00 
	 * @Modifier  yangx
	 * @Modified Date: 2010-8-3下午01:54:00    
	 */
	public Project getProjectBySubProjectId(String subProjectId);
	
	/**
	 * FuncName:createProjectCodeByQO
	 * Description: 生成项目编号,Service层必须开启DB事物
	 * @param queryObject:为扩展参数，暂时不用,projBuyMethod:项目采购方式
	 * @return String
     * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String createProjectCodeByQO(QueryObject queryObject,String projBuyMethod)throws EsException;
	
	/**
	 * FuncName:getProjectByObjId
	 * Description :根据主键获得项目  
	 * @param   objId:
	 * @return  Project
	 * @author liuke
	 * @Create Date: 2010-9-7上午09:53:55 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-7上午09:53:55 
	 */
	public Project getProjectByObjId(String objId);

	/** 
	 * FuncName:updateProjectTime
	 * Description:维护项目时间
	 * @param   projectRule:项目规则
	 * @return  Project
	 * @author yangx
	 * @Create Date: 2010-11-10下午05:33:10 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-10下午05:33:10   
	 */
	public Project updateProjectTime(ProjectRule projectRule);

	/** 
	 * FuncName:updateProjectevalTime
	 * Description:更新项目评标时间 
	 * @param   projectRule:项目规则
	 * @return Project
	 * @author yangx 
	 * @Create Date: 2010-11-10下午05:59:20 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-10下午05:59:20    
	 */
	public Project updateProjectevalTime(ProjectRule projectRule);

	/**
	 * FuncName: removeProject
	 * Description:删除项目
	 * @param   projectId:项目主键
	 * @return void
	 * @author yangx 
	 * @Create Date: 2010-9-19下午02:03:04 
	 * @Modifier yangx  
	 * @Modified Date: 2010-9-19下午02:03:04     
	 */
	public void removeProject(String projectId);
	
	/** 
	 * FuncName:saveInputTenderInfo
	 * Description :  录入招标项目
	 * @param   project：项目,jsonArray:标段的JSON数组
	 * @return  Project
	 * @author 		yangx
	 * @Create Date: 2010-9-21下午01:55:19 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-21下午01:55:19    
	 */
	public Project saveInputTenderInfo(Project project,JSONArray jsonArray);
	
	/** 
	 * FuncName:checkProjectIsComplete
	 * Description:检查项目完整性
	 * @param   projectId:项目Id
	 * @return Project
	 * @author yangx 
	 * @Create Date: 2010-9-28下午04:54:03 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-28下午04:54:03    
	 */
	public Project checkProjectIsComplete(String projectId);
	
	/** 
	 * FuncName:getPuaseProjectList
	 * Description:获取暂停项目列表
	 * @param   page:分页对象,queryObject:组装的查询条件
	 * @return Page<Project>
	 * @author yangx  
	 * @Create Date: 2010-10-11下午04:09:45 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午04:09:45    
	 */
	public Page<Project> getPuaseProjectList(Page page,QueryObject<Project> queryObject);

	/** 
	 * FuncName:getPuaseProjectListForAudit
	 * Description: 获取待审核暂停项目列表
	 * @param   page:分页对象,queryObject:查询条件
	 * @return Page<Project>
	 * @author yangx 
	 * @Create Date: 2010-10-11下午07:07:03 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午07:07:03 by   
	 */
	public Page<Project> getPuaseProjectListForAudit(Page page,QueryObject<Project> queryObject);

	/** 
	 * FuncName:searchProjectListForSuperviseManager
	 * Description:根据监管人管理员获取所有项目列表
	 * @param   queryObject:查询条件,page:分页对象
	 * @return Page<Project>
	 * @author yangx  
	 * @Create Date: 2010-10-13下午04:50:32 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-10-13下午04:50:32   
	 */
	public Page<Project> searchProjectListForSuperviseManager(QueryObject<Project> queryObject,Page<Project> page);

	/** 
	 * FuncName:searchProjectListForSupervise
	 * Description :根据监管人获取所有项目列表
	 * @param   queryObject:组装的查询条件对象,page:分页对象,superviseId:监管人主键
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:14:18 
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:14:18    
	 */
	public Page<Project> searchProjectListForSupervise(QueryObject<Project> queryObject,Page<Project> page,String superviseId);

	/** 
	 * FuncName:searchProjectListForAgentManager
	 * Description :   根据代理机构管理员获取所有项目列表
	 * @param   queryObject:组装的查询对象,page:分页对象,agentMId:代理机构Id 
	 * @return   Page<Project>
	 * @author     yangx
	 * @Create Date: 2010-10-13下午05:16:44 
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:16:44     
	 */
	public Page<Project> searchProjectListForAgentManager(QueryObject<Project> queryObject,Page<Project> page,String agentMId);
	
	/** 
	 * FuncName:searchProjectListForAgent
	 * Description :  根据代理机构Id获取所有项目列表
	 * @param   queryObject:组装的查询对象,page:分页对象,agentId:代理机构主键
	 * @return Page<Project>
	 * @author yangx  
	 * @Create Date: 2010-10-13下午05:26:46
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:26:46    
	 */
	public Page<Project> searchProjectListForAgent(QueryObject<Project> queryObject,Page<Project> page,String agentId);

	/** 
	 * FuncName:searchProjectListForBuyer
	 * Description :  根据采购人Id获取所有项目列表
	 * @param   queryObject:组装的查询条件,page:分页对象,buyerId:采购人主键
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:29:07 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-13下午05:29:07   
	 */
	public Page<Project> searchProjectListForBuyer(QueryObject<Project> queryObject,Page<Project> page,String buyerId);

	/** 
	 * FuncName:searchProjectListForSupply
	 * Description :  根据供应商Id获取所有项目列表
	 * @param   queryObject:查询条件,page:分页对象,supplyId:供应商主键
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:31:46 
	 * @Modifier   yangx
	 * @Modified Date: 2010-10-13下午05:31:46     
	 */
	public Page<Project> searchProjectListForSupply(QueryObject<Project> queryObject,Page<Project> page,String supplyId);

	/** 
	 * FuncName:searchProjectListForGovernment
	 * Description :根据业务处室Id获取所有项目
	 * @param   queryObject:查询条件,page:分页对象,governmentId:业务处室Id
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-15下午01:18:43  
	 * @Modifier yangx
	 * @Modified Date: 2010-10-15下午01:18:43   
	 */
	public Page<Project> searchProjectListForGovernment(QueryObject<Project> queryObject,Page<Project> page,String governmentId);
	
	
	/** 
	 * FuncName:searchProjectListForGovernment
	 * Description :根据条件获取项目列表数据
	 * @param   queryObject:查询条件,page:分页对象
	 * @return  Page<Project>
	 * @author chenhj
	 * @Create Date: 2011-10-14下午01:18:43  
	 * @Modifier chenhj
	 * @Modified Date: 2011-10-14下午01:18:43   
	 */
	public Page<Project> searchProjectList(QueryObject<Project> queryObject,Page<Project> page);
	
	/** 
	 * FuncName:getProjectRuleByProjectId
	 * Description :  根据项目Id获取项目规则
	 * @param   projectId：项目Id
	 * @return  ProjectRule
	 * @author    yangx
	 * @Create Date: 2010-11-10下午05:04:41 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-10下午05:04:41    
	 */
	public ProjectRule getProjectRuleByProjectId(String projectId);
	
	/**
	 * FuncName: getProjectListByQueryObject
	 * Description : 根据QObject查询项目信息
	 * @param queryObject:组装的查询对象
	 * @return List<Project>
	 * @author: liuke
	 * @Create Date:2010-12-24  下午02:04:58
	 * @Modifier: liuke
	 * @Modified Date:2010-12-24  下午02:04:58
	 */
	public List<Project>getProjectListByQueryObject(QueryObject<Project> queryObject);
	
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
	 * FuncName: saveECPProjectByTaskPlanSubs
	 * Description :  根据选中的申报书条目创建项目
	 * @param project 项目 taskPlanSubIds 申报书条目
	 * @param project
	 * @param taskPlanSubIds void
	 * @author: liuke
	 * @Create Date:2011-3-1  下午05:57:24
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午05:57:24
	 */
	public void saveECPProjectByTaskPlanSubs(Project project, String taskPlanSubIds);
	
	
	/** 
	 * FuncName:getSubProjectMTaskPlanSubByProjectId
	 * Description:根据包组ID获取中间表(包组与申报书条目中间表)值
	 * @param   projectId:项目主键
	 * @return List
	 * @author yangx  
	 * @Create Date: 2010-6-25下午01:11:34 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-6-25下午01:11:34    
	 */
	@SuppressWarnings("unchecked")
	public List getSubProjectMTaskPlanSubBySubProjectId(String projectId);
	
	
	
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
	 * FuncName:searchProjectList
	 * Description::获取项目负责人项目列表数目
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
	 * FuncName:searchProjectList
	 * Description:所有项目的总金额
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author 	shenjz
	 * @Create Date: 2011-4-8上午9:54:23  
	 * @Modifier shenjz
	 * @Modified Date: 2011-4-8上午9:54:23   
	 */
	public Double searchProjectMoney(QueryObject<Project> queryObject);
	/**
	 * FuncName: findProjectByProjCode
	 * Description :  通过项目编号查找项目
	 * @param 
	 * @param projCode
	 * @return Project
	 * @author: shenjz
	 * @Create Date:2011-6-23  下午04:23:18
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  下午04:23:18
	 */
	public Project findProjectByProjCode(String projCode);
	
	/**
	 * Description :  根据选中的备案书条目创建建筑工程项目
	 * @param project 项目 taskPlanSubIds 备案书条目
	 * @param project
	 * @param taskPlanSubIds void
	 * Created at 2011-6-22 11:04
	 */
	public void saveECPJZGCProjectByTaskPlanSubs(Project project, String taskPlanSubIds);
	/**
	 * FuncName: findProjectByProjCodeAndParent
	 * Description :  根据项目编号与parent查询项目
	 * @param 
	 * @param projCode
	 * @param parentId
	 * @return Project
	 * @author: liuke
	 * @Create Date:2011-5-30  下午03:54:03
	 * @Modifier: liuke
	 * @Modified Date:2011-5-30  下午03:54:03
	 */
	public Project findProjectByProjCodeAndParent(String projCode,String parentId);
	
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
	 * FuncName: saveSubProject
	 * Description: 保存建筑工程包组
	 * @param   project:包组 
	 * @return  Project
	 * @author 	  zhouzhanghe
	 * @Create Date: 2011-8-18 17:47 
	 */
	public Project saveSubProject(Project subProject) throws EsException ;
	/** 
	 * FuncName:removeProjectAndReq
	 * Description:删除标段以及中间表
	 * @param   projectId:项目主键
	 * @return void
	 * @author zhouzhanghe  
	 * @Create Date: 2011-8-19 09:52 
	 */
	public void removeSubProject(String subProjectId);
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
	
	//BigDecimal数值相加
	 public double add(double v1, double v2);
		/**
		 * FuncName: confirmFinish
		 * Description :  创建或修改对象
		 * @param 
		 * @param objId:项目主键
		 * @throws Exception void
		 * @author: shenjz
		 * @Create Date:2011-8-29  下午04:41:21
		 * @Modifier: shenjz
		 * @Modified Date:2011-8-29  下午04:41:21
		 */
	 public PurchaseDoc saveConfirmFinish(String objId,String content,String keyWord) throws Exception;
	 
		
	/** 
	 * FuncName: getProjectRuleByTenderId
	 * Description :  根据tenderId获取项目规则
	 * if 该ID为包组ID
	 *   则去查找包给的规则
	 *   if not exist 
	 *      则去查询项目的规则
	 * else
	 *    去查询项目规则
	 * @param   projectId：项目Id
	 * @return  ProjectRule
	 * @author    zhouzhanghe
	 * @Create Date: 2011-11-08 11:02 
	 */
	public ProjectRule getProjectRuleByTenderId(String tenderId);

	
	/** 
	 * FuncName: getProjectImplStatusByProjectId
	 * Description : 根据项目Id获取项目实施状态[00：正常;01:暂停;02:终止;]
	 * @param   projectId：项目Id
	 * @author    yangyc
	 * @Create Date: 2011-11-09 10:02 
	 */
	public String getProjectImplStatusByProjectId(String projectId);

	
	
	/** 
	 * FuncName : checkProjectBudgetByProjectId
	 * Description :  检查项目拆分预算是否完成
	 * Create Date: 2011-11-9上午11:51:55 by yangx  
	 * Modified Date: 2011-11-9上午11:51:55 by yangx
	 * @param   projectId：项目ID
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean checkProjectBudgetByProjectId(String projectId) throws Exception;

	
	/** 
	 * FuncName : finishSubProject
	 * Description :  完成分包
	 * Create Date: 2011-11-9下午01:13:41 by yangx  
	 * Modified Date: 2011-11-9下午01:13:41 by yangx
	 * @param   projectId：项目ID
	 * @Exception   
	 */
	public Project finishSubProject(String projectId) throws Exception;
	
	/**
	 * 根据项目ID获得该项目是否存在包组
	 * @param projectId
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-11-10 14:39
	 */
	public boolean getIsExistSubProject(String projectId);

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

	/**
	 * FuncName : getProjectListByByProjId
	 * Description :  根据projectId获取项目
	 * @param projectId
	 * @return 如果没分包List为项目,如果分包List为包组
	 * @author: zhouzhanghe
	 * @Create Date:2011-10-26 10:01
	 */
	 public List<Project> getProjectListByByProjId(String projectId);

	/**
	 * FuncName: searchProjectListByTenderType
	 * Description :  根据项目类型查询项目
	 * @param 
	 * @param queryObject
	 * @param page
	 * @return Page<Project>
	 * @author: liuke
	 * @Create Date:2011-8-8  下午12:36:36
	 * @Modifier: liuke
	 * @Modified Date:2011-8-8  下午12:36:36
	 */
	public Page<Project> searchProjectListByTenderType(QueryObject<Project> queryObject,Page<Project> page);

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
		 * FuncName: getProjectListByProjectItem
		 * Description :  获取已经根据条目创建的项目
		 * @param 
		 * @param itemId
		 * @return List<ProjectItemTend>
		 * @author: shenjz
		 * @Create Date:2011-12-23  上午10:13:25
		 * @Modifier: shenjz
		 * @Modified Date:2011-12-23  上午10:13:25
		 */
		public List<ProjectItemTend> getProjectListByProjectItem(String itemId);
}
