package com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.service;

import com.gpcsoft.epp.webService.ueSystem.common.dto.response.LoginDTO;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.qulification.dto.MaintainDTO;

public interface QulificationService {

	/**
	 * 保存资质信息
	 * @param userCode 用户code
	 * @param orgCode 机构code
	 * @param maintainDTO 资质信息
	 * @return
	 * @author zhouzhanghe
	 * @since 2011.3.25
	 */
	public LoginDTO updateQulification(String userCode, String orgCode, MaintainDTO maintainDTO);
}
