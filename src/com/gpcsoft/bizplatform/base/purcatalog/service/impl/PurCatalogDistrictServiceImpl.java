package com.gpcsoft.bizplatform.base.purcatalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.bizplatform.base.purcatalog.manager.PurCatalogDistrictManager;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogDistrictService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class PurCatalogDistrictServiceImpl extends BaseGenericServiceImpl<PurCatalogDistrict> implements PurCatalogDistrictService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("purCatalogDistrictManagerImpl")
	private PurCatalogDistrictManager purCatalogDistrictManager;

}
