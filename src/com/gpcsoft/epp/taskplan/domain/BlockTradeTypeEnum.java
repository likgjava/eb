package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 
  *  Comments: <strong>TaskPlanConfirmStatusEnum</strong>大宗交易       		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：yangx    					                            
  *  <br/>Modified Date:  yangx               
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class BlockTradeTypeEnum {

	/**
	 * 录入
	 */
	public final static String RECORD = "1";
	/**
	 * 抽取
	 */
	public final static String RXTRACTION = "2";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue("com.gpcsoft.epp.taskplan.domain.BlockTrade.type", str);
	} 

}
