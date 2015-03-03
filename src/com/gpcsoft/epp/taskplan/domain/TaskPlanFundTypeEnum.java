package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/**
 * 
  *  Comments: <strong>TaskPlanConfirmStatusEnum</strong>资金来源        		
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
public class TaskPlanFundTypeEnum {
	public final static String TASK_PLAN_FUND_TYPE= "taskplanfundtype";
	
	/**
	 * 计划内
	 */
	public final static String PROGRAM = "00";
	
	/**
	 * 计划外
	 */
	public final static String SCHEME = "01";
	
	public static String getFundTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(TaskPlanFundTypeEnum.TASK_PLAN_FUND_TYPE, str);
	}
}
