package com.gpcsoft.bizplatform.base.purcatalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.purcatalog.dao.PurCatalogDetailDao;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogDetailService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class PurCatalogDetailServiceImpl extends BaseGenericServiceImpl<PurCatalogDetail> implements PurCatalogDetailService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("purCatalogDetailDaoHibernate")
	private PurCatalogDetailDao purCatalogDetailDaoHibernate;

}
