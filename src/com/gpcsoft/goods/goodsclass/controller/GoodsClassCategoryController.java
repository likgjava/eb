package com.gpcsoft.goods.goodsclass.controller;


import java.util.ArrayList;
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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;
import com.gpcsoft.goods.goodsclass.service.GoodsClassCategoryService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="goodsClassCategoryFormView"
  *  url="view///goodsClassCategoryForm.jsp"
  * @gpcsoft.view value="goodsClassCategoryTreeFormView"
  *  url="view///goodsClassCategoryTreeForm.jsp"
  * @gpcsoft.view value="goodsClassCategoryListView"
  *  url="view///goodsClassCategoryList.jsp"
  * @gpcsoft.view value="goodsClassCategoryDetailView"
  *  url="view///goodsClassCategoryDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsClassCategory.class})
@RequestMapping("/GoodsClassCategoryController.do")//页面请求路径,可修改
public class GoodsClassCategoryController extends AnnotationMultiController<GoodsClassCategory> {

	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	@Autowired(required=true) @Qualifier("goodsClassCategoryServiceImpl")
	private GoodsClassCategoryService goodsClassCategoryService;
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoServiceImpl;
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryServiceImpl;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;

	/** 
	 * Description :  根据品目获取商品分类信息
	 * Create Date: 2010-8-3上午11:03:32 by sunl  Modified Date: 2010-8-3上午11:03:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsClassByCategory")
	public ModelAndView getGoodsClassByCategory(String categoryId,String categoryCode,Boolean isLeaf,HttpServletRequest request) throws Exception {
		List<GoodsClass> goodsClass=new ArrayList<GoodsClass>();
		goodsClass = goodsClassCategoryService.getClassListByCategory(categoryId,categoryCode,true);
    	Map model = new HashMap();
    	model.put("result", goodsClass);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/** 
	 * Description : 获取还未指定维护供应商的产品分类 
	 * Create Date: 2010-8-6上午09:18:06 by guoyr  Modified Date: 2010-8-6上午09:18:06 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getUnSpecifiedGoodsClass")
	public ModelAndView getUnSpecifiedGoodsClass(String objId, String goodsBrandId) throws Exception {
		List<GoodsClass> goodsClass=new ArrayList<GoodsClass>();
		goodsClass = goodsClassCategoryService.getUnSpecifiedGoodsClass(objId,goodsBrandId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", goodsClass);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * Description :  根据分类获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAssignedPurcategory")
	public ModelAndView getAssignedPurcategory(String classId) throws Exception {
		List<PurCategory> purCategoryList=new ArrayList<PurCategory>();
		purCategoryList = goodsClassCategoryService.getAssignedPurcategory(classId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", purCategoryList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据经营范围获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCategoryByMainProducts")
	public ModelAndView getCategoryByMainProducts(HttpServletRequest request) throws Exception {
		List<Object[]> res = new ArrayList<Object[]>();
		String bidForRangeCodes = "";
		//判断当前用户角色,如果是管理员或者商品库管理员，则获取所有品目
		User user = AuthenticationHelper.getCurrentUser(true);
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER) || roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_GOODS_MANAGER)){
			res = purCategoryServiceImpl.getCategoryByMainProducts("");
		} else {
			bidForRangeCodes = orgInfoServiceImpl.get(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()).getBidForRangeCode();
			if(StringUtils.hasLength(bidForRangeCodes)) {
				res = purCategoryServiceImpl.getCategoryByMainProducts(bidForRangeCodes);
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", res);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据品目获取商品分类信息(jdbc查询方式)
	 * Create Date: 2010-8-3上午11:03:32 by sunl  Modified Date: 2010-8-3上午11:03:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getClassByCategory")
	public ModelAndView getClassByCategory(String categoryId,String categoryCode,Boolean isLeaf,HttpServletRequest request) throws Exception {
		List<Object[]> res = new ArrayList<Object[]>();
		res = goodsClassCategoryService.getClassByCategory(categoryId,categoryCode,true);
    	Map model = new HashMap();
    	model.put("result", res);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据关键字查询商品类项目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getProductsBySearchName")
	public ModelAndView getProductsBySearchName(HttpServletRequest request) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		OrgInfo orgInfo = orgInfoServiceImpl.get(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		//判断当前用户角色,如果是管理员或者商品库管理员，则获取所有品目
		User user = AuthenticationHelper.getCurrentUser(true);
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER) || roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_GOODS_MANAGER)){
			params.put("categoryCodes", "");
		} else {
			params.put("categoryCodes", orgInfo.getBidForRangeCode());
		}
		params.put("keyWords", StringUtils.ascii2Native(request.getParameter("keyWords")));
		
		List<String[]> products = goodsService.getProductsBySearchName(params);
		model.put("result", products);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
