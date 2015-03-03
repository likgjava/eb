package com.gpcsoft.epp.resproject.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ResProjectItemEnum {
	public final static String RESPROJECTITEMENUM = "resProjectItemEnum";

	/**
	 * 监察
	 */
	public final static String JC = "01";
	/**
	 * 设计
	 */
	public final static String SJ = "02";
	/**
	 * 监理
	 */
	public final static String JL = "03";
	/**
	 * 施工
	 */
	public final static String SG = "04";
	
	/**
	 * 装饰装修
	 */
	public final static String ZSZX = "05";
	
	/**
	 * 园林绿化
	 */
	public final static String YLLH = "06";
	
	/**
	 * 重要设备采购
	 */
	public final static String ZYSBCG = "07";
	
	/**
	 * 重要材料采购
	 */
	public final static String ZYCLCG = "08";
	/**
	 * 
	 * Description :  
	 * Create Date: 2011-12-15下午07:24:14 by chenhongjun  
	 * Modified Date: 2011-12-15下午07:24:14 by chenhongjun
	 *@param str
	 *@return
	 *下午07:24:14 
	 *String
	 */
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ResProjectItemEnum.RESPROJECTITEMENUM, str);
	} 
	
	

}
