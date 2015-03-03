package com.gpcsoft.pubservice.application.concern.enumeration;


public class ConcernEnum {
	/**
	 * 关注名单类型[01:我的客户 02:黑名单  03：潜在客户]
	 */
	public final static String CONCERN_GROUP_LIST_TYPE = "listType";
    public static final String SHORT_LIST  = "01";
    public static final String LONG_LIST  = "02";
    public static final String BLACK_LIST  = "03";
    public static final String CLIENT_LIST = "01";
    public static final String HACKER_LIST = "02";
    public static final String POTENTIAL_LIST = "03";
    
    
    /**
     * 分组类型[01:供应商 02:采购人]
     */
    public final static String CONCERN_GROUP_TYPE = "groupType";
	public final static String SUPPLIER_GROUP = "01";
	public final static String BUYER_GROUP = "02";
}
