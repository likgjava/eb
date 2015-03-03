package com.gpcsoft.epp.signuprecord.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.signuprecord.dao.SignUpMResponeDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.epp.signuprecord.manager.SignUpMResponeManager;

@Repository
public class SignUpMResponeManagerImpl extends BaseManagerImpl<SignUpMRespone> implements SignUpMResponeManager {

	@Autowired(required=true)  @Qualifier("signUpMResponeDaoHibernate")
	private SignUpMResponeDao signUpMResponeDaoHibernate;

}
