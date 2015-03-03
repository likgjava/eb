package com.gpcsoft.agreement.management.service;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.core.service.BaseGenericService;

public interface AgreementSecondService extends BaseGenericService<AgreementSecond> {

	/** 
	 * Description :  保存二级协议
	 * Create Date: 2010-4-25下午11:35:29 by yucy  Modified Date: 2010-4-25下午11:35:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveAgreementSecond(Map<String, Object> params);

}
