package com.gpcsoft.agreement.management.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.core.service.BaseGenericService;

public interface AgreementService extends BaseGenericService<Agreement> {

	/** 
	 * Description :  生成协议编号
	 * Create Date: 2010-5-14下午04:30:55 by yucy  Modified Date: 2010-5-14下午04:30:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String createAgreementNo();

	/** 
	 * Description :  取得供应商可以录入行情的区间  
	 * Create Date: 2010-9-26下午05:20:12 by yucy  Modified Date: 2010-9-26下午05:20:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictByOrg(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  保存协议
	 * Create Date: 2011-12-5下午05:10:46 by yucy  Modified Date: 2011-12-5下午05:10:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveAgreement(Agreement agreement) throws Exception;
}
