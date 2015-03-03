package com.gpcsoft.agreement.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.service.OrderItemService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="orderItemFormView"
  *  url="view/agreement/order/orderItemForm.jsp"
  * @gpcsoft.view value="orderItemTreeFormView"
  *  url="view/agreement/order/orderItemTreeForm.jsp"
  * @gpcsoft.view value="orderItemListView"
  *  url="view/agreement/order/orderItemList.jsp"
  * @gpcsoft.view value="orderItemDetailView"
  *  url="view/agreement/order/orderItemDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrderItem.class})
@RequestMapping("/OrderItemController.do")//页面请求路径,可修改
public class OrderItemController extends AnnotationMultiController<OrderItem> {

	@Autowired(required=true) @Qualifier("orderItemServiceImpl")
	private OrderItemService orderItemService;
	

	/** 
	 * Description :  改变订单的一个商品的数量、金额
	 * Create Date: 2010-4-12下午01:55:36 by liangxj  Modified Date: 2010-4-12下午01:55:36 by liangxj
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateGoodsChangeQtyAndMoney")   
	public ModelAndView updateGoodsChangeQtyAndMoney(HttpServletRequest request, OrderItem orderItem) throws Exception {
		Map<String, String> model = new HashMap<String, String>();   
		
		orderItemService.updateGoodsChangeQtyAndMoney(orderItem);
		model.put(Constants.JSON_RESULT, "修改成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  查询订单明细和配件信息
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrderItemGoodsOption")   
	public ModelAndView getOrderItemGoodsOption(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		//paramsMap这个是查询条件
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("protaskId", request.getParameter("protaskId"));
		
		List<OrderItem> list = orderItemService.getOrderItemGoodsOption(paramsMap);
		
		model.put("rows", list);     
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
