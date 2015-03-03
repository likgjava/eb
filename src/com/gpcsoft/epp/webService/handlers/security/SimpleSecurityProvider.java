package com.gpcsoft.epp.webService.handlers.security;

import java.util.List;

import org.apache.axis.MessageContext;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.srplatform.auth.domain.User;

public class SimpleSecurityProvider implements SecurityProvider{
	
	private UserApiService userApiServiceImpl;
	private UserApiService getUserApiServiceImpl(){
		if(userApiServiceImpl == null)
			userApiServiceImpl = (UserApiService)FrameBeanFactory.getBean("userApiServiceImpl");
		return userApiServiceImpl;
	}
	
	public AuthenticatedUser authenticate(MessageContext messagecontext) {
		String userName = messagecontext.getUsername();
		String password = encryptPassword(messagecontext.getPassword());//MD5加密
		
		List<User> userList = getUserApiServiceImpl().getUserByUserName(userName, password);
		if(userList != null && userList.size() != 0 && userList.get(0) != null)
			return new ZCWSAuthenticatedUser(userList.get(0).getObjId());
		return null;
	}

	public boolean userMatches(AuthenticatedUser authenticateduser, String s) {
		return false;
	}
	
	/**
	 * 密码MD5加密
	 * @param password
	 * @return
	 */
	public String encryptPassword(String password)
	{
		return MathUtil.encryptPassWordSHA(password);
	}
}
