package com.gpcsoft.agreement.cart.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsAccessories;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="goodsAccessoriesCartItemFormView"
  *  url="view//cart/goodsAccessoriesCartItemForm.jsp"
  * @gpcsoft.view value="goodsAccessoriesCartItemTreeFormView"
  *  url="view//cart/goodsAccessoriesCartItemTreeForm.jsp"
  * @gpcsoft.view value="goodsAccessoriesCartItemListView"
  *  url="view//cart/goodsAccessoriesCartItemList.jsp"
  * @gpcsoft.view value="goodsAccessoriesCartItemDetailView"
  *  url="view//cart/goodsAccessoriesCartItemDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ShoppingCartGoodsAccessories.class})
@RequestMapping("/ShoppingCartGoodsAccessoriesController.do")//页面请求路径,可修改
public class ShoppingCartGoodsAccessoriesController extends AnnotationMultiController<ShoppingCartGoodsAccessories> {

}
