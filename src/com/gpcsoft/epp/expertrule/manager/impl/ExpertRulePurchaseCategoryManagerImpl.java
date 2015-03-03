package com.gpcsoft.epp.expertrule.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.expertrule.dao.ExpertRulePurchaseCategoryDao;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;
import com.gpcsoft.epp.expertrule.manager.ExpertRulePurchaseCategoryManager;

@Repository
public class ExpertRulePurchaseCategoryManagerImpl extends BaseManagerImpl<ExpertRulePurchaseCategory> implements ExpertRulePurchaseCategoryManager {

	@Autowired(required=true)  @Qualifier("expertRulePurchaseCategoryDaoHibernate")
	private ExpertRulePurchaseCategoryDao expertRulePurchaseCategoryDaoHibernate;

}
