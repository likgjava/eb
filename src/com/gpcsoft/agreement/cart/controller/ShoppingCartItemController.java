package com.gpcsoft.agreement.cart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.service.ShoppingCartItemService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="cartItemsFormView"
  *  url="view/agreement/cart/cartItemsForm.jsp"
  * @gpcsoft.view value="cartItemsTreeFormView"
  *  url="view/agreement/cart/cartItemsTreeForm.jsp"
  * @gpcsoft.view value="cartItemsListView"
  *  url="view/agreement/cart/cartItemsList.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ShoppingCartItem.class})
@RequestMapping("/ShoppingCartItemController.do")//页面请求路径,可修改
public class ShoppingCartItemController extends AnnotationMultiController<ShoppingCartItem> {
	
	@Autowired(required=true) @Qualifier("shoppingCartItemServiceImpl")
	private ShoppingCartItemService shoppingCartItemService;
	
	/** 
	 * Description :  添加商品到购物车 ShopingCartItem
	 * Create Date: 2010-4-22下午06:05:09 by wangsw  Modified Date: 2010-4-22下午06:05:09 by yucy
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAppendGoodsToShoppingCart")   
	public ModelAndView saveAppendGoodsToShoppingCart(String shoppingCartItemStr,HttpSession session) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cartGoodsOptions", ShoppingCartGoodsOption.class);
		map.put("cartGoodsGifts", ShoppingCartGoodsGift.class);
		map.put("cartGoodsAccessories", ShoppingCartGoodsAccessories.class);
		ShoppingCartItem shoppingCartItem=JsonUtils.json2Bean(shoppingCartItemStr, ShoppingCartItem.class, map);
		Map<String, Object> model = new HashMap<String, Object>();
		String protaskItemObjId = (String)session.getAttribute("protaskItemObjId");
		
		shoppingCartItemService.saveAppendGoodsToShoppingCart(shoppingCartItem,protaskItemObjId);
		
		//如果购物车内的任务书id不为空，则清空session
		if(shoppingCartItem.getProtaskItem() != null && shoppingCartItem.getProtaskItem().getObjId() != null)
			session.removeAttribute("protaskItemObjId");
		
		model.put(Constants.JSON_RESULT, "操作成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  改变购物车的一个商品的数量、金额
	 * Create Date: 2010-4-12下午01:55:36 by wangsw  Modified Date: 2010-4-12下午01:55:36 by wangsw
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateGoodsChangeQtyAndMoney")   
	public ModelAndView updateGoodsChangeQtyAndMoney(HttpServletRequest request, String cartItemStr) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		//cartItem.getSupplier().setObjId(request.getParameter("supplier.objId"));
		ShoppingCartItem cartItem = JsonUtils.json2Bean(cartItemStr, ShoppingCartItem.class);
		shoppingCartItemService.updateGoodsChangeQtyAndMoney(cartItem);
		model.put(Constants.JSON_RESULT, "修改成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
}
