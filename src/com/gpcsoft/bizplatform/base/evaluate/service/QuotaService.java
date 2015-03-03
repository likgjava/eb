package com.gpcsoft.bizplatform.base.evaluate.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.core.service.BaseGenericService;

public interface QuotaService extends BaseGenericService<Quota> {


	/** 
	 * Description :   根据组织机构信息(OrgInfo)取得评价指标集合
	 * Create Date: 2010-7-21下午04:08:00 by yucy  Modified Date: 2010-7-21下午04:08:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Quota> getQuotaLisstByOrgInfo(Map<String, Object> params) throws Exception;

	/** 
	 * Description :  删除指标项(检查可删性)
	 * Create Date: 2010-7-26下午03:43:19 by yucy  Modified Date: 2010-7-26下午03:43:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public int delQuota(String[] quotaIdArray) throws Exception;

}
