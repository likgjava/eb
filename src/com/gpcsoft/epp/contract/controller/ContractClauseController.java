package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractClause;
import com.gpcsoft.epp.contract.service.ContractClauseService;

/**
  * @gpcsoft.view value="contractClauseFormView"
  *  url="view/es/planform/contract/contractClauseForm.jsp"
  * @gpcsoft.view value="contractClauseTreeFormView"
  *  url="view/es/planform/contract/contractClauseTreeForm.jsp"
  * @gpcsoft.view value="contractClauseListView"
  *  url="view/es/planform/contract/contractClauseList.jsp"
  * @gpcsoft.view value="contractClauseDetailView"
  *  url="view/es/planform/contract/contractClauseDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractClause.class})
@RequestMapping("/ContractClauseController.do")//页面请求路径,可修改
public class ContractClauseController extends AnnotationMultiController<ContractClause> {

	@Autowired(required=true) @Qualifier("contractClauseServiceImpl")
	private ContractClauseService contractClauseService;

}
