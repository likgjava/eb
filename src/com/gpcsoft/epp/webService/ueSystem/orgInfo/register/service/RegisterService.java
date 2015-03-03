package com.gpcsoft.epp.webService.ueSystem.orgInfo.register.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;

public interface RegisterService {

	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构工商注册信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午02:04:45
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午02:04:45
	 */
	public  LoginDTO updateRegisterMessage(String orgCode,String xml);
	
	/**
	 * FuncName: getResgister
	 * Description :  UE平台机构信息下载REGISTER
	 * @param userCode
	 * @param password
	 * @param orgCode
	 * @param infoType
	 * @return String
	 * @author: shenjz
	 * @Create Date:2011-3-25  下午03:34:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  下午03:34:45
	 */
	public String getResgister(String userCode,String password,String orgCode,String infoType)throws Exception;
}
