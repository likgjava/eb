package com.gpcsoft.epp.eval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.eval.domain.CongFactorEvalMethod;
import com.gpcsoft.epp.eval.service.CongFactorEvalMethodService;

/**
  * @gpcsoft.view value="congFactorEvalMethodFormView"
  *  url="view/es/planform/eval/congFactorEvalMethodForm.jsp"
  * @gpcsoft.view value="congFactorEvalMethodTreeFormView"
  *  url="view/es/planform/eval/congFactorEvalMethodTreeForm.jsp"
  * @gpcsoft.view value="congFactorEvalMethodListView"
  *  url="view/es/planform/eval/congFactorEvalMethodList.jsp"
  * @gpcsoft.view value="congFactorEvalMethodDetailView"
  *  url="view/es/planform/eval/congFactorEvalMethodDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CongFactorEvalMethod.class})
@RequestMapping("/CongFactorEvalMethodController.do")//页面请求路径,可修改
public class CongFactorEvalMethodController extends AnnotationMultiController<CongFactorEvalMethod> {

	@Autowired(required=true) @Qualifier("congFactorEvalMethodServiceImpl")
	private CongFactorEvalMethodService congFactorEvalMethodService;

}
