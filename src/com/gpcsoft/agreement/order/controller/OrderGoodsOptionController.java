package com.gpcsoft.agreement.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.agreement.order.service.OrderGoodsOptionService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="orderGoodsOptionFormView"
  *  url="view/agreement/order/orderGoodsOptionForm.jsp"
  * @gpcsoft.view value="orderGoodsOptionTreeFormView"
  *  url="view/agreement/order/orderGoodsOptionTreeForm.jsp"
  * @gpcsoft.view value="orderGoodsOptionListView"
  *  url="view/agreement/order/orderGoodsOptionList.jsp"
  * @gpcsoft.view value="orderGoodsOptionDetailView"
  *  url="view/agreement/order/orderGoodsOptionDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrderGoodsOption.class})
@RequestMapping("/OrderGoodsOptionController.do")//页面请求路径,可修改
public class OrderGoodsOptionController extends AnnotationMultiController<OrderGoodsOption> {

	@Autowired(required=true) @Qualifier("orderGoodsOptionServiceImpl")
	private OrderGoodsOptionService orderGoodsOptionService;

	/** 
	 * Description :  改变选配的一个商品的数量、金额
	 * Create Date: 2010-4-12下午01:55:36 by liangxj  Modified Date: 2010-4-12下午01:55:36 by liangxj
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateGoodsChangeQtyAndMoney")   
	public ModelAndView updateGoodsChangeQtyAndMoney(HttpServletRequest request, OrderGoodsOption option) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		orderGoodsOptionService.updateGoodsChangeQtyAndMoney(option);
		model.put(Constants.JSON_RESULT, "修改成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
