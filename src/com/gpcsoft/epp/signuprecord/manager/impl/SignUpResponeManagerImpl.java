package com.gpcsoft.epp.signuprecord.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.signuprecord.dao.SignUpResponeDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.manager.SignUpResponeManager;

@Repository
public class SignUpResponeManagerImpl extends BaseManagerImpl<SignUpRespone> implements SignUpResponeManager {

	@Autowired(required=true)  @Qualifier("signUpResponeDaoHibernate")
	private SignUpResponeDao signUpResponeDaoHibernate;

}
