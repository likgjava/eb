package com.gpcsoft.agreement.ftl.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.ftl.dao.FtlTemplatePropertyDao;
import com.gpcsoft.agreement.ftl.manager.FtlTemplatePropertyManager;
import com.gpcsoft.agreement.ftl.domain.FtlTemplateProperty;

@Repository
public class FtlTemplatePropertyManagerImpl extends BaseManagerImpl<FtlTemplateProperty> implements FtlTemplatePropertyManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("ftlTemplatePropertyDaoHibernate")
	private FtlTemplatePropertyDao ftlTemplatePropertyDaoHibernate;

}
