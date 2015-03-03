package com.gpcsoft.epp.contract.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ContractOperInfoDao;
import com.gpcsoft.epp.contract.domain.ContractOperInfo;

import org.springframework.stereotype.Repository;

@Repository
public class ContractOperInfoDaoHibernate extends BaseGenericDaoHibernate<ContractOperInfo> implements ContractOperInfoDao {

}
