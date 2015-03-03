package com.gpcsoft.epp.project.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.ProjImplStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.service.RecordFormService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.xml.SingleSeriesXmlBuilder;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;

/**
 * @gpcsoft.view value="projectView"
 *               url="view/es/planform/project/projectView.jsp"
 * @gpcsoft.view value="tdProjectView"
 *               url="view/es/planform/project/tdProjectView.jsp"
 * @gpcsoft.view value="cqProjectView"
 *               url="view/es/planform/project/cqProjectView.jsp"
 * @gpcsoft.view value="jzProjectView"
 *               url="view/es/planform/project/jzProjectView.jsp"
 * @gpcsoft.view value="viewProjectFrom"
 *               url="view/es/planform/project/projectFormForSupervise.jsp"
 * @gpcsoft.view value="printProjectView"
 *               url="view/es/planform/project/printProjectView.jsp"
 * @gpcsoft.view value="projectListView"
 *               url="view/es/planform/projreview/projectList.jsp"
 * @gpcsoft.view value="projectInfoView"
 *               url="view/es/planform/projreview/projectInfo.jsp"
 * @gpcsoft.view value="putOnProjectRecord"
 *               url="view/es/planform/projreview/putOnProjectRecord.jsp"
 * @gpcsoft.view value="toPurchasingItemsProject"
 *               url="view/es/planform/projreview/purchasingItemsProject.jsp"
 * @gpcsoft.view value="toEbuyMethodProject"
 *               url="view/es/planform/projreview/ebuyMethodProject.jsp"
 * @gpcsoft.view value="toMoneyProject"
 *               url="view/es/planform/projreview/moneyProject.jsp"
 * @gpcsoft.view value="getProjectPurCategoryNumber"
 *               url="view/es/planform/projreview/getProjectPurCategoryNumber.jsp"
 * @gpcsoft.view value="projectHistoryRecord"              
 *               url="view/es/planform/projreview/projectHistoryRecord.jsp"
 * @gpcsoft.view value="projectList"              
 *               url="view/es/planform/project/projectList.jsp"
 *              
 */
@Controller
// 标识为控制器
@Scope("request")
@SessionAttributes(types = { Project.class, ProjectMTaskPlan.class })
@RequestMapping("/ProjectViewController.do")
// 页面请求路径,可修改
@SuppressWarnings("unchecked")
public class ProjectViewController extends AnnotationMultiController<Project> {

	@Autowired(required = true)
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;

	@Autowired(required = true)
	@Qualifier("bidServiceImpl")
	private BidService bidService;

	@Autowired(required = true)
	@Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;

	@Autowired(required = true)
	@Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;

	@Autowired(required = true)
	@Qualifier("userApiServiceImpl")
	private UserApiService userApiService;

	@Autowired(required = true)
	@Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;

	@Autowired(required = true)
	@Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;

	@Autowired(required = true)
	@Qualifier("recordFormServiceImpl")
	private RecordFormService recordFormService;

	@Autowired(required = true)
	@Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigService;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;

