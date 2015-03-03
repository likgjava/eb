package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.WitnessParty;
import com.gpcsoft.epp.contract.service.WitnessPartyService;

/**
  * @gpcsoft.view value="witnessPartyFormView"
  *  url="view/es/planform/contract/witnessPartyForm.jsp"
  * @gpcsoft.view value="witnessPartyTreeFormView"
  *  url="view/es/planform/contract/witnessPartyTreeForm.jsp"
  * @gpcsoft.view value="witnessPartyListView"
  *  url="view/es/planform/contract/witnessPartyList.jsp"
  * @gpcsoft.view value="witnessPartyDetailView"
  *  url="view/es/planform/contract/witnessPartyDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={WitnessParty.class})
@RequestMapping("/WitnessPartyController.do")//页面请求路径,可修改
public class WitnessPartyController extends AnnotationMultiController<WitnessParty> {

	@Autowired(required=true) @Qualifier("witnessPartyServiceImpl")
	private WitnessPartyService witnessPartyService;

}
