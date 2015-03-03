package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.ConRefValueDao;
import com.gpcsoft.epp.eval.domain.ConRefValue;
import com.gpcsoft.epp.eval.manager.ConRefValueManager;

@Repository
public class ConRefValueManagerImpl extends BaseManagerImpl<ConRefValue> implements ConRefValueManager {

	@Autowired(required=true)  @Qualifier("conRefValueDaoHibernate")
	private ConRefValueDao conRefValueDaoHibernate;

}
