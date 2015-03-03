package com.gpcsoft.epp.webService.handlers.security;

import org.apache.axis.security.AuthenticatedUser;

public class ZCWSAuthenticatedUser implements AuthenticatedUser{

	private String name;
	ZCWSAuthenticatedUser(String name){
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
