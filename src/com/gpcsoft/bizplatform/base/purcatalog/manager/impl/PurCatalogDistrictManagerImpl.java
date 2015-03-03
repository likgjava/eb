package com.gpcsoft.bizplatform.base.purcatalog.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDistrictDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.bizplatform.base.purcatalog.manager.PurCatalogDistrictManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class PurCatalogDistrictManagerImpl extends BaseManagerImpl<PurCatalogDistrict> implements PurCatalogDistrictManager {

	@Autowired(required=true)  @Qualifier("purCatalogDistrictDaoHibernate")
	private PurCatalogDistrictDao purCatalogDistrictDaoHibernate;

	public Boolean deletePurCatalogDistrictByCataId(String purCatalogId) {
		return purCatalogDistrictDaoHibernate.deletePurCatalogDistrictByCataId(purCatalogId);
	}

}
