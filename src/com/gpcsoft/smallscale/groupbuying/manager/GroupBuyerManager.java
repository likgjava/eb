package com.gpcsoft.smallscale.groupbuying.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;

public interface GroupBuyerManager extends BaseManager<GroupBuyer> {

	/** 
	 * Description :  修改支付状态
	 * Create Date: 2011-6-24上午11:27:13 by likg  Modified Date: 2011-6-24上午11:27:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updatePayStatus(String objId, String payStatus) throws Exception;
}