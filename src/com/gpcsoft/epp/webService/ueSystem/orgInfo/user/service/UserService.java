package com.gpcsoft.epp.webService.ueSystem.orgInfo.user.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;

public interface UserService {

	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构用户注册信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateUserMessage(String orgCode,String xml);
}
