package com.gpcsoft.pubservice.mydesktop.manager.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.pubservice.mydesktop.dao.ModelIndexDao;
import com.gpcsoft.pubservice.mydesktop.manager.ModelIndexManager;

@Repository
public class ModelIndexManagerImpl extends BaseManagerImpl<GpcBaseObject> implements ModelIndexManager {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("modelIndexDaoJdbc")
	private ModelIndexDao modelIndexDao;

}
