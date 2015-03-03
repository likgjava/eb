package com.gpcsoft.goods.goodsprice.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;

/**
  * @gpcsoft.view value="goodsPriceProcessFormView"
  *  url="view/agreement/GoodsPriceProcess/goodsPriceProcessForm.jsp"
  * @gpcsoft.view value="goodsPriceProcessTreeFormView"
  *  url="view/agreement/GoodsPriceProcess/goodsPriceProcessTreeForm.jsp"
  * @gpcsoft.view value="goodsPriceProcessListView"
  *  url="view/agreement/GoodsPriceProcess/goodsPriceProcessList.jsp"
  * @gpcsoft.view value="goodsPriceProcessDetailView"
  *  url="view/agreement/GoodsPriceProcess/goodsPriceProcessDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsPriceProcess.class})
@RequestMapping("/GoodsPriceProcessController.do")//页面请求路径,可修改
public class GoodsPriceProcessController extends AnnotationMultiController<GoodsPriceProcess> {

}
