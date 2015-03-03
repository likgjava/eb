package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>ProjAdviceProcWayEnum</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-10-11 下午03:29:17 by yangx    					                            
  *  <br/>Modified Date:  2010-10-11 下午03:29:17 by yangx                                   
  *	  @since 0.5
  *   @version: 0.5 
  */ 
public class ProjExceptionApplyEnum {

	public final static String PROJ_ADVICE_PROC_WAY = "projAdviceProcWay";

	/**
	 * 重新招标
	 */
	public final static String REPURVHASE = "01";
	
	/**
	 * 变更采购方式
	 */
	public final static String CHANGEEBUYMETHOD = "02";
	
	/**
	 * 终止项目
	 */
	public final static String STOPPROJECT = "03";
	
	/**
	 * 是重新立项
	 */
	public final static String ISRECREATEPROJECT = "04";
	
	/**
	 * 否重新立项
	 */
	public final static String NORECREATEPROJECT = "05";
	
	
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjExceptionApplyEnum.PROJ_ADVICE_PROC_WAY, str);
	} 
	
}
