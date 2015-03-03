package com.gpcsoft.epp.webService.ueSystem.bulletin.service;

import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.ApplyProjectDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SignUpResponeDTO;

public interface SignUpRecordService extends BaseGenericService{

	
	public boolean saveSignUpRecord(ApplyProjectDTO applyProjectDTO, List<SignUpResponeDTO> signUpResponeDTOList)  throws Exception;
}
