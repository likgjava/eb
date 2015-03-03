package com.gpcsoft.pubservice.application.advertisement.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.advertisement.dao.AdvertisingPositionDao;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisingPositionDaoHibernate extends BaseGenericDaoHibernate<AdvertisingPosition> implements AdvertisingPositionDao {

}
