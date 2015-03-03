package com.gpcsoft.epp.requirement.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.requirement.dao.PurReqAttachmentDao;
import com.gpcsoft.epp.requirement.domain.PurReqAttachment;
import com.gpcsoft.epp.requirement.manager.PurReqAttachmentManager;

@Repository
public class PurReqAttachmentManagerImpl extends BaseManagerImpl<PurReqAttachment> implements PurReqAttachmentManager {

	@Autowired(required=true)  @Qualifier("purReqAttachmentDaoHibernate")
	private PurReqAttachmentDao purReqAttachmentDaoHibernate;

}
