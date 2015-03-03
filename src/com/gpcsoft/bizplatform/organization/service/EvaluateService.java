package com.gpcsoft.bizplatform.organization.service;


import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;

public interface EvaluateService extends BaseGenericService<Evaluate> {

	/** 
	 * Description : 取得机构评价统计表 
	 * Create Date: 2010-8-12下午05:26:37 by yucy  Modified Date: 2010-8-12下午05:26:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getTotalTableInfo(OrgInfo currentOrg ,Character Level) throws Exception;

	/** 
	 * Description :  取得机构指标评价详情信息
	 * Create Date: 2010-8-13下午03:30:41 by yucy  Modified Date: 2010-8-13下午03:30:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getQuataScoreDetail(OrgInfo currentOrg, String quotaType) throws Exception;
	
	/** 
	 * Description :  获取评价列表
	 * Create Date: 2011-2-23下午06:05:22 by likg  Modified Date: 2011-2-23下午06:05:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Evaluate> getEvaluateList(Page<Evaluate> page, Map<String, Object> param) throws Exception;

	/** 
	 * Description : 根据机构id取得评价信息
	 * Create Date: 2011-12-7上午11:56:19 by yucy  Modified Date: 2011-12-7上午11:56:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getEvaluateByOrgId(String orgIds) throws Exception;
}
