package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfoAudit;
import com.gpcsoft.epp.contract.service.ContractPaymentApplyInfoAuditService;

/**
  * @gpcsoft.view value="contractPaymentApplyInfoAuditFormView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoAuditForm.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoAuditTreeFormView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoAuditTreeForm.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoAuditListView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoAuditList.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoAuditDetailView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoAuditDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractPaymentApplyInfoAudit.class})
@RequestMapping("/ContractPaymentApplyInfoAuditController.do")//页面请求路径,可修改
public class ContractPaymentApplyInfoAuditController extends AnnotationMultiController<ContractPaymentApplyInfoAudit> {

	@Autowired(required=true) @Qualifier("contractPaymentApplyInfoAuditServiceImpl")
	private ContractPaymentApplyInfoAuditService contractPaymentApplyInfoAuditService;

}
