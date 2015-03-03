package com.gpcsoft.agreement.management.manager;

import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.core.manager.BaseManager;

public interface AgreementManager extends BaseManager<Agreement> {

	/** 
	 * Description :  生成协议编号
	 * Create Date: 2010-5-14下午04:32:02 by yucy  Modified Date: 2010-5-14下午04:32:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String createAgreementNo();

}
