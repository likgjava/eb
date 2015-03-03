package com.gpcsoft.epp.webService.ueSystem.orgInfo.capital.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;

public interface CapitalService {

	/**
	 * FuncName: updateCapitalMessage
	 * Description : 维护资本信息
	 * @param 
	 * @param orgCode
	 * @param xml
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-3-25  下午04:57:19
	 * @Modifier: liuke
	 * @Modified Date:2011-3-25  下午04:57:19
	 */
	public  LoginDTO updateCapitalMessage(String orgCode,String xml);
}
