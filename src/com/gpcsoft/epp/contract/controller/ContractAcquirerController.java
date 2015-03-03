package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.service.ContractAcquirerService;

/**
  * @gpcsoft.view value="contractAcquirerFormView"
  *  url="view/es/planform/contract/contractAcquirerForm.jsp"
  * @gpcsoft.view value="contractAcquirerTreeFormView"
  *  url="view/es/planform/contract/contractAcquirerTreeForm.jsp"
  * @gpcsoft.view value="contractAcquirerListView"
  *  url="view/es/planform/contract/contractAcquirerList.jsp"
  * @gpcsoft.view value="contractAcquirerDetailView"
  *  url="view/es/planform/contract/contractAcquirerDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractAcquirer.class})
@RequestMapping("/ContractAcquirerController.do")//页面请求路径,可修改
public class ContractAcquirerController extends AnnotationMultiController<ContractAcquirer> {

	@Autowired(required=true) @Qualifier("contractAcquirerServiceImpl")
	private ContractAcquirerService contractAcquirerService;

}
