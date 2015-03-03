package com.gpcsoft.epp.webService.ueSystem.orgInfo.business.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;


public interface BusinessService {

	/**
	 * FuncName: updateBusinessMessage
	 * Description :  维护营业信息
	 * @param 
	 * @param orgCode
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-25  下午04:57:37
	 * @Modifier: liuke
	 * @Modified Date:2011-3-25  下午04:57:37
	 */
	public  LoginDTO updateBusinessMessage(String orgCode,String xml);
	
	/**
	 * FuncName: getBusiness
	 * Description :  创建或修改对象
	 * @param userCode
	 * @param password
	 * @param orgCode
	 * @param infoType
	 * @return String
	 * @throws Exception 
	 * @author: shenjz
	 * @Create Date:2011-3-25  下午04:38:30
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  下午04:38:30
	 */
	public String getBusiness(String userCode,String password,String orgCode,String infoType)throws Exception;

}
