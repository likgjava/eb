package com.gpcsoft.agreement.bargin.project.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.project.domain.RecommendProject;
import com.gpcsoft.agreement.bargin.project.service.ProjectShowService;
import com.gpcsoft.agreement.bargin.project.service.RecommendProjectService;
import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  @gpcsoft.view value="showBargainProjectListView"
  *  url="view/agreement/bargin/project/showproject/show_bargain_project_list.jsp"
  *  
  *  @gpcsoft.view value="bargainProjectListForShowView"
  *  url="view/agreement/bargin/project/showproject/bargain_project_list_div_l.jsp"
  *  
  *  @gpcsoft.view value="showBiddingProjectListView"
  *  url="view/agreement/bargin/project/showproject/show_bidding_project_list.jsp"
  *  
  *  @gpcsoft.view value="biddingProjectListForShowView"
  *  url="view/agreement/bargin/project/showproject/bidding_project_list_div_l.jsp"
  *  
  *  @gpcsoft.view value="recommendProjectSuppView"
  *  url="view/agreement/bargin/project/showproject/recommend_project_supp.jsp"
  *  
  *  @gpcsoft.view value="recommendBiddingProjectIndexView"
  *  url="view/agreement/showbulletin/recommend_bidding_project.jsp"
  *  
  *  @gpcsoft.view value="recommendBargainProjectIndexView"
  *  url="view/agreement/showbulletin/recommend_bargain_project.jsp"
 */

@Controller
@Scope("request")
@SessionAttributes(types={Project.class})
@RequestMapping("/ProjectShowController.do")
public class ProjectShowController extends AnnotationMultiController<Project> {
	
