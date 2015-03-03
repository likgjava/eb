package com.gpcsoft.bizplatform.organization.dao.hibernate;


import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.ScoreDao;
import com.gpcsoft.bizplatform.organization.domain.Score;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class ScoreDaoHibernate extends BaseGenericDaoHibernate<Score> implements ScoreDao {

}
