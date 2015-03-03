package com.gpcsoft.epp.purchasedoc.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class DosBuyTypeEnum {
	/**
	 * 招标文件购买状态
	 */
public final static String DOS_BUY_TYPE = "dosBuyType";	
	
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
	//return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue("com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc.auditStatus", str);
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(DosBuyTypeEnum.DOS_BUY_TYPE, str);
} 
}
