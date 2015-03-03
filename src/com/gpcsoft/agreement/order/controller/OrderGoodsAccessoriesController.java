package com.gpcsoft.agreement.order.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.order.domain.OrderGoodsAccessories;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="orderGoodsAccessoriesFormView"
  *  url="view//order/orderGoodsAccessoriesForm.jsp"
  * @gpcsoft.view value="orderGoodsAccessoriesTreeFormView"
  *  url="view//order/orderGoodsAccessoriesTreeForm.jsp"
  * @gpcsoft.view value="orderGoodsAccessoriesListView"
  *  url="view//order/orderGoodsAccessoriesList.jsp"
  * @gpcsoft.view value="orderGoodsAccessoriesDetailView"
  *  url="view//order/orderGoodsAccessoriesDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrderGoodsAccessories.class})
@RequestMapping("/OrderGoodsAccessoriesController.do")//页面请求路径,可修改
public class OrderGoodsAccessoriesController extends AnnotationMultiController<OrderGoodsAccessories> {

}
