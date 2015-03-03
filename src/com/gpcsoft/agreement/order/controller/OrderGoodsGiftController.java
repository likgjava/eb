package com.gpcsoft.agreement.order.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.order.domain.OrderGoodsGift;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrderGoodsGift.class})
@RequestMapping("/OrderGoodsGiftController.do")//页面请求路径,可修改
public class OrderGoodsGiftController extends AnnotationMultiController<OrderGoodsGift> {

//	@Autowired(required=true) @Qualifier("orderGoodsGiftServiceImpl")
//	private OrderGoodsGiftController orderGoodsGiftService;

}
