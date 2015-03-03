package com.gpcsoft.agreement.management.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface AgreementDao extends BaseGenericDao<Agreement> {

	/** 
	 * Description :  取得供应商可以录入行情的区间  
	 * Create Date: 2010-9-26下午05:20:12 by yucy  Modified Date: 2010-9-26下午05:20:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictByOrg(Map<String, Object> param) throws Exception;

}
