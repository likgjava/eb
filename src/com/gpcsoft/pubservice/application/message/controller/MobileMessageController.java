package com.gpcsoft.pubservice.application.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.message.domain.MobileMessage;
import com.gpcsoft.pubservice.application.message.service.MobileMessageService;

/**
  * @gpcsoft.view value="mobileMessageFormView"
  *  url="view/pubservice/application/message/mobileMessageForm.jsp"
  * @gpcsoft.view value="mobileMessageTreeFormView"
  *  url="view/pubservice/application/message/mobileMessageTreeForm.jsp"
  * @gpcsoft.view value="mobileMessageListView"
  *  url="view/pubservice/application/message/mobileMessageList.jsp"
  * @gpcsoft.view value="mobileMessageDetailView"
  *  url="view/pubservice/application/message/mobileMessageDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={MobileMessage.class})
@RequestMapping("/MobileMessageController.do")//页面请求路径,可修改
public class MobileMessageController extends AnnotationMultiController<MobileMessage> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("mobileMessageServiceImpl")
	private MobileMessageService mobileMessageService;

}
