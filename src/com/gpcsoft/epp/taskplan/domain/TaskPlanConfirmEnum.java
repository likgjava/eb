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
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class TaskPlanConfirmEnum {
	
	public final static String TASK_PLAN_CONFIRM = "taskplanconfirm";
	/**
	 * 待审核
	 */
	public final static String WAIT_AUDIT = "00";
	
	/**
	 * 审核中
	 */
	public final static String AUDITING = "01";
	
	/**
	 * 审核通过
	 */
	public final static String AUDIT_PASS = "02";
	
	
	/**
	 * 待统评处审核
	 */
	public final static String AUDIT_FOR_TPC = "03";
	
	/**
	 * 审核不通过
	 */
	public final static String AUDIT_NO_PASS = "04";
	
	/**
	 * 抽取代理机构不通过
	 */
	public final static String SELECT_AGENT_NOPASS = "05";
	
	/**
	 * 抽取代理机构通过
	 */
	public final static String SELECT_AGENT_PASS = "06";
	
	
	/**
	 * 审核抽取代理机构
	 */
	public final static String SELECT_AGENT = "07";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(TaskPlanConfirmEnum.TASK_PLAN_CONFIRM, str);
	}
	
	/**
	 * 申报书进程状态
	 */
	public final static String TASKPLAN_PROCESS_STATUS = "taskplanProcessStatus";
	public final static String TASKPLAN_NOT_BEGAIN = "00";//未开始
	public final static String TASKPLAN_PROGRESSING = "01";//进行中
	public final static String TASKPLAN_DOWN = "02";//已完成
	
	public static String getTaskplanProcessStatusCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(TaskPlanConfirmEnum.TASKPLAN_PROCESS_STATUS, str);
	}
}
