package com.gpcsoft.agreement.ftl.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class FtlTemplateEnum {
	/**
	 * 模板类型[合同:00,项目:01,公告:02]
	 */
	public final static String FTL_TYPE = "ftlTemplateType";
    public static final String CONTRACT  = "00";
    public static final String PROJECT  = "01";
    public static final String BULLETIN  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getFtlTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(FtlTemplateEnum.FTL_TYPE, str);
    }
    
}
