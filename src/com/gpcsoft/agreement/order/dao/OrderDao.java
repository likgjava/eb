package com.gpcsoft.agreement.order.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;

public interface OrderDao extends BaseGenericDao<Order> {

	/** 
	 * Description :  取得任务单相关订单(byTaskId)
	 * Create Date: 2010-4-13上午11:46:31 by yucy  Modified Date: 2010-4-13上午11:46:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Order> listOrderByTask(Page<Order> page, String protaskId);

	/** 
	 * Description :  取得任务单相关订单(byTaskItemId)
	 * Create Date: 2010-4-13下午03:31:42 by yucy  Modified Date: 2010-4-13下午03:31:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Order> listOrderByTaskItem(Page<Order> page, String protaskItemId);

	
	/**
	 * Description :  获得订单商品配件信息
	 * Create Date: 2010-4-13下午04:18:13 by sunl  Modified Date: 2010-4-13下午04:18:13 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<OrderItem> getOrderGoodsOption(Map<String, Object> paramsMap);
	
	/**
	 * Description : 将合同的id更新到订单里面 
	 * Create Date: 2010-4-13下午07:14:02 by liangxj  Modified Date: 2010-4-13下午07:14:02 by wangsw
	 * @param  orderIds 订单id，多个id以逗号分隔  contractId 合同id
	 * @return  
	 * @Exception
	 */
	void saveOrderContract(String orderIds,String contractId);
	
	/**
	 * Description :  作废订单，同步删除订单与任务书的关系
	 * Create Date: 2010-4-21下午03:38:52 by liangxj  Modified Date: 2010-4-21下午03:38:52 by liangxj
	 * @param   合同id
	 * @return  
	 * @Exception
	 */
	void invalidOrder(String contractId);

	/** 
	 * Description :  更改订单支付状态
	 * Create Date: 2011-6-20下午02:34:39 by yucy  Modified Date: 2011-6-20下午02:34:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updatePayStatus(String id, String status) throws Exception;
}
