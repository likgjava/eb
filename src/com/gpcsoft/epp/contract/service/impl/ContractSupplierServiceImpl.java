package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.manager.ContractSupplierManager;
import com.gpcsoft.epp.contract.service.ContractSupplierService;

@Service 
public class ContractSupplierServiceImpl extends BaseGenericServiceImpl<ContractSupplier> implements ContractSupplierService {

	@Autowired(required=true) @Qualifier("contractSupplierManagerImpl")
	private ContractSupplierManager contractSupplierManager;

}
