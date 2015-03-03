package com.gpcsoft.agreement.cart.controller;

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

import com.gpcsoft.agreement.cart.domain.ShoppingCart;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.service.ShoppingCartService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="show_goods_list"
  *  url="view/order/showGoods/show_goods_list.jsp"
  *  
  * @gpcsoft.view value="toMyShoppinCartView"
  *  url="view/agreement/shoppingcart/my_shopping_car_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ShoppingCart.class})
@RequestMapping("/ShoppingCartController.do")//页面请求路径,可修改
public class ShoppingCartController extends AnnotationMultiController<ShoppingCart> {

	@Autowired(required=true) @Qualifier("shoppingCartServiceImpl")
	private ShoppingCartService shoppingCartService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
	 * Description :跳转我的购物车
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toMyShoppinCart")   
	public ModelAndView toMyShoppinCart(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		paramsMap.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());//当前用户orgInfo
		paramsMap.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());//当前用户userId

		List<ShoppingCartItem> ShoppingCartItemList = shoppingCartService.findMyShoppingCar(paramsMap);
		model.put("ShoppingCartItemList", ShoppingCartItemList);	
		
		return new ModelAndView("toMyShoppinCartView", model);
	}
	
	/** 
	 * Description :  跳转到显示挑选商品页面
	 * Create Date: 2010-5-13下午04:42:46 by wangsw  Modified Date: 2010-5-13下午04:42:46 by wangsw
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowGoodsList")   
	public ModelAndView toShowGoodsList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		paramsMap.put("categoryId", request.getParameter("categoryId"));//品目
		paramsMap.put("goodsClassId", request.getParameter("goodsClassId"));//分类
		//分类层次位置
		List<GoodsClass> gclevel = new ArrayList<GoodsClass>();
		//下级分类集合
		List<GoodsClass> gclassList = new ArrayList<GoodsClass>();
		if(paramsMap.get("goodsClassId")!=null){
			gclassList=shoppingCartService.findGoodsClassForShowGoods(paramsMap);
			GoodsClass gc = (GoodsClass)shoppingCartService.get(request.getParameter("goodsClassId"), GoodsClass.class);//当前分类
			while(gc!=null){
				gclevel.add(gc);
				gc = gc.getParentClazz();
			}
		}
			
		if(gclassList.size()==0)
			gclassList=null;
		List<GoodsBrand> brandList=shoppingCartService.findBrandForShowGoods(paramsMap);
		
		model.put("brandList", brandList);
		model.put("gclassList", gclassList);
		model.put("goodsClassId", request.getParameter("goodsClassId"));
		model.put("categoryId", request.getParameter("categoryId"));
		model.put("gclevel", gclevel);
		return new ModelAndView("show_goods_list",model);
    }
	
	/** 
	 * Description :  我的购物车
	 * Create Date: 2010-4-9上午10:41:55 by wangsw  Modified Date: 2010-4-9上午10:41:55 by yucy
	 * @Exception   
	 */
	@RequestMapping(params = "method=findMyShoppingCar")   
	public ModelAndView findMyShoppingCar(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		OrgInfo org = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		paramsMap.put("orgInfoId", org!=null?org.getObjId():"-1" );//当前用户orgInfo
		paramsMap.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());//当前用户userId
		List<ShoppingCartItem> list = shoppingCartService.findMyShoppingCar(paramsMap);
		model.put("rows", list);	
		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{//goodsParamSet
				"shoppingCart"
		});//FrameJsonView.INCLUDED_PROPERTIES  表示输出的时候包括复杂属性
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  删除购物车里的一个商品
	 * Create Date: 2010-4-14下午05:47:02 by wangsw  Modified Date: 2010-4-14下午05:47:02 by yucy
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeShoppingItem")   
	public ModelAndView removeShoppingItem(ShoppingCartItem shoppingCartItem) throws Exception {//request.getParameter("option").toString()
		Map<String, Object> model = new HashMap<String, Object>();   
		shoppingCartService.removeShoppingItem(shoppingCartItem);
		model.put(Constants.JSON_RESULT, "操作成功!");
		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{//goodsParamSet
				"goods"
		});//FrameJsonView.INCLUDED_PROPERTIES  表示输出的时候包括复杂属性
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description : 清空购物车
	 * Create Date: 2010-5-5上午09:54:34 by wangsw  Modified Date: 2010-5-5上午09:54:34 by yucy
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeAllByOrgInfo")   
	public ModelAndView removeAllByOrgInfo(OrgInfo orgInfo) throws Exception {//request.getParameter("option").toString()
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		param.put("orgId", orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
		param.put("userId",AuthenticationHelper.getCurrentUser(true).getObjId());
		shoppingCartService.removeAllByOrgInfo(param);
		model.put(Constants.JSON_RESULT, "操作成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
