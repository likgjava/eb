package com.gpcsoft.agreement.order.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;

/** 
  *  Comments: <strong>OrderService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-10-22 下午02:43:04 by yucy    					                            
  *  <br/>Modified Date:  2010-10-22 下午02:43:04 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
/** 
  *  Comments: <strong>OrderService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-10-22 下午02:43:06 by yucy    					                            
  *  <br/>Modified Date:  2010-10-22 下午02:43:06 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public interface OrderService extends BaseGenericService<Order> {

	/** 
	 * Description :  取得任务单相关订单(byTaskId)
	 * Create Date: 2010-4-13上午11:45:28 by yucy  Modified Date: 2010-4-13上午11:45:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	Page listOrderByTask(Page<Order> page, HttpServletRequest request);

	/** 
	 * Description :  取得任务单相关订单(byTaskItemId)
	 * Create Date: 2010-4-13下午03:29:41 by yucy  Modified Date: 2010-4-13下午03:29:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	Page listOrderByTaskItem(Page<Order> page, HttpServletRequest request);

	/**
	 * Description :  取得订单商品配件信息
	 * Create Date: 2010-4-13下午04:16:32 by sunl  Modified Date: 2010-4-13下午04:16:32 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<OrderItem> getOrderGoodsOption(Map<String, Object> paramsMap);
	
	/**
	 * Description : 将合同的id更新到订单里面 
	 * Create Date: 2010-4-13下午07:14:02 by liangxj  Modified Date: 2010-4-13下午07:14:02 by liangxj
	 * @param  参数集合
	 * @return  
	 * @Exception
	 */
	void saveOrderContract(Map<String, Object> paramsMap);
	
	/**
	 * Description : 保存/提交订单
	 * Create Date: 2010-4-19下午03:15:02 by sunl  Modified Date: 2010-4-19下午03:15:02 by sunl
	 * @param  order 订单 , methodName 操作方法
	 * @return  
	 * @Exception
	 */
	Order saveOrder(Order order,String methodName) throws Exception;
	
	/**
	 * Description :  作废订单，同步删除订单与任务书的关系
	 * Create Date: 2010-4-21下午03:38:52 by liangxj  Modified Date: 2010-4-21下午03:38:52 by liangxj
	 * @param   合同id
	 * @return  
	 * @Exception
	 */
	void invalidOrder(String contractId) throws Exception;
	
	/**
	 * Description :  创建订单
	 * Create Date: 2010-4-23上午11:15:22 by liangxj  Modified Date: 2010-4-23上午11:15:22 by yucy
	 * @param   carItemObjIds 购物车商品id
	 * @return  
	 * @Exception 不是同一供应商，选购品目不存在任务书中
	 */
	Order createOrder(Map<String, Object> param) throws Exception;

	/**
	 * Description :  创建订单（bidding）
	 * Create Date: 2010-4-23上午11:15:22 by yucy  Modified Date: 2010-4-23上午11:15:22 by yucy
	 * @param   bargainRecordItemObjIds 议价记录商品id
	 * @return  
	 * @Exception 不是同一供应商，选购品目不存在任务书中
	 */
	Object createOrderByBidding(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  作废订单
	 * Create Date: 2010-11-25上午11:42:04 by yucy  Modified Date: 2010-11-25上午11:42:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveDestroyOrder(Map<String, Object> param) throws Exception;

}
