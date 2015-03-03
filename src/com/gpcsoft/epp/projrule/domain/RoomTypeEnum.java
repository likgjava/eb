/**
 * 
 */
package com.gpcsoft.epp.projrule.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * @author Administrator
 *
 */
public class RoomTypeEnum {
public final static String ROOM_TYPE = "roomtype";
	
public final static String OPEN_BID_ROOM = "00";	 //开标室

public final static String BID_ROOM = "01";          //评标室


public static String getRoomTypeCN(String str){
	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(RoomTypeEnum.ROOM_TYPE, str);
}
}
