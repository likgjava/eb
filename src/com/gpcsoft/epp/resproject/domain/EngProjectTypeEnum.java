package com.gpcsoft.epp.resproject.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class EngProjectTypeEnum {

	public final static String ENG_PROJECT_TYPE = "engProjType";
	
	
	public final static String PROJECT_NATURE="projectNature";
	
	/**
	 * 房屋建筑工程
	 */
	public final static String FW = "00";

	/**
	 * 市政公用设施
	 */
	public final static String SZ = "01";
	
	
	//00:新建、01:扩建、02:改建)
	
	public final static String PROJECT_NATURE_NEW="00";//新建
	
	public final static String PROJECT_NATURE_EXT="01";//扩建
	
	public final static String PROJECT_NATURE_MODIFY="02";//改建
	
	public final static String getProjectNatureCn(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EngProjectTypeEnum.PROJECT_NATURE, str);
	} 
	
	public static String getEngProjectTypeCn(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(EngProjectTypeEnum.ENG_PROJECT_TYPE, str);
	} 
}
