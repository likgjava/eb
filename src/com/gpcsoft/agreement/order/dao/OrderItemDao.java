package com.gpcsoft.agreement.order.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrderItemDao extends BaseGenericDao<OrderItem> {
	/** 
	 * Description :  改变订单的数量、金额
	 * Create Date: 2010-4-12下午03:17:17 by liangxj  Modified Date: 2010-4-12下午03:17:17 by liangxj
	 * @Exception   
	 */
	void updateChangeQtyAndMoney(OrderItem orderItem);
	
	/**
	 * Description :  获得订单商品配件信息
	 * Create Date: 2010-4-13下午04:18:13 by sunl  Modified Date: 2010-4-13下午04:18:13 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<OrderItem> getOrderItemGoodsOption(Map<String, Object> paramsMap);
	
	/** 
	 * Description :  根据合同id获得订单的明细，并获得商品的选配
	 * Create Date: 2010-12-14下午01:39:56 by liangxj  Modified Date: 2010-12-14下午01:39:56 by liangxj
	 * @param   contractId 合同Id
	 * @return  
	 * @Exception   
	 */
	public List<OrderItem> getOrderItemByContract(String contractId);

	/** 
	 * Description :  删除时同步订单中的品目名称
	 * Create Date: 2011-3-1下午09:32:12 by yucy  Modified Date: 2011-3-1下午09:32:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCategoryNames(String itemsId) throws Exception;
}
