package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractOperInfo;
import com.gpcsoft.epp.contract.service.ContractOperInfoService;

/**
  * @gpcsoft.view value="contractOperInfoFormView"
  *  url="view/es/planform/contract/contractOperInfoForm.jsp"
  * @gpcsoft.view value="contractOperInfoTreeFormView"
  *  url="view/es/planform/contract/contractOperInfoTreeForm.jsp"
  * @gpcsoft.view value="contractOperInfoListView"
  *  url="view/es/planform/contract/contractOperInfoList.jsp"
  * @gpcsoft.view value="contractOperInfoDetailView"
  *  url="view/es/planform/contract/contractOperInfoDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractOperInfo.class})
@RequestMapping("/ContractOperInfoController.do")//页面请求路径,可修改
public class ContractOperInfoController extends AnnotationMultiController<ContractOperInfo> {

	@Autowired(required=true) @Qualifier("contractOperInfoServiceImpl")
	private ContractOperInfoService contractOperInfoService;

}
