package com.gpcsoft.bizplatform.suppliers.controller;

import java.util.ArrayList;
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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;
import com.gpcsoft.bizplatform.base.industry.service.IndustryService;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.RecommendSupplierService;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ShangquanhuiyuanPrivilegeService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.auth.service.UserService;

/**
  * @gpcsoft.view value="supplierShowListView"
  *  url="view/bizplatform/suppliers/showsupplier/show_supplier_list.jsp"
  * @gpcsoft.view value="supplierShowForListView"
  *  url="view/bizplatform/suppliers/showsupplier/supplier_list_div_l.jsp"
  * @gpcsoft.view value="supplierShowInfoView"
  *  url="view/bizplatform/suppliers/showsupplier/show_supplier_detail.jsp"
  * @gpcsoft.view value="supplierRecommendView"
  *  url="view/bizplatform/suppliers/showsupplier/supplier_recommend_list.jsp"
  * @gpcsoft.view value="recommendSupplierIndexView"
  *  url="view/srplatform/portal/include/recommend_supplier_list.jsp"
  * @gpcsoft.view value="showSupplierIndexView"
  *  url="view/bizplatform/suppliers/showsupplier/show_supplier_index.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/SupplierShowController.do")//页面请求路径,可修改
public class SupplierShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	
	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
	@Autowired(required=true) @Qualifier("industryServiceImpl")
	private IndustryService industryService;
	
	@Autowired(required=true) @Qualifier("recommendSupplierServiceImpl")
	private RecommendSupplierService recommendSupplierService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("userServiceImpl")
	UserService userService;
	
	@Autowired(required = true) @Qualifier("userManagerImpl")
	UserManager userManager;
	
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryServiceImpl;
	
	@Autowired(required=true) @Qualifier("shangquanhuiyuanPrivilegeServiceImpl")
	private ShangquanhuiyuanPrivilegeService shangquanhuiyuanPrivilegeService;
	
    /** 
     * Description :  跳转到供应商展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toSupplierList")
    public ModelAndView toSupplierList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** categoryCode和districtId可能来自与详情页面的参数；evalSort和keyWord,districtLevel来自与首页的参数 */
    	String districtLevel = request.getParameter("districtLevel");
    	String evalSort = request.getParameter("evalSort");
    	String categoryCode = request.getParameter("categoryCode");
    	String districtId = request.getParameter("districtId");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	
    	Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	paramsMap.put("districtLevel", districtLevel);
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("evalSort", evalSort);
    	paramsMap.put("categoryCode", categoryCode);
    	paramsMap.put("districtId", districtId);
    	
    	
    	/** 取下级品目展示信息*/
    	List<String[]> listCategory = supplierService.getCategoryListShowByCategory("", categoryCode, true,keyWord);
    	model.put("categoryList", listCategory);
    	
    	/** 取区域信息*/
    	List<String[]> listDistrict = supplierService.getDistrictListForShow(null, categoryCode,level,keyWord);
    	model.put("districtList", listDistrict);
    	
    	/** 获取行业信息*/
    	List<Industry> listIndustry = industryService.getIndustryByLevel(new Short("1"));  //获得顶级行业
    	model.put("industryList", listIndustry);
    	
    	/** 取供应商信息*/
    	Page<Supplier> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Supplier> pageData = supplierService.getSupplierListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("districtId", districtId);
    	model.put("categoryCode", categoryCode);
    	model.put("districtLevel", districtLevel);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	model.put("evalSort", evalSort);
    	
    	if(StringUtils.hasLength(categoryCode)){
    		for(Object[] object: listCategory){
    			if(categoryCode.equals( (String)object[1] )) model.put("categoryName",(String) object[2] );
    		}
    	}
    	
        return new ModelAndView("supplierShowListView",model);
    }
    
    
	/** 
	 * Description :  获取供应商展示的列表数据
	 * Create Date: 2010-7-27上午11:46:10 by liangxj  Modified Date: 2010-7-27上午11:46:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getSupplierForList")   
	public ModelAndView getSupplierForList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//品目和区域
    	paramsMap.put("categoryCode", request.getParameter("categoryCode"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	
    	//排序
    	paramsMap.put("evalSort", request.getParameter("evalSort"));
    	paramsMap.put("validSort", request.getParameter("validSort"));
    	
    	//高级搜索条件
    	paramsMap.put("isManufacturerC", request.getParameter("isManufacturerC"));//是否是厂商
    	paramsMap.put("isManufacturerJ", request.getParameter("isManufacturerJ"));
    	
    	paramsMap.put("entPrpt", request.getParameter("entPrpt"));  //企业性质
    	paramsMap.put("belongIndustry", request.getParameter("belongIndustry"));  //所属行业
    	paramsMap.put("unitScape", request.getParameter("unitScape"));//企业规模
    	String keyWord = request.getParameter("keyWord");//关键字
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("regMoneyType", request.getParameter("regMoneyType"));
    
    	
    	/** 取供应商信息 */
    	Page<Supplier> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Supplier> pageData = supplierService.getSupplierListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
		return new ModelAndView("supplierShowForListView",model);
    }
	
	/** 
	 * Description :  根据投标类别获得区域的展示信息集合
	 * Create Date: 2010-8-3上午11:44:58 by sunl  Modified Date: 2010-8-3上午11:44:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByCategory")
	public ModelAndView getDistrictListShowByCategory(String categoryCode,String districtId,String districtLevel,HttpServletRequest request) throws Exception {
		String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
		
		Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	List<String[]> district = supplierService.getDistrictListForShow(districtId, categoryCode,level,keyWord);
		
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", district);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 获得推荐供应商--根据评价 
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-8-20上午11:58:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendSupplier")
	public ModelAndView getRecommendSupplier(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		/** keyWord来自与首页的参数 */
		String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("categoryCode", request.getParameter("categoryCode"));
		paramsMap.put("evalSort", "desc");
		
		/** 取供应商信息*/
    	Page<Supplier> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Supplier> pageData = supplierService.getSupplierListForShow(page,paramsMap);
    	model.put("supplierList",pageData.getData());

		return new ModelAndView("supplierRecommendView", model);
	}
	
	/** 
	 * Description :  获得供应商的详细信息，包含资质、成功案例、评价
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getSupplierInfo")
	public ModelAndView getSupplierInfo(String objId ,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询供应商
		Supplier supplier = supplierService.getSupplierAllInfo(objId);
		model.put("supplier", supplier);
		
		//导航参数
		model.put("categoryCode", request.getParameter("categoryCode"));
		model.put("districtId", request.getParameter("districtId"));
		
		if(request.getParameter("categoryName")!=null&&!"".equals(request.getParameter("categoryName"))){
			model.put("categoryName", StringUtils.ascii2Native(request.getParameter("categoryName")));
		}
		if(request.getParameter("districtName")!=null&&!"".equals(request.getParameter("districtName"))){
			model.put("districtName", StringUtils.ascii2Native(request.getParameter("districtName")));
		}

		//查询评价详细信息 
		List<String[]> quotaDetailList = (List<String[]>)evaluateService.getQuataScoreDetail(supplier.getOrgInfo(),"01");
		model.put("quotaDetailList", quotaDetailList);
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
    	
    	//是否显示联系方式
    	if(AuthenticationHelper.getCurrentUser() != null  && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
    		model.put("isShowContact", shangquanhuiyuanPrivilegeService.getIsShowContact((AuthenticationHelper.getCurrentUser().getOrgInfo()).getObjId(), supplier.getOrgInfo().getObjId()));
    	}
    	
		return new ModelAndView("supplierShowInfoView", model);
	}
	
	/** 
	 * Description :  获得推荐的供应商--根据推荐表
	 * Create Date: 2010-10-19上午11:18:57 by likg  Modified Date: 2010-10-19上午11:18:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendSupplierInfo")
	public ModelAndView getRecommendSupplierInfo(HttpServletRequest request, String viewName) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("categoryCode", request.getParameter("categoryCode"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
		
		Page<Supplier> page = prePage(request);
		Page<Supplier> pageData = recommendSupplierService.getRecommendSupplierInfo(page, paramsMap);
    	model.put("supplierList",pageData.getData());
    	
    	String viewStr = "supplierRecommendView";
    	if(null!=viewName) 
    	{
    		viewStr = viewName;
    	}
		
		return new ModelAndView(viewStr, model);
	}
	
	
	/** 
	 * Description :  跳转到供应商库首页
	 * Create Date: 2011-2-18下午04:22:45 by sunl  Modified Date: 2011-2-18下午04:22:45 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowSupplierIndexView")
	public ModelAndView toShowSupplierIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获得用户角色
		User user=AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr",session.getAttribute("roleStr"));
		}
		model.put("user", user);
    	
		//推荐供应商
		Page<Supplier> pageSupplierRecommend = new Page<Supplier>(1, 12, 12, new ArrayList<Supplier>());
		model.put("supplierList", recommendSupplierService.getRecommendSupplierInfo(pageSupplierRecommend, paramsMap).getData());
		
    	//最新加盟供应商
		Page<Supplier> pageSupplierNew = new Page<Supplier>(1, 8, 8, new ArrayList<Supplier>());
		paramsMap.clear();
    	model.put("newSupplierList", supplierService.getSupplierListForShow(pageSupplierNew, paramsMap).getData());
    	
		//获得热门标签信息
    	model.put("hotTagsType", OrganizationEnum.SUPPLIER_TAGS);
    	
    	//获取采购品目分类，组装数据
    	QueryObject<PurCategory> query = new QueryObjectBase<PurCategory>();
    	query.setEntityClass(PurCategory.class);
    	query.getQueryProjections().setOrderProperty("objId");
		query.getQueryProjections().setDescFlag(false);
    	List<PurCategory> allList = purCategoryServiceImpl.findByQuery(query);
    	List<PurCategory> purCategoryList = new ArrayList<PurCategory>();
		String currentId = null;
		PurCategory currentCategory = null;
		for(PurCategory p : allList) {
			//第一级
			if(p.getObjId().length() == 1) {
				purCategoryList.add(p);
			}
			//是上一个的孩子
			else if(p.getObjId().startsWith(currentId)) {
				p.setParent(currentCategory);
				currentCategory.getChildren().add(p);
			}
			//是上一个的兄弟
			else if(p.getObjId().length() == currentId.length()) {
				p.setParent(currentCategory.getParent());
				currentCategory.getParent().getChildren().add(p);
			}
			//是上一个的叔叔
			else if(currentId.length() - p.getObjId().length() == 2) {
				p.setParent(currentCategory.getParent().getParent());
				currentCategory.getParent().getParent().getChildren().add(p);
			}
			//是上一个的爷爷
			else if(currentId.length() - p.getObjId().length() == 4) {
				p.setParent(currentCategory.getParent().getParent().getParent());
				currentCategory.getParent().getParent().getParent().getChildren().add(p);
			}
			currentId = p.getObjId();
			currentCategory = p;
		}
		model.put("purCategoryList", purCategoryList);
		
		return new ModelAndView("showSupplierIndexView", model);
	}
}
