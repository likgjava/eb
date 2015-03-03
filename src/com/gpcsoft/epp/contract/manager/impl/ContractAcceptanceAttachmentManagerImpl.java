package com.gpcsoft.epp.contract.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.contract.dao.ContractAcceptanceAttachmentDao;
import com.gpcsoft.epp.contract.domain.ContractAcceptanceAttachment;
import com.gpcsoft.epp.contract.manager.ContractAcceptanceAttachmentManager;

@Repository
public class ContractAcceptanceAttachmentManagerImpl extends BaseManagerImpl<ContractAcceptanceAttachment> implements ContractAcceptanceAttachmentManager {

	@Autowired(required=true)  @Qualifier("contractAcceptanceAttachmentDaoHibernate")
	private ContractAcceptanceAttachmentDao contractAcceptanceAttachmentDaoHibernate;

}
