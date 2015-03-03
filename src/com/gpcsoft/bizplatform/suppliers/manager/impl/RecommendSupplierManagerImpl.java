package com.gpcsoft.bizplatform.suppliers.manager.impl;

import org.springframework.stereotype.Repository;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.bizplatform.suppliers.manager.RecommendSupplierManager;
import com.gpcsoft.bizplatform.suppliers.domain.RecommendSupplier;

@Repository
public class RecommendSupplierManagerImpl extends BaseManagerImpl<RecommendSupplier> implements RecommendSupplierManager {

	//@Autowired(required=true)  @Qualifier("recommendSupplierDaoHibernate")
	//private RecommendSupplierDao recommendSupplierDaoHibernate;

}