	@Autowired(required=true) @Qualifier("projectShowServiceImpl")
	private ProjectShowService projectShowService;
	
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	
	@Autowired(required=true) @Qualifier("recommendProjectServiceImpl")
	private RecommendProjectService recommendProjectService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	/** 
	 * Description :  跳转到电子采购项目列表展示页面，二级页面
	 * Create Date: 2011-8-15上午10:40:05 by likg  Modified Date: 2011-8-15上午10:40:05 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toShowBargainProjectList")
    public ModelAndView toShowBargainProjectList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord")); //关键字
    	String purCategoryId = request.getParameter("purCategoryId"); //采购品目id
    	paramsMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION); //竞价采购方式
    	paramsMap.put("purCategoryId", purCategoryId);
    	paramsMap.put("districtId", request.getParameter("districtId")); //区域ID
    	paramsMap.put("keyWord", keyWord);
    	
    	//获取不同采购价区段的项目数量
    	List<String[]> bulletinNumList = projectShowService.getAmountRangeListForShow(paramsMap);
    	model.put("bulletinNumList", bulletinNumList);
    	
    	//获取区域信息
    	List<Object[]> districtList = projectShowService.getDistrictListForShow(paramsMap);
    	model.put("districtList", districtList);
    	
    	//获取品目信息
    	List<Object[]> purCategoryList = projectShowService.getPurCategoryListForShow(paramsMap);
    	model.put("purCategoryList", purCategoryList);
    	
    	//获取采购项目信息
    	Page<Project> page = prePage(request);
    	Page<Project> pageData = projectShowService.getProjectListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	model.put("nowDate", new Date());
    	
    	//回设高亮参数或首页参数
    	model.put("districtId", request.getParameter("districtId"));
    	model.put("keyWord", StringUtils.returnIngoreStr(keyWord));
    	model.put("purCategoryId", purCategoryId); //采购品目id
    	if(StringUtils.hasLength(purCategoryId)) {
    		PurCategory purCategory = purCategoryService.get(purCategoryId);
    		model.put("purCategoryName", purCategory.getCategoryName()); //采购品目名称
    	}
    	
    	return new ModelAndView("showBargainProjectListView", model);
    }
	
	/** 
	 * Description :  获取电子采购项目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBargainProjectListForShow")
	public ModelAndView getBargainProjectListForShow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("amountRange", request.getParameter("amountRange")); //采购金额范围
    	paramsMap.put("districtId", request.getParameter("districtId")); //区域Id
    	paramsMap.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目id
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort")); //排序字段
    	paramsMap.put("isRecommendProject", request.getParameter("isRecommendProject")); //推荐项目
    	paramsMap.put("projectProcess", request.getParameter("projectProcess")); //项目进度
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(request.getParameter("keyWord"))));
    	paramsMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION); //竞价采购方式
    	
    	//获取采购项目信息
    	Page<Project> page = prePage(request);
    	Page<Project> pageData = projectShowService.getProjectListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT", pageData);
    	model.put("nowDate", new Date());
    	
		return new ModelAndView("bargainProjectListForShowView", model);
	}
	
	/** 
	 * Description :  跳转到电子招标项目列表展示页面，二级页面
	 * Create Date: 2011-8-15上午10:40:05 by likg  Modified Date: 2011-8-15上午10:40:05 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toShowBiddingProjectList")
	public ModelAndView toShowBiddingProjectList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord")); //关键字
		String purCategoryId = request.getParameter("purCategoryId"); //采购品目id
		paramsMap.put("ebuyMethod", EbuyMethodEnum.OPEN_BIDDING); //采购方式=公开招标
		paramsMap.put("purCategoryId", purCategoryId);
		paramsMap.put("keyWord", keyWord);
		
		//获取不同采购价区段的项目数量
		List<String[]> bulletinNumList = projectShowService.getAmountRangeListForShow(paramsMap);
		model.put("bulletinNumList", bulletinNumList);
		
		//获取区域信息
		List<Object[]> districtList = projectShowService.getDistrictListForShow(paramsMap);
		model.put("districtList", districtList);
		
		//获取品目信息
		List<Object[]> purCategoryList = projectShowService.getPurCategoryListForShow(paramsMap);
		model.put("purCategoryList", purCategoryList);
		
		//获取采购项目信息
		Page<Project> page = prePage(request);
		Page<Project> pageData = projectShowService.getProjectListForShow(page, paramsMap);
		endPage(model, pageData, request);
		model.put("PAGERESULT", pageData);
		model.put("nowDate", new Date());
		
		//回设高亮参数或首页参数
		model.put("keyWord", StringUtils.returnIngoreStr(keyWord));
		model.put("purCategoryId", purCategoryId); //采购品目id
		if(StringUtils.hasLength(purCategoryId)) {
			PurCategory purCategory = purCategoryService.get(purCategoryId);
			model.put("purCategoryName", purCategory.getCategoryName()); //采购品目名称
		}
		
		return new ModelAndView("showBiddingProjectListView", model);
	}
	
	/** 
	 * Description :  获取电子招标项目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBiddingProjectListForShow")
	public ModelAndView getBiddingProjectListForShow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("amountRange", request.getParameter("amountRange")); //采购金额范围
		paramsMap.put("districtId", request.getParameter("districtId")); //区域Id
		paramsMap.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目Id
		paramsMap.put("createTimeSort", request.getParameter("createTimeSort")); //排序字段
		paramsMap.put("isRecommendProject", request.getParameter("isRecommendProject")); //推荐项目
		paramsMap.put("projectProcess", request.getParameter("projectProcess")); //项目进度
		paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(request.getParameter("keyWord"))));
		paramsMap.put("ebuyMethod", EbuyMethodEnum.OPEN_BIDDING); //采购方式=公开招标
		
		//获取采购项目信息
		Page<Project> page = prePage(request);
		Page<Project> pageData = projectShowService.getProjectListForShow(page, paramsMap);
		endPage(model, pageData, request);
		model.put("PAGERESULT", pageData);
		model.put("nowDate", new Date());
		
		return new ModelAndView("biddingProjectListForShowView", model);
	}
	
	/** 
	 * Description :  获得区域列表展示信息集合
	 * Create Date: 2011-8-16上午09:27:11 by likg  Modified Date: 2011-8-16上午09:27:11 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListForShow")
	public ModelAndView getDistrictListForShow(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("ebuyMethod", request.getParameter("ebuyMethod")); //采购方式
		paramsMap.put("amountRange", request.getParameter("amountRange")); //采购金额范围
		paramsMap.put("districtId", request.getParameter("districtId")); //区域Id
		paramsMap.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目Id
    	List<Object[]> district = projectShowService.getDistrictListForShow(paramsMap);
    	model.put("result", district);
    	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取采购品目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPurCategoryListForShow")
	public ModelAndView getPurCategoryListForShow(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("ebuyMethod", request.getParameter("ebuyMethod")); //采购方式
		paramsMap.put("amountRange", request.getParameter("amountRange")); //采购金额范围
		paramsMap.put("districtId", request.getParameter("districtId")); //区域Id
		paramsMap.put("purCategoryId", request.getParameter("purCategoryId")); //采购品目Id
		List<Object[]> purCategoryList = projectShowService.getPurCategoryListForShow(paramsMap);
		model.put("purCategoryList", purCategoryList);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取页面右侧的推荐项目列表视图
	 * Create Date: 2011-8-17下午01:22:57 by likg  Modified Date: 2011-8-17下午01:22:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toRecommendProjectSuppView")
	public ModelAndView toRecommendProjectSuppView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("amountRange", request.getParameter("amountRange")); //采购金额范围
    	paramsMap.put("categoryId", request.getParameter("categoryId")); //采购品目Id
		
    	//获取推荐项目数据
		Page<RecommendProject> page = prePage(request);
		Page<RecommendProject> pageData = recommendProjectService.getRecommendProject(page, paramsMap);
		model.put("recommendProjectList", pageData.getData());
		
		return new ModelAndView("recommendProjectSuppView", model);
	}
	
	/** 
	 * Description :  加载推荐招标项目视图（电子招标首页）
	 * Create Date: 2011-10-31下午06:21:03 by likg  Modified Date: 2011-10-31下午06:21:03 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getRecommendBiddingProjectIndexView")
	public ModelAndView getRecommendBiddingProjectIndexView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		
		//6、获取推荐项目(招标项目)
		Page<RecommendProject> pageProj = new Page<RecommendProject>(1, 1, 1, new ArrayList<RecommendProject>());
		paramsMap.clear();
		paramsMap.put("ebuyMethod", EbuyMethodEnum.OPEN_BIDDING);
		List<RecommendProject> rpList = recommendProjectService.getRecommendProject(pageProj, paramsMap).getData();
		if(rpList!=null && rpList.size()==1) {
			RecommendProject recommendProject = rpList.get(0);
			model.put("recommendProject", recommendProject);
			
			//获取项目规则信息
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(recommendProject.getProject().getObjId());
			model.put("projectRule", projectRule);
			
			//获取当前用户与此项目的关系
			if(user!=null && user.getOrgInfo()!=null) {
				//OrgInfo orgInfo = (OrgInfo) user.getOrgInfo(); //当前用户的机构信息
				//重新到数据库中查询OrgInfo(防止在登录期间管理员审核机构信息，而获取不到机构的审核状态)
				OrgInfo orgInfo = orgInfoService.get(user.getOrgInfo().getObjId()); //当前用户的机构信息
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
				model.put("currentOrgId", orgInfo.getObjId());
			} else {
				model.put("userType", "visitor");
			}
		}
		model.put("nowDate", new Date());
		
		return new ModelAndView("recommendBiddingProjectIndexView", model);
	}
	
	/** 
	 * Description :  加载推荐竞价项目视图（电子采购首页）
	 * Create Date: 2011-10-31下午06:21:03 by likg  Modified Date: 2011-10-31下午06:21:03 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getRecommendBargainProjectIndexView")
	public ModelAndView getRecommendBargainProjectIndexView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获得用户角色
		User user = AuthenticationHelper.getCurrentUser();
		
		//5、获取推荐项目(竞价项目)
		Page<RecommendProject> pageProj = new Page<RecommendProject>(1, 1, 1, new ArrayList<RecommendProject>());
		paramsMap.clear();
		paramsMap.put("ebuyMethod", EbuyMethodEnum.COMPETITION);
		List<RecommendProject> rpList = recommendProjectService.getRecommendProject(pageProj, paramsMap).getData();
		if(rpList!=null && rpList.size()==1) {
			RecommendProject recommendProject = rpList.get(0);
			model.put("recommendProject", recommendProject);
			
			//获取项目规则信息
			List<ProjProcessRule> projRuleList = projProcessRuleService.getProjProcessRuleByProjectId(recommendProject.getProject().getObjId());
			model.put("projRuleList", projRuleList);
			
			//获取当前用户与此项目的关系
			if(user!=null && user.getOrgInfo()!=null) {
				//OrgInfo orgInfo = (OrgInfo) user.getOrgInfo(); //当前用户的机构信息
				//重新到数据库中查询OrgInfo(防止在登录期间管理员审核机构信息，而获取不到机构的审核状态)
				OrgInfo orgInfo = orgInfoService.get(user.getOrgInfo().getObjId()); //当前用户的机构信息
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
				model.put("currentOrgId", orgInfo.getObjId());
			} else {
				model.put("userType", "visitor");
			}
		}
		model.put("nowDate", new Date());
		
		return new ModelAndView("recommendBargainProjectIndexView", model);
	}
	
}
