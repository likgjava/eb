package com.gpcsoft.goods.goodsprice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;
import com.gpcsoft.goods.goodsprice.service.GoodsOptFitPriceService;

/**
  * @gpcsoft.view value="goodsOptFitPriceFormView"
  *  url="view/agreement/GoodsOptFitPrice/goodsOptFitPriceForm.jsp"
  * @gpcsoft.view value="goodsOptFitPriceTreeFormView"
  *  url="view/agreement/GoodsOptFitPrice/goodsOptFitPriceTreeForm.jsp"
  * @gpcsoft.view value="goodsOptFitPriceListView"
  *  url="view/agreement/GoodsOptFitPrice/goodsOptFitPriceList.jsp"
  * @gpcsoft.view value="goodsOptFitPriceDetailView"
  *  url="view/agreement/GoodsOptFitPrice/goodsOptFitPriceDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsOptFitPrice.class})
@RequestMapping("/GoodsOptFitPriceController.do")//页面请求路径,可修改
public class GoodsOptFitPriceController extends AnnotationMultiController<GoodsOptFitPrice> {
	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("goodsOptFitPriceServiceImpl")
	private GoodsOptFitPriceService goodsOptFitPriceService;

}
