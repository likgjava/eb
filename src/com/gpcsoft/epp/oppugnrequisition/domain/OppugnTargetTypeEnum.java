package com.gpcsoft.epp.oppugnrequisition.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class OppugnTargetTypeEnum {

	/**
	 * 质疑采购人
	 */
	public final static String OPPUGN_BUYER = "00";
	
	/**
	 * 质疑代理机构
	 */
	public final static String OPPUGN_AGENT = "01";
	
	/**
	 * 质疑采购人和代理机构
	 */
	public final static String OPPUGN_ALL = "02";
	
	
	public final static String OPPU_TYPE = "oppuType";
	
	/**
	 * 采购文件质疑
	 */
	public final static String FILE = "00";
	
	/**
	 * 采购过程质疑
	 */
	public final static String PROCEDURE = "01";
	
	/**
	 * 初定采购结果质疑
	 */
	public final static String RESULT = "02";
	
	/**
	 * 其他类型质疑
	 */
	public final static String OTHER = "03";
	
	public final static String CONSBUYERNAME_TYPE = "consBuyerName";
	
	/**
	 * 采购人
	 */
	public final static String BUYER = "00";
	
	/**
	 * 代理机构
	 */
	public final static String AGENCY = "01";
	
	/**
	 * 两者
	 */
	public final static String THEYTWO = "02";
	
	public final static String REPLY_TYPE = "replyType";
	
	/**
	 * 原始答复
	 */
	public final static String ORIGINAL = "00";
	
	/**
	 * 追加答复
	 */
	public final static String ADDITIONAL = "01";
	
	public static String getOppuTypeCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OppugnTargetTypeEnum.OPPU_TYPE, str);
	}
	
	public static String getConsBuyerNameCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OppugnTargetTypeEnum.CONSBUYERNAME_TYPE, str);
	}
	
	public static String getReplyCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OppugnTargetTypeEnum.REPLY_TYPE, str);
	}
}
