package com.gpcsoft.epp.project.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.RoleTypeEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.NumberUtil;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.domain.ProjectItemTend;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.service.ProjectExceptionApplyService;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.MeetRoomService;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.service.PreqEntryService;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.epp.resproject.service.ResProjectItemService;
import com.gpcsoft.epp.resproject.service.ResProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.service.RecordFormService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.epp.taskplan.service.TaskPlanSubService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.EmployeeService;
import com.gpcsoft.srplatform.auth.service.ExtAttachmentService;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
  * @gpcsoft.view value="projectFormView"
  *  url="view/es/planform/project/projectForm.jsp"
  * @gpcsoft.view value="projectTreeFormView"
  *  url="view/es/planform/project/projectTreeForm.jsp"
  * @gpcsoft.view value="projectListView"
  *  url="view/es/planform/project/projectList.jsp"
  * @gpcsoft.view value="projectDetailView"
  *  url="view/es/planform/project/projectDetail.jsp"
  * @gpcsoft.view value="projectNumSet"
  *  url="view/es/planform/project/number_set.jsp"
  * @gpcsoft.view value="groupMemberFormView"
  *  url="view/es/planform/projrule/groupMemberForm.jsp"
  * @gpcsoft.view value="project"
  *  url="view/es/planform/project/project.jsp"
  * @gpcsoft.view value="project2"
  *  url="view/es/planform/project/project2.jsp"
  * @gpcsoft.view value="projectInfo"
  *  url="view/es/planform/project/projectInfo.jsp"
  * @gpcsoft.view value="toViewProjectInfo"
  *  url="view/es/planform/project/viewProjectInfo.jsp"
  * @gpcsoft.view value="projectCreate"
  *  url="/view/es/planform/project/project_create.jsp"
  * @gpcsoft.view value="projectBagView"
  *  url="/view/es/planform/project/project_bag_View.jsp"
  *  @gpcsoft.view value="projectDepart"
  *  url="/view/es/planform/project/assignDept.jsp"
  *  @gpcsoft.view value="assignDepartDetail"
  *  url="view/es/planform/project/assignDeptDetail.jsp"
  *  @gpcsoft.view value="agentAssign"
  *  url="view/es/planform/project/agentAssign.jsp"
  *  @gpcsoft.view value="agentAssignDetail"
  *  url="view/es/planform/project/agentAssignDetail.jsp" 
  *  @gpcsoft.view value="bidTypeSet"
  *  url="view/es/planform/project/bidTypeSet.jsp" 
  *   @gpcsoft.view value="bidTypeDetail"
  *  url="view/es/planform/project/bidTypeDetail.jsp" 
  *  @gpcsoft.view value="projectInfoForAgent"
  *  url="view/es/planform/project/project_agent.jsp"
  *	 @gpcsoft.view value="projectInfoForSupervise"
  *  url="view/es/planform/project/project_supervise.jsp"
  *  @gpcsoft.view value="projectInfoForBuyer"
  *  url="view/es/planform/project/project_buyer.jsp"
  *  @gpcsoft.view value="projectInfoDljg"
  *  url="view/es/planform/project/projectListDljg.jsp"
  *  @gpcsoft.view value="projectInfoDljgBiXuan"
  *  url="view/es/planform/project/projectListDljgBiXuan.jsp"
  *  @gpcsoft.view value="projectInfoForSupplier"
  *  url="view/es/planform/project/project_supplier.jsp"
  *  @gpcsoft.view value="subprojectCreateOrUpdate"
  *  url="view/es/planform/project/project_create_form.jsp"
  *  @gpcsoft.view value="projectGenViewForAgent"
  *  url="view/es/planform/project/general_view_agent.jsp"
  *  @gpcsoft.view value="subProjectDetailView"
  *  url="view/es/planform/project/subProjectDetailView.jsp"
  *  @gpcsoft.view value="subprojectUpdate"
  *  url="view/es/planform/project/project_update_form.jsp"
  *  @gpcsoft.view value="getSubProjectForSupplier"
  *  url="view/es/planform/project/subProjectView.jsp"
  *  @gpcsoft.view value="craeteProjForTaskPlanSub"
  *  url="view/es/planform/project/craeteProjForTaskPlanSub.jsp"
  *  @gpcsoft.view value="craeteProjForResProject"
  *  url="view/es/planform/project/craeteProjForResProject.jsp"
  *   @gpcsoft.view value="craeteProjForResProject2"
  *  url="view/es/planform/project/craeteProjForResProject2.jsp"
  *  @gpcsoft.view value="createProjForTaskPlanSubForAgent"
  *  url="view/es/planform/project/craeteProjForTaskPlanSubForAgent.jsp"
  *  
  *  @gpcsoft.view value="createProjectFormView"
  *  url="view/es/planform/project/createProject.jsp"
  *  @gpcsoft.view value="agentProjectInfoForward"
  *  url="ProjectController.do?method=toProjectInfoForAgent"
  *  @gpcsoft.view value="buyerProjectInfoForward"
  *  url="ProjectController.do?method=toProjectInfoForBuyer"
  *  @gpcsoft.view value="supplierProjectInfoForward"
  *  url="ProjectController.do?method=toProjectInfoForSupplier"
  *  @gpcsoft.view value="superviseProjectInfoForward"
  *  url="ProjectController.do?method=toProjectInfoForSupervise"
  *  @gpcsoft.view value="toUpdateProjectTime"
  *  url="view/es/planform/project/toUpdateProjectTime.jsp"
  *  @gpcsoft.view value="toUpdateProjectTimeByProjectView"
  *  url="view/es/planform/project/toUpdateProjectTimeByProjectView.jsp"
  *  @gpcsoft.view value="toUpdateProjImplStatus"
  *  url="view/es/planform/project/toUpdateProjImplStatus.jsp"
  *  @gpcsoft.view value="updateProjImplStatus"
  *  url="view/es/planform/project/toUpdateProjImplStatus.jsp"
  *  @gpcsoft.view value="updateProjectEvalTimeView"
  *  url="view/es/planform/project/updateProjectEvalTime.jsp"
  *  @gpcsoft.view value="toModifyProjectInfo"
  *  url="view/es/planform/project/modifyProjForTaskPlanSub.jsp"
  *  @gpcsoft.view value="toInputTenderInfo"
  *  url="view/es/planform/project/projectInfoForm.jsp"
  *  @gpcsoft.view value="toInputTenderInfoDetail"
  *  url="view/es/planform/project/projectInfoDetail.jsp"
  *  @gpcsoft.view value="toInputTenderInfoView"
  *  url="view/es/planform/project/projectInfoView.jsp"
  *  @gpcsoft.view value="toCheckProject"
  *  url="view/es/planform/project/checkProject.jsp"
  *  @gpcsoft.view value="toPuaseProject"
  *  url="view/es/planform/project/puaseProjectForm.jsp"
  *  @gpcsoft.view value="toProjectInfoForTab"
  *  url="view/es/planform/project/projectInfoForTab.jsp" 
  *  @gpcsoft.view value="buyerProjectInfoForwardForTab"
  *  url="ProjectController.do?method=toProjectInfoForBuyerForTab"
  *  @gpcsoft.view value="buyerProjectInfoForTab"
  *  url="view/es/planform/project/project_buyerForTab.jsp"
  *  @gpcsoft.view value="toProjectInfoForSupplierForTab"
  *  url="ProjectController.do?method=toProjectInfoForSupplierForTab"
  *	 @gpcsoft.view value="supplierProjectInfoForTab"
  *  url="view/es/planform/project/project_supplierForTab.jsp"
  *  
  *  @gpcsoft.view value="toCreateProjectByReProjectIetem"
  *  url="view/es/planform/project/craeteProjForResItemProject.jsp"
  *  
  *	 @gpcsoft.view value="projectInfoForTabDetail"
  *  url="view/es/planform/project/projectInfoForTabDetail.jsp"
  *  
  *  @gpcsoft.view value="moreProjectListPage"
  *  url="view/es/planform/project/moreProjectListPage.jsp" 
  *  
  *  @gpcsoft.view value="projectMessagePage"
  *  url="view/es/planform/projrule/projectMessagePage.jsp"
  *  @gpcsoft.view value="printSupplierPage"
  *  url="view/es/planform/project/printSupplierPage.jsp"
  *  @gpcsoft.view value="searchProjectListDljg2"
  *  url="view/es/planform/project/projectListDljg2.jsp"
  *  @gpcsoft.view value="searchProjectListDljgBiXuan2"
  *  url="view/es/planform/project/projectListDljgBiXuan2.jsp"
  *  @gpcsoft.view value="projectOthrAttcInfo"
  *  url="view/es/planform/project/projectOthrAttcInfo.jsp"
  *  @gpcsoft.view value="toPuaseProjectDetail"
  *  url="view/es/planform/project/puaseProjectDetailForm.jsp"
  *  @gpcsoft.view value="epp_projectCreateJzgc"
  *  url="view/es/planform/project/project_create_jzgc.jsp"
  *  @gpcsoft.view value="epp_projectCreateFormJCGC"
  *  url="view/es/planform/project/project_create_form_jzgc.jsp"
  *  @gpcsoft.view value="toPuaseProjectList"
  *  url="view/es/planform/project/toPuaseProjectList.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Project.class,ProjectMTaskPlan.class})
