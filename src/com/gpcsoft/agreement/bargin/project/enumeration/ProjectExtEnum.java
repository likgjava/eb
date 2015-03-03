package com.gpcsoft.agreement.bargin.project.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ProjectExtEnum 
{
	/**
     * 交货方式[00:卖家发货, 01:上门取货, 02:第三方物流]
     */
    public final static String DELIVERY_TYPE = "biz.project.deliveryType";
    public final static String SELLER_DELIVERY = "01";
    public final static String DOOR_TO_DOOR = "012";
    public final static String THIRD_PART_DELIVERY = "03";
    
	public static String getDeliveryCN(String str)
	{
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjectExtEnum.DELIVERY_TYPE, str);
	}
	
	/**
     * 支付方式[00:卖家发货, 01:上门取货, 02:第三方物流]
     */
    public final static String PAY_TYPE = "biz.project.payType";
    public final static String TRANSFER  = "01";//转账汇款
    public final static String CASH = "02";//现金交易
    
	public static String getPayTypeCN(String str)
	{
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjectExtEnum.PAY_TYPE, str);
	}
}
