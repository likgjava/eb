package com.gpcsoft.epp.noticemanage.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/** 
  *  Comments: <strong>通知类型</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-6-28 上午09:39:53 by wangcl    					                            
  *  <br/>Modified Date:  2010-6-28 上午09:39:53 by wangcl                                 
  *      
  *  @since 0.5
  *  @version: 0.5 
  */ 
public class NoticeTypeEnum {
	/** 成交通知书 */
	public static final String DEAL_YES ="00";
	/** 结果通知书 */
	public static final String DEAL_NO="01";
	/** 未起草 */
	public static final String NO_DRAFT="00";
	/** 已起草 */
	public static final String ALREADY_DRAFT="01";
	
	public static String getTitle(String type){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME))
		.getDescByValue("es.planform.notice.type", type);
	}
	public static String getResult(String type){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME))
		.getDescByValue("es.planform.notice.result", type);
	}
}
