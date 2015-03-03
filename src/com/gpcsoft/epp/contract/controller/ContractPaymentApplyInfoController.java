package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.service.ContractPaymentApplyInfoService;

/**
  * @gpcsoft.view value="contractPaymentApplyInfoFormView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoForm.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoTreeFormView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoTreeForm.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoListView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoList.jsp"
  * @gpcsoft.view value="contractPaymentApplyInfoDetailView"
  *  url="view/es/planform/contract/contractPaymentApplyInfoDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractPaymentApplyInfo.class})
@RequestMapping("/ContractPaymentApplyInfoController.do")//页面请求路径,可修改
public class ContractPaymentApplyInfoController extends AnnotationMultiController<ContractPaymentApplyInfo> {

	@Autowired(required=true) @Qualifier("contractPaymentApplyInfoServiceImpl")
	private ContractPaymentApplyInfoService contractPaymentApplyInfoService;

}
