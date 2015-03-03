package com.gpcsoft.agreement.pub.service;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.core.service.BaseGenericService;

public interface AgContractService extends BaseGenericService<AgContract> {
	
	/** 
	 * Description :  确认合同
	 * Create Date: 2010-4-13上午11:45:28 by liangxj  Modified Date: 2010-4-13上午11:45:28 by liangxj
	 * @param   sureRole 确认角色 supplier 或者 buyer
	 * @return  
	 * @Exception   
	 */
	AgContract auditContract(AgContract contract, String sureRole) throws Exception;
	
	/** 
	 * Description :  保存合同
	 * Create Date: 2010-4-13上午11:45:28 by liangxj  Modified Date: 2010-4-13上午11:45:28 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	AgContract saveContract(AgContract baseobject)throws Exception;
	
	/** 
	 * Description :  作废合同
	 * Create Date: 2010-4-13上午11:45:28 by liangxj  Modified Date: 2010-4-13上午11:45:28 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	AgContract releaseContract(AgContract baseobject, String sureRole)throws Exception;
}
