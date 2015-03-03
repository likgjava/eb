package com.gpcsoft.epp.bid.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class BailRecordTypeEnum {
	/**
	 * 招标文件购买状态
	 */
public final static String BAIL_RECORD_TYPE = "bailRecordType";	
	
/**
 * 购买申请
 */
public final static String ONLY_APPLICATION = "00";

/**
 * 支付
 */
public final static String ALREADY_PAY = "01";

/**
 * 汇票
 */
public static final String Bill_Of_Exchange = "01";
/**
 * 支票
 */
public static final String Check = "02";
/**
 * 电汇
 */
public static final String Telegraphic_Transfer = "03";
/**
 * 现金
 */
public static final String CASH = "04";
/**
 * 其他
 */
public static final String OTHER = "05";

public static String getCN(String str){
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(BailRecordTypeEnum.BAIL_RECORD_TYPE, str);
} 
}
