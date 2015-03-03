package com.gpcsoft.epp.worktask.domain;

public class WorkTaskPriorityTypeEnum {

	public final static String LEVEL1 = "1";
    public final static String LEVEL2 = "2";
    public final static String LEVEL3 = "3";
    public final static String LEVEL4 = "4";
    public final static String LEVEL5 = "5";
    
    
    /**
     * 获得中文信息
     * @param status
     * @return
     */
    public static String getWorkTaskPriorityTypeEnumCN(String enumEN) {
    	if(enumEN==null)
    		return "null";
    	else if(WorkTaskPriorityTypeEnum.LEVEL1.equals(enumEN))
    		return "最高";
    	else if(WorkTaskPriorityTypeEnum.LEVEL2.equals(enumEN))
    		return "高";
    	else if(WorkTaskPriorityTypeEnum.LEVEL3.equals(enumEN))
    		return "正常";
    	else if(WorkTaskPriorityTypeEnum.LEVEL4.equals(enumEN))
    		return "低";
    	else if(WorkTaskPriorityTypeEnum.LEVEL5.equals(enumEN))
    		return "最低";
    	return "";
    }
    
    
}
