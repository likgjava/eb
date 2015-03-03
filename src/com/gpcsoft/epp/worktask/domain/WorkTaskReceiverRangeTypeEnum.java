package com.gpcsoft.epp.worktask.domain;

public class WorkTaskReceiverRangeTypeEnum {
	
	public final static String ALL = "ALL";
    public final static String SPECIFY_RANGE = "SPECIFY_RANGE";
    
    /**
     * 获得中文信息
     * @param status
     * @return
     */
    public static String getWorkTaskReceiverRangeTypeEnumCN(String enumEN) {
    	if(enumEN==null)
    		return "null";
    	else if(WorkTaskReceiverRangeTypeEnum.ALL.equals(enumEN))
    		return "所有有对应权限的人员";
    	else if(WorkTaskReceiverRangeTypeEnum.SPECIFY_RANGE.equals(enumEN))
    		return "特定范围内所有对应权限的人员";
    	
    	return "";
    }
    

}
