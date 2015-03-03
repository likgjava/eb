package com.gpcsoft.smallscale.groupbuying.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class GroupBuyingEnum {
	
    /**
	 * 支付方式【00:货到付款，01:网银，02:支付宝】
	 */
	public final static String PAYMENTMETHOD = "smallscale.groupbuying.paymentMethod";
	public static final String COD = "00";
	public static final String PAY_ONLINE = "01";
	public static final String ALIPAY = "02";
	public static final String getPaymentMethodCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(GroupBuyingEnum.PAYMENTMETHOD, str);
	}
	
	/**
	 * 配送方式【00:快递，01:平邮，02:EMS】
	 */
	public final static String SHIPPINGMETHOD = "smallscale.groupbuying.shippingMethod";
	public static final String EXPRESS = "00";
	public static final String NORMAL_POST = "01";
	public static final String EMS = "02";
	public static final String getShippingMethodCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(GroupBuyingEnum.SHIPPINGMETHOD, str);
	}
	
	/**
	 * 支付状态【00:未支付， 01:已支付】
	 */
	public final static String PAY_STATUS = "smallscale.groupbuying.payStatus";
	public static final String NO_PAY  = "00";
	public static final String HAS_PAY  = "01";

	public static final String getPayStatusCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PAY_STATUS, str);
	}
    
}
