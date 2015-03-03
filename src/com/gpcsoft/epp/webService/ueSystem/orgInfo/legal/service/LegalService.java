package com.gpcsoft.epp.webService.ueSystem.orgInfo.legal.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;

public interface LegalService {

	/**
	 * FuncName: updateBasicMessage
	 * Description :维护机构法务信息
	 * @param 
	 * @param xml
	 * @return String
	 * @author: chench
	 * @Create Date:2011-3-25  下午02:04:45
	 * @Modifier: chench
	 * @Modified Date:2011-3-25  下午02:04:45
	 */
	public  LoginDTO updateLegalMessage(String orgCode,String xml);
}
