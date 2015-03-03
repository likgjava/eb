package com.gpcsoft.epp.bid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BidReceipt;
import com.gpcsoft.epp.bid.service.BidReceiptService;

/**
  * @gpcsoft.view value="bidReceiptFormView"
  *  url="view/epp/bid/bidReceiptForm.jsp"
  * @gpcsoft.view value="bidReceiptTreeFormView"
  *  url="view/epp/bid/bidReceiptTreeForm.jsp"
  * @gpcsoft.view value="bidReceiptListView"
  *  url="view/epp/bid/bidReceiptList.jsp"
  * @gpcsoft.view value="bidReceiptDetailView"
  *  url="view/epp/bid/bidReceiptDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BidReceipt.class})
@RequestMapping("/BidReceiptController.do")//页面请求路径,可修改
public class BidReceiptController extends AnnotationMultiController<BidReceipt> {

	@Autowired(required=true) @Qualifier("bidReceiptServiceImpl")
	private BidReceiptService bidReceiptService;

}
