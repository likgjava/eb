package com.gpcsoft.agreement.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.agreement.order.service.OrderProtaskService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="orderProtaskFormView"
  *  url="view/agreement/order/orderProtaskForm.jsp"
  * @gpcsoft.view value="orderProtaskTreeFormView"
  *  url="view/agreement/order/orderProtaskTreeForm.jsp"
  * @gpcsoft.view value="orderProtaskListView"
  *  url="view/agreement/order/orderProtaskList.jsp"
  * @gpcsoft.view value="orderProtaskDetailView"
  *  url="view/agreement/order/orderProtaskDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrderProtask.class})
@RequestMapping("/OrderProtaskController.do")//页面请求路径,可修改
public class OrderProtaskController extends AnnotationMultiController<OrderProtask> {

	@Autowired(required=true) @Qualifier("orderProtaskServiceImpl")
	private OrderProtaskService orderProtaskService;
	
	/**
	 * Description :  保存
	 * Create Date: 2010-4-16上午11:00:14 by sunl  Modified Date: 2010-4-16上午11:00:14 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveOrderProtask")
	public ModelAndView saveOrderProtask(String orderProtaskStr) throws Exception {	
		OrderProtask orderProtask=JsonUtils.json2Bean(orderProtaskStr, OrderProtask.class);
		Map<String, Object> model = new HashMap<String, Object>();
		
		orderProtaskService.save(orderProtask);
		
		model.put("orderProtask", orderProtask);//输出绑定的结果
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/**
	 * Description :  删除
	 * Create Date: 2010-4-16上午11:00:14 by sunl  Modified Date: 2010-4-16上午11:00:14 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=removeOrderProtaskByOrder")
	public ModelAndView removeOrderProtaskByOrder(String orderItemId) throws Exception {	
		Map<String, Object> model = new HashMap<String, Object>();
		
		orderProtaskService.remove(new String[]{"orderItem.objId"}, new String[]{orderItemId}, OrderProtask.class);
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
}
