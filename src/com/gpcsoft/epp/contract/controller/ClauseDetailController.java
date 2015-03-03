package com.gpcsoft.epp.contract.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ClauseDetail;
import com.gpcsoft.epp.contract.service.ClauseDetailService;

/**
  * @gpcsoft.view value="clauseDetailFormView"
  *  url="view/es/planform/contract/clauseDetailForm.jsp"
  * @gpcsoft.view value="clauseDetailTreeFormView"
  *  url="view/es/planform/contract/clauseDetailTreeForm.jsp"
  * @gpcsoft.view value="clauseDetailListView"
  *  url="view/es/planform/contract/clauseDetailList.jsp"
  * @gpcsoft.view value="clauseDetailDetailView"
  *  url="view/es/planform/contract/clauseDetailDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ClauseDetail.class})
@RequestMapping("/ClauseDetailController.do")//页面请求路径,可修改
public class ClauseDetailController extends AnnotationMultiController<ClauseDetail> {

	@Autowired(required=true) @Qualifier("clauseDetailServiceImpl")
	private ClauseDetailService clauseDetailService;

}
