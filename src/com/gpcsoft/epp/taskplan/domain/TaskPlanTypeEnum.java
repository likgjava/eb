package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/**
 * 
  *  Comments: <strong>TaskPlanTypeEnum</strong>申报书类型       		
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
public class TaskPlanTypeEnum {
	
	public final static String TASK_PLAN_TYPE = "taskplantype";
	/**
	 * 普通
	 */
	public final static String NORMAL = "00";
	
	/**
	 * 大宗交易
	 */
	public final static String BLOCK_TRADE = "01";
	
	/**
	 * 一级采购人
	 */
	public final static String BUYER_ONE = "A";
	/**
	 * 二级采购人
	 */
	public final static String BUYER_TWO = "B";
	
	/**
	 * 政府采购
	 */
	public final static String ZFCG = "01";
	

	/**
	 * 土地交易
	 */
	public final static String TDJY = "02";
	
	
	/**
	 * 产权交易
	 */
	public final static String CQJY = "03";
	
	
	/**
	 * 建筑工程
	 */
	public final static String JZGC = "04";
	
	/**
	 * 代理机构比选
	 */
	public final static String DLBX = "05";
	
	public static String getTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(TaskPlanTypeEnum.TASK_PLAN_TYPE, str);
	}
}
