package com.gpcsoft.epp.bid.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class BailStatusCNEnum {

public final static String BAIL_STATUS = "es_bailStatus";//保证金记录状态00：未收；01：已收；02：已退 ;03 已结转

public final static String NO_RECEIVE = "00";//未收

public final static String ALREADY_RECEIVE = "01";//已收

public final static String ALREADY_BACK = "02";//已退

public final static String TRANSFER = "03";//已结转

public static String getCN(String str){
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(BailStatusCNEnum.BAIL_STATUS, str);
} 	
}
