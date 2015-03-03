package com.gpcsoft.pubservice.application.advertisement.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class AdvertisingMessage {
	
	/**
	 * 广告文件类型(00.图片文件,01.flash文件 02.跑马灯)
	 */
	public final static String ADVERTISING_FILE_TYPE = "advertising.file.type.adverType";
	public final static String ADVERTYPE_PICTURE = "00";
	public final static String ADVERTYPE_FLASH = "01";
	public final static String ADVERTYPE_PAOMADENG = "02";
	public static final String getAdverTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(AdvertisingMessage.ADVERTISING_FILE_TYPE, str);
	}
	
    /**
     * 使用状态[临时:00,有效:01,报废:02]
     */
    public final static String ADVERTISING_USE_STATUS = "useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(AdvertisingMessage.ADVERTISING_USE_STATUS, str);
    }
    
	/**
	 * 审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String ADVERTISING_AUDIT_STATUS = "auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(AdvertisingMessage.ADVERTISING_AUDIT_STATUS, str);
    }
}