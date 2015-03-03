package com.gpcsoft.agreement.bargin.bulletin.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.agreement.bargin.bulletin.service.BulletinShowService;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyResultServiceXygh;
import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.agreement.bargin.project.service.ProjectShowService;
import com.gpcsoft.agreement.bargin.project.service.RecommendProjectService;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.bizplatform.base.application.service.HotTagsService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.goods.goods.domain.RecommendGoods;
import com.gpcsoft.goods.goods.service.RecommendGoodsService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.service.IndexViewService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
 * @gpcsoft.view value="bulletinShowListView"
 *  url="view/agreement/showbulletin/show_bulletin_list.jsp"
 *  
 * @gpcsoft.view value="bulletinShowForListView"
 *  url="view/agreement/showbulletin/bulletin_list_div_l.jsp"
 *  
 * @gpcsoft.view value="bulletinShowForBuyPreListView"
 *  url="view/agreement/showbulletin/bulletin_buy_pre_div.jsp"
 *  
 * @gpcsoft.view value="bulletinShowInfoView"
 *  url="view/agreement/showbulletin/show_bulletin_detail.jsp"
 *  
 * @gpcsoft.view value="bulletinRecommendView"
 *  url="view/agreement/showbulletin/bulletin_recommend_list.jsp"
 *  
 * @gpcsoft.view value="bulletinRecommendIndexView"
 *  url="view/srplatform/portal/include/recommend_project_list.jsp"
 *  
 * @gpcsoft.view value="bulletinShowDetailView"
 *  url="view/pubservice/bizdata/bulletin/bulletin_detail.jsp"
 *  
 * @gpcsoft.view value="simpleBulletinDetailView"
 *  url="view/agreement/bargin/project/recommend/bulletin_detail_view.jsp"
 *  
 *  @gpcsoft.view value="bulletinBuyPreView"
 *  url="view/agreement/showbulletin/show_buy_pre_list.jsp"
 *  
 *  @gpcsoft.view value="showBulletinIndexView"
 *  url="view/agreement/showbulletin/show_bulletin_index.jsp"
 *  
 *  @gpcsoft.view value="showBiddingIndexView"
 *  url="view/agreement/showbulletin/show_bidding_index.jsp"
 *  
 *  @gpcsoft.view value="showBargainIndexView"
 *  url="view/agreement/showbulletin/show_bargain_index.jsp"
 *  
 *  @gpcsoft.view value="downloadPurchaseDocFileView"
 *  url="view/agreement/showbulletin/download_purchase_doc_file.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/BulletinShowController.do")//页面请求路径,可修改
