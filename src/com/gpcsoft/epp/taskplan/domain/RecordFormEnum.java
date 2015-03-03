package com.gpcsoft.epp.taskplan.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class RecordFormEnum {
	public final static String RECFORMBUYMETHOD = "epp.RecordForm.recFormBuyMethod";

	public static String getRecFormBuyMethodCn(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RecordFormEnum.RECFORMBUYMETHOD, str);
	} 
}
