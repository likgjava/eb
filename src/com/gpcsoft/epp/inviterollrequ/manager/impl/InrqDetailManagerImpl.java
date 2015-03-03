package com.gpcsoft.epp.inviterollrequ.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.inviterollrequ.dao.InrqDetailDao;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.manager.InrqDetailManager;

@Repository
public class InrqDetailManagerImpl extends BaseManagerImpl<InrqDetail> implements InrqDetailManager {

	@Autowired(required=true)  @Qualifier("inrqDetailDaoHibernate")
	private InrqDetailDao inrqDetailDaoHibernate;

}
