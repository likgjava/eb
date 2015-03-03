package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>项目实施状态</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-15 上午11:02:28 by liuy    					                            
  *  <br/>Modified Date:  2010-7-15 上午11:02:28 by liuy  
  */ 
public class ProjImplStatusEnum {
	public final static String PROJ_IMPL_STATUS = "projImplStatus";

	/**
	 * 正常
	 */
	public final static String NORMAL = "00";
	
	/**
	 * 暂停
	 */
	public final static String SUSPEND = "01";
	
	/**
	 * 终止
	 */
	public final static String STOP = "02";
	
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjImplStatusEnum.PROJ_IMPL_STATUS, str);
	} 
	

}
