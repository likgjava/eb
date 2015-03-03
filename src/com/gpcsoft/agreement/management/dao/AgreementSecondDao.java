package com.gpcsoft.agreement.management.dao;

import java.util.Map;

import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface AgreementSecondDao extends BaseGenericDao<AgreementSecond> {

	/** 
	 * Description :  保存二级协议
	 * Create Date: 2010-4-25下午11:38:01 by yucy  Modified Date: 2010-4-25下午11:38:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer saveAgreementSecond(Map<String, Object> params);

}
