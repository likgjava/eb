package com.gpcsoft.bizplatform.organization.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.SuccessCaseDao;
import com.gpcsoft.bizplatform.organization.domain.SuccessCase;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class SuccessCaseDaoHibernate extends BaseGenericDaoHibernate<SuccessCase> implements SuccessCaseDao {

}
