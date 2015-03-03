package com.gpcsoft.epp.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.contract.domain.ContractAcceptanceAttachment;
import com.gpcsoft.epp.contract.service.ContractAcceptanceAttachmentService;

/**
  * @gpcsoft.view value="contractAcceptanceAttachmentFormView"
  *  url="view/es/planform/contract/contractAcceptanceAttachmentForm.jsp"
  * @gpcsoft.view value="contractAcceptanceAttachmentTreeFormView"
  *  url="view/es/planform/contract/contractAcceptanceAttachmentTreeForm.jsp"
  * @gpcsoft.view value="contractAcceptanceAttachmentListView"
  *  url="view/es/planform/contract/contractAcceptanceAttachmentList.jsp"
  * @gpcsoft.view value="contractAcceptanceAttachmentDetailView"
  *  url="view/es/planform/contract/contractAcceptanceAttachmentDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ContractAcceptanceAttachment.class})
@RequestMapping("/ContractAcceptanceAttachmentController.do")//页面请求路径,可修改
public class ContractAcceptanceAttachmentController extends AnnotationMultiController<ContractAcceptanceAttachment> {

	@Autowired(required=true) @Qualifier("contractAcceptanceAttachmentServiceImpl")
	private ContractAcceptanceAttachmentService contractAcceptanceAttachmentService;

}
