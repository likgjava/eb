package com.gpcsoft.agreement.cart.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.cart.domain.ShoppingCartGoodsGift;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ShoppingCartGoodsGift.class})
@RequestMapping("/ShoppingCartGoodsGiftController.do")//页面请求路径,可修改
public class ShoppingCartGoodsGiftController extends AnnotationMultiController<ShoppingCartGoodsGift> {

//	@Autowired(required=true) @Qualifier("shoppingCartGoodsGiftServiceImpl")
//	private ShoppingCartGoodsGiftService shoppingCartGoodsGiftService;
	
}
