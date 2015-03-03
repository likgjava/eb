package com.gpcsoft.epp.resproject.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.DateUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.resproject.dao.ResProjectDao;
import com.gpcsoft.epp.resproject.domain.MoneySource;
import com.gpcsoft.epp.resproject.domain.MoneySourceCategory;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.epp.resproject.service.MoneySourceCategoryService;
import com.gpcsoft.epp.resproject.service.ResProjectService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class ResProjectServiceImpl extends BaseGenericServiceImpl<ResProject> implements ResProjectService {
	
	@Autowired(required = true) @Qualifier("resProjectDaoHibernate")
	private ResProjectDao resProjectDao;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	/**
	 * 查询项目列表(代理机构)
	 * @param queryParamMap 查询 参数集合
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectInfoListForAgenty(Map<String,Object> queryParamMap,Page<ResProject> page)throws Exception{
		User user = AuthenticationHelper.getCurrentUser();
		Map<String,Object> model = new HashMap<String,Object>();
		OrgInfo agenty = userApiService.getOrgInfoByUser(user);
		String projectStatus = (String)queryParamMap.get("projectStatus");
		
		Page<ResProject> resultPage = null;
		if("all".equals(projectStatus)){
			resultPage = resProjectDao.findResProjectListOfAgenty(agenty.getObjId(),queryParamMap,page);
		}else{
			resultPage = resProjectDao.findResProjectListOfAgentyLeader(agenty.getObjId(), user.getEmp(), queryParamMap,page);
		}
		
		model.put("resultPage", resultPage);
		
		return model;
	}

	/**
	 * 查询项目详细信息
	 * @param resProjectId 项目ID
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectInfo(String parentId,String resProjectId)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		
		ResProject resProject = null;
		String[] moneyModels = new String[5];
		if(StringUtils.isNotBlank(resProjectId)){//已存在的顶级项目或子项目修改
			resProject = this.get(resProjectId);
		}else{//新建
			if(parentId!=null){//子项目新建
				ResProject parent = this.get(parentId);
				resProject = new ResProject();
				BeanUtils.copyProperties(parent, resProject);
				resProject.setObjId(null);
				resProject.setProjectName(null);
				resProject.setProjectNo(null);
				resProject.setParent(parent);
			}else{//顶级项目新建
				User user = AuthenticationHelper.getCurrentUser();
				OrgInfo agenty = userApiService.getOrgInfoByUser(user);
				resProject = new ResProject();
				resProject.setAgenty(agenty);
				resProject.setAgentyLeader(user.getEmp());
				resProject.setAgentyLinker(user.getEmp().getName());
			}
		}
		String[] moneyModelArray = (resProject.getMoneyModel()==null?"":resProject.getMoneyModel()).split(",");
		for(String moneyModel:moneyModelArray){
			if(StringUtils.isNotBlank(moneyModel)){
				moneyModels[Integer.parseInt(moneyModel)-1] = moneyModel;
			}
		}
		model.put("moneyModels", moneyModels);
		//获取代理机构员工列表
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		//获得中央投资资金来源类型集合
		List<Object[]> centralMoneySourceList = resProjectDao.findResProjectOfMoneySourceList(resProjectId==null?parentId:resProjectId,"00");
		List<Object[]> notCentralMoneySourceList = resProjectDao.findResProjectOfMoneySourceList(resProjectId==null?parentId:resProjectId,"01");
		//计算中央投资和非中央投资的投资金额
		BigDecimal centralInvestmentMoney = new BigDecimal("0");
		for(Object[] objs:centralMoneySourceList){
			centralInvestmentMoney = centralInvestmentMoney.add((BigDecimal)objs[2]);
		}
		BigDecimal notCentralInvestmentMoney = new BigDecimal("0");
		for(Object[] objs:notCentralMoneySourceList){
			notCentralInvestmentMoney = notCentralInvestmentMoney.add((BigDecimal)objs[2]);
		}
		model.put("resProject", resProject);
		model.put("empList", empList);
		model.put("centralMoneySourceList", centralMoneySourceList);
		model.put("notCentralMoneySourceList", notCentralMoneySourceList);
		model.put("centralInvestmentMoney", centralInvestmentMoney);
		model.put("notCentralInvestmentMoney", notCentralInvestmentMoney);
		return model;
	}
	
	/**
	 * 保存项目信息
	 * @param resProject
	 * @param moneySourceMap
	 * @throws Exception
	 */
	public void saveResProjectInfo(ResProject resProject,Map<String,String[]> moneySourceMap)throws Exception{
		if(StringUtils.isBlank(resProject.getObjId())){
			resProject.setObjId(null);
		}
		//设置代理机构项目负责人
		if(resProject.getAgentyLeader().getObjId()!=null){
			Employee agentyLeader = userApiService.getEmployeeById(resProject.getAgentyLeader().getObjId());
			resProject.setAgentyLeader(agentyLeader);
			resProject.setAgentyLinker(agentyLeader.getName());
		}
		//设置业主单位项目负责人
		if(resProject.getBudgetLeader().getObjId()!=null){
			Employee budgetLeader = userApiService.getEmployeeById(resProject.getBudgetLeader().getObjId());
			resProject.setBudgetLeader(budgetLeader);
			resProject.setBudgetLinker(budgetLeader.getName());
		}
		
		//设置项目是否为叶子节点,(设置原则，如果顶级项目进行了分解，那么设置顶级项目为0，子级项目为1.如果顶级项目没有进行分解，那么则等到顶级项目立项时在设置为1)
		if(StringUtils.isNotBlank(resProject.getParent().getObjId())){//有父项目，子项目是叶子节点，父项目不是
			resProject.setIsLeaf("1");
			ResProject parent = this.get(resProject.getParent().getObjId());
			parent.setIsLeaf("0");
			resProjectDao.getHibernateTemplate().saveOrUpdate(parent);
		}
		if(resProjectDao.findResProjectOfSubList(resProject.getObjId()).size()>0){
			resProject.setIsLeaf("0");
		}
		
		if(!"01".equals(resProject.getProjectStatus())){
			resProject.setUseStatus("00");
			resProject.setProjectStatus("00");
		}
		if(StringUtils.isNotBlank(resProject.getParent().getObjId())){//子级项目
			ResProject parent = this.get(resProject.getParent().getObjId());
			resProject.setTaskPlanId(parent.getTaskPlanId());
		}else{//顶级项目
			if(StringUtils.isBlank(resProject.getTaskPlanId())){//顶级项目第一次保存时，创建申报书
				TaskPlan taskPlan = new TaskPlan();
				setTaskPlanInfo(resProject,taskPlan);
				resProjectDao.getHibernateTemplate().saveOrUpdate(taskPlan);
				resProject.setTaskPlanId(taskPlan.getObjId());
			}else{//顶级项目修改时，更新申报书
				TaskPlan taskPlan = resProjectDao.getHibernateTemplate().get(TaskPlan.class, resProject.getTaskPlanId());
				setTaskPlanInfo(resProject,taskPlan);
				resProjectDao.getHibernateTemplate().saveOrUpdate(taskPlan);
			}
		}
		
		
		resProject.setCreateUser(AuthenticationHelper.getCurrentUser());
		resProject.setCreateTime(DateUtil.getDate());
		
		resProjectDao.getHibernateTemplate().saveOrUpdate(resProject);
		
		String[] categoryIds = (String[])moneySourceMap.get("categoryIds");
		String[] moneys = (String[])moneySourceMap.get("moneys");
		
		List<MoneySource> moneySourceList = resProjectDao.findResProjectOfMoneySourceList(resProject.getObjId());
		for(int i=0;i<categoryIds.length;i++){
			boolean flag = false;
			for(MoneySource moneySource:moneySourceList){
				if(categoryIds[i].equals(moneySource.getMoneySourceCategory().getObjId())){
					moneySource.setMoney(new BigDecimal(moneys[i]));
					flag = true;
				}
			}
			if(!flag){
				MoneySource moneySource = new MoneySource();
				moneySource.setResProject(resProject);
				moneySource.setMoneySourceCategory((MoneySourceCategory)resProjectDao.get(categoryIds[i], MoneySourceCategory.class));
				moneySource.setMoney(new BigDecimal(moneys[i]));
				moneySourceList.add(moneySource);
			}
		}
		resProjectDao.getHibernateTemplate().saveOrUpdateAll(moneySourceList);
		
	}
	
	private void setTaskPlanInfo(ResProject resProject,TaskPlan taskPlan)throws Exception{
		taskPlan.setTaskName(resProject.getProjectName());
		taskPlan.setTaskAgent(resProject.getAgenty());
		taskPlan.setAgentLeader(resProject.getAgentyLinker());
		taskPlan.setAgentLeaderTel(resProject.getAgentyLinkerTel());
		taskPlan.setBudget(resProject.getBudget());
		taskPlan.setBudgetName(resProject.getBudgetName());
		taskPlan.setLeader(resProject.getBudgetLeader());
		taskPlan.setBudgetLinker(resProject.getBudgetLinker());
		taskPlan.setBudgetLinkerTel(resProject.getBudgetLinkerTel());
		taskPlan.setDepartment(resProject.getDepartment());
		taskPlan.setDepartmentName(resProject.getDepartmentName());
		taskPlan.setDepartmentLinker(resProject.getDepartmentLinker());
		taskPlan.setDepartmentLinkerTel(resProject.getDepartmentLinkerTel());
		taskPlan.setCommissioner(resProject.getCommissionedUnit());
		taskPlan.setCommissionUnitName(resProject.getCommissionedUnitName());
		taskPlan.setCommissionUnitLinker(resProject.getCommissionedUnitLinker());
		taskPlan.setCommissionedUnitTel(resProject.getCommissionedUnitTel());
		taskPlan.setEbuyMethod("01");
	}
	
	/**
	 * 删除项目信息
	 * @param resProjectId 项目ID
	 * @throws Exception
	 */
	public void deleteResProjectInfo(String resProjectId)throws Exception{
		if(StringUtils.isNotBlank(resProjectId)){
			List<MoneySource> moneySourceList = null;
			ResProject resProject = this.get(resProjectId);
			List<ResProject> subs = resProjectDao.findResProjectOfSubList(resProjectId); 
			for(ResProject sub:subs){
				moneySourceList = resProjectDao.findResProjectOfMoneySourceList(sub.getObjId());
				resProjectDao.getHibernateTemplate().deleteAll(moneySourceList);
			}
			resProjectDao.getHibernateTemplate().deleteAll(subs);
			
			moneySourceList = resProjectDao.findResProjectOfMoneySourceList(resProjectId);
			resProjectDao.getHibernateTemplate().deleteAll(moneySourceList);
			//如果删除的时子项目，那么判断父项目下是否还有其他的子项目，如果没有了，那么将父项目的isLeaf置空。
			if(resProject.getParent()!=null){
				ResProject parent = this.get(resProject.getParent().getObjId());
				List<ResProject> list = resProjectDao.findResProjectOfSubList(resProject.getParent().getObjId());
				if(list.size()==1){
					parent.setIsLeaf(null);
					resProjectDao.getHibernateTemplate().saveOrUpdate(parent);
				}
			}
			resProjectDao.getHibernateTemplate().delete(resProject);
		}
	}
	
	/**
	 * 结束项目
	 * @param resProjectId
	 * @throws Exception
	 */
	public void updateResProjectToEnd(String resProjectId)throws Exception{
		ResProject resProject = this.get(resProjectId);		
	
		//查询原项目下的子级项目
		List<ResProject> list = resProjectDao.findResProjectOfSubList(resProject.getObjId());
		for(ResProject sub:list){
			sub.setProjectStatus("02");
		}
		resProjectDao.getHibernateTemplate().saveOrUpdateAll(list);		
		//结束本身
		resProject.setProjectStatus("02");
		resProjectDao.getHibernateTemplate().saveOrUpdate(resProject);
	}
	
	/**
	 * 查询项目的子项目集合信息
	 * @param resProjectId 项目ID
	 * @param page 分页对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findResProjectOfSubList(String resProjectId,Page<ResProject> page)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		Page<ResProject> resultPage = resProjectDao.findResProjectOfSubList(resProjectId,page);
		model.put("resultPage", resultPage);
		return model;
	}
	
	/**
	 * 查询子项目详细信息
	 * @param parentId 父项目ID
	 * @param subResProjectId 子项目ID
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findSubResProjectInfo(String parentId,String subResProjectId)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		ResProject parent = this.get(parentId);
		ResProject subResProject = null;
		String[] moneyModels = new String[5];
		if(StringUtils.isNotBlank(subResProjectId)){
			subResProject = this.get(subResProjectId);
		}else{
			subResProject = parent;
			subResProject.setObjId(null);
		}
		String[] moneyModelArray = (subResProject.getMoneyModel()==null?"":subResProject.getMoneyModel()).split(",");
		for(String moneyModel:moneyModelArray){
			moneyModels[Integer.parseInt(moneyModel)-1] = moneyModel;
		}
		model.put("moneyModels", moneyModels);
		//获取代理机构员工列表
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		//获得中央投资资金来源类型集合
		List<Object[]> centralMoneySourceList = resProjectDao.findResProjectOfMoneySourceList(subResProjectId==null?parentId:subResProjectId,"00");
		List<Object[]> notCentralMoneySourceList = resProjectDao.findResProjectOfMoneySourceList(subResProjectId==null?parentId:subResProjectId,"01");
		model.put("parent", parent);
		model.put("resProject", subResProject);
		model.put("empList", empList);
		model.put("centralMoneySourceList", centralMoneySourceList);
		model.put("notCentralMoneySourceList", notCentralMoneySourceList);
		return model;
	}
	
	/**
	 * 保存招标项目
	 * @param project
	 * @param resProjectId
	 * @throws Exception
	 */
	public void saveTenderProjectInfo(Project project,String resProjectId,String projSubIds)throws Exception{
		ResProject resProject = this.get(resProjectId);
		
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
		
		project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());
	    project.setTenderRecord("00");//未备案
	    
	    Employee manager = userApiService.getEmployeeById(project.getManager().getObjId());
	    User createUser = userApiService.getUserByEmpId(manager.getObjId());
	    project.setManager(manager);
	    project.setCreateUser(createUser);
	    project.setCreateTime(DateUtil.getDate());
	  //此次立项的需求条目ID,以创建项目任务书条目中间表数据
	    if(StringUtils.isNotBlank(projSubIds)&& projSubIds.endsWith(",")) {
	    	projSubIds = projSubIds.substring(0,projSubIds.length()-1);
	    }
	    project.setTaskPlan((TaskPlan)resProjectDao.get(resProject.getTaskPlanId(), TaskPlan.class));
	    project.setResProjectId(resProjectId);
	    resProjectDao.getHibernateTemplate().saveOrUpdate(project);
	    projectService.saveECPProjectByTaskPlanSubs(project, projSubIds);
	    ProjectRule projectRule = new ProjectRule();//项目规则
		projectRule.setProject(project);
		projectRule.setIsDividePack(false);
		resProjectDao.getHibernateTemplate().saveOrUpdate(projectRule);//保存项目规则
		
		
		resProject.setIsLeaf("1");
		resProject.setUseStatus("01");
		resProject.setProjectStatus("01");
		resProjectDao.getHibernateTemplate().saveOrUpdate(resProject);
		if(resProject.getParent()!=null){
			ResProject parent = resProject.getParent();
			parent.setUseStatus("01");
			parent.setProjectStatus("01");
			resProjectDao.getHibernateTemplate().saveOrUpdate(parent);
		}
	}

	/**
	 * FuncName : loadResProjectInfoListForBudget
	 * Description :  
	 * Create Date: 2011-12-21下午07:31:45 by liuke
	 * Modified Date: 2011-12-21下午07:31:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> findResProjectInfoListForBudget(
			Map<String, Object> queryParamMap, Page<ResProject> page)
			throws Exception {
		User user = AuthenticationHelper.getCurrentUser();
		Map<String,Object> model = new HashMap<String,Object>();
		Page<ResProject> resultPage = null;
		resultPage = resProjectDao.findResProjectInfoListForBudget(user.getObjId(), queryParamMap,page);
		model.put("resultPage", resultPage);
		return model;
	}

	
	/**
	 * FuncName : findResProjectItemListByResProjectId
	 * Description :  
	 * Create Date: 2011-12-22上午10:46:01 by liuke
	 * Modified Date: 2011-12-22上午10:46:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ResProjectItem> findResProjectItemListByResProjectId(
			String resProjectId) throws Exception{
		return resProjectDao.findResProjectItemListByResProjectId(resProjectId);
	}

	/**
	 * FuncName : saveResProjectItem
	 * Description :  
	 * Create Date: 2011-12-22下午03:17:17 by liuke
	 * Modified Date: 2011-12-22下午03:17:17 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveResProjectItem(ResProjectItem resProjectItem) {
		resProjectDao.save(resProjectItem, ResProjectItem.class);
	}

	/**
	 * FuncName : saveResProject
	 * Description :  
	 * Create Date: 2011-12-23上午11:37:01 by liuke
	 * Modified Date: 2011-12-23上午11:37:01 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveResProject(ResProject resProject) {
		resProjectDao.save(resProject);
	}
	
	
	/** 
	 * FuncName : saveResProjectForAudit
	 * Description :  审核招标项目
	 * Create Date: 2011-12-24下午12:27:52 by yangx  
	 * Modified Date: 2011-12-24下午12:27:52 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ResProject saveResProjectForAudit(ResProject resProject,String opinion){
		resProject = resProjectDao.save(resProject);
		String auditResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		if (AuditStatusEnum.AUDIT_PASS.equals(resProject.getAuditStatus())) {
			auditResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity("审核招标项目","",opinion,resProject.getObjId(),resProject.getObjId(),"",auditResult);
		return resProject;
	}
}
