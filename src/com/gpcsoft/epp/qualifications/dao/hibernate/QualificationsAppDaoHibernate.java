package com.gpcsoft.epp.qualifications.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.qualifications.dao.QualificationsAppDao;
import com.gpcsoft.epp.qualifications.domain.QualificationsApp;

import org.springframework.stereotype.Repository;

@Repository
public class QualificationsAppDaoHibernate extends BaseGenericDaoHibernate<QualificationsApp> implements QualificationsAppDao {

}
