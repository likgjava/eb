package com.gpcsoft.pubservice.application.template.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class DotTemplateEnum {
	
    /**
	 * 范本类型【00:合同范本，01:采购文件范本】
	 */
	public final static String TEMPLATETYPE = "pubservice.application.template.templateType";
	public static final String CONTRACT_TEMPLATE = "00";
	public static final String BID_FILE_TEMPLATE = "01";
	public static final String getTemplateTypeCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(DotTemplateEnum.TEMPLATETYPE, str);
	}
	
}
