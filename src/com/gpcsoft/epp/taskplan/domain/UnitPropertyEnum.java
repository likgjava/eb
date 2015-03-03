package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class UnitPropertyEnum {
	
	public final static String RECORD_FORM_UNIT_PRO = "unitProperty";
	/**
	 * 国有
	 */
	public final static String GY = "01";
	
	/**
	 * 民营
	 */
	public final static String MY = "02";
	
	/**
	 * 外商独资
	 */
	public final static String WS = "03";
	
	/**
	 * 中外合资
	 */
	public final static String ZW = "04";
	
	/**
	 * 行政机关
	 */
	public final static String XZJG = "05";
	
	/**
	 * 事业单位
	 */
	public final static String SYDW = "06";
	
	public static String getUnitPropertyCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(UnitPropertyEnum.RECORD_FORM_UNIT_PRO, str);
	}
}
