package com.gpcsoft.agreement.order.dao;

import com.gpcsoft.agreement.order.domain.OrderProtask;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrderProtaskDao extends BaseGenericDao<OrderProtask> {
	
	/** 
	 * Description :  签订或作废合同时，将同步修改任务书条目的已完成数量和金额
	 * Create Date: 2010-4-20下午02:15:20 by liangxj  Modified Date: 2010-4-20下午02:15:20 by wangsw
	 * @param   合同id ,操作符 "+" 或 "-"
	 * @return  
	 * @Exception   
	 */
	void updateProtaskByContractForSign(String contractObjId,String operator) throws Exception;
	
	/** 
	 * Description :  作废合同时，将同步删除订单与任务书的关系
	 * Create Date: 2010-4-20下午02:15:20 by liangxj  Modified Date: 2010-4-20下午02:15:20 by wangsw
	 * @param   合同id ,操作符 "+" 或 "-"
	 * @return  
	 * @Exception   
	 */
	void deleteProtaskByContractForInvalid(String contractObjId) throws Exception;
}
