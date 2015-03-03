package com.gpcsoft.epp.common.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


/** 
 * @Description: 指标分类 枚举
 * @version V1.0
 * @Create Date 2010-8-10 上午10:01:24 By wanghz   
 */
public class FactortypeDeEnum{

	public final static String FactortypeDeEnum = "factorTypeEnum";
	
	public final static String FACTOR_TYPE_COMMON = "0";// 通用
	public final static String FACTOR_TYPE_INDUSTRY = "1";// 行业
	public static String getCN(Character factorType){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(FactortypeDeEnum,factorType);
	}
}
