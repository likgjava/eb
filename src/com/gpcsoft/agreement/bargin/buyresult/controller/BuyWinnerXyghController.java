package com.gpcsoft.agreement.bargin.buyresult.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.agreement.bargin.buyresult.service.BuyWinnerServiceXygh;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/BuyWinnerXyghController.do")//页面请求路径,可修改
public class BuyWinnerXyghController extends AnnotationMultiController {
	
	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("buyWinnerServiceXyghImpl")
	private BuyWinnerServiceXygh buyWinnerServiceXyghImpl;
	
	
}
