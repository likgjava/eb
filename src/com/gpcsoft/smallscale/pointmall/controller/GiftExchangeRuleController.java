package com.gpcsoft.smallscale.pointmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.smallscale.pointmall.service.GiftExchangeRuleService;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;

/**
  * @gpcsoft.view value="giftExchangeRuleFormView"
  *  url="view/smallscale/pointmall/giftExchangeRuleForm.jsp"
  * @gpcsoft.view value="giftExchangeRuleTreeFormView"
  *  url="view/smallscale/pointmall/giftExchangeRuleTreeForm.jsp"
  * @gpcsoft.view value="giftExchangeRuleListView"
  *  url="view/smallscale/pointmall/giftExchangeRuleList.jsp"
  * @gpcsoft.view value="giftExchangeRuleDetailView"
  *  url="view/smallscale/pointmall/giftExchangeRuleDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GiftExchangeRule.class})
@RequestMapping("/GiftExchangeRuleController.do")//页面请求路径,可修改
public class GiftExchangeRuleController extends AnnotationMultiController<GiftExchangeRule> {

	@Autowired(required=true) @Qualifier("giftExchangeRuleServiceImpl")
	private GiftExchangeRuleService giftExchangeRuleService;

	public void setGiftExchangeRuleService(GiftExchangeRuleService giftExchangeRuleService) {
		this.giftExchangeRuleService = giftExchangeRuleService;
	}

	public GiftExchangeRuleService getGiftExchangeRuleService() {
		return giftExchangeRuleService;
	}

}
