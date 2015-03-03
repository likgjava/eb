package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/**
 * 
  *  Comments: <strong>TaskPlanConfirmEnum</strong>申报书确认状态       		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-5-13 上午05:43:28 by yemx    					                            
  *  <br/>Modified Date:  2010-5-13 上午05:43:28 by yemx                            
  *  @since 0.4
  *  @version: 0.5
 */
public class TaskPlanDrawTypeEnum {
	
	public final static String TASK_PLAN_DRAW_Type = "taskplandrawtype";
	/**
	 * 随机抽取
	 */
	public final static String RANDOM_SELECT = "00";
	
	/**
	 * 指定选择
	 */
	public final static String APPOINT_SELECT = "01";
}
