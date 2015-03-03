package com.gpcsoft.agreement.order.service;
import com.gpcsoft.agreement.order.domain.OrderGoodsOption;
import com.gpcsoft.core.service.BaseGenericService;

public interface OrderGoodsOptionService extends BaseGenericService<OrderGoodsOption> {
	/** 
	 * Description : 改变选配的数量、金额 
	 * Create Date: 2010-4-12下午02:02:33 by liangxj  Modified Date: 2010-4-12下午02:02:33 by liangxj
	 * @param   选配
	 * @Exception   
	 */
	void updateGoodsChangeQtyAndMoney(OrderGoodsOption option);
}
