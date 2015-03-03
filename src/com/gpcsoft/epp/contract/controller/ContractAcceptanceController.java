package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractAcceptance;
import com.gpcsoft.epp.contract.service.ContractAcceptanceService;

/**
  * @gpcsoft.view value="contractAcceptanceFormView"
  *  url="view/es/planform/contract/contractAcceptanceForm.jsp"
  * @gpcsoft.view value="contractAcceptanceTreeFormView"
  *  url="view/es/planform/contract/contractAcceptanceTreeForm.jsp"
  * @gpcsoft.view value="contractAcceptanceListView"
  *  url="view/es/planform/contract/contractAcceptanceList.jsp"
  * @gpcsoft.view value="contractAcceptanceDetailView"
  *  url="view/es/planform/contract/contractAcceptanceDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractAcceptance.class})
@RequestMapping("/ContractAcceptanceController.do")//页面请求路径,可修改
public class ContractAcceptanceController extends AnnotationMultiController<ContractAcceptance> {

	@Autowired(required=true) @Qualifier("contractAcceptanceServiceImpl")
	private ContractAcceptanceService contractAcceptanceService;

}
