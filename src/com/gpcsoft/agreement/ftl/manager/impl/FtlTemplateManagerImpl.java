package com.gpcsoft.agreement.ftl.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.agreement.ftl.dao.FtlTemplateDao;
import com.gpcsoft.agreement.ftl.manager.FtlTemplateManager;
import com.gpcsoft.agreement.ftl.domain.FtlTemplate;

@Repository
public class FtlTemplateManagerImpl extends BaseManagerImpl<FtlTemplate> implements FtlTemplateManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("ftlTemplateDaoHibernate")
	private FtlTemplateDao ftlTemplateDaoHibernate;

}
