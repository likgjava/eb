package com.gpcsoft.epp.eval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.service.CongFactorResponseService;

/**
  * @gpcsoft.view value="congFactorResponseFormView"
  *  url="view/es/planform/eval/congFactorResponseForm.jsp"
  * @gpcsoft.view value="congFactorResponseTreeFormView"
  *  url="view/es/planform/eval/congFactorResponseTreeForm.jsp"
  * @gpcsoft.view value="congFactorResponseListView"
  *  url="view/es/planform/eval/congFactorResponseList.jsp"
  * @gpcsoft.view value="congFactorResponseDetailView"
  *  url="view/es/planform/eval/congFactorResponseDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CongFactorResponse.class})
@RequestMapping("/CongFactorResponseController.do")//页面请求路径,可修改
public class CongFactorResponseController extends AnnotationMultiController<CongFactorResponse> {

	@Autowired(required=true) @Qualifier("congFactorResponseServiceImpl")
	private CongFactorResponseService congFactorResponseService;

}
