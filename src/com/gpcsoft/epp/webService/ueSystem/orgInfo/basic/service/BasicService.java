package com.gpcsoft.epp.webService.ueSystem.orgInfo.basic.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;


public interface BasicService {

	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构基本信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-24  下午02:04:45
	 * @Modifier: liuke
	 * @Modified Date:2011-3-24  下午02:04:45
	 */
	public LoginDTO updateBasicMessage(String orgCode,String xml);
	
	/**
	 * FuncName: getBasicXml
	 * Description :  创建或修改对象
	 * @param 
	 * @return String
	 * @author: shenjz
	 * @Create Date:2011-3-25  上午11:21:48
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-25  上午11:21:48
	 */
	public String getOrginfoXml(String userCode,String password,String orgCode,String infoType) throws Exception;

}
