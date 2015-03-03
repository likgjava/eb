package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractFundDetail;
import com.gpcsoft.epp.contract.service.ContractFundDetailService;

/**
  * @gpcsoft.view value="contractFundDetailFormView"
  *  url="view/es/planform/contract/contractFundDetailForm.jsp"
  * @gpcsoft.view value="contractFundDetailTreeFormView"
  *  url="view/es/planform/contract/contractFundDetailTreeForm.jsp"
  * @gpcsoft.view value="contractFundDetailListView"
  *  url="view/es/planform/contract/contractFundDetailList.jsp"
  * @gpcsoft.view value="contractFundDetailDetailView"
  *  url="view/es/planform/contract/contractFundDetailDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractFundDetail.class})
@RequestMapping("/ContractFundDetailController.do")//页面请求路径,可修改
public class ContractFundDetailController extends AnnotationMultiController<ContractFundDetail> {

	@Autowired(required=true) @Qualifier("contractFundDetailServiceImpl")
	private ContractFundDetailService contractFundDetailService;

}
