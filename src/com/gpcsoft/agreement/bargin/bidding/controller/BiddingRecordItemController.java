package com.gpcsoft.agreement.bargin.bidding.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingRecordItem.class})
@RequestMapping("/BiddingRecordItemController.do")//页面请求路径,可修改
public class BiddingRecordItemController extends AnnotationMultiController<BiddingRecordItem> 
{

}
