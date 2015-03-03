package com.gpcsoft.epp.project.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class ProjectEnum {
	public final static String PROJECTENUM = "projectEnum";

	/**
	 * 政府采购
	 */
	public final static String ZFCG = "01";
	/**
	 * 土地交易
	 */
	public final static String TDJY = "02";
	/**
	 * 产权交易
	 */
	public final static String CQJY = "03";
	/**
	 * 建筑工程
	 */
	public final static String JZGC = "04";
	
	/**
	 * 代理机构比选
	 */
	public final static String DLBX="05";
	
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
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(ProjectEnum.PROJECTENUM, str);
	} 
	
	

}
