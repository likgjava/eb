package com.gpcsoft.bizplatform.organization.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.dao.EvaluateDao;
import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.EvaluateManager;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

@Service 
public class EvaluateServiceImpl extends BaseGenericServiceImpl<Evaluate> implements EvaluateService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("evaluateManagerImpl")
	private EvaluateManager evaluateManager;
	
	@Autowired(required=true) @Qualifier("evaluateDaoHibernate")
	private EvaluateDao evaluateDaoHibernate;
	
	/** 
	 * Description : 取得机构评价统计表 
	 * Create Date: 2010-8-12下午05:26:37 by yucy  Modified Date: 2010-8-12下午05:26:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getTotalTableInfo(OrgInfo currentOrg ,Character Level) throws Exception {
		return evaluateDaoHibernate.getTotalTableInfo(currentOrg,Level);
	}

	/** 
	 * Description :  取得机构指标评价详情信息
	 * Create Date: 2010-8-13下午03:30:41 by yucy  Modified Date: 2010-8-13下午03:30:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getQuataScoreDetail(OrgInfo currentOrg, String quotaType)  throws Exception {
		return evaluateDaoHibernate.getQuataScoreDetail( currentOrg, quotaType);
	}

	/** 
	 * Description :  获取评价列表
	 * Create Date: 2011-2-23下午06:05:22 by likg  Modified Date: 2011-2-23下午06:05:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Evaluate> getEvaluateList(Page<Evaluate> page, Map<String, Object> param) throws Exception {
		return evaluateDaoHibernate.getEvaluateList(page, param);
	}

	/** 
	 * Description : 根据机构id取得评价信息
	 * Create Date: 2011-12-7上午11:56:19 by yucy  Modified Date: 2011-12-7上午11:56:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getEvaluateByOrgId(String orgIds) throws Exception {
		return evaluateDaoHibernate.getEvaluateByOrgId(orgIds);
	}
}
