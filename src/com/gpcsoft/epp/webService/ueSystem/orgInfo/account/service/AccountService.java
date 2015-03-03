package com.gpcsoft.epp.webService.ueSystem.orgInfo.account.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;



public interface AccountService {

	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构开户信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateAccountMessage(String orgCode,String xml);
}
