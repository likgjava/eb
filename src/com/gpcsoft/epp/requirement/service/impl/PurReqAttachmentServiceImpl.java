package com.gpcsoft.epp.requirement.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.requirement.domain.PurReqAttachment;
import com.gpcsoft.epp.requirement.manager.PurReqAttachmentManager;
import com.gpcsoft.epp.requirement.service.PurReqAttachmentService;

@Service 
public class PurReqAttachmentServiceImpl extends BaseGenericServiceImpl<PurReqAttachment> implements PurReqAttachmentService {

	@Autowired(required=true) @Qualifier("purReqAttachmentManagerImpl")
	private PurReqAttachmentManager purReqAttachmentManager;

}
