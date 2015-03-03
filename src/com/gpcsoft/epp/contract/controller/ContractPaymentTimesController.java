package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractPaymentTimes;
import com.gpcsoft.epp.contract.service.ContractPaymentTimesService;

/**
  * @gpcsoft.view value="contractPaymentTimesFormView"
  *  url="view/es/planform/contract/contractPaymentTimesForm.jsp"
  * @gpcsoft.view value="contractPaymentTimesTreeFormView"
  *  url="view/es/planform/contract/contractPaymentTimesTreeForm.jsp"
  * @gpcsoft.view value="contractPaymentTimesListView"
  *  url="view/es/planform/contract/contractPaymentTimesList.jsp"
  * @gpcsoft.view value="contractPaymentTimesDetailView"
  *  url="view/es/planform/contract/contractPaymentTimesDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractPaymentTimes.class})
@RequestMapping("/ContractPaymentTimesController.do")//页面请求路径,可修改
public class ContractPaymentTimesController extends AnnotationMultiController<ContractPaymentTimes> {

	@Autowired(required=true) @Qualifier("contractPaymentTimesServiceImpl")
	private ContractPaymentTimesService contractPaymentTimesService;

}
