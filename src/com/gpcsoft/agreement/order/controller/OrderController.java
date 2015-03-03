 package com.gpcsoft.agreement.order.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.service.OrderService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="orderBFormView"
  *  url="view/agreement/order/order/order_purchaser_conform.jsp"
  *  
  * @gpcsoft.view value="orderBDetailView"
  *  url="view/agreement/order/order/order_purchaser_detail.jsp"
  * @gpcsoft.view value="orderSFormView"
  *  url="view/agreement/order/order/order_supplier_confirm.jsp"
  *  
  * @gpcsoft.view value="orderSDetailView"
  *  url="view/agreement/order/order/order_supplier_detail.jsp"
  *  
  * @gpcsoft.view value="divDetailView"
  *  url="view/agreement/order/order/order_detail_div.jsp"
  *  
  * @gpcsoft.view value="monitorDetailView"
  *  url="view/agreement/order/order/order_monitor_detail.jsp"
  *  
  * @gpcsoft.view value="orderListView"
  *  url="view/agreement/order/orderList.jsp"
  *  
  * @gpcsoft.view value="orderSupplierListView"
  *  url="view/agreement/order/order/order_supplier_list.jsp"
  *  
  * @gpcsoft.view value="orderBuyerListView"
  *  url="view/agreement/order/order/order_purchaser_list.jsp"
  *  
  * @gpcsoft.view value="orderBuyerListXEJYView"
  *  url="view/agreement/order/order/s_order_purchaser_list.jsp"
  *  
  * @gpcsoft.view value="orderSupplierListXEJYView"
  *  url="view/agreement/order/order/s_order_supplier_list.jsp"
  *  
  *  @gpcsoft.view value="toOrderPayView"
  *  url="view/srplatform/chinabank/send_order.jsp"
  *  
  *  @gpcsoft.view value="orgOrderMonitorListView"
  *  url="view/agreement/order/order/org_order_monitor_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Order.class})
@RequestMapping("/OrderController.do")//页面请求路径,可修改
public class OrderController extends AnnotationMultiController<Order> {

	@Autowired(required=true) @Qualifier("orderServiceImpl")
	private OrderService orderService;
	
