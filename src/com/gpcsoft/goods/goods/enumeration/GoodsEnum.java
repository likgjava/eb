package com.gpcsoft.goods.goods.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class GoodsEnum {
	/**
	 * 商品审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String GOODS_STATUS = "auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(GoodsEnum.GOODS_STATUS, str);
    }
    
    /**
     * 使用状态[临时:00,有效:01,报废:02]
     */
    public final static String GOODS_USE_STATUS = "useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(GoodsEnum.GOODS_USE_STATUS, str);
    }
    
    /**
     * 启卖禁卖[起卖:01,禁卖:02]
     */
    public final static String GOODS_SELL_STATUS = "sellStatus";
    public static final String SELL_START  = "01";
    public static final String SELL_STOP  = "02";
    public static final String getSellStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(GoodsEnum.GOODS_SELL_STATUS, str);
    }
    
    /**
     * 商品的几个是否属性(目前其中文显示名称先定义为是和否)
     */
    public final static String YES="1";
    public final static String NO="0";
    public static final String getGoodsBooleanCN(String propertyName,String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(propertyName, str);
    }
}
