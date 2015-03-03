package com.gpcsoft.epp.signuprecord.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.signuprecord.dao.SignUpResponeDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;

import org.springframework.stereotype.Repository;

@Repository
public class SignUpResponeDaoHibernate extends BaseGenericDaoHibernate<SignUpRespone> implements SignUpResponeDao {

}
