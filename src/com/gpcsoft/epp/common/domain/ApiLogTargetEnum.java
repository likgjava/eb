package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ApiLogTargetEnum {

	public final static String API_LOG_Target = "apiLogTarget";

	/**
	 * 青岛门户网站
	 */
	public final static String QINGDAO_PORTAL = "00";
	
	/**
	 * 财政网
	 */
	public final static String QINGDAO_EXT = "01";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ApiLogTargetEnum.API_LOG_Target, str);
	}
}
