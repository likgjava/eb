package com.gpcsoft.epp.buyresult.domain;


/**
 * 
  *  Comments: <strong>ConfirmResultEnum</strong>确认结果            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-5-17 上午04:57:21 by yemx    					                            
  *  <br/>Modified Date:  2010-5-17 上午04:57:21 by yemx                                 
  *      
  *  @since 0.4
  *  @version: 0.5
 */
public class ConfirmResultEnum {
	
	/**
	 * 无人参与
	 */
	public final static String NO_PARTICIPATOR = "00";
	
	/**
	 * 没有足够参与者
	 */
	public final static String NO_ENOUGH_PARTICIPATOR = "01";

	/**
	 * 选定
	 */
	public final static String CONFIRM = "02";
	
	/**
	 * 放弃
	 */
	public final static String GIVEUP = "03";
	
	
	public static String getConfirmResultCN(String str){
		if(ConfirmResultEnum.NO_PARTICIPATOR.equals(str)){
			return "无人参与";
		}else if(ConfirmResultEnum.NO_ENOUGH_PARTICIPATOR.equals(str)){
			return "没有足够参与者";
		}else if(ConfirmResultEnum.CONFIRM.equals(str)){
			return "选定";
		}else if(ConfirmResultEnum.GIVEUP.equals(str)){
			return "放弃";
		}else{
			return "未确定结果";
		}
	} 
	
}
