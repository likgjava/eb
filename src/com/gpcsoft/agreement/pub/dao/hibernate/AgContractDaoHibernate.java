package com.gpcsoft.agreement.pub.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.pub.dao.AgContractDao;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class AgContractDaoHibernate extends BaseGenericDaoHibernate<AgContract> implements AgContractDao {

}
