package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class AuditStatusEnum {

	public final static String AUDIT_STATUS = "es.auditStatus";
	/**
	 * 待审核
	 */
	public final static String WAIT_AUDIT = "00";
	
	/**
	 * 审核通过
	 */
	public final static String AUDIT_PASS = "01";
	
	/**
	 * 审核不通过
	 */
	public final static String AUDIT_NO_PASS = "02";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(AuditStatusEnum.AUDIT_STATUS, str);
	}
	
}
