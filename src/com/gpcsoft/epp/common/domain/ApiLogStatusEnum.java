package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ApiLogStatusEnum {

	public final static String API_LOG_Status = "apiLogStatus";
	
	/**
	 * 记录日志信息：成功
	 */
	public final static String APILOG_TRUE = "true";

	/**
	 * 记录日志信息：失败
	 */
	public final static String APILOG_FALSE = "false";
	
	/**
	 * 记录日志信息：异常
	 */
	public final static String APILOG_EXCEPTION = "exception";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ApiLogStatusEnum.API_LOG_Status, str);
	}
}
