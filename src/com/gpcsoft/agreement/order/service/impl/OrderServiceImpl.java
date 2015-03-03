package com.gpcsoft.agreement.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.manager.OrderManager;
import com.gpcsoft.agreement.order.service.OrderService;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

@Service 
public class OrderServiceImpl extends BaseGenericServiceImpl<Order> implements OrderService {

	@Autowired(required=true) @Qualifier("orderManagerImpl")
	private OrderManager orderManager;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDao;
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.order.service.OrderService#listOrderByTask(com.gpcsoft.core.utils.Page, javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	public Page<Order> listOrderByTask(Page<Order> page,HttpServletRequest request){
		return orderManager.listOrderByTask(page,request);
	}


	@SuppressWarnings("unchecked")
	public Page<Order> listOrderByTaskItem(Page<Order> page, HttpServletRequest request) {
		return  orderManager.listOrderByTaskItem(page,request);
	}
	
	/**
	 * 获得订单商品配件信息
	 */
	public List<OrderItem> getOrderGoodsOption(Map<String, Object> paramsMap) {
		return orderManager.getOrderGoodsOption(paramsMap);
	}

	/**
	 * 将合同的id更新到订单里面 
	 */
	public void saveOrderContract(Map<String, Object> paramsMap) {
		orderManager.saveOrderContract(paramsMap);
	}

	/**
	 * 保存/提交订单
	 */
	public Order saveOrder(Order baseobject,String methodName) throws Exception{
		return orderManager.saveOrder(baseobject, methodName);
	}

	/**
	 * 作废订单，同步删除订单与任务书的关系
	 */
	public void invalidOrder(String contractId) throws Exception{
		orderManager.invalidOrder(contractId);
	}

	/**
	 * Description :  创建订单
	 * Create Date: 2010-4-16上午11:00:14 by liangxj  Modified Date: 2010-4-16上午11:00:14 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Order createOrder(Map<String, Object> param) throws Exception {
		return orderManager.createOrderByShoppingCart((String)param.get("carItemObjIds"),null);
	}

	/**
	 * 创建订单（议价项目），异常情况：不是同一供应商，选购品目不存在任务书中
	 */
	public Order createOrderByBidding(Map<String, Object> param) throws Exception {
		return orderManager.createOrderByBidding(param);
	}

	/** 
	 * Description :  作废订单
	 * Create Date: 2010-11-25上午11:42:04 by yucy  Modified Date: 2010-11-25上午11:42:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveDestroyOrder(Map<String, Object> param) throws Exception {
		Order order = orderManager.get((String)param.get("objId"));
		order.setUseStatus(enumService.getValueByConstant("com.gpcsoft.eps.agreement.order.domain.Order.useStatus","INVALID"));
		orderManager.save(order);
		if("supplier".equals(param.get("destroyType"))){
			sendMassageAndEmail(order,order.getSupplier(),order.getBuyer());
		}else{
			sendMassageAndEmail(order,order.getBuyer(),order.getSupplier());
		}
		return true;
	}
	
	/** 
	 * Description :  发送邮件和站内信
	 * Create Date: 2010-11-19上午10:10:33 by yucy  Modified Date: 2010-11-19上午10:10:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void sendMassageAndEmail(Order order,OrgInfo sendOrg,OrgInfo receiveOrg)throws Exception {
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("orderId", order.getObjId());
		templateMap.put("orderNo", order.getOrderNo());
		templateMap.put("orgName", sendOrg.getOrgName());
		//templateMap.put("content", "<a href=\"javascript:void(0);\" onclick=\"viewOrderDetail('"+order.getObjId()+"','"+order.getOrderNo()+"')\">"+order.getOrderNo()+"</a>已由"+sendOrg.getOrgName()+ "作废！");
		String  messageContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.ORDER_DESTORY_TEMPLATE), templateMap);
		//发送站内信
		MessageUtil.sendMessagePrivete(
				orgInfoDao.getAllManagersByOrgInfoIds(User.USER_TYPE_MANAGER.toString(), receiveOrg.getObjId()),
				order.getOrderNo()+"已作废！", 
				messageContent, 
				null, 
				null, 
				true);
	}

	@Override
	protected void onAfterRemove(String s) {
		super.onAfterRemove(s);
	}
	
}
