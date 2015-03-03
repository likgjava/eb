package com.gpcsoft.epp.signuprecord.domain;

public class SignUprecordEnum {
	/** 有报名信息标志 */
	public final static String SIGNUP_RECORD_FLAG = "01";
	/** 无报名信息标志 */
	public final static String NO_SIGNUP_RECORD_FLAG = "02";
	/** 可以报名标志 */
	public final static String CAN_SIGNUP_RECORD = "01";
	/** 不可以报名标志 */
	public final static String CAN_NOT_SIGNUP_RECORD = "02";
	/**未报名*/
	public final static String NO_SIGNUP_RECORD = "00";  
	/**报名审核中*/
	public final static String SIGNUP_RECORD_FOR_AUDIT = "01";   
	/**报名审核通过,待交标书费*/
	public final static String SIGNUP_RECORD_FOR_PAY = "02";   
	/**报名成功*/
	public final static String SIGNUP_RECORD_SUCCESS = "09";   
}
