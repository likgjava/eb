package com.gpcsoft.agreement.management.manager;

import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.core.manager.BaseManager;

public interface AgreementSecondManager extends BaseManager<AgreementSecond> {

	/** 
	 * Description :  保存二级协议
	 * Create Date: 2010-5-7上午10:33:09 by yucy  Modified Date: 2010-5-7上午10:33:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer saveAgreementSecond(Map<String, Object> params);

}
