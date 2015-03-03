
package com.gpcsoft.epp.eval.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
 * @Description
 * @version V1.0
 * @Create Date 2010-8-2 上午10:19:03 By wanghz   
 */
public class CongruousFactorValueDataTypeEnum {
	public final static String CONGRUOUS_FACTOR_VALUE_DATA_TYPE = "congruousFactorValueDataType";
	public final static String STRING_TYPE = "00";
	public final static String ENUMERATER_TYPE = "01";
	public final static String LIST_TYPE = "02";
	public final static String NUMBER_TYPE = "03";
	public final static String BOOLEAN_TYPE = "04";
	public final static String DATE_TYPE = "05";
	public final static String OTHER_TYPE = "06";
	
	public static String getCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CongruousFactorValueDataTypeEnum.CONGRUOUS_FACTOR_VALUE_DATA_TYPE, str);
	} 
}
