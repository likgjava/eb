package com.gpcsoft.smallscale.point.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.point.dao.PromoterInfoDao;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;
import org.springframework.stereotype.Repository;

@Repository
public class PromoterInfoDaoHibernate extends BaseGenericDaoHibernate<PromoterInfo> implements PromoterInfoDao {

}
