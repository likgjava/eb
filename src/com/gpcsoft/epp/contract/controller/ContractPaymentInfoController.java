package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractPaymentInfo;
import com.gpcsoft.epp.contract.service.ContractPaymentInfoService;

/**
  * @gpcsoft.view value="contractPaymentInfoFormView"
  *  url="view/es/planform/contract/contractPaymentInfoForm.jsp"
  * @gpcsoft.view value="contractPaymentInfoTreeFormView"
  *  url="view/es/planform/contract/contractPaymentInfoTreeForm.jsp"
  * @gpcsoft.view value="contractPaymentInfoListView"
  *  url="view/es/planform/contract/contractPaymentInfoList.jsp"
  * @gpcsoft.view value="contractPaymentInfoDetailView"
  *  url="view/es/planform/contract/contractPaymentInfoDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractPaymentInfo.class})
@RequestMapping("/ContractPaymentInfoController.do")//页面请求路径,可修改
public class ContractPaymentInfoController extends AnnotationMultiController<ContractPaymentInfo> {

	@Autowired(required=true) @Qualifier("contractPaymentInfoServiceImpl")
	private ContractPaymentInfoService contractPaymentInfoService;

}
