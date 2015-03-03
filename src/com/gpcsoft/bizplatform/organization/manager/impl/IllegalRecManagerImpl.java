package com.gpcsoft.bizplatform.organization.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.IllegalRecDao;
import com.gpcsoft.bizplatform.organization.domain.IllegalRec;
import com.gpcsoft.bizplatform.organization.manager.IllegalRecManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;


@Repository
public class IllegalRecManagerImpl extends BaseManagerImpl<IllegalRec> implements IllegalRecManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("illegalRecDaoHibernate")
	private IllegalRecDao illegalRecDaoHibernate;

}
