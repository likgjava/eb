package com.gpcsoft.epp.workgroup.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 
  *  Comments: <strong>SigninEnum</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-29 上午10:29:33 by liuke   					                            
  *  <br/>Modified Date:  2010-7-29 上午10:29:33 by liuke                                 
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class SigninTypeEnum {
	public final static String SIGNIN_STATUS = "signinStatus";
	
	/** 未签到 */
	public static final String SIGNIN_NO ="00";
	/** 已签到 */
	public static final String SIGNIN_YES="01";
	/** 正选专家 */
	public static final String IS_AMOUN="02";
	/** 备选专家 */
	public static final String IS_NOTAMOUN="03";
	
	
	
	public static String getCN(String str){
		//return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue("com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc.auditStatus", str);
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SigninTypeEnum.SIGNIN_STATUS, str);
	} 	
}
