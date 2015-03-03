package com.gpcsoft.epp.contract.dao.hibernate;



import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.contract.dao.ClauseDetailDao;
import com.gpcsoft.epp.contract.domain.ClauseDetail;

import org.springframework.stereotype.Repository;

@Repository
public class ClauseDetailDaoHibernate extends BaseGenericDaoHibernate<ClauseDetail> implements ClauseDetailDao {

}
