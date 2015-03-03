package com.gpcsoft.smallscale.groupbuying.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;

public interface GroupBuyerService extends BaseGenericService<GroupBuyer> {

	/** 
	 * Description :  保存团购采购人信息，并生产订单信息
	 * Create Date: 2011-6-22下午07:39:35 by likg  Modified Date: 2011-6-22下午07:39:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveGroupBuyer(GroupBuyer groupBuyer) throws Exception;

	/** 
	 * Description :  修改支付状态
	 * Create Date: 2011-6-24上午11:27:13 by likg  Modified Date: 2011-6-24上午11:27:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updatePayStatus(String objId, String payStatus) throws Exception;

}
