package com.gpcsoft.epp.requirement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.requirement.domain.PurRequirement;
import com.gpcsoft.epp.requirement.service.PurRequirementService;

/**
  * @gpcsoft.view value="purRequirementFormView"
  *  url="view/es/planform/requirement/purRequirementForm.jsp"
  * @gpcsoft.view value="purRequirementTreeFormView"
  *  url="view/es/planform/requirement/purRequirementTreeForm.jsp"
  * @gpcsoft.view value="purRequirementListView"
  *  url="view/es/planform/requirement/purRequirementList.jsp"
  * @gpcsoft.view value="purRequirementDetailView"
  *  url="view/es/planform/requirement/purRequirementDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurRequirement.class})
@RequestMapping("/PurRequirementController.do")//页面请求路径,可修改
public class PurRequirementController extends AnnotationMultiController<PurRequirement> {

	@Autowired(required=true) @Qualifier("purRequirementServiceImpl")
	private PurRequirementService purRequirementService;
	
}
