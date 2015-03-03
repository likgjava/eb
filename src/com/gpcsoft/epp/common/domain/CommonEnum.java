package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class CommonEnum {
	
		//系统识别为ztb
	public final static String _SYS_FLAG = "ztb";
	
	public final static String CONFIRM_STATUS = "confirmStatus";
	/**
	 * 待确认
	 */
	public final static String CONFIRM_STATUS_WAIT = "00";
	
	/**
	 * 审核中
	 */
	public final static String CONFIRM_STATUS_NEGOTIATE = "01";
	
	/**
	 * 确认
	 */
	public final static String CONFIRM_STATUS_SURE = "02";
	
	/**
	 * 统评处审核
	 */
	public final static String CONFIRM_STATUS_EXERCISE = "03";
	
	
	/**
	 * 退回
	 */
	public final static String CONFIRM_STATUS_BACK = "04";
	
	public final static String USER_STATUS = "useStatus";
	/**
	 * 临时
	 */
	public final static String USER_STATUS_TEMP = "00";
	
	/**
	 * 正式
	 */
	public final static String USER_STATUS_FORMAL = "01";
	
	/**
	 * 作废
	 */
	public final static String USER_STATUS_CANCEL = "02";
	
	public final static String PUBLIC_STATUS =  "relStatus";
	
	/**
	 * 未发布
	 */
	public final static String PUBLIC_STATUS_WAIT = "00";
	
	/**
	 * 已发布
	 */
	public final static String PUBLIC_STATUS_PUBLIC = "01";
	
	/**
	 * 撤回
	 */
	public final static String PUBLIC_STATUS_RETURN = "02";
	
	/*定义上传文件类型的取值*/
	public static final String FILE_TYPE_REQUIRE = "Require";//采购需求
	public static final String FILE_TYPE_PURCHASE = "Purchase";//采购文件
	public static final String FILE_TYPE_PURCHASE_BUSINESS = "business";//采购文件-商务标
	public static final String FILE_TYPE_PURCHASE_TECHNICAL = "technical";//采购文件-技术标
	public static final String FILE_TYPE_PURCHASE_BUSINESS_STATUS = "00";//采购文件-商务标状态
	public static final String FILE_TYPE_PURCHASE_TECHNICAL_STATUS = "01";//采购文件-技术标状态
	public static final String FILE_TYPE_BID = "Bid";//投标
	public static final String FILE_TYPE_BID_BUSINESS="business";//投标文件商务部分
	public static final String FILE_TYPE_BID_TECHNICAL="technical";//投标文件商务部分
	public static final String FILE_TYPE_TEMPLATES = "Templates";
	public static final String FILE_TYPE_ORGINFO = "OrgInfo";
	public static final String FILE_TYPE_QUALIZATION = "Qualization";
	public static final String FILE_TYPE_RECEIPT = "Receipt"; //回执
	
	
	public static final String GPCSOFT_FILE_TYPE = "ebd";
	//文件路径
	public final static String PUR_FILE_DIR = "purFile";//采购文件夹名称
	
	public final static String BID_FILE_DIR = "bidFile";//投标文件夹名称
	
	public static String getUseCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.USER_STATUS, str);
	}
	
	public static String getConfirmCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.CONFIRM_STATUS, str);
	} 

	public static String getPublicStatusCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CommonEnum.PUBLIC_STATUS, str);
	} 
}
