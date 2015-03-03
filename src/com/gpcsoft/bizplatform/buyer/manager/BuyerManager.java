package com.gpcsoft.bizplatform.buyer.manager;

import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;

public interface BuyerManager  extends BaseManager<Buyer>{

	/** 
	 * Description : 更新采购人审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateBuyerStatus(String buyerId,OrgInfo orgInfo) throws Exception;
	
	/** 
	 * Description :  保存或更新采购人扩展信息
	 * 				  传入参数为buyerId和表单对象buyer
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer saveBuyer(boolean isSave,Buyer buyer) throws Exception;
}
