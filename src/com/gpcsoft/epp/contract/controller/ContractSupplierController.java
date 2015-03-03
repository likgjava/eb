package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.service.ContractSupplierService;

/**
  * @gpcsoft.view value="contractSupplierFormView"
  *  url="view//planform/contract/contractSupplierForm.jsp"
  * @gpcsoft.view value="contractSupplierTreeFormView"
  *  url="view//planform/contract/contractSupplierTreeForm.jsp"
  * @gpcsoft.view value="contractSupplierListView"
  *  url="view//planform/contract/contractSupplierList.jsp"
  * @gpcsoft.view value="contractSupplierDetailView"
  *  url="view//planform/contract/contractSupplierDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractSupplier.class})
@RequestMapping("/ContractSupplierController.do")//页面请求路径,可修改
public class ContractSupplierController extends AnnotationMultiController<ContractSupplier> {

	@Autowired(required=true) @Qualifier("contractSupplierServiceImpl")
	private ContractSupplierService contractSupplierService;

}
