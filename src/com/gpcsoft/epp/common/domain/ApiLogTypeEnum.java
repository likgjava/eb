package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ApiLogTypeEnum {
	
	public final static String API_LOG_TYPE = "apiLogType";
	/**
	 * 公告
	 */
	public final static String BULLETIN = "00";
	/**
	 * 代办任务
	 */
	public final static String WAIT_PROC_WORK_TASK = "01";
	/**
	 * 短消息
	 */
	public final static String SHORT_MESSAGE = "02";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ApiLogTypeEnum.API_LOG_TYPE, str);
	}

}
