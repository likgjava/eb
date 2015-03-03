package com.gpcsoft.agreement.order.dao;

import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrderGoodsOptionDao extends BaseGenericDao<OrderGoodsOption> {
	/** 
	 * Description :  改变选配的数量、金额
	 * Create Date: 2010-4-12下午03:17:17 by liangxj  Modified Date: 2010-4-12下午03:17:17 by liangxj
	 * @Exception   
	 */
	void updateChangeQtyAndMoney(OrderGoodsOption option);
}