@RequestMapping("/ProjectController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class ProjectController extends AnnotationMultiController<Project> {

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	

	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("taskPlanSubServiceImpl")
	private TaskPlanSubService taskPlanSubService;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("meetRoomServiceImpl")
	private MeetRoomService meetRoomService;	
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required = true) @Qualifier("extAttachmentServiceImpl")
	private ExtAttachmentService extAttachmentServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectExceptionApplyServiceImpl")
	private ProjectExceptionApplyService projectExceptionApplyService;
	
	@Autowired(required=true) @Qualifier("preqEntryServiceImpl")
	private PreqEntryService preqEntryService;
	
	@Autowired(required = true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true) @Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true) @Qualifier("resProjectServiceImpl")
	private ResProjectService resProjectService;
	
	@Autowired(required=true) @Qualifier("resProjectItemServiceImpl")
	private ResProjectItemService resProjectItemService;
	
	@Autowired(required=true) @Qualifier("recordFormServiceImpl")
	private RecordFormService recordFormService;
	
	/** 
	 * Description :  申报书立项（项目经办人/项目经理从申报书立项）
	 * Create Date: 2011-9-20下午03:32:21 by sunl  Modified Date: 2011-9-20下午03:32:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateProject")
	public ModelAndView toCreateProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProject\n");
		
		TaskPlan taskplan = taskPlanService.get(request.getParameter("taskPlanId"));
		
		Map model = new HashMap();
		model.put("projCode", projectService.createProjectCodeByQO(null, taskplan.getEbuyMethod()));
		model.put("ebuyMethod",taskplan.getEbuyMethod());//采购方式
		model.put("ebuyMethodCN",taskplan.getEbuyMethodCN());//采购方式
		model.put("taskType",taskplan.getTaskType());//类型
		model.put("taskTypeCN",taskplan.getTaskTypeCN());//类型
		model.put("agentName",((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getOrgName());//招标单位
		String taskPlanSubIds = "";
		
		List<TaskPlanSub> subs = taskPlanService.getTaskPlanSubByTaskPlan(taskplan.getObjId());
		for (TaskPlanSub taskPlanSub : subs) {
			taskPlanSubIds += taskPlanSub.getObjId() + ",";
		}
		if(taskPlanSubIds.length()>0) {
			taskPlanSubIds = taskPlanSubIds.substring(0,taskPlanSubIds.length()-1);
		}
		
		if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
			model.put("taskPlanSubIds",taskPlanSubIds);
		}else {
			model.put("taskPlanSubIds","-1");
		}
		model.put("taskPlanId",taskplan.getObjId());
		model.put("departmentLinker", taskplan.getDepartmentLinker());//招标项目负责人
		model.put("departmentLinkerTel", taskplan.getDepartmentLinkerTel());//招标项目负责人联系方式
		model.put("budgetLeader", taskplan.getLeader());//业主单位项目负责人
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);
		
		return new ModelAndView("createProjectFormView", model);
	}
	
	/** 
	 * Description :  从申报书立项，创建项目
	 * Create Date: 2011-9-21上午11:59:52 by sunl  Modified Date: 2011-9-21上午11:59:52 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveProjectByTaskPlan")
	public ModelAndView saveProjectByTaskPlan(Project project,String taskPlanSubIds,String signUpCondFactorStr, HttpServletRequest request, SessionStatus status)throws Exception{
		logger.debug("\nes=ProjectController||saveProjectByTaskPlanSubs\n");
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    
	    //更新项目经理联系方式
	    if(StringUtils.hasLength(project.getManager().getMobile())) {
	    	Employee manager = employeeService.get(project.getManager().getObjId());
	    	manager.setMobile(project.getManager().getMobile());
	    	employeeService.save(manager);
	    }
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(CommonEnum._SYS_FLAG);//添加系统标示 modify by yangx
	    
	    //此次立项的需求条目ID,以创建项目任务书条目中间表数据
	    String projSubIds = request.getParameter("projSubIds");
	    if(StringUtils.hasLength(projSubIds)) {
	    	projSubIds = projSubIds.substring(0,projSubIds.length()-1);
	    }
	    projectService.saveECPProjectByTaskPlanSubs(project, projSubIds);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/**
	 * FuncName: toCreateProjectByConsignId
	 * Description :  根据项目Id创建比选项目 
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:28:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:28:33
	 */
	@RequestMapping(params="method=toCreateProjectByReProjectId")
	public ModelAndView toCreateProjectByReProjectId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectByConsignId\n");
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		ResProject resProject = resProjectService.get(objId);
		model.put("resProjectId", objId);
		model.put("resProject", resProject);
		return new ModelAndView("craeteProjForResProject", model);
	}
	/**
	 * FuncName: toCreateProjectByConsignId
	 * Description :  根据项目Id创建比选项目 
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:28:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:28:33
	 */
	@RequestMapping(params="method=toCreateProjectByReProjectId2")
	public ModelAndView toCreateProjectByReProjectId2(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectByConsignId\n");
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		ResProject resProject = resProjectService.get(objId);
		model.put("projCode", projectService.createProjectCodeByQO(null, "00"));
		model.put("resProjectId", objId);
		model.put("resProject", resProject);
		
		User user = AuthenticationHelper.getCurrentUser();//获得当前人所在部门的所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(user.getEmp().getCompany().getObjId());
		int j = 0;
		for(int i=0;i<empList.size();i++){
			if(empList.get(i).getObjId().equals(user.getEmp().getObjId())){
				j=i;
			}
		}
		if(j!=0){//用以将当前登录人的信息放在集合第一位
			empList.set(j, empList.get(0));
			empList.set(0, user.getEmp());
		}
		model.put("empList",empList);
		
		
		return new ModelAndView("craeteProjForResProject2", model);
	}
	/**
	 * FuncName: toCreateProjectByConsignId
	 * Description :  根据项目条目Id分标段
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:28:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:28:33
	 */
	@RequestMapping(params="method=toCreateProjectByReProjectIetem")
	public ModelAndView toCreateProjectByReProjectIetem(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectByConsignId\n");
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		ResProjectItem resProjectItem =  resProjectItemService.get(objId);
		model.put("resProjectItem", resProjectItem);
		List<ProjectItemTend> projectItemTendList = projectService.getProjectListByProjectItem(objId);
		model.put("projectItemTendList", projectItemTendList);
		model.put("projCode", projectService.createProjectCodeByQO(null, "00"));
		return new ModelAndView("toCreateProjectByReProjectIetem", model);
	}
	/**
	 * FuncName: toCreateProjectByConsignId
	 * Description :  根据项目条目Id分标段
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:28:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:28:33
	 */
	@RequestMapping(params="method=toCreateProjectByReProjectIetem2")
	public ModelAndView toCreateProjectByReProjectIetem2(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectByConsignId\n");
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		ResProjectItem resProjectItem =  resProjectItemService.get(objId);
		model.put("resProjectItem", resProjectItem);
		model.put("projCode", projectService.createProjectCodeByQO(null, "00"));
		return new ModelAndView("toCreateProjectByReProjectIetem2", model);
	}
	/**
	 * FuncName: saveProjectByTaskPlan
	 * Description : 项目申报书立项创建比选项目
	 * @param 
	 * @param project
	 * @param taskPlanSubIds
	 * @param signUpCondFactorStr
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:49:46
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:49:46
	 */
	@RequestMapping(params = "method=saveProjectByResProject")
	public ModelAndView saveProjectByResProject(Project project, HttpServletRequest request, SessionStatus status)throws Exception{
		logger.debug("\nes=ProjectController||saveProjectByTaskPlanSubs\n");
		ResProject res = resProjectService.get(request.getParameter("resProjectId"));
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setBuyersId(res.getBudget().getObjId());
	    project.setBuyersName(res.getBudgetName());
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());//添加系统标示 modify by yangx
	    project.setTenderType(ProjectEnum.DLBX);
	    project.setResProjectId(res.getObjId());
	    projectService.save(project);
	    ProjectRule pr = new ProjectRule();
	    pr.setProject(project);
	    projectService.save(pr, ProjectRule.class);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: saveProjectByTaskPlan
	 * Description : 项目申报书立项创建分标段项目
	 * @param 
	 * @param project
	 * @param taskPlanSubIds
	 * @param signUpCondFactorStr
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-21  下午09:49:46
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-21  下午09:49:46
	 */
	@RequestMapping(params = "method=saveProjectByResProjectItem")
	public ModelAndView saveProjectByResProjectItem(Project project, HttpServletRequest request, SessionStatus status)throws Exception{
		logger.debug("\nes=ProjectController||saveProjectByTaskPlanSubs\n");
		logger.debug(request.getParameter("resProjecItemId"));
		ResProjectItem resProjectItem = resProjectItemService.get(request.getParameter("resProjecItemId"));
		project.setAgencies(resProjectItem.getResProject().getAgenty());
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());//添加系统标示 modify by yangx
	    project.setTenderType(ProjectEnum.JZGC);
	    project.setEbuyMethod(resProjectItem.getEbuyMethod());
	    project.setResProjectId(resProjectItem.getResProject().getObjId());
	    ResProject resProject = resProjectItem.getResProject();
	    RecordForm recordForm = recordFormService.get(resProject.getRecordForm().getObjId());
	    OrgInfo orginfo = userApiService.getOrgInfoByUser(recordForm.getCreateUser());
	    project.setBuyersId(orginfo.getObjId());
	    project.setBuyersName(orginfo.getOrgName());
	    projectService.save(project);
	    ProjectItemTend pt = new ProjectItemTend();
	    pt.setProject(project);
	    pt.setResProjectItem(resProjectItem);
	    projectService.save(pt, ProjectItemTend.class);
	    ProjectRule pr = new ProjectRule();
	    pr.setProject(project);
	    projectService.save(pr, ProjectRule.class);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/** 
	 * FuncName:toCreateProjectByConsignId
	 * Description:根据委托协议ID创建招标项目
	 * @param request
	 * @return ModelAndView
	 * @author liuy
	 * @Create Date: 2010-7-15上午09:41:38 
	 * @Modifier  liuy
	 * @Modified Date: 2010-7-15上午09:41:38  
	 */
	@RequestMapping(params="method=toCreateProjectByConsignId")
	public ModelAndView toCreateProjectByConsignId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectByConsignId\n");
		String ebuyMethodCN = URLDecoder.decode(request.getParameter("ebuyMethodCN"), "UTF-8");
		String taskTypeCN = URLDecoder.decode(request.getParameter("taskTypeCN"), "UTF-8");
		Map model = new HashMap();
		model.put("projCode", projectService.createProjectCodeByQO(null, request.getParameter("ebuyMethod")));
		model.put("ebuyMethod",request.getParameter("ebuyMethod"));//采购方式
		model.put("ebuyMethodCN",ebuyMethodCN);//采购方式
		model.put("taskType",request.getParameter("taskType"));//类型
		model.put("taskTypeCN",taskTypeCN);//类型
		String taskPlanSubIds = request.getParameter("taskPlanSubIds");
		if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
			model.put("taskPlanSubIds",taskPlanSubIds);
		}else {
			model.put("taskPlanSubIds","-1");
		}
		User user = AuthenticationHelper.getCurrentUser();//获得当前人所在部门的所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(user.getEmp().getCompany().getObjId());
		model.put("empList",empList);
		return new ModelAndView("craeteProjForTaskPlanSub", model);
	}
	
	/** 
	 * Description :  代理机构立项
	 * Create Date: 2011-9-22下午07:51:37 by sunl  Modified Date: 2011-9-22下午07:51:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateProjectForAgent")
	public ModelAndView toCreateProjectForAgent(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toCreateProjectForAgent\n");
		TaskPlan taskplan = taskPlanService.get(request.getParameter("taskPlanId"));
		Map model = new HashMap();
		model.put("projCode", projectService.createProjectCodeByQO(null, taskplan.getEbuyMethod()));
		model.put("ebuyMethod",taskplan.getEbuyMethod());//采购方式
		model.put("ebuyMethodCN",taskplan.getEbuyMethodCN());//采购方式
		model.put("taskType",taskplan.getTaskType());//类型
		model.put("taskTypeCN",taskplan.getTaskTypeCN());//类型
		model.put("currentYear", Calendar.YEAR);//当前年去两位
		model.put("agentName",((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getOrgName());//招标单位
		String taskPlanSubIds = "";
		
		List<TaskPlanSub> subs = taskPlanService.getTaskPlanSubByTaskPlan(taskplan.getObjId());
		for (TaskPlanSub taskPlanSub : subs) {
			taskPlanSubIds += taskPlanSub.getObjId() + ",";
		}
		if(taskPlanSubIds.length()>0) {
			taskPlanSubIds = taskPlanSubIds.substring(0,taskPlanSubIds.length()-1);
		}
		
		if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
			model.put("taskPlanSubIds",taskPlanSubIds);
		}else {
			model.put("taskPlanSubIds","-1");
		}
		
		model.put("taskPlanId",taskplan.getObjId());
		model.put("departmentLinker", taskplan.getDepartmentLinker());//招标项目负责人
		model.put("departmentLinkerTel", taskplan.getDepartmentLinkerTel());//招标项目负责人联系方式
		model.put("budgetLeader", taskplan.getLeader());//业主单位项目负责人
		model.put("budgetName", taskplan.getBudgetName());//业主单位项目负责人
		
		model.put("taskPlan",taskplan );
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);
		
		return new ModelAndView("createProjForTaskPlanSubForAgent", model);
	}
	
	@Override
	protected Map<String, String> getEnumColumns() {
		Map map = new HashMap();
		map.put("ebuyMethod", EbuyMethodEnum.EBUY_METHOD);
		return map;
	}
	
	/**
	 * FuncName:searchProjectForSupervise
	 * Description:根据角色查询项目列表
	 * @param   request
	 * @return ModelAndView
	 * @author guom  
	 * @Create Date: 2010-5-12上午15:27:35 
	 * @Modifier  guom
	 * @Modified Date: 2010-5-12上午15:27:35 
	 */
	@RequestMapping(params="method=searchProjectForSupervise")
	public ModelAndView searchProjectForSupervise(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||searchProjectForSupervise\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		String curRoleType = this.checkRole(null,request);
		User user=AuthenticationHelper.getCurrentUser();
		if(RoleTypeEnum.ECP_AGENCY.equals(curRoleType)){
			page = projectService.searchProjectForAgent(queryObject, page, user);
		}else if(RoleTypeEnum.ECP_BUYER.equals(curRoleType)){
			page = projectService.searchProjectForBuyer(queryObject, page, user);
		}else if(RoleTypeEnum.ECP_SUPPLIER.equals(curRoleType)){
			page = projectService.searchProjectForSupplier(queryObject, page, user);
		}else if(RoleTypeEnum.ECP_SUPERVISE.equals(curRoleType)){
			page = projectService.searchProjectForSupervise(queryObject, page, user);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:saveProjectMonitor
	 * Description :  保存监管人
	 * @param request,status
	 * @return ModelAndView
	 * @author liuy
	 * @Create Date: 2010-7-17下午03:16:14 
	 * @Modifier  liuy
	 * @Modified Date: 2010-7-17下午03:16:14 
	 */
	@RequestMapping(params = "method=saveProjectMonitor")
	public ModelAndView saveProjectMonitor(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||saveProjectMonitor\n");
		Project project = projectService.get(request.getParameter("projectId"));
		String monitorId = request.getParameter("monitorId");
		Employee monitor = new Employee();
		monitor.setObjId(monitorId);
		project.setMonitor(monitor);
		projectService.save(project);
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
		
	/** 
	 * FuncName:saveProjectMonitor
	 * Description :  保存经办人
	 * @param request,status
	 * @return ModelAndView
	 * @author liuy
	 * @Create Date: 2010-7-17下午03:16:14 
	 * @Modifier  liuy
	 * @Modified Date: 2010-7-17下午03:16:14 
	 */
	@RequestMapping(params = "method=saveProjectMamanger")
	public ModelAndView saveProjectMamanger(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||saveProjectMonitor\n");
		Project project = projectService.get(request.getParameter("projectId"));
		String monitorId = request.getParameter("managerId");
		Employee maanger = new Employee();
		maanger.setObjId(monitorId);
		project.setManager(maanger);
		projectService.save(project);
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FucnName:toProjectInfo
	 * Description : 跳转项目信息详细显示页面[根据角色不同跳转不同请求] 
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-14下午01:23:57 
	 * @Modifier liuke
	 * @Modified Date: 2010-5-14下午01:23:57 
	 */
	@RequestMapping(params="method=toProjectInfo")
	public ModelAndView toProjectInfo(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectInfo\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(objId);
		Object[] situation = projectPlanService.getSituationByProject(objId);
		model.put("process",situation[2]);
		model.put("project", project);
		model.put("projectRule", projectRule);
		String curRoleType = this.checkRole(project,request);
		String returnUrl = "";
		if(RoleTypeEnum.ECP_AGENCY.equals(curRoleType)){
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfo";
		}else if(RoleTypeEnum.ECP_BUYER.equals(curRoleType)){
			returnUrl = "buyerProjectInfoForward";
			if(request.getParameter("toProjectInfoForBuyer")!= null){
				returnUrl = "projectInfo";
			}
		}else if(RoleTypeEnum.ECP_SUPPLIER.equals(curRoleType)){
			returnUrl = "supplierProjectInfoForward";
		}else if(RoleTypeEnum.ECP_SUPERVISE.equals(curRoleType)){
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfo";
		}else{
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfo";
		}
		return new ModelAndView(returnUrl, model);
	}
	
	
	/** 
	 * FuncName : toViewProjectInfo
	 * Description :  跳转到查看项目信息页面
	 * Create Date: 2011-11-16上午10:01:05 by yangx  
	 * Modified Date: 2011-11-16上午10:01:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewProjectInfo")
	public ModelAndView toViewProjectInfo(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toViewProjectInfo\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(objId);
		Object[] situation = projectPlanService.getSituationByProject(objId);
		model.put("process",situation[2]);
		model.put("project", project);
		model.put("projectRule", projectRule);
		return new ModelAndView("toViewProjectInfo", model);
	}
	
	/** 
	 * FuncName:toProjectInfoForTab
	 * Description : 跳转项目信息详细显示页面[根据角色不同跳转不同请求] 
	 * @param request,status
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-17下午03:16:14 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-17下午03:16:14 by 
	 */
	@RequestMapping(params="method=toProjectInfoForTab")
	public ModelAndView toProjectInfoForTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectInfoForTab\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		model.put("project", project);
		String returnUrl = "toProjectInfoForTab";
		return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * FuncName:toProjectCraete
	 * Description :  到项目信息显示页面[需要项目的包组信息
	 * @param request
	 * @return ModelAndView
	 * @author Administrator
	 * @Create Date: 2010-5-21下午02:22:54 
	 * @Modifier:Administrator
	 * @Modified Date: 2010-5-21下午02:22:54 
	 * @Modifier: zhouzhanghe
	 * @Modified Date: 2011-8-19 16:27 
	 */
	@RequestMapping(params="method=toProjectCraete")
	public ModelAndView toProjectCraete(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||toProjectCraete\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(projectId); //获取中间表数据
		List<Project> subProjects = projectService.getSubProjectByProjectId(projectId);  //获取项目对应的包组信息
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		if (project!=null) {//根据报名开始时间判断是否可以对做分包组进行新增、修改和删除
			if (projectRule.getSignUpSTime()!=null&&(projectRule.getSignUpSTime()).before(new Date())) {//判断是否有报名时间且报名时间大于今天
				model.put("isStart","yes");//设置标志位不能进行新增、修改和删除操作
			}
		}
		String role = checkRole(project,request);
		model.put("parentId", projectId);
		model.put("project", project);
		model.put("role", role);
		status.setComplete();//因页面操作需要，这个请求到达页面前需要清空session
		if(ProjectEnum.JZGC.equals(project.getTenderType())){		//如果是“建筑工程”
			model.put("subProjectList", subProjectList);
			model.put("subProjects",subProjects);
			return new ModelAndView("epp_projectCreateJzgc", model);
		}else{
			double quantity = 0.0;
			double money =0.00;
			for(Project proj:subProjects){
			  List <SubProjectMTaskPlanSub> subProjectMList = projectService.getSubProjectMTaskPlanSubBySubProjectId(proj.getObjId());
			  quantity=0.0;
			  money=0.00;
			  for(int j=0;j<subProjectMList.size();j++){
				quantity = projectService.add(quantity,new Double(subProjectMList.get(j).getQuantity().toString()));
				money = projectService.add(money,new Double(subProjectMList.get(j).getMoney().toString()));
			  }
			    proj.setRealCount(new BigDecimal(quantity)); //数量
				proj.setRealMoney(new BigDecimal(money));    //金额
			}
			model.put("subProjectList", subProjectList);
			model.put("subProjects",subProjects);
			return new ModelAndView("projectCreate", model);
		}
	}
	
	/** 
	 * FuncName:toSubProjectCraete
	 * Description:到包组新增页面[采购明细]
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-30上午10:34:31 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-30上午10:34:31  
	 */
	@RequestMapping(params="method=toSubProjectCraete")
	public ModelAndView toSubProjectCraete(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toSubProjectCraete\n");
		String parentId = request.getParameter("parentId");
		Project project = projectService.getProjectByObjId(parentId);// 判断是否可以结束桌面待办任务
		Boolean isEndWorkTask = false;
		if (null == project.getSubProject() ||  project.getSubProject().isEmpty()) {
			isEndWorkTask = true;
		}
		Map model = new HashMap();
		model.put("parentId", parentId);
		model.put("isEndWorkTask", isEndWorkTask);
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanSubByTaskPlanId(parentId);
		model.put("taskPlanMSubList",taskPlanMSubList);
		return new ModelAndView("subprojectCreateOrUpdate", model);
	}
	
	/** 
	 * FuncName:toSubProjectUpdate
	 * Description:到包组修改页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-25上午11:23:06
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-25上午11:23:06    
	 */
	@RequestMapping(params="method=toSubProjectUpdate")
	public ModelAndView toSubProjectUpdate(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toSubProjectUpdate\n");
		String subProjId = request.getParameter("subProjId");
		Map model = new HashMap();
		if(null != subProjId && !"".equals(subProjId)){
			Project subProject = projectService.get(subProjId);
			List projectMTaskPlanList = projectService.getSubProjectMTaskPlanSubBySubProjectId(subProjId);
			List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanSubByTaskPlanId(subProject.getParentId());
			model.put("taskPlanMSubList",taskPlanMSubList);
			if(projectMTaskPlanList!=null&&projectMTaskPlanList.size()>0){
				for(int i=0;i<projectMTaskPlanList.size();i++){// 循环包组与申报书条目中间对象
					SubProjectMTaskPlanSub spmt = (SubProjectMTaskPlanSub)projectMTaskPlanList.get(i);
					for(int j=0;j<taskPlanMSubList.size();j++){//循环申报书与申报书条目中间对象
						TaskPlanMSub taskPlanMSub = (TaskPlanMSub)taskPlanMSubList.get(j);
						if(spmt.getTaskPlanSub()!=null&&taskPlanMSub.getTaskPlanSub()!=null&&(spmt.getTaskPlanSub().getObjId()).equals(taskPlanMSub.getTaskPlanSub().getObjId())){
							taskPlanMSub.setMoney(spmt.getMoney());//如果是同一条目设置显示金额
							taskPlanMSub.setQuantity(spmt.getQuantity());//如果是同一条目设置显示数量
							model.put("purchaseId",taskPlanMSub.getTaskPlanSub().getPurchase().getObjId());
						}
					}
				}
			}
			model.put("subProject", subProject);
			model.put("projectMTaskPlan", projectMTaskPlanList);
			model.put("parentId", subProject.getParentId());
		}
		return new ModelAndView("subprojectUpdate", model);
	}
	
	/**
	 * FuncName:toProjectInfoForBuyer
	 * Description:代理机构跳转项目信息详细显示页面 
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-14下午01:23:57 
	 * @Modifier  liuke
	 * @Modified Date: 2010-5-14下午01:23:57 
	 */
	@RequestMapping(params="method=toProjectInfoForBuyer")
	public ModelAndView toProjectInfoForBuyer(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ProjectController||toProjectInfoForBuyer\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		List subProjectList = projectService.getSubProjectByProjectId(objId);
		model.put("project", project);
		model.put("subProjectList", subProjectList);
		model.put("subProjectListSize", subProjectList.size());
		return new ModelAndView("projectInfoForBuyer", model);
	}

	/**
	 * FuncName: toProjectInfoForBuyerForTab
	 * Description : 跳转到采购人tab页
	 * @param request,status
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2010-12-24  上午10:05:09
	 * @Modifier: liuke
	 * @Modified Date:2010-12-24  上午10:05:09
	 */
	@RequestMapping(params="method=toProjectInfoForBuyerForTab")
	public ModelAndView toProjectInfoForBuyerForTab(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ProjectController||toProjectInfoForBuyerForTab\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		List subProjectList = projectService.getSubProjectByProjectId(objId);
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		model.put("orginfo", org);
		model.put("project", project);
		model.put("subProjectList", subProjectList);
		return new ModelAndView("buyerProjectInfoForTab", model);
	}
	
	/**
	 * FucnName:searchProjectListDljg
	 * Description:查询显示代理项目列表（不通过控件生成，所以不需要分页）
	 * @param    request
	 * @return  ModelAndView
	 * @author guom
	 * @Create Date: 2010-6-7下午4:23:57 
	 * @Modifier  guom
	 * @Modified Date: 2010-6-7下午4:23:57 
	 */
	@RequestMapping(params="method=searchProjectListDljg")
	public ModelAndView searchProjectListDljg(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||searchProjectListDljg\n");
		String serviceName=request.getParameter("serviceName");
		String projProcessStatus = request.getParameter("projProcessStatus");//项目状态
		String taskType=request.getParameter("taskType");//任务书类型
		String taskPlanId=request.getParameter("taskPlanId");
		String resProjectId=request.getParameter("resProjectId");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 if(!ProjectEnum.DLBX.equals(mc.getCode())){
				 if(taskType == null){
					 messageList.add(mc);
					 }else if(taskType.equals(mc.getCode())){
						 messageList.add(mc); 
					 }
			 }
			
		}
		/*Added at 2011-5-26 by zhouzhanghe，对messageList排序*/
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		
		/*Added at 2011-5-30 by zhouzhanghe，放入页面上显示的采购方式*/
		List ebuyMethodList = null;
		if(taskType == null){
			ebuyMethodList = EbuyMethodEnum.getAllEbuyMethod();
		 }else{
			 ebuyMethodList = EbuyMethodEnum.getEbuyMethodByTaskType(taskType);
		 }
		model.put("taskPlanId", taskPlanId);
		model.put("resProjectId", resProjectId);
		model.put("ebuyMethodList",ebuyMethodList);
		model.put("projProcessStatus", projProcessStatus);
		model.put("messageList", messageList);
		model.put("serviceName", serviceName);
		return new ModelAndView("projectInfoDljg", model);
	}
	/**
	 * FuncName: searchProjectListDljgBiXuan
	 * Description :  代理机构笔选(初始进入页)
	 * @param 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-20  上午10:41:43
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-20  上午10:41:43
	 */
	@RequestMapping(params="method=searchProjectListDljgBiXuan")
	public ModelAndView searchProjectListDljgBiXuan(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||searchProjectListDljg\n");
		String serviceName=request.getParameter("serviceName");
		String projProcessStatus = request.getParameter("projProcessStatus");//项目状态
		String taskType=request.getParameter("taskType");//任务书类型
		String taskPlanId=request.getParameter("taskPlanId");
		String resProjectId=request.getParameter("resProjectId");
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 if(ProjectEnum.DLBX.equals(mc.getCode())){
				 if(taskType == null){
					 messageList.add(mc);
				 }else if(taskType.equals(mc.getCode())){
					 messageList.add(mc); 
				 }
			 }
		}
		/*Added at 2011-5-26 by zhouzhanghe，对messageList排序*/
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		/*Added at 2011-5-30 by zhouzhanghe，放入页面上显示的采购方式*/
		List ebuyMethodList = null;
		if(taskType == null){
			ebuyMethodList = EbuyMethodEnum.getAllEbuyMethod();
		 }else{
			 ebuyMethodList = EbuyMethodEnum.getEbuyMethodByTaskType(taskType);
		 }
		model.put("taskPlanId", taskPlanId);
		model.put("resProjectId", resProjectId);
		model.put("ebuyMethodList",ebuyMethodList);
		model.put("projProcessStatus", projProcessStatus);
		model.put("messageList", messageList);
		model.put("serviceName", serviceName);
		return new ModelAndView("projectInfoDljgBiXuan", model);
	}
	
	/**
	 * FuncName: searchProjectListDljgBiXuan2
	 * Description :  代理机构笔选(ajax异步加载页)
	 * @param 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-20  上午10:41:17
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-20  上午10:41:17
	 */
	@RequestMapping(params="method=searchProjectListDljgBiXuan2")
	public ModelAndView searchProjectListDljgBiXuan2(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||searchProjectListDljg\n");
		String serviceName=request.getParameter("serviceName");
		String pageNum=request.getParameter(Page.PAGE_NAME);
		String rp=request.getParameter(Page.PAGE_SIZE_NAME);
		Page page=new Page();
		if(null != pageNum && "0".equals(pageNum)){
			pageNum = "1";
		}
		int curpage = Integer.parseInt(pageNum == null ? "1":pageNum);		//得到当前页数   
		int limit = Integer.parseInt(rp == null ? "5":rp);					//得到当前页大小  
		int start = (curpage==1?0:Page.getStartOfPage(curpage, limit));
		page.setStart(start);
		page.setPageSize(limit);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		User user=AuthenticationHelper.getCurrentUser();
		if (serviceName!=null&&"searchProjectListForAgent".equals(serviceName)) {//代理机构项目负责人
			page = projectService.searchProjectListForAgent(queryObject, page, user.getEmp().getObjId());
		} else if (serviceName!=null&&"searchProjectListForAgentManager".equals(serviceName)) {//代理机构管理员
			page = projectService.searchProjectListForAgentManager(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForSupervise".equals(serviceName)) {//监管单位经办人
			page = projectService.searchProjectListForSupervise(queryObject, page, user.getEmp().getObjId());
		} else if (serviceName!=null&&"searchProjectListForSuperviseManager".equals(serviceName)) {//监管单位管理员
			page = projectService.searchProjectListForSuperviseManager(queryObject, page);
		} else if (serviceName!=null&&"searchProjectListForBuyer".equals(serviceName)) {//采购人
			page = projectService.searchProjectListForBuyer(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForSupply".equals(serviceName)) {//供应商
			page = projectService.searchProjectListForSupply(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForGovernment".equals(serviceName)) {//业务处室经办人
			Department department = userApiService.getDepartmentByUserId(user.getObjId());
			page = projectService.searchProjectListForGovernment(queryObject, page, department.getObjId());
		}
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=searchProjectListDljg2&serviceName="+serviceName);//设置分页对应的请求地址
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		model.put("PAGERESULT", page);
		String cols = "projCode,projName,ebuyMethod,tenderType";// cols 指定的返回请求参数
		Map<String,Object> map = request.getParameterMap();
		for(String key:cols.split(",")){
			for(Map.Entry entry:map.entrySet()){
				if(key.equals(entry.getKey())){
					model.put((String)entry.getKey(), request.getParameter((String)entry.getKey()));
				}
			}
		}
		return new ModelAndView("searchProjectListDljgBiXuan2", model);
	}
	
	/** 
	 * FuncName:toProjectInfoForSupplier
	 * Description :  供应商跳转项目信息详细显示页面 
	 * @param   request,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-25下午04:02:46   
	 * @Modifier yangx
	 * @Modified Date: 2010-6-25下午04:02:46   
	 */
	@RequestMapping(params="method=toProjectInfoForSupplier")
	public ModelAndView toProjectInfoForSupplier(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ProjectController||toProjectInfoForSupplier\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(objId);
		List subProjectList = projectService.getSubProjectByProjectId(objId);
		model.put("project", project);
		model.put("subProjectList", subProjectList);
		model.put("subProjectListSize", subProjectList.size());
		model.put("projectRule", projectRule);
		return new ModelAndView("projectInfoForSupplier", model);
	}
	
	@RequestMapping(params="method=toProjectInfoForSupplierForTab")
	public ModelAndView toProjectInfoForSupplierForTab(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ProjectController||toProjectInfoForSupplierForTab\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(objId);
		List subProjectList = projectService.getSubProjectByProjectId(objId);
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		model.put("orginfo", org);
		model.put("project", project);
		model.put("subProjectList", subProjectList);
		model.put("projectRule", projectRule);
		return new ModelAndView("supplierProjectInfoForTab", model);
	}
	
	/** 
	 * FuncName:saveProjectAndReq
	 * Description :  保存包组和申报书条目中间表数据
	 * @param   request,project:项目,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-24下午03:12:49 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-24下午03:12:49  
	 */
	@RequestMapping(params="method=saveProjectAndReq")
	public ModelAndView saveProjectAndReq(HttpServletRequest request,Project project,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||saveProjectAndReq\n");
		String recordCount = request.getParameter("recordCount");//拼装好的条目ID,申报书ID,数量,金额
		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
		if ("undefined".equals(ebuyMethod)) {
			ebuyMethod = projectService.getProjectByObjId(project.getParentId()).getEbuyMethod();
		}
		project.setEbuyMethod(ebuyMethod);
		project.setManager((projectService.getProjectByObjId(project.getParentId()).getManager()));
		projectService.saveProjectAndReq(project,recordCount);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:updateProjectAndReq
	 * Description :  修改包组和申报书条目中间表数据
	 * @param   request,project:项目,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-24下午03:12:49 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-24下午03:12:49     
	 */
	@RequestMapping(params="method=updateProjectAndReq")
	public ModelAndView updateProjectAndReq(HttpServletRequest request,Project project,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||updateProjectAndReq\n");
		String recordCount = request.getParameter("recordCount"); 	// 拼装好的条目ID,申报书ID,数量：
		String ebuyMethod = request.getParameter("ebuyMethod"); 	//采购方式 
		project.setEbuyMethod(ebuyMethod);
		project.setManager((projectService.getProjectByObjId(project.getParentId()).getManager()));
		projectService.updateProjectAndReq(project,recordCount);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:removeProjectAndReq
	 * Description:删除包组和申报书条目中间表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-25上午10:16:05 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-25上午10:16:05   
	 */
	@RequestMapping(params="method=removeProjectAndReq")
	public ModelAndView removeProjectAndReq(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||removeProjectAndReq\n");
		String objId = request.getParameter("objId");//包组ID
		if(objId==null||"".equals(objId)){
			throw new Exception(EsExceptionEnum.PROJECTID_NOT_EXISTS);
		}
		projectService.removeProjectAndReq(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getSubProjectForSupplier
	 * Description :  跳转到查看包组信息页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-25下午04:08:39 
	 * @Modifier yangx
	 * @Modified Date: 2010-6-25下午04:08:39    
	 */
	@RequestMapping(params="method=getSubProjectForSupplier")
	public ModelAndView getSubProjectForSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjProcessRuleController||toProjProcessRule\n");
		String projectId = request.getParameter("projectId");//包组ID
		if(projectId==null||"".equals(projectId)){
			throw new Exception(EsExceptionEnum.PROJECTID_NOT_EXISTS);
		}
		Project project = projectService.get(projectId);//包组
		
		List <SubProjectMTaskPlanSub> projectMTaskPlanList = projectService.getSubProjectMTaskPlanSubBySubProjectId(project.getObjId());
		double money = 0.0;
		for(int j=0;j<projectMTaskPlanList.size();j++){
			money = projectService.add(money,new Double(projectMTaskPlanList.get(j).getMoney().toString()));
		  }
		project.setRealMoney(new BigDecimal(money));    //金额
		Map model = new HashMap();
		model.put("project",project);
		model.put("projectMTaskPlanList",projectMTaskPlanList);
		return new ModelAndView("getSubProjectForSupplier", model);
	}
	
	/** 
	 * FuncName:checkRole
	 * Description:根据当前用户判断角色类型
	 * @param request
	 * @return String
	 * @author Administrator
	 * @Create Date: 2010-6-18下午05:27:46 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-6-18下午05:27:46 
	 */
	private String checkRole(Project project,HttpServletRequest request)throws Exception {
		 logger.debug("\nes ProjectController||checkRole\n");
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String requestUrl = "";
		if(project == null) {
			if(orgInfo.getAgencyId() != null && !"".equals(orgInfo.getAgencyId())){
				requestUrl = RoleTypeEnum.ECP_AGENCY;
			}
			else if(orgInfo.getBuyerId() != null && !"".equals(orgInfo.getBuyerId())){
				requestUrl = RoleTypeEnum.ECP_BUYER;
			}
			else if(orgInfo.getSupplierId() != null && !"".equals(orgInfo.getSupplierId())){
				requestUrl = RoleTypeEnum.ECP_SUPPLIER;
			}
			else if(orgInfo.getSupervisionId() != null && !"".equals(orgInfo.getSupervisionId())){
				requestUrl = RoleTypeEnum.ECP_SUPERVISE;
			}
		}
		else {
			if(orgInfo.getObjId().equals(project.getAgencies().getObjId())){
				requestUrl = RoleTypeEnum.ECP_AGENCY;
			}
			else if(project.getBuyersId().indexOf(orgInfo.getObjId()) > -1){
				requestUrl = RoleTypeEnum.ECP_BUYER;
			}
			else{
				requestUrl = RoleTypeEnum.ECP_SUPPLIER;
			}
		}
		return requestUrl;
	}

	/**
	 * FuncName:saveProjectByTaskPlanSubs
	 * Description:创建项目
	 * @param project:项目,taskPlanSubIds:申报书条目主键,request,status
	 * @return  ModelAndView
	 * @author liuke
	 */
	@RequestMapping(params = "method=saveProjectByTaskPlanSubs")
	public ModelAndView saveProjectByTaskPlanSubs(Project project,String taskPlanSubIds, HttpServletRequest request, SessionStatus status)throws Exception{
		logger.debug("\nes=ProjectController||saveProjectByTaskPlanSubs\n");
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(CommonEnum._SYS_FLAG);//添加系统标示 modify by yangx
		//projectService.saveProjectByTaskPlanSubs(project, taskPlanSubIds);
	    projectService.saveECPProjectByTaskPlanSubs(project, taskPlanSubIds);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:updateProjectProcess
	 * Description :  更新项目进度条
	 * @param   request,status
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-27下午01:00:23 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-27下午01:00:23 
	 */
	@RequestMapping(params = "method=updateProjectProcess")
	public ModelAndView updateProjectProcess(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||updateProjectProcess\n");
		String objId = request.getParameter("objId");
		Assert.assertNotNull(objId);
		Map model = new HashMap();
		Object[] situation = projectPlanService.getSituationByProject(objId);
		model.put("process",situation[2] );
		return new ModelAndView(Constants.JSON_VIEW, model);
	} 

	/** 
	 * FuncName:getProjProecssPers
	 * Description :将项目的进度计算出来
	 * @param projList:项目集合
	 * @return List<Project>
	 * @author liuy
	 * @Create Date: 2010-7-27下午05:12:41   
	 * @Modifier liuy
	 * @Modified Date: 2010-7-27下午05:12:41 
	 */
	private List<Project> getProjProecssPers(List<Project> projList) throws Exception {
		logger.debug("\nes ProjectController||getProjProecssPers\n");
		if(projList != null && !projList.isEmpty()){
			for (Iterator iterator = projList.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				Object[] situation = projectPlanService.getSituationByProject(project.getObjId());
				project.setProcessPers(String.valueOf(situation[2]));
			}
		}
		return projList;
	}
	
	/**
	 * FuncName:toUpdateProjectTime
	 * Description : 转向对项目开始报名时间，结束报名时间，投标开始时间，投标结束时间进行维护的页面
	 * @param project:项目对象
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-8-2下午01:14:48 
	 * @Modifier    shenjianzhuang
	 * @Modified Date: 2010-8-2下午01:14:48 
	 */
	@RequestMapping(params = "method=toUpdateProjectTime")
	public ModelAndView toUpdateProjectTime(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toUpdateProjectTime\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		model.put("projectRule", projectRule);
		return new ModelAndView("toUpdateProjectTime", model);
	}
	
	/**
	 * FuncName:toUpdateProjectTimeByProjectView
	 * Description: 项目预览页面 更新项目时间{供应商报名、投标时间}
	 * @param request,projectId:项目主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-9 上午11:33:44 
	 */
	@RequestMapping(params = "method=toUpdateProjectTimeByProjectView")
	public ModelAndView toUpdateProjectTimeByProjectView(HttpServletRequest request,String projectId)throws EsException {
		logger.debug("\nes=ProjectController||toUpdateProjectTimeByProjectView\n");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		model.put("project", project);
		return new ModelAndView("toUpdateProjectTimeByProjectView", model);
	}

	/** 
	 * FuncName:toModifyProjectInfo
	 * Description:跳转到修改项目
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-2下午02:05:51   
	 * @Modifier yangx
	 * @Modified Date: 2010-9-2下午02:05:51   
	 */
	@RequestMapping(params = "method=toModifyProjectInfo")
	public ModelAndView toModifyProjectInfo(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toModifyProjectInfo\n");
		String objId = request.getParameter("projectId");
		Map model = new HashMap();
		Project project = projectService.get(objId);
		List<TaskPlanSub> list = taskPlanSubService.getTaskPlanSubByProjectId(project.getObjId());
		StringBuffer taskPlanSubIds_ = new StringBuffer();
		if (list!=null&&list.size()>0) {
			for (TaskPlanSub taskPlanSub:list) {
				taskPlanSubIds_ .append(taskPlanSub.getObjId()+",");
			}
			model.put("taskPlanSubIds",taskPlanSubIds_.substring(0,taskPlanSubIds_.lastIndexOf(",")));
		}
		model.put("project", project);
		return new ModelAndView("toModifyProjectInfo", model);
	}
	
	/** 
	 * FuncName:removeProject
	 * Description :  删除项目
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-19下午01:57:14 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-19下午01:57:14   
	 */
	@RequestMapping(params = "method=removeProject")
	public ModelAndView removeProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||removeProject\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		if (project!=null&&CommonEnum.USER_STATUS_TEMP.equals(project.getUseStatus())) {//判断是否为临时状态：临时状态可以删除,正式状态不能删除
			projectService.removeProject(projectId);
		}else{
			throw new EsException(messageSource.getMessage(EsExceptionEnum.NOT_DELPROJECT));
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:toInputTenderInfo
	 * Description :代理机构：跳转到录入招标信息页面
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-21上午10:01:47 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-21上午10:01:47 
	 */
	@RequestMapping(params = "method=toInputTenderInfo")
	public ModelAndView toInputTenderInfo(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toInputTenderInfo\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);//获取项目
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//获取包组
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		BidRoom bidRoom = new BidRoom();
		List<BidRoom> bidRoomList = meetRoomService.getBidRoomByProjectId(projectId);
		for(BidRoom bid:bidRoomList) {
			if("01".equals(bid.getMeetRoom().getRoomType()))	{
				 bidRoom = bid;
			}
		}
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		Map model = new HashMap();
		model.put("bidRoom", bidRoom);
		model.put("project",project);
		model.put("projectRule",projectRule);
		model.put("subProjectList",subProjectList);
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("subProjectLength",subProjectList.size());
		model.put("projProcessRule", projProcessRule);
		return new ModelAndView("toInputTenderInfoDetail", model);
	}
	
	/** 
	 * FuncName:toUpdateInputTenderInfo
	 * Description :  代理机构：跳转到修改招标信息页面
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-9-21上午10:01:47 
	 * @Modifier  liuke
	 * @Modified Date: 2010-9-21上午10:01:47    
	 */
	@RequestMapping(params = "method=toUpdateInputTenderInfo")
	public ModelAndView toUpdateInputTenderInfo(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toUpdateInputTenderInfo\n");
		String projectId = request.getParameter("objId");
		Project project = projectService.getProjectByObjId(projectId);//获取项目
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//获取包组
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		List<BidRoom> bidRoomList = meetRoomService.getBidRoomByProjectId(projectId);
		BidRoom bidRoom = new BidRoom();
		for(BidRoom bid:bidRoomList) {
			if("01".equals(bid.getMeetRoom().getRoomType())) {
				 bidRoom = bid;
			}
		}
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanSubByTaskPlanId(projectId);
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("bidRoom", bidRoom);
		model.put("project",project);
		model.put("projectRule",projectRule);
		model.put("subProjectList",subProjectList);
		model.put("subProjectLength",subProjectList.size());
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("projProcessRule", projProcessRule);
		return new ModelAndView("toInputTenderInfo", model);
	}
	
	/** 
	 * FuncName:saveInputTenderInfo
	 * Description :  代理机构：保存录入招标信息
	 * @param   project:项目,subProjects:包组项目,status,request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-21下午01:47:55 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-21下午01:47:55     
	 */
	@RequestMapping(params = "method=saveInputTenderInfo")
	public ModelAndView saveInputTenderInfo(String project,String subProjects,SessionStatus status,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||saveInputTenderInfo\n");
		Project projectq = JsonUtils.json2Bean(JsonUtils.getJSONString(project), Project.class);
		JSONArray jsonArray = JSONArray.fromObject(subProjects);
		projectService.saveInputTenderInfo(projectq,jsonArray);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:toCheckProject
	 * Description : 代理机构：检测项目完整性
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-21下午03:42:29 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-21下午03:42:29    
	 */
	@RequestMapping(params = "method=toCheckProject")
	public ModelAndView toCheckProject(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toCheckProject\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.checkProjectIsComplete(projectId);
		Map model = new HashMap();
		model.put("project",project);
		return new ModelAndView("toCheckProject",model);
	}
	
	/** 
	 * FuncName:topuaseProjectList
	 * Description:代理机构：跳转到暂停项目列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-11下午04:03:16 
	 * @Modifier yangx
	 * @Modified Date: 2010-10-11下午04:03:16   
	 */
	@RequestMapping(params = "method=topuaseProjectList")
	public ModelAndView topuaseProjectList(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||topuaseProjectList\n");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		QueryObject<Project> queryObject = new QueryObjectBase<Project>();
		queryObject.setEntityClass(Project.class);
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		queryObject.getQueryParam().and(new QueryParam("agencies",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		Page page = prePage(request);
		page = projectService.getPuaseProjectList(page,queryObject);
		Map model = new HashMap();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:topuaseProjectListForAudit
	 * Description :  采购办：跳转到待审核暂停项目列表
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-10-11下午07:04:17 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-11下午07:04:17  
	 */
	@RequestMapping(params = "method=topuaseProjectListForAudit")
	public ModelAndView topuaseProjectListForAudit(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||topuaseProjectListForAudit\n");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		User user = AuthenticationHelper.getCurrentUser();
		QueryObject<Project> queryObject = new QueryObjectBase<Project>();
		queryObject.setEntityClass(Project.class);
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		queryObject.getQueryParam().and(new QueryParam("superviseId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		Page page = prePage(request);
		page = projectService.getPuaseProjectListForAudit(page,queryObject);
		Map model = new HashMap();
		model.put(FrameJsonView.INCLUDED_PROPERTIES,new String[]{"agencies","agencies.orgName"});
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}	
	
	/**
	 * FuncName: toProjectInfoDetailForTab
	 * Description :从项目tab页进入项目  
	 * @param request
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2010-12-24  上午11:17:19
	 * @Modifier: liuke
	 * @Modified Date:2010-12-24  上午11:17:19
	 */
	@RequestMapping(params="method=toProjectInfoDetailForTab")
	public ModelAndView toProjectInfoDetailForTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectInfoDetailForTab\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(objId);
		Object[] situation = projectPlanService.getSituationByProject(objId);
		String returnUrl ="";
		String curRoleType = this.checkRole(project,request);
		if(RoleTypeEnum.ECP_AGENCY.equals(curRoleType)){
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfoForTabDetail";
		}else if(RoleTypeEnum.ECP_BUYER.equals(curRoleType)){
			returnUrl = "buyerProjectInfoForwardForTab";
			if(request.getParameter("toProjectInfoForBuyer")!= null){
				returnUrl = "projectInfoForTabDetail";
			}
		}else if(RoleTypeEnum.ECP_SUPPLIER.equals(curRoleType)){
			returnUrl = "toProjectInfoForSupplierForTab";
		}else if(RoleTypeEnum.ECP_SUPERVISE.equals(curRoleType)){
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfoForTabDetail";
		}else{
			model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
			returnUrl = "projectInfoForTabDetail";
		}
		model.put("waitporcPlanList", projectPlanService.getWaitprocWorkTaskProjectPlan(objId, objId,AuthenticationHelper.getCurrentUser().getObjId()));
		model.put("process",situation[2]);
		model.put("project", project);
		model.put("projectRule", projectRule);
		return new ModelAndView(returnUrl, model);
	}
	
	/**
	 * FuncName: toProjectInfoDetailForTab
	 * Description : 跳转到得到更多项目列表页面
	 * @param request
	 * @return ModelAndView
	 * @throws Exception 
	 * @author: liuke
	 * @Create Date:2010-12-24  上午11:53:55
	 * @Modifier: liuke
	 * @Modified Date:2010-12-24  上午11:53:55
	 */
	@RequestMapping(params="method=toMoreProjectPageForTab")
	public ModelAndView toMoreProjectPageForTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toMoreProjectPageForTab\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		if(orgInfo.getAgencyId()!=null&&!"".equals(orgInfo.getAgencyId())){//当前角色是代理机构
			queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		}
		if(orgInfo.getBuyerId()!=null&&!"".equals(orgInfo.getBuyerId())){//当前角色是采购人
			queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}
		if(orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){//当前角色是供应商
			queryObject.getQueryParam().and(new QueryParam("supplyId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}
		if(orgInfo.getSupervisionId()!=null&&!"".equals(orgInfo.getSupervisionId())){//当前用户是采购办
			queryObject.getQueryParam().and(new QueryParam("superviseId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		}
		List<Project> projectList = projectService.getProjectListByQueryObject(queryObject);
		String returnUrl = "moreProjectListPage";
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * 
	 * FuncName: toProjectMessagePage
	 * Description : 跳转到从预定评标室查看项目弹出页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-1-18  下午12:48:25
	 * @Modifier: liuke
	 * @Modified Date:2011-1-18  下午12:48:25
	 */
	@RequestMapping(params = "method=toProjectMessagePage")
	public ModelAndView toProjectMessagePage(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toProjectMessagePage\n");
		String objId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(objId);
		Map model = new HashMap();
		model.put("project", project);
		return new ModelAndView("projectMessagePage", model);
	}	
	
	/** 
	 * FuncName: checkSubProject
	 * Description :  检查当前项目下包组编号、名称是否重复
	 * Create Date: 2011-1-19下午07:02:28 by yangx  Modified Date: 2011-1-19下午07:02:28 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkSubProject")
	public ModelAndView checkSubProject(HttpServletRequest request)throws Exception{
		String key = request.getParameter("key");
		String value = URLDecoder.decode(request.getParameter("value"),"UTF-8");
		String projectId = request.getParameter("projectId");
		String subProjectId = request.getParameter("subProjectId");
		QueryObject<Project> queryObject = new QueryObjectBase<Project>();
		if (key!=null&&!"".equals(key)&&"code".equals(key)) {
			queryObject.getQueryParam().and(new QueryParam("code",QueryParam.OPERATOR_EQ,value));
		} else {
			queryObject.getQueryParam().and(new QueryParam("name",QueryParam.OPERATOR_EQ,value));
		}
		queryObject.getQueryParam().and(new QueryParam("projectId",QueryParam.OPERATOR_EQ,projectId));
		queryObject.getQueryParam().and(new QueryParam("subProjectId",QueryParam.OPERATOR_EQ,subProjectId));
		List<Project> projectList= projectService.getProjectByNameOrCode(queryObject);
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	
    /**
     * 
     * FuncName: getSubProjectMTaskPlanSubByPackId
     * Description : 根据包组ID获得包组项目中间表数�?
     * @param 
     * @param request
     * @return
     * @throws Exception ModelAndView
     * @author: liuke
     * @Create Date:2011-2-23  下午05:24:05
     * @Modifier: liuke
     * @Modified Date:2011-2-23  下午05:24:05
     */
	@RequestMapping(params = "method=getSubProjectMTaskPlanSubByPackId")
	public ModelAndView getSubProjectMTaskPlanSubByPackId(HttpServletRequest request)throws Exception{
		String packId = request.getParameter("packId");
		SubProjectMTaskPlanSub  subProjectMTaskPlanSub = projectService.getSubProjectMTaskPlanSubByPackId(packId);
		String num = "";
		Integer n = new Integer(0);
		if(subProjectMTaskPlanSub==null){//该项目没有分�?
			List<ProjectMTaskPlan>  projectMTaskPlanList = projectMTaskPlanService.getProjectMTaskPlanByProjectId(packId);
			for(ProjectMTaskPlan projectMTaskPlan:projectMTaskPlanList){
				if(projectMTaskPlan!=null&&projectMTaskPlan.getTaskPlanSub()!=null){
					TaskPlanSub taskPlanSub = taskPlanSubService.get(projectMTaskPlan.getTaskPlanSub());
					String number = taskPlanSub.getQuantity().toString();
					n += new Integer(number);
				}
			}
			num = n+"";
		}else{
			num = subProjectMTaskPlanSub.getQuantity().toString();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num",num);
		return new ModelAndView(Constants.JSON_VIEW,map);
	}
	/**
	 * FuncName: printProjectView
	 * Description : 打印报名的供应商清单
	 * @param reqeust
	 * @param projectId
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-23  下午01:28:05
	 * @Modifier: liuke
	 * @Modified Date:2011-3-23  下午01:28:05
	 */
	@RequestMapping(params = "method=printProjectView")
	public ModelAndView printProjectView(HttpServletRequest reqeust, String projectId)throws Exception {
		List<SignUprecord> signupList = signUprecordService.getSignupRecordList(projectId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("signupList",signupList);
		return new ModelAndView("printSupplierPage", model);
	}
	/**
	 * FuncName: searchProjectListDljg
	 * Description :  查询显示代理机构项目列表（为实现tab形式而额外增加的方法）
	 * @param 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-20  下午03:07:27
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-20  下午03:07:27
	 */
	@RequestMapping(params="method=searchProjectListDljg2")
	public ModelAndView searchProjectListDljg2(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||searchProjectListDljg\n");
		String serviceName=request.getParameter("serviceName");
		String pageNum=request.getParameter(Page.PAGE_NAME);
		String rp=request.getParameter(Page.PAGE_SIZE_NAME);
		Page page=new Page();
		if(null != pageNum && "0".equals(pageNum)){
			pageNum = "1";
		}
		int curpage = Integer.parseInt(pageNum == null ? "1":pageNum);		//得到当前页数   
		int limit = Integer.parseInt(rp == null ? "5":rp);					//得到当前页大小  
		int start = (curpage==1?0:Page.getStartOfPage(curpage, limit));
		page.setStart(start);
		page.setPageSize(limit);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		User user=AuthenticationHelper.getCurrentUser();
		if (serviceName!=null&&"searchProjectListForAgent".equals(serviceName)) {//代理机构项目负责人
			page = projectService.searchProjectListForAgent(queryObject, page, user.getEmp().getObjId());
		} else if (serviceName!=null&&"searchProjectListForAgentManager".equals(serviceName)) {//代理机构管理员
			page = projectService.searchProjectListForAgentManager(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForSupervise".equals(serviceName)) {//监管单位经办人
			page = projectService.searchProjectListForSupervise(queryObject, page, user.getEmp().getObjId());
		} else if (serviceName!=null&&"searchProjectListForSuperviseManager".equals(serviceName)) {//监管单位管理员
			page = projectService.searchProjectListForSuperviseManager(queryObject, page);
		} else if (serviceName!=null&&"searchProjectListForBuyer".equals(serviceName)) {//采购人
			page = projectService.searchProjectListForBuyer(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForSupply".equals(serviceName)) {//供应商
			page = projectService.searchProjectListForSupply(queryObject, page, (user.getOrgInfo()).getObjId());
		} else if (serviceName!=null&&"searchProjectListForGovernment".equals(serviceName)) {//业务处室经办人
			Department department = userApiService.getDepartmentByUserId(user.getObjId());
			page = projectService.searchProjectListForGovernment(queryObject, page, department.getObjId());
		}
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=searchProjectListDljg2&serviceName="+serviceName);//设置分页对应的请求地址
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		model.put("PAGERESULT", page);
		String cols = "projCode,projName,ebuyMethod,tenderType";// cols 指定的返回请求参数
		Map<String,Object> map = request.getParameterMap();
		for(String key:cols.split(",")){
			for(Map.Entry entry:map.entrySet()){
				if(key.equals(entry.getKey())){
					model.put((String)entry.getKey(), request.getParameter((String)entry.getKey()));
				}
			}
		}
		return new ModelAndView("searchProjectListDljg2", model);
	}
	
	/** 
	 * Description :  手动结束任务书
	 * Create Date: 2011-11-3下午8:33:38 by sunl  Modified Date: 2011-11-3下午8:33:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=overTaskPlan")
	public ModelAndView overTaskPlan(HttpServletRequest reqeust) throws Exception {
		String taskPlanId = reqeust.getParameter("taskPlanId");//获取任务书ID
		TaskPlan taskplan = taskPlanService.get(taskPlanId);
		taskplan.setProcessStatus(TaskPlanConfirmEnum.TASKPLAN_DOWN);//已完成
		taskPlanService.save(taskplan);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", "success");
		return new ModelAndView(Constants.JSON_VIEW, model); 
	}
	
	/**
	 * 根据传入的业务主键查询相关的附件信息，并跳转到attachmentList对应的页面
	 * @param reqeust
	 * @return
	 * @throws Exception
	 * Created at 2011-4-29 10:02 by zhouzhanghe
	 */
	@RequestMapping(params = "method=toViewProjectOthrAttcInfo")
	public ModelAndView toViewProjectOthrAttcInfo(HttpServletRequest reqeust)throws Exception {
		String attBizId = reqeust.getParameter("attBizId");
		
		List<Attachment> attachmentList = null;
		try {
			attachmentList = extAttachmentServiceImpl.getAttachmentsByAttBizId(attBizId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("attachmentList", attachmentList);
		
		return new ModelAndView("projectOthrAttcInfo", model);
	}
	/** 
	 * Description :  代理机构：跳转到暂停项目表单页
	 * Create Date: 2010-10-11上午11:28:45 by yangx  Modified Date: 2010-10-11上午11:28:45 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPuaseProject")
	public ModelAndView toPuaseProject(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toPuaseProject\n");
		String projectId = request.getParameter("subProjId");
		String fromType = request.getParameter("fromType");
		String returnUrl = "toPuaseProject";
		Project project = projectService.getProjectByObjId(projectId);
		if(project.getParentId()==null||"".equals(project.getParentId())){ //说明没有分包
			List<ProjectMTaskPlan> projectMTaskPlanList = projectMTaskPlanService.getProjectMTaskPlanByProjectId(project.getObjId());
			BigDecimal money = new BigDecimal(0);
			for (Iterator iterator = projectMTaskPlanList.iterator(); iterator.hasNext();) {//循环遍历预算金额
				ProjectMTaskPlan pmt = (ProjectMTaskPlan) iterator.next();
				if(pmt.getBudgetMoney()!=null){
					money =money.add(pmt.getBudgetMoney());
				}
			}
			project.setBudgetTotalMoney(money);
		}else{  //说明分包
			 List<SubProjectMTaskPlanSub> subProjectMList = projectService.getSubProjectMTaskPlanSubBySubProjectId(project.getObjId());
			 BigDecimal money = new BigDecimal(0);
			 for (Iterator iterator = subProjectMList.iterator(); iterator.hasNext();) { //循环遍历预算金额
				 SubProjectMTaskPlanSub spmt = (SubProjectMTaskPlanSub) iterator.next();
					if(spmt.getMoney()!=null){
						money =	money.add(spmt.getMoney());
					}
				}
				project.setBudgetTotalMoney(money);
		}
		ProjectExceptionApply projectExceptionApply = projectExceptionApplyService.getProjectExceptionApplyByProjectId(projectId);
		if (projectExceptionApply!=null&&(AuditStatusEnum.AUDIT_PASS.equals(projectExceptionApply.getAuditStatus())||AuditStatusEnum.WAIT_AUDIT.equals(projectExceptionApply.getAuditStatus()))) {
			returnUrl = "toPuaseProjectDetail";
		}
		Map model = new HashMap();
		model.put("project",project);
		model.put("fromType",fromType);
		model.put("projectExceptionApply",projectExceptionApply);
		return new ModelAndView(returnUrl,model);
	}
	
	
	
	/**
	 * FuncName :toSetGenviewDefine 
	 * Description :  跳转到设置开标一览表表头页面
	 * Create Date: 2011-10-31上午11:02:59 by liuke
	 * Modified Date: 2011-10-31上午11:02:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=toPuaseProjectList")
	public ModelAndView toPuaseProjectList(HttpServletRequest request) throws Exception{
		logger.debug("\nes CongruousFactorController||toSetGenviewDefine\n");
		String projectId = request.getParameter("projectId");
	
		
		List<Project> subProjectList = new ArrayList<Project>();
		Project project = projectService.getProjectByObjId(projectId);
		subProjectList = projectService.getSubProjectByProjectId(projectId);
		
		if(subProjectList.isEmpty()){
			subProjectList.add(project);	
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("subProjectList", subProjectList);
		return new ModelAndView("toPuaseProjectList",model);
	}
	
	
	
	
	/** 
	 * Description :  维护项目状态
	 * Create Date: 2011-5-21上午11:43:24 by yangx  Modified Date: 2011-5-21上午11:43:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateProjImplStatus")
	public ModelAndView updateProjImplStatus(HttpServletRequest request,SessionStatus stauts)throws Exception{
		String objId = request.getParameter("projectId");
		String projImplStatus=request.getParameter("projImplStatus");
		Project project = projectService.get(objId);
		project.setProjImplStatus(projImplStatus);
		project.setProjImplStatusCN(projImplStatus);
		projectService.save(project);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("project",project);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description:获取项目数据
	 * @param prjCode:项目记录号
     * @param username:用户名
     * @param password: 密码
	 * @throws Exception
	 * @author: zhouzhanghe
	 * @Create Date:2011-6-30  上午09:58:27
	 */
	@RequestMapping(params = "method=getProjectData")
	public void getProjectData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean operDesc = true;//存放操作结果
		List<DataItem> dataItemDTOList = new ArrayList<DataItem>();
		String prjCode = request.getParameter("prjCode");//项目编号
		String errorMessage ="";
		try {
			Project p =  projectService.findProjectByProjCode(prjCode);
				DataItem prjCodeDataItem = new DataItem();
				prjCodeDataItem.setId("prjCode");
				prjCodeDataItem.setName("项目编号");
				prjCodeDataItem.setValue(p.getProjCode());
				prjCodeDataItem.setType("project");
				prjCodeDataItem.setReadOnly("true");
				dataItemDTOList.add(prjCodeDataItem);
				
				DataItem prjName = new DataItem();
				prjName.setId("prjName");
				prjName.setName("项目名称");
				prjName.setValue(p.getProjName());
				prjName.setType("project");
				prjName.setReadOnly("true");
				dataItemDTOList.add(prjName);
				
				DataItem angentCode = new DataItem();
				angentCode.setId("angentCode");
				angentCode.setName("代理机构编号"); 
				angentCode.setValue(p.getAgencies().getOrgCode());
				angentCode.setType("buyingCenter");
				angentCode.setReadOnly("true");
				dataItemDTOList.add(angentCode);
				
				DataItem angentName = new DataItem();
				angentName.setId("angentName");
				angentName.setName("代理机构名称"); 
				angentName.setValue(p.getAgencies().getOrgName());
				angentName.setType("buyingCenter");
				angentName.setReadOnly("true");
				dataItemDTOList.add(angentName);
				
				DataItem fileType = new DataItem();
				fileType.setId("fileType");
				fileType.setName("文件类型"); 
				String fileTypes = "";
				if(EbuyMethodEnum.INQUIRY.equals(p.getEbuyMethod())){//如果采购方式是询价
					fileTypes = PurchaseDocEnum.INQPDOC;
				}else{ //其他采购方式
					fileTypes = PurchaseDocEnum.PURCHASEDOC;
				}
				fileType.setValue(PurchaseDocEnum.getCN(fileTypes));
				fileType.setType("project");
				fileType.setReadOnly("true");
				dataItemDTOList.add(fileType);
				
				DataItem projectTime = new DataItem();
				projectTime.setId("prjTime");
				projectTime.setName("立项时间"); 
				projectTime.setValue(DateUtil.format(p.getCreateTime(), "yyyy年MM月dd日"));
				projectTime.setType("project");
				projectTime.setReadOnly("true");
				dataItemDTOList.add(projectTime);
				
				DataItem purchaseType = new DataItem();
				purchaseType.setId("purchaseType");
				purchaseType.setName("采购方式"); 
				purchaseType.setValue(p.getEbuyMethodCN());
				purchaseType.setType("project");
				purchaseType.setReadOnly("true");
				dataItemDTOList.add(purchaseType);
				
				DataItem biddingOrgName = new DataItem();
				biddingOrgName.setId("biddingOrgName");
				biddingOrgName.setName("招标机构"); 
				biddingOrgName.setValue(p.getBuyersName());
				biddingOrgName.setType("project");
				biddingOrgName.setReadOnly("true");
				dataItemDTOList.add(biddingOrgName);
				
				DataItem purchaseOrgName = new DataItem();
				purchaseOrgName.setId("purchaseOrgName");
				purchaseOrgName.setName("采购人"); 
				purchaseOrgName.setValue(p.getBuyersName());
				purchaseOrgName.setType("project");
				purchaseOrgName.setReadOnly("true");
				dataItemDTOList.add(purchaseOrgName);
				
				//TODO 目前只放入代理机构的地址
				DataItem applyLocation = new DataItem();
				applyLocation.setId("applyLocation");
				applyLocation.setName("线下报名地址"); 
				applyLocation.setValue(p.getAgencies().getOrgAddress());
				applyLocation.setType("project");
				applyLocation.setReadOnly("true");
				dataItemDTOList.add(applyLocation);
				
				DataItem applyStartTime = new DataItem();
				applyStartTime.setId("applyStartTime");
				applyStartTime.setName("开始时间（报名）"); 
				ProjectRule projectRule = projectService.getProjectRuleByProjectId(p.getObjId());
				if(projectRule != null && projectRule.getSignUpSTime() != null)
					applyStartTime.setValue(DateUtil.format(projectRule.getSignUpSTime(),"yyyy年MM月dd日"));
				applyStartTime.setType("project");
				applyStartTime.setReadOnly("true");
				dataItemDTOList.add(applyStartTime);
				
				DataItem applyEndTime = new DataItem();
				applyEndTime.setId("applyEndTime");
				applyEndTime.setName("截止时间（报名）"); 
				if(projectRule != null && projectRule.getSignUpETime() != null)
				applyEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
				applyEndTime.setType("project");
				applyEndTime.setReadOnly("true");
				dataItemDTOList.add(applyEndTime);
				
				DataItem tenderFeeNumber = new DataItem();
				tenderFeeNumber.setId("tenderFeeNumber");
				tenderFeeNumber.setName("标书费金额"); 
				tenderFeeNumber.setValue((p.getPurDocPrice()!=null)?p.getPurDocPrice().toString():"");
				tenderFeeNumber.setType("project");
				tenderFeeNumber.setReadOnly("true");
				dataItemDTOList.add(tenderFeeNumber);
				
				//TODO 目前"标书费缴纳截止时间"取为"报名结束时间"
				DataItem tenderFeeEndTime = new DataItem();
				tenderFeeEndTime.setId("tenderFeeEndTime");
				tenderFeeEndTime.setName("标书费缴纳截止时间"); 
				if(projectRule != null && projectRule.getSignUpETime() != null)
				tenderFeeEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
				tenderFeeEndTime.setType("project");
				tenderFeeEndTime.setReadOnly("true");
				dataItemDTOList.add(tenderFeeEndTime);
				
				DataItem openBankOrg = new DataItem();
				openBankOrg.setId("openBankOrg");
				openBankOrg.setName("开户单位（缴纳）"); 
				Agency agency = userApiService.getAgentMessageByObjId(p.getAgencies().getObjId());
				if(agency != null && agency.getOpenBank() != null)
					openBankOrg.setValue(agency.getOpenBank());
				openBankOrg.setType("project");
				openBankOrg.setReadOnly("true");
				dataItemDTOList.add(openBankOrg);
				
				DataItem openBankAccount = new DataItem();
				openBankAccount.setId("openBankAccount");
				openBankAccount.setName("账号（缴纳）"); 
				if(agency != null && agency.getOpenAccount() != null)
					openBankAccount.setValue(agency.getOpenAccount());
				openBankAccount.setType("project");
				openBankAccount.setReadOnly("true");
				dataItemDTOList.add(openBankAccount);
				
				//TODO 未存入支付方式
				DataItem openBankPayment = new DataItem();
				openBankPayment.setId("openBankPayment");
				openBankPayment.setName("支付方式（缴纳）"); 
				openBankPayment.setValue("");
				openBankPayment.setType("project");
				openBankPayment.setReadOnly("true");
				dataItemDTOList.add(openBankPayment);
				
				DataItem tenderStartTime = new DataItem();
				tenderStartTime.setId("tenderStartTime");
				tenderStartTime.setName("开始时间（投标）"); 
				if(projectRule != null && projectRule.getSubmitStTime() != null)
					tenderStartTime.setValue(DateUtil.format(projectRule.getSubmitStTime(),"yyyy年MM月dd日"));
				tenderStartTime.setType("project");
				tenderStartTime.setReadOnly("true");
				dataItemDTOList.add(tenderStartTime);
				
				DataItem tenderEndTime = new DataItem();
				tenderEndTime.setId("tenderEndTime");
				tenderEndTime.setName("截止时间（投标）"); 
				if(projectRule != null && projectRule.getSubmitETime() != null)
					tenderEndTime.setValue(DateUtil.format(projectRule.getSubmitETime(),"yyyy年MM月dd日"));
				tenderEndTime.setType("project");
				tenderEndTime.setReadOnly("true");
				dataItemDTOList.add(tenderEndTime);
				
				DataItem openTime = new DataItem();
				openTime.setId("openTime");
				openTime.setName("开标时间"); 
				if(projectRule != null && projectRule.getOpenBidSDate() != null)
					openTime.setValue(DateUtil.format(projectRule.getOpenBidSDate(),"yyyy年MM月dd日"));
				openTime.setType("project");
				openTime.setReadOnly("true");
				dataItemDTOList.add(openTime);
				
				DataItem openLocation = new DataItem();
				openLocation.setId("openLocation");
				openLocation.setName("开标地点"); 
				if(projectRule != null && projectRule.getOpenBidAddr() != null)
					openLocation.setValue(projectRule.getOpenBidAddr());
				openLocation.setType("project");
				openLocation.setReadOnly("true");
				dataItemDTOList.add(openLocation);
				
				DataItem prjLeader = new DataItem();
				prjLeader.setId("prjLeader");
				prjLeader.setName("项目负责人"); 
				if(p.getManager() != null)
					prjLeader.setValue(p.getManager().getName());
				prjLeader.setType("project");
				prjLeader.setReadOnly("true");
				dataItemDTOList.add(prjLeader);
				
				DataItem prjLeaderLink = new DataItem();
				prjLeaderLink.setId("prjLeaderLink");
				prjLeaderLink.setName("负责人联系方式"); 
				if(p.getManager() != null && p.getManager().getTelOffice() != null)
					prjLeaderLink.setValue(p.getManager().getTelOffice());
				prjLeaderLink.setType("project");
				prjLeaderLink.setReadOnly("true");
				dataItemDTOList.add(prjLeaderLink);
				
				/*查询包组数据*/
				List<Project> subProjectList = new ArrayList<Project>();
				subProjectList = projectService.getSubProjectByProjectId(p.getObjId());
				
				
				if(subProjectList == null || subProjectList.size() == 0){//如果当前项目未分包
					List<PreqEntry> preqEntryList = preqEntryService.getPreqEntryByprojectId(p.getObjId());
					for (int i=0;i<preqEntryList.size();i++) {
						PreqEntry preqEntry = preqEntryList.get(i);
						DataItem project_requirement_1_itemCode = new DataItem();
						project_requirement_1_itemCode.setId("project_requirement_"+i+"_itemCode");
						project_requirement_1_itemCode.setName("采购品目"); 
						project_requirement_1_itemCode.setValue(preqEntry.getPurchaseCode());
						project_requirement_1_itemCode.setType("project");
						project_requirement_1_itemCode.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_itemCode);
						
						DataItem project_requirement_1_itemName = new DataItem();
						project_requirement_1_itemName.setId("project_requirement_"+i+"_itemName");
						project_requirement_1_itemName.setName("采购品目名称"); 
						project_requirement_1_itemName.setValue(preqEntry.getPurchaseName());
						project_requirement_1_itemName.setType("project");
						project_requirement_1_itemName.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_itemName);
						
						
						DataItem project_requirement_1_purNum = new DataItem();
						project_requirement_1_purNum.setId("project_requirement_"+i+"_purNum");
						project_requirement_1_purNum.setName("采购数量"); 
						project_requirement_1_purNum.setValue(preqEntry.getQuantity().toString());
						project_requirement_1_purNum.setType("project");
						project_requirement_1_purNum.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_purNum);
						
						DataItem project_requirement_1_unitPrice = new DataItem();
						project_requirement_1_unitPrice.setId("project_requirement_"+i+"_unitPrice");
						project_requirement_1_unitPrice.setName("单价"); 
						project_requirement_1_unitPrice.setValue((preqEntry.getTotalPrice().floatValue()/preqEntry.getQuantity().floatValue())+""); 
						project_requirement_1_unitPrice.setType("project");
						project_requirement_1_unitPrice.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_unitPrice);
				 
						DataItem project_requirement_1_specification = new DataItem();
						project_requirement_1_specification.setId("project_requirement_"+i+"_specification");
						project_requirement_1_specification.setName("规格"); 
						project_requirement_1_specification.setValue(preqEntry.getSpec()); 
						project_requirement_1_specification.setType("project");
						project_requirement_1_specification.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_specification);
						
						/*放入技术要求*/
						DataItem project_requirement_1_reqTechnic = new DataItem();
						project_requirement_1_reqTechnic.setId("project_requirement_"+i+"_reqTechnic");
						project_requirement_1_reqTechnic.setName("技术要求"); 
						project_requirement_1_reqTechnic.setType("project");
						project_requirement_1_reqTechnic.setValue(preqEntry.getTechnical());
						project_requirement_1_reqTechnic.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqTechnic);
						
						//放入付款条件
						DataItem project_requirement_1_reqPayCause = new DataItem();
						project_requirement_1_reqPayCause.setId("project_requirement_"+i+"_reqPayCause");
						project_requirement_1_reqPayCause.setName("付款条件"); 
						project_requirement_1_reqPayCause.setValue(preqEntry.getPaymentClause());
						project_requirement_1_reqPayCause.setType("project");
						project_requirement_1_reqPayCause.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqPayCause);
						
						//放入交货要求
						DataItem project_requirement_1_reqDelivery = new DataItem();
						project_requirement_1_reqDelivery.setId("project_requirement_"+i+"_reqDelivery");
						project_requirement_1_reqDelivery.setName("交货要求"); 
						project_requirement_1_reqDelivery.setValue(preqEntry.getDeliveryRequire());
						project_requirement_1_reqDelivery.setType("project");
						project_requirement_1_reqDelivery.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqDelivery);
						
						DataItem project_requirement_1_reqService = new DataItem();
						project_requirement_1_reqService.setId("project_requirement_"+i+"_reqService");
						project_requirement_1_reqService.setName("服务承诺"); 
						project_requirement_1_reqService.setValue(preqEntry.getServicePromise());
						project_requirement_1_reqService.setType("project");
						project_requirement_1_reqService.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqService);
						
						//放入质量要求
						DataItem project_requirement_1_reqQuality = new DataItem();
						project_requirement_1_reqQuality.setId("project_requirement_"+i+"_reqQuality");
						project_requirement_1_reqQuality.setName("质量要求"); 
						project_requirement_1_reqQuality.setValue(preqEntry.getQualityAssurance());
						project_requirement_1_reqQuality.setType("project");
						project_requirement_1_reqQuality.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqQuality);
						
						//放入维修期限
						DataItem project_requirement_1_reqWarrenty = new DataItem();
						project_requirement_1_reqWarrenty.setId("project_requirement_"+i+"_reqWarrenty");
						project_requirement_1_reqWarrenty.setName("维修期限"); 
						project_requirement_1_reqWarrenty.setValue(preqEntry.getWarrentyLen());
						project_requirement_1_reqWarrenty.setType("project");
						project_requirement_1_reqWarrenty.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqWarrenty);
						
						//放入验收标准
						DataItem project_requirement_1_reqAcceptStandard = new DataItem();
						project_requirement_1_reqAcceptStandard.setId("project_requirement_"+i+"_reqAcceptStandard");
						project_requirement_1_reqAcceptStandard.setName("验收标准"); 
						project_requirement_1_reqAcceptStandard.setValue(preqEntry.getAcceptStandard());
						project_requirement_1_reqAcceptStandard.setType("project");
						project_requirement_1_reqAcceptStandard.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_reqAcceptStandard);
						
						DataItem project_requirement_1_memo = new DataItem();
						project_requirement_1_memo.setId("project_requirement_"+i+"_memo");
						project_requirement_1_memo.setName("备注"); 
						project_requirement_1_memo.setValue(preqEntry.getRemark());
						project_requirement_1_memo.setType("project");
						project_requirement_1_memo.setReadOnly("true");
						dataItemDTOList.add(project_requirement_1_memo);
						
					}
				}else{ //如果项目分包
					/*封装包组数据*/
					for(int i = 0 ; i < subProjectList.size() ; i ++){
						Project subP = subProjectList.get(i);
						List<PreqEntry> subPreqEntryList = preqEntryService.getPreqEntryBySubProjectId(subP.getObjId());//根据包件获取项目需求;
						PreqEntry subPreqEntry = null;
						if(subPreqEntryList != null && !subPreqEntryList.isEmpty()){
							subPreqEntry = subPreqEntryList.get(0);
						}
						DataItem packId = new DataItem();
						packId.setId("pack_" + String.valueOf(i + 1) + "_id");
						packId.setName("记录号"); 
						packId.setValue(subP.getObjId());
						packId.setType("project");
						packId.setReadOnly("true");
						dataItemDTOList.add(packId);
						
						DataItem packCode = new DataItem();
						packCode.setId("pack_" + String.valueOf(i + 1) + "_code");
						packCode.setName("包件编号"); 
						packCode.setValue(subP.getProjCode());
						packCode.setType("project");
						packCode.setReadOnly("true");
						dataItemDTOList.add(packCode);
						
						DataItem packName = new DataItem();
						packName.setId("pack_" + String.valueOf(i + 1) + "_name");
						packName.setName("包件名称"); 
						packName.setValue(subP.getProjName());
						packName.setType("project");
						packName.setReadOnly("true");
						dataItemDTOList.add(packName);
						
						DataItem packMemo = new DataItem();
						packMemo.setId("pack_" + String.valueOf(i + 1) + "_memo");
						packMemo.setName("采购摘要"); 
						packMemo.setValue(subP.getProjSummary());
						packMemo.setType("project");
						packMemo.setReadOnly("true");
						dataItemDTOList.add(packMemo);
						
						DataItem subPapplyStartTime = new DataItem();
						subPapplyStartTime.setId("pack_" + String.valueOf(i + 1) + "_applyStartTime");
						subPapplyStartTime.setName("开始时间（报名）"); 
						if(projectRule != null && projectRule.getSignUpSTime() != null)
							subPapplyStartTime.setValue(DateUtil.format(projectRule.getSignUpSTime(),"yyyy年MM月dd日"));
						subPapplyStartTime.setType("project");
						subPapplyStartTime.setReadOnly("true");
						dataItemDTOList.add(subPapplyStartTime);
						
						DataItem subPapplyEndTime = new DataItem();
						subPapplyEndTime.setId("pack_" + String.valueOf(i + 1) + "_applyEndTime");
						subPapplyEndTime.setName("截止时间（报名）"); 
						if(projectRule != null && projectRule.getSignUpETime() != null)
							subPapplyEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
						subPapplyEndTime.setType("project");
						subPapplyEndTime.setReadOnly("true");
						dataItemDTOList.add(subPapplyEndTime);
						
						DataItem subPtenderStartTime = new DataItem();
						subPtenderStartTime.setId("pack_" + String.valueOf(i + 1) + "_tenderStartTime");
						subPtenderStartTime.setName("开始时间（投标）"); 
						if(projectRule != null && projectRule.getSubmitStTime() != null)
							subPtenderStartTime.setValue(DateUtil.format(projectRule.getSubmitStTime(),"yyyy年MM月dd日"));
						subPtenderStartTime.setType("project");
						subPtenderStartTime.setReadOnly("true");
						dataItemDTOList.add(subPtenderStartTime);
						
						DataItem subPtenderEndTime = new DataItem();
						subPtenderEndTime.setId("pack_" + String.valueOf(i + 1) + "_tenderEndTime");
						subPtenderEndTime.setName("截止时间（投标）"); 
						if(projectRule != null && projectRule.getSubmitETime() != null)
							subPtenderEndTime.setValue(DateUtil.format(projectRule.getSubmitETime(),"yyyy年MM月dd日"));
						subPtenderEndTime.setType("project");
						subPtenderEndTime.setReadOnly("true");
						dataItemDTOList.add(subPtenderEndTime);
						
						DataItem subPopenTime = new DataItem();
						subPopenTime.setId("pack_" + String.valueOf(i + 1) + "_openTime");
						subPopenTime.setName("开标时间"); 
						if(projectRule != null && projectRule.getOpenBidSDate() != null)
							subPopenTime.setValue(DateUtil.format(projectRule.getOpenBidSDate(),"yyyy年MM月dd日"));
						subPopenTime.setType("project");
						subPopenTime.setReadOnly("true");
						dataItemDTOList.add(subPopenTime);
						
						DataItem requirement_1_itemCode = new DataItem();
						requirement_1_itemCode.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_itemCode");
						requirement_1_itemCode.setName("采购品目"); 
						requirement_1_itemCode.setValue(subP.getPurCategoryIds());
						requirement_1_itemCode.setType("project");
						requirement_1_itemCode.setReadOnly("true");
						dataItemDTOList.add(requirement_1_itemCode);
						
						DataItem requirement_1_itemName = new DataItem();
						requirement_1_itemName.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_itemName");
						requirement_1_itemName.setName("采购品目名称"); 
						requirement_1_itemName.setValue(subP.getPurCategoryNames());
						requirement_1_itemName.setType("project");
						requirement_1_itemName.setReadOnly("true");
						dataItemDTOList.add(requirement_1_itemName);
						
						if(subPreqEntry != null && subPreqEntry.getObjId() != null){
							DataItem requirement_1_purNum = new DataItem();
							requirement_1_purNum.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_purNum");
							requirement_1_purNum.setName("采购数量"); 
							requirement_1_purNum.setValue(subPreqEntry.getQuantity().toString());
							requirement_1_purNum.setType("project");
							requirement_1_purNum.setReadOnly("true");
							dataItemDTOList.add(requirement_1_purNum);
							
							DataItem requirement_1_unitPrice = new DataItem();
							requirement_1_unitPrice.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_unitPrice");
							requirement_1_unitPrice.setName("单价"); 
							requirement_1_unitPrice.setValue((subPreqEntry.getTotalPrice().floatValue()/subPreqEntry.getQuantity().floatValue())+"");
							requirement_1_unitPrice.setType("project");
							requirement_1_unitPrice.setReadOnly("true");
							dataItemDTOList.add(requirement_1_unitPrice);
							
							DataItem requirement_1_specification = new DataItem();
							requirement_1_specification.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_specification");
							requirement_1_specification.setName("规格"); 
							requirement_1_specification.setValue(subPreqEntry.getSpec());
							requirement_1_specification.setType("project");
							requirement_1_specification.setReadOnly("true");
							dataItemDTOList.add(requirement_1_specification);
							
							DataItem pack_1_requirement_1_reqTechnic = new DataItem();
							pack_1_requirement_1_reqTechnic.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqTechnic");
							pack_1_requirement_1_reqTechnic.setName("技术要求"); 
							pack_1_requirement_1_reqTechnic.setValue(subPreqEntry.getTechnical());
							pack_1_requirement_1_reqTechnic.setType("project");
							pack_1_requirement_1_reqTechnic.setReadOnly("true");
							dataItemDTOList.add(pack_1_requirement_1_reqTechnic);
							
							DataItem requirement_1_reqPayCause = new DataItem();
							requirement_1_reqPayCause.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqPayCause");
							requirement_1_reqPayCause.setName("付款条件"); 
							requirement_1_reqPayCause.setValue(subPreqEntry.getPaymentClause());
							requirement_1_reqPayCause.setType("project");
							requirement_1_reqPayCause.setReadOnly("true");
							dataItemDTOList.add(requirement_1_reqPayCause);
							
							DataItem requirement_1_reqDelivery = new DataItem();
							requirement_1_reqDelivery.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqDelivery");
							requirement_1_reqDelivery.setName("交货要求"); 
							requirement_1_reqDelivery.setValue(subPreqEntry.getDeliveryRequire());
							requirement_1_reqDelivery.setType("project");
							requirement_1_reqDelivery.setReadOnly("true");
							dataItemDTOList.add(requirement_1_reqDelivery);
							
							DataItem requirement_1_reqQuality = new DataItem();
							requirement_1_reqQuality.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqQuality");
							requirement_1_reqQuality.setName("质量要求"); 
							requirement_1_reqQuality.setValue(subPreqEntry.getQualityAssurance());
							requirement_1_reqQuality.setType("project");
							requirement_1_reqQuality.setReadOnly("true");
							dataItemDTOList.add(requirement_1_reqQuality);
							
							DataItem requirement_1_reqWarrenty = new DataItem();
							requirement_1_reqWarrenty.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqWarrenty");
							requirement_1_reqWarrenty.setName("维修期限"); 
							requirement_1_reqWarrenty.setValue(subPreqEntry.getWarrentyLen());
							requirement_1_reqWarrenty.setType("project");
							requirement_1_reqWarrenty.setReadOnly("true");
							dataItemDTOList.add(requirement_1_reqWarrenty);
							
							DataItem requirement_1_reqAcceptStandard = new DataItem();
							requirement_1_reqAcceptStandard.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqAcceptStandard");
							requirement_1_reqAcceptStandard.setName("验收标准"); 
							requirement_1_reqAcceptStandard.setValue(subPreqEntry.getAcceptStandard());
							requirement_1_reqAcceptStandard.setType("project");
							requirement_1_reqAcceptStandard.setReadOnly("true");
							dataItemDTOList.add(requirement_1_reqAcceptStandard);
							
							DataItem requirement_1_memo = new DataItem();
							requirement_1_memo.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_memo");
							requirement_1_memo.setName("备注"); 
							requirement_1_memo.setValue(subPreqEntry.getRemark()); 
							requirement_1_memo.setType("project");
							requirement_1_memo.setReadOnly("true");
							dataItemDTOList.add(requirement_1_memo);
						}
					}
				}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			operDesc = false;
		}
		
		/*生成响应数据*/
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getProjectData xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(operDesc ? "Y" : "N")+"</operTag>");
		responseXML.append("<operDesc/>");
		responseXML.append("<operException>"+errorMessage+"</operException>");
		responseXML.append("<prjCode>"+ prjCode +"</prjCode>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<dataItemList>");
		for(DataItem dataItem : dataItemDTOList){
			responseXML.append(XmlUtil.objectToXml(dataItem));
		}
		responseXML.append("</dataItemList>");
		responseXML.append("</body>");
		responseXML.append("</getProjectData>");
		response.getWriter().write(responseXML.toString());
		response.getWriter().close();
	}
	
	/**
	 * 更新项目数据
	 * @param request
	 * @throws Exception
	 * @author: zhouzhanghe
	 * @Create Date:2011-6-30  上午09:58:27
	 */
	@RequestMapping(params = "method=uploadProjectData")
	public void uploadProjectData(HttpServletRequest request) throws Exception{
		
	}
	/**
	 * FuncName: getMoreEntryBailrecordProjectList
	 * Description : 得到待录入保证金的项目列表
	 * @param 
	 * @param request
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2011-8-10  下午01:12:51
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  下午01:12:51
	 */
	@RequestMapping(params = "method=getMoreEntryBailrecordProjectList")
	public ModelAndView getMoreEntryBailrecordProjectList(HttpServletRequest request)throws Exception{
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		Page page = prePage(request);
		String projIds="";
		  Set<String> projectSet = new HashSet<String>();	
		  List<Object> list = signUprecordService.getSignUprecordNotEntryBailRecord();
		     for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		    	  Object[] obj = (Object[]) iterator.next();
				  boolean flag = bailRecordService.isCanEntryBailRecord(obj);
				  if(flag){
					  if(obj[2]!=null){
						  projectSet.add((String)obj[2]);
					  }else{
						  projectSet.add((String)obj[1]);
					  }
				  }
			}
		     for (Iterator iterator1 = projectSet.iterator(); iterator1.hasNext();) {
				String projId = (String) iterator1.next();
				projIds += projId + ",";
			}
		 	 QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		 	 queryObject.getQueryParam().and(new QueryParam("projectIds",QueryParam.OPERATOR_EQ, (projIds.substring(0, projIds.length()-1)).replace(",", "','")));
		 	 queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		 	 queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		 	 Page pageData = projectService.searchProjectListForEntryBailRecord(queryObject, page);
		     Map model = new HashMap();
		     this.endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName: toJZGCSubProjectCraete
	 * Description: 到新增标段页面
	 * @param   request
	 * @return  ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-8-18 16:50
	 */
	@RequestMapping(params="method=toJZGCSubProjectCraete")
	public ModelAndView toJZGCSubProjectCraete(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toSubProjectCraete\n");
		String parentId = request.getParameter("parentId");
		Map model = new HashMap();
		List<Project> projectList = projectService.getSubProjectByProjectId(parentId);
		
		Project subProject = new Project();
		subProject.setParentId(parentId);
		subProject.setProjCode(String.valueOf(projectList.size() + 1));
		subProject.setProjName(NumberUtil.cnvtIntgStrgToChnsStrg(String.valueOf(projectList.size() + 1)) + "标段");
		model.put("subProject", subProject);
		return new ModelAndView("epp_projectCreateFormJCGC", model);
	}
	
	
	/** 
	 * FuncName: saveSubProjectJZGC
	 * Description: 保存标段
	 * @param   request
	 * @return  ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-8-19 08:51
	 */
	@RequestMapping(params="method=saveSubProjectJZGC")
	public ModelAndView saveSubProjectJZGC(HttpServletRequest request,Project project,SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||saveProjectAndReq\n");
		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
		if ("undefined".equals(ebuyMethod)) {
			ebuyMethod = projectService.getProjectByObjId(project.getParentId()).getEbuyMethod();
		}
		project.setEbuyMethod(ebuyMethod);
		projectService.saveSubProject(project);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName: toSubProjectUpdateJZGC
	 * Description: 到修改标段页面
	 * @param   request
	 * @return  ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-8-19 09:30
	 */
	@RequestMapping(params="method=toSubProjectUpdateJZGC")
	public ModelAndView toSubProjectUpdateJZGC(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toSubProjectUpdate\n");
		String subProjId = request.getParameter("subProjId");
		Map model = new HashMap();
		Project subProject = projectService.get(subProjId);
			 
		model.put("subProject", subProject);
		return new ModelAndView("epp_projectCreateFormJCGC", model);
	}
	
	/** 
	 * FuncName: removeProjectAndReq
	 * Description: 删除包组以及中间表
	 * @param   projectId:项目主键
	 * @return void
	 * @author zhouzhanghe  
	 * @Create Date: 2011-8-19 09:52 
	 */
	@RequestMapping(params="method=removeSubProject")
	public ModelAndView removeSubProject(HttpServletRequest request, String subProjectId) throws Exception {
		logger.debug("\nes=ProjectController||removeProjectAndReq\n");
		if(subProjectId==null||"".equals(subProjectId)){
			throw new Exception(EsExceptionEnum.PROJECTID_NOT_EXISTS);
		}
		projectService.removeSubProject(subProjectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	/** 
	 * FuncName : checkProjectBudget
	 * Description :  检查项目预算是否拆分完
	 * Create Date: 2011-11-9上午11:49:08 by yangx  
	 * Modified Date: 2011-11-9上午11:49:08 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=checkProjectBudget")
	public ModelAndView checkProjectBudget(HttpServletRequest request)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String projectId = request.getParameter("projectId");
		Boolean checkResult = projectService.checkProjectBudgetByProjectId(projectId);
		model.put("checkResult", checkResult);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/** 
	 * FuncName : finishSubProject
	 * Description :  完成分包
	 * Create Date: 2011-11-9下午01:12:36 by yangx  
	 * Modified Date: 2011-11-9下午01:12:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finishSubProject")
	public ModelAndView finishSubProject(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		projectService.finishSubProject(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toProjectBagView
	 * Description :  到项目包组列表查看页面
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午06:50:52
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午06:50:52
	 */
	@RequestMapping(params="method=toProjectBagView")
	public ModelAndView toProjectBagView(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes=ProjectController||toProjectBagView\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(projectId); //获取中间表数据
		List<Project> subProjects = projectService.getSubProjectByProjectId(projectId);  //获取项目对应的包组信息
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		if (project!=null) {//根据报名开始时间判断是否可以对做分包组进行新增、修改和删除
			if (projectRule.getSignUpSTime()!=null&&(projectRule.getSignUpSTime()).before(new Date())) {//判断是否有报名时间且报名时间大于今天
				model.put("isStart","yes");//设置标志位不能进行新增、修改和删除操作
			}
		}
		String role = checkRole(project,request);
		model.put("parentId", projectId);
		model.put("project", project);
		model.put("role", role);
		status.setComplete();//因页面操作需要，这个请求到达页面前需要清空session
		if(ProjectEnum.JZGC.equals(project.getTenderType())){		//如果是“建筑工程”
			model.put("subProjectList", subProjectList);
			model.put("subProjects",subProjects);
			return new ModelAndView("epp_projectCreateJzgc", model);
		}else{
			double quantity = 0.0;
			double money =0.00;
			for(Project proj:subProjects){
			  List <SubProjectMTaskPlanSub> subProjectMList = projectService.getSubProjectMTaskPlanSubBySubProjectId(proj.getObjId());
			  quantity=0.0;
			  money=0.00;
			  for(int j=0;j<subProjectMList.size();j++){
				quantity = projectService.add(quantity,new Double(subProjectMList.get(j).getQuantity().toString()));
				money = projectService.add(money,new Double(subProjectMList.get(j).getMoney().toString()));
			  }
			    proj.setRealCount(new BigDecimal(quantity)); //数量
				proj.setRealMoney(new BigDecimal(money));    //金额
			}
			model.put("subProjectList", subProjectList);
			model.put("subProjects",subProjects);
			return new ModelAndView("projectBagView", model);
		}
	}
	
	/**
	 * FuncName: toViewInputTenderInfo
	 * Description :  到招标信息查看页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午07:10:23
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午07:10:23
	 */
	@RequestMapping(params = "method=toViewInputTenderInfo")
	public ModelAndView toViewInputTenderInfo(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ProjectController||toViewInputTenderInfo\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);//获取项目
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//获取包组
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		List<BidRoom> bidRoomList = meetRoomService.getBidRoomByProjectId(projectId);
		BidRoom bidRoom = new BidRoom();
		for(BidRoom bid:bidRoomList) {
			if("01".equals(bid.getMeetRoom().getRoomType())) {
				 bidRoom = bid;
			}
		}
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanSubByTaskPlanId(projectId);
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("bidRoom", bidRoom);
		model.put("project",project);
		model.put("projectRule",projectRule);
		model.put("subProjectList",subProjectList);
		model.put("subProjectLength",subProjectList.size());
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("projProcessRule", projProcessRule);
		return new ModelAndView("toInputTenderInfoView", model);
	}
	/**
	 * FuncName: saveRecordProject
	 * Description :  监管科备案项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-19  下午02:05:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-19  下午02:05:23
	 */
	@RequestMapping(params = "method=saveRecordProject")
	public ModelAndView saveRecordProject(HttpServletRequest request){
		Map model = new HashMap();
		try {
			Project project = projectService.getProjectByObjId(request.getParameter("projectId"));
			project.setTenderRecord("01");
			projectService.save(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: saveDRecordProject
	 * Description :  监管科撤销项目备案
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-19  下午02:05:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-19  下午02:05:23
	 */
	@RequestMapping(params = "method=saveDRecordProject")
	public ModelAndView saveDRecordProject(HttpServletRequest request){
		Map model = new HashMap();
		try {
			Project project = projectService.getProjectByObjId(request.getParameter("projectId"));
			project.setTenderRecord("00");
			projectService.save(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
