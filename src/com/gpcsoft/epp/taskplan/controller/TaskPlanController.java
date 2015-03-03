package com.gpcsoft.epp.taskplan.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryProjections;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.BeanUtil;
import com.gpcsoft.epp.common.utils.UploadFileResult;
import com.gpcsoft.epp.common.utils.UploadFileUtil;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.service.ConsignService;
import com.gpcsoft.epp.consign.service.ConsignTaskPlanService;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.service.PreqEntryService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.service.TaskPlanDetailService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMDetailService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.EmployeeService;
import com.gpcsoft.srplatform.auth.service.OrganizationService;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;
import com.gpcsoft.srplatform.workPlan.domain.PlanTemplate;


/**
 * @gpcsoft.view value="taskPlanFormView"
 *  url="view/es/planform/taskplan/taskPlanForm.jsp"
 * @gpcsoft.view value="taskPlanFormViewForAgent"
 *  url="view/es/planform/taskplan/taskPlanFormForAgent.jsp"
 *  
  * @gpcsoft.view value="taskPlanFormView"
  *  url="view/es/planform/taskplan/taskPlanForm.jsp"
  * @gpcsoft.view value="taskPlanCreateFormView"
  *  url="view/es/planform/taskplan/taskPlanCreate.jsp"
  * @gpcsoft.view value="updateTaskPlanFormView"
  *  url="view/es/planform/taskplan/updateTaskPlanForm.jsp"
  * @gpcsoft.view value="taskPlanUpdateFormView"
  *  url="view/es/planform/taskplan/taskPlanUpdatem.jsp"
  * @gpcsoft.view value="taskPlanUpdateFormForAgent"
  *  url="view/es/planform/taskplan/taskPlanUpdateForAgent.jsp"
  *  
  * @gpcsoft.view value="taskPlanFormForSumView"
  *  url="view/es/planform/taskplan/taskPlanFormForSum.jsp"
  * @gpcsoft.view value="updateTaskPlanFormForSumView"
  *  url="view/es/planform/taskplan/updateTaskPlanFormForSum.jsp"
  * @gpcsoft.view value="taskPlanDetailView"
  *  url="view/es/planform/taskplan/taskPlanDetail.jsp"
  * @gpcsoft.view value="taskPlanDetailSubView"
  *  url="view/es/planform/taskplan/taskPlanDetailForSum.jsp"
  * @gpcsoft.view value="taskPlanDetailDivView"
  *  url="view/es/planform/taskplan/taskPlanDetailDiv.jsp"
  * @gpcsoft.view value="getAuthUser"
  *  url="view/es/planform/project/agent_assign1.jsp"
  * @gpcsoft.view value="authTaskPlan"
  *  url="view/es/planform/taskplan/taskPlanFormForAudit.jsp"
  * @gpcsoft.view value="getMoreForTaskPlanList"
  *  url="view/es/planform/taskplan/moreForTaskPlanList.jsp"
  *  @gpcsoft.view value="addTaskPlanListForSupe"
  *  url="view/es/planform/taskplan/addTaskPlanListForSupe.jsp"
  *  @gpcsoft.view value="modifyTaskPlanListForSupe"
  *  url="view/es/planform/taskplan/modifyTaskPlanListForSupe.jsp"
  *  @gpcsoft.view value="taskPlanListForConsign"
  *  url="view/es/planform/taskplan/taskPlanListForConsign.jsp"
  *  @gpcsoft.view value="requestContentPrintPage"
  *  url="view/es/common/RequestContentPrint.jsp"
  *  @gpcsoft.view value="addTaskPlanSubRequireInfo"
  *  url="view/es/planform/taskplan/taskPlanSubForm1.jsp"
  *  @gpcsoft.view value="lookeTaskPlanSubRequireInfo"
  *  url="view/es/planform/taskplan/lookTaskPlanSubFormInfo.jsp"
  *  @gpcsoft.view value="lookeTaskPlanDetailInfo"
  *  url="view/es/planform/taskplan/taskPlanDetail1.jsp"
  *  @gpcsoft.view value="taskPlanShowView"
  *  url="view/es/planform/taskplan/taskPlanShowDetail.jsp"
  *  @gpcsoft.view value="selectGovernmentLinkerView"
  *  url="view/es/planform/taskplan/SelectGovernmentLinkerBox.jsp"
  *  @gpcsoft.view value="selectAgentPage"
  *  url="view/es/planform/taskplan/selectAgentPage.jsp"
  *  @gpcsoft.view value="taskPlanDetailForSelectAgent"
  *  url="view/es/planform/taskplan/taskPlanDetailForSelectAgent.jsp"
  *  @gpcsoft.view value="auditTaskPlanForSelectAgentView"
  *  url="view/es/planform/taskplan/auditTaskPlanForSelectAgentView.jsp"
  *  @gpcsoft.view value="taskPlanListForCreateProj"
  *  url="view/es/planform/taskplan/taskPlanListForCreateProj.jsp"
  *  @gpcsoft.view value="taskPlanListForCreateProjBiXuan"
  *  url="view/es/planform/taskplan/taskPlanListForCreateProjBiXuan.jsp"
  *  @gpcsoft.view value="taskPlanListForCreateProjBiXuan2"
  *  url="view/es/planform/taskplan/taskPlanListForCreateProjBiXuan2.jsp"
  *  @gpcsoft.view value="taskPlanZFCGListForCreateProj"
  *  url="view/es/planform/taskplan/taskPlanZFCGListForCreateProj.jsp"
  *  @gpcsoft.view value="taskPlanTDJYListForCreateProj"
  *  url="view/es/planform/taskplan/taskPlanTDJYListForCreateProj.jsp"
  *  @gpcsoft.view value="taskPlanCQJYListForCreateProj"
  *  url="view/es/planform/taskplan/taskPlanCQJYListForCreateProj.jsp"
  *  @gpcsoft.view value="taskPlanJZGCListForCreateProj"
  *  url="view/es/planform/taskplan/taskPlanJZGCListForCreateProj.jsp" 
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlan.class})
@RequestMapping("/TaskPlanController.do")//页面请求路径,可修改
public class TaskPlanController extends AnnotationMultiController<TaskPlan> {

	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanServiceImpl")
	private ConsignTaskPlanService consignTaskPlanService;
	
	@Autowired(required=true) @Qualifier("organizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required = true) @Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("preqEntryServiceImpl")
	private PreqEntryService preqEntryService;
	@Autowired(required=true) @Qualifier("taskPlanMDetailServiceImpl")
	private TaskPlanMDetailService taskPlanMDetailService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required=true) @Qualifier("consignServiceImpl")
	private ConsignService consignService;
	
	@Autowired(required = true) @Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigService;

	@Autowired(required=true) @Qualifier("taskPlanDetailServiceImpl")
	private TaskPlanDetailService taskPlanDetailService;
	
	/** 
	 * Description :  获取不在项目中的申报书明细列表
	 * Create Date: 2011-9-20下午03:39:39 by sunl  Modified Date: 2011-9-20下午03:39:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTaskPlanSubNotInProject")
	public ModelAndView getTaskPlanSubNotInProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getTaskPlanSubListByNotblockAndPass\n");
	    QueryObject<TaskPlanSub> query = QueryWebUtils.getQuery(request, TaskPlanSub.class);
	    query.setEntityClass(TaskPlanSub.class);
	    initQueryColums(request, query);//构造前台的查询条件和指定列
	    String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		QueryProjections projections = new QueryProjections();
		projections.setProperty(queryColumns.split(","));
		query.setQueryProjections(projections);
		User user = AuthenticationHelper.getCurrentUser();
		String usedTaskPlanSub = taskPlanService.getTaskPlanSubInProject();//在项目申报书中间表中存在的申报书条目	
		query.getQueryParam().and(new QueryParam("taskAgentId",QueryParam.OPERATOR_EQ,((OrgInfo)user.getOrgInfo()).getObjId()));
		query.getQueryParam().and(new QueryParam("taskPlanSubIds",QueryParam.OPERATOR_EQ,(String)request.getParameter("taskPlanSubIds")));
		query.getQueryParam().and(new QueryParam("taskPlanSubIds_not",QueryParam.OPERATOR_EQ,usedTaskPlanSub));
		Page page = prePage(request);
		page = taskPlanService.getTaskPlanSubListByNotblockAndPass(page,query);
		Map model = new HashMap();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到申报书添加（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午02:54:36 by sunl  Modified Date: 2011-9-20下午02:54:36 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateTaskPlan")
	public ModelAndView toCreateTaskPlan()throws Exception {
		logger.debug("\nes=TaskPlanController||toCreateTaskPlanWwain\n");
		float moneySub = 0;//预算总金额,初始值为0
		float moneyDetail = 0;//总资金，初始值为0
		TaskPlan taskPlan = new TaskPlan();
		taskPlan.setGovernment(new Department());//业务处室
		taskPlan.setTaskCode(taskPlanService.createTaskPlanCodeByQO(null));//设定初始编号
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设定初始状态:临时
		taskPlan.setTaskType(TaskPlanTypeEnum.NORMAL);//申报书类型
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);

		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("taskPlanCreateFormView", model);
	}
	
	/** 
	 * Description :  跳转到申报书修改（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-21上午10:40:54 by sunl  Modified Date: 2011-9-21上午10:40:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateTaskPlan")
	public ModelAndView toUpdateTaskPlan(String objId)throws Exception {
		logger.debug("\nTaskPLanContorller||toUpdateTaskPlan\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);//获得对象
		if(taskPlan.getGovernment()==null){   //业务处室为空，说明是从xml导入的申报书
			taskPlan.setGovernment(new Department());
		}
		float moneySub = taskPlanService.getMoneySubByTaskPlan(taskPlan);//申报书明细预算总金额的和
		float moneyDetail = taskPlanService.getMoneyDetailByTaskPlan(taskPlan);//采购资金明细总资金的和
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("taskPlanUpdateFormView", model);
	}
	
	/** 
	 * Description :  跳转到申报书修改（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-21上午10:40:54 by sunl  Modified Date: 2011-9-21上午10:40:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateTaskPlanForAgent")
	public ModelAndView toUpdateTaskPlanForAgent(String objId)throws Exception {
		logger.debug("\nTaskPLanContorller||toUpdateTaskPlan\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);//获得对象
		if(taskPlan.getGovernment()==null){   //业务处室为空，说明是从xml导入的申报书
			taskPlan.setGovernment(new Department());
		}
		float moneySub = taskPlanService.getMoneySubByTaskPlan(taskPlan);//申报书明细预算总金额的和
		float moneyDetail = taskPlanService.getMoneyDetailByTaskPlan(taskPlan);//采购资金明细总资金的和
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("taskPlanUpdateFormForAgent", model);
	}
	
	/** 
	 * Description :  保存申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午03:15:58 by sunl  Modified Date: 2011-9-20下午03:15:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveTaskPlan")
	public ModelAndView saveTaskPlan(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nTaskPLanContorller||createTaskPlanTwain\n");
		
		//保存附件
		String attachRelaId = request.getParameter("attachRelaId"); 
		taskPlan.setAttachRelaId(attachRelaId);
		
		//获取机构信息
		Company buyerOrg = userApiService.getOrgnizationByOrgInfoId(taskPlan.getBudget().getObjId());
		
		//根据业主单位和姓名获取员工
		Employee leaderE = userApiService.getEmployeeByOrgAndName(buyerOrg.getObjId(), taskPlan.getLeaderName());
		
		//不存在业主单位那个员工，则保存
		if(leaderE == null) {
			Employee leader = taskPlan.getLeader();
			leader.setName(taskPlan.getLeaderName());
			leader.setCompany(buyerOrg);
			Employee obj = employeeService.save(leader);
			taskPlan.setLeader(obj);
		}	
		else {
			leaderE.setMobile(taskPlan.getLeader().getMobile());
			taskPlan.setLeader(leaderE);
		}

		taskPlanService.saveTaskPlan(taskPlan);
		//创建计划 edit by wangcl 二级机构获取上级机构的计划模板
		Organization org = (Organization)projectPlanService.get(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), Organization.class);
		String parentOrgId = (org.getParent()!=null&&org.getParent().getObjId()!=null)?org.getParent().getObjId():null;
		
		//暂时可不要创建项目计划，在立项阶段创建计划
		projectPlanService.createProjectPlan(null, parentOrgId,  taskPlan.getObjId(),PlanTemplate.TEMPLATE_CODE_TASKPLAN);
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  提交申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午03:15:58 by sunl  Modified Date: 2011-9-20下午03:15:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=submitTaskPlan")
	public ModelAndView submitTaskPlan(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes TaskPlanController||submitTaskPlan\n");	
		//保存附件
		String attachRelaId = request.getParameter("attachRelaId"); 
		taskPlan.setAttachRelaId(attachRelaId);
		
		//获取机构信息
		Company buyerOrg = userApiService.getOrgnizationByOrgInfoId(taskPlan.getBudget().getObjId());
		
		//根据业主单位和姓名获取员工
		Employee leaderE = userApiService.getEmployeeByOrgAndName(buyerOrg.getObjId(), taskPlan.getLeaderName());
		
		//不存在业主单位那个员工，则保存
		if(leaderE == null) {
			Employee leader = taskPlan.getLeader();
			leader.setName(taskPlan.getLeaderName());
			leader.setCompany(buyerOrg);
			Employee obj = employeeService.save(leader);
			taskPlan.setLeader(obj);
		}	
		else {
			leaderE.setMobile(taskPlan.getLeader().getMobile());
			taskPlan.setLeader(leaderE);
		}
		
		taskPlanService.submitTaskPlan(taskPlan);  
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  提交申报书（招标中心管理员或项目经理维护申报书）
	 * Create Date: 2011-9-20下午03:15:58 by sunl  Modified Date: 2011-9-20下午03:15:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=submitTaskPlanForAgent")
	public ModelAndView submitTaskPlanForAgent(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes TaskPlanController||submitTaskPlan\n");	
		//保存附件
		String attachRelaId = request.getParameter("attachRelaId"); 
		taskPlan.setAttachRelaId(attachRelaId);
		
		//业主单位,招标人的项目负责人不为空
		if(taskPlan.getLeader()!=null&&taskPlan.getLeader().getObjId()!=null){
			Employee employee=employeeService.get(taskPlan.getLeader().getObjId());
			taskPlan.setBudgetLinker(employee.getName());
		}
		
		//万元转换为元保存
		// String money= taskPlan.getTotalAmount().multiply(new BigDecimal(10000)).toString();
		 taskPlan.setTotalAmount(taskPlan.getTotalAmount().multiply(new BigDecimal(10000)));
		
		taskPlanService.submitTaskPlan(taskPlan);  
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toCreateTaskPlanWwain
	 * Description :  跳转到创建采购申报书页面(二级采购人)
	 * @param: 
	 * @return  ModelAndView
	 * @author: wanghz
	 * @throws Exception 
	 * @Create Date:2010-9-6 上午10:54:11  
	 * @Modifier: wanghz
	 * @Modified Date:2010-9-6 上午10:54:11
	 * @Modified at 2011-5-23 13:48 by zhouzhanghe
	 */	
	@RequestMapping(params = "method=toCreateTaskPlanWwain")
	public ModelAndView toCreateTaskPlanWwain()throws Exception {
		logger.debug("\nes=TaskPlanController||toCreateTaskPlanWwain\n");
		float moneySub = 0;//预算总金额,初始值为0
		float moneyDetail = 0;//总资金，初始值为0
		TaskPlan taskPlan = new TaskPlan();
		Organization org = organizationService.getTopOrg(AuthenticationHelper.getCurrentUser().getEmp().getCompany().getObjId(),"1");//获取主管单位及联系人信息
		OrgInfo o = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		taskPlan.setDepartmentName(org.getName());//设定机构名称
		taskPlan.setDepartmentLinker(org.getContact());//设定机构联系方式
		taskPlan.setDepartmentLinkerTel(org.getTel());//设定机构联系电话
		taskPlan.setDepartment((Company)org);//设定机构
		taskPlan.setTaskAgent(new OrgInfo());//代理机构
		taskPlan.setGovernment(new Department());//业务处室
		taskPlan.setTaskCode(taskPlanService.createTaskPlanCodeByQO(null));//设定初始编号
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设定初始状态:临时
		taskPlan.setTaskType(TaskPlanTypeEnum.NORMAL);//申报书类型
		taskPlan.setBudget(o);//采购主体
		taskPlan.setBudgetLinkerTel(o.getCompany().getTel());//联系电话
		taskPlan.setBudgetLinker(o.getCompany().getContact());//联系人
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("taskPlanFormView", model);
	}
	
	/** 
	 * Description :  代理机构维护申报书
	 * Create Date: 2011-9-22下午07:43:43 by sunl  Modified Date: 2011-9-22下午07:43:43 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateTaskPlanForAgent")
	public ModelAndView toCreateTaskPlanForAgent()throws Exception {
		logger.debug("\nes=TaskPlanController||toCreateTaskPlanWwain\n");
		float moneySub = 0;//预算总金额,初始值为0
		float moneyDetail = 0;//总资金，初始值为0
		TaskPlan taskPlan = new TaskPlan();
//		Organization org = organizationService.getTopOrg(AuthenticationHelper.getCurrentUser().getEmp().getCompany().getObjId(),"1");//获取主管单位及联系人信息
 		//OrgInfo o =userApiService.
 		List<OrgInfo> oApi=userApiService.getAllAgents(null);
//		taskPlan.setDepartmentName(org.getName());//设定机构名称
//		taskPlan.setDepartmentLinker(org.getContact());//设定机构联系方式
//		taskPlan.setDepartmentLinkerTel(org.getTel());//设定机构联系电话
//		taskPlan.setDepartment((Company)org);//设定机构
 		
		//代理机构
		if(oApi!=null&&oApi.size()>0){
	 		taskPlan.setTaskAgent(oApi.get(0));//代理机构
		}
		
		taskPlan.setGovernment(new Department());//业务处室
		taskPlan.setTaskCode(taskPlanService.createTaskPlanCodeByQO(null));//设定初始编号
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态:待确认
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设定初始状态:临时
		taskPlan.setTaskType(TaskPlanTypeEnum.NORMAL);//申报书类型
//		taskPlan.setBudgetLinkerTel(o.getCompany().getTel());//联系电话
//		taskPlan.setBudgetLinker(o.getCompany().getContact());//联系人
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		//获取机构下所有员工
		List<Employee> empList = userApiService.getEmpListByCurUserOrgId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());
		model.put("empList",empList);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("taskPlanFormViewForAgent", model);
	}
	
	/**
	 * FuncName: toSelectGovernmentLinkerView
	 * Description :  跳转到业务处室联系人
	 * @param: 
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date:2010-7-27 上午10:54:11  
	 * @Modifier: wanghz
	 * @Modified Date:2010-7-27 上午10:54:11
	 */	
	@RequestMapping(params = "method=toSelectGovernmentLinkerView")
	public ModelAndView toSelectGovernmentLinkerView()throws Exception {
		logger.debug("\nes=TaskPlanController||toSelectGovernmentLinkerView\n");
		return new ModelAndView("selectGovernmentLinkerView");
	}
	
	/**
	 * FuncName: createTaskPlanTwain
	 * Description :  保存创建采购申报书(二级采购人)
	 * @param: taskPlan:申报书,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date:2010-9-6 上午10:26:58   
	 * @Modifier: wanghz
	 * @Modified Date:2010-9-6 上午10:26:58 
	 */	
	@RequestMapping(params = "method=createTaskPlanTwain")
	public ModelAndView createTaskPlanTwain(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nTaskPLanContorller||createTaskPlanTwain\n");
		User user = AuthenticationHelper.getCurrentUser();//添加预算单位[默认为当前用户]
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		taskPlan.setBudget(orgInfo);
		taskPlan.setBudgetName(orgInfo.getOrgName());
		taskPlan.setGovernment(null);
		taskPlanService.saveCreateTaskPlanTwain(taskPlan);
		//创建计划 edit by wangcl 二级机构获取上级机构的计划模板
		Organization org = (Organization)projectPlanService.get(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), Organization.class);
		String parentOrgId = (org.getParent()!=null&&org.getParent().getObjId()!=null)?org.getParent().getObjId():null;
		projectPlanService.createProjectPlan(null, parentOrgId,  taskPlan.getObjId(),PlanTemplate.TEMPLATE_CODE_TASKPLAN);
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: createTaskPlanTwain
	 * Description :  保存创建采购申报书(二级采购人)
	 * @param: taskPlan:申报书,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date:2010-9-6 上午10:26:58   
	 * @Modifier: wanghz
	 * @Modified Date:2010-9-6 上午10:26:58 
	 */	
	@RequestMapping(params = "method=createTaskPlanForAgent")
	public ModelAndView createTaskPlanForAgent(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		//保存附件
		String attachRelaId = request.getParameter("attachRelaId"); 
		taskPlan.setAttachRelaId(attachRelaId);
		//业主单位,招标人的项目负责人不为空
		if(taskPlan.getLeader()!=null&&taskPlan.getLeader().getObjId()!=null){
			Employee employee=employeeService.get(taskPlan.getLeader().getObjId());
			taskPlan.setBudgetLinker(employee.getName());
		}
		//万元转换为元保存
		// String money= taskPlan.getTotalAmount().multiply(new BigDecimal(10000)).toString();
		 taskPlan.setTotalAmount(taskPlan.getTotalAmount().multiply(new BigDecimal(10000)));
		 taskPlanService.saveTaskPlan(taskPlan);
		
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: toUpdateTaskPlanWwain
	 * Description : 跳转到修改采购申报书(二级采购人)
	 * @param: objId:申报书主键
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6 上午10:54:50   
	 * @Modifier: wanghz
	 * @Modified Date: 2010-9-6 上午10:54:50  
	 * Modified at 2011-5-23 14:22 by zhouzhanghe  
	 */	
	@RequestMapping(params = "method=toUpdateTaskPlanWwain")
	public ModelAndView toUpdateTaskPlanWwain(String objId)throws Exception {
		logger.debug("\nTaskPLanContorller||toUpdateTaskPlanWwain\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);//获得对象
		if(taskPlan.getGovernment()==null){   //业务处室为空，说明是从xml导入的申报书
			taskPlan.setGovernment(new Department());
		}
		float moneySub = taskPlanService.getMoneySubByTaskPlan(taskPlan);//申报书明细预算总金额的和
		float moneyDetail = taskPlanService.getMoneyDetailByTaskPlan(taskPlan);//采购资金明细总资金的和
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("updateTaskPlanFormView", model);
	}
	
	/**
	 * FuncName: submitTaskPlanTwain
	 * Description : 提交采购申报书(二级采购人)
	 * @param: taskPlan:申报书对象
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6 上午10:54:50   
	 * @Modifier: wanghz
	 * @Modified Date: 2010-9-6 上午10:54:50  
	 */	
	@RequestMapping(params = "method=submitTaskPlanTwain")
	public ModelAndView submitTaskPlanTwain(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws EsException {
		logger.debug("\nes TaskPlanController||submitTaskPlanTwain\n");	
		taskPlan.setGovernment(null);
		taskPlanService.saveSubmitTaskPlanByBuyerLevel(taskPlan,TaskPlanTypeEnum.BUYER_TWO);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: removeTaskPlan
	 * Description :删除采购申报书
	 * @param: objId:申报书主键,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6 上午10:54:50   
	 * @Modifier: wanghz
	 * @Modified Date: 2010-9-6 上午10:54:50  
	 */	
	@RequestMapping(params = "method=removeTaskPlan")
	public ModelAndView removeTaskPlan(String objId, HttpServletRequest request,SessionStatus status)throws EsException {
		logger.debug("\nTaskPLanContorller||removeTaskPlan\n");
		taskPlanService.removeTaskPlan(objId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: showDetail
	 * Description :查看详细信息
	 * @param: objId:申报书主键,request
	 * @return  ModelAndView
	 * @author: liangxj
	 * @Create Date: 2010-5-20下午07:00:02   
	 * @Modifier: liangxj
	 * @Modified Date: 2010-5-20下午07:00:02   
	 */	
	@RequestMapping(params = "method=showDetail")
	public ModelAndView showDetail(String objId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||showDetail\n");
		String type = request.getParameter("type");
		String isChanged = request.getParameter("isChanged");
		String isBlockTrade = request.getParameter("isBlockTrade");
		String fromto = request.getParameter("fromto");
		String noConsign = request.getParameter("noConsign");
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
	  	TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);//获得申报书
	  	String returnUrl = "taskPlanDetailView";
	  	List<Consign> ctList = consignTaskPlanService.getConsignByTaskPlan(objId);	//获得委托协议
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("fromto", fromto);
		model.put("taskPlan", taskPlan);
		model.put("ctList", ctList);
		model.put("isBlockTrade", isBlockTrade);
		model.put("noConsign", noConsign);
		if(request.getParameter("from")!=null){
			model.put("from", request.getParameter("from"));
		}
		if("sub".equals(type)) {//表示跳转到提交审核的详情页面
			returnUrl = "taskPlanDetailSubView";
		}
		if("div".equals(type)) {//表示跳转到详细信息的div页面
			model.put("isChanged",isChanged);
			returnUrl = "taskPlanDetailDivView";
		}
		if("selectAgent".equals(type)){//跳转到选择代理机构的申报书详情页面
			returnUrl = "taskPlanDetailForSelectAgent";
		}
		
		/*Added at 2011-11-03 13:48 by chenhj  查看附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		model.put("budgetId",orgInfo.getObjId());
		return new ModelAndView(returnUrl, model);
	}	
	
	/**
	 * FuncName: toCreateTaskPlanStair
	 * Description :跳转到创建采购申报书(一级采购人)
	 * @param: 
	 * @return  ModelAndView
	 * @author: liangxj
	 * @throws Exception 
	 * @Create Date: 2010-5-20下午07:00:02   
	 * @Modifier: liangxj
	 * @Modified Date: 2010-5-20下午07:00:02   
	 * Modified at 2011-5-23 13:48 by zhouzhanghe
	 */	
	@SuppressWarnings("unused")
	@RequestMapping(params = "method=toCreateTaskPlanStair")
	public ModelAndView toCreateTaskPlanStair()throws Exception {
		logger.debug("\nes=TaskPlanController||toCreateTaskPlanStair\n");
		TaskPlan taskPlan = new TaskPlan();
	
		
		OrgInfo o = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		taskPlan.setTaskCode(taskPlanService.createTaskPlanCodeByQO(null));//设定初始编号
		taskPlan.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);//设定初始状态
		taskPlan.setAuditDetail(CommonEnum.CONFIRM_STATUS_WAIT);
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		taskPlan.setTaskType(TaskPlanTypeEnum.NORMAL);//申报书类型
		Organization org = organizationService.getTopOrg(AuthenticationHelper.getCurrentUser().getEmp().getCompany().getObjId(),"1");//获取主管单位及联系人信息
		taskPlan.setDepartmentName(org.getName());
		taskPlan.setDepartmentLinker(org.getContact());
		taskPlan.setDepartmentLinkerTel(org.getTel());
		taskPlan.setDepartment((Company)org);
		taskPlan.setTaskAgent(new OrgInfo());//代理机构和业务处室
		taskPlan.setGovernment(new Department());
		Employee emp = AuthenticationHelper.getCurrentUser().getEmp();
		taskPlan.setBudgetLinkerTel(org.getTel());//联系电话  
		taskPlan.setBudgetLinker(emp.getName());//联系人
		Map<String,Object> model = new HashMap<String,Object>();	
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		model.put("taskPlan", taskPlan);
		return new ModelAndView("taskPlanFormForSumView", model);
	}
	
	/**
	 * FuncName: createTaskPlanStair
	 * Description :保存创建采购申报书(一级采购人)
	 * @param: taskPlan:申报书对象,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6下午01:04:38  
	 * @Modifier: liangxj
	 * @Modified Date: 2010-9-6下午01:04:38   
	 * @Modified Date: 2011-3-31下午01:04:38  zhouzhanghe
	 */	
	@RequestMapping(params = "method=createTaskPlanStair")
	public ModelAndView createTaskPlanStair(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes TaskPlanController||createTaskPlanStair\n");
		User user = AuthenticationHelper.getCurrentUser();//添加预算单位[默认为当前用户]
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		taskPlan.setBudget(orgInfo);
		taskPlan.setBudgetName(orgInfo.getOrgName());
		taskPlanService.saveCreateTaskPlanStair(taskPlan);
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: toUpdateTaskPlanStair
	 * Description :跳转到修改采购申报书(一级采购人)
	 * @param: objId:申报书主键
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6下午01:04:38  
	 * @Modifier: liangxj
	 * @Modified Date: 2010-9-6下午01:04:38   
	 * Modified at 2011-5-23 13:48 by zhouzhanghe
	 */	
	@RequestMapping(params = "method=toUpdateTaskPlanStair")
	public ModelAndView toUpdateTaskPlanStair(String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||toUpdateTaskPlanStair\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);
		float moneySub = taskPlanMSubService.getTaskPlanMSubByStatus(objId);;
		float moneyDetail = taskPlanMDetailService.getTaskPlanMDetailByStatus(objId);
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail",moneyDetail);
		
		/*Added at 2011-5-23 13:48 by zhouzhanghe加入上传申报书附件功能*/
		List<SysConfigItem> sysConfigItemListString = sysConfigService.getSysConfigItemBySysConfigItemPathAndDataType(SysConfigEnum.TASKPLAN+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.TASKPLAN_ATTACHMENT+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		
		return new ModelAndView("updateTaskPlanFormForSumView", model);
	}
	
	/**
	 * FuncName: updateTaskPlanStair
	 * Description :保存修改采购申报书(一级采购人)
	 * @param: taskPlan:申报书对象,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6下午01:04:38  
	 * @Modifier: liangxj
	 * @Modified Date: 2010-9-6下午01:04:38   
	 */	
	@RequestMapping(params = "method=updateTaskPlanStair")
	public ModelAndView updateTaskPlanStair(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws EsException {
		logger.debug("\nes TaskPlanController||updateTaskPlanStair\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		taskPlan.setBudget(orgInfo);
		taskPlan.setBudgetName(orgInfo.getOrgName());
		taskPlanService.updateTaskPlanStair(taskPlan);  
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: submitTaskPlanStair
	 * Description :提交采购申报书(一级采购人)
	 * @param: taskPlan:申报书对象,request,status
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6下午01:04:38  
	 * @Modifier: liangxj
	 * @Modified Date: 2010-9-6下午01:04:38   
	 */	
	@RequestMapping(params = "method=submitTaskPlanStair")
	public ModelAndView submitTaskPlanStair(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws EsException {
		logger.debug("\nes TaskPlanController||submitTaskPlanStair\n");
		taskPlanService.saveSubmitTaskPlanByBuyerLevel(taskPlan,TaskPlanTypeEnum.BUYER_ONE);  
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: submitTwainTaskPlan
	 * Description : 提交申报书,一级采购人提交二级采购人申报书
	 * @param: objIds:申报书主键,request,opinion:审核意见
	 * @return  ModelAndView
	 * @author: liangxj
	 * @Create Date: 2010-6-6下午01:04:38  
	 * @Modifier: liangxj
	 * @Modified Date: 2010-6-6下午01:04:38   
	 */	
	@RequestMapping(params = "method=submitTwainTaskPlan")
	public ModelAndView submitTwainTaskPlan(String objIds,HttpServletRequest request,String opinion)throws Exception {
		logger.debug("\nes=TaskPlanController||submitTwainTaskPlan\n");
		taskPlanService.saveAuditBySuperior(objIds, opinion, true);
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName: rejectTwainTaskPlan
	 * Description : 退回申报书,一级采购人退回二级申报书
	 * @param: objIds:申报书主键,request,opinion:审核意见
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-7 上午09:47:41
	 * @Modifier: wanghz
	 * @Modified Date: 2010-9-7 上午09:47:41
	 */	
	@RequestMapping(params = "method=rejectTwainTaskPlan")
	public ModelAndView rejectTwainTaskPlan(String objIds,HttpServletRequest request,String opinion)throws Exception {
		logger.debug("\nes=TaskPlanController||rejectTwainTaskPlan\n");
		taskPlanService.saveAuditBySuperior(objIds, opinion, false);
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName: toSaveOrUpdate
	 * Description : 桌面待办:跳转到修改退回申报书页面[二级采购人]
	 * @param:  objId:申报书主键
	 * @return  ModelAndView
	 * @author: liangxj
	 * @Create Date: 2010-5-20下午07:00:02
	 * @Modifier: liangxj
	 * @Modified Date:2010-5-20下午07:00:02
	 */	
	@RequestMapping(params = "method=toSaveOrUpdate")
	public ModelAndView toSaveOrUpdate(String objId)throws Exception {
		logger.debug("\nes TaskPlanController||toSaveOrUpdate\n");
		Map<String,Object> model = new HashMap<String,Object>();
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);		
		float moneySub = taskPlanService.getMoneySubByTaskPlan(taskPlan);
		float moneyDetail = taskPlanService.getMoneyDetailByTaskPlan(taskPlan);
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		return new ModelAndView("updateTaskPlanFormView", model);
	}
	
	/**
	 * FuncName: toSaveOrUpdateForSum
	 * Description : 桌面待办:跳转到修改退回申报书页面[一级采购人]
	 * @param:  objId:申报书主键
	 * @return  ModelAndView
	 * @author: liangxj
	 * @Create Date: 2010-5-20下午07:00:02
	 * @Modifier: liangxj
	 * @Modified Date:2010-5-20下午07:00:02
	 */	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toSaveOrUpdateForSum")
	public ModelAndView toSaveOrUpdateForSum(String objId)throws Exception {
		logger.debug("\nes TaskPlanController||toSaveOrUpdateForSum\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);//获得对象;
		float moneySub = taskPlanMSubService.getTaskPlanMSubByStatus(objId);
		float moneyDetail = taskPlanMDetailService.getTaskPlanMDetailByStatus(objId);
		if(null==taskPlan.getTaskAgent()){
			OrgInfo taskAgent = new OrgInfo();
			taskPlan.setTaskAgent(taskAgent);
		}	
		Map model = new HashMap();	
		model.put("taskPlan", taskPlan);
		model.put("moneySub", moneySub);
		model.put("moneyDetail", moneyDetail);
		return new ModelAndView("updateTaskPlanFormForSumView", model);
	}
	
	/**
	 * FuncName: toBuyerTaskPlanPrintPage
	 * Description:跳转到打印申报书页面[采购人]
	 * @param:  request
	 * @return  ModelAndView
	 * @author: liuke
	 * @Create Date: 2010-7-13下午05:26:13
	 * @Modifier: liuke
	 * @Modified Date:2010-7-13下午05:26:13
	 */	
	@RequestMapping(params = "method=toBuyerTaskPlanPrintPage")
	public ModelAndView toBuyerTaskPlanPrintPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toBuyerTaskPlanPrintPage\n");
		String taskplanId = request.getParameter("taskPlanId");
		TaskPlan taskPlan = taskPlanService.get(taskplanId);
		if(null==taskPlan.getTaskAgentName()){
			taskPlan.setTaskAgentName("");
		}
		List<TaskPlanSub> taskPlanSubList = taskPlanService.getTaskPlanSubByTaskPlan(taskplanId);
		List<TaskPlanDetail> taskPlanDetailList = taskPlanService.getTaskPlanDetailByTaskPlan(taskplanId);
		Double totalSuperiorApp = 0.0;   //总计上级拨款
		Double totalLocalApp = 0.0;      //总计市级财政
		Double totalOwnerApp = 0.0;      //总计单位自筹
		Double totalOtherApp = 0.0;      //总计其他资金
		for(Iterator<TaskPlanDetail> iterator=taskPlanDetailList.iterator();iterator.hasNext();) {
			TaskPlanDetail taskPlanDetail = iterator.next();
			if(taskPlanDetail.getSuperiorApp()!=null){
				totalSuperiorApp += Double.valueOf(taskPlanDetail.getSuperiorApp());
			}
			if(taskPlanDetail.getLocalApp()!=null){
				totalLocalApp += Double.valueOf(taskPlanDetail.getLocalApp());
			}
			if(taskPlanDetail.getOwnerApp()!=null){
				totalOwnerApp += Double.valueOf(taskPlanDetail.getOwnerApp());
			}
			if(taskPlanDetail.getOtherApp()!=null){
				totalOtherApp += Double.valueOf(taskPlanDetail.getOtherApp());
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskPlan", taskPlan);
		model.put("taskPlanSubList", taskPlanSubList);
		model.put("taskPlanDetailList", taskPlanDetailList);
		model.put("totalSuperiorApp", totalSuperiorApp.toString());
		model.put("totalLocalApp", totalLocalApp.toString());
		model.put("totalOwnerApp", totalOwnerApp.toString());
		model.put("totalOtherApp", totalOtherApp.toString());
		String content = freeMarkerService.getFreeMarkerContent("buyerTaskPlanPrintPage.ftl", model);//设置申报书打印模板 
		this.shallContentPlacedSession(request,content);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: auditStockBankrollPass
	 * Description:审核采购资金[通过]
	 * @param:  taskPlan:申报书对象,ids:申报书主键,request,status,opinion:审核意见
	 * @return  ModelAndView
	 * @author: wanghz
	 * @Create Date: 2010-9-6下午05:26:13
	 * @Modifier: wanghz
	 * @Modified Date:2010-9-6下午05:26:13
	 */	
	@RequestMapping(params = "method=auditStockBankrollPass")
	public ModelAndView auditStockBankrollPass(TaskPlan taskPlan,String ids,HttpServletRequest request, SessionStatus status,String opinion)throws Exception {
		logger.debug("\nes TaskPlanController||auditStockBankrollReject\n");
		if(ids == null||"".equals(ids)){
			ids = taskPlan.getObjId();
		}
		taskPlanService.saveAuditStockBankroll(ids, opinion,true);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:auditStockBankrollReject
	 * Description: 审核采购资金[退回]
	 * @param taskPlan:申报书对象,ids:申报书主键,request,status,opinion:意见
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date:2010-9-6 下午02:15:12 
	 * @Modifier: wanghz
	 * @Modified Date:2010-9-6 下午02:15:12 
	 */
	@RequestMapping(params = "method=auditStockBankrollReject")
	public ModelAndView auditStockBankrollReject(TaskPlan taskPlan,String ids ,HttpServletRequest request, SessionStatus status,String opinion)throws Exception {
		logger.debug("\nes TaskPlanController||auditStockBankrollReject\n");
		if(ids == null||ids.equals("")){
			ids = taskPlan.getObjId();
		}
		taskPlanService.saveAuditStockBankroll(ids, opinion,false);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:getAuthUser
	 * Description : 获得项目负责人
	 * @param request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-6下午05:50:50 
	 * @Modifier: yangx
	 * @Modified Date:2010-6-6下午05:50:50 
	 * 
	 */
	@RequestMapping(params = "method=getAuthUser")
	public ModelAndView getAuthUser(HttpServletRequest request) {
		logger.debug("\nes=TaskPlanController||getAuthUser\n");
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		String css =request.getParameter("css");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);
		User user = AuthenticationHelper.getCurrentUser();
		List<Employee> empList= null;
		if(user.getEmp()!=null){
			empList= userApiService.getAllEmpListByCurUserEmpId(user.getEmp().getObjId());
		}
		if(css!=null){
			model.put("css", "false");
		}
		model.put("taskPlan",taskPlan);
		model.put("empList",empList);
		return new ModelAndView("getAuthUser", model);
	}
	
	/**
	 * FuncName:submitLeader
	 * Description:保存指定项目负责人
	 * @param taskPlan:申报书对象,request,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-6下午05:50:50 
	 * @Modifier: yangx
	 * @Modified Date:2010-6-6下午05:50:50 
	 * 
	 */
	@RequestMapping(params = "method=submitLeader")
	public ModelAndView submitLeader(TaskPlan taskPlan,HttpServletRequest request, SessionStatus status) {
		logger.debug("\nes=TaskPlanController||submitLeader\n");
		taskPlanService.saveLeader(taskPlan);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName:submitLeaderNoPass
	 * Description :采购办主任退回申报书
	 * @param taskPlan:采购计划,request,status
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-12-19上午10:40:22    
	 * @Modifier liuke
	 * @Modified Date: 2010-12-19上午10:40:22 
	 */
	@RequestMapping(params = "method=submitLeaderNoPass")
	public ModelAndView submitLeaderNoPass(TaskPlan taskPlan,HttpServletRequest request, SessionStatus status) {
		logger.debug("\nes=TaskPlanController||submitLeaderNoPass\n");
		taskPlan.setConfirmStatus(TaskPlanConfirmEnum.AUDIT_NO_PASS);
		taskPlanService.saveLeader(taskPlan);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toAuditTaskPlanPage
	 * Description:到审核申报书页面
	 * @param:  request,objId:申报书主键
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:50:50 
	 * @Modifier:liangxj
	 * @Modified Date: 2010-6-6下午05:50:50 by liangxj
	 */
	@RequestMapping(params = "method=toAuditTaskPlanPage")
	public ModelAndView toAuditTaskPlanPage(HttpServletRequest request, String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||toAuditTaskPlanPage\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);
		Map<String,TaskPlan> model = new HashMap<String,TaskPlan>();
		model.put("taskPlan",taskPlan);
		return new ModelAndView("authTaskPlan", model);				
	}
	
	/**
	 * FuncName:auditStockTaskPlanPass
	 * Description: 审核采购申报书[通过]
	 * @param taskPlan:申报书对象,ids:申报书主键,request,status,opinion:审核意见
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date:2010-9-6 下午02:15:12 
	 * @Modifier:wanghz
	 * @Modified Date:2010-9-6 下午02:15:12 
	 */
	@RequestMapping(params = "method=auditStockTaskPlanPass")
	public ModelAndView auditStockTaskPlanPass(TaskPlan taskPlan,String ids,HttpServletRequest request, SessionStatus status,String opinion)throws Exception {
		logger.debug("\nes TaskPlanController||auditStockTaskPlanPass\n");
		if(ids==null||"".equals(ids)){
			ids = taskPlan.getObjId();
		}
		OrgInfo taskagent = null;
		if(taskPlan.getTaskAgent()!=null){
		 taskagent = (OrgInfo) taskPlanService.get(taskPlan.getTaskAgent().getObjId(), OrgInfo.class);
		}
		taskPlanService.saveAuditStockTaskPlan(ids, opinion,true,taskagent);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:auditStockTaskPlanReject
	 * Description: 审核采购申报书[退回]
	 * @param taskPlan:申报书对象,ids:申报书主键,request,status,opinion:审核意见
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午02:15:12 
	 * @Modifier:wanghz
	 * @Modified Date:2010-9-6 下午02:15:12 
	 */
	@RequestMapping(params = "method=auditStockTaskPlanReject")
	public ModelAndView auditStockTaskPlanReject(TaskPlan taskPlan,String ids ,HttpServletRequest request, SessionStatus status,String opinion)throws Exception {
		logger.debug("\nes TaskPlanController||auditStockBankrollReject\n");
		if(ids==null||ids.equals("")){
			ids = taskPlan.getObjId();
		}
		taskPlanService.saveAuditStockTaskPlan(ids, opinion,false,null);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:toSelectAgentPage
	 * Description :跳转到抽取代理机构页面  
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-12上午10:39:15   
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-12上午10:39:15  
	 */
	@RequestMapping(params = "method=toSelectAgentPage")
	public ModelAndView toSelectAgentPage(HttpServletRequest request)throws Exception{
	  logger.debug("\nes TaskPlanController||toSelectAgentPage\n");
	  String selectNum = messageSource.getMessage("selectNum");
	  String taskplanId = request.getParameter("objId");
	  String buyerLevel = request.getParameter("buyerType");
	  TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(taskplanId);
	  Map<String,Object> model = new HashMap<String,Object>();
	  model.put("taskPlan", taskPlan);
	  model.put("buyerLevel", buyerLevel);
	  model.put("selectNum", selectNum);
	 return new ModelAndView("selectAgentPage",model);	
	}
	
	/**
	 * FuncName:saveSelectAgentForTaskplan
	 * Description :保存选择代理机构的申报书  
	 * @param   request
	 * @return  ModelAndView
	 * @author: liuke
	 * @Create Date: 2010-10-12下午04:58:55 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-12下午04:58:55 by liuke
	 */
	@RequestMapping(params = "method=saveSelectAgentForTaskplan")
	public ModelAndView saveSelectAgentForTaskplan(HttpServletRequest request)throws Exception{
		 logger.debug("\nes TaskPlanController||saveSelectAgentForTaskplan\n");
		 String taskplanId = request.getParameter("taskplanId");
		 String drawType = request.getParameter("drawType");
		 String agentId = request.getParameter("selectAgentId");
		
		 taskPlanService.saveTaskPlanForSelectAgent(taskplanId,drawType,agentId);//保存选择代理机构的申报书  
		 return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	/**
	 * FuncName:toAuditTaskPlanSelectForAgentPage
	 * Description :跳转到审核申报书代理机构页面  
	 * @param   request,objId:申报书主键
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-13上午10:32:14   
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-13上午10:32:14 
	 */
	@RequestMapping(params = "method=toAuditTaskPlanSelectForAgentPage")
	public ModelAndView toAuditTaskPlanSelectForAgentPage(HttpServletRequest request, String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||searchProjectForSupervise\n");
		TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(objId);
		Map<String,TaskPlan> model = new HashMap<String,TaskPlan>();
		model.put("taskPlan",taskPlan);
		return new ModelAndView("auditTaskPlanForSelectAgentView", model);				
	}
	
	/**
	 * FuncName:auditTaskPlanForSelectAgent
	 * Description :审核申报书代理机构  
	 * @param   ids:申报书主键,confirmStatus:审核状态,opinion:审核意见
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-13上午10:59:02 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-13上午10:59:02 
	 */
	@RequestMapping(params = "method=auditTaskPlanForSelectAgent")
	public ModelAndView auditTaskPlanForSelectAgent(String ids,String confirmStatus,  SessionStatus status,String opinion)throws Exception {
		logger.debug("\nes TaskPlanController||auditTaskPlanForSelectAgent\n");
		taskPlanService.auditTaskPlanForSelectAgent(ids, opinion, confirmStatus);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrg
	 * Description :  根据机构，获得其下级机构的未汇总的采购书
	 * @param orgId:代理机构主键,request
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:50:50 by liangxj  
	 * @Modifier:liangxj
	 * @Modified Date: 2010-6-6下午05:50:50 by liangxj
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getSubNotSumSubsByOrg")
	public ModelAndView getSubNotSumSubsByOrg(String orgId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getSubNotSumSubsByOrg\n");
		Map<String, Object> model = new HashMap<String, Object>();
		Page page = prePage(request);//预分页,算出当前页和大小等
		String taskCode = request.getParameter("taskCode");
		String applyDate = request.getParameter("applyDate");
		Page<TaskPlan> pageData = taskPlanService.getSubNotSumSubsByOrg(page,orgId,TaskPlanSumEnum.SUMMARY,taskCode,applyDate);
		model.put("total", Long.valueOf(pageData.getTotalRowNum()));
		model.put("page", Long.valueOf(page.getStart()/(long)page.getPageSize() +1));
		model.put("rows", pageData.getData());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:getSubNotSumSubsByOrg
	 * Description :  根据机构，获得其下级机构的未汇总的所有采购书
	 * @param orgId:代理机构主键,request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2011-2-11下午13:50:50 
	 * @Modifier:shenjz
	 * @Modified Date: 2011-2-11下午13:50:50 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getSubAllNotSumSubsByOrg")
	public ModelAndView getSubAllNotSumSubsByOrg(String orgId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getSubNotSumSubsByOrg\n");
		Map<String, Object> model = new HashMap<String, Object>();
		Page page = prePage(request);//预分页,算出当前页和大小等
		String taskCode = request.getParameter("taskCode");
		String applyDate = request.getParameter("applyDate");
		Page<TaskPlan> pageData = taskPlanService.getSubAllNotSumSubsByOrg(page,orgId,TaskPlanSumEnum.SUMMARY,taskCode,applyDate);
		model.put("total", Long.valueOf(pageData.getTotalRowNum()));
		model.put("page", Long.valueOf(page.getStart()/(long)page.getPageSize() +1));
		model.put("rows", pageData.getData());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:getMoreForTaskPlanList
	 * Description : 桌面待办:跳转到申报书信息列表 <更多...>功能
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-13上午11:39:09 
	 * @Modified:yangx
	 * @Modified Date: 2010-7-13上午11:39:09
	 */
	@RequestMapping(params="method=getMoreForTaskPlanList")
	public ModelAndView getMoreForTaskPlanList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getMoreForTaskPlanList\n");
		String useStatus = request.getParameter("useStatus");
		String confirmStatus = request.getParameter("confirmStatus");
		String auditDetail = request.getParameter("auditDetail");
		String leader = request.getParameter("leader");//待审核的申报书 
		String governmentId = request.getParameter("governmentId");//待审核资金的申报书
		String taskAgentId = request.getParameter("taskAgentId");//待委托的申报书 
		Map<String,String> model = new HashMap<String,String>();
		model.put("useStatus", useStatus);
		model.put("confirmStatus", confirmStatus);
		model.put("auditDetail", auditDetail);
		model.put("leader", leader);
		model.put("governmentId", governmentId);
		model.put("taskAgentId", taskAgentId);
		return new ModelAndView("getMoreForTaskPlanList", model);
	}
	
	/** 
	 * FuncName:getTaskPlanList
	 * Description:桌面待办:根据条件查询申报书 <更多...>功能
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-13下午01:09:32 
	 * @Modifier:yangx
	 * @Modified Date: 2010-7-13下午01:09:32 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getTaskPlanList")
	public ModelAndView getTaskPlanList(HttpServletRequest request)throws Exception {
		logger.debug("\nTaskPlanController||getTaskPlanList\n");
		String taskCode = request.getParameter("taskCode");
		String taskName = request.getParameter("taskName");
		String useStatus = request.getParameter("useStatus");
		String confirmStatus = request.getParameter("confirmStatus");
		String auditDetail = request.getParameter("auditDetail");
		String leader = request.getParameter("leader");
		String governmentId = request.getParameter("governmentId");
		String isSubmit = request.getParameter("isSubmit");    //是否为待提交申报书
		String taskAgentId = request.getParameter("taskAgentId");
		String taskType = request.getParameter("taskType");   //是否为大宗交易
		User user=AuthenticationHelper.getCurrentUser();
		Map<String,Object> model = new HashMap<String,Object>();
		Page<TaskPlan> page = prePage(request);
		Page<TaskPlan> pageData = taskPlanService.getTaskPlanPageByQueryObject(taskCode,taskName,useStatus,confirmStatus,auditDetail,leader,governmentId,isSubmit,taskAgentId,taskType,user,page);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:auditAllTaskPlan
	 * Description: 批量审核采购资金
	 * @param status,taskId:申报书主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-8-9 上午10:52:57 
	 */
	@RequestMapping(params = "method=auditAllTaskPlan")
	public ModelAndView auditAllTaskPlan(SessionStatus status,String taskId)throws Exception {
		logger.debug("\nes=TaskPlanController||auditAllTaskPlan\n");
		if(null == taskId || "".equals(taskId)){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.AUDIT_ALL_TASKPLAN_ERROR));
		}
		taskPlanService.saveAuditAllTaskPlan(taskId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:getTaskPlanSubListByNotblockAndPass
	 * Description : 得到非大宗已审核通过待起草委托协议的申报书明细列表[根据当前代理机构操作人，过滤数据]
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-8上午09:39:48 
	 * @Modifier:liuke
	 * @Modified Date: 2010-7-8上午09:39:48  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTaskPlanSubListByNotblockAndPass")
	public ModelAndView getTaskPlanSubListByNotblockAndPass(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getTaskPlanSubListByNotblockAndPass\n");
	    QueryObject<TaskPlanSub> query = QueryWebUtils.getQuery(request, TaskPlanSub.class);
	    query.setEntityClass(TaskPlanSub.class);
	    initQueryColums(request, query);//构造前台的查询条件和指定列
	    String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		QueryProjections projections = new QueryProjections();
		projections.setProperty(queryColumns.split(","));
		query.setQueryProjections(projections);
		User user = AuthenticationHelper.getCurrentUser();
		query.getQueryParam().and(new QueryParam("taskAgentId",QueryParam.OPERATOR_EQ,((OrgInfo)user.getOrgInfo()).getObjId()));
		query.getQueryParam().and(new QueryParam("taskPlanSubIds",QueryParam.OPERATOR_EQ,(String)request.getParameter("taskPlanSubIds")));
		query.getQueryParam().and(new QueryParam("taskPlanSubIds_not",QueryParam.OPERATOR_EQ,(String)request.getParameter("taskPlanSubIds_not")));
		Page page = prePage(request);
		page = taskPlanService.getTaskPlanSubListByNotblockAndPass(page,query);
		Map model = new HashMap();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:getTaskPlanSubListByConfirmConsign
	 * Description :  得到所有已确认委托协议后的申报书明细列表
	 * @param request
	 * @return ModelAndView
	 * @author liuy
	 * @Create Date: 2010-7-15下午02:25:38 
	 * @Modifier:liuy
	 * @Modified Date: 2010-7-15下午02:25:38 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTaskPlanSubListByConfirmConsign")
	public ModelAndView getTaskPlanSubListByConfirmConsign(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getTaskPlanSubListByConfirmConsign\n");
//		QueryObject<TaskPlanSub> query = QueryWebUtils.getQuery(request, TaskPlanSub.class);
//	    query.setEntityClass(TaskPlanSub.class);
//	    initQueryColums(request, query);//构造前台的查询条件和指定列
//	    String queryColumns = makeQueryColumns(request);//接收前台的指定列名
//		QueryProjections projections = new QueryProjections();
//		projections.setProperty(queryColumns.split(","));
//		query.setQueryProjections(projections);
//		User user = AuthenticationHelper.getCurrentUser();
//		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
//		String taskType = request.getParameter("taskType"); //申报书类型
//		String usedTaskPlanSub = taskPlanService.getTaskPlanSubInProject();//在项目申报书中间表中存在的申报书条目	
//		String taskPlanSubIds_not = request.getParameter("taskPlanSubIds_not");//已经勾选的申报书条目
		//将不需要查出的申报书条目合并
//		if(usedTaskPlanSub != null && usedTaskPlanSub.length() > 1){//若获取的已立项的申报书明细有数据，则用方法一去拼装ID
//			usedTaskPlanSub += ","+taskPlanSubIds_not;
//		}else{
//			usedTaskPlanSub = taskPlanSubIds_not;
//		}
//		query.getQueryParam().and(new QueryParam("taskAgentId",QueryParam.OPERATOR_EQ,((OrgInfo)user.getOrgInfo()).getObjId()));
//		query.getQueryParam().and(new QueryParam("taskPlanSubIds",QueryParam.OPERATOR_EQ,(String)request.getParameter("taskPlanSubIds")));
//		query.getQueryParam().and(new QueryParam("taskPlanSubIds_not",QueryParam.OPERATOR_EQ,taskPlanSubIds_not));
//		query.getQueryParam().and(new QueryParam("ebuyMethod",QueryParam.OPERATOR_EQ,ebuyMethod));
//		query.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,taskType));
		User user = AuthenticationHelper.getCurrentUser();
		String budgetName = request.getParameter("budgetName");//预算单位名称
		String purchaseName = request.getParameter("purchaseName");//采购名称
		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
		String taskType = request.getParameter("taskType"); //申报书类型
		String taskPlanSubIds_not = request.getParameter("taskPlanSubIds_not");//已经勾选的申报书条目
		String agentId = ((OrgInfo)user.getOrgInfo()).getObjId();
		Page page = prePage(request);
		page = taskPlanService.getTaskPlanSubListByConfirmConsign(page,budgetName,purchaseName,ebuyMethod,taskType,taskPlanSubIds_not,agentId);
		Map model = new HashMap();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:getmodifyTaskPlanSubListByConfirmConsign
	 * Description:修改项目：得到所有已确认委托协议后的申报书明细列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2下午05:19:23 
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午05:19:23 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getmodifyTaskPlanSubListByConfirmConsign")
	public ModelAndView getmodifyTaskPlanSubListByConfirmConsign(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||getmodifyTaskPlanSubListByConfirmConsign\n");
//		QueryObject<TaskPlanSub> query = QueryWebUtils.getQuery(request, TaskPlanSub.class);
//	    query.setEntityClass(TaskPlanSub.class);
//	    initQueryColums(request, query);//构造前台的查询条件和指定列
//	    String queryColumns = makeQueryColumns(request);//接收前台的指定列名
//		QueryProjections projections = new QueryProjections();
//		projections.setProperty(queryColumns.split(","));
//		query.setQueryProjections(projections);
//		String usedTaskPlanSub = taskPlanService.getTaskPlanSubInProject(projectId);//在项目申报书中间表中存在的申报书条目
//		String taskPlanSubIds_not = request.getParameter("taskPlanSubIds_not");//已经勾选的申报书条目
//		//将不需要查出的申报书条目合并
//		if(usedTaskPlanSub != null && usedTaskPlanSub.length() > 1){//若获取的已立项的申报书明细有数据，则用方法一去拼装ID
//			usedTaskPlanSub += usedTaskPlanSub;
//			if (taskPlanSubIds_not!=null&&!"".equals(taskPlanSubIds_not)) {
//				if (taskPlanSubIds_not.startsWith(",")) {
//					usedTaskPlanSub += taskPlanSubIds_not;
//				}else {
//					usedTaskPlanSub += ","+taskPlanSubIds_not;
//				} 
//			}
//		}else{
//			if (taskPlanSubIds_not!=null&&!"".equals(taskPlanSubIds_not)) {
//				usedTaskPlanSub += taskPlanSubIds_not;
//			}
//		}
//		query.getQueryParam().and(new QueryParam("taskAgentId",QueryParam.OPERATOR_EQ,((OrgInfo)user.getOrgInfo()).getObjId()));
//		query.getQueryParam().and(new QueryParam("taskPlanSubIds",QueryParam.OPERATOR_EQ,(String)request.getParameter("taskPlanSubIds")));
//		query.getQueryParam().and(new QueryParam("taskPlanSubIds_not",QueryParam.OPERATOR_EQ,usedTaskPlanSub));
//		query.getQueryParam().and(new QueryParam("ebuyMethod",QueryParam.OPERATOR_EQ,ebuyMethod));
//		query.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,taskType));
		User user = AuthenticationHelper.getCurrentUser();
		String budgetName = request.getParameter("budgetName");//预算单位名称
		String purchaseName = request.getParameter("purchaseName");//采购名称
		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
		String taskType = request.getParameter("taskType"); //申报书类型
		String taskPlanSubIds_not = request.getParameter("taskPlanSubIds_not");//已经勾选的申报书条目
		String agentId = ((OrgInfo)user.getOrgInfo()).getObjId();
		Page page = prePage(request);
		page = taskPlanService.getTaskPlanSubListByConfirmConsign(page,budgetName,purchaseName,ebuyMethod,taskType,taskPlanSubIds_not,agentId);
		Map model = new HashMap();
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:toTaskPlanSubListPageForSuperaddition
	 * Description:跳转到追加申报书条目页面
	 * @param  request
	 * @return ModelAndView
	 * @author Administrator
	 * @Create Date: 2010-7-13下午05:26:13 by Administrator
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-13下午05:26:13 by Administrator
	 */
	@RequestMapping(params = "method=toTaskPlanSubListPageForSuperaddition")
	public ModelAndView toTaskPlanSubListPageForSuperaddition(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toTaskPlanSubListPageForSuperaddition\n");
		Map<String,Object> model = new HashMap<String,Object>();
		String  budgetName = request.getParameter("budgetName");
		model.put("budgetName",budgetName);
		model.put(Page.PAGE_SIZE_NAME, request.getParameter(Page.PAGE_SIZE_NAME));
		model.put("taskPlanSubIds_not",request.getParameter("taskPlanSubIds_not"));
		model.put("ebuyMethod", request.getParameter("ebuyMethod"));
		model.put("taskType", request.getParameter("taskType"));
		return new ModelAndView("addTaskPlanListForSupe", model);
	}
	
	/** 
	 * FuncName:toModifyTaskPlanSubListPageForSuperaddition
	 * Description:修改项目：跳转到追加申报书条目页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date:2010-9-2下午05:15:08  
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午05:15:08 
	 */
	@RequestMapping(params = "method=toModifyTaskPlanSubListPageForSuperaddition")
	public ModelAndView toModifyTaskPlanSubListPageForSuperaddition(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toModifyTaskPlanSubListPageForSuperaddition\n");
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(Page.PAGE_SIZE_NAME, request.getParameter(Page.PAGE_SIZE_NAME));
		model.put("taskPlanSubIds_not",request.getParameter("taskPlanSubIds_not"));
		model.put("ebuyMethod", request.getParameter("ebuyMethod"));
		model.put("taskType", request.getParameter("taskType"));
		model.put("projectId", request.getParameter("projectId"));
		return new ModelAndView("modifyTaskPlanListForSupe", model);
	}
	
	/** 
	 * FuncName:toTaskPlanListPageForConsign
	 * Description:跳转到审核通过申报书页面[为了起草委托协议]
	 * @param request
	 * @return ModelAndView
	 * @author Administrator
	 * @Create Date: 2010-7-13下午05:26:13 
	 * @Modifier:Administrator
	 * @Modified Date: 2010-7-13下午05:26:13  
	 */
	@RequestMapping(params = "method=toTaskPlanListPageForConsign")
	public ModelAndView toTaskPlanListPageForConsign(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toTaskPlanListPageForConsign\n");
		User user = AuthenticationHelper.getCurrentUser();
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("orgInfo", (OrgInfo)user.getOrgInfo());
		return new ModelAndView("taskPlanListForConsign");
	}
		
	/**
	 * FuncName:toAgentTaskPlanPrintPage
	 * Description:跳转到打印申报书页面[业务处室]
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-21下午08:13:11 
	 * @Modifier: liuke
	 * @Modified Date: 2010-7-21下午08:13:11 
	 */
	@RequestMapping(params = "method=toAgentTaskPlanPrintPage")
	public ModelAndView toAgentTaskPlanPrintPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toAgentTaskPlanPrintPage\n");
		String taskplanId = request.getParameter("taskPlanId");
		TaskPlan taskPlan = taskPlanService.get(taskplanId);
		List<TaskPlanSub> taskPlanSubList = taskPlanService.getTaskPlanSubByTaskPlan(taskplanId);
		List<TaskPlanDetail> taskPlanDetailList = taskPlanService.getTaskPlanDetailByTaskPlan(taskplanId);
		Double totalSuperiorApp = 0.0;   //总计上级拨款
		Double totalLocalApp = 0.0;      //总计市级财政
		Double totalOwnerApp = 0.0;      //总计单位自筹
		Double totalOtherApp = 0.0;      //总计其他资金
		for(Iterator<TaskPlanDetail> iterator=taskPlanDetailList.iterator();iterator.hasNext();) {
			TaskPlanDetail taskPlanDetail = iterator.next();
			if(taskPlanDetail.getSuperiorApp()!=null){
				totalSuperiorApp += Double.valueOf(taskPlanDetail.getSuperiorApp());
			}
			if(taskPlanDetail.getLocalApp()!=null){
				totalLocalApp += Double.valueOf(taskPlanDetail.getLocalApp());
			}
			if(taskPlanDetail.getOwnerApp()!=null){
				totalOwnerApp += Double.valueOf(taskPlanDetail.getOwnerApp());
			}
			if(taskPlanDetail.getOtherApp()!=null){
				totalOtherApp += Double.valueOf(taskPlanDetail.getOtherApp());
			}
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskPlan", taskPlan);
		model.put("taskPlanSubList", taskPlanSubList);
		model.put("taskPlanDetailList", taskPlanDetailList);
		model.put("totalSuperiorApp", totalSuperiorApp.toString());
		model.put("totalLocalApp", totalLocalApp.toString());
		model.put("totalOwnerApp", totalOwnerApp.toString());
		model.put("totalOtherApp", totalOtherApp.toString());
		String content = freeMarkerService.getFreeMarkerContent("agentTaskPlanPrintPage.ftl", model);//设置申报书打印模板 
		this.shallContentPlacedSession(request,content);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:shallContentPlacedSession
	 * Description: 将预览内容放置session中
	 * @param reqeust,content:预置的内容
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-1-19 下午04:41:56 
	 */
	@RequestMapping(params = "method=shallContentPlacedSession")
	public ModelAndView shallContentPlacedSession(HttpServletRequest reqeust, String content)throws Exception {
		logger.debug("\nes=TaskPlanController||shallContentPlacedSession\n");
		if(null == content || "".equals(content) || "NULL".equals(content.toUpperCase())){
			content = reqeust.getParameter("content");
		}
		reqeust.getSession().setAttribute("content", content);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toRequestContentView
	 * Description: 跳转到预览打印页面
	 * @param:
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-1-19 下午04:43:06 
	 */
	@RequestMapping(params = "method=toRequestContentView")
	public ModelAndView toRequestContentView()throws Exception {
		logger.debug("\nes=TaskPlanController||toRequestContentView\n");
		return new ModelAndView("requestContentPrintPage");
	}
	
	/**
	 * FuncName:toAddTaskPlanSubRequireInfoView
	 * Description: 添加申报书明细需求信息
	 * @param:
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-26 上午11:31:03 
	 */
	@RequestMapping(params = "method=toAddTaskPlanSubRequireInfoView")
	public ModelAndView toAddTaskPlanSubRequireInfoView()throws Exception {
		logger.debug("\nes=TaskPlanController||toAddTaskPlanSubRequireInfoView\n");
		return new ModelAndView("addTaskPlanSubRequireInfo");
	}
	
	/**
	 * FuncName:toLookTaskPlanSubRequireInfoView
	 * Description: 查看申报书明细需求信息
	 * @param:
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-26 上午11:30:44  
	 * 
	 */
	@RequestMapping(params = "method=toLookTaskPlanSubRequireInfoView")
	public ModelAndView toLookTaskPlanSubRequireInfoView()throws Exception {
		logger.debug("\nes=TaskPlanController||toLookTaskPlanSubRequireInfoView\n");
		return new ModelAndView("lookeTaskPlanSubRequireInfo");
	}
	
	/**
	 * FuncName:toLookeTaskPlanDetailInfo
	 * Description:查看申报书
	 * @param objId 申报书ID
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-26 上午11:30:13  
	 */
	@RequestMapping(params = "method=toLookeTaskPlanDetailInfo")
	public ModelAndView toLookeTaskPlanDetailInfo(String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||toLookeTaskPlanDetailInfo\n");
		TaskPlan taskPlan = taskPlanService.get(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskPlan", taskPlan);
		return new ModelAndView("lookeTaskPlanDetailInfo",model);
	}
	
	/**
	 * FuncName:taskPlanPrintView
	 * Description: 获取申报书预览内容
	 * @param objId 申报书ID
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-26 上午11:29:42  
	 */
	@RequestMapping(params = "method=taskPlanPrintView")
	public ModelAndView taskPlanPrintView(HttpServletRequest request,String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||taskPlanPrintView\n");
		TaskPlan taskPlan = taskPlanService.get(objId);
		List<PreqEntry> preqEntryList = preqEntryService.getAllByTaskPlanId(objId);// 获取申报书条目
		List<TaskPlanDetail> taskPlanDetailList = taskPlanMDetailService.getDetailByTaskPlan(objId);
		if(null == taskPlanDetailList){
			taskPlanDetailList = new ArrayList<TaskPlanDetail>(1);
		}
		StringBuffer returnContent = new StringBuffer();// 定义样式
		returnContent.append("<style><!--");
		returnContent.append("tr{mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes;height:34.5pt}");
		returnContent.append("td{width:83.4pt;border:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:34.5pt}");
		returnContent.append("th{width:83.4pt;border:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:34.5pt}");
		returnContent.append("table{width:600.85pt;border-collapse:collapse;mso-yfti-tbllook:1184;mso-padding-alt:0cm 0cm 0cm 0cm;border:solid windowtext 1.0pt;}");
		returnContent.append("p{text-indent:30.0pt;line-height:150%;width:600.85pt;}");
		returnContent.append("--></style>");
		returnContent.append("<br><br><br><br><p style='text-indent:100pt'><b><span style='font-size:36.0pt;font-family:宋体;color:red'>");// 标题
		returnContent.append(taskPlan.getTaskName()+"申报书</span></b>");
		returnContent.append("<br><br><br>"+this.generatotRowInfo("一、申报书信息","15.0pt"));// 申报书信息
		returnContent.append("<table border=1 cellspacing=0 cellpadding=0 width=700><tbody><tr>");
		returnContent.append("<th><label>编号：</label></th>");
		returnContent.append("<td>"+taskPlan.getTaskCode()+"</td>");
		returnContent.append("<th><label>申报书名称：</label></th>");
		returnContent.append("<td>"+taskPlan.getTaskName()+"</td></tr>");
		returnContent.append("<tr><th><label>采购方式：</label></th>");
		returnContent.append("<td>"+taskPlan.getEbuyMethodCN()+"</td>");
		returnContent.append("<th><label>申报书类型：</label></th>");
		returnContent.append("<td>"+taskPlan.getTaskTypeCN()+"</td></tr>");
		returnContent.append("<tr><th><label>申请日期：</label></th>");
		returnContent.append("<td>"+taskPlan.getApplyDate()+"</td>");
		returnContent.append("<th><label style='white-space:nowrap;'>期望完成时间：</label></th>");
		returnContent.append("<td>"+taskPlan.getFinishDate()+"</td></tr>");
		returnContent.append("<tr><th><label>代理机构：</label></th>");
		returnContent.append("<td>"+taskPlan.getTaskAgentName()+"</td>");
		returnContent.append("<th><label style='white-space:nowrap;'>预算单位联系人：</label></th>");
		returnContent.append("<td>"+taskPlan.getBudgetLinker()+"</td></tr>");
		returnContent.append("<tr><th><label style='white-space:nowrap;'>预算单位联系电话：</label></th>");
		returnContent.append("<td colspan='3'>"+taskPlan.getBudgetLinkerTel()+"</td></tr>");
		returnContent.append("<tr><th><label>业务处室：</label></th>");
		returnContent.append("<td>"+taskPlan.getGovernmentName()+"</td>");
		returnContent.append("<th><label style='white-space:nowrap;'>业务处室联系人：</label></th>");
		returnContent.append("<td>"+taskPlan.getGovLinker()+"</td></tr>");
		returnContent.append("<tr><th><label style='white-space:nowrap;'>业务处室联系电话</label></th>");
		returnContent.append("<td colspan='3'>"+taskPlan.getGovLinkerTel()+"</td></tr>");
		returnContent.append("<tr><th><label>主管单位：</label></th>");
		returnContent.append("<td>"+taskPlan.getDepartmentName()+"</td>");
		returnContent.append("<th><label style='white-space:nowrap;'>主管单位联系人：</label></th>");
		returnContent.append("<td>"+taskPlan.getDepartmentLinker()+"</td></tr>");
		returnContent.append("<tr><th><label style='white-space:nowrap;'>主管单位联系电话：</label></th>");
		returnContent.append("<td colspan='3'>"+taskPlan.getDepartmentLinkerTel()+"</td></tr>");
		returnContent.append("</tbody></table>");
		returnContent.append("<p style='width:600.85pt;'>-----------------------------------------------------------------------------------------------------------------------------------</p>");
		returnContent.append(this.generatotRowInfo("二、申报书明细信息","15.0pt"));// 申报书明细
		for(PreqEntry preqEntry:preqEntryList){
			returnContent.append("<table border=1 cellspacing=0 cellpadding=0 width=700><tr>");
			returnContent.append("<th><label>采购品目：</th>");
			returnContent.append("<td><div>"+preqEntry.getTaskPlanSub().getPurchaseName()+"</div></td>");
			returnContent.append("<th><label>计量单位：</label></th>");
			returnContent.append("<td><div>"+preqEntry.getTaskPlanSub().getUnit()+"</div></td>");
			returnContent.append("<th><label>规格：</label></th>");
			returnContent.append("<td><div>"+preqEntry.getSpec()+"</div></td></tr>");
			returnContent.append("<tr><th><label>数量：</label></th>");
			returnContent.append("<td><div>"+preqEntry.getTaskPlanSub().getQuantity()+"</div></td>");
			returnContent.append("<th><label style='white-space:nowrap;'>预算金额（元）：</label></th>");
			returnContent.append("<td><div>"+preqEntry.getTaskPlanSub().getTotalPrice()+"</div></td>");
			returnContent.append("<th><label>保修期：</label></th>");
			returnContent.append("<td><div>"+preqEntry.getWarrentyLen()+"</div></td></tr></table>");
			returnContent.append("<p>1、付款条件</p><p><span>"+preqEntry.getPaymentClause()+"</span></p>");
			returnContent.append("<p>2、交货要求</p><p><span>"+preqEntry.getDeliveryRequire()+"</span></p>");
			returnContent.append("<p>3、服务承诺</p><p><span>"+preqEntry.getServicePromise()+"</span></p>");
			returnContent.append("<p>4、质量保证</p><p><span>"+preqEntry.getQualityAssurance()+"</span></p>");
			returnContent.append("<p>5、验收标准</p><p><span>"+preqEntry.getAcceptStandard()+"</span></p>");
			returnContent.append("<p>6、技术要求</p><p>"+preqEntry.getTechnical()+"</p>");
			returnContent.append("<p style='width:600.85pt;'>-----------------------------------------------------------------------------------------------------------------------------------</p>");
		}
		returnContent.append(this.generatotRowInfo("三、采购资金明细","15.0pt"));// 采购资金明细
		int i=1;
		for(TaskPlanDetail taskPlanDetail:taskPlanDetailList){
			returnContent.append("<p><span>"+i+"、<b>批准文号</b>"+taskPlanDetail.getApprovalNumber()+"</span>、&nbsp;&nbsp;");
			returnContent.append("<span><b>上级拨款</b>"+taskPlanDetail.getSuperiorApp()+"(元)</span>、&nbsp;&nbsp;");
			returnContent.append("<span><b>本级财政</b>"+taskPlanDetail.getLocalApp()+"(元)</span>、&nbsp;&nbsp;");
			returnContent.append("<span><b>自筹资金</b>"+taskPlanDetail.getOwnerApp()+"(元)</span>、&nbsp;&nbsp;");
			returnContent.append("<span><b>其他资金</b>"+taskPlanDetail.getOtherApp()+"(元)</span>、&nbsp;&nbsp;");
			returnContent.append("<span><b>总资金</b>"+taskPlanDetail.getQuantity()+"(元)。</span></p>");
			i++;
		}
		request.getSession().setAttribute("content", returnContent.toString().replaceAll("null","").replaceAll("\n","</br>"));
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toTaskPlanShowView
	 * Description: 查看申报书
	 * @param objId:申报书主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-27 上午09:55:58 
	 */
	@RequestMapping(params = "method=toTaskPlanShowView")
	public ModelAndView toTaskPlanShowView(String objId)throws Exception {
		logger.debug("\nes=TaskPlanController||toTaskPlanShowView\n");
		TaskPlan taskPlan = taskPlanService.get(objId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskPlan", taskPlan);
		return new ModelAndView("taskPlanShowView",model);
	}
	
	/**
	 * FuncName:generatotRowInfo
	 * Description: 生成行标题信息
	 * @param titleName:题目,fontSize:字体大小
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-26 下午02:39:22
	 */
	private String generatotRowInfo(String titleName,String fontSize)throws Exception {
		logger.debug("\nes=TaskPlanController||generatotRowInfo\n");
		StringBuffer returnContent = new StringBuffer();
		returnContent.append("<p style='text-indent:30.0pt;line-height:150%'>");
		returnContent.append("<b><span style='font-size:"+fontSize+";line-height:150%;font-family:宋体;mso-ascii-font-family:仿宋_GB2312;mso-hansi-font-family:仿宋_GB2312'><br>");
		returnContent.append(titleName);
		returnContent.append("</span></b></p>");
		return returnContent.toString();
	}
	
	/**
	 * FuncName:auditAllStapleTaskPlan
	 * Description: 批量审核大宗申报书,通过
	 * @param status,taskId申报书主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-8-9 下午01:27:30  
	 */
	@SuppressWarnings("unused")
	@RequestMapping(params = "method=auditAllStapleTaskPlan")
	private ModelAndView auditAllStapleTaskPlan(SessionStatus status,String taskId)throws Exception {
		logger.debug("\nes=TaskPlanController||auditAllStapleTaskPlan\n");
		if(null == taskId || "".equals(taskId)){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.AUDIT_ALL_STAPLE_TASKPLAN_ERROR));
		}
		taskPlanService.saveAuditAllStapleTaskPlan(taskId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:updateTaskPlanTwain
	 * Description: 更新采购申报书(二级采购人)
	 * @param taskPlan 申报书
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 上午10:26:58  
	 */
	@RequestMapping(params = "method=updateTaskPlanTwain")
	public ModelAndView updateTaskPlanTwain(TaskPlan taskPlan, HttpServletRequest request,SessionStatus status)throws EsException {
		logger.debug("\nTaskPLanContorller||updateTaskPlanTwain\n");
		
		//万元转找为元保存
		 taskPlan.setTotalAmount(taskPlan.getTotalAmount().multiply(new BigDecimal(10000)));
		//业主单位,招标人的项目负责人不为空
		if(taskPlan.getLeader()!=null&&taskPlan.getLeader().getObjId()!=null){
				Employee employee=employeeService.get(taskPlan.getLeader().getObjId());
				taskPlan.setBudgetLinker(employee.getName());
		}
		taskPlanService.updateTaskPlanTwain(taskPlan);  
		status.setComplete();
		Map<String,String> model = new HashMap<String,String>();
		model.put("taskPlanId", taskPlan.getObjId());
		model.put("taskCode", taskPlan.getTaskCode());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:auditColligateTaskPlanReject
	 * Description: 审核大宗申报书[退回]
	 * @param taskPlan:申报书对象,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午02:15:12 
	 */
	@RequestMapping(params = "method=auditColligateTaskPlanReject")
	public ModelAndView auditColligateTaskPlanReject(TaskPlan taskPlan,HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes TaskPlanController||auditColligateTaskPlanReject\n");
		String opinion = request.getParameter("opinion");
		taskPlanService.saveAuditColligateTaskPlanReject(taskPlanService.get(taskPlan.getObjId()),opinion);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:auditColligateTaskPlanPass
	 * Description: 审核大宗申报书[通过]
	 * @param taskPlan:申报书对象,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午02:15:12 
	 */
	@RequestMapping(params = "method=auditColligateTaskPlanPass")
	public ModelAndView auditColligateTaskPlanPass(TaskPlan taskPlan,HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes TaskPlanController||auditColligateTaskPlanPass\n");
		String opinion = request.getParameter("opinion");
		taskPlanService.saveAuditColligateTaskPlanPass(taskPlanService.get(taskPlan.getObjId()),opinion);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);				
	}
	
	/**
	 * FuncName:selectAgent
	 * Description :随机抽取代理机构  
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-12下午03:41:00
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-12下午03:41:00 
	 */
	@RequestMapping(params = "method=selectAgent")
	public ModelAndView selectAgent(HttpServletRequest request)throws Exception{
		logger.debug("\nes TaskPlanController||selectAgent\n");
		 String selectAgents = request.getParameter("selectAgents"); //获得抽取代理机构列表的Id
		 OrgInfo agent = null;
		 if(selectAgents.equals("")){//如果抽取代理机构列表的Id为"",则在所有代理机构中随机抽取一个
			List<OrgInfo> agentList = userApiService.getAllAgents(null);
				Random rd = new Random();
				agent = agentList.get(rd.nextInt(agentList.size()-1));
		 }else{
			 String agentId = taskPlanService.getRandomAgent(selectAgents);//随机抽取代理机构
			 agent  = (OrgInfo) taskPlanService.get(agentId, OrgInfo.class);
		 }
		 Map<String,Object> model = new HashMap<String,Object>();
		 model.put("agent", agent);
		 return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	 * FuncName:getNoSetupProjectLeader
	 * Description: 获取未指定项目经办人申报书
	 * @param type{count:记录条数,data:数据集合}
	 * @return  ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-2 下午01:30:38  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getNoSetupProjectLeader")
	public ModelAndView getNoSetupProjectLeader(String type)throws EsException {
		logger.debug("\nes TaskPlanController||getNoSetupProjectLeader\n");
		Map model = new HashMap();
		List<TaskPlan> taskPlanList = taskPlanService.getNoSetupProjectLeader(type);
		model.put("taskPlanList", taskPlanList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :保存代理机构退回的申报书  
	 * @param    request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@RequestMapping(params = "method=saveReturnTaskplanByAgent")
	public ModelAndView saveReturnTaskplanByAgent(HttpServletRequest request)throws Exception{
		logger.debug("\nes TaskPlanController||saveReturnTaskplanByAgent\n");
		 String opinion = request.getParameter("opinion");
		 String taskplanId = request.getParameter("taskplanId");
		 if(request.getParameter("consignId")!=null&&request.getParameter("consignId")!=""){
			 consignService.removeNotBlockConsign(request.getParameter("consignId"));//删除已经保存的委托协议
		 }
		 taskPlanService.saveReturnTaskplanByAgent(opinion, taskplanId);
		 Map<String,Object> model = new HashMap<String,Object>();
		 return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	 * @Description: 导入申报书XML
	 * @return ModelAndView
	 * @throws Exception 
	 * @throws Exception
	 * @Create Date 2011-4-11 上午09:26:52 By liuke
	 */
	@RequestMapping(params = "method=inputTaskplanXML")
	public ModelAndView inputTaskplanXML(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		response.setCharacterEncoding("UTF-8");
		Boolean flag = new Boolean(true);   //是否重复保存申报书标示
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo budget = userApiService.getOrgInfoByUser(user);
		UploadFileUtil uploadFile = new UploadFileUtil();
		UploadFileResult result = uploadFile.getDataObject(request, "xmlUrl",new String[]{UploadFileResult.FILE_TYPE_XML});
		List<TaskPlan> taskplanList = new ArrayList<TaskPlan>();
		String errorMessage = "";
		if (result.isResult()) {
			org.dom4j.Document document = (org.dom4j.Document)result.getObject();
			Element element =  document.getRootElement();
			try {
				java.util.List<org.dom4j.Element> reportPlanDataElements = element.elements("reportPlan");// 获取reportPlan节点
				for (org.dom4j.Element reportPlanDataElement:reportPlanDataElements){
					TaskPlan taskPlan = (TaskPlan)BeanUtil.getObjectByElement(reportPlanDataElement);
					org.dom4j.Element departmentElement = reportPlanDataElement.element("department");
					org.dom4j.Element departmentElement2 = reportPlanDataElement.element("orgCode");
					OrgInfo org = userApiService.getOrgInfoByOrgCode(departmentElement2.getTextTrim());
					Company company = new Company();
					company.setObjId(org.getCompany().getObjId());
					taskPlan.setDepartment(company);//设置主管单位ID,因为company里无objId属性，工具类无法赋值，暂时手动赋值。
					taskPlan.getBudget().setObjId(org.getObjId());
					taskPlan.getBudget().setOrgCode(org.getOrgCode());
					taskPlan.setCreateUser(user); //设置申报书创建人为当前导入xml用户。
					taskPlan.setUseStatus(CommonEnum.USER_STATUS_TEMP); //设置申报书状态为草稿
					java.util.List<org.dom4j.Element> taskPlanMSubsDataElements = reportPlanDataElement.elements("taskPlanMSubs");// 获取taskPlanMSubs节点
					for (org.dom4j.Element dataEmenet:taskPlanMSubsDataElements) {
						TaskPlanMSub taskPlanMSub = (TaskPlanMSub)BeanUtil.getObjectByElement(dataEmenet);
						java.util.List<org.dom4j.Element> taskPlanSubDataElements = dataEmenet.elements("taskPlanSub");
						TaskPlanSub taskPlanSub = (TaskPlanSub)BeanUtil.getObjectByElement(taskPlanSubDataElements.get(0));
						taskPlanSub.setCreateUser(taskPlan.getCreateUser());//设置申报书条目创建人
						taskPlanSub.setTotalPrice(new BigDecimal(this.doubleNotE(Double.parseDouble(taskPlanSub.getTotalPrice().toString())*10000)));
						PurCategory purchase = (PurCategory) taskPlanService.get(taskPlanSub.getPurchase().getObjId(), PurCategory.class);
						if(purchase!=null){
							taskPlanSub.setPurchaseName(purchase.getCategoryName());
						}
						taskPlanSub.setBudgetName(taskPlan.getBudgetName());
						Boolean isSum = taskPlanMSub.getIsSum();
						if(isSum){//是汇总的
							taskPlanMSub.setStatus(TaskPlanSumEnum.SUMMARY);// 普通
						}else{
							taskPlanMSub.setStatus(TaskPlanSumEnum.NORMAL);//  汇总
						}
						taskPlanMSub.setTaskPlan(taskPlan);
						taskPlanMSub.setTaskPlanSub(taskPlanSub);
						taskPlan.getTaskPlanMSubs().add(taskPlanMSub);
					}
					
					java.util.List<org.dom4j.Element> taskPlanMDetailsDataElements = reportPlanDataElement.elements("taskPlanMDetails");// 获取taskPlanMDetails节点
					for (org.dom4j.Element dataEmenet:taskPlanMDetailsDataElements) {
						TaskPlanMDetail taskPlanMDetail = (TaskPlanMDetail)BeanUtil.getObjectByElement(dataEmenet);
						java.util.List<org.dom4j.Element> taskPlanDetailDataElements = dataEmenet.elements("taskPlanDetail");
						TaskPlanDetail taskPlanDetail = (TaskPlanDetail)BeanUtil.getObjectByElement(taskPlanDetailDataElements.get(0));
						taskPlanDetail.setCreateUser(taskPlan.getCreateUser());
						taskPlanDetail.setSuperiorApp(this.doubleNotE(Double.parseDouble(taskPlanDetail.getSuperiorApp())*10000));
						taskPlanDetail.setLocalApp(this.doubleNotE(Double.parseDouble(taskPlanDetail.getLocalApp())*10000));
						taskPlanDetail.setOwnerApp(this.doubleNotE(Double.parseDouble(taskPlanDetail.getOwnerApp())*10000));
						taskPlanDetail.setOtherApp(this.doubleNotE(Double.parseDouble(taskPlanDetail.getOtherApp())*10000));
						taskPlanDetail.setQuantity(this.doubleNotE(Double.parseDouble(taskPlanDetail.getQuantity())*10000));
						Boolean isSum = taskPlanMDetail.getIsSum();
						if(isSum){//是汇总的
							taskPlanMDetail.setStatus(TaskPlanSumEnum.SUMMARY);// 普通
						}else{
							taskPlanMDetail.setStatus(TaskPlanSumEnum.NORMAL);//  汇总
						}
						taskPlanMDetail.setTaskPlan(taskPlan);
						taskPlanMDetail.setTaskPlanDetail(taskPlanDetail);
						taskPlan.getTaskPlanMDetails().add(taskPlanMDetail);
					}	
					taskplanList.add(taskPlan);
				}
				for(TaskPlan taskPlan:taskplanList){
					String orgCode = taskPlan.getBudget().getOrgCode();
					if(!budget.getOrgCode().equals(orgCode)){ //判断预算单位是否为当前单位
						flag = false;
						errorMessage = "导入的申报书采购人与当前用户不一致,请检查导入文件是否正确！";
						break;
					}
					boolean isExit = taskPlanService.getTaskPlanIsExsit(taskPlan);
					if(isExit==true){  //判断申报书是否已经存在
						flag = false;
						errorMessage = "已存在此申报书，不能重复导入！";
						break;
					}
				}
				if(!flag){
					model.put("result",errorMessage);
					return new ModelAndView(Constants.JSON_VIEW,model);
				}
				if(flag){
					taskPlanService.saveTaskPlan(taskplanList);
					model.put("result","成功导入申报书！");
				}
			} catch (Exception e) {
				model.put("result","xml导入异常,请联系管理员!");
			}
		}else{
			model.put("result","xml文档格式错误！");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * 将double数据转化为不用科学计数法显示
	 */
	public String  doubleNotE(Double e){
		NumberFormat formatter = NumberFormat.getNumberInstance();//用来构建一个数字转化的工厂类方法
		formatter.setMaximumFractionDigits(8);//控制数字的显示,设置数的小数部分的最大位数
		String sdouble = formatter.format(e);//调用方法对数字进行转化
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sdouble.length(); i++) {
			char s = sdouble.charAt(i);
			if (s != ',') {
				sb.append(s);
			}
		}
		return sb.toString();
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toTaskPlanListForCreateProj")
	public ModelAndView toTaskPlanListForCreateProj(HttpServletRequest request)throws Exception{
		 Map model = new HashMap();
		 List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		 List<MessageCode> messageList = new ArrayList<MessageCode>();
		 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 if(!ProjectEnum.DLBX.equals(mc.getCode())){
				 messageList.add(mc);
			 }
		}
	   
		/* Added at 2011-10-20 by liuke，对messageList排序 */
		 Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		 model.put("messageList", messageList);
		return new ModelAndView("taskPlanListForCreateProj",model);	
	}
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达笔选项目立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toTaskPlanListForCreateProjBiXuan")
	public ModelAndView toTaskPlanListForCreateProjBiXuan(HttpServletRequest request)throws Exception{
		 Map model = new HashMap();
		 List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		 List<MessageCode> messageList = new ArrayList<MessageCode>();
		 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 if(ProjectEnum.DLBX.equals(mc.getCode())){
				 messageList.add(mc);
			 }
		}
	   
		/* Added at 2011-10-20 by liuke，对messageList排序 */
		 Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		 model.put("messageList", messageList);
		return new ModelAndView("taskPlanListForCreateProjBiXuan",model);	
	}
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达建筑工程非笔选项目立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toTaskPlanListForCreateProjBiXuan2")
	public ModelAndView toTaskPlanListForCreateProjBiXuan2(HttpServletRequest request)throws Exception{
		 Map model = new HashMap();
		 List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		 List<MessageCode> messageList = new ArrayList<MessageCode>();
		 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 if(ProjectEnum.DLBX.equals(mc.getCode())){
				 messageList.add(mc);
			 }
		}
	   
		/* Added at 2011-10-20 by liuke，对messageList排序 */
		 Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		 model.put("messageList", messageList);
		return new ModelAndView("taskPlanListForCreateProjBiXuan2",model);	
	}
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :跳转到政府采购任务书立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * Created at 2011-5-25 13:32 by zhouzhanghe
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toZFCGTaskPlanListForCreateProj")
	public ModelAndView toZFCGTaskPlanListForCreateProj(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for(String str : list){
			if(ProjectEnum.ZFCG.equals(str.split(":")[0])){
				 MessageCode mc = new MessageCode(str.split(":")[0], str.split(":")[1]);
				 messageList.add(mc);
			}
		}
		
		model.put("ebuyMethodList",EbuyMethodEnum.getEbuyMethodByTaskType(ProjectEnum.ZFCG));
		model.put("messageList", messageList);
		return new ModelAndView("taskPlanZFCGListForCreateProj",model);	
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :跳转到建筑工程任务书立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * Created at 2011-5-25 13:32 by zhouzhanghe
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toJZGCTaskPlanListForCreateProj")
	public ModelAndView toJZGCTaskPlanListForCreateProj(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for(String str : list){
			if(ProjectEnum.JZGC.equals(str.split(":")[0])){
				MessageCode mc = new MessageCode(str.split(":")[0], str.split(":")[1]);
				messageList.add(mc);
			}
		}
		
		model.put("ebuyMethodList",EbuyMethodEnum.getEbuyMethodByTaskType(ProjectEnum.JZGC));
		model.put("messageList", messageList);
		return new ModelAndView("taskPlanJZGCListForCreateProj",model);	
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :跳转到产权交易任务书立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * Created at 2011-5-25 13:32 by zhouzhanghe
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toCQJYTaskPlanListForCreateProj")
	public ModelAndView toCQJYTaskPlanListForCreateProj(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for(String str : list){
			if(ProjectEnum.CQJY.equals(str.split(":")[0])){
				MessageCode mc = new MessageCode(str.split(":")[0], str.split(":")[1]);
				messageList.add(mc);
			}
		}
		
		model.put("ebuyMethodList",EbuyMethodEnum.getEbuyMethodByTaskType(ProjectEnum.CQJY));
		model.put("messageList", messageList);
		return new ModelAndView("taskPlanCQJYListForCreateProj",model);	
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :跳转到土地交易任务书立项页面  
	 * @param    request
	 * @return  ModelAndView
	 * Created at 2011-5-25 13:32 by zhouzhanghe
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toTDJYTaskPlanListForCreateProj")
	public ModelAndView toTDJYTaskPlanListForCreateProj(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for(String str : list){
			if(ProjectEnum.TDJY.equals(str.split(":")[0])){
				MessageCode mc = new MessageCode(str.split(":")[0], str.split(":")[1]);
				messageList.add(mc);
			}
		}
		model.put("ebuyMethodList",EbuyMethodEnum.getEbuyMethodByTaskType(ProjectEnum.TDJY));
		model.put("messageList", messageList);
		return new ModelAndView("taskPlanTDJYListForCreateProj",model);	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTaskPlanDetailByQueryObject")   
	public ModelAndView getTaskPlanDetailByQueryObject(String queryColumns, String includedProperties, HttpServletRequest request) throws Exception {
		String taskPlanId = request.getParameter("taskPlanId");
		String approvalNumber = request.getParameter("approvalNumber");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = userApiService.getOrgInfoByUser(user);
		List departmentList= taskPlanDetailService.getTaskPlanDetailList(orgInfo.getObjId(), approvalNumber);
		departmentList=HqlResultConvertUtils.hqlResultConvert(departmentList, queryColumns.split(","), null,TaskPlanDetail.class, getEnumColumns());
		Map model = new HashMap();
		model.put(Constants.JSON_RESULT, departmentList);  
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
