package com.gpcsoft.pubservice.common.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class CommonEnum {
	
	//注册地址枚举key值
	public final static String APP_REGURL = "application.regUrl";
	
	//系统标识符-小额采购
	public final static String XEJY = "xejy";
	
	//系统标识符-项目采购
	public final static String ZTB = "ztb";
	
	/**
	 * 使用状态
	 */
	public final static String USER_STATUS = "bizdata.useStatus";
	public final static String USER_STATUS_DRAEF = "00";
	public final static String USER_STATUS_CONFIRM = "01";
	public final static String USER_STATUS_INVALID = "02";
	
	/**
	 *  确认状态
	 */
	public final static String CONFIRM_STATUS = "bizdata.confirmStatus";
	public final static String CONFIRM_STATUS_WAIT = "00";
	public final static String CONFIRM_STATUS_NEGOTIATE = "01";
	public final static String CONFIRM_STATUS_CANCEL = "02";
	
	/**审核状态*/
	public final static String AUDIT_STATUS = "bizdata.auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";

	/**
	 * 合同类型
	 */
	public final static String CONTRACT_TYPE = "bizdata.contractType";
	public final static String CONTRACT_TYPE_PROJECT = "01";
	public final static String CONTRACT_TYPE_PROTOCAL = "01";
	
	
	/**
	 * bargain中的useStatus
	 */
	public final static String BARGAIN_USE_STATUS ="bizdata.bargainUseStatus";
	public final static String BARGAIN_USE_STATUS_TOSTART = "00";
	public final static String BARGAIN_USE_STATUS_BARGAINING = "01";
	public final static String BARGAIN_USE_STATUS_DONE = "02";
	

	
	public final static String getUseCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.USER_STATUS, str);
	}
	public final static String getConfirmCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.CONFIRM_STATUS, str);
	} 
	public final static String getContractTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.CONTRACT_TYPE, str);
	} 
	public final static String getAuditStatusCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.AUDIT_STATUS, str);
	} 
	public final static String getBargainUseCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.BARGAIN_USE_STATUS, str);
	}
	

}
