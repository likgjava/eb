package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/**
 * 
  *  Comments: <strong>TaskPlanTypeEnum</strong>资金来源       		
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
public class TaskPlanIndSourceEnum {
	
	public final static String TASK_PLAN_IND_SOURCE= "taskplanindsource";
	/**
	 * 年初预算安排
	 */
	public final static String BUDGET = "00";
	
	/**
	 * 上级拨款
	 */
	public final static String APPROPRIATION = "01";
	/**
	 * 预算追加
	 */
	public final static String APPEND = "02";
	
	/**
	 * 其他
	 */
	public final static String OTHER = "03";
	
	public static String getIndSourceCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(TaskPlanIndSourceEnum.TASK_PLAN_IND_SOURCE, str);
	}
}
