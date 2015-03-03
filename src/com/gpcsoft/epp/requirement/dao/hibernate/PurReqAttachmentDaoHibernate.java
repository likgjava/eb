package com.gpcsoft.epp.requirement.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.requirement.dao.PurReqAttachmentDao;
import com.gpcsoft.epp.requirement.domain.PurReqAttachment;

@Repository
public class PurReqAttachmentDaoHibernate extends BaseGenericDaoHibernate<PurReqAttachment> implements PurReqAttachmentDao {

}
