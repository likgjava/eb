package com.gpcsoft.smallscale.pay.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.pay.domain.PayRecord;
import com.gpcsoft.smallscale.pay.service.PayRecordService;

/**
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PayRecord.class})
@RequestMapping("/PayRecordController.do")//页面请求路径,可修改
public class PayRecordController extends AnnotationMultiController<PayRecord> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("payRecordServiceImpl")
	private PayRecordService payRecordService;
	
}
