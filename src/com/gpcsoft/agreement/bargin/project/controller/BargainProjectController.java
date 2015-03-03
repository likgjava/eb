package com.gpcsoft.agreement.bargin.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingEliminateSupplier;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingChatService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingEliminateSupplierService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordDetailService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordHistoryService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.agreement.bargin.bulletin.service.BulletinShowService;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyResultServiceXygh;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.agreement.bargin.project.domain.ProjectContactInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectPayInfo;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.agreement.bargin.project.service.BargainProjectService;
import com.gpcsoft.agreement.bargin.project.service.BargainTurnService;
import com.gpcsoft.agreement.bargin.project.service.ProjectContactInfoService;
import com.gpcsoft.agreement.bargin.project.service.ProjectPayInfoService;
import com.gpcsoft.agreement.bargin.project.service.ProjectSignInfoService;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.service.ShoppingCartService;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.service.ProtaskItemService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryProjections;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.service.ConcernService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.OrganizationManager;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.auth.service.OrganizationService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemPreValue;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;


/**
  *  @gpcsoft.view value="toUpdateBargainProjectView"
  *  url="view/agreement/bargin/project/bargain/bargain_project_modify.jsp"
  *  
  *  @gpcsoft.view value="toUpdateRequireInfoView"
  *  url="view/agreement/bargin/project/bargain/modify_project_requireItem.jsp"
  *  
  *  @gpcsoft.view value="toUpdateSignPayLinkerInfoView"
  *  url="view/agreement/bargin/project/bargain/modify_project_other_info.jsp"
  *  
  *  @gpcsoft.view value="toDelayProjectTimeView"
  *  url="view/agreement/bargin/project/bargain/delay_project_time.jsp"
  *  					 
  *  @gpcsoft.view value="toUpdateRuleView"
  *  url="view/agreement/bargin/project/bargain/modify_bargain_turn.jsp"
  *  
  *  @gpcsoft.view value="toProjectDetailSupplierView"
  *  url="view/agreement/bargin/project/project_detail_view_supplier.jsp"
  *  
  *  @gpcsoft.view value="toProjectDetailView"
  *  url="view/agreement/bargin/project/project_detail_view.jsp"
  *  
  *  @gpcsoft.view value="toProjectView"
  *  url="view/agreement/bargin/project/project_view.jsp"
  *  
  *  @gpcsoft.view value="toProjectSupplierView"
  *  url="view/agreement/bargin/project/project_supplier_view.jsp"
  *  
  *  @gpcsoft.view value="loadMinRecordByTurnProjSupplierView"
  *  url="view/agreement/bargin/project/project_detail_record_turn_supplier.jsp"
  *  
  *  @gpcsoft.view value="loadMinRecordByTurnProjView"
  *  url="view/agreement/bargin/project/project_detail_record_turn.jsp"
  *  
  *  @gpcsoft.view value="createBidProjectView_1"
  *  url="view/agreement/bargin/project/bargain/create_bid_project_1.jsp"
  *  
  *  @gpcsoft.view value="createBidProjectView_2"
  *  url="view/agreement/bargin/project/bargain/create_bid_project_2.jsp"
  *  
  *  @gpcsoft.view value="createBidProjectView_3"
  *  url="view/agreement/bargin/project/bargain/create_bid_project_3.jsp"
  *  
  *  @gpcsoft.view value="projectSubmitResultView"
  *  url="view/agreement/bargin/project/bargain/project_submit_result.jsp"
  *  
  *  @gpcsoft.view value="toSupplierNoGoodsBargainView"
  *  url="view/agreement/bargin/project/hall/supplier_nogoods_bargain.jsp"
  *  
  *  @gpcsoft.view value="toSupplierGoodsBargainView"
  *  url="view/agreement/bargin/project/hall/supplier_goods_bargain.jsp"
  *  
  *  @gpcsoft.view value="toBuyerNoGoodsBargainView"
  *  url="view/agreement/bargin/project/hall/buyer_nogoods_bargain.jsp"
  *  
  *  @gpcsoft.view value="toBuyerGoodsBargainView"
  *  url="view/agreement/bargin/project/hall/buyer_goods_bargain.jsp"
  *  
  *  @gpcsoft.view value="toHistoryTurnBargainInfo"
  *  url="view/agreement/bargin/project/hall/history_turn.jsp"
  *  
  *  @gpcsoft.view value="orgProjectMonitorListView"
  *  url="view/agreement/bargin/project/bargain/org_project_monitor_list.jsp"
  *  
  *  @gpcsoft.view value="orgProjectQueryListView"
  *  url="view/agreement/bargin/project/bargain/bargain_project_query_list.jsp"
  *  
  *  @gpcsoft.view value="stopProjectView"
  *  url="view/agreement/bargin/project/stop_project.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Project.class})
@RequestMapping("/BargainProjectController.do")//页面请求路径,可修改
public class BargainProjectController extends AnnotationMultiController<Project> {
	
	@Autowired(required=true) @Qualifier("bargainProjectServiceImpl")
	private BargainProjectService bargainProjectService;
	
	@Autowired(required=true) @Qualifier("shoppingCartServiceImpl")
	private ShoppingCartService shoppingCartService;
	
	@Autowired(required=true) @Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigServiceImpl;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoServiceImpl;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemServiceImpl;
	
	@Autowired(required=true) @Qualifier("bargainTurnServiceImpl")
	private BargainTurnService bargainTurnServiceImpl;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleServiceImpl;
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("biddingChatServiceImpl")
	private BiddingChatService  biddingChatServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectPayInfoServiceImpl")
	private ProjectPayInfoService  projectPayInfoService;
	
	@Autowired(required=true) @Qualifier("projectPayInfoServiceImpl")
	private ProjectPayInfoService  projectPayInfoServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectSignInfoServiceImpl")
	private ProjectSignInfoService  projectSignInfoServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectContactInfoServiceImpl")
	private ProjectContactInfoService  projectContactInfoServiceImpl;
	
	@Autowired(required=true) @Qualifier("buyResultServiceXyghImpl")
	private BuyResultServiceXygh buyResultServiceXygh;
	
	@Autowired(required=true) @Qualifier("protaskItemServiceImpl")
	private ProtaskItemService protaskItemServiceImpl;
	
	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;
	
	@Autowired(required=true) @Qualifier("organizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired(required=true) @Qualifier("organizationManagerImpl")
	private OrganizationManager organizationManager;
	
	@Autowired(required=true) @Qualifier("biddingRecordDetailServiceImpl")
	private BiddingRecordDetailService biddingRecordDetailService;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinServiceImpl;
	
	@Autowired(required=true) @Qualifier("biddingRecordHistoryServiceImpl")
	private BiddingRecordHistoryService biddingRecordHistoryService;
	
	@Autowired(required=true) @Qualifier("bulletinShowServiceImpl")
	private BulletinShowService bulletinShowServiceImpl;

	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	@Autowired(required=true) @Qualifier("biddingEliminateSupplierServiceImpl")
	private BiddingEliminateSupplierService biddingEliminateSupplierService;
	
	@Autowired(required=true) @Qualifier("concernServiceImpl")
	private ConcernService concernService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/** 
	 * Description :  跳转至创建竞价项目页面-第一步创建项目和需求条目页面
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateBidProject_1")
	public ModelAndView toCreateBidProject_1(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		//先清空避免更新内存中的对象
		status.setComplete();
		request.getSession().removeAttribute("project");
		
		//购物车条目
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		paramsMap.put("cartItem", request.getParameter("cartItem"));
		
		//任务书和任务书条目
		model.put("taskId", request.getParameter("taskId"));
		model.put("taskItemIds", request.getParameter("taskItemIds"));
		//采购需求Id
		model.put("requirementIds", request.getParameter("requirementIds"));
		
		//获取购物车条目
		if(StringUtils.hasLength(request.getParameter("cartItem"))) {
			List<ShoppingCartItem> shoppingCartItemList = shoppingCartService.getShoppingCarItem(paramsMap);
			model.put("shoppingCartItemList", shoppingCartItemList);	
		}
		
		//获取任务单条目
		if(StringUtils.hasLength(request.getParameter("taskItemIds"))) {
			List<ProtaskItem> taskItemList = protaskItemServiceImpl.getProtaskItemList(request.getParameter("taskItemIds"));
			model.put("taskItemList", taskItemList);
		}
		
		//获取采购需求
		if(StringUtils.hasLength(request.getParameter("requirementIds"))) {
			List<Requirement> requirementList = requirementService.getRequirementList(request.getParameter("requirementIds"));
			model.put("requirementList", requirementList);
		}
		
		//先清空避免更新内存中的对象
		status.setComplete();
		
		return new ModelAndView("createBidProjectView_1",model);
	}
	
	/** 
	 * Description : 跳转至设置轮次和规则信息页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateBidProject_2")
	public ModelAndView toCreateBidProject_2(HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String projId = request.getParameter("projId");
		
		//先清空避免更新内存中的对象
		status.setComplete();
		request.getSession().removeAttribute("project");
		
		if(StringUtils.hasLength(projId)) {
			//根据竞价编码-获取规则列表
			List<SysConfigItem> sysconfigitemList = sysConfigServiceImpl.getSysConfigItemBySysConfigTypeCode(SysConfigEnum.BargainProjectRule);
			//获取竞价规则值
			for (SysConfigItem sysConfigItem : sysconfigitemList) {
				Object obj = sysConfigServiceImpl.getSysConfigItemValueByItemCode(sysConfigItem.getObjId());
				sysConfigItem.setConfigItemValue(obj);
			}
			
			model.put("sysconfigitemList", sysconfigitemList);
		}
		model.put("projId", projId);
		
		//先清空避免更新内存中的对象
		status.setComplete();
		
		return new ModelAndView("createBidProjectView_2",model);
	}
	
	/** 
	 * Description : 跳转至创建项目付款方式、联系方式、供应商资质要求等信息页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateBidProject_3")
	public ModelAndView toCreateBidProject_3(HttpServletRequest request , SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		User user=AuthenticationHelper.getCurrentUser(true);
		
		//先清空避免更新内存中的对象
		status.setComplete();
		request.getSession().removeAttribute("project");
		
		model.put("projId", request.getParameter("projId"));
		model.put("companyId", AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId());//记录companyId
		model.put("contact", user.getEmp().getCompany());
		model.put("contactName", user.getEmp().getName());
		
		//先清空避免更新内存中的对象
		status.setComplete();
		
		return new ModelAndView("createBidProjectView_3",model);
	}
	
	/** 
	 * Description :  跳转到项目提交结果页面
	 * Create Date: 2011-6-30上午08:49:41 by likg  Modified Date: 2011-6-30上午08:49:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toProjectSubmitResultView")
	public ModelAndView toProjectSubmitResultView(HttpServletRequest request , SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//先清空避免更新内存中的对象
		status.setComplete();
		
		return new ModelAndView("projectSubmitResultView", model);
	}
	
	/** 
	 * Description :  创建竞价项目(商品描述)，同步创建项目与任务书关联表，需求条目表
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createProjectNoGoods")
	public ModelAndView createProjectNoGoods(Project project,HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("requirementIds",request.getParameter("requirementIds")==null?"":request.getParameter("requirementIds")); 
		paramsMap.put("task",request.getParameter("task")==null?"":request.getParameter("task"));
		paramsMap.put("require",request.getParameter("require")==null?"":request.getParameter("require"));
		paramsMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION);  //采购方式-竞价
		
		//如果是修改
		Project proj = null;
		if(StringUtils.hasLength(project.getObjId())) {
			proj = projectServiceImpl.get(project.getObjId());
			proj.setProjName(project.getProjName());
			proj.setSignUpSTime(project.getSignUpSTime());
			proj.setSignUpETime(project.getSignUpETime());
			proj.setBudgetTotalMoney(project.getBudgetTotalMoney());
		}else {
			proj = project;
			proj.setTenderRecord("00"); //设置备案状态为：未备案
		}
		
		//是否委托代理,如果是，则委托给北京国信商通公司代理
		String isDelegate = request.getParameter("isDelegate")==null?"":request.getParameter("isDelegate");//是否委托代理
		if(isDelegate.equals("1")) {//选择了委托代理
			Organization org = organizationService.getOrganizationBySpeciaFlag("MN").get(0);
			OrgInfo orgInfo = orgInfoServiceImpl.getLastedOrgInfo(org.getObjId(), true);
			proj.setAgencies(orgInfo);
		} else {
			proj.setAgencies(null);
		}
		
		Project resultProject = bargainProjectService.createProjectNoGoods(proj,paramsMap);
		
		model.put("projId", resultProject.getObjId());
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  创建竞价项目(有商品)，同步创建项目与任务书关联表，需求条目等信息
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createProjectGoods")
	public ModelAndView createProjectGoods(Project project,HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		
		paramMap.put("task",request.getParameter("task")==null?"":request.getParameter("task"));//任务书条目和项目关联关系
		paramMap.put("require",request.getParameter("require")==null?"":request.getParameter("require"));//需求条目
		paramMap.put("cartItemIds", request.getParameter("cartItemIds"));//购物车条目数量单价等
		paramMap.put("requirementIds",request.getParameter("requirementIds")==null?"":request.getParameter("requirementIds"));//采购需求
		
		paramMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION);  //采购方式-竞价
		
		//是否委托代理,如果是，则委托给北京国信商通公司代理
		String isDelegate = request.getParameter("isDelegate")==null?"":request.getParameter("isDelegate");//是否委托代理
		if(isDelegate.equals("1")) {//选择了委托代理
			Organization org = organizationService.getOrganizationBySpeciaFlag("MN").get(0);
			OrgInfo orgInfo = orgInfoServiceImpl.getLastedOrgInfo(org.getObjId(), true);
			project.setAgencies(orgInfo);
		} else {
			project.setAgencies(null);
		}
		
		Project proj = bargainProjectService.createProjectGoods(project,paramMap);
		
		model.put("projId", proj.getObjId());
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  创建轮次和项目规则信息，并同步项目状态、报名起止时间、竞价起止时间
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createTurnAndRule")
	public ModelAndView createTurnAndRule(HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String projId = request.getParameter("projId");
		Date evalStartTime = DateUtil.parse(request.getParameter("evalStartTime"), "yyyy-MM-dd HH:mm:ss");
		Date evalEndTime = DateUtil.parse(request.getParameter("evalEndTime"), "yyyy-MM-dd HH:mm:ss");

		//将参数放到集合中
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("turn", request.getParameter("turn")==null?"":request.getParameter("turn"));
		paramMap.put("rule", request.getParameter("rule")==null?"":request.getParameter("rule"));
		
		//保存竞价开始时间和结束时间
		Project project = projectServiceImpl.get(projId);
		project.setEvalStartTime(evalStartTime);
		project.setEvalEndTime(evalEndTime);
		
		bargainProjectService.createTurnAndRule(project,paramMap);
		
		model.put("projId", project.getObjId());
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  创建报名信息、付款方式、联系方式等信息
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createSignPayLinkerInfo")
	public ModelAndView createSignPayLinkerInfo(
			ProjectSignInfo projectSignInfo,
			ProjectPayInfo projectPayInfo,
			ProjectContactInfo projectContactInfo,
			HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(request.getParameter("payInfo.objId"))){
			projectPayInfo.setObjId(request.getParameter("payInfo.objId"));
		}
		if(StringUtils.hasLength(request.getParameter("contactInfo.objId"))){
			projectContactInfo.setObjId(request.getParameter("contactInfo.objId"));
		}
		if(StringUtils.hasLength(request.getParameter("signInfo.objId"))){
			projectSignInfo.setObjId(request.getParameter("signInfo.objId"));
		}
		
		//报名信息、付款方式、联系方式等信息
		params.put("projId", request.getParameter("projId"));
		params.put("signInfoStr", request.getParameter("signInfoStr"));
		params.put("payInfoStr", request.getParameter("payInfoStr"));
		params.put("contactInfoStr", request.getParameter("contactInfoStr"));
		params.put("status", request.getParameter("status"));
		
        //附件处理
		if("upload".equals(request.getParameter("fileType")) && request instanceof MultipartHttpServletRequest ){
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
				CommonsMultipartFile file = null;
				file = (CommonsMultipartFile)multipartRequest.getFile("assessment_File") ;
				if(file.getSize()!=0){
					try {
						Object o = AttachmentUtil.uploadFile(request,"assessment_File");
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.save(attachment);
							projectSignInfo.setAssessmentFile(attachment);
						}
					}catch(Exception de) {
						//returnMessage = StringUtils.string2ASCII(de.getMessage());
					}
				}
		}
		
        params.put("projectSignInfo",projectSignInfo);
        params.put("projectPayInfo",projectPayInfo);
        params.put("projectContactInfo",projectContactInfo);
		
		bargainProjectService.createSignPayLinkerInfo(params);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/** 
	 * Description :  发布项目，发公告
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSubmitProject")
	public ModelAndView toSubmitProject(HttpServletRequest request,SessionStatus status) throws Exception {
		String projId = request.getParameter("projectId");
		
		bargainProjectService.releaseProject(projId);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 跳转至修改竞价项目页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateBargainProject")
	public ModelAndView toUpdateBargainProject(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		String objId = request.getParameter("objId");
		
		model.put("projectId", objId);
		
		return new ModelAndView("toUpdateBargainProjectView",model);
	}
	
	/** 跳转至修改竞价项目需求条目页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateRequireInfo")
	public ModelAndView toUpdateRequireInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String objId = request.getParameter("objId");
		//获取项目 信息
		Project project = projectServiceImpl.get(objId);
		model.put("objId", project.getObjId());
		model.put("projectName", project.getProjName());
		model.put("signUpSTime", project.getSignUpSTime());
		model.put("signUpETime", project.getSignUpETime());
		
		//是否委托代理
		if(null != project.getAgencies() && !"".equals(project.getAgencies().getObjId())) {
			model.put("isDelegate", true);
		} else {
			model.put("isDelegate", false);
		}
		
		//获取需求条目信息
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		return new ModelAndView("toUpdateRequireInfoView",model);
	}
	
	/** 跳转至修改项目规则信息
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateTurnAndRule")
	public ModelAndView toUpdateTurnAndRule(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String projId = request.getParameter("objId");
		
		//获取项目 信息
		Project project = projectServiceImpl.get(projId);
		model.put("projId", project.getObjId());
		model.put("evalStartTime", project.getEvalStartTime());
		model.put("evalEndTime", project.getEvalEndTime());
		
		//获取轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(projId);
		model.put("bargainTurnList", bargainTurnList);
		
		//获取规则信息
		List<SysConfigItem> sysconfigItemList = sysConfigServiceImpl.getSysConfigItemBySysConfigTypeCode(SysConfigEnum.BargainProjectRule);
		model.put("sysConfigItemList", sysconfigItemList);
		
		//获取项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(projId);
		model.put("projruleList", projruleList);
		
		return new ModelAndView("toUpdateRuleView",model);
	}
	
	/** 跳转至修改供应商资质、付款方式、联系方式等信息页
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateSignPayLinkerInfo")
	public ModelAndView toUpdateSignPayLinkerInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		//项目ID
		String objId = request.getParameter("objId");
		
		//存入项目ID
		model.put("projId", objId);
		
		//获取报名条件信息
		ProjectSignInfo signInfo = projectSignInfoServiceImpl.getSignInfoByProjectId(objId);
		if(signInfo != null) {
			model.put("signInfo", signInfo);
		}
		
		//获取支付方式信息
		ProjectPayInfo payInfo = projectPayInfoServiceImpl.getPayInfoByProjectId(objId);
		if(payInfo != null) {
			model.put("payInfo", payInfo);
		}
		
		//获取联系方式信息
		ProjectContactInfo contactInfo = projectContactInfoServiceImpl.getContactInfoByProjectId(objId);
		if(contactInfo != null) {
			model.put("contactInfo", contactInfo);
		} else {
			//获取当前联系人信息
			User user=AuthenticationHelper.getCurrentUser(true);
			model.put("contact", user.getEmp().getCompany());
			model.put("contactName", user.getEmp().getName());
		}
		
		return new ModelAndView("toUpdateSignPayLinkerInfoView",model);
	}
	
	/** 跳转至项目延时页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDelayProjectTime")
	public ModelAndView toDelayProjectTime(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		//获取项目信息
		String objId = request.getParameter("objId");
		Project project = projectServiceImpl.get(objId);
		model.put("projId", project.getObjId());
		model.put("signUpSTime", project.getSignUpSTime());
		model.put("signUpETime", project.getSignUpETime());
		model.put("evalStartTime", project.getEvalStartTime());
		model.put("evalEndTime", project.getEvalEndTime());
		
		//获取轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(objId);
		model.put("bargainTurnList", bargainTurnList);		
		
		//获取当前时间
		model.put("currentTime", DateUtil.getCurDateTime());
		
		return new ModelAndView("toDelayProjectTimeView",model);
	}
	
	/** 
	 * Description :  项目延时，修改公告内容
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveDelayProject")
	public ModelAndView saveDelayProject(HttpServletRequest request,SessionStatus status) throws Exception {
		//将参数放到集合中
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("turn", request.getParameter("turn")==null?"":request.getParameter("turn"));
		
		//保存竞价开始时间和结束时间
		Project project = projectServiceImpl.get(request.getParameter("projId"));
		project.setEvalStartTime(DateUtil.parse(request.getParameter("evalStartTime"), "yyyy-MM-dd HH:mm:ss"));
		project.setEvalEndTime(DateUtil.parse(request.getParameter("evalEndTime"), "yyyy-MM-dd HH:mm:ss"));
		
		//保存报名开始时间和结束时间 by yucy 
		project.setSignUpSTime(DateUtil.parse(request.getParameter("signUpSTime"), "yyyy-MM-dd HH:mm:ss"));
		project.setSignUpETime(DateUtil.parse(request.getParameter("signUpETime"), "yyyy-MM-dd HH:mm:ss"));
		
		//更新项目报价起止时间
		project = projectServiceImpl.save(project);
		
		paramMap.put("project", project);
		
		//保存延时项目，并修改公告
		bargainProjectService.saveDelayProject(paramMap);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * 结束竞价
	 * Description :  
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=endBidding")   
	public ModelAndView endBidding(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("projId");
		
		Project project = projectServiceImpl.get(objId);
		project.setEvalEndTime(new Date());
		project.setProjProcessStatus(ProjProcessStatusEnum.OPEN_BID);
		project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.OPEN_BID));
		
		//如果报名结束时间在当前时间之后,则更新报名结束时间为当前时间(使供应商不能再报名)
		if(project.getSignUpETime() != null) {
			if(project.getSignUpETime().getTime() > new Date().getTime()) {
				project.setSignUpETime(new Date());
			}
		}
		projectServiceImpl.save(project);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * 删除竞价或议价项目（删除项目任务书关联表,删除需求条目,轮次,报价,报价条目,项目规则）
	 * Description :  
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=removeRBProject")   
	public ModelAndView removeRBProject(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		String type = request.getParameter("type");//删除还是作废
		
		//删除项目和关联信息
		if(StringUtils.hasLength(type) && "remove".equals(type)) {
			bargainProjectService.removeRBProject(objId);
		}
		//作废项目
		else if(StringUtils.hasLength(type) && "invalid".equals(type)) {
			Project project = projectServiceImpl.get(objId);
			project.setUseStatus(CommonEnum.USER_STATUS_CANCEL);
			projectServiceImpl.save(project);
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * 获得项目扩展信息，发布项目据此提示用户
	 * Description :  
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getProjectExInfo")   
	public ModelAndView getProjectExInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		
		ProjectPayInfo payInfo = projectPayInfoService.getPayInfoByProjectId(projectId);//交付信息
		ProjectSignInfo signInfo = projectSignInfoServiceImpl.getSignInfoByProjectId(projectId);//报名条件
		ProjectContactInfo contactInfo = projectContactInfoServiceImpl.getContactInfoByProjectId(projectId);//联系信息
		
		Integer requireCount = requireItemServiceImpl.getRequireItemCount(projectId);
		
		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(projectId);
		
		model.put("payCount", payInfo==null?"0":"1");
		model.put("signCount", signInfo==null?"0":"1");
		model.put("contactCount", contactInfo==null?"0":"1");
		model.put("ruleCount", projruleList==null?"0":projruleList.size());
		model.put("requireCount", requireCount);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String status = request.getParameter("status")==null?"":request.getParameter("status");
		
		//竞价中项目(evalStartTime<nowDate<evalEndTime && projProcessStatus == 160)
		if("bargaining".equals(status)) {
			query.getQueryParam().and(new QueryParam("evalStartTime",QueryParam.OPERATOR_LT,new Date()));		
			query.getQueryParam().and(new QueryParam("evalEndTime",QueryParam.OPERATOR_GT,new Date()));		
			query.getQueryParam().and(new QueryParam("projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.SUPPLIERS_BID));
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("evalEndTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
		}
		//即将开始竞价(evalStartTime > nowDate || (evalStartTime<nowDate<evalEndTime && useStatus=00) || useStatus=01 && projProcessStatus=20)
		else if("begaining".equals(status)) {
			QueryParam or = new QueryParam();
			
			QueryParam and = new QueryParam();  //(evalStartTime<nowDate<evalEndTime && useStatus=00)
			and.and(new QueryParam("evalStartTime",QueryParam.OPERATOR_LT,new Date()));		
			and.and(new QueryParam("evalEndTime",QueryParam.OPERATOR_GT,new Date()));	
			and.and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
			or.or(and);
			
			QueryParam and1 = new QueryParam("evalStartTime",QueryParam.OPERATOR_GT,new Date());  //evalStartTime > nowDate
			or.or(and1);
			
			QueryParam and2 = new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP);//useStatus=00
			or.or(and2);
			
			QueryParam and3 = new QueryParam();  //(useStatus=01 && projProcessStatus=20)
			and3.and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
			and3.and(new QueryParam("projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));

			or.or(and3);
			
			query.getQueryParam().and(or);
			
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("evalStartTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
		}
		//竞价结束(evalEndTime < nowDate || projProcessStatus == 170 && useStatus=01(已提交))
		else if("over".equals(status)) {
			QueryParam or = new QueryParam();
			or.or(new QueryParam("evalEndTime",QueryParam.OPERATOR_LT,new Date()));
			or.or(new QueryParam("projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.OPEN_BID));
			query.getQueryParam().and(or);
			
			query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
			
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("createTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
		}
		
		//代理人项目列表
		if(OrganizationEnum.ROLE_AGENCY.equals(request.getParameter("orgType"))){
			OrgInfo orgInfo = orgInfoServiceImpl.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true);
			query.getQueryParam().and(new QueryParam("agencies.objId",orgInfo.getObjId()));		
		}
		//采购人项目列表
		else if(OrganizationEnum.ROLE_BUYER.equals(request.getParameter("orgType"))){
			query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));		
		}
		//管理员列表
		else if(OrganizationEnum.MANAGER_NAME.equals(request.getParameter("orgType"))){
			//暂无逻辑
			
			
		}
		
		query.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_NE,CommonEnum.USER_STATUS_CANCEL));
		if(query.getQueryProjections()==null){
			query.setQueryProjections(new QueryProjections());
		}
		return query;
	}
	
	/** 
	 * Description : 跳转到查看项目
	 * Create Date: 2010-10-25下午02:47:31 by yucy  Modified Date: 2010-10-25下午02:47:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toProjectDetailView")   
	public ModelAndView toProjectDetailView( HttpServletRequest request ) throws Exception{
		Map<String ,Object> model = new HashMap<String , Object>();
		
		String objId = request.getParameter("projectId");
		//项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);
		
		//项目状态信息(数组用于页面判断)
		Integer[] projStatus  = null;
		//发布项目公告
		if(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE.equals(project.getProjProcessStatus()) ){
			projStatus = new Integer[]{1,0,0,0,0};
		}
		//报价
		else if( ProjProcessStatusEnum.SUPPLIERS_BID.equals(project.getProjProcessStatus()) && project.getEvalStartTime().getTime()<= new Date().getTime()&& project.getEvalEndTime().getTime()>= new Date().getTime() ){
			projStatus = new Integer[]{2,1,1,0,0};
		}
		//确定结果
		else if( ProjProcessStatusEnum.OPEN_BID.equals(project.getProjProcessStatus()) || project.getEvalEndTime().getTime() < new Date().getTime()){
			projStatus = new Integer[]{2,2,2,1,0};
		}
		//订单起草
		else if( ProjProcessStatusEnum.CALIBRATION_BID.equals(project.getProjProcessStatus()) ){
			projStatus = new Integer[]{2,2,2,2,1};
		}
		model.put("projStatus", projStatus);

		
		//轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(objId);
		model.put("bargainTurnList", bargainTurnList);

		//是否可以单个报价
		String singlePrice = "0";
		
		//报价次数
		String bargainNumber = "0";
		
		//成交原则
		String bargainRule = "";
		
		//是否可以显示采购人预算
		String buyerBudget = "0";
		
		//项目扩展信息
		ProjectPayInfo projectExtInfo =  projectPayInfoService.getPayInfoByProjectId(project.getObjId());
		if(projectExtInfo==null){
			projectExtInfo = new ProjectPayInfo();
		}
		model.put("projectExtInfo", projectExtInfo);


		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(objId);
		for (ProjProcessRule projProcessRule : projruleList) {
			
			//报价次数
			if(SysConfigEnum.BargainProjectRule_bargainNumber.equals(projProcessRule.getCode())){
				bargainNumber = projProcessRule.getResValue().equals("1")?"多次报价":"单次报价";
			}
			
			//采购人预算
			else if(SysConfigEnum.BargainProjectRule_buyerBudget.equals(projProcessRule.getCode())){
				buyerBudget = projProcessRule.getResValue();
				projProcessRule.setResValue(enumService.getDescByValue("srplatform.baseData.dictionary.dicUseCache", projProcessRule.getResValue()));
			}
			//成交原则()
			else if(SysConfigEnum.BargainProjectRule_bargainRule.equals(projProcessRule.getCode())){
				SysConfigItem sysConfigItem = sysConfigServiceImpl.getSysConfigItemByCode(SysConfigEnum.BargainProjectRule + "_" + SysConfigEnum.BargainProjectRule_bargainRule);
				for(SysConfigItemPreValue sysConfigItemPreValue: sysConfigItem.getPreValues()){
					if(sysConfigItemPreValue.getCode().equals(projProcessRule.getResValue())){
						projProcessRule.setResValue(sysConfigItemPreValue.getName());
					}
				}
				bargainRule = projProcessRule.getResValue();
			} 			
			//单个报价
			else if(SysConfigEnum.BargainProjectRule_singlePrice.equals(projProcessRule.getCode())){
				SysConfigItem sysConfigItem = sysConfigServiceImpl.getSysConfigItemByCode(SysConfigEnum.BargainProjectRule + "_" + SysConfigEnum.BargainProjectRule_singlePrice);
				for(SysConfigItemPreValue sysConfigItemPreValue: sysConfigItem.getPreValues()){
					if(sysConfigItemPreValue.getCode().equals(projProcessRule.getResValue())){
						projProcessRule.setResValue(sysConfigItemPreValue.getName());
					}
				}
				singlePrice = projProcessRule.getResValue();
			}
			//是否
			else {
				projProcessRule.setResValue(enumService.getDescByValue("srplatform.baseData.dictionary.dicUseCache", projProcessRule.getResValue()));
			}
 		}
		//是否单个报价
		model.put("singlePrice", singlePrice);
		
		//是否显示采购人预算
		model.put("buyerBudget", buyerBudget);
		
		//成交原则
		model.put("bargainRule",bargainRule);
		
		//报价次数
		model.put("bargainNumber", bargainNumber);
		
		model.put("projruleList", projruleList);
		
		model.put("cureentDate", new Date().getTime());
		model.put("evalStartTime",project.getEvalStartTime()==null?"":project.getEvalStartTime().getTime());
		model.put("evalEndTime", project.getEvalEndTime()==null?"":project.getEvalEndTime().getTime());
		
		//项目需求信息
		List<RequireItem> requireItemList =  requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		ModelAndView mv = null;
		
		String turnId  = null;
		if(bargainTurnList.size()>0){
			 turnId = bargainTurnList.get(0).getObjId();
			 model.put("turnId", turnId);
		}
		
		//获取最低报价
		List<Object[]> biddingRecordObjectList = biddingRecordDetailService.getMinRecord(objId);
		model.put("biddingRecordObjectList", biddingRecordObjectList);

		//供应商和供应商报价信息（第一轮）
		if("buyer".equals(request.getParameter("userType"))){//采购人页面
			List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getMinBiddingRecordBySupplyProjIdturnId(objId,turnId,null);
			model.put("biddingRecordList", biddingRecordList);
			
			//成交结果
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("projectId", project.getObjId());
			Map<String,Object> buyResultData = buyResultServiceXygh.getBuyResultData(param) ;
			model.put("buyResult", buyResultData.get("buyResult"));
			model.put("buyWinnerList", buyResultData.get("buyWinnerList"));
			
			mv = new ModelAndView("toProjectDetailView", model);
		}else{//供应商页面
			List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getMinBiddingRecordBySupplyProjIdturnId(objId,turnId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
			model.put("biddingRecordList", biddingRecordList);
			
			//报名信息
			SignUprecord signUprecord = signUprecordServiceXygh.getSignUprecordBySupplierId(objId, AuthenticationHelper.getCurrentUser());
			model.put("signUprecord", signUprecord);
			
			//成交结果
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("projectId", project.getObjId());
			param.put("userType","supplier") ;
			Map<String,Object> buyResultData = buyResultServiceXygh.getBuyResultData(param) ;
			model.put("buyResult", buyResultData.get("buyResult"));
			model.put("buyWinnerList", buyResultData.get("buyWinnerList"));
			
			mv = new ModelAndView("toProjectDetailSupplierView", model);
		}
		return mv;
	}
	
	/** 
	 * Description : 跳转到查看项目
	 * Create Date: 2010-10-25下午02:47:31 by yucy  Modified Date: 2010-10-25下午02:47:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toProjectView")   
	public ModelAndView toProjectView( HttpServletRequest request ) throws Exception{
		Map<String ,Object> model = new HashMap<String , Object>();
		
		String objId = request.getParameter("projectId");
		//项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);
		
		//项目状态信息(数组用于页面判断)
		Integer[] projStatus  = null;
		
		//获取项目的进度信息，供应商报名信息，当前用户信息
		bulletinShowServiceImpl.getProjectInfo(project, BulletinTypeEnum.BARGIN_BULLETIN, model);

		//报价
		if( ProjProcessStatusEnum.SUPPLIERS_BID.equals(project.getProjProcessStatus()) && project.getEvalStartTime().getTime()<= new Date().getTime()&& project.getEvalEndTime().getTime()>= new Date().getTime() ){
			projStatus = new Integer[]{2,1,1,0,0};
		}
		//订单起草
		else if( ProjProcessStatusEnum.CALIBRATION_BID.equals(project.getProjProcessStatus()) ){
			projStatus = new Integer[]{2,2,2,2,1};
			//结果供应商
			String dealSupplierId = "";
			for(BuyWinner buyWinner :  buyWinnerService.getBuyWinnerByProjectId(project.getObjId())){
				if(ResultTypeEnum.DEAL_YES.equals(buyWinner.getResultType())){
					dealSupplierId += (StringUtils.hasLength(dealSupplierId)?",":"") + buyWinner.getSelllerId() ;
				}
			}
			model.put("dealSupplierId", dealSupplierId);
		}
		//确定结果
		else if(project.getEvalEndTime()!=null&&( ProjProcessStatusEnum.OPEN_BID.equals(project.getProjProcessStatus()) || project.getEvalEndTime().getTime() < new Date().getTime())){
			projStatus = new Integer[]{2,2,2,1,0};
		}
		//发布项目公告
		else{
			projStatus = new Integer[]{1,0,0,0,0};
		}
		model.put("projStatus", projStatus);
		
		//轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(objId);
		model.put("bargainTurnList", bargainTurnList);
		
		//项目扩展信息
		ProjectPayInfo projectPayInfo =  projectPayInfoService.getPayInfoByProjectId(project.getObjId());
		model.put("projectPayInfo", projectPayInfo!=null?projectPayInfo:new ProjectPayInfo());
		
		//项目报名信息
		ProjectSignInfo projectSignInfo = projectSignInfoServiceImpl.getSignInfoByProjectId(project.getObjId());
		model.put("projectSignInfo", projectSignInfo!=null?projectSignInfo:new ProjectSignInfo());
		
		//项目联系信息
		ProjectContactInfo projectContactInfo = projectContactInfoServiceImpl.getContactInfoByProjectId(project.getObjId());
		model.put("projectContactInfo", projectContactInfo!=null?projectContactInfo:new ProjectContactInfo());
		
		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(objId);
		for (ProjProcessRule projProcessRule : projruleList) {
			if("supplier".equals(request.getParameter("userType")) && "buyerBudget".equalsIgnoreCase(projProcessRule.getCode())){
				model.put("isShowBudget", "1".equals(projProcessRule.getResValue()));
			}
			if("threeMust".equalsIgnoreCase(projProcessRule.getCode())){//是否至少三家
				model.put("threeMust", "1".equals(projProcessRule.getResValue()));
			}
			projProcessRule.setResValue(enumService.getDescByValue("srplatform.baseData.dictionary.dicUseCache", projProcessRule.getResValue()));
 		}
		model.put("projruleList",projruleList);
		
		model.put("cureentDate", new Date().getTime());
		model.put("evalStartTime",project.getEvalStartTime()==null?"":project.getEvalStartTime().getTime());
		model.put("evalEndTime", project.getEvalEndTime()==null?"":project.getEvalEndTime().getTime());
		
		//项目需求信息
		List<RequireItem> requireItemList =  requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		Bulletin bulletin = bulletinServiceImpl.getBulletinByProjectId(project.getObjId(), BulletinTypeEnum.BARGIN_RESULT_BULLETIN);
		if(bulletin==null){
			bulletin = bulletinServiceImpl.getBulletinByProjectId(project.getObjId(), BulletinTypeEnum.BARGIN_BULLETIN);
		}
		
		//公告信息
		model.put("bulletin",bulletin==null?new Bulletin():bulletin );
		ModelAndView mv = null;
		//当前用户的id
		model.put("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
		
		//供应商和供应商报价信息（第一轮）
		if("buyer".equals(request.getParameter("userType"))){//采购人页面
			
			//获取最低报价
			List<Object[]> biddingRecordObjectList = biddingRecordDetailService.getMinRecord(objId);
			model.put("biddingRecordObjectList", biddingRecordObjectList);
			
			//获取剔除的供应商
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("projId", objId);
			List<BiddingEliminateSupplier> eliminateSupplierList = biddingEliminateSupplierService.getEliminateSupplier(param);
			model.put("eliminateSupplierList", eliminateSupplierList);
			
			//获取黑名单客户
			List<String> blackOrgIdList = concernService.getMyBlacklist(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId(), null);
			model.put("blackOrgIdList", blackOrgIdList);
			
			mv = new ModelAndView("toProjectView", model);
		}
		else{
			//获取最低报价
			List<Object[]> biddingRecordObjectList = biddingRecordDetailService.getMyMinRecord(objId,AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			model.put("biddingRecordObjectList", biddingRecordObjectList);
			model.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());

			//供应商页面
			mv = new ModelAndView("toProjectSupplierView", model);
		}
		return mv;
	}
	
	/** 
	 * Description :  跳转到最低报价页面（可load）
	 * Create Date: 2010-10-26下午11:53:04 by yucy  Modified Date: 2010-10-26下午11:53:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadMinRecordByTurnProjSupplierView")   
	ModelAndView loadMinRecordByTurnProjSupplierView (HttpServletRequest request) throws Exception{
		
		String turnId = request.getParameter("turnId");
		
		String projectId = request.getParameter("projectId");
		
		Map<String ,Object> model = new HashMap<String , Object>();
		
		ModelAndView mv = null;
		//供应商和供应商报价信息（第一轮）
		if("buyer".equals(request.getParameter("userType"))){//采购人页面
			List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getMinBiddingRecordBySupplyProjIdturnId(projectId,turnId,null);
			model.put("biddingRecordList", biddingRecordList);
			mv = new ModelAndView("loadMinRecordByTurnProjView", model);
		}else{//供应商页面
			List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getMinBiddingRecordBySupplyProjIdturnId(projectId,turnId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
			model.put("biddingRecordList", biddingRecordList);
			mv = new ModelAndView("loadMinRecordByTurnProjSupplierView", model);
		}
		return mv;
	}
	
	/** 
	 * Description :  取出待评价对象
	 * Create Date: 2010-8-6下午03:20:46 by yucy  Modified Date: 2010-8-6下午03:20:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEvaluateObjectSupplier")   
	public ModelAndView getEvaluateObjectSupplier(String projectId) throws Exception {
		Map<String, Object> model = bargainProjectService.getEvaluateObjectSupplier(projectId);
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 供应商-竞价厅页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param  
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSupplierBargainPage")
	public ModelAndView toSupplierBargainPage(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		String objId = request.getParameter("objId");
		User user = AuthenticationHelper.getCurrentUser(true);
		//当前供应商ID
		model.put("myOrgInfoId", user.getOrgInfo().getObjId());
		model.put("myOrgInfoName",((OrgInfo)user.getOrgInfo()).getOrgName());
		
		//获取服务器时间
		String currentDate = DateUtil.getCurDateTime();
		model.put("currentDate", currentDate);

		//项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project_supplier", project);
		
		//轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(objId);
		model.put("bargainTurnList", bargainTurnList);
		
		//需求条目
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		//当前时间所属轮次
		BargainTurn currentTurn = bargainTurnServiceImpl.getCurrentBargainTurn(objId);
		model.put("currentTurn", currentTurn);
		
		//判断供应商是否已被剔除
		boolean isEliminated = biddingEliminateSupplierService.isSupplierEliminated(objId, null, user.getOrgInfo().getObjId());
		model.put("isEliminated", isEliminated);
		
		paramMap.put("projId", objId);//项目ID
		paramMap.put("supplierId", user.getOrgInfo().getObjId());//供应商ID
		paramMap.put("orderType", "bargin_time desc");//供应商-按照报价时间倒叙排序
		
		//获取供应商对每个需求的报价
		for (RequireItem requireItem : requireItemList) {
			paramMap.put("requireId", requireItem.getObjId());//需求条目ID
			paramMap.put(requireItem.getObjId(), biddingRecordDetailService.getBiddingDetailList(paramMap));
		}
		model.put("myBargain", paramMap);
		
		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(objId);
		//报价次数规则
		model.put("bargainNumber", isRule("bargainNumber",projruleList));
		//是否显示最低报价规则
		model.put("minPrice", isRule("minPrice",projruleList));
		//是否显示报价排名规则
		model.put("selfRanking", isRule("selfRanking",projruleList));
		//是否显示采购人预算
		model.put("buyerBudget", isRule("buyerBudget",projruleList));
		//是否显示排名
		model.put("selfRanking", isRule("selfRanking",projruleList));
		
		//判断有没有商品
		Integer res = requireItemServiceImpl.isGoodsOrDesc(objId);
		String result = res>0==true?"Goods":"NoGoods";
		String viewName = "toSupplier"+result+"BargainView";
		
		return new ModelAndView(viewName,model);
	}
	
	/** 采购人-竞价厅页面
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param  
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBuyerBargainPage")
	public ModelAndView toBuyerBargainPage(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		String objId = request.getParameter("objId");
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo();
		model.put("myOrgInfoId", orgInfo.getObjId());
		model.put("myOrgInfoName", orgInfo.getOrgName());
		model.put("userType", "buyer");
		
		//获取服务器时间
		String currentDate = DateUtil.getCurDateTime();
		model.put("currentDate", currentDate);

		//项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project_buyer", project);
		
		//轮次信息
		List<BargainTurn> bargainTurnList = bargainTurnServiceImpl.getBargainTurnByProjId(objId);
		model.put("bargainTurnList", bargainTurnList);
		
		//获取已报名的供应商列表
		List<SignUprecord> supplierList = signUprecordService.getSignupRecordList(objId);
		model.put("supplierList", supplierList);
		
		//需求条目
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		//当前时间所属轮次
		BargainTurn currentTurn = bargainTurnServiceImpl.getCurrentBargainTurn(objId);
		model.put("currentTurn", currentTurn);
		
		paramMap.put("projId", objId);//项目ID
		paramMap.put("orderType", "goods_price asc");//采购人-按照报价排序
		
		//获取供应商对每个需求的报价
		for (RequireItem requireItem : requireItemList) {
			paramMap.put("requireId", requireItem.getObjId());//需求条目ID
			paramMap.put(requireItem.getObjId(), biddingRecordDetailService.getBiddingDetailList(paramMap));
		}
		model.put("myBargain", paramMap);
		
		//获取剔除的供应商
		List<BiddingEliminateSupplier> eliminateSupplierList = biddingEliminateSupplierService.getEliminateSupplier(paramMap);
		model.put("eliminateSupplierList", eliminateSupplierList);
		
		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId(objId);
		//报价次数规则
		model.put("bargainNumber", isRule("bargainNumber",projruleList));
		//是否显示最低报价规则
		model.put("minPrice", isRule("minPrice",projruleList));
		//是否显示报价排名规则
		model.put("selfRanking", isRule("selfRanking",projruleList));
		
		//判断有没有商品
		Integer res = requireItemServiceImpl.isGoodsOrDesc(objId);
		String result = res>0==true?"Goods":"NoGoods";
		String viewName = "toBuyer"+result+"BargainView";
		
		return new ModelAndView(viewName,model);
	}
	
	/** 查看历史报价
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param  
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getHistoryByDetail")
	public ModelAndView getHistoryByDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String bargainDetailId = request.getParameter("bargainDetailId");
		
		List<Object[]> historyList = biddingRecordHistoryService.getHistoryByDetail(bargainDetailId);
		model.put("historyList", historyList);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 根据轮次查看报价信息
	 * Description :  
	 * Create Date: 2010-10-8下午04:05:30 by sunl  Modified Date: 2010-10-8下午04:05:30 by sunl
	 * @param  
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBargainDetailByTurnId")
	public ModelAndView getBargainDetailByTurnId(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		String projId = request.getParameter("projId");
		String turnId = request.getParameter("turnId");
		String roleType = request.getParameter("roleType")==null?"":request.getParameter("roleType");

		//项目信息
		Project project = projectServiceImpl.get(projId);
		model.put("project_detail", project);
		
		//需求条目
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(projId);
		model.put("requireItemList", requireItemList);
		
		//当前时间所属轮次
		BargainTurn currentTurn = bargainTurnServiceImpl.get(turnId);
		model.put("currentTurn", currentTurn);
		
		paramMap.put("projId", projId);//项目ID
		paramMap.put("orderType", "goods_price asc");//采购人-按照报价排序
		if(roleType.equals("supplier")) {
			paramMap.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());//供应商ID
		}
		
		//获取供应商对每个需求的报价
		for (RequireItem requireItem : requireItemList) {
			paramMap.put("requireId", requireItem.getObjId());//需求条目ID
			paramMap.put(requireItem.getObjId(), biddingRecordDetailService.getBiddingDetailList(paramMap));
		}
		model.put("myBargain", paramMap);
		
		return new ModelAndView("toHistoryTurnBargainInfo",model);
	}
	
	/** 
	 * Description :  定时器-获取当前轮次、项目状态、需求的最低报价、供应商排名等信息
	 * Create Date: 2011-5-27上午09:23:28 by sunl  Modified Date: 2011-5-27上午09:23:28 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCurrentBargainTurn")   
	public ModelAndView getCurrentBargainTurn(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		User user = AuthenticationHelper.getCurrentUser(true);
		//当前供应商ID
		model.put("currentSupplierId", user.getOrgInfo().getObjId());
		
		//获取当前时间所在轮次id、时间、轮次号
		String projId = request.getParameter("projId");
		Project proj = projectServiceImpl.get(projId);

		//项目已结束
		if(Integer.valueOf(proj.getProjProcessStatus()) >= 170) {
			model.put("over", true);
		} else {
			BargainTurn turn = bargainTurnServiceImpl.getCurrentBargainTurn(projId);
			model.put("currentTurnId", turn == null?"":turn.getObjId());
			model.put("turnNo", turn == null?"":turn.getTurnNo());
			model.put("startTime", turn == null?"":DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", turn.getStartTime()));
			model.put("endTime", turn == null?"":DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", turn.getEndTime()));
			
			//获取需求的最低报价
			String requireIds = request.getParameter("requireIds");
			
			Map<String, Object> paramMap = new HashMap<String, Object>(); 
			paramMap.put("projId", projId);//项目ID
			
			//如果当前用户是供应商，则查询供应商排名
			if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_SUPPLIER)) {
				//获取供应商排名
				List<Object[]> rankingList = biddingRecordDetailService.getMyRanking(projId, requireIds, null);
				model.put("rankingList", rankingList);
				
				//获取需求条目最低报价
				List<Object[]> minRequirePriceList = biddingRecordDetailService.getRequireMinPrice(projId, requireIds, null);
				model.put("minRequirePriceList", minRequirePriceList);
				
				//判断供应商是否已被剔除
				boolean isEliminated = biddingEliminateSupplierService.isSupplierEliminated(projId, null, user.getOrgInfo().getObjId());
				model.put("isEliminated", isEliminated);
			} 
			
			//如果当前用户是采购人，则查询是否有新聊天内容
			if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_BUYER)) {
				paramMap.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+","+request.getParameter("supplierIds"));  //发送人和接受人
				List<Object[]> newChatCount = biddingChatServiceImpl.findNewChatContent(paramMap);
				if(newChatCount != null && newChatCount.size() > 0) {
					model.put("newChatCount", newChatCount.get(0));
				}else{
					model.put("newChatCount", new Object[0]);
				}
				
				//获取剔除供应商
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("projId", projId);
				List<BiddingEliminateSupplier> eliminateSupplierList = biddingEliminateSupplierService.getEliminateSupplier(param);
				model.put("eliminateSupplierList", eliminateSupplierList);
			}
			
			//定时刷新供应商报价
			paramMap.put("orderType", "goods_price asc");//采购人-按照报价排序
			//获取项目的所有需求条目的报价
			model.put("bargainDetailList", biddingRecordDetailService.getBiddingDetailList(paramMap));
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  返回规则类型的布尔值
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isRule(String configItemType,List<ProjProcessRule> projruleList) throws Exception {
		boolean res = false;
		for (ProjProcessRule projProcessRule : projruleList) {
			if(projProcessRule.getCode().equals(configItemType) && projProcessRule.getResValue().equals("1")) {
				res = true;
			}
		}
		return res;
	}
	
	/** 
	 * Description :  跳转到上级公司监控下级公司项目列表页面
	 * Create Date: 2011-7-26上午10:11:50 by likg  Modified Date: 2011-7-26上午10:11:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgProjectMonitorListView")
	public ModelAndView toOrgProjectMonitorListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取上级公司的信息
		OrgInfo orgInfo = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("companyId", orgInfo.getCompany().getObjId()); //上级公司的公司id
		model.put("orgInfoId", orgInfo.getObjId()); //上级公司的机构id
		
		return new ModelAndView("orgProjectMonitorListView", model);
	}
	
	
	/** 
	 * Description :  跳转到上级公司监控下级公司项目列表页面
	 * Create Date: 2011-7-26上午10:11:50 by likg  Modified Date: 2011-7-26上午10:11:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=orgProjectQueryListView")
	public ModelAndView orgProjectQueryListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("subOrgCount", organizationManager.getSubOrgByParentId(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId()));
		
		//获取上级公司的信息
		OrgInfo orgInfo = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("companyId", orgInfo.getCompany().getObjId()); //上级公司的公司id
		model.put("orgInfoId", orgInfo.getObjId()); //上级公司的机构id
		
		return new ModelAndView("orgProjectQueryListView", model);
	}
	
	/** 
	 * Description :  跳转到终止项目页面(填写终止原因)
	 * Create Date: 2011-12-6下午04:55:23 by likg  Modified Date: 2011-12-6下午04:55:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toStopProjectView")
	public ModelAndView toStopProjectView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);
		
		return new ModelAndView("stopProjectView", model);
	}
	
	/** 
	 * Description :  保存终止项目的信息
	 * Create Date: 2011-12-6下午05:47:29 by likg  Modified Date: 2011-12-6下午05:47:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveStopProjectInfo")
	public ModelAndView saveStopProjectInfo(ProjectExceptionApply projectExceptionApply, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		//保存终止项目的信息
		param.put("needAudit", request.getParameter("needAudit"));
		bargainProjectService.saveStopProjectInfo(projectExceptionApply, param);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