	@Autowired(required=true)
	@Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	/** 
	 * Description : 跳转到订单支付页面
	 * Create Date: 2011-6-15下午11:35:14 by yucy  Modified Date: 2011-6-15下午11:35:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrderPayView")
    public ModelAndView toOrderPayView(HttpServletRequest request) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//订单号
		model.put("v_oid", request.getParameter("v_oid"));
		
		//订单id
		String orderId = request.getParameter("orderId");
		model.put( "order" , orderService.get(orderId) );
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orderId", orderId);
		model.put("orderItemList", orderService.getOrderGoodsOption(paramsMap));
		
		//订单金额
		model.put("v_amount", request.getParameter("v_amount"));
		
		//商户返回调用业务方法
		model.put("v_back_req_method", "orderPayResult");
		
		return new ModelAndView("toOrderPayView",model);
    }
	
	/** 
	 * Description :  查询订单明细和配件信息
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrderGoodsOption")   
	public ModelAndView getOrderGoodsOption(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		//paramsMap这个是查询条件
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orderId", request.getParameter("orderId"));
		
		List<OrderItem> list = orderService.getOrderGoodsOption(paramsMap);
		
		model.put("rows", list);     
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :跳转订单管理（供应商）
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrderSupplierList")   
	public ModelAndView toOrderSupplierList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		ModelAndView mv = null;
		if("XEJY".equals(request.getParameter("appType"))){
			mv = new ModelAndView("orderSupplierListXEJYView",model);
		}else {
			mv = new ModelAndView("orderSupplierListView", model);
		}
		return mv;
    }
	
	/** 
	 * Description :跳转订单管理（采购人）
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrderBuyerList")   
	public ModelAndView toOrderBuyerList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		ModelAndView mv = null;
		if("XEJY".equals(request.getParameter("appType"))){
			mv = new ModelAndView("orderBuyerListXEJYView",model);
		}else {
			mv = new ModelAndView("orderBuyerListView",model);
		}
		return mv;
    }
	
	/** 
	 * Description :  跳转到form页面
	 * Create Date: 2010-5-21下午02:46:50 by liangxj  Modified Date: 2010-5-21下午02:46:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrderForm")   
	public ModelAndView toOrderForm(HttpServletRequest request,String objId,String navigate,String type) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Order order = null;
		if(null!=objId&&!"".equals(objId)){
			order = orderService.get(objId);
		}else {
			order = new Order();
		}
		model.put("order", order);
		model.put("type", type);
		
		//准备列表数据
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orderId", objId);
		model.put("orderItemList", orderService.getOrderGoodsOption(paramsMap));
		
		model.put("isNogoodsPrice", isNogoodsPrice(order.getOrderItems()));//是否有单价
		
		if("bdetail".equals(navigate)){
				return new ModelAndView("orderBDetailView", model);  //采购人修改订单
			
		}else if("sdetail".equals(navigate)){
				return new ModelAndView("orderSDetailView", model);  //供应商修改订单
		}else if("sform".equals(navigate)){
				return new ModelAndView("orderSFormView", model);  //供应商提交订单
		}else if("divDetail".equals(navigate)){
			return new ModelAndView("divDetailView", model); //div弹出层 by yucy
		}else if("monitorDetail".equals(navigate)){
			return new ModelAndView("monitorDetailView", model); //订单详细休息(监控)
		}
		else{
			return new ModelAndView("orderBFormView", model);  //采购人提交订单
		}
    }
	
	/** 
	 * Description :  保存订单的合同id
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveOrderContract")   
	public ModelAndView saveOrderContract(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		//paramsMap这个是查询条件
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orderIds", request.getParameter("orderIds"));
		paramsMap.put("contractId", request.getParameter("contractId"));
		
		orderService.saveOrderContract(paramsMap);

		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/**
	 * Description :  保存
	 * Create Date: 2010-4-16上午11:00:14 by sunl  Modified Date: 2010-4-16上午11:00:14 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveOrder")
	public ModelAndView saveOrder(HttpServletRequest request,String orderStr) throws Exception {
		Order order=JsonUtils.json2Bean(orderStr ,Order.class);
		
		order.setBuyerOption(StringUtils.ascii2Native(order.getBuyerOption()));
		order.setSupplierOption(StringUtils.ascii2Native(order.getSupplierOption()));

		String methodName = request.getParameter("methodName");
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("order", orderService.saveOrder(order,methodName));//输出绑定的结果
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/** 
	 * Description :  作废订单
	 * Create Date: 2010-11-25上午11:34:26 by yucy  Modified Date: 2010-11-25上午11:34:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=destroyOrder")
	public ModelAndView destroyOrder(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", request.getParameter("objId"));
		param.put("destroyType", request.getParameter("destroyType"));
		orderService.saveDestroyOrder(param);
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/**
	 * Description :  创建订单
	 * Create Date: 2010-4-16上午11:00:14 by liangxj  Modified Date: 2010-4-16上午11:00:14 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=createOrder")
	public ModelAndView createOrder(HttpServletRequest request,String carItem) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("carItemObjIds", request.getParameter("carItem"));
		param.put("createType", request.getParameter("createType"));
		Order order= orderService.createOrder(param);
		model.put("order", order);//输出绑定的结果
		//准备列表数据
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orderId", order.getObjId());
		model.put("orderItemList", orderService.getOrderGoodsOption(paramsMap));
		model.put("isNogoodsPrice", isNogoodsPrice(order.getOrderItems()));//是否有单价
		model.put("type", "edit_toSubmit");
		return new ModelAndView("orderBFormView", model);	
	}
	
	/**
	 * Description :  创建订单（Bidding）
	 * Create Date: 2010-4-16上午11:00:14 by yucy  Modified Date: 2010-4-16上午11:00:14 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=createOrderByBidding")
	public ModelAndView createOrderByBidding(HttpServletRequest request,String biddingRecordItem) throws Exception {
		String biddingRecordItemObjIds = request.getParameter("biddingRecordItem");
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("biddingRecordItemObjIds", biddingRecordItemObjIds);
		param.put("appType", request.getParameter("appType"));
		
		Order order = (Order)orderService.createOrderByBidding(param);
		model.put("order",order );//输出绑定的结果
		model.put("orderItemList", order.getOrderItems());//输出绑定的结果
		model.put("isNogoodsPrice", isNogoodsPrice(order.getOrderItems()));//是否有单价
		model.put("type", "edit_toSubmit");
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/** 
	 * Description :  返回是否有单价
	 * Create Date: 2010-12-18下午02:24:20 by yucy  Modified Date: 2010-12-18下午02:24:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isNogoodsPrice(Set<OrderItem> orderItems){
		boolean isNogoodsPrice = true;
		for (OrderItem orderItem:orderItems) {
			if(orderItem.getAgreePrice()==null&&orderItem.getGoodsPrice()==null) break;
			if((new BigDecimal(0).compareTo(orderItem.getAgreePrice())+new BigDecimal(0).compareTo(orderItem.getGoodsPrice()))<0){
				isNogoodsPrice = false;
			}
		}
		return isNogoodsPrice;
	}
	
	@SuppressWarnings("unchecked")
	@Override // by yucy
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		String tabsType = request.getParameter("tabsType");
		String orgType = request.getParameter("orgType");

		//供应商待确认
		if(null!=tabsType&&"toSure".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplier.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//待提交
		else if(null!=tabsType&&"toSubmit".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//待对方确认
		else if(null!=tabsType&&"toSupSure".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//待供应商调整
		else if(null!=tabsType&&"toSupAdjust".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));  //供应商确认状态 02
		}
		//已完成
		else if(null!=tabsType&&"toSupdone".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//待调整
		else if(null!=tabsType&&"toOppositeAdjust".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplierConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));  //供应商确认状态 02
		}
		//已完成
		else if(null!=tabsType&&"done".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplierConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		else{
			
		}
		return query;
	}
	
	/** 
	 * Description :  打印订单
	 * Create Date: 2010-12-13下午04:19:42 by liangxj  Modified Date: 2010-12-13下午04:19:42 by liangxj
	 * @param   orderTemp 订单模板 objId 订单id
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params={"method=printOrder"})
	public ModelAndView printOrder(HttpServletRequest request, String objId,String orderTemp) throws Exception
	{
	   Order order = (Order)this.orderService.get(objId);
	
	   Map<String,Order> templateMap = new HashMap<String,Order>();
	   templateMap.put("order", order);
	
	   Map<String,String> model = new HashMap<String,String>();
	   
	   //加载订单模板
	   String template = "order.ftl";
	   if(orderTemp != null)
		   template = orderTemp;
	    
	   model.put("content", this.freeMarkerService.getFreeMarkerContent(template, templateMap));
	   return new ModelAndView("jsonView", model);
	}
	
	/** 
	 * Description :  跳转到上级公司监控下级公司的订单列表页面
	 * Create Date: 2011-7-26上午10:11:50 by likg  Modified Date: 2011-7-26上午10:11:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgOrderMonitorListView")
	public ModelAndView toOrgOrderMonitorListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取上级公司的信息
		OrgInfo orgInfo = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("companyId", orgInfo.getCompany().getObjId()); //上级公司的公司id
		model.put("orgInfoId", orgInfo.getObjId()); //上级公司的机构id
		
		return new ModelAndView("orgOrderMonitorListView", model);
	}
}
