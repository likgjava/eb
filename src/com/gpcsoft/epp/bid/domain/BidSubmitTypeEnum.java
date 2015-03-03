/**
 * 
 */
package com.gpcsoft.epp.bid.domain;

/**
 * 
  *  Comments: <strong>EbuyMethodEnum</strong>投标方式         		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  Create Date: 2010-8-17下午02:56:25 by shenjianzhuang  
  *   Modified Date: 2010-8-17下午02:56:25 by shenjianzhuang 
  *  @since 0.4
  *  @version: 0.5
 */
public class BidSubmitTypeEnum {
	
	public static String BIDSUBMITTYPE = "BidSubmitType";//投标方式
	
	public static String ONLINE_SUBMITTYPE = "00";//网上投标
	
	public static String NOT_ONLINE_SUBMITTYPE = "01";//网下投标
	
	public static String BID_NORMAL = "00";   //正常
	
	public static String BID_UPDATE = "01";   //修改
	
	public static String BID_REMOVE = "02";   //撤销
}
