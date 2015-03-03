package com.gpcsoft.epp.requirement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.requirement.domain.PurReqAttachment;
import com.gpcsoft.epp.requirement.service.PurReqAttachmentService;

/**
  * @gpcsoft.view value="purReqAttachmentFormView"
  *  url="view/es/planform/requirement/purReqAttachmentForm.jsp"
  * @gpcsoft.view value="purReqAttachmentTreeFormView"
  *  url="view/es/planform/requirement/purReqAttachmentTreeForm.jsp"
  * @gpcsoft.view value="purReqAttachmentListView"
  *  url="view/es/planform/requirement/purReqAttachmentList.jsp"
  * @gpcsoft.view value="purReqAttachmentDetailView"
  *  url="view/es/planform/requirement/purReqAttachmentDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurReqAttachment.class})
@RequestMapping("/PurReqAttachmentController.do")//页面请求路径,可修改
public class PurReqAttachmentController extends AnnotationMultiController<PurReqAttachment> {

	@Autowired(required=true) @Qualifier("purReqAttachmentServiceImpl")
	private PurReqAttachmentService purReqAttachmentService;

}
