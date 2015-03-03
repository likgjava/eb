package com.gpcsoft.epp.taskplan.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.taskplan.dao.RecordFormDao;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.manager.RecordFormManager;

@Repository
public class RecordFormManagerImpl extends BaseManagerImpl<RecordForm> implements RecordFormManager {

	@Autowired(required=true)  @Qualifier("recordFormDaoHibernate")
	private RecordFormDao recordFormDaoHibernate;

}
