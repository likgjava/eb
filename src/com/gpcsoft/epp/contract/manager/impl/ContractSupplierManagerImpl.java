package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractSupplierDao;
import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.manager.ContractSupplierManager;

@Repository
public class ContractSupplierManagerImpl extends BaseManagerImpl<ContractSupplier> implements ContractSupplierManager {

	@Autowired(required=true)  @Qualifier("contractSupplierDaoHibernate")
	private ContractSupplierDao contractSupplierDaoHibernate;

}
