package com.gpcsoft.epp.worktask.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
 *  Comments: <strong>WorkTaskResultEnum</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   agreement                    					          
 *  <br/>Module ID: 执行平台    		
 *  <br/>Create Date：2010-7-21 下午12:55:06 by yangx    					                            
 *  <br/>Modified Date:  2010-7-21 下午12:55:06 by yangx                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
public class CompletedWorkTaskResultEnum {
	
	public final static String WORKTASK_RESULT = "workTaskResult";
	
	/**
	 * 审核通过
	 */
	public final static String WORKTASK_PASSAUDIT = "00";
	/**
	 * 审核不通过
	 */
	public final static String WORKTASK_NOPASSAUDIT = "01";
	/**
	 * 完成
	 */
	public final static String WORKTASK_SAVE = "02";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CompletedWorkTaskResultEnum.WORKTASK_RESULT, str);
	} 
}
