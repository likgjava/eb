package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractFundDetailDao;
import com.gpcsoft.epp.contract.domain.ContractFundDetail;

import org.springframework.stereotype.Repository;

@Repository
public class ContractFundDetailDaoHibernate extends BaseGenericDaoHibernate<ContractFundDetail> implements ContractFundDetailDao {

}
