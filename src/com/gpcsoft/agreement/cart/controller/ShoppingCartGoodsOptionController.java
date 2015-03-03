package com.gpcsoft.agreement.cart.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsOption;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="shoppingCartGoodsOptionFormView"
  *  url="view/agreement/cart/shoppingCartGoodsOptionForm.jsp"
  * @gpcsoft.view value="shoppingCartGoodsOptionTreeFormView"
  *  url="view/agreement/cart/shoppingCartGoodsOptionTreeForm.jsp"
  * @gpcsoft.view value="shoppingCartGoodsOptionListView"
  *  url="view/agreement/cart/shoppingCartGoodsOptionList.jsp"
  * @gpcsoft.view value="shoppingCartGoodsOptionDetailView"
  *  url="view/agreement/cart/shoppingCartGoodsOptionDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ShoppingCartGoodsOption.class})
@RequestMapping("/ShoppingCartGoodsOptionController.do")//页面请求路径,可修改
public class ShoppingCartGoodsOptionController extends AnnotationMultiController<ShoppingCartGoodsOption> {


}
