package com.gpcsoft.epp.buyresult.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/** 
  *  Comments: <strong>中标结果类型</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-6-28 上午09:39:53 by wangcl    					                            
  *  <br/>Modified Date:  2010-6-28 上午09:39:53 by wangcl                                 
  *      
  *  @since 0.5
  *  @version: 0.5 
  */ 
public class ResultTypeEnum {
	/** 成交 */
	public static final String DEAL_YES ="00";
	/** 不成交 */
	public static final String DEAL_NO="01";
	
	public static String getResult(String type){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME))
		.getDescByValue("es.planform.buywinner.result", type);
	}
}
