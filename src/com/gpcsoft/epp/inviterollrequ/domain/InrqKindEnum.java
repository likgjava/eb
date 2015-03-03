package com.gpcsoft.epp.inviterollrequ.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class InrqKindEnum {

public final static String INRQ_KIND = "es_inrqKind";	

public final static String NORMAL = "00";  //正常函

public final static String WITHDRAW = "01";  //撤销函

public static String getCN(String str){
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(InrqKindEnum.INRQ_KIND, str);
} 
}
