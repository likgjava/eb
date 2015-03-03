package com.gpcsoft.agreement.order.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: 流程枚举
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-7-22 下午02:26:52 by sunl    					                            
  *  <br/>Modified Date:  2010-7-22 下午02:26:52 by sunl                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class OrderEnum {
	
	/**
	 * 支付状态【00:未支付， 01:已支付】
	 */
	public final static String PAY_STATUS = "com.gpcsoft.eps.agreement.order.domain.Order.payStatus";
	public static final String NO_PAY  = "00";
	public static final String HAS_PAY  = "01";

	public static final String getPayStatusCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PAY_STATUS, str);
	}
}
