package com.gpcsoft.epp.signuprecord.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.signuprecord.dao.SignUpCondFactorDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.manager.SignUpCondFactorManager;

@Repository
public class SignUpCondFactorManagerImpl extends BaseManagerImpl<SignUpCondFactor> implements SignUpCondFactorManager {

	@Autowired(required=true)  @Qualifier("signUpCondFactorDaoHibernate")
	private SignUpCondFactorDao signUpCondFactorDaoHibernate;

}
