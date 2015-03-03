package com.gpcsoft.smallscale.business.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>商圈会员枚举类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 小额交易平台     		
  *  <br/>Create Date：2010-11-25 下午02:55:59 by yucy    					                            
  *  <br/>Modified Date:  2010-11-25 下午02:55:59 by yucy                                   
  *  <p>@since 0.5
  */ 
public class BusinessMemberEnum {
	/**
	 * 会员时长【1:一年会员， 2:两年会员， 3:三年会员】
	 */
	public final static String MEMBER_TIME_TYPE = "smallscale.business.timeType";
	public static final String ONEYEAR  = "1";
	public static final String TWOYEAR = "2";
	public static final String THREEYEAR = "3";
	public static final String getTimeTypeCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(BusinessMemberEnum.MEMBER_TIME_TYPE, str);
	}
}
