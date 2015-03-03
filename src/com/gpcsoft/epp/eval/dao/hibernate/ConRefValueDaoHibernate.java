package com.gpcsoft.epp.eval.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.eval.dao.ConRefValueDao;
import com.gpcsoft.epp.eval.domain.ConRefValue;
import org.springframework.stereotype.Repository;

@Repository
public class ConRefValueDaoHibernate extends BaseGenericDaoHibernate<ConRefValue> implements ConRefValueDao {

}
