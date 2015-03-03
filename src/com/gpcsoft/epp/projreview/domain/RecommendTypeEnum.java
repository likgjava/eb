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
public class RecommendTypeEnum {

	public final static String RECOMMEND_TYPE = "recommendType";	
	/**
	 *  推荐
	 */
	public final static String RECOMMEND ="01";
	
	
	/**
	 *  不推荐
	 */
	public final static String NORECOMMEND ="00";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RecommendTypeEnum.RECOMMEND_TYPE, str);
	}
}
