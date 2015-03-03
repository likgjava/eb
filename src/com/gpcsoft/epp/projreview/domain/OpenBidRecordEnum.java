package com.gpcsoft.epp.projreview.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 
  *  Comments: <strong>RecommendTypeEnum</strong>是否推荐类型
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
public class OpenBidRecordEnum {

	public final static String OPEN_BR_STATUS = "recommendType";	
	/**
	 *  未开标
	 */
	public final static String NO_OPENBID ="00";
	
	
	/**
	 *  开标成功
	 */
	public final static String OPENBID_SUCCESS ="01";
	
	
	/**
	 *  开标失败
	 */
	public final static String OPENBID_ERROR ="02";
	
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RecommendTypeEnum.RECOMMEND_TYPE, str);
	}
}
