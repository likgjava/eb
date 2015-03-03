package com.gpcsoft.epp.oppugnrequisition.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.oppugnrequisition.dao.OppugnReplyDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.manager.OppugnReplyManager;

@Repository
public class OppugnReplyManagerImpl extends BaseManagerImpl<OppugnReply> implements OppugnReplyManager {

	@Autowired(required=true)  @Qualifier("oppugnReplyDaoHibernate")
	private OppugnReplyDao oppugnReplyDaoHibernate;

	/**
	 * 根据质疑获取回复
	 */
	public List<OppugnReply> getReplyByOppId(String oppId) throws Exception {
		return oppugnReplyDaoHibernate.getReplyByOppId(oppId);
	}
}
