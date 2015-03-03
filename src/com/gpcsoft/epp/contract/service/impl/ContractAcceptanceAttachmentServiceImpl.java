package com.gpcsoft.epp.contract.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.contract.domain.ContractAcceptanceAttachment;
import com.gpcsoft.epp.contract.manager.ContractAcceptanceAttachmentManager;
import com.gpcsoft.epp.contract.service.ContractAcceptanceAttachmentService;

@Service 
public class ContractAcceptanceAttachmentServiceImpl extends BaseGenericServiceImpl<ContractAcceptanceAttachment> implements ContractAcceptanceAttachmentService {

	@Autowired(required=true) @Qualifier("contractAcceptanceAttachmentManagerImpl")
	private ContractAcceptanceAttachmentManager contractAcceptanceAttachmentManager;

}
