package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractSupplierDao;
import com.gpcsoft.epp.contract.domain.ContractSupplier;

import org.springframework.stereotype.Repository;

@Repository
public class ContractSupplierDaoHibernate extends BaseGenericDaoHibernate<ContractSupplier> implements ContractSupplierDao {

}
