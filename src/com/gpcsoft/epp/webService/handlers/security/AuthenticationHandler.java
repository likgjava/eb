package com.gpcsoft.epp.webService.handlers.security;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;

public class AuthenticationHandler extends BasicHandler{


	public void invoke(MessageContext messagecontext) throws AxisFault {
		SecurityProvider securityProvider = null;
		
		if(messagecontext.getProperty("securityProvider") == null){
				securityProvider = new SimpleSecurityProvider();
				messagecontext.setProperty("securityProvider", securityProvider);
		}else{
			securityProvider = (SecurityProvider) messagecontext.getProperty("securityProvider");
		}
		
		if(securityProvider == null){
			throw new AxisFault("没有认证器");
		}
		
		AuthenticatedUser authUser = securityProvider.authenticate(messagecontext);
		if(authUser == null){
			throw new AxisFault("ZC-0001", "用户验证失败", null, null);
		}
		
		//用户通过认证，把用户的设置成认证了的用户。
		messagecontext.setProperty("authenticatedUser", authUser);
	}
}
