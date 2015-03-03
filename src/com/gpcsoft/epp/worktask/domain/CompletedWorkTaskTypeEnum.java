package com.gpcsoft.epp.worktask.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>CompletedWorkTaskTypeEnum</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 执行平台    		
  *  <br/>Create Date：2010-7-21 上午10:46:51 by yangx    					                            
  *  <br/>Modified Date:  2010-7-21 上午10:46:51 by yangx                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class CompletedWorkTaskTypeEnum {
	
	public final static String WORKTASK_TYPE = "workTaskType";
	
	/**
	 * 申报书
	 */
	public final static String WORKTASK_TYPE_TASKPLAN = "00";
	/**
	 * 公告
	 */
	public final static String WORKTASK_TYPE_BULLETIN = "01";
	/**
	 * 供应商报名
	 */
	public final static String WORKTASK_TYPE_SIGNUPRECORD = "02";
	/**
	 * 招标文件
	 */
	public final static String WORKTASK_TYPE_PURCHASEDOC= "03";
	/**
	 * 合同
	 */
	public final static String WORKTASK_TYPE_CONTRACT= "04";
	
	/**
	 * 委托协议
	 */
	public final static String WORKTASK_TYPE_CONSIGN= "05";
	
	/**
	 * 招标文件论证结论
	 */
	public final static String WORKTASK_TYPE_PROOFOPINION= "06";
	/**
	 * 邀请函
	 */
	public final static String WORKTASK_TYPE_INVITEROLLREQU= "07";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CompletedWorkTaskTypeEnum.WORKTASK_TYPE, str);
	} 

}
