package com.gpcsoft.agreement.bargin.requirement.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>采购需求枚举</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-3-30 上午10:31:37 by yucy    					                            
  *  <br/>Modified Date:  2011-3-30 上午10:31:37 by yucy                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class RequirementEnum {

	/**
	 * 审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String AUDITSTATUS = "com.gpcsoft.eps.agreement.requirement.auditstatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RequirementEnum.AUDITSTATUS, str);
    }
    
    /**
     * 使用状态[临时:00,有效:01,无效:02]
     */
    public final static String USESTATUS = "com.gpcsoft.eps.agreement.requirement.usestatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RequirementEnum.USESTATUS, str);
    }
    
    /**
     * 发布状态[未发布:00,已发布:01]
     */
    public final static String PUBLISHEDSTATUS = "com.gpcsoft.eps.agreement.requirement.pubstatus";

	public final static String UNPUBLISHED = "00";
	public final static String PUBLISHED = "01";
	public static final String getPubStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RequirementEnum.PUBLISHEDSTATUS, str);
    }
}
