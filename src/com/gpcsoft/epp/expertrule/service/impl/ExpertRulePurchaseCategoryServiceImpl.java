package com.gpcsoft.epp.expertrule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;
import com.gpcsoft.epp.expertrule.manager.ExpertRulePurchaseCategoryManager;
import com.gpcsoft.epp.expertrule.service.ExpertRulePurchaseCategoryService;

@Service 
public class ExpertRulePurchaseCategoryServiceImpl extends BaseGenericServiceImpl<ExpertRulePurchaseCategory> implements ExpertRulePurchaseCategoryService {

	@Autowired(required=true) @Qualifier("expertRulePurchaseCategoryManagerImpl")
	private ExpertRulePurchaseCategoryManager expertRulePurchaseCategoryManager;

}
