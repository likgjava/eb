package com.gpcsoft.goods.goods.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsClassBrand;
import com.gpcsoft.goods.goods.service.GoodsClassBrandService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="goodsClassBrandFormView"
  *  url="view///goodsClassBrandForm.jsp"
  * @gpcsoft.view value="goodsClassBrandTreeFormView"
  *  url="view///goodsClassBrandTreeForm.jsp"
  * @gpcsoft.view value="goodsClassBrandListView"
  *  url="view///goodsClassBrandList.jsp"
  * @gpcsoft.view value="goodsClassBrandDetailView"
  *  url="view///goodsClassBrandDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsClassBrand.class})
@RequestMapping("/GoodsClassBrandController.do")//页面请求路径,可修改
public class GoodsClassBrandController extends AnnotationMultiController<GoodsClassBrand> {

	@Autowired(required=true) @Qualifier("goodsClassBrandServiceImpl")
	private GoodsClassBrandService goodsClassBrandService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  根据商品分类获得商品品牌的信息集合
	 * Create Date: 2010-8-3上午11:44:58 by sunl  Modified Date: 2010-8-3上午11:44:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBrandsListByClass")
	public ModelAndView getBrandsListByClass(String classId,String classCode) throws Exception {
		List<GoodsBrand> goodsBrand=new ArrayList<GoodsBrand>();
		
		goodsBrand = goodsClassBrandService.getBrandsListByClass(classId,classCode);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", goodsBrand);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据商品品牌获得商品的分类信息集合
	 * Create Date: 2011-1-18下午04:36:02 by yucy  Modified Date: 2011-1-18下午04:36:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getClassListByBrand")
	public ModelAndView getClassListByBrand(String brandId) throws Exception {
		List<GoodsClass> goodsClassList=new ArrayList<GoodsClass>();
		//获得分类信息之后需要拼装
		goodsClassList = goodsClassBrandService.getClassListByBrand(brandId);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", goodsClassList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取指定维护供应商的产品分类 
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAssignedGoodsClass")
	public ModelAndView getAssignedGoodsClass(String brandId) throws Exception {
		List<GoodsClass> goodsClassList=new ArrayList<GoodsClass>();
		OrgInfo currentOrgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo(); 
		String orgId = currentOrgInfo.getObjId();
		if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER)){
			orgId = "";
		}
		
		goodsClassList = goodsClassBrandService.getAssignedGoodsClass(brandId,orgId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", goodsClassList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
