package com.gpcsoft.epp.signuprecord.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.epp.signuprecord.manager.SignUpMResponeManager;
import com.gpcsoft.epp.signuprecord.service.SignUpMResponeService;

@Service 
public class SignUpMResponeServiceImpl extends BaseGenericServiceImpl<SignUpMRespone> implements SignUpMResponeService {

	@Autowired(required=true) @Qualifier("signUpMResponeManagerImpl")
	private SignUpMResponeManager signUpMResponeManager;

}
