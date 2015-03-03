package com.gpcsoft.agreement.ftl.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.agreement.ftl.manager.FtlTemplateManager;
import com.gpcsoft.agreement.ftl.service.FtlTemplateService;
import com.gpcsoft.agreement.ftl.dao.FtlTemplateDao;
import com.gpcsoft.agreement.ftl.domain.FtlTemplate;

@Service 
public class FtlTemplateServiceImpl extends BaseGenericServiceImpl<FtlTemplate> implements FtlTemplateService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("ftlTemplateManagerImpl")
	private FtlTemplateManager ftlTemplateManager;

	@Autowired(required=true)  @Qualifier("ftlTemplateDaoHibernate")
	private FtlTemplateDao ftlTemplateDaoHibernate;
	
	/** 
	 * Description : 验证当前用户下的模板是不是唯一的
	 * Create Date: 2010-12-2下午03:49:37 by guoyr  Modified Date: 2010-12-2下午03:49:37 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String ftlName, String objId, String createUserId ){
		return ftlTemplateDaoHibernate.isUnique(ftlName, objId, createUserId);
	}
}
