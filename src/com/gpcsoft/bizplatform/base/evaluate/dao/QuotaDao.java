package com.gpcsoft.bizplatform.base.evaluate.dao;


import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface QuotaDao extends BaseGenericDao<Quota> {

	/** 
	 * Description :  根据组织机构信息(OrgInfo)取得评价指标集合
	 * Create Date: 2010-7-21下午04:14:34 by yucy  Modified Date: 2010-7-21下午04:14:34 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Quota> getQuotaLisstByOrgInfo(Map<String, Object> params) throws Exception;

	/** 
	 * Description :  删除指标项
	 * Create Date: 2010-7-26下午03:43:19 by yucy  Modified Date: 2010-7-26下午03:43:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public int delQuota(String[] quotaIdArray);

	/** 
	 * Description :  (检查可删性)
	 * Create Date: 2010-9-3上午09:46:22 by yucy  Modified Date: 2010-9-3上午09:46:22 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean checkDelQuota(String[] quotaIdArray) throws Exception;

}