	/**
	 * FuncName:toProjectView Description :到项目总览页面
	 * @param request,projectId:项目主键,status
	 * @return ModelAndView
	 * @author liuy
	 * @Create Date: 2010-8-14下午01:20:32
	 * @Modifier liuy
	 * @Modified Date: 2010-8-14下午01:20:32
	 * @Modified at 2011-5-23 13:48 by zhouzhanghe
	 */
	@RequestMapping(params = "method=toProjectView")
	public ModelAndView toProjectView(HttpServletRequest request,
			String projectId, SessionStatus status) throws Exception {
		logger.debug("\nes ProjectViewController||toProjectView\n");
		Assert.assertNotNull(projectId);
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(projectId);
		String returnView = "projectView";// 默认跳到政府采购的概览页面
		if (project.getTenderType().equals(ProjectEnum.TDJY)) {
			RecordForm recordForm = recordFormService
					.getRecordFormByPrjId(projectId);
			if (recordForm != null) {
				model.put("recordForm", recordForm);
			}
			returnView = "tdProjectView";// 跳到土地交易的概览页面
		} else if (project.getTenderType().equals(ProjectEnum.CQJY)) {
			RecordForm recordForm = recordFormService
					.getRecordFormByPrjId(projectId);
			if (recordForm != null) {
				model.put("recordForm", recordForm);
			}
			returnView = "cqProjectView";// 跳到产权交易的概览页面
		} else if (project.getTenderType().equals(ProjectEnum.JZGC)) {
			RecordForm recordForm = recordFormService
					.getRecordFormByPrjId(projectId);
			if (recordForm != null) {
				model.put("recordForm", recordForm);
			}
			returnView = "jzProjectView";// 跳到建筑工程的概览页面
		}
		model.put("project", project);
		List<SignUprecord> signupList = signUprecordService
				.getSignupRecordList(projectId);
		model.put("signupList", signupList);
		Map bidmodel = new HashMap();
		ProjProcessRule projProcessRule = projProcessRuleService
				.getProjProcessRuleByProjectIdAndCode(projectId, "SUBPROJECT");
		if (projProcessRule == null) {// 用规则判断若是否需要分包，若需要则需要遍历
			for (Iterator iterator = project.getSubProject().iterator(); iterator
					.hasNext();) {
				Project subProject = (Project) iterator.next();
				List<BidPackage> bidPackageList = bidService
						.getBidPackageListByProjectId(subProject.getObjId());
				for (BidPackage bidPack : bidPackageList) { // 判断该包组是否已经开标
					List<OpenBid> list = openBidService
							.getOpenBidByProjectIdAndPackId(projectId,
									subProject.getObjId());
					if (list.size() > 0) {
						bidPack.setIsOpenBid("YES");
					} else {
						bidPack.setIsOpenBid("NO");
					}
				}
				model.put("pac", "YES");
				bidmodel.put(subProject.getObjId() + "|"
						+ subProject.getProjName(), bidPackageList);
			}
		} else {
			if (projProcessRule.getResValue().equals("true")) {
				for (Iterator iterator = project.getSubProject().iterator(); iterator
						.hasNext();) {
					Project subProject = (Project) iterator.next();
					List<BidPackage> bidPackageList = bidService
							.getBidPackageListByProjectId(subProject.getObjId());
					for (BidPackage bidPack : bidPackageList) { // 判断该包组是否已经开标
						List<OpenBid> list = openBidService
								.getOpenBidByProjectIdAndPackId(projectId,
										subProject.getObjId());
						if (list.size() > 0) {
							bidPack.setIsOpenBid("YES");
						} else {
							bidPack.setIsOpenBid("NO");
						}
					}
					model.put("pac", "YES");
					bidmodel.put(subProject.getObjId() + "|"
							+ subProject.getProjName(), bidPackageList);
				}
			} else {
				List<Bid> bidList = bidService.getBidListByProjectId(projectId);
				for (Bid bid : bidList) {// 判断该项目是否已经开标
					OpenBid openBid = openBidService
							.getOpenBidByProjectId(projectId);
					if (openBid != null) {
						bid.setIsOpenBid("YES");
					} else {
						bid.setIsOpenBid("NO");
					}
				}
				model.put("pac", "NO");
				bidmodel.put(project.getObjId() + "|" + project.getProjName(),
						bidList);
			}
		}
		ProjectRule projectRule = projectService
				.getProjectRuleByProjectId(projectId);
		List<TaskPlanMSub> TaskPlanMSubList = taskPlanMSubService
				.getTaskPlanMSubListByProjectId(projectId);
		boolean submitStTimeHasBeen = true;// 是否已过投标开始时间
		if (projectRule == null || projectRule.getSubmitStTime() == null
				|| new Date().before(projectRule.getSubmitStTime())) {
			submitStTimeHasBeen = false;
		}
		User user = AuthenticationHelper.getCurrentUser(true);
		model.put("currentUser", user);
		model.put("submitStTimeHasBeen", submitStTimeHasBeen);
		model.put("TaskPlanMSubList", TaskPlanMSubList);
		model.put("thisTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
		model.put("bidmodel", bidmodel);
		model.put("projectRule", projectRule);
		/* Added at 2011-5-23 13:48 by zhouzhanghe加入上传项目附件功能 */
		List<SysConfigItem> sysConfigItemListString = sysConfigService
				.getSysConfigItemBySysConfigItemPathAndDataType(
						SysConfigEnum.PROJECT
								+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B
								+ SysConfigEnum.PROJECT_ATTACHMENT
								+ SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A,
						"String");
		model.put("sysConfigItemListString", sysConfigItemListString);
		model.put("sysConfigItemListStringLength", sysConfigItemListString
				.size());
		return new ModelAndView(returnView, model);
	}

	/**
	 * FuncName:toViewProjectForSupervise 
	 * Description:采购办：根据项目Id查看项目信息
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-9下午02:40:39
	 * @Modifier yangx
	 * @Modified Date: 2010-9-9下午02:40:39
	 */
	@RequestMapping(params = "method=toViewProjectForSupervise")
	public ModelAndView toViewProjectForSupervise(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes ProjectViewController||toViewProjectForSupervise\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByTotal(projectId);
		Map model = new HashMap();
		ProjectRule projectRule = projectService
				.getProjectRuleByProjectId(projectId);
		model.put("projectRule", projectRule);
		model.put("project", project);
		return new ModelAndView("viewProjectFrom", model);
	}

	/**
	 * FuncName:searchProjectListForSuperviseManager Description : 监管人管理员：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-13下午04:44:59
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午04:44:59
	 */
	@RequestMapping(params = "method=searchProjectListForSuperviseManager")
	public ModelAndView searchProjectListForSuperviseManager(
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes ProjectViewController||searchProjectListForSuperviseManager\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		page = projectService.searchProjectListForSuperviseManager(queryObject,
				page);
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:searchProjectListForSupervise Description:监管人:获取项目列表
	 * @param yangx
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-13下午04:44:59
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午04:44:59
	 */
	@RequestMapping(params = "method=searchProjectListForSupervise")
	public ModelAndView searchProjectListForSupervise(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes ProjectViewController||searchProjectListForSupervise\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForSupervise(queryObject, page,
				user.getEmp().getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName: searchProjectListForAgentManager Description:代理机构管理员：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:16:10
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午05:16:10
	 */
	@RequestMapping(params = "method=searchProjectListForAgentManager")
	public ModelAndView searchProjectListForAgentManager(
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes ProjectViewController||searchProjectListForAgentManager\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		page = projectService.searchProjectListForAgentManager(queryObject,
				page, orgInfo.getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:searchProjectListForAgent Description:代理机构项目负责人：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:26:17
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午05:26:17
	 */
	@RequestMapping(params = "method=searchProjectListForAgent")
	public ModelAndView searchProjectListForAgent(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes ProjectViewController||searchProjectListForAgent\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForAgent(queryObject, page, user
				.getEmp().getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:searchProjectListForBuyer Description:采购人：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @@Create Date: 2010-10-13下午05:26:17
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午05:26:17
	 */
	@RequestMapping(params = "method=searchProjectListForBuyer")
	public ModelAndView searchProjectListForBuyer(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectViewController||searchProjectListForBuyer\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		page = projectService.searchProjectListForBuyer(queryObject, page,
				orgInfo.getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:searchProjectListForSupply Description :供应商：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-13下午05:26:17
	 * @Modifier yangx
	 * @Modified Date: 2010-10-13下午05:26:17
	 */
	@RequestMapping(params = "method=searchProjectListForSupply")
	public ModelAndView searchProjectListForSupply(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes=ProjectViewController||searchProjectListForSupply\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		page = projectService.searchProjectListForSupply(queryObject, page,
				orgInfo.getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:searchProjectListForGovernment Description :业务处室经办人：获取项目列表
	 * @param request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-10-15下午01:17:40
	 * @Modifier yangx
	 * @Modified Date: 2010-10-15下午01:17:40
	 */
	@RequestMapping(params = "method=searchProjectListForGovernment")
	public ModelAndView searchProjectListForGovernment(
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes=ProjectViewController||searchProjectListForGovernment\n");
		Page page = prePage(request);
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		User user = AuthenticationHelper.getCurrentUser();
		Department department = userApiService.getDepartmentByUserId(user
				.getObjId());
		page = projectService.searchProjectListForGovernment(queryObject, page,
				department.getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		page.setData(this.getProjProecssPers(page.getData()));//将分布数据传到前台
		super.endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:printProjectView
	 * @Description: 打印概览页面
	 * @param reqeust,projectId:项目主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-1-19 下午04:41:56
	 */
	@RequestMapping(params = "method=printProjectView")
	public ModelAndView printProjectView(HttpServletRequest reqeust,
			String projectId) throws Exception {
		logger.debug("\nes=ProjectViewController||printProjectView\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(projectId);
		model.put("project", project);
		List<SignUprecord> signupList = signUprecordService
				.getSignupRecordList(projectId);
		model.put("signupList", signupList);
		Map bidmodel = new HashMap();
		ProjProcessRule projProcessRule = projProcessRuleService
				.getProjProcessRuleByProjectIdAndCode(projectId, "SUBPROJECT");
		if (projProcessRule == null) {// 用规则判断若是否需要分包，若需要则需要遍历
			for (Iterator iterator = project.getSubProject().iterator(); iterator
					.hasNext();) {
				Project subProject = (Project) iterator.next();
				List<BidPackage> bidPackageList = bidService
						.getBidPackageListByProjectId(subProject.getObjId());
				for (BidPackage bidPack : bidPackageList) { // 判断该包组是否已经开标
					List<OpenBid> list = openBidService
							.getOpenBidByProjectIdAndPackId(projectId,
									subProject.getObjId());
					if (list.size() > 0) {
						bidPack.setIsOpenBid("YES");
					} else {
						bidPack.setIsOpenBid("NO");
					}
				}
				model.put("pac", "YES");
				bidmodel.put(subProject.getProjName(), bidPackageList);
			}
		} else {
			if (projProcessRule.getResValue().equals("true")) {
				for (Iterator iterator = project.getSubProject().iterator(); iterator
						.hasNext();) {
					Project subProject = (Project) iterator.next();
					List<BidPackage> bidPackageList = bidService
							.getBidPackageListByProjectId(subProject.getObjId());
					for (BidPackage bidPack : bidPackageList) { // 判断该包组是否已经开标
						List<OpenBid> list = openBidService
								.getOpenBidByProjectIdAndPackId(projectId,
										subProject.getObjId());
						if (list.size() > 0) {
							bidPack.setIsOpenBid("YES");
						} else {
							bidPack.setIsOpenBid("NO");
						}
					}
					model.put("pac", "YES");
					bidmodel.put(subProject.getProjName(), bidPackageList);
				}
			} else {
				List<Bid> bidList = bidService.getBidListByProjectId(projectId);
				for (Bid bid : bidList) {// 判断该项目是否已经开标
					OpenBid openBid = openBidService
							.getOpenBidByProjectId(projectId);
					if (openBid != null) {
						bid.setIsOpenBid("YES");
					} else {
						bid.setIsOpenBid("NO");
					}
				}
				model.put("pac", "NO");
				bidmodel.put(project.getProjName(), bidList);
			}
		}
		ProjectRule projectRule = projectService
				.getProjectRuleByProjectId(projectId);
		List<TaskPlanMSub> TaskPlanMSubList = taskPlanMSubService
				.getTaskPlanMSubListByProjectId(projectId);
		model.put("TaskPlanMSubList", TaskPlanMSubList);
		model.put("thisTime", new Date());
		model.put("bidmodel", bidmodel);
		model.put("projectRule", projectRule);
		return new ModelAndView("printProjectView", model);
	}

	/**
	 * FuncName: getProjectCounts Description : 获取代理机构项目管理员项目列表数目
	 * @param request
	 * @throws Exception
	 *             ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-4-8 上午09:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-8 上午09:12:14
	 */
	@RequestMapping(params = "method=getProjectCounts")
	public ModelAndView getProjectCounts(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectViewController||getProjectCounts\n");
		Map model = new HashMap();
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		if (orgInfo.getAgencyId() != null) {
			if (Role.ROLE_TYPE_MANAGER.equals(user.getUsrIsAdmin())) {
				queryObject.getQueryParam().and(
						new QueryParam("agencies", QueryParam.OPERATOR_EQ,
								orgInfo.getObjId()));
			} else {
				queryObject.getQueryParam().and(
						new QueryParam("manager", QueryParam.OPERATOR_EQ, user
								.getEmp().getObjId()));
			}

		} else if (orgInfo.getSupplierId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("supplyId", QueryParam.OPERATOR_EQ, orgInfo
							.getObjId()));
		} else if (orgInfo.getBuyerId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("buyerId", QueryParam.OPERATOR_EQ, orgInfo
							.getObjId()));
		} else if (orgInfo.getSupervisionId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("superviseId", QueryParam.OPERATOR_EQ, user
							.getEmp().getObjId()));
		}
		int projectCount = Integer.parseInt(projectService.searchProjectCount(
				queryObject).toString());
		model.put("projectCount", projectCount);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName: getProjectCounts Description : 获取项目金额的总数目
	 * @param request
	 * @throws Exception
	 *             ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-4-8 上午09:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-8 上午09:12:14
	 */
	@RequestMapping(params = "method=getProjectMoney")
	public ModelAndView getProjectMoney(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectViewController||getProjectCounts\n");
		Map model = new HashMap();
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		QueryObject queryObject = QueryWebUtils
				.getQuery(request, Project.class);
		if (orgInfo.getAgencyId() != null) {
			if (Role.ROLE_TYPE_MANAGER.equals(user.getUsrIsAdmin())) {
				queryObject.getQueryParam().and(
						new QueryParam("agencies", QueryParam.OPERATOR_EQ,
								orgInfo.getObjId()));
			} else {
				queryObject.getQueryParam().and(
						new QueryParam("manager", QueryParam.OPERATOR_EQ, user
								.getEmp().getObjId()));
			}

		} else if (orgInfo.getSupplierId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("supplyId", QueryParam.OPERATOR_EQ, orgInfo
							.getObjId()));
		} else if (orgInfo.getBuyerId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("buyerId", QueryParam.OPERATOR_EQ, orgInfo
							.getObjId()));
		} else if (orgInfo.getSupervisionId() != null) {
			queryObject.getQueryParam().and(
					new QueryParam("superviseId", QueryParam.OPERATOR_EQ, user
							.getEmp().getObjId()));
		}
		Double projectCount = projectService.searchProjectMoney(queryObject);
		/**
		 * NumberFormat 是所有数字格式的抽象基类。 此类提供了格式化和分析数字的接 口。 NumberFormat
		 * 还提供了一些方法，用来确定哪些语言环境具有数字格式， 以及它们的名称是什么。 NumberFormat
		 * 有助于格式化和分析任何语言环境的数字
		 */
		NumberFormat formatter = NumberFormat.getNumberInstance();// 用来构建一个数字转化的工厂类方法
		formatter.setMaximumFractionDigits(8);// 控制数字的显示
		String sdouble = formatter.format(projectCount);// 调用方法对数字进行转化,设置数的小数部分的最大位数
		model.put("projectCount", sdouble);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName: getProjectPackages Description : 获得项目包组
	 * @param request
	 * @throws Exception
	 *             ModelAndView
	 * @author: liuke
	 * @Create Date:2011-6-1 下午04:17:15
	 * @Modifier: liuke
	 * @Modified Date:2011-6-1 下午04:17:15
	 */
	@RequestMapping(params = "method=getProjectPackages")
	public ModelAndView getProjectPackages(HttpServletRequest request)
			throws Exception {
		Map model = new HashMap();
		String projectId = request.getParameter("projectId");
		Project project = (Project) projectService.get(projectId);
		Set<Project> subProjs = project.getSubProject();
		model.put("subProjs", subProjs);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FucnName:searchProjectList Description:显示项目列表
	 * @param request
	 * @return ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-6-28 下午 1:43:57
	 */
	@RequestMapping(params = "method=searchProjectList")
	public ModelAndView searchProjectList(HttpServletRequest request,
			SessionStatus status) throws Exception {
		logger.debug("\nes=ProjectController||searchProjectListDljg\n");
		String serviceName = request.getParameter("serviceName");
		String projProcessStatus = request.getParameter("projProcessStatus");// 项目状态
		String taskType = request.getParameter("taskType");// 任务书类型
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list = ((EnumService) FrameBeanFactory
				.getBean(EnumServiceImpl.BEAN_NAME))
				.getEnum(ProjectEnum.PROJECTENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {

			String string = (String) iterator.next();
			MessageCode mc = new MessageCode(string.split(":")[0], string
					.split(":")[1]);
			if (taskType == null) {
				messageList.add(mc);
			} else if (taskType.equals(mc.getCode())) {
				messageList.add(mc);
			}
		}
		/* Added at 2011-5-26 by zhouzhanghe，对messageList排序 */
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());

		/* Added at 2011-5-30 by zhouzhanghe，放入页面上显示的采购方式 */
		List ebuyMethodList = null;
		if (taskType == null) {
			ebuyMethodList = EbuyMethodEnum.getAllEbuyMethod();
		} else {
			ebuyMethodList = EbuyMethodEnum.getEbuyMethodByTaskType(taskType);
		}
		model.put("ebuyMethodList", ebuyMethodList);
		model.put("projProcessStatus", projProcessStatus);
		model.put("messageList", messageList);
		model.put("serviceName", serviceName);
		return new ModelAndView("projectListView", model);
	}

	/**
	 * FucnName:toProjectInfo Description :
	 * @param request
	 * @return ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-6-28 下午15:46:57
	 */
	@RequestMapping(params = "method=toProjectInfo")
	public ModelAndView toProjectInfo(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectController||toProjectInfo\n");
		String objId = request.getParameter("projectId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		model.put("project", project);
		return new ModelAndView("projectInfoView", model);
	}

	/**
	 * FucnName:ProjectController Description :
	 * @param request
	 * @return ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-6-28 下午15:46:57
	 */
	@RequestMapping(params = "method=toPutOnProjectRecord")
	public ModelAndView toPutOnProjectRecord(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectController||toProjectInfo\n");
		String objId = request.getParameter("projectId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		model.put("project", project);
		return new ModelAndView("putOnProjectRecord", model);
	}

	/**
	 * FuncName: toPurchasingItemsProject Description : 项目采购分类报表页面
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:38:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:38:33
	 */
	@RequestMapping(params = "method=toPurchasingItemsProject")
	public ModelAndView toPurchasingItemsProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		Long a1 = projectService.getProjectNumber("A");
		Long b1 = projectService.getProjectNumber("B");
		Long c1 = projectService.getProjectNumber("C");
		model.put("a1", a1);
		model.put("b1", b1);
		model.put("c1", c1);
		return new ModelAndView("toPurchasingItemsProject", model);
	}

	/**
	 * FuncName: purchasingItemsProject Description : 项目采购分类报表
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:38:33
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:38:33
	 */
	@RequestMapping(params = "method=purchasingItemsProject")
	public void purchasingItemsProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long a1 = projectService.getProjectNumber("A");
		Long b1 = projectService.getProjectNumber("B");
		Long c1 = projectService.getProjectNumber("C");
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap map = new ListOrderedMap();
		map.put("date", "货物类");
		map.put("count", a1);
		list.add(map);
		ListOrderedMap map1 = new ListOrderedMap();
		map1.put("date", "工程类");
		map1.put("count", b1);
		list.add(map1);
		ListOrderedMap map2 = new ListOrderedMap();
		map2.put("date", "服务类");
		map2.put("count", c1);
		list.add(map2);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购分类");// 设置图形标题
		builder.setXAxisName("采购分类");// 设置图形x轴显示名称
		builder.setYAxisName(messageSource
				.getMessage("loginAnalysis.yAxisName"));// 设置图形y轴显示名称
		builder.setNamePosition(0);// 设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);// 设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff"); // 图表边框色
		builder.setBgColor("ffffff"); // 图表背景色
		String str = builder.buildXml(list);
		response.getWriter().write(str);
		response.getWriter().close();
	}

	/**
	 * FuncName: toEbuyMethodProject Description : 项目采购方式报表页面
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:39:06
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:39:06
	 */
	@RequestMapping(params = "method=toEbuyMethodProject")
	public ModelAndView toEbuyMethodProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String year = request.getParameter("year");
		if (request.getParameter("year") == null) {
			year = DateUtil.getCurrentYear();
		}
		request.getSession().setAttribute("year", year);
		model.put("year", year);
		// 政府采购
		Long openBidZfcg = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.OPEN_BIDDING, ProjectEnum.ZFCG, year);
		Long inviteBidZfcg = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.INVITE_BIDDING, ProjectEnum.ZFCG, year);
		Long negotiate = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.NEGOTIATE, ProjectEnum.ZFCG, year);
		Long inquiry = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.INQUIRY, ProjectEnum.ZFCG, year);
		Long single_source = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.SINGLE_SOURCE, ProjectEnum.ZFCG, year);
		// 土地交易
		Long openBidTdjy = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.OPEN_BIDDING, ProjectEnum.TDJY, year);
		Long listTdjy = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.LIST, ProjectEnum.TDJY, year);
		Long auctionTdjy = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.AUCTION, ProjectEnum.TDJY, year);
		// 产权交易
		Long competition = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.COMPETITION, ProjectEnum.CQJY, year);
		// 建筑工程
		Long openBidJzgc = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.OPEN_BIDDING, ProjectEnum.JZGC, year);
		Long inviteBidJzgc = projectService.getProjectEbuyMethodNumber(
				EbuyMethodEnum.INVITE_BIDDING, ProjectEnum.JZGC, year);
		model.put("openBidZfcg", openBidZfcg);
		model.put("inviteBidZfcg", inviteBidZfcg);
		model.put("negotiate", negotiate);
		model.put("inquiry", inquiry);
		model.put("single_source", single_source);
		model.put("openBidTdjy", openBidTdjy);
		model.put("listTdjy", listTdjy);
		model.put("auctionTdjy", auctionTdjy);
		model.put("competition", competition);
		model.put("openBidJzgc", openBidJzgc);
		model.put("inviteBidJzgc", inviteBidJzgc);
		return new ModelAndView("toEbuyMethodProject", model);
	}

	/**
	 * FuncName: ebuyMethodProject Description : 项目采购方式报表
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:39:06
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:39:06
	 */
	@RequestMapping(params = "method=ebuyMethodProject")
	public void ebuyMethodProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String year = (String) request.getSession().getAttribute("year");
		if (year == null) {
			year = DateUtil.getCurrentYear();
		}
		Long e0 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.OPEN_BIDDING, year);
		Long e1 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.INVITE_BIDDING, year);
		Long e2 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.NEGOTIATE, year);
		Long e3 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.INQUIRY, year);
		Long e4 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.SINGLE_SOURCE, year);
		Long e5 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.TALK, year);
		Long e6 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.COMPETITION, year);
		Long e7 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.REVERSE, year);
		Long e8 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.LIST, year);
		Long e9 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.COMPETITIVE_BIDDING, year);
		Long e10 = projectService.getProjectEbuyMethodNumber2(
				EbuyMethodEnum.AUCTION, year);
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap map = new ListOrderedMap();
		map.put("date", "公开招标");
		map.put("count", e0);
		list.add(map);
		ListOrderedMap map1 = new ListOrderedMap();
		map1.put("date", "邀请招标");
		map1.put("count", e1);
		list.add(map1);
		ListOrderedMap map2 = new ListOrderedMap();
		map2.put("date", "竞争性谈判");
		map2.put("count", e2);
		list.add(map2);
		ListOrderedMap map3 = new ListOrderedMap();
		map3.put("date", "询价");
		map3.put("count", e3);
		list.add(map3);
		ListOrderedMap map4 = new ListOrderedMap();
		map4.put("date", "单一来源");
		map4.put("count", e4);
		list.add(map4);
		ListOrderedMap map5 = new ListOrderedMap();
		map5.put("date", "议价");
		map5.put("count", e5);
		list.add(map5);
		ListOrderedMap map6 = new ListOrderedMap();
		map6.put("date", "竞价");
		map6.put("count", e6);
		list.add(map6);
		ListOrderedMap map7 = new ListOrderedMap();
		map7.put("date", "反拍");
		map7.put("count", e7);
		list.add(map7);
		ListOrderedMap map8 = new ListOrderedMap();
		map8.put("date", "挂牌");
		map8.put("count", e8);
		list.add(map8);
		ListOrderedMap map9 = new ListOrderedMap();
		map9.put("date", "竞标");
		map9.put("count", e9);
		list.add(map9);
		ListOrderedMap map10 = new ListOrderedMap();
		map10.put("date", "拍卖");
		map10.put("count", e10);
		list.add(map10);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("采购方式");// 设置图形标题
		builder.setXAxisName("采购方式");// 设置图形x轴显示名称
		builder.setYAxisName(messageSource
				.getMessage("loginAnalysis.yAxisName"));// 设置图形y轴显示名称
		builder.setNamePosition(0);// 设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);// 设置y轴显示的值在结果集中的序号
		builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setBorderColor("ffffff"); // 图表边框色
		builder.setBgColor("ffffff"); // 图表背景色
		String str = builder.buildXml(list);
		response.getWriter().write(str);
		response.getWriter().close();

	}

	/**
	 * FuncName: moneyProject 
	 * Description : 项目金额分析报表
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:39:30
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:39:30
	 */
	@RequestMapping(params = "method=toMoneyProject")
	public ModelAndView toMoneyProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String yeString = request.getParameter("year");
		if(null==yeString){
			yeString = DateUtil.getCurrentYear();
		}
		String date1 = yeString + "-01-01";
		String date2 = yeString + "-12-31";
		List<ProjectMTaskPlan> list = projectService.searchProjectMoney2(date1,
				date2);
		String m1 = this.getProjectMoney(list, 1).toString();
		String m2 = this.getProjectMoney(list, 2).toString();
		String m3 = this.getProjectMoney(list, 3).toString();
		String m4 = this.getProjectMoney(list, 4).toString();
		String m5 = this.getProjectMoney(list, 5).toString();
		String m6 = this.getProjectMoney(list, 6).toString();
		String m7 = this.getProjectMoney(list, 7).toString();
		String m8 = this.getProjectMoney(list, 8).toString();
		String m9 = this.getProjectMoney(list, 9).toString();
		String m10 = this.getProjectMoney(list, 10).toString();
		String m11 = this.getProjectMoney(list, 11).toString();
		String m12 = this.getProjectMoney(list, 12).toString();
		model.put("m1", m1);
		model.put("m2", m2);
		model.put("m3", m3);
		model.put("m4", m4);
		model.put("m5", m5);
		model.put("m6", m6);
		model.put("m7", m7);
		model.put("m8", m8);
		model.put("m9", m9);
		model.put("m10", m10);
		model.put("m11", m11);
		model.put("m12", m12);
		model.put("year", yeString);
		return new ModelAndView("toMoneyProject", model);
	}

	/**
	 * FuncName: moneyProject Description : 项目金额分析报表
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-28 上午09:39:30
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 上午09:39:30
	 */
	@RequestMapping(params = "method=moneyProject")
	public void moneyProject(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String yeString = (String) request.getSession().getAttribute("year");
		if(null==yeString){
			yeString = DateUtil.getCurrentYear();
		}
		String date1 = yeString + "-01-01";
		String date2 = yeString + "-12-31";
		List<ProjectMTaskPlan> list2 = projectService.searchProjectMoney2(
				date1, date2);
		String m1 = this.getProjectMoney(list2, 1).toString();
		String m2 = this.getProjectMoney(list2, 2).toString();
		String m3 = this.getProjectMoney(list2, 3).toString();
		String m4 = this.getProjectMoney(list2, 4).toString();
		String m5 = this.getProjectMoney(list2, 5).toString();
		String m6 = this.getProjectMoney(list2, 6).toString();
		String m7 = this.getProjectMoney(list2, 7).toString();
		String m8 = this.getProjectMoney(list2, 8).toString();
		String m9 = this.getProjectMoney(list2, 9).toString();
		String m10 = this.getProjectMoney(list2, 10).toString();
		String m11 = this.getProjectMoney(list2, 11).toString();
		String m12 = this.getProjectMoney(list2, 12).toString();
		List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
		ListOrderedMap map = new ListOrderedMap();
		map.put("date", "1月");
		map.put("count", m1);
		list.add(map);
		ListOrderedMap map1 = new ListOrderedMap();
		map1.put("date", "2月");
		map1.put("count", m2);
		list.add(map1);
		ListOrderedMap map2 = new ListOrderedMap();
		map2.put("date", "3月");
		map2.put("count", m3);
		list.add(map2);
		ListOrderedMap map3 = new ListOrderedMap();
		map3.put("date", "4月");
		map3.put("count", m4);
		list.add(map3);
		ListOrderedMap map4 = new ListOrderedMap();
		map4.put("date", "5月");
		map4.put("count", m5);
		list.add(map4);
		ListOrderedMap map5 = new ListOrderedMap();
		map5.put("date", "6月");
		map5.put("count", m6);
		list.add(map5);
		ListOrderedMap map6 = new ListOrderedMap();
		map6.put("date", "7月");
		map6.put("count", m7);
		list.add(map6);
		ListOrderedMap map7 = new ListOrderedMap();
		map7.put("date", "8月");
		map7.put("count", m8);
		list.add(map7);
		ListOrderedMap map8 = new ListOrderedMap();
		map8.put("date", "9月");
		map8.put("count", m9);
		list.add(map8);
		ListOrderedMap map9 = new ListOrderedMap();
		map9.put("date", "10月");
		map9.put("count", m10);
		list.add(map9);
		ListOrderedMap map10 = new ListOrderedMap();
		map10.put("date", "11月");
		map10.put("count", m11);
		list.add(map10);
		ListOrderedMap map11 = new ListOrderedMap();
		map11.put("date", "12月");
		map11.put("count", m12);
		list.add(map11);
		SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
		builder.setCaption("资金分析(单位:元)");// 设置图形标题
		builder.setXAxisName("月份");// 设置图形x轴显示名称
		builder.setYAxisName("资金");// 设置图形y轴显示名称
		builder.setNamePosition(0);// 设置x轴显示的值在结果集中的序号
		builder.setValuePosition(1);// 设置y轴显示的值在结果集中的序号
		// builder.setBaseFontSize("12");
		builder.setSameColor(false);
		builder.setShowValues("1");
		builder.setDecimalPrecision("1");
		builder.setLabelDisplay("");
		builder.setSlantLabels("");
		builder.setBorderColor("ffffff"); // 图表边框色
		builder.setBgColor("ffffff"); // 图表背景色
		String str = builder.buildXml(list);
		response.getWriter().write(str);
		response.getWriter().close();
	}

	/**
	 * FuncName: getProjectPurCategoryNumber Description : 到品目排名页面
	 * @return ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-6-29 上午10:34:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-29 上午10:34:07
	 */
	@RequestMapping(params = "method=getProjectPurCategoryNumber")
	public ModelAndView getProjectPurCategoryNumber(HttpServletRequest request) {
		Map model = new HashMap();
		String year = request.getParameter("year");
		if (null == year) {
			year = DateUtil.getCurrentYear();
		}
		List list = projectService.getProjectPurCategoryNumber(year);
		List list2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if(i==10){break;}
			Object[] object = (Object[]) list.get(i);
			list2.add(object);
		}
		model.put("year", year);
		model.put("list", list2);
		model.put("size", list.size());
		return new ModelAndView("getProjectPurCategoryNumber", model);
	}
	
	/**
	 * FuncName: pmpmShow
	 * Description :  品目排名
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-6-29  上午10:52:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-29  上午10:52:31
	 */
	@RequestMapping(params = "method=pmpmShow")//品目排名
	public void pmpmShow(HttpServletResponse response,HttpServletRequest request) throws Exception {
			String year = (String) request.getSession().getAttribute("year");
			if (null == year) {
				year = DateUtil.getCurrentYear();
			}
			List list2 = projectService.getProjectPurCategoryNumber(year);
			List<ListOrderedMap> list = new ArrayList<ListOrderedMap>();
			for (int i = 0; i < list2.size(); i++) {
				if(i==10){break;}
				Object[] object = (Object[]) list2.get(i);
				ListOrderedMap map = new ListOrderedMap();
				map.put("date", object[0].toString());
				map.put("count",object[1].toString());
				list.add(map);
			}
			SingleSeriesXmlBuilder builder = new SingleSeriesXmlBuilder();
			builder.setCaption("品目分析");//设置图形标题
			builder.setXAxisName("品目");//设置图形x轴显示名称
			builder.setYAxisName(messageSource.getMessage("loginAnalysis.yAxisName"));//设置图形y轴显示名称
			builder.setNamePosition(0);//设置x轴显示的值在结果集中的序号
			builder.setValuePosition(1);//设置y轴显示的值在结果集中的序号
			builder.setBaseFontSize("12");
			builder.setSameColor(false);
			builder.setShowValues("1");
			builder.setDecimalPrecision("1");
			builder.setLabelDisplay("");
			builder.setSlantLabels("");
			builder.setBorderColor("ffffff");  //图表边框色
			builder.setBgColor("ffffff");      //图表背景色
			String str = builder.buildXml(list);
			response.getWriter().write(str);
			response.getWriter().close();
    }
	
	/**
	 * FuncName: getProjectMoney Description : 从列表中获取到某个月份的项目总金额
	 * @param list
	 * @param month
	 * @return Double
	 * @author: shenjz
	 * @Create Date:2011-6-28 下午04:33:04
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-28 下午04:33:04
	 */
	private Double getProjectMoney(List<ProjectMTaskPlan> list, int month) {
		Double total = 0d;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ProjectMTaskPlan projectMTaskPlan = (ProjectMTaskPlan) iterator
					.next();
			if (projectMTaskPlan.getTproject().getCreateTime().getMonth() == (month - 1)) {
				total += Double
						.parseDouble((projectMTaskPlan.getBudgetMoney() == null ? "0"
								: projectMTaskPlan.getBudgetMoney()).toString());
			}
		}
		return total;
	}
	
	
	
	/**
	 * FucnName:toProjectHistoryRecord
	 *  Description : 跳转到查看项目历史页面
	 * @param request
	 * @return ModelAndView
	 * @author zhouzhanghe
	 * @Create Date: 2011-6-28 下午15:46:57
	 */
	@RequestMapping(params = "method=toProjectHistoryRecord")
	public ModelAndView toProjectHistoryRecord(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
		String objId = request.getParameter("projectId");
		Map model = new HashMap();
		Project project = projectService.getProjectByTotal(objId);
		model.put("project", project);
		return new ModelAndView("projectHistoryRecord", model);
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
	 * FuncName: toStrartingProjectListForJG
	 * Description :  监管查询正在进行中的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toStrartingProjectListForJG")
	public ModelAndView toStrartingProjectListForJG(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("projImplStatus",QueryParam.OPERATOR_EQ,ProjImplStatusEnum.NORMAL));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
		page = projectService.searchProjectListForSuperviseManager(queryObject, page);
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectViewController.do?method=toStrartingProjectListForJG");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  采购人查询正在进行中的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toStrartingProjectListForCGR")
	public ModelAndView toStrartingProjectListForCGR(HttpServletRequest request)throws Exception {
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("projImplStatus",QueryParam.OPERATOR_EQ,ProjImplStatusEnum.NORMAL));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForBuyer(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStrartingProjectListForCGR");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  供应商查询正在进行中的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toStrartingProjectListForGYS")
	public ModelAndView toStrartingProjectListForGYS(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("projImplStatus",QueryParam.OPERATOR_EQ,ProjImplStatusEnum.NORMAL));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForSupply(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStrartingProjectListForGYS");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  项目经理查询已经结束的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toEndedProjectListForPrjManager")
	public ModelAndView toEndedProjectListForPrjManager(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForAgent(queryObject, page, user.getEmp().getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toEndedProjectListForPrjManager");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  代理机构管理查询已经结束的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toEndedProjectListForDljgManager")
	public ModelAndView toEndedProjectListForDljgManager(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForAgentManager(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toEndedProjectListForDljgManager");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  监管查询已经结束的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toEndedProjectListForJG")
	public ModelAndView toEndedProjectListForJG(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		page = projectService.searchProjectListForSuperviseManager(queryObject, page);
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toEndedProjectListForJG");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  采购人查询已经结束的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toEndedProjectListForCGR")
	public ModelAndView toEndedProjectListForCGR(HttpServletRequest request)throws Exception {
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForBuyer(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStrartingProjectListForCGR");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  供应商查询已经结束的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toEndedProjectListForGYS")
	public ModelAndView toEndedProjectListForGYS(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForSupply(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStrartingProjectListForGYS");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  项目经理查询正在进行的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toStartingProjectListForPrjManager")
	public ModelAndView toStartingProjectListForPrjManager(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("projImplStatus",QueryParam.OPERATOR_EQ,ProjImplStatusEnum.NORMAL));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForAgent(queryObject, page, user.getEmp().getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStartingProjectListForPrjManager");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
	
	/**
	 * FuncName: toStrartingProjectListForJG
	 * Description :  代理机构管理查询正在进行的项目
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-16  下午03:31:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-16  下午03:31:23
	 */
	@RequestMapping(params = "method=toStartingProjectListForDljgManager")
	public ModelAndView toStartingProjectListForDljgManager(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||toProjectHistoryRecord\n");
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
		page.setPageSize(5);
		page.setStartRowNum(start);
		QueryObject queryObject = QueryWebUtils.getQuery(request, Project.class);
		queryObject.getQueryParam().and(new QueryParam("projImplStatus",QueryParam.OPERATOR_EQ,ProjImplStatusEnum.NORMAL));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("tenderRecord",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
		User user=AuthenticationHelper.getCurrentUser();
		page = projectService.searchProjectListForAgentManager(queryObject, page, (user.getOrgInfo()).getObjId());
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("pageSearchUrl", "ProjectController.do?method=toStartingProjectListForDljgManager");//设置分页对应的请求地址
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
		return new ModelAndView("projectList", model);
	}
}
