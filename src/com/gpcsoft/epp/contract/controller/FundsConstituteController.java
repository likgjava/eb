package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.FundsConstitute;
import com.gpcsoft.epp.contract.service.FundsConstituteService;

/**
  * @gpcsoft.view value="fundsConstituteFormView"
  *  url="view/es/planform/contract/fundsConstituteForm.jsp"
  * @gpcsoft.view value="fundsConstituteTreeFormView"
  *  url="view/es/planform/contract/fundsConstituteTreeForm.jsp"
  * @gpcsoft.view value="fundsConstituteListView"
  *  url="view/es/planform/contract/fundsConstituteList.jsp"
  * @gpcsoft.view value="fundsConstituteDetailView"
  *  url="view/es/planform/contract/fundsConstituteDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={FundsConstitute.class})
@RequestMapping("/FundsConstituteController.do")//页面请求路径,可修改
public class FundsConstituteController extends AnnotationMultiController<FundsConstitute> {

	@Autowired(required=true) @Qualifier("fundsConstituteServiceImpl")
	private FundsConstituteService fundsConstituteService;

}
