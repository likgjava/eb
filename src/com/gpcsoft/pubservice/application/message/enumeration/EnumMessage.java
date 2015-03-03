package com.gpcsoft.pubservice.application.message.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class EnumMessage {
	
	
	
	/**
	 * 站内信类型 (系统站内信：00  私人站内信：01)
	 */
	public final static String SYSTEM_MESSAGE = "00";
	public final static String PRIVATE_MESSAGE = "01";
	
	public final static String SIGNUP_AUDIT_MESSAGE = "signup.audit.message.template";
	
	/**
	 * 类型 (普通留言：00)
	 */
	public final static String TYPE_NOTE = "00";
	
	/**
	 * 文件和保证金支付模板
	 */
	public final static String DOC_PAY = "signup.doc.pay";
	public final static String BAIL_PAY = "signup.bail.pay";
	
	/**
	 * 类型 (客服咨询：01)
	 */
	public final static String TYPE_CONSULATION = "01";
	
	
	/**
	 * 留言/咨询   回复状态(0:没有回复   1：已经回复)
	 */
	public static final String IS_REPLY_YES="1";
	public static final String IS_REPLY_NO="0";
	public final static String IS_REPLY_STATUS = "Note.isReply";
	public static final String getReplyCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EnumMessage.IS_REPLY_STATUS, str);
    }
	
	/**
	 * 留言/咨询  阅读状态(0:未阅读   1：已阅读)
	 */
	public final static String IS_READ_YES = "1";
	public final static String IS_READ_NO = "0";	
	public final static String IS_READ_STATUS = "Note.isRead";
	public static final String getReadCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EnumMessage.IS_READ_STATUS, str);
	}
}
