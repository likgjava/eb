package com.gpcsoft.epp.project.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.consign.manager.ConsignTaskPlanManager;
import com.gpcsoft.epp.eval.dao.CongruousFactorDao;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.eval.manager.CongruousFactorTypeManager;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.dao.ProjectItemTendDao;
import com.gpcsoft.epp.project.dao.ProjectMTaskPlanDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectCountView;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectItemTend;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlanEnum;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.manager.GenTenderCode;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocAttDao;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.service.RecordFormService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.dao.AttachmentDao;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

@Service 
public class ProjectServiceImpl extends BaseGenericServiceImpl<Project> implements ProjectService {
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanManagerImpl")
	private ConsignTaskPlanManager consignTaskPlanManager;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanManagerImpl")
	private ProjectMTaskPlanManager projectMTaskPlanManager; 
	
	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true) @Qualifier("congruousFactorTypeManagerImpl")
	private CongruousFactorTypeManager congruousFactorTypeManager;
	
	@Autowired(required=true)  @Qualifier("congruousFactorDaoHibernate")
	private CongruousFactorDao congruousFactorDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectPlanManagerImpl")
	private ProjectPlanManager projectPlanManager;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;

	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;

	@Autowired(required=true) @Qualifier("projectMTaskPlanDaoHibernate")
	private ProjectMTaskPlanDao projectMTaskPlanDao; 
	
	@Autowired(required=true) @Qualifier("recordFormServiceImpl")
	private RecordFormService recordFormServiceImpl;
	
	@Autowired(required=true)  @Qualifier("purchaseDocDaoHibernate")
	private PurchaseDocDao purchaseDocDaoHibernate;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true)  @Qualifier("purchaseDocAttDaoHibernate")
	private PurchaseDocAttDao purchaseDocAttDao;
	
	@Autowired(required=true)  @Qualifier("attachmentDaoHibernate")
	private AttachmentDao attachmentDao;
	@Autowired(required=true) @Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("projectItemTendDaoHibernate")
	private ProjectItemTendDao projectItemTendHibernate;
	
	private GenTenderCode genTenderCode; 
	private GenTenderCode getGenTenderCodeImpl(){
		if(this.genTenderCode == null){
			this.genTenderCode = (GenTenderCode)FrameBeanFactory.getBean("genTenderCodeImpl");
		}
		return this.genTenderCode;
	}
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
	public Page<Project> searchProjectForBuyer(QueryObject queryObject,Page<Project> page, User user) {
		logger.debug("\nes ProjectServiceImpl||searchProjectForBuyer\n");
		QueryParam queryParam = new QueryParam("buyerId",QueryParam.OPERATOR_EQ,((OrgInfo)user.getOrgInfo()).getObjId());
		queryObject.getQueryParam().and(queryParam);
		return projectManager.searchProject(queryObject, page);
	}

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
	public Page<Project> searchProjectForAgent(QueryObject queryObject,Page<Project> page, User user) {
		logger.debug("\nes ProjectServiceImpl||searchProjectForAgent\n");
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		QueryParam queryParam = new QueryParam("agencies",QueryParam.OPERATOR_EQ,orgInfo.getObjId());
		queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));//modify by yangx
		queryObject.getQueryParam().and(queryParam);
		return projectManager.searchProject(queryObject, page);
	}

	/** 
	 * FuncName:searchProjectForSupplier
	 * Description :根据查询条件，获得供应商相关的项目
	 * @param queryObject:组装的查询条件,page:分页对象,user:当前用户
	 * @return Page<Project>
	 * @author Administrator
	 * @Create Date: 2010-5-19上午10:10:20 
	 * @Modifier   Administrator
	 * @Modified Date: 2010-5-19上午10:10:20  
	 */
	public Page<Project> searchProjectForSupplier(QueryObject queryObject,Page<Project> page, User user) {
		logger.debug("\nes ProjectServiceImpl||searchProjectForSupplier\n");
		return projectManager.searchProjectByCurUser(queryObject, page, user);
	}

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
	public Project getProjectByTotal(String objId) {
		logger.debug("\nes ProjectServiceImpl||getProjectByTotal\n");
		Project project = projectManager.get(objId);
		ProjectCountView projectCountView = (ProjectCountView)this.get(objId, ProjectCountView.class);
		project.setProjectCountView(projectCountView);
		return project;
	}

	/** 
	 * FuncName:getSubProjectByProjectId
	 * Description:根据项目Id获得项目对应的包组信息
	 * @param   projectId:项目主键
	 * @return List<Project>
	 * @author yangx  
	 * @Create Date: 2010-11-9下午05:13:01 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-9下午05:13:01     
	 */
	public List<Project> getSubProjectByProjectId(String projectId) throws Exception {
		logger.debug("\nes ProjectServiceImpl||getSubProjectByProjectId\n");
		return projectManager.getSubProjectByQueryObject(projectId);
	}

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
	public Project saveProjectAndReq(Project project,String recordCount){
		logger.debug("\nes ProjectServiceImpl||saveProjectAndReq\n");
		projectManager.save(project);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(project.getParentId());
		if(projectRule.getIsDividePack()==false){//如果分包，将项目分包状态改为分包
			projectRule.setIsDividePack(true);
			projectManager.save(projectRule,ProjectRule.class);
		}
		String purCategoryId = "";//品目Ids
		String purCategoryName = "";//品目名称
		String buyersId = "";//采购人Ids
		String buyersName = "";//采购人名称
		if(recordCount!=null&&!"".equals(recordCount)){
			String[] mData = recordCount.split(":");//获取包组与条目中间表数据
			if(mData!=null&&mData.length>0){
				for(int i=0;i<mData.length;i++){
					String[] data = mData[i].split(SeparationEnum.COMMA);//获取每条包组与条目中间表数据
					if(data!=null&&data.length>2){
						String taskPlanSubId = data[0];	//申报书条目Id
						String taskPlanId = data[1];//申报书Id	
						String num = data[2];//数量	
						String money = data[3];//金额	
						TaskPlanSub taskPlanSub = (TaskPlanSub)projectManager.get(taskPlanSubId,TaskPlanSub.class);
						OrgInfo buyer = userApiManager.getOrgInfoByUserId(taskPlanSub.getCreateUser().getObjId());//采购人
						SubProjectMTaskPlanSub projectMTaskPlan = new SubProjectMTaskPlanSub();
						projectMTaskPlan.setProject(project);//标段
						Project tProject = new Project();//招标项目
						tProject.setObjId(project.getParentId());
						projectMTaskPlan.setTproject(tProject);
//						BigDecimal mm =new BigDecimal(money);
						projectMTaskPlan.setMoney(new BigDecimal(money));//金额
//						BigDecimal quantity = mm.divide(taskPlanSub.getTotalPrice().divide(taskPlanSub.getQuantity(),0),0);
						projectMTaskPlan.setQuantity(new BigDecimal(num));//数量
						projectMTaskPlan.setBuyMainBody(buyer);//采购人
						TaskPlan taskPlan = new TaskPlan();//申报书
						taskPlan.setObjId(taskPlanId);
						projectMTaskPlan.setTaskPlan(taskPlan);
						projectMTaskPlan.setTaskPlanSub(taskPlanSub);//申报书条目
						projectManager.save(projectMTaskPlan,SubProjectMTaskPlanSub.class);
						buyersId += buyer.getObjId()+SeparationEnum.COMMA;
						buyersName +=buyer.getOrgName()+SeparationEnum.COMMA;
						purCategoryId += taskPlanSub.getPurchase().getCategoryCode()+SeparationEnum.COMMA;
						purCategoryName += taskPlanSub.getPurchase().getCategoryName()+SeparationEnum.COMMA;
					}
				}
			}
		}
		project.setPurCategoryIds(purCategoryId.substring(0,purCategoryId.lastIndexOf(SeparationEnum.COMMA)));
		project.setPurCategoryNames(purCategoryName.substring(0,purCategoryName.lastIndexOf(SeparationEnum.COMMA)));
		project.setBuyersId(buyersId.substring(0,buyersId.lastIndexOf(SeparationEnum.COMMA)));
		project.setBuyersName(buyersName.substring(0,buyersName.lastIndexOf(SeparationEnum.COMMA)));
		projectManager.save(project);
		Project parentProject = this.getProjectByObjId(project.getParentId());
		parentProject.setUser(AuthenticationHelper.getCurrentUser());
		parentProject.setParentBizId(project.getParentId());
		return parentProject;
	}
	
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
	public void removeProjectAndReq(String projectId){
		logger.debug("\nes ProjectController||toCreateProjectByConsignId\n");
		Project subproject = projectManager.get(projectId);//判断项目是否还有剩余包组
		String  parentId = subproject.getParentId();
		projectManager.remove(projectId,Project.class);//删除标段
		projectManager.removeProjectMTaskPlan(projectId);//删除中间表
		BigDecimal packnum = projectDaoHibernate.getPackNumsByProjectId(parentId);
		if(packnum.intValue()==0){//项目无剩余包组
			ProjectRule projectRule = projectManager.getProjectRuleByProjectId(parentId);
			projectRule.setIsDividePack(false);
			projectManager.save(projectRule,ProjectRule.class);
		}
	}
	
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
	public List getSubProjectMTaskPlanSubByProjectId(String projectId){
		logger.debug("\nes ProjectServiceImpl||getSubProjectMTaskPlanSubByProjectId\n");
		QueryObject<SubProjectMTaskPlanSub> queryObject = new QueryObjectBase<SubProjectMTaskPlanSub>();
		queryObject.setEntityClass(SubProjectMTaskPlanSub.class);
		queryObject.getQueryParam().and(new QueryParam("tproject.objId",QueryParam.OPERATOR_EQ,projectId));
		/** 标段与申报书条目中间对象 */
		return projectManager.getProjectMPreqByProjectId(queryObject);
	}
	
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
	public void updateProjectAndReq(Project project,String recordCount){
		logger.debug("\nes ProjectController||toCreateProjectByConsignId\n");
		projectManager.save(project);
		String purCategoryId = "";//品目Ids
		String purCategoryName = "";//品目名称
		projectManager.removeProjectMTaskPlan(project.getObjId());//先删除中间表数据
		if(recordCount!=""&&recordCount!=null){
			String[] mData = recordCount.split(":");//获取包组与条目中间表数据
			if(mData!=null&&mData.length>0){
				for(int i=0;i<mData.length;i++){
					String[] data = mData[i].split(SeparationEnum.COMMA);//获取每条包组与条目中间表数据
					if(data!=null&&data.length>2){
						String taskPlanSubId = data[0];	//申报书条目Id
						String taskPlanId = data[1];	//申报书Id
						String num = data[2];			//数量
						String money = data[3];			//金额
						TaskPlanSub taskPlanSub = (TaskPlanSub)projectManager.get(taskPlanSubId,TaskPlanSub.class);
						SubProjectMTaskPlanSub projectMTaskPlan = new SubProjectMTaskPlanSub();
						projectMTaskPlan.setProject(project);//标段
						Project tProject = new Project();//招标项目
						tProject.setObjId(project.getParentId());
						projectMTaskPlan.setTproject(tProject);
						projectMTaskPlan.setMoney(new BigDecimal(money));//金额
//						BigDecimal quantity = new BigDecimal(money).divide(taskPlanSub.getTotalPrice().divide(taskPlanSub.getQuantity(),0),0);
						projectMTaskPlan.setQuantity(new BigDecimal(num));//数量
						projectMTaskPlan.setBuyMainBody(userApiManager.getOrgInfoByUserId(taskPlanSub.getCreateUser().getObjId()));//采购人
						TaskPlan taskPlan = new TaskPlan();//申报书
						taskPlan.setObjId(taskPlanId);
						projectMTaskPlan.setTaskPlan(taskPlan);
						projectMTaskPlan.setTaskPlanSub(taskPlanSub);//申报书条目
						projectManager.save(projectMTaskPlan,SubProjectMTaskPlanSub.class);//保存中间表数据
						purCategoryId += taskPlanSub.getPurchase().getCategoryCode()+SeparationEnum.COMMA;
						purCategoryName += taskPlanSub.getPurchase().getCategoryName()+SeparationEnum.COMMA;
					}
				}
			}
		}
		project.setPurCategoryIds(purCategoryId.substring(0,purCategoryId.lastIndexOf(SeparationEnum.COMMA)));
		project.setPurCategoryNames(purCategoryName.substring(0,purCategoryName.lastIndexOf(SeparationEnum.COMMA)));
		projectManager.save(project);
	}

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
	public Page<Project> searchProjectForSupervise(QueryObject queryObject,Page<Project> page, User user) {
		logger.debug("\nes ProjectServiceImpl||searchProjectForSupervise\n");
		return projectManager.searchProject(queryObject, page);
	}

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
	public String getSumTaskPlanDetailProjectId(String projectId){
		logger.debug("\n ProjectServiceImpl||getSumTaskPlanDetailProjectId\n");
		return projectManager.getSumTaskPlanDetailProjectId(projectId);
	}

	/** 
	 * FuncName:saveProjectByTaskPlanSubs
	 * Description :根据选中的申报书条目创建项目
	 * @param project:项目,taskPlanSubIds:申报书条目主键
	 * @return void
	 * @author liuy
	 * @Create Date: 2010-7-15下午03:27:19    
	 * @Modifier liuy
	 * @Modified Date: 2010-7-15下午03:27:19  
	 */
	public void saveProjectByTaskPlanSubs(Project project, String taskPlanSubIds) {
		logger.debug("\nes ProjectServiceImpl||saveProjectByTaskPlanSubs\n");
		this.save(project);
		Set<String> set =new HashSet<String>();
		Set<String> set2 =new HashSet<String>();
		String buyersId = "";//采购人Ids
		String buyersName = "";//采购人名称
		String purCategoryIds = "";//品目Ids
		String purCategoryName = "";//品目名称
		BigDecimal budgetTotalMoney = new BigDecimal("0");//预算总金额
		//保存招标项目与申报书明细的中间表
		QueryObject<ConsignTaskPlan> queryObject = new QueryObjectBase<ConsignTaskPlan>();
		queryObject.setEntityClass(ConsignTaskPlan.class);
		queryObject.getQueryParam().and(new QueryParam("taskPlanSubs",QueryParam.OPERATOR_EQ,taskPlanSubIds));
		List<ConsignTaskPlan> list = consignTaskPlanManager.searchByQueryObject(queryObject);
		TaskPlan taskPlan = null;
		for (int i = 0; i < list.size(); i++) {
			ConsignTaskPlan consignTaskPlan = (ConsignTaskPlan)list.get(i);
			ProjectMTaskPlan projectMTaskPlan = new ProjectMTaskPlan();
			projectMTaskPlan.setTproject(project);
			projectMTaskPlan.setTaskPlanSub(consignTaskPlan.getTaskPlanSub().getObjId());
			projectMTaskPlan.setBuyMainBody(consignTaskPlan.getConsign().getConsBuyer());
			projectMTaskPlan.setPurchaseId(consignTaskPlan.getTaskPlanSub().getPurchase().getObjId());//品目Id
			projectMTaskPlan.setPurchaseName(consignTaskPlan.getTaskPlanSub().getPurchase().getCategoryName());//品目名称
			projectMTaskPlanManager.save(projectMTaskPlan);
			set.add(consignTaskPlan.getConsign().getConsBuyer().getObjId()+SeparationEnum.COMMA);
			set2.add(consignTaskPlan.getConsign().getConsBuyer().getOrgName()+SeparationEnum.COMMA);
			budgetTotalMoney = budgetTotalMoney.add(consignTaskPlan.getTaskPlanSub().getTotalPrice());
			purCategoryIds += consignTaskPlan.getTaskPlanSub().getPurchase().getObjId()+SeparationEnum.COMMA;
			purCategoryName += consignTaskPlan.getTaskPlanSub().getPurchase().getCategoryName()+SeparationEnum.COMMA;
			if (null == taskPlan) {
				taskPlan = consignTaskPlan.getTaskPlan();
			}
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			buyersId +=str; 
		}
		for (Iterator iterator = set2.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			buyersName +=str; 
		}
		// 保存项目监管人
		project.setMonitor(AuthenticationHelper.getCurrentUser().getEmp());
		project.setMonitor(taskPlan.getLeader());
		project.setBuyersId(buyersId.substring(0,buyersId.lastIndexOf(SeparationEnum.COMMA)));
		project.setBuyersName(buyersName.substring(0,buyersName.lastIndexOf(SeparationEnum.COMMA)));
		project.setBudgetTotalMoney(budgetTotalMoney);
		project.setPurCategoryIds(purCategoryIds.substring(0,purCategoryIds.lastIndexOf(SeparationEnum.COMMA)));
		project.setPurCategoryNames(purCategoryName.substring(0,purCategoryName.lastIndexOf(SeparationEnum.COMMA)));
		this.save(project);
		ProjectRule projectRule = new ProjectRule();//项目规则
		projectRule.setProject(project);
		projectRule.setIsDividePack(false);
		this.save(projectRule,ProjectRule.class);//保存项目规则
	}
	
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
	public Project getProjectBySubProjectId(String subProjectId){
		logger.debug("\nes ProjectServiceImpl||getProjectBySubProjectId\n");
		return projectDaoHibernate.getProjectBySubProjectId(subProjectId);
	}
	
	/**
	 * FuncName:createProjectCodeByQO
	 * Description: 生成项目编号,Service层必须开启DB事物
	 * @param queryObject:为扩展参数，暂时不用,projBuyMethod:项目采购方式
	 * @return String
     * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String createProjectCodeByQO(QueryObject queryObject,
			String projBuyMethod) throws EsException {
		logger.debug("\nes ProjectController||createProjectCodeByQO\n");
		String tenderCode = null;
		if(this.getGenTenderCodeImpl() == null){//若没有配置编号生成规则，则使用执行平台默认的
			tenderCode =  projectManager.generatorProjectCodeByQO(queryObject, projBuyMethod);
		}else{//否则使用配置的规则
			tenderCode = this.getGenTenderCodeImpl().getTenderCode(projBuyMethod);
		}
		return tenderCode;
	}

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
	public Project getProjectByObjId(String objId) {
		logger.debug("\n ProjectServiceImpl||getProjectByObjId\n");
		return projectManager.get(objId);
	}

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
	public Project updateProjectTime(ProjectRule projectRule){
		logger.debug("\nes ProjectServiceImpl||updateProjectTime\n");
		ProjectRule projectRuleq = (ProjectRule)projectManager.get(projectRule.getObjId(),ProjectRule.class);
		projectRuleq.setSignUpETime(projectRule.getSignUpETime());
		projectRuleq.setSignUpSTime(projectRule.getSignUpSTime());
		projectRuleq.setSubmitStTime(projectRule.getSubmitStTime());
		projectRuleq.setSubmitETime(projectRule.getSubmitETime());
		projectRuleq.setOpenBidSDate(projectRule.getOpenBidSDate());
		projectManager.save(projectRuleq,ProjectRule.class);
		Project saveProject = projectRuleq.getProject();
		saveProject.setUser(AuthenticationHelper.getCurrentUser());
		saveProject.setParentBizId(saveProject.getObjId());
		return saveProject;
	}
	
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
	public Project updateProjectevalTime(ProjectRule projectRule){
		logger.debug("\nes ProjectServiceImpl||updateProjectevalTime\n");
		projectManager.save(projectRule,ProjectRule.class);
		Project saveProject = projectRule.getProject();
		saveProject.setUser(AuthenticationHelper.getCurrentUser());
		saveProject.setParentBizId(saveProject.getObjId());
		return saveProject;
	}
	
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
	public void removeProject(String projectId){
		logger.debug("\nes ProjectServiceImpl||removeProject\n");
		projectPlanManager.removeProjectPlan(projectId);
		projectDaoHibernate.removeProjectRule(projectId);
		projectDaoHibernate.removeProject(projectId);
	}

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
	public Project saveInputTenderInfo(Project project,JSONArray jsonArray){
		logger.debug("\nes ProjectServiceImpl||saveInputTenderInfo\n");
		Project projectq = null;
		this.saveInputSubTenderInfo(jsonArray);//保存包组
		if (project!=null&&project.getObjId()!=null) {
			projectq = this.get(project.getObjId());//查询项目
			ProjectRule projectRule = projectManager.getProjectRuleByProjectId(project.getObjId());//查询规则
			projectq.setProjName(project.getProjName());
			projectq.setPurDocPrice(project.getPurDocPrice());
			projectq.setContent(project.getContent());
			projectq.setBail(project.getBail());
			projectq.setBailPercent(project.getBailPercent());
			if(project.getProjCode()!=null){
				projectq.setProjCode(project.getProjCode());//项目编号
			}
			//修改项目规则时，同步修改招标文件价格
			String fileType="";
			if(EbuyMethodEnum.INQUIRY.equals(projectq.getEbuyMethod())){//如果采购方式是询价
				fileType = PurchaseDocEnum.INQPDOC;
			}else{ //其他采购方式
				fileType = PurchaseDocEnum.PURCHASEDOC;
			}
			PurchaseDoc  doc =	purchaseDocDaoHibernate.getPurchaseDocByProjectIdAndFileType(project.getObjId(), fileType);
			if(doc!=null){
				doc.setFilePrice((project.getPurDocPrice()!=null)?project.getPurDocPrice().toString():""); //同步招标文件价格
				purchaseDocDaoHibernate.save(doc);
			}
			projectManager.save(projectq);
			if(projectRule==null){
				ProjectRule newProjectRule = new ProjectRule();
				newProjectRule.setProject(projectq);
				newProjectRule.setOpenBidAddr(project.getOpenBidAddr());
				newProjectRule.setOpenBidSDate(project.getOpenBidStartDate());
				newProjectRule.setSignUpETime(project.getSignUpETime());
				newProjectRule.setSignUpSTime(project.getSignUpSTime());
				newProjectRule.setSubmitETime(project.getTenderEndTime());
				newProjectRule.setSubmitStTime(project.getTenderStartTime());
				newProjectRule.setEvalSTime(project.getEvalStartTime());
				newProjectRule.setServiceFeePayType(project.getServiceFeePayType());//服务费支付方式
				newProjectRule.setServiceFeeCalculateType(project.getServiceFeeCalculateType());//服务费计算方式
				newProjectRule.setFixAmount(project.getFixAmount());//固定金额
				newProjectRule.setRuleAnonymous(project.getRuleAnonymous());
				projectManager.save(newProjectRule,ProjectRule.class);
			}else{
				projectRule.setProject(projectq);
				projectRule.setOpenBidAddr(project.getOpenBidAddr());
				projectRule.setOpenBidSDate(project.getOpenBidStartDate());
				projectRule.setSignUpETime(project.getSignUpETime());
				projectRule.setSignUpSTime(project.getSignUpSTime());
				projectRule.setSubmitETime(project.getTenderEndTime());
				projectRule.setSubmitStTime(project.getTenderStartTime());
				projectRule.setEvalSTime(project.getEvalStartTime());
				projectRule.setServiceFeePayType(project.getServiceFeePayType());//服务费支付方式
				projectRule.setServiceFeeCalculateType(project.getServiceFeeCalculateType());//服务费计算方式
				projectRule.setFixAmount(project.getFixAmount());//固定金额
				projectRule.setRuleAnonymous(project.getRuleAnonymous());
				projectManager.save(projectRule,ProjectRule.class);
			}
			projectq.setUser(AuthenticationHelper.getCurrentUser());
			projectq.setParentBizId(projectq.getObjId());
		}
		return projectq;
	}
	
	/** 
	 * FuncName:saveInputSubTenderInfo
	 * Description :  录入标段信息
	 * @param   jsonArray：包组JSON数组
	 * @return  Project
	 * @author 	  yangx
	 * @Create Date: 2010-9-21下午02:53:47 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-21下午02:53:47    
	 */
	private void saveInputSubTenderInfo(JSONArray jsonArray){
		if(null != jsonArray && (!jsonArray.isEmpty())){
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jSONObject = (JSONObject)jsonArray.get(i);
				Project subProject = (Project)JSONObject.toBean(jSONObject,Project.class);
				BigDecimal  bail = subProject.getBail();
				BigDecimal  bailPercent = subProject.getBailPercent();
				BigDecimal  docPrice = subProject.getPurDocPrice();
				if (subProject!=null&&subProject.getObjId()!=null) {
					subProject = this.get(subProject.getObjId());
					subProject.setBail(bail);
					subProject.setBailPercent(bailPercent);
					subProject.setPurDocPrice(docPrice);
					projectManager.save(subProject);
				}
			}
		}
	}
	
	/** 
	 * FuncName:decideScoreIsEqualByProjectId
	 * Description :  一个分类下的指标 分值总和是否等于 指标分类的权重值
	 * @param   projectId:项目主键
	 * @return  boolean
	 * @author yangx
	 * @Create Date: 2010-9-27上午11:33:21 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-27上午11:33:21  
	 */
	public boolean decideScoreIsEqualByProjectId(String projectId){
		boolean returnResult = false;
		List<CongruousFactorType> listCongruousFactorType = congruousFactorTypeManager.getCongruousFactorTypeByProjectId(projectId);
		if (listCongruousFactorType!=null&&listCongruousFactorType.size()>0) {
			for (CongruousFactorType congruousFactorType:listCongruousFactorType) {
				if (congruousFactorType.getWeightCoefficient()!=null&&!Integer.valueOf("0").equals(congruousFactorType.getWeightCoefficient())) {//判断权重是否为0
					Double scoreSum = congruousFactorDaoHibernate.getScoreSum(congruousFactorType.getObjId());
					BigDecimal weightCoefficient = new BigDecimal(congruousFactorType.getWeightCoefficient());
					BigDecimal scoreSumq = new BigDecimal(scoreSum);
					if (weightCoefficient.compareTo(scoreSumq)!=0) {//判断权重是否等于分值总和
						returnResult = true;
					}
				}
			}
		}
		return returnResult; 
	}
	
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
	public Project checkProjectIsComplete(String projectId){
		logger.debug("\n ProjectServiceImpl||checkProjectIsComplete\n");
		Project project = projectManager.get(projectId);//查询项目
		ProjProcessRule projProcessRule = projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECT);//是否分包
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//查询项目规则
		String totalPrice = projectDaoHibernate.getSumSProjectMTSByProjectId(projectId);
		if (projProcessRule!=null&&projProcessRule.getResValue()!=null&&"true".equals(projProcessRule.getResValue())) {//项目规则分包
			if (projectRule.getIsDividePack()&&(project.getBudgetTotalMoney().compareTo(new BigDecimal(totalPrice))==0)) {//判断项目的预算总金额和
				project.setIsDividePack("1");
				project.setUser(AuthenticationHelper.getCurrentUser());
				project.setParentBizId(project.getObjId());
			}else{
				project.setIsDividePack("2");
			}
		}else{//项目规则不分包
			project.setIsDividePack("3");
			project.setUser(AuthenticationHelper.getCurrentUser());
			project.setParentBizId(project.getObjId());
		}
		return project;
	}
	
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
	public Page<Project> getPuaseProjectList(Page page,QueryObject<Project> queryObject) {
		logger.debug("\nes=ProjectServiceImpl||getPuaseProjectList\n");
		return projectDaoHibernate.getPuaseProjectList(page,queryObject);
	}
	
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
	public Page<Project> getPuaseProjectListForAudit(Page page,QueryObject<Project> queryObject){
		logger.debug("\n=ProjectServiceImpl||getPuaseProjectListForAudit\n");
		return projectDaoHibernate.getPuaseProjectListForAudit(page,queryObject);
	}
	
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
	public Page<Project> searchProjectListForSuperviseManager(QueryObject<Project> queryObject,Page<Project> page){
		logger.debug("\nes ProjectServiceImpl||searchProjectListForSuperviseManager\n");
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
	/** 
	 * FuncName:searchProjectListForSupervise
	 * Description :根据监管人获取所有项目列表
	 * @param   queryObject:组装的查询条件对象,page:分页对象,superviseId:监管人主键
	 * @return  Page<Project>
	 * @author     yangx
	 * @Create Date: 2010-10-13下午05:14:18 
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:14:18    
	 */
	public Page<Project> searchProjectListForSupervise(QueryObject<Project> queryObject,Page<Project> page,String superviseId){
		logger.debug("\nes ProjectServiceImpl||searchProjectListForSupervise\n");
		queryObject.getQueryParam().and(new QueryParam("superviseId",QueryParam.OPERATOR_EQ,superviseId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
	/** 
	 * FuncName:searchProjectListForAgentManager
	 * Description :根据代理机构管理员获取所有项目列表
	 * @param   queryObject:组装的查询对象,page:分页对象,agentMId:代理机构Id 
	 * @return   Page<Project>
	 * @author     yangx
	 * @Create Date: 2010-10-13下午05:16:44 
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:16:44     
	 */
	public Page<Project> searchProjectListForAgentManager(QueryObject<Project> queryObject,Page<Project> page,String agentMId){
		logger.debug("\nes ProjectServiceImpl||searchProjectListForAgentManager\n");
		queryObject.getQueryParam().and(new QueryParam("agencies",QueryParam.OPERATOR_EQ,agentMId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
	/** 
	 * FuncName:searchProjectListForAgent
	 * Description:根据代理机构Id获取所有项目列表
	 * @param   queryObject:组装的查询对象,page:分页对象,agentId:代理机构主键
	 * @return Page<Project>
	 * @author yangx  
	 * @Create Date: 2010-10-13下午05:26:46
	 * @Modifier    yangx
	 * @Modified Date: 2010-10-13下午05:26:46    
	 */
	public Page<Project> searchProjectListForAgent(QueryObject<Project> queryObject,Page<Project> page,String agentId){
		logger.debug("\n=ProjectServiceImpl||searchProjectListForAgent\n");
		queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,agentId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
	/** 
	 * FuncName:searchProjectListForBuyer
	 * Description:根据采购人Id获取所有项目列表
	 * @param   queryObject:组装的查询条件,page:分页对象,buyerId:采购人主键
	 * @return  Page<Project>
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:29:07 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-13下午05:29:07   
	 */
	public Page<Project> searchProjectListForBuyer(QueryObject<Project> queryObject,Page<Project> page,String buyerId){
		logger.debug("\n=ProjectServiceImpl||searchProjectListForBuyer\n");
		queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,buyerId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
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
	public Page<Project> searchProjectListForSupply(QueryObject<Project> queryObject,Page<Project> page,String supplyId){
		logger.debug("\n=ProjectServiceImpl||searchProjectListForSupply\n");
		queryObject.getQueryParam().and(new QueryParam("supplyId",QueryParam.OPERATOR_EQ,supplyId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
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
	public Page<Project> searchProjectListForGovernment(QueryObject<Project> queryObject,Page<Project> page,String governmentId){
		logger.debug("\n=ProjectServiceImpl||searchProjectListForSupply\n");
		queryObject.getQueryParam().and(new QueryParam("governmentId",QueryParam.OPERATOR_EQ,governmentId));
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}
	
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
	public ProjectRule getProjectRuleByProjectId(String projectId){
		logger.debug("\n=ProjectServiceImpl||getProjectRuleByProjectId\n");
		return projectManager.getProjectRuleByProjectId(projectId);
	}

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
	public List<Project> getProjectListByQueryObject(QueryObject<Project> queryObject) {
		return projectDaoHibernate.searchAllProject(queryObject);
	}

	/** 
	 * FuncName: getProjectByNameOrCode  
	 * Description : 根据包组名称或编号查询项目下的包组
	 * Create Date: 2011-1-19下午08:43:54 by yangx  Modified Date: 2011-1-19下午08:43:54 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Project> getProjectByNameOrCode(QueryObject<Project> queryObject) {
		return projectDaoHibernate.getProjectByNameOrCode(queryObject);
	}

	
	
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
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-6-22 15:42 
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-6-23 16:20 
	 */
	public void saveECPProjectByTaskPlanSubs(Project project,String taskPlanSubIds) {
		logger.debug("\nes ProjectServiceImpl||saveECPProjectByTaskPlanSubs\n");
		this.save(project);
		Set<String> set =new HashSet<String>();
		Set<String> set2 =new HashSet<String>();
		String buyersId = "";//采购人Ids
		String buyersName = "";//采购人名称
		String purCategoryIds = "";//品目Ids
		String purCategoryName = "";//品目名称
		BigDecimal budgetTotalMoney = new BigDecimal("0");//预算总金额
		//保存招标项目与申报书明细的中间表
		List<TaskPlanMSub> list = taskPlanMSubService.getTaskPlanMSubByTaskPlanSubId(taskPlanSubIds);
		TaskPlan taskPlan = null;
		for (int i = 0; i < list.size(); i++) {
			TaskPlanMSub taskPlanMSub = (TaskPlanMSub)list.get(i);
			ProjectMTaskPlan projectMTaskPlan = new ProjectMTaskPlan();
			projectMTaskPlan.setTproject(project);
			projectMTaskPlan.setTaskPlanSub(taskPlanMSub.getTaskPlanSub().getObjId());
			projectMTaskPlan.setBuyMainBody(taskPlanMSub.getTaskPlan().getBudget());
			projectMTaskPlan.setPurchaseId(taskPlanMSub.getTaskPlanSub().getPurchase().getObjId());//品目Id
			projectMTaskPlan.setPurchaseName(taskPlanMSub.getTaskPlanSub().getPurchase().getCategoryName());//品目名称
			projectMTaskPlan.setBudgetMoney(taskPlanMSub.getTaskPlanSub().getTotalPrice());//预算总金额
			projectMTaskPlan.setQuantity(taskPlanMSub.getTaskPlanSub().getQuantity());//数量
			
			
			/*设置来源类型*/
			if(ProjectEnum.ZFCG.equals(project.getTenderType()))
					projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.CGSBS);
			else if(ProjectEnum.TDJY.equals(project.getTenderType()))
				projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.TDJYDJB);
			else if(ProjectEnum.CQJY.equals(project.getTenderType()))
				projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.CQJYDJB);
			else if(ProjectEnum.JZGC.equals(project.getTenderType()))
				projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.JZLDJB);
			
			projectMTaskPlanManager.save(projectMTaskPlan);
			set.add(taskPlanMSub.getTaskPlan().getBudget().getObjId()+SeparationEnum.COMMA);
			set2.add(taskPlanMSub.getTaskPlan().getBudget().getOrgName()+SeparationEnum.COMMA);
			budgetTotalMoney = budgetTotalMoney.add(taskPlanMSub.getTaskPlanSub().getTotalPrice());
			purCategoryIds += taskPlanMSub.getTaskPlanSub().getPurchase().getObjId()+SeparationEnum.COMMA;
			purCategoryName += taskPlanMSub.getTaskPlanSub().getPurchase().getCategoryName()+SeparationEnum.COMMA;
			if (null == taskPlan) {
				taskPlan = taskPlanMSub.getTaskPlan();
			}
		}
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			buyersId +=str; 
		}
		for (Iterator iterator = set2.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			buyersName +=str; 
		}
		// 保存项目监管人
		//若项目监管人没有，则保存为当前立项人员
		if(taskPlan.getLeader() != null && !"".equals(taskPlan.getLeader())){
			project.setMonitor(taskPlan.getLeader());
		}else{
			project.setMonitor(AuthenticationHelper.getCurrentUser().getEmp());
		}
		project.setBuyersId(buyersId.substring(0,buyersId.lastIndexOf(SeparationEnum.COMMA)));
		project.setBuyersName(buyersName.substring(0,buyersName.lastIndexOf(SeparationEnum.COMMA)));
		project.setBudgetTotalMoney(budgetTotalMoney);
		project.setPurCategoryIds(purCategoryIds.substring(0,purCategoryIds.lastIndexOf(SeparationEnum.COMMA)));
		project.setPurCategoryNames(purCategoryName.substring(0,purCategoryName.lastIndexOf(SeparationEnum.COMMA)));
		this.save(project);
		ProjectRule projectRule = new ProjectRule();//项目规则
		projectRule.setProject(project);
		projectRule.setIsDividePack(false);
		this.save(projectRule,ProjectRule.class);//保存项目规则
		
		
	}
	
	/**
	 * Description :  根据选中的备案书条目创建建筑工程项目
	 * @param project 项目 taskPlanSubIds 备案书条目
	 * @param project
	 * @param taskPlanSubIds void
	 * Created at 2011-6-22 11:04 by zhouzhanghe
	 */
	public void saveECPJZGCProjectByTaskPlanSubs(Project project,
			String taskPlanSubIds) {
		logger.debug("\nes ProjectServiceImpl||saveECPProjectByTaskPlanSubs\n");
		this.save(project);
		BigDecimal budgetTotalMoney = new BigDecimal("0");//预算总金额
		//保存招标项目与申报书明细的中间表
		String[] recordFormIds = taskPlanSubIds.split(SeparationEnum.COMMA);
		for(String objId : recordFormIds){
			RecordForm recordForm = recordFormServiceImpl.get(objId);
			
			ProjectMTaskPlan projectMTaskPlan = new ProjectMTaskPlan();
			
			projectMTaskPlan.setTproject(project);
			projectMTaskPlan.setTaskPlanSub(recordForm.getObjId());
			projectMTaskPlan.setBuyMainBody(recordForm.getRecFormOrgId());
			projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.JZLDJB);
			projectMTaskPlanManager.save(projectMTaskPlan);
			recordForm.setProject(project);
			recordFormServiceImpl.save(recordForm);
			
			project.setBuyersId(recordForm.getRecFormOrgId().getObjId() + SeparationEnum.COMMA + (project.getBuyersId() == null ? "" : project.getBuyersId()));
			project.setBuyersName(recordForm.getRecFormOrgId().getOrgName() + SeparationEnum.COMMA + (project.getBuyersName() == null ? "" : project.getBuyersName()));
		}

		/*去掉结尾逗号*/
		if(project.getBuyersId() != null && project.getBuyersId().endsWith(SeparationEnum.COMMA)){
			project.setBuyersId(project.getBuyersId().substring(0, project.getBuyersId().length() - 1));
		}
		if(project.getBuyersName() != null && project.getBuyersName().endsWith(SeparationEnum.COMMA)){
			project.setBuyersName(project.getBuyersName().substring(0, project.getBuyersName().length() - 1));
		}
		
		project.setMonitor(AuthenticationHelper.getCurrentUser().getEmp());// 保存项目监管人
		project.setBudgetTotalMoney(budgetTotalMoney);
		if (project.getPurCategoryIds()==null||"".equals(project.getPurCategoryIds())) {//如果不存在品目ID
			project.setPurCategoryIds("B");
		}
		if (project.getPurCategoryNames()==null||"".equals(project.getPurCategoryNames())) {//如果不存在品目名称
			project.setPurCategoryNames("工程类");
		}
		this.save(project);
		ProjectRule projectRule = new ProjectRule();//项目规则
		projectRule.setProject(project);
		projectRule.setIsDividePack(false);
		this.save(projectRule,ProjectRule.class);//保存项目规则
		
	}
	
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
	public List getSubProjectMTaskPlanSubBySubProjectId(String projectId){
		logger.debug("\nes ProjectServiceImpl||getSubProjectMTaskPlanSubByProjectId\n");
		QueryObject<SubProjectMTaskPlanSub> queryObject = new QueryObjectBase<SubProjectMTaskPlanSub>();
		queryObject.setEntityClass(SubProjectMTaskPlanSub.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		/** 标段与申报书条目中间对象 */
		return projectManager.getProjectMPreqByProjectId(queryObject);
	}
	
	
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
	public SubProjectMTaskPlanSub getSubProjectMTaskPlanSubByPackId(
			String packId) {
		return projectDaoHibernate.getSubProjectMTaskPlanSubByPackId(packId);
	}
	
	/** 
	 * FuncName:searchProjectList
	 * Description:获取项目负责人项目列表数目
	 * @param  queryObject[ebuyMethod：采购方式,projCode：项目编号,projName：项目名称,buyerId：采购人Id,agencies：代理机构Id,manager项目负责人Id,superviseId：监管机构经办人Id,supplyId：供应商Id,governmentId：业务处室Id]
	 * @param  page:分页对象
	 * @return  Page<Project>
	 * @author shenjz
	 * @Create Date: 2011-4-8上午9:54:23  
	 * @Modifier shenjz
	 * @Modified Date: 2011-4-8上午9:54:23   
	 */
	public Long searchProjectCount(QueryObject<Project> queryObject){
		return projectDaoHibernate.searchProjectCount(queryObject);
	}
	
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
	public Double searchProjectMoney(QueryObject<Project> queryObject){
		return projectMTaskPlanDao.searchProjectMoney(queryObject);
	}

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
	public Project findProjectByProjCodeAndParent(String projCode,
			String parentId) {
		return projectManager.findProjectByProjCodeAndParent(projCode, parentId);
	}
	/**
	 * FuncName: findProjectByProjCode
	 * Description :  通过项目编号查找项目
	 * @param projCode
	 * @return Project
	 * @author: shenjz
	 * @Create Date:2011-6-23  下午04:23:18
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-23  下午04:23:18
	 */
	public Project findProjectByProjCode(String projCode) {
		return projectDaoHibernate.findProjectByProjCode(projCode);
	}
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
	public Long getProjectNumber(String itemsId){
		return projectDaoHibernate.getProjectNumber(itemsId);
	}
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
	public Long getProjectEbuyMethodNumber(String ebuyMethod,String tenderType,String year){
		return projectDaoHibernate.getProjectEbuyMethodNumber(ebuyMethod, tenderType,year);
	}
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
	public Long getProjectEbuyMethodNumber2(String ebuyMethod,String year){
		return projectDaoHibernate.getProjectEbuyMethodNumber2(ebuyMethod,year);
	}
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
	public List<ProjectMTaskPlan> searchProjectMoney2(String date1,String date2){
		return projectMTaskPlanDao.searchProjectMoney2(date1, date2);
	}
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
	public List getProjectPurCategoryNumber(String year){
		return projectDaoHibernate.getProjectPurCategoryNumber(year);
	}
	/** 
	 * FuncName: saveSubProject
	 * Description: 保存建筑工程包组
	 * @param   project:包组 
	 * @return  Project
	 * @author 	  zhouzhanghe
	 * @Create Date: 2011-8-18 17:47 
	 */
	public Project saveSubProject(Project subProject) throws EsException {
		if(subProject.getParentId() == null){
			throw new EsException("保存失败，由于传入的项目主键为空。");
		}
		Project project = get(subProject.getParentId());
		
		//保存标段
		projectManager.save(subProject, Project.class);
		
		//删除原来的标段和备案书中间表
		projectDaoHibernate.removeProjectMTaskPlan(subProject.getObjId());
		
		//保存标段关联备案书
		//TODO 尚未关联备案书
		SubProjectMTaskPlanSub subProjectMTaskPlanSub = new SubProjectMTaskPlanSub();
		subProjectMTaskPlanSub.setProject(subProject);//关联标段
		subProjectMTaskPlanSub.setTproject(project);//关联项目
		subProjectMTaskPlanSub.setMoney(subProject.getBudgetTotalMoney());//金额
		subProjectMTaskPlanSub.setBuyMainBody(orgInfoManager.get(project.getBuyersId()));//采购人
		projectManager.save(subProjectMTaskPlanSub, SubProjectMTaskPlanSub.class);
		
		//更新项目信息
		BigDecimal packnum = projectDaoHibernate.getPackNumsByProjectId(subProject.getParentId());
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(subProject.getParentId());
		if(packnum.intValue()==0)//项目无剩余标段
			projectRule.setIsDividePack(false);
		else
			projectRule.setIsDividePack(true);
		projectManager.save(projectRule,ProjectRule.class);
		
		subProject.setUser(AuthenticationHelper.getCurrentUser());
		subProject.setParentBizId(subProject.getParentId());
		return subProject;
	}
	/** 
	 * FuncName:removeProjectAndReq
	 * Description:删除标段以及中间表
	 * @param   projectId:项目主键
	 * @return void
	 * @author zhouzhanghe  
	 * @Create Date: 2011-8-19 09:52 
	 */
	public void removeSubProject(String subProjectId){
		logger.debug("\nes ProjectController||toCreateProjectByConsignId\n");
		Project subproject = projectManager.get(subProjectId);//判断项目是否还有剩余标段
		String  parentId = subproject.getParentId();
		projectManager.remove(subProjectId, Project.class);//删除标段
		BigDecimal packnum = projectDaoHibernate.getPackNumsByProjectId(parentId);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(parentId);
		if(packnum.intValue()==0)//项目无剩余标段
			projectRule.setIsDividePack(false);
		else
			projectRule.setIsDividePack(true);
		projectManager.save(projectRule,ProjectRule.class);
	}
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
	public Page<Project> searchProjectListForEntryBailRecord(
			QueryObject<Project> queryObject, Page<Project> page) {
		return projectDaoHibernate.searchProjectListForEntryBailRecord(queryObject, page);
	}
	public double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
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
	public PurchaseDoc saveConfirmFinish(String objId,String content,String keyWord) throws Exception{
		Project project = projectManager.get(objId);
		String epp_projectAttaPath = messageSource.getMessage("epp.projectAttaPath");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><appendices>");
		List<PurchaseDocAtt> purchaseDocAttList = purchaseDocAttDao.getPurchaseDocAttList(objId);
		for (Iterator iterator = purchaseDocAttList.iterator(); iterator
				.hasNext();) {
			PurchaseDocAtt purchaseDocAtt = (PurchaseDocAtt) iterator.next();
			List<Attachment> attachmentList =  attachmentManager.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
			Attachment attachment = attachmentList.get(0);
			File file = new File(epp_projectAttaPath+attachment.getPath()+attachment.getFileName());
			if(file.exists()){
				ZipUtils.unZip(epp_projectAttaPath+attachment.getPath()+attachment.getFileName(), epp_projectAttaPath+attachment.getPath());
				Document document = ZipUtils.readXMLFileFromZipFileByfileName(epp_projectAttaPath+attachment.getPath()+attachment.getFileName(), "BiddingAppendix.xml");
				Element elements =  document.getRootElement();
				List<Element> tenderDocumentElements = elements.elements("appendix");
				for (Iterator iterator2 = tenderDocumentElements.iterator(); iterator2
						.hasNext();) {
					Element element = (Element) iterator2.next();
					String name = element.attributeValue("name");
					String docId = element.attributeValue("docId");
					String fileName = element.attributeValue("fileName");
					String type = element.attributeValue("type");
					String packCode = element.attributeValue("packCode");
					xml.append("<appendix name=\""+name+"\" docId=\""+docId+"\" fileName=\""+fileName+"\" packCode=\""+packCode+"\" type=\""+type+"\"/>");
				}
			}
		}
		xml.append("</appendices>");
		FileUtils.writerFile(epp_projectAttaPath+File.separator+objId+File.separator+"purFile"+File.separator+"BiddingAppendix.xml", xml.toString());
		String[] fielNames = FileUtil.listFiles(epp_projectAttaPath+File.separator+objId+File.separator+"purFile"+File.separator); 
		List listFileNames = new ArrayList();
		for (int i = 0; i < fielNames.length; i++) {
			String fielName = fielNames[i];
			if(fielName.indexOf(".")!=-1){
				listFileNames.add(fielName);
			}
		}
		String[]fielNames2 = new String[listFileNames.size()];
		for(int i=0;i<listFileNames.size();i++){
			fielNames2[i] = (String) listFileNames.get(i);
		}
		Attachment attachment = new Attachment();
		attachment.setFileName(UUID.randomUUID().toString());
		attachment.setRelationId(UUID.randomUUID().toString());
		attachment.setPath(File.separator+objId+File.separator+"purFile"+File.separator);
		File file = new File(epp_projectAttaPath+File.separator+objId+File.separator+"purFile"+File.separator+attachment.getFileName());
		ZipUtils.zipFiles(file, epp_projectAttaPath+File.separator+objId+File.separator+"purFile"+File.separator, fielNames2);
		attachment.setViewName(project.getProjName()+"采购文件"+"."+CommonEnum.GPCSOFT_FILE_TYPE);
		attachment = (Attachment) attachmentDao.save(attachment, Attachment.class);
		PurchaseDoc purchaseDoc = purchaseDocDaoHibernate.getPurchaseDocByProjectId(project.getObjId());
		if(purchaseDoc == null){
			purchaseDoc = new PurchaseDoc();
		}
		purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
		purchaseDoc.setProject(project);//关联项目
		purchaseDoc.setAttachRelaId(attachment.getRelationId());//关联附件
		purchaseDoc.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		purchaseDoc.setContent(content);
		purchaseDoc.setKeyWord(keyWord);
		ProjProcessRule projProcessRule= projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//采购办审核
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
		}
		purchaseDoc.setParentBizId(project.getObjId());
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDocDaoHibernate.save(purchaseDoc);
		completedWorkTaskManager.createCompTaskByPassivity("保存招标文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,"",purchaseDoc.getObjId(),purchaseDoc.getObjId(),"","");//保存已办任务
		return purchaseDoc;
	}

	/* Description :
	 * @param   根据条件获取项目列表数据
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-10-14上午09:10:46 by chenhongjun  
	 *  Modified Date: 2011-10-14上午09:10:46 by chenhongjun 
	 * @see com.gpcsoft.epp.project.service.ProjectService#searchProjectList(com.gpcsoft.core.dao.hibernate.query.QueryObject, com.gpcsoft.core.utils.Page)
	 *
	 */
	public Page<Project> searchProjectList(QueryObject<Project> queryObject,
			Page<Project> page) {
		return projectDaoHibernate.searchProjectList(queryObject, page);
	}

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
	public ProjectRule getProjectRuleByTenderId(String tenderId) {
		Project project = get(tenderId);
		ProjectRule projectRule = null;
		if(project == null)
			return null;
		
		/*如果是项目*/
		if(project.getParentId() == null){
			projectRule = getProjectRuleByProjectId(project.getObjId());
		}else{
			projectRule = getProjectRuleByProjectId(project.getObjId());
			/*如果没找到包组规则，则查找项目规则*/
			if(projectRule == null)
				projectRule = getProjectRuleByProjectId(project.getParentId());
		}
		
		return projectRule;
	}


	/** 
	 * FuncName: getProjectImplStatusByProjectId
	 * Description : 根据项目Id获取项目实施状态[00：正常;01:暂停;02:终止;]
	 * @param   projectId：项目Id
	 * @author    yangyc
	 * @Create Date: 2011-11-09 10:02 
	 */
	public String getProjectImplStatusByProjectId(String projectId) {
		// TODO Auto-generated method stub
		return projectDaoHibernate.getProjectImplStatusByProjectId(projectId);
	}
	
	/** 
	 * FuncName : checkProjectBudgetByProjectId
	 * Description :  检查项目拆分预算是否完成
	 * Create Date: 2011-11-9上午11:51:55 by yangx  
	 * Modified Date: 2011-11-9上午11:51:55 by yangx
	 * @param   projectId：项目ID
	 * @return  Boolean
	 * @Exception   
	 */
	public Boolean checkProjectBudgetByProjectId(String projectId) throws Exception{
		Project project = projectManager.get(projectId);//查询项目
		String totalPrice = projectDaoHibernate.getSumSProjectMTSByProjectId(projectId);
		if (project.getBudgetTotalMoney().compareTo(new BigDecimal(totalPrice))==0) {//判断项目的预算总金额和
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * FuncName : finishSubProject
	 * Description :  完成分包
	 * Create Date: 2011-11-9下午01:13:41 by yangx  
	 * Modified Date: 2011-11-9下午01:13:41 by yangx
	 * @param   projectId：项目ID
	 * @Exception   
	 */
	public Project finishSubProject(String projectId) throws Exception{
		Project project = projectManager.get(projectId);//查询项目
		project.setUser(AuthenticationHelper.getCurrentUser(true));
		project.setParentBizId(projectId);
		return project;
	}

	/**
	 * 根据项目ID获得该项目是否存在包组
	 * @param projectId
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-11-10 14:39
	 */
	public boolean getIsExistSubProject(String projectId) {
		return projectDaoHibernate.getSubProjectCountByProjectId(projectId) > 0 ? true : false;
	}

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
	 public SubProjectMTaskPlanSub getTaskPlanSubBySubTenderId(String subTenderId){
		 return  projectDaoHibernate.getTaskPlanSubBySubTenderId(subTenderId);
	 }

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
	public Page<Project> searchProjectListByTenderType(QueryObject<Project> queryObject,Page<Project> page){
		logger.debug("\n=ProjectServiceImpl||searchProjectListByTenderType\n");
		return projectDaoHibernate.searchProjectList(queryObject,page);
	}

	/** 
	 * FuncName : getProjectIdByPurchaseDocId
	 * Description :  根据招标文件Id获取项目
	 * Create Date: 2011-10-20  11:30  by caojz  
	 * Modified Date: 2011-10-20 11:30 by caojz
	 * @param   purchaseId
	 * @return  Project
	 * @Exception   
	 */
	public  Project  getProjectIdByPurchaseDocId(String purchaseId){
		return this.projectDaoHibernate.getProjectIdByPurchaseDocId(purchaseId);
	}

	/**
	 * FuncName : getProjectListByByProjId
	 * Description :  根据projectId获取项目
	 * @param projectId
	 * @return 如果没分包List为项目,如果分包List为包组
	 * @author: zhouzhanghe
	 * @Create Date:2011-10-26 10:01
	 */
	public List<Project> getProjectListByByProjId(String projectId) {
		List returnList = projectDaoHibernate.getSubProjectByProjectId(projectId);
		if(returnList == null || returnList.size() == 0){
			returnList = new ArrayList();
			returnList.add(get(projectId));
		}
		return returnList;
	}
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
	public List<ProjectItemTend> getProjectListByProjectItem(String itemId){
		return projectItemTendHibernate.getProjectListByProjectItem(itemId);
	}
}
