package com.gpcsoft.epp.bulletin.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 
  *  Comments: <strong>BulletinConfirmStatusEnum</strong>公告确认状态        		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-5-13 上午05:43:28 by yemx    					                            
  *  <br/>Modified Date:  2010-5-13 上午05:43:28 by yemx                            
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class BulletinConfirmStatusEnum {

	/**
	 * 待确认
	 */
	public final static String WAIT = "00";
	
	/**
	 * 审核中
	 */
	public final static String NEGOTIATE = "01";
	
	/**
	 * 确认
	 */
	public final static String SURE = "02";
	
	/**
	 * 退回
	 */
	public final static String BACK = "04";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue("com.gpcsoft.epp.taskplan.domain.TaskPlan.confirmStatus", str);
	} 
	
	
}
