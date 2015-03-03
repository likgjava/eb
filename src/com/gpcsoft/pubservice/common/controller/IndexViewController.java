package com.gpcsoft.pubservice.common.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.agreement.bargin.project.service.ProjectShowService;
import com.gpcsoft.agreement.bargin.project.service.RecommendProjectService;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.bizplatform.base.application.service.HotTagsService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.RecommendSupplierService;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.contract.service.ContractService;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqKindEnum;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;
import com.gpcsoft.epp.noticemanage.domain.NoticeStatusEnum;
import com.gpcsoft.epp.noticemanage.service.NoticeService;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTaskModel;
import com.gpcsoft.epp.worktask.service.WaitprocWorkTaskService;
import com.gpcsoft.goods.goods.domain.RecommendGoods;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;
import com.gpcsoft.goods.goods.service.RecommendGoodsBrandService;
import com.gpcsoft.goods.goods.service.RecommendGoodsService;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.service.IndexViewService;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingService;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

/** 
  *  Comments: <strong>IndexViewController</strong>            		
  *	 <br/>	协议供货首页	        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-19 下午02:30:59 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-19 下午02:30:59 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
/**
 * @gpcsoft.view value="indexView"
 *  url="view/srplatform/portal/index.jsp"
 * @gpcsoft.view value="loginView"
 *  url="view/srplatform/login/login.jsp"
 * @gpcsoft.view value="loginDivView"
 *  url="view/srplatform/login/loginDiv.jsp"
 * @gpcsoft.view value="goodsClassSortView"
 *  url="view/srplatform/portal/include/category_sort.jsp"
 * @gpcsoft.view value="toMydesktopView"
 * url="view/common/myDesktop.jsp"
 * 
 * @gpcsoft.view value="recommendProjectIndexView"
 * url="view/srplatform/portal/include/recommend_project_index.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GpcBaseObject.class})
@RequestMapping("/IndexViewController.do")//页面请求路径,可修改
public class IndexViewController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("goodsClassServiceImpl")
	private GoodsClassService goodsClassServiceImpl;
	
	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	
	@Autowired(required=true) @Qualifier("indexViewServiceImpl")
	private IndexViewService indexViewService;
	
	@Autowired(required=true) @Qualifier("recommendProjectServiceImpl")
	private RecommendProjectService recommendProjectService;
	
	@Autowired(required=true) @Qualifier("recommendSupplierServiceImpl")
	private RecommendSupplierService recommendSupplierService;
	
	@Autowired(required=true) @Qualifier("recommendGoodsServiceImpl")
	private RecommendGoodsService recommendGoodsService;
	
	@Autowired(required=true) @Qualifier("hotTagsServiceImpl")
	private HotTagsService hotTagsService;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	private WaitprocWorkTaskService waitprocWorkTaskService;
	
	@Autowired(required=true) @Qualifier("inrqDetailServiceImpl")
	private InrqDetailService inrqDetailService;
	
	@Autowired(required=true) @Qualifier("noticeServiceImpl")
	private NoticeService noticeService;	
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("recommendGoodsBrandServiceImpl")
	private RecommendGoodsBrandService recommendGoodsBrandService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("groupBuyingServiceImpl")
	private GroupBuyingService groupBuyingService;
	
	@Autowired(required=true) @Qualifier("projectShowServiceImpl")
	private ProjectShowService projectShowService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	
	@Autowired(required=true) @Qualifier("contractServiceImpl")
	private ContractService contractService;	

	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	/** 
	 * Description :  跳转到登录页面
	 * Create Date: 2010-11-2下午06:41:03 by liangxj  Modified Date: 2010-11-2下午06:41:03 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toLogin")   
	public ModelAndView toLogin(HttpServletRequest request)throws Exception { 
		String viewName = request.getParameter("viewName");
		
		if(!StringUtils.hasLength(viewName)) {
			viewName = "loginView";
		}
		
		return new ModelAndView(viewName);
	}
	
	/** 
	 * Description :  获取当前用户方法
	 * Create Date: 2011-2-22上午10:42:24 by sunl  Modified Date: 2011-2-22上午10:42:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCurrentUser")   
	public ModelAndView getCurrentUser(HttpServletRequest request, HttpSession session)throws Exception { 
		Map<String,Object> model = new HashMap<String,Object>();
		User user = indexViewService.getAndSetCurrentUserInfo(model, session);
		
		if(user != null) {
			model.put("cuid", user.getObjId());
			model.put("cuname", user.getUsName());
			model.put("coid",user.getOrgInfo());
			model.put("isLogin", true);
			
			//判断当前用户是否有xejy和ztb资源
			if(user.getAuthorities() != null) {
				model.put("hasXejy", AuthenticationHelper.hasPermission(com.gpcsoft.pubservice.common.enumeration.CommonEnum.XEJY, user.getAuthorities()));
				model.put("hasZtb", AuthenticationHelper.hasPermission(com.gpcsoft.pubservice.common.enumeration.CommonEnum.ZTB, user.getAuthorities()));
			}
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  首页加载需要的
	 * Create Date: 2010-7-7下午02:54:49 by liangxj  Modified Date: 2010-7-7下午02:54:49 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=index")   
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//打开外网样式标记
		request.getSession().setAttribute("isOutCss", true);
		
		//获取当前用户的信息
		User user = indexViewService.getAndSetCurrentUserInfo(model, session);
		if(user != null) model.put("user",user);
		model.put("ready", true);
		
		//关键字
		String keyWord = request.getParameter("keyWord");
		if(StringUtils.hasLength(keyWord)){
			keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
			request.setAttribute("keyWord", keyWord);
		}else{
			request.setAttribute("keyWord", "请输入关键字");
		}
		
    	//2、进行中的招标项目，及项目规则
		Page<Project> pageBiddingProject = new Page<Project>(1, 3, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "bargaining");
		List<Project> biddingProjectList = projectShowService.getBiddingProjectList(pageBiddingProject, paramsMap).getData();
		model.put("biddingProjectList", biddingProjectList);
		List<ProjectRule> projectRuleList = new ArrayList<ProjectRule>();
		for(Project project : biddingProjectList) {
			projectRuleList.add(projectService.getProjectRuleByProjectId(project.getObjId()));
		}
		model.put("projectRuleList", projectRuleList);
		
		//3、已成交的招标项目
		Page<Project> pageBiddingDealedProject = new Page<Project>(1, 3, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "dealed");
		model.put("biddingDealedProjectList", projectShowService.getBiddingProjectList(pageBiddingDealedProject, paramsMap).getData());
		
		//4、进行中的竞价项目
		Page<Project> pageBargainProject = new Page<Project>(1, 3, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "bargaining");
		model.put("bargainProjectList", projectShowService.getBargainProjectList(pageBargainProject, paramsMap).getData());
		
		//5、已成交的竞价项目
		Page<Project> pageBargainDealedProject = new Page<Project>(1, 3, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "dealed");
		model.put("bargainDealedProjectList", projectShowService.getBargainProjectList(pageBargainDealedProject, paramsMap).getData());
		
		//6、精品团购
		Page<GroupBuying> pageGroupBuying = new Page<GroupBuying>(1, 2, 2, new ArrayList<GroupBuying>());
		paramsMap.clear();
		model.put("groupBuyingList", groupBuyingService.getGroupBuyingList(pageGroupBuying, paramsMap).getData());
		
		//7、查询重点采购企业
		Page<Buyer> pageBuyer = new Page<Buyer>(1, 6, 6, new ArrayList<Buyer>());
		paramsMap.clear();
		paramsMap.put("dealMoneySort", "desc");
    	model.put("newBuyerList", buyerService.getBuyerListForShow(pageBuyer, paramsMap).getData());
		
		//8、推荐供应商
		Page<Supplier> pageSupplier = new Page<Supplier>(1, 6, 6, new ArrayList<Supplier>());
		paramsMap.clear();
		model.put("supplierList", recommendSupplierService.getRecommendSupplierInfo(pageSupplier, paramsMap).getData());
		
		//9、推荐品牌
		Page<RecommendGoodsBrand> pageBrand = new Page<RecommendGoodsBrand>(1, 8, 8, new ArrayList<RecommendGoodsBrand>());
		paramsMap.clear();
		model.put("recommendBrandList", recommendGoodsBrandService.getRecommendGoodsBrand(pageBrand, paramsMap).getData());
		
		//10、推荐商品
		Page<RecommendGoods> pageGoods = new Page<RecommendGoods>(1, 10, 10, new ArrayList<RecommendGoods>());
		paramsMap.clear();
		model.put("recommendGoodsList", recommendGoodsService.getRecommendGoods(pageGoods, paramsMap).getData());
		
		//获得供应商采购人信息
		request.getSession().setAttribute("orgCountMap", indexViewService.getStatisticsInfo());
		
		return new ModelAndView("indexView", model);
    }
	
	/** 
	 * Description :  加载首页中推荐项目
	 * Create Date: 2011-10-31下午04:48:35 by likg  Modified Date: 2011-10-31下午04:48:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getRecommendProjectIndexView")   
	public ModelAndView getRecommendProjectIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		User user = indexViewService.getAndSetCurrentUserInfo(model, session);
		
		//获取首页重点推荐项目
		Page<RecommendProject> pageProj = new Page<RecommendProject>(1, 1, 1, new ArrayList<RecommendProject>());
		paramsMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION);
		List<RecommendProject> rpList = recommendProjectService.getRecommendProject(pageProj, paramsMap).getData();
		if(rpList!=null && rpList.size()==1) {
			RecommendProject recommendProject = rpList.get(0);
			model.put("recommendProject", recommendProject);
			
			//获取当前用户与此项目的关系
			if(user!=null && user.getOrgInfo()!=null) {
				OrgInfo orgInfo = (OrgInfo) user.getOrgInfo(); //当前用户的机构信息
				String userType = ""; //当前用户在项目中充当的角色
				Boolean hasSignUp = null; //供应商是否报名
				Project project = recommendProject.getProject();
				if(user.getObjId().equals(project.getCreateUser().getObjId())) {
					userType = "publisher";
				} else if(project.getAgencies()!=null && orgInfo.getObjId().equals(project.getAgencies().getObjId())) {
					userType = "agency";
				} else if(StringUtils.hasLength(orgInfo.getSupplierId())) {
//					String roleStr = (String) model.get("roleStr");
//					if(StringUtils.hasLength(roleStr) && roleStr.indexOf("s")!=-1) {
//						userType = "supplier";
//						Map<String, Object> param = new HashMap<String, Object>(); 
//						param.put("orgId", orgInfo.getObjId());
//						param.put("projectId", project.getObjId());
//						hasSignUp = signUprecordServiceXygh.ifHasSignUp(param);
//					}
					Supplier supplier = supplierService.get(orgInfo.getSupplierId());
					if(OrganizationEnum.USE_VALID.equals(orgInfo.getUseStatus()) && OrganizationEnum.PASS_EXAM.equals(orgInfo.getAuditStatus()) && OrganizationEnum.ENABLE.equals(orgInfo.getIsOff()) && OrganizationEnum.PASS_EXAM.equals(supplier.getAuditStatus())) {
						userType = "supplier";
						Map<String, Object> param = new HashMap<String, Object>(); 
						param.put("orgId", orgInfo.getObjId());
						param.put("projectId", project.getObjId());
						hasSignUp = signUprecordServiceXygh.ifHasSignUp(param);
					}
				}
				model.put("userType", userType);
				model.put("hasSignUp", hasSignUp);
			} else {
				model.put("userType", "visitor");
			}
		}
		model.put("nowDate", new Date());
		
		return new ModelAndView("recommendProjectIndexView", model);
	}
	
	/** 
	 * Description :  获取首字母查询的商品分类列表
	 * Create Date: 2010-7-7下午02:55:27 by liangxj  Modified Date: 2010-7-7下午02:55:27 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsClassByIndex")   
	public ModelAndView getGoodsClassByIndex(HttpServletRequest request){
		String nameFirstSpell = request.getParameter("nameFirstSpell").toUpperCase();
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("list", goodsClassServiceImpl.findGoodsClassForIndex(nameFirstSpell));
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  获得商品分类信息
	 * Create Date: 2010-8-18上午11:36:30 by liangxj  Modified Date: 2010-8-18上午11:36:30 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsClassForSort")   
	public ModelAndView getGoodsClassForSort(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();   
		model.put("gclist", goodsClassServiceImpl.findGoodsClassForIndex(null));
		return new ModelAndView("goodsClassSortView", model);
    }
	
	/** 
	 * Description :  获得商品分类信息,只获得包含商品的分类
	 * Create Date: 2010-8-18上午11:36:30 by liangxj  Modified Date: 2010-8-18上午11:36:30 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsClassForSortHasGoods")   
	public ModelAndView getGoodsClassForSortHasGoods(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();   
		model.put("gclist", goodsClassServiceImpl.findGoodsClassForHasGoods(null));
		return new ModelAndView("goodsClassSortView", model);
    }
	
	/** 
	 * Description :  获取热门标签列表
	 * Create Date: 2011-8-12上午10:24:47 by likg  Modified Date: 2011-8-12上午10:24:47 by likg
	 * @param   maxResults:获取最大记录数 tagsType:标签类型
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getHotTagsList" )
	public ModelAndView getHotTagsList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("maxResults", new Integer(request.getParameter("maxResults")));
		param.put("tagsType", request.getParameter("tagsType"));
    	model.put("hotTagsList", hotTagsService.getHotTagsList(param));
    	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 保存当前机构ID
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@RequestMapping(params = "method=setOrgInfoToUser")   
	public ModelAndView setOrgInfoToUser() throws Exception { 
		logger.debug("\nWaitprocWorkTaskService||setOrgInfoToUser\n");
		Map<String, Object> model = new HashMap<String, Object>();  
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = userApiService.getOrgInfoByUser(user);
		if(orgInfo!=null){
			AuthenticationHelper.getCurrentUser().setOrgInfo(orgInfo);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description : 到"我的桌面"
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toMyDesktop")   
	public ModelAndView toMyDesktop() throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();  
		User user=AuthenticationHelper.getCurrentUser();
		Department department = userApiService.getDepartmentByUserId(user.getObjId());
		model.put("department", department);
		model.put("employee", user.getEmp());
		model.put("orgInfo", (OrgInfo)user.getOrgInfo());
		model.put("user", user);
		Set<String> urls = new HashSet<String>(0);
		for (String resurl:user.getAuth().split(",")) {
			if (!resurl.contains("/") && !resurl.contains(".jsp") && !resurl.contains(".do") && !resurl.contains(".")) {
				urls.add(resurl);
			}
		}
		Boolean isShowDeskWorkTask = new Boolean(messageSource.getMessage("desktopWorkTaskIsShow"));// 是否显示桌面待办任务
		List<WaitprocWorkTaskModel> waitprocWorkTaskModelList = new ArrayList<WaitprocWorkTaskModel>(0);
		if (isShowDeskWorkTask) {
			waitprocWorkTaskModelList = waitprocWorkTaskService.getAllWaitprocWorkTaskModelByParamtypeCodes(urls.toArray(new String[]{}));// 获取待办任务MODEL
		}
		List<WaitprocWorkTaskModel> workTaskList = this.generatorWorkTaskList(urls.toArray(new String[]{}));// 获取待办任务MODEL
		model.put("workTaskList", workTaskList);
		model.put("waitprocWorkTaskModelList", waitprocWorkTaskModelList);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		if (orgInfo.getAgencyId()!=null&&!"".equals(orgInfo.getAgencyId())) {//判断当前用户是否为代理机构
			//String usedTaskPlanSub = taskPlanService.getTaskPlanSubInProject();//在项目申报书中间表中存在的申报书条目	
			BigDecimal taskPlanSubIds = taskPlanMSubService.getTaskPlanMSubForECPWaitCreateProj(orgInfo.getObjId());//获取待立项的申报书条目
			model.put("waitTaskPlanSubNum", taskPlanSubIds);
		}
		    //添加子流程待办任务展示      add by liuke 
		
		Set<Role> roles = ((User)userApiService.get(user.getObjId(),User.class)).getRoles();
		String roleStr = "";
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			roleStr += role.getObjId() +",";
		}
		if(roleStr.lastIndexOf(",")!=-1){
			roleStr = roleStr.substring(0,roleStr.lastIndexOf(","));
			roleStr = roleStr.replace(",", "','");
			BigDecimal processInstanceNodeNum = projectPlanService.getProcessInstanceNodebyRole(roleStr);
			model.put("processInstanceNodeNum", processInstanceNodeNum);
		}
		
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ProjectEnum.PROJECTENUM);
		List<MessageCode> tenderTypeList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 MessageCode mc = new MessageCode();
			 String string = (String) iterator.next();
			 mc.setCode(string.split(":")[0]);
			 mc.setMessage(string.split(":")[1]);
			 tenderTypeList.add(mc);
		 }
	     int j = 0;
	     MessageCode mc1 = null;
		 for (int i=0;i<tenderTypeList.size();i++) {
			 if(tenderTypeList.get(i).getCode().equals("01")){
				 j = i;
				 mc1 = tenderTypeList.get(i);
			 }
		 }
		 MessageCode mc2 = tenderTypeList.get(0);
		 tenderTypeList.set(0, mc1);
		 tenderTypeList.set(j, mc2);
		 model.put("tenderTypeList", tenderTypeList);
		return new ModelAndView("toMydesktopView", model);
    }
	
	/**
	 * @Description: 生成待办任务列表
	 * @param resurl 资源URL
	 * @return List
	 * @throws Exception
	 * @Create Date 2010-8-31 下午04:59:25 By wanghz
	 */
	@SuppressWarnings("unchecked")
	private List generatorWorkTaskList(String[] resurl)throws EsException {
	logger.debug("\nes IndexViewController||generatorWorkTaskList\n");
	  /*
	    No_SubmitTaskPlan  待提交申报书
	    N0_AuditTaskPlanForSub   审核下级待提交申报书
	    No_WaitAuditTaskPlanDetail     待审核资金采购申报书
	    No_WaitAuditTaskPlanForTJC     统计处审核大宗申报书
	    NO_SetupProjectLeader          指定项目负责人
	    No_AuditTaskPlan               待审核采购申报书
	    NO_UpdateBackTaskPlan 退回待修改申报书
	    No_selectAgent   抽取代理机构
		No_selectAgentAgain 重新抽取代理机构
		No_auditSelectAgent 抽取代理机构审核
		No_toTrustTaskPlan  待委托的申报书
		No_submitConsign  待提交委托协议
		No_waitConfConsign 待确认委托协议
		No_UpdateConsign 待修改委托协议
		NO_WaitConfPurDOC 待确认招标文件
		No_WatchdogCheckPurchaseDocFile 监察局审核招标文件
		No_WaitAuditPurDOC 待审核招标文件
		No_WaitAuditPurDOCCount 待修改的招标文件
		No_WaitConfInqpDoc  待确认询价文件
		No_WatchdogCheckInqpDocFile     监察局审核询价文件
		No_WaitInqpDocCount 待审核询价文件
		No_UpdateInqpDoc    待修改的询价文件
		No_WaitAuditBulletin 待审核招标公告
		No_UpdatePurBulletin 待修改的招标公告
		No_WaitAuditInqpBulletin 待审核询价公告
		No_UpdateInqpBulletin   待修改的询价公告
		No_WaitAuditInviterollrequ        待审核的邀请函
		No_submitClarificationDoc 待提交澄清文件
		No_WaitConfigClarificationDoc 待确认的澄清文件
		No_WatchdogCheckClarificationDoc 监察局审核澄清文件
		No_WaitAuditClarificationDoc 待审核的澄清文件
		No_UpdateClarificationDoc    待修改的澄清文件
		No_SignUprecordCount 待审核的供应商报名
		No_ResultBulletinPublicity 待审核中标公示
		No_WaitUpdateBulletinPublicity 待修改中标公示
		No_ResultBulletinCount 待审核中标公告
		No_UpdateResultBulletin 待修改的中标公告
		No_WaitConfigContract       待确认的合同
		
		NO_PassBulletin     		审核通过的招标公告
		NO_PassChangeBulletin		审核通过的更正公告
		NO_PassResultBulletin		审核通过的中标公告
		NO_UpdateSignUprecord		 待修改的报名信息
		NO_PassInqpBulletin			 审核通过的询价公告
		NO_PassBulletinPublicity		审核通过的中标公示
		NO_SingleSourceInviterollrequ	单一采购邀请函
		NO_InviteBiddingInviterollrequ	邀请招标邀请函
		NO_UpdateContract				待修改的合同 
		NO_WaitConfigNotice			待确认通知书
		NO_PassContract				待修改的合同 
									 
		*/
		List<WaitprocWorkTaskModel> workTaskList = new ArrayList<WaitprocWorkTaskModel>(0);// 获取授权的待办任务列表
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		userApiService.getDepartmentByUserId(user.getObjId());
		for (String url:resurl) {
			if ("No_SubmitTaskPlan".equals(url)) {// 待提交的申报书
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待提交采购申报书");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditDetail",QueryParam.OPERATOR_EQ,TaskPlanConfirmEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,TaskPlanConfirmEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
					queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("isSubmit",QueryParam.OPERATOR_EQ,"yes"));
					BigDecimal waitAuditTaskPlan = taskPlanService.getTaskPlanTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditTaskPlan+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("NO_WaitConfPurDOC".equals(url)) {// 待确认招标文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待确认招标文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEDOC));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.FORAUDIT));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
					BigDecimal waitConfPurDOC = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitConfPurDOC+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitAuditPurDOC".equals(url)) {// 待审核招标文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核招标文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEDOC));//采购文件
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEOFFICE_WAIT));//采购办
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
					BigDecimal waitAuditBulletin = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditBulletin+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitAuditPurDOCCount".equals(url)) {// 待修改的招标文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改的招标文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.RETURNBACK));
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEDOC));//招标文件
					queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));//代理机构的人只能修改自己的
					BigDecimal waitAuditPurDOCCount = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditPurDOCCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitConfInqpDoc".equals(url)) {//待确认询价文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待确认询价文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.FORAUDIT));
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.INQPDOC));//询价文件
					queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));//采购人只能确认自己的
					BigDecimal waitInqpDocCount = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitInqpDocCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitInqpDocCount".equals(url)) {// 待审核询价文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核询价文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEOFFICE_WAIT));
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.INQPDOC));//询价文件
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal waitInqpDocCount = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitInqpDocCount+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_UpdateInqpDoc".equals(url)) {//待修改询价文件
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改询价文件");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.RETURNBACK));
					queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.INQPDOC));//询价文件
					queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));//代理机构的人只能修改自己的
					BigDecimal waitInqpDocCount = purchaseDocService.getPurDocTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitInqpDocCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitAuditBulletin".equals(url)) {// 待审核招标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核招标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.PURCHASE_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal waitAuditBulletin = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditBulletin+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_UpdatePurBulletin".equals(url)) {// 待修改的招标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改的招标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.PURCHASE_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_NO_PASS));
					BigDecimal purBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(purBulletinCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitAuditInqpBulletin".equals(url)) {// 待审核询价公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核询价公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.INQPBULLETIN_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal waitAuditInqpBulletin = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditInqpBulletin+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_UpdateInqpBulletin".equals(url)) {//待修改的询价公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改询价公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.INQPBULLETIN_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_NO_PASS));
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal waitAuditInqpBulletin = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitAuditInqpBulletin+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_SignUprecordCount".equals(url)) {// 待审核的供应商报名
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核的供应商报名");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					queryObject.getQueryParam().and(new QueryParam("bailRecord",QueryParam.OPERATOR_EQ,"isPayFor"));
					BigDecimal signUprecordCount = signUprecordService.getSignUprecordTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(signUprecordCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_ResultBulletinPublicity".equals(url)) {// 待审核中标公示
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核中标公示");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_PREVIEW));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal resultBulletinPublicity = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinPublicity+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitUpdateBulletinPublicity".equals(url)) {// 待修改中标公示
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改中标公示");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_PREVIEW));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_NO_PASS));
					BigDecimal resultBulletinPublicity = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinPublicity+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_ResultBulletinCount".equals(url)) {// 待审核中标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待审核中标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.WAIT_AUDIT));
					queryObject.getQueryParam().and(new QueryParam("monitorId",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal resultBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinCount+""));
					if (0 == waitprocWorkTaskModel.getCount()) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_UpdateResultBulletin".equals(url)) {// 待修改的中标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改的中标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_NO_PASS));
					BigDecimal resultBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("No_WaitConfigContract".equals(url)) {//待确认的合同
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待确认的合同");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("cuyerId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
					BigDecimal waitContractCount = contractService.getContractCountByQuery(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(waitContractCount+""));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_PassBulletin".equals(url)) {// 审核通过的招标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("审核通过的招标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.PURCHASE_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
					BigDecimal purBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(purBulletinCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_PassChangeBulletin".equals(url)) {// 审核通过的更正公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("审核通过的更正公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.VARIATION_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
					BigDecimal variationBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(variationBulletinCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_PassResultBulletin".equals(url)) {// 审核通过的中标公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("审核通过的中标公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
					BigDecimal resultBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_UpdateSignUprecord".equals(url)) {//  待修改的报名信息
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改的报名信息");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_NO_PASS));
					queryObject.getQueryParam().and(new QueryParam("supplierID",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					BigDecimal signUprecordCount = signUprecordService.getSignUprecordTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(signUprecordCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
	
			if ("NO_PassInqpBulletin".equals(url)) {//  审核通过的询价公告
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("审核通过的询价公告");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.INQPBULLETIN_BULLETIN));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
					BigDecimal inqpBulletinCount = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(inqpBulletinCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_PassBulletinPublicity".equals(url)) {//  审核通过的中标公示
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("审核通过的中标公示");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_PREVIEW));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
					queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
					BigDecimal resultBulletinPublicity = bulletinService.getBulletinTotalByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(resultBulletinPublicity.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_SingleSourceInviterollrequ".equals(url)) {//  单一采购邀请函
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("单一采购邀请函");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("inrqDKind",QueryParam.OPERATOR_EQ,InrqKindEnum.NORMAL));
					queryObject.getQueryParam().and(new QueryParam("ebuyMethodType",QueryParam.OPERATOR_EQ,EbuyMethodEnum.SINGLE_SOURCE));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,InrqAuditStatusEnum.AUDIT_PASS));
					BigDecimal inrqDetailCount = inrqDetailService.getInrqDetailNumByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(inrqDetailCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_InviteBiddingInviterollrequ".equals(url)) {//  邀请招标邀请函
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("邀请招标邀请函");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("inrqDKind",QueryParam.OPERATOR_EQ,InrqKindEnum.NORMAL));
					queryObject.getQueryParam().and(new QueryParam("ebuyMethodType",QueryParam.OPERATOR_EQ,EbuyMethodEnum.INVITE_BIDDING));
					queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,InrqAuditStatusEnum.AUDIT_PASS));
					BigDecimal inviteCount = inrqDetailService.getInrqDetailNumByQueryObject(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(inviteCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_UpdateContract".equals(url)) {//  待修改的合同 
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待修改的合同 ");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
					BigDecimal updateContractCount = contractService.getContractCountByQuery(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(updateContractCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			if ("NO_PassContract".equals(url)) {//  被退回的合同
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("被退回的合同 ");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_CANCEL));
					BigDecimal updateContractCount = contractService.getContractCountByQuery(queryObject);
					waitprocWorkTaskModel.setCount(Long.parseLong(updateContractCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
			
			if ("NO_WaitConfigNotice".equals(url)) {//  待确认通知书
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName("待确认通知书 ");
				waitprocWorkTaskModel.setWorktaskType(url);
				try {
					QueryObject queryObject = new QueryObjectBase();
					queryObject.getQueryParam().and(new QueryParam("sendStatus",QueryParam.OPERATOR_EQ,NoticeStatusEnum.SENDSTATUS_YES));
					queryObject.getQueryParam().and(new QueryParam("receiveStatus",QueryParam.OPERATOR_EQ,NoticeStatusEnum.RECEVICESTATUS_NO));
					queryObject.getQueryParam().and(new QueryParam("orgInfoId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
					queryObject.getQueryParam().and(new QueryParam("num",QueryParam.OPERATOR_EQ,"forNum"));
					List list =  noticeService.getNoticeListByQueryObject(queryObject);
					BigDecimal  noticeCount = BigDecimal.valueOf((Long)list.get(0));
					waitprocWorkTaskModel.setCount(Long.parseLong(noticeCount.toString()));
					if (waitprocWorkTaskModel.getCount() == 0) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				workTaskList.add(waitprocWorkTaskModel);
			}
		}
		return workTaskList;
	}
}
