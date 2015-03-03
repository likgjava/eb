package com.gpcsoft.pubservice.application.message.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.message.dao.AdviseDao;
import com.gpcsoft.pubservice.application.message.domain.Advise;
import org.springframework.stereotype.Repository;

@Repository
public class AdviseDaoHibernate extends BaseGenericDaoHibernate<Advise> implements AdviseDao {

}
