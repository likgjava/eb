package com.gpcsoft.epp.expertrule.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;

public interface ExpertRulePurchaseCategoryDao extends BaseGenericDao<ExpertRulePurchaseCategory> {

	/** 
	 * Description :保存专家库品目
	 * Create Date: 2010-8-5上午11:35:42 by liuke  Modified Date: 2010-8-5上午11:35:42 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveExpertRulePurchaseCategory(ExpertRulePurchaseCategory category);
	
	
	/**
	 * 
	 * Description :删除专家库品目 
	 * Create Date: 2010-11-18上午10:46:44 by liuke  Modified Date: 2010-11-18上午10:46:44 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeAllExpertRulePurchaseCategory();
	
}
