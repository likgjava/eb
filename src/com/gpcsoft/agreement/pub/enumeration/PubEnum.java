package com.gpcsoft.agreement.pub.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;


public class PubEnum {
	
	/**
	 * 合同确认状态:00:待确认,SURE:01:同意,BACK:02:退回
	 */
	public final static String CONSTRACT_CONFIRM_STATUS = "com.gpcsoft.eps.agreement.pub.domain.Contract.confirmStatus";
	
	public static final String getConfirmStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(CONSTRACT_CONFIRM_STATUS, str);
    }

}
