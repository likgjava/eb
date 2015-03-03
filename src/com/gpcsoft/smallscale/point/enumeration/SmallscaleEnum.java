package com.gpcsoft.smallscale.point.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/**
 * 积分管理常量
 * @author Administrator
 *
 */
public class SmallscaleEnum {
	/**
	 * 积分获取方式 0 购买积分 1 交易积分 3 推荐采购人 4 交换积分 5礼券积分
	 */
	public final static String EXCHANGE_STATUS = "com.gpcsoft.smallscale.point.domain.Exchange.exchangeType";
	public static final String EXCHANGE_TYPE_BUY="0"; 
	public static final String EXCHANGE_TYPE_DEAL="1"; 
	public static final String EXCHANGE_TYPE_HORTATION="3"; 
	public static final String EXCHANGE_TYPE_EXECHANGE="4"; 
	public static final String EXCHANGE_TYPE_GIF="5"; 
	public static final String getExchangeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.EXCHANGE_STATUS, str);
    }
	
	//积分来源： 00注册,01供应商报名,02 发布采购公告,03 推荐采购人
	public static final String EXCHANGE_SOURCE_REG = "00";
	public static final String EXCHANGE_SOURCE_BID = "01";
	public static final String EXCHANGE_SOURCE_BUL = "02";
	public static final String EXCHANGE_SOURCE_PRO = "03";
	
	/**
	 * 积分状态 0-无效 1-有效
	 */
	public final static String CURRENT_STATUS = "com.gpcsoft.smallscale.point.domain.Exchange.currentStatus";
	public static final String CURRENT_STATUS_NO="0"; 
	public static final String CURRENT_STATUS_YES="1"; 
	
	public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.CURRENT_STATUS, str);
    }
	
	/**
	 * 消费类型 0 参与采购 1罚分
	 */
	public final static String CONSUME_TYPE = "com.gpcsoft.smallscale.point.domain.Consume.consumeType";
	public static final String CONSUME_TYPE_YES="0";
	public static final String CONSUME_TYPE_NO="1";
	public static final String CURRENT_TYPE_GIFT="2";
	public static final String getConsumeTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.CONSUME_TYPE, str);
    }
	
	/**
	* 推广人录入类型（00 采购人记录 01推广人记录 02管理员）
	*/
	public static final String RECORD_TYPE_BUYER = "00";
	public static final String RECORD_TYPE_PROMOTER = "01";
	public static final String RECORD_TYPE_ADMIN = "02";
	
	/**
	* 推广人类型（ 1 友善大使 2诚信大使 3爱心大使'）
	*/
	public static final String PROMOTER_TYPE_F = "1";
	public static final String PROMOTER_TYPE_B = "2";
	public static final String PROMOTER_TYPE_L = "3";

	
	
	/**推广人处理状态 00 未处理 01已处理*/
	public static final String PROMOTER_STATUS_NEW = "00";
	public static final String PROMOTER_STATUS_DEAL = "01";
	
	/**礼品处理状态 00 未处理 01已处理*/
	public static final String DEAL_STATUS_NO = "00";
	public static final String DEAL_STATUS_YES = "01";
	public static final String DEAL_STATUS_OUTSTOCK = "02";
	public static final String DEAL_STATUS_RECEIVE = "03";
	
	public static final Integer PROMOTER_CODE_LENGTH = 4;
	
	/**礼品类型 00 实物 01虚拟*/
	public final static String GIFT_TYPE = "com.gpcsoft.smallscale.pointmall.domain.Gift.giftType";
	public static final String REALLY_OBJECTS = "00";
	public static final String VIRTUAL_OBJECTS = "01";
	public static final String getGiftTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.GIFT_TYPE, str);
    }
	
    /**
     * 礼物的几个是否属性(目前其中文显示名称先定义为是和否)
     */
	public final static String GIFT_BOOLEAN = "com.gpcsoft.smallscale.pointmall.domain.Gift.boolean";
    public final static String YES="1";
    public final static String NO="0";
    public static final String getGiftBooleanCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.GIFT_BOOLEAN, str);
    }
    
    /**礼品快递方式 00 快递 01EMS*/
	public final static String GIFT_POST_TYPE = "com.gpcsoft.smallscale.pointmall.domain.Rule.fpostType";
	public static final String GIFT_POST_TYPE_FPOST = "00";
	public static final String GIFT_POST_TYPE_EMS = "01";
	public static final String getGiftPostTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(SmallscaleEnum.GIFT_POST_TYPE, str);
    }
	
}
