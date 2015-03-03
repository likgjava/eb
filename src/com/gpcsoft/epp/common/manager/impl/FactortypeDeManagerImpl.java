package com.gpcsoft.epp.common.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.dao.FactortypeDeDao;
import com.gpcsoft.epp.common.domain.FactortypeDe;
import com.gpcsoft.epp.common.manager.FactortypeDeManager;

@Repository
public class FactortypeDeManagerImpl extends BaseManagerImpl<FactortypeDe> implements FactortypeDeManager {

	@Autowired(required=true)  @Qualifier("factortypeDeDaoHibernate")
	private FactortypeDeDao factortypeDeDaoHibernate;

}
