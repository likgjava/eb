package com.gpcsoft.epp.inviterollrequ.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class InrqAuditStatusEnum {

public final static String INRQ_AUDIT_STATUS = "es_inrqAuditStatus";

public final static String  WAIT_AUDIT = "00";  //待提交

public final static String AUDITING = "01";  //待审核

public final static String AUDIT_PASS = "02";  //审核通过

public final static String AUDIT_NO_PASS = "03";  //审核不通过

public static String getCN(String str){
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(InrqAuditStatusEnum.INRQ_AUDIT_STATUS, str);
} 

}
