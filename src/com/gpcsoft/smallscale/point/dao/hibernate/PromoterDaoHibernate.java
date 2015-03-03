package com.gpcsoft.smallscale.point.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.PromoterDao;
import com.gpcsoft.smallscale.point.domain.Promoter;
import org.springframework.stereotype.Repository;

@Repository
public class PromoterDaoHibernate extends BaseGenericDaoHibernate<Promoter> implements PromoterDao {

}