public class BulletinShowController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("bulletinShowServiceImpl")
	private BulletinShowService bulletinShowService;
	
	@Autowired(required=true) @Qualifier("recommendProjectServiceImpl")
	private RecommendProjectService recommendProjectService;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	
	@Autowired(required=true) @Qualifier("indexViewServiceImpl")
	private IndexViewService indexViewService;
	
	@Autowired(required=true) @Qualifier("buyResultServiceXyghImpl")
	private BuyResultServiceXygh buyResultServiceXygh;
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordService;
	
	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true) @Qualifier("recommendGoodsServiceImpl")
	private RecommendGoodsService recommendGoodsService;
	
	@Autowired(required=true) @Qualifier("hotTagsServiceImpl")
	private HotTagsService hotTagsServiceService;
	
	@Autowired(required=true) @Qualifier("projectShowServiceImpl")
	private ProjectShowService projectShowService;
	
	@Autowired(required = true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required = true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/** 
	 * Description :  跳转到电子招标首页
	 * Create Date: 2011-8-5下午12:57:54 by likg  Modified Date: 2011-8-5下午12:57:54 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowBiddingIndexView")
	public ModelAndView toShowBiddingIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr", session.getAttribute("roleStr"));
		}
		
		//1、工程类项目
		Page<Project> pageEngineeringProject = new Page<Project>(1L, 3L, 3, new ArrayList<Project>());
    	paramsMap.clear();
		paramsMap.put("purCategoryId", "B");
		List<Project> engineeringProjectList = projectShowService.getBiddingProjectList(pageEngineeringProject, paramsMap).getData();
		model.put("engineeringProjectList", engineeringProjectList);
		List<ProjectRule> engineeringProjectRuleList = new ArrayList<ProjectRule>();
		for(Project project : engineeringProjectList) {
			engineeringProjectRuleList.add(projectService.getProjectRuleByProjectId(project.getObjId()));
		}
		model.put("engineeringProjectRuleList", engineeringProjectRuleList);
		
		//2、货物类项目
		Page<Project> pageGoodsProject = new Page<Project>(1L, 3L, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("purCategoryId", "A");
		List<Project> goodsProjectList = projectShowService.getBiddingProjectList(pageGoodsProject, paramsMap).getData();
		model.put("goodsProjectList", goodsProjectList);
		List<ProjectRule> goodsProjectRuleList = new ArrayList<ProjectRule>();
		for(Project project : goodsProjectList) {
			goodsProjectRuleList.add(projectService.getProjectRuleByProjectId(project.getObjId()));
		}
		model.put("goodsProjectRuleList", goodsProjectRuleList);
		
		//3、服务类项目
		Page<Project> pageServiceProject = new Page<Project>(1L, 3L, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("purCategoryId", "C");
		List<Project> serviceProjectList = projectShowService.getBiddingProjectList(pageServiceProject, paramsMap).getData();
		model.put("serviceProjectList", serviceProjectList);
		List<ProjectRule> serviceProjectRuleList = new ArrayList<ProjectRule>();
		for(Project project : serviceProjectList) {
			serviceProjectRuleList.add(projectService.getProjectRuleByProjectId(project.getObjId()));
		}
		model.put("serviceProjectRuleList", serviceProjectRuleList);
		
		//4、最近成交项目
		Page<Project> pageBiddingDealedProject = new Page<Project>(1, 6, 6, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "dealed");
		model.put("biddingDealedProjectList", projectShowService.getBiddingProjectList(pageBiddingDealedProject, paramsMap).getData());
		
		//5、重点采购企业
		Page<Buyer> pageBuyer = new Page<Buyer>(1, 5, 5, new ArrayList<Buyer>());
		paramsMap.clear();
		paramsMap.put("dealMoneySort", "desc");
		List<Buyer> buyerList = buyerService.getBuyerListForShow(pageBuyer, paramsMap).getData();
    	model.put("buyerList", buyerList);
    	//获取企业的发标次数
    	List<Long> bulletinNumList = new ArrayList<Long>();
    	for(Buyer buyer : buyerList) {
    		paramsMap.clear();
    		paramsMap.put("orgInfoId", buyer.getOrgInfo().getObjId());
    		bulletinNumList.add(bulletinShowService.getBulletinNum(paramsMap));
    	}
    	model.put("bulletinNumList", bulletinNumList);
    	
		return new ModelAndView("showBiddingIndexView", model);
	}
	
	/** 
	 * Description :  跳转到电子采购首页
	 * Create Date: 2011-8-5下午12:57:54 by likg  Modified Date: 2011-8-5下午12:57:54 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowBargainIndexView")
	public ModelAndView toShowBargainIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr", session.getAttribute("roleStr"));
		}
		
		//1、进行中的竞价项目
		Page<Project> pageBargainProject = new Page<Project>(1, 3, 3, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "bargaining");
		List<Project> bargainProjectList = projectShowService.getBargainProjectList(pageBargainProject, paramsMap).getData();
		model.put("bargainProjectList", bargainProjectList);
		//获取项目规则信息
		List<List<ProjProcessRule>> projRuleListList = new ArrayList<List<ProjProcessRule>>();
		for(Project project : bargainProjectList) {
			projRuleListList.add(projProcessRuleService.getProjProcessRuleByProjectId(project.getObjId()));
		}
		model.put("projRuleListList", projRuleListList);
		
		//2、最近成交项目
		Page<Project> pageBargainDealedProject = new Page<Project>(1, 6, 6, new ArrayList<Project>());
		paramsMap.clear();
		paramsMap.put("projectProcess", "dealed");
		model.put("bargainDealedProjectList", projectShowService.getBargainProjectList(pageBargainDealedProject, paramsMap).getData());
		
		//3、重点采购企业
		Page<Buyer> pageBuyer = new Page<Buyer>(1, 5, 5, new ArrayList<Buyer>());
		paramsMap.clear();
		paramsMap.put("dealMoneySort", "desc");
		List<Buyer> buyerList = buyerService.getBuyerListForShow(pageBuyer, paramsMap).getData();
		model.put("buyerList", buyerList);
		//获取企业的发标次数
		List<Long> bulletinNumList = new ArrayList<Long>();
		for(Buyer buyer : buyerList) {
			paramsMap.clear();
			paramsMap.put("orgInfoId", buyer.getOrgInfo().getObjId());
			bulletinNumList.add(bulletinShowService.getBulletinNum(paramsMap));
		}
		model.put("bulletinNumList", bulletinNumList);
		
		//4、推荐商品
		Page<RecommendGoods> pageGoods = new Page<RecommendGoods>(1, 4, 4, new ArrayList<RecommendGoods>());
		paramsMap.clear();
		model.put("recommendGoodsList", recommendGoodsService.getRecommendGoods(pageGoods, paramsMap).getData());
		
		//6、通过采购品目热门标签查询采购需求
		paramsMap.clear();
		paramsMap.put("tagsType", OrganizationEnum.PUR_CATEGORY_TAGS);
		paramsMap.put("order", "tagsId_asc");
		List<HotTags> hotTagList = hotTagsServiceService.getHotTagsList(paramsMap);
		List<HotTags> pHotTagList = new ArrayList<HotTags>();
		List<List<HotTags>> hotTagListList = new ArrayList<List<HotTags>>();
		for(int i=0; i<2; i++) {hotTagListList.add(new ArrayList<HotTags>());}
		String categoryId = null;
		for(HotTags tag : hotTagList) {
			if(categoryId==null || !tag.getTagsId().startsWith(categoryId)) {
				if(pHotTagList.size() == 3) {break;}
				pHotTagList.add(tag);
				categoryId = tag.getTagsId();
			}else if(tag.getTagsId().startsWith(categoryId)) {
				hotTagListList.get(pHotTagList.size()-1).add(tag);
			}
		}
		//查询采购需求
		List<List<Requirement>> requirementListList = new ArrayList<List<Requirement>>();
		Page<Requirement> pageRequirement = null;
		for(int i=0; i<pHotTagList.size(); i++) {
			if(i != 2) pageRequirement = new Page<Requirement>(1, 4, 4, new ArrayList<Requirement>());
			else pageRequirement = new Page<Requirement>(1, 2, 2, new ArrayList<Requirement>());
			paramsMap.clear();
			paramsMap.put("categoryCode", pHotTagList.get(i).getTagsId());
			requirementListList.add(requirementService.getRequirementListForShow(pageRequirement, paramsMap).getData());
		}
		model.put("pHotTagList", pHotTagList);
		model.put("hotTagListList", hotTagListList);
		model.put("requirementListList", requirementListList);
		
		return new ModelAndView("showBargainIndexView", model);
	}
	
	/** 
	 * Description :  跳转到商品需求首页
	 * Create Date: 2011-2-21上午10:20:54 by yucy  Modified Date: 2011-2-21上午10:20:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toShowBulletinIndexView")
	public ModelAndView toShowBulletinIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>(); //查询条件
		
		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr", session.getAttribute("roleStr"));
		}
		
		//获取商品分类
		model.put("purCategoryList", purCategoryService.findPurCategoryForIndex(null));
		
    	//1.1、查询采购预告(项目id为空)
		Page<Bulletin> page = new Page(1, 10, 10, new ArrayList<Bulletin>());
		paramsMap.clear();
		paramsMap.put("status", "buyPreview");
		model.put("buyPreviewList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		//1、查询采购需求
		Page<Requirement> pageRequirement = new Page<Requirement>();
		pageRequirement.setStart(0);
		paramsMap.clear();
		pageRequirement.setPageSize(10);
		paramsMap.put("order", "pubTime");
		model.put("requirementList", requirementService.getRequirementListForShow(pageRequirement, paramsMap).getData());
		
    	//2、获取推荐项目
		Page<RecommendProject> pageProj = new Page<RecommendProject>(1, 4, 4, new ArrayList<RecommendProject>());
		paramsMap.clear();
		paramsMap.put("status", "purchasePreview");
		List<RecommendProject> rpList = recommendProjectService.getRecommendProject(pageProj, paramsMap).getData();
		model.put("recommendProjects", rpList);
		if(user!=null && user.getOrgInfo()!=null) {
			OrgInfo orgInfo = (OrgInfo) user.getOrgInfo(); //当前用户的机构信息
			Object[] userTypes = new Object[rpList.size()]; //当前用户在项目中充当的角色
			Object[] hasSignUps = new Object[rpList.size()]; //供应商是否报名
			for(int i=0; i<rpList.size(); i++) {
				Project project = rpList.get(i).getProject();
				if(user.getObjId().equals(project.getCreateUser().getObjId())) {
					userTypes[i] = "publisher";
				} else if(project.getAgencies()!=null && orgInfo.getObjId().equals(project.getAgencies().getObjId())) {
					userTypes[i] = "agency";
				} else if(StringUtils.hasLength(orgInfo.getSupplierId())) {
					String roleStr = (String) model.get("roleStr");
					if(StringUtils.hasLength(roleStr) && roleStr.indexOf("s")!=-1) {
						userTypes[i] = "supplier";
						Map<String, Object> param = new HashMap<String, Object>(); 
						param.put("orgId", orgInfo.getObjId());
						param.put("projectId", project.getObjId());
						hasSignUps[i] = signUprecordServiceXygh.ifHasSignUp(param);
					}
				}
			}
			model.put("userTypes", userTypes);
			model.put("hasSignUps", hasSignUps);
		} else {
			model.put("userType", "visitor");
		}
		
		//3、查询正在交易的项目
//		page = new Page(1, 7, 7, new ArrayList<Bulletin>());
//		paramsMap.clear();
//		paramsMap.put("status", "bargaining");
//		model.put("bargainingBulletinList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		//4、查询已完成的项目
    	page = new Page(1L, 10L, 10, new ArrayList<Bulletin>());
    	paramsMap.clear();
		paramsMap.put("status", "bargained");
		model.put("bargainedBulletinList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		//5、查询未开始的项目
    	page = new Page(1L, 10L, 10, new ArrayList<Bulletin>());
    	paramsMap.clear();
		paramsMap.put("status", "purchasePreview");
		model.put("previewBulletinList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		//6、查询统计数字
		Map<String,String> countMap = indexViewService.getProjectStatistics();
		model.put("countMap", countMap);
		
		//7、查询招标公告
    	page = new Page(1L, 10L, 10, new ArrayList<Bulletin>());
    	paramsMap.clear();
		paramsMap.put("status", "biddingBulletin");
		model.put("biddingBulletinList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		//8、查询中标公告
		page = new Page(1L, 10L, 10, new ArrayList<Bulletin>());
		paramsMap.clear();
		paramsMap.put("status", "winbidBulletin");
		model.put("winbidBulletinList", bulletinShowService.getBulletinForList(page, paramsMap).getData());
		
		return new ModelAndView("showBulletinIndexView", model);
	}
	
	
	/** 
	 * Description :  跳转到采购项目展示的二级页面，也就是列表页面
	 * Create Date: 2010-10-20下午02:45:13 by likg  Modified Date: 2010-10-20下午02:45:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toBulletinList")
    public ModelAndView toBulletinList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>(); // 查询条件
    	
    	String districtLevel = request.getParameter("districtLevel"); // 区域级别
    	String districtId = request.getParameter("districtId"); // 区域ID
    	String bulletinType = request.getParameter("bulletinType"); //公告类型
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));//关键字
    	String purCategoryCode = request.getParameter("purCategoryCode"); //采购品目编号
    	String purCategoryId = request.getParameter("purCategoryId"); //采购品目id
    	
    	Short level = 0;
    	if(StringUtils.hasLength(districtLevel)) {
    		level = new Short(districtLevel);
    	}
    	paramsMap.put("districtLevel", districtLevel);
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("districtId", districtId);
    	paramsMap.put("purCategoryId", purCategoryId);
    	paramsMap.put("bulletinType", bulletinType);
    	
    	//获取不同采购价区段的项目数量
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("purCategoryId", purCategoryId);
    	params.put("bulletinType", bulletinType);
    	List<String[]> bulletinNumList = bulletinShowService.getBulletinListShowByBudgetMoney(params);
    	model.put("bulletinNumList", bulletinNumList);
    	
    	//获取区域信息
    	params.clear();
    	params.put("purCategoryId", purCategoryId);
    	params.put("bulletinType", bulletinType);
    	params.put("districtLevel", level);
    	List<String[]> listDistrict = bulletinShowService.getDistrictListForShow(params);
    	model.put("districtList", listDistrict);
    	
    	//获取商品分类信息
    	Map<String, Object> params2 = new HashMap<String, Object>();
    	params2.put("purCategoryCode", purCategoryCode);
    	params2.put("bulletinType", bulletinType);
    	params2.put("isLeaf", true);
    	List<String[]> listClass = bulletinShowService.getGoodsClassList(params2);
    	model.put("purCategoryList", listClass);
    	
    	//获取采购项目信息
    	Page<Bulletin> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Bulletin> pageData = bulletinShowService.getBulletinListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	//回设高亮参数或首页参数
    	model.put("districtId", districtId);
    	model.put("districtLevel", districtLevel);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("currentTime", new Date()); //当前时间
    	model.put("purCategoryId",purCategoryId); //采购品目id
    	model.put("purCategoryCode",purCategoryCode); //采购品目Code
    	model.put("bulletinType", bulletinType); //公告类型
    	if(StringUtils.hasLength(purCategoryId)) {
    		PurCategory purCategory = purCategoryService.get(purCategoryId);
    		model.put("purCategoryName", purCategory.getCategoryName()); //采购品目名称
    	}
    	
    	return new ModelAndView("bulletinShowListView",model);
    }
	
	
	/** 
	 * Description :  采购预告列表页面
	 * Create Date: 2010-12-30下午02:45:13 by dongcl  Modified Date: 2010-12-10下午02:45:13 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toBuyPreList")
    public ModelAndView toBuyPreList(HttpServletRequest request) throws Exception
    {
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>(); // 查询条件   	
    	
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));//关键字    	
    	
    	String isRecommendProject = request.getParameter("isRecommendProject"); //推荐采购项目公告  	
   
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	
    	paramsMap.put("isRecommendProject", isRecommendProject);    	
    	
    	//获取采购项目信息
    	Page<Object> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Object> pageData = bulletinShowService.getBulletinBuyPreList(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	//回设参数   
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));    
    	
    	return new ModelAndView("bulletinBuyPreView",model);
    }
	
	/** 
	 * Description :   获取采购预购的列表	 * 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBulletinBuyPreForList")
	public ModelAndView getBulletinBuyPreForList(HttpServletRequest request) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();

    	//排序    	
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort")); //公告创建时间
    	
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	
    	//获取采购项目信息
    	Page<Object> page = prePage(request);
    	Page<Object> pageData = bulletinShowService.getBulletinBuyPreList(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	
		return new ModelAndView("bulletinShowForBuyPreListView", model);
	}
	
	/** 
	 * Description :   获取采购项目展示的列表数据(详细信息)
	 * Create Date: 2010-10-21上午09:59:43 by likg  Modified Date: 2010-10-21上午09:59:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBulletinForList")
	public ModelAndView getBulletinForList(HttpServletRequest request) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//采购金额区间和区域和商品类别
    	paramsMap.put("intervalBudgetMoney", request.getParameter("intervalBudgetMoney"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	paramsMap.put("purCategoryId", request.getParameter("purCategoryId"));
    	
    	//排序
    	paramsMap.put("budgetMoneySort", request.getParameter("budgetMoneySort")); //采购金额
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort")); //公告创建时间
    	
    	//高级搜索条件
    	paramsMap.put("evaltime_0", request.getParameter("evaltime_0"));
    	paramsMap.put("evaltime_1", request.getParameter("evaltime_1"));
    	paramsMap.put("evaltime_2", request.getParameter("evaltime_2"));
    	
    	paramsMap.put("isRecommendProject", request.getParameter("isRecommendProject"));
    	paramsMap.put("isPurchasePreview", request.getParameter("isPurchasePreview"));
    	
    	paramsMap.put("bulletinType", request.getParameter("bulletinType")); //公告类型
    	paramsMap.put("ebuyMethod", request.getParameter("ebuyMethod")); //采购方式
    	
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	
    	//获取采购项目信息
    	Page<Bulletin> page = prePage(request);
    	Page<Bulletin> pageData = bulletinShowService.getBulletinListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	model.put("currentTime", new Date()); //当前时间
    	
		return new ModelAndView("bulletinShowForListView", model);
	}
	
	/** 
	 * Description :  根据采购金额区间获得区域的展示信息集合
	 * Create Date: 2010-10-21上午10:13:48 by likg  Modified Date: 2010-10-21上午10:13:48 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByIntervalBudgetMoney")
	public ModelAndView getDistrictListShowByIntervalBudgetMoney(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String intervalBudgetMoney = request.getParameter("intervalBudgetMoney"); //采购金额区间
		String districtLevel = request.getParameter("districtLevel"); //区域级别
		String districtId = request.getParameter("districtId"); //区域Id
		String purCategoryId = request.getParameter("purCategoryId"); //采购品目Id
		
		//处理区域级别
		Short level = 0;
    	if(StringUtils.hasLength(districtLevel)) {
    		level = new Short(districtLevel);
    	}
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("intervalBudgetMoney", intervalBudgetMoney);
    	param.put("districtLevel", level);
    	param.put("districtId", districtId);
    	param.put("purCategoryId", purCategoryId);
    	List<String[]> district = bulletinShowService.getDistrictListForShow(param);
    	model.put("result", district);
    	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * Description :  根据采购金额区间和区域获取商品类别的信息集合
	 * Create Date: 2010-11-23下午12:43:07 by likg  Modified Date: 2010-11-23下午12:43:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsClassListShowByIntervalBudgetMoneyAndDistrict")
	public ModelAndView getGoodsClassListShowByIntervalBudgetMoneyAndDistrict(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		String intervalBudgetMoney = request.getParameter("intervalBudgetMoney"); //采购金额区间
		String districtLevel = request.getParameter("districtLevel"); //区域级别
		String districtId = request.getParameter("districtId"); //区域Id
		String purCategoryId = request.getParameter("purCategoryId"); //采购品目Id
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("intervalBudgetMoney", intervalBudgetMoney);
		params.put("districtId", districtId);
		params.put("isLeaf", true);
		params.put("districtLevel", districtLevel);
		params.put("purCategoryId", purCategoryId);
		List<String[]> purCategoryInfo = bulletinShowService.getGoodsClassList(params);
		model.put("result", purCategoryInfo);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得推荐的采购项目列表
	 * Create Date: 2010-10-21下午06:18:16 by likg  Modified Date: 2010-10-21下午06:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getRecommendProject")
	public ModelAndView getRecommendProject(HttpServletRequest request, String viewName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
    	paramsMap.put("intervalBudgetMoney", request.getParameter("intervalBudgetMoney")); //采购金额区间
    	paramsMap.put("categoryCode", request.getParameter("categoryCode")); //采购品目编号
		
    	//获取推荐项目数据
		Page<RecommendProject> page = prePage(request);
		Page<RecommendProject> pageData = recommendProjectService.getRecommendProject(page, paramsMap);
		model.put("recommendProjects", pageData.getData());
		
    	if(!StringUtils.hasLength(viewName)) {
    		viewName = "bulletinRecommendView";
    	}
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  获得采购项目的详细信息
	 * Create Date: 2010-10-22上午08:41:06 by likg  Modified Date: 2010-10-22上午08:41:06 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBulletinInfo")
	public ModelAndView getBulletinInfo(String objId, String viewName, HttpServletRequest request, HttpSession session) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		
		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr",session.getAttribute("roleStr"));
		}
		
		//获取公告信息
		Bulletin bulletin = null;
		if(StringUtils.hasLength(objId)) {
			bulletin = bulletinService.get(objId);
		} else {
			String bulletinType = request.getParameter("bulletinType"); //公告类型
			String projectId = request.getParameter("projectId"); //项目Id
			bulletin = bulletinService.get(projectId);
			if(bulletin==null){
				List<Bulletin> bulletinList = bulletinShowService.getBulletinList(projectId, bulletinType);
				if(bulletinList!=null && !bulletinList.isEmpty()) {
					bulletin = bulletinList.get(0);
				}
			}
		}
		model.put("bulletin", bulletin);
		try {
			Attachment content = bulletin.getContent();
			String attachPath = content.getPath()==null?"":content.getPath();
			String contentPath = getDefaultPath() + attachPath+content.getFileName();
			
		    InputStream in = new FileInputStream(contentPath);
			model.put("bullContents",FileUtil.read(in));
			
			in.close();
		} catch (Exception e) {
			model.put("bullContents", "附件不存在或路径错误！");
		}
		
		//获取项目公告发布者的信息
		OrgInfo orgInfo = orgInfoService.get(bulletin.getProject().getBuyersId());
		Buyer buyer = buyerService.getBuyerAllInfo(orgInfo.getBuyerId());
		model.put("buyer", buyer);
		param.put("orgInfoId", orgInfo.getObjId());
		Long bulletinNum = bulletinShowService.getBulletinNum(param);
		model.put("bulletinNum", bulletinNum);
		
		//获取项目的进度信息，供应商报名信息，当前用户信息
		bulletinShowService.getProjectInfo(bulletin.getProject(), bulletin.getBullType(), model);
		
		//获取相关公告(品目相关或发布者相关)
		Page<Bulletin> page = new Page(1, 10, 10, new ArrayList<Bulletin>());
		param.clear();
		param.put("purCategoryIds", bulletin.getProject().getPurCategoryIds());
		param.put("orgInfoId", bulletin.getProject().getBuyersId());
		model.put("projectCreator", bulletin.getProject().getCreateUser().getObjId());//项目创建人
		param.put("status", "relatedBulletin");
		model.put("relatedBulletinList", bulletinShowService.getBulletinForList(page, param).getData());
		
		//竞价公告
		if(BulletinTypeEnum.BARGIN_BULLETIN.equals(bulletin.getBullType())) {
			//获取已报名的供应商列表
			List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(bulletin.getProject().getObjId());
			model.put("signUprecordList", signUprecordList);
		}
		//结果公告
		else if(BulletinTypeEnum.BARGIN_RESULT_BULLETIN.equals(bulletin.getBullType())) {
			//取得成交信息(已结束报价的项目)
			param.clear();
			param.put("projectId", bulletin.getProject().getObjId());
			List<BuyWinner> buyWinnerList = (List<BuyWinner>)buyResultServiceXygh.getBuyResultData(param).get("buyWinnerList");
			
			//取竞价/议价记录
			List<BiddingRecord> biddingRecordList = null;
			if(EbuyMethodEnum.COMPETITION.equals(bulletin.getProject().getEbuyMethod())){
				biddingRecordList =  biddingRecordService.getMinBiddingRecordByProjectId(bulletin.getProject().getObjId(),null);
			}else if(EbuyMethodEnum.TALK.equals(bulletin.getProject().getEbuyMethod())){
				biddingRecordList =  biddingRecordService.getMinBiddingRecordByTalkProjectId(bulletin.getProject().getObjId(),null);
			}
			
			//组装记录
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			for(BuyWinner buyWinner: buyWinnerList){
				if(biddingRecordList!=null){
					for(BiddingRecord biddingRecord: biddingRecordList){
						if(buyWinner.getResultType().equals(ResultTypeEnum.DEAL_YES) &&buyWinner.getSelllerId().equals(biddingRecord.getSupplier().getObjId())){
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("winner",buyWinner );
							map.put("record", biddingRecord);
							resultList.add(map);
						}
					}
				}
			}
			model.put("resultList", resultList);
		}
		
		if(!StringUtils.hasLength(viewName)) {
			viewName = "bulletinShowInfoView";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description : 查看公告内容 
	 * Create Date: 2010-10-9下午04:22:30 by guoyr  Modified Date: 2010-10-9下午04:22:30 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowBulletinView")
	public ModelAndView toShowBulletinView(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(StringUtils.hasLength(objId)) {
			Bulletin bulletin  = bulletinService.get(objId);
			model.put("bulletin", bulletin);
			
			try {
				Attachment content = bulletin.getContent();
				String attachPath = content.getPath()==null?"":content.getPath();
				String contentPath = getDefaultPath() + attachPath+content.getFileName();
				InputStream in = new FileInputStream(contentPath);
				model.put("bullContents", FileUtil.read(in));
				in.close();
			} catch (Exception e) {
				model.put("bullContents", "附件不存在或路径错误！");
			}
		}else {
			model.put("bulletin", new Bulletin());
		}
		
		return new ModelAndView("bulletinShowDetailView", model);
	}
	
	/** 
	 * Description :  打印项目公告
	 * Create Date: 2011-2-17上午09:40:32 by likg  Modified Date: 2011-2-17上午09:40:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=printBulletin")
	public ModelAndView printBulletin(HttpServletRequest request, String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(StringUtils.hasLength(objId)) {
			Bulletin bulletin  = bulletinService.get(objId);
			model.put("bulletin", bulletin);
			
			try {
				Attachment content = bulletin.getContent();
				String attachPath = content.getPath()==null?"":content.getPath();
				String contentPath = getDefaultPath() + attachPath+content.getFileName();
				InputStream in = new FileInputStream(contentPath);
				model.put("bulletinContents", FileUtil.read(in));
				in.close();
			} catch (Exception e) {
				model.put("bulletinContents", "附件不存在或路径错误！");
			}
		}else {
			model.put("bulletin", new Bulletin());
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  将项目采购公告通过邮件分享给好友
	 * Create Date: 2011-2-28上午11:26:17 by likg  Modified Date: 2011-2-28上午11:26:17 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=shareBulletin")
	public ModelAndView shareBulletin(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("bulletinId", request.getParameter("bulletinId"));
		param.put("toEmail", request.getParameter("toEmail"));
		
		bulletinShowService.shareBulletin(param);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得配置文件中的文件存储路径
	 * Create Date: 2011-6-24下午05:05:28 by sunl  Modified Date: 2011-6-24下午05:05:28 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getDefaultPath() throws Exception {
		return messageSource.getMessage("uploadUrl");
	}
	
	/** 
	 * Description :  根据项目Id获取公告信息
	 * Create Date: 2011-8-16下午02:25:00 by likg  Modified Date: 2011-8-16下午02:25:00 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBulletinByProjectId")
	public ModelAndView getBulletinByProjectId(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//获取公告信息
		String bulletinType = request.getParameter("bulletinType"); //公告类型
		String projectId = request.getParameter("projectId"); //项目Id
		model.put("bulletinList", bulletinShowService.getBulletinList(projectId, bulletinType));
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到招标文件的下载页面
	 * Create Date: 2011-10-18下午05:13:32 by likg  Modified Date: 2011-10-18下午05:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDownloadPurchaseDocFileView")
	public ModelAndView toDownloadPurchaseDocFileView(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//获取采购文件信息
		String projectId = request.getParameter("projectId"); //项目Id
		PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		if(null != purchaseDoc) {
			//查询供应商的报名信息
			Boolean signUpAuditPassed = false;
			SignUprecord signUprecord = signUprecordService.getSignUprecordBySupplierId(projectId, AuthenticationHelper.getCurrentUser());
			if(signUprecord!=null && AuditStatusEnum.AUDIT_PASS.equals(signUprecord.getAuditStatus())) { //审核通过
				signUpAuditPassed = true;
				
				//获取采购文件的附近信息
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
				if (null!=attachmentList && !attachmentList.isEmpty()) {
					model.put("attachmentName", attachmentList.get(0).getViewName());
				}
				model.put("purchaseDoc", purchaseDoc);
			}
			
			model.put("signUpAuditPassed", signUpAuditPassed);
		}
		
		return new ModelAndView("downloadPurchaseDocFileView", model);
	}
}
