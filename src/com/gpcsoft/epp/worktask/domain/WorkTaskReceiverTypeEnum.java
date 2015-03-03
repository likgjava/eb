package com.gpcsoft.epp.worktask.domain;

public class WorkTaskReceiverTypeEnum {


	public final static String ALL = "ALL";
	public final static String ALL_VALUE = "-1";
    public final static String ORG = "ORG";
    public final static String UNIT = "UNIT";
    public final static String ROLE = "ROLE";
    public final static String USER = "USER";
    
    
    /**
     * 获得中文信息
     * @param status
     * @return
     */
    public static String getWorkTaskReceiverTypeEnumCN(String enumEN) {
    	if(enumEN==null)
    		return "null";
    	else if(WorkTaskReceiverTypeEnum.ALL.equals(enumEN))
    		return "所有";
    	else if(WorkTaskReceiverTypeEnum.ORG.equals(enumEN))
    		return "机构";
    	else if(WorkTaskReceiverTypeEnum.UNIT.equals(enumEN))
    		return "组织";
    	else if(WorkTaskReceiverTypeEnum.ROLE.equals(enumEN))
    		return "角色";
    	else if(WorkTaskReceiverTypeEnum.USER.equals(enumEN))
    		return "用户";
    	
    	return "";
    }
    
    
}
