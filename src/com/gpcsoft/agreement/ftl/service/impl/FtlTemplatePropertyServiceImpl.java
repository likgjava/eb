package com.gpcsoft.agreement.ftl.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.agreement.ftl.manager.FtlTemplatePropertyManager;
import com.gpcsoft.agreement.ftl.service.FtlTemplatePropertyService;
import com.gpcsoft.agreement.ftl.domain.FtlTemplateProperty;

@Service 
public class FtlTemplatePropertyServiceImpl extends BaseGenericServiceImpl<FtlTemplateProperty> implements FtlTemplatePropertyService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("ftlTemplatePropertyManagerImpl")
	private FtlTemplatePropertyManager ftlTemplatePropertyManager;

}
